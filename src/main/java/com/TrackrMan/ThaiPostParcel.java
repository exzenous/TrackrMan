package com.TrackrMan;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class ThaiPostParcel extends Parcel {

    public ThaiPostParcel(String name, String trackCode) {
        super(name, trackCode);
        setVendorTag("thailandpost");
    }

    public Boolean trackThis(){
        String tokenFirst = null , status = null, json = null;

        // Turn off SSL Validation Check (stupid!)
        try {
            super.turnOffSSLCheck();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        try {
            // Request 1st Layer API; Get Token First!
            tokenFirst = requestToken();
            // Request 2st Layer API; Get Item Status
            json = requestStatus( tokenFirst, this.getTrackCode() );
            this.setJsonString( json );
            // Request 1st Layer API; Get Token First!
            status = decodeJSON( json, this.getTrackCode() );
        } catch (Exception ignored) { }

        System.out.println("This Parcel Status: " + status);

        if (status != null){
            this.setRealStatus(status);
            char firstNumCode = (status.charAt(0));
            char lastNumCode = status.charAt(2);
            switch (firstNumCode){
                case '1':
                    this.setStatus("accepted");
                    break;
                case '2' :
                    this.setStatus("transport");
                    break;
                case '3' :
                    this.setStatus("delivery");
                    break;
                case '4' :
                    this.setStatus("unsuccess");
                    break;
                case '5' :
                    this.setStatus("success");
                    break;
                default:
                    this.setStatus("fail");
                    break;
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void setRealStatus(String realStatus) {
        String finalCode;
        switch (realStatus){
            case "101":
                super.setRealStatus("Being\nPosted");
                break;
            case "102": case "103" :
                super.setRealStatus("Posted");
                break;
            case "201":
                super.setRealStatus("In\nTransit");
                break;
            case "203":
                super.setRealStatus("Being\nReturned");
                break;
            case "301": case "302":
                super.setRealStatus("Being\nDelivered");
                break;
            case "401":
                super.setRealStatus("Failed\nDelivery");
                break;
            case "501":
                super.setRealStatus("Final\nDelivery");
                break;
            default:
                super.setRealStatus("Unknown");
                break;
        }
    }

    public String requestToken() throws Exception {

        // Init Link
        URL url = new URL("https://trackapi.thailandpost.co.th/post/api/v1/authenticate/token");
        String token = "Token KGDcUzZVD&RnRMH;QmUIC/T?M6WXJ&D=FmQfRvMGKET+H~ZpG5KQDrP0SPFiF*CvI4H2U7F=N5Q*HxAkH5LeUI2JyA1BLZ=N1O!";

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Authorization",token);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("Accept","application/json");

        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        System.out.println("Request Token Connection Status Code: " + connection.getResponseCode());

        // Fetch Data as String One Character per Time
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String charIn;
        StringBuffer reposond = new StringBuffer();

        while ((charIn = in.readLine()) != null) {
            reposond.append(charIn);
        }
        in.close();
        connection.disconnect();

        // Convert to JSONObject to Get Token
        JSONObject realJSON = new JSONObject(reposond.toString());
        String tokenToRealAPI = "Token " + realJSON.get("token").toString();

        return tokenToRealAPI;
    }

    public String requestStatus(String prevToken, String code) throws Exception {

        // Init Link
        URL url = new URL("https://trackapi.thailandpost.co.th/post/api/v1/track");
        String bodyParems = "{\n" +
                "   \"status\": \"all\",\n" +
                "   \"language\": \"EN\",\n" +
                "   \"barcode\": [\n" +
                "       \"" + code + "\"\n" +
                "  ]\n" +
                "}";

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Authorization",prevToken);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("Accept","application/json");

        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // Init Body
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(bodyParems.getBytes());
        os.flush();
        os.close();

        System.out.println("Get Item Connection Status Code: " + connection.getResponseCode());

        // Fetch Data as String One Character per Time
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String charIn;
        StringBuffer response = new StringBuffer();

        while ((charIn = in.readLine()) != null) {
            response.append(charIn);
        }
        in.close();
        connection.disconnect();

        return response.toString();

    }

    public String decodeJSON(String response, String code){
        // Convert to JSONObject to Get Status
        JSONObject realJSON = new JSONObject(response);
        JSONObject responseJSON = realJSON.getJSONObject("response");
        JSONObject itemsJSON = responseJSON.getJSONObject("items");

        // API can do multiple items but we need only one
        JSONArray itemStatusArray = itemsJSON.getJSONArray(code);
        int statusNum = itemStatusArray.length();
        if (statusNum > 0) {
            JSONObject lastStatus = (JSONObject) itemStatusArray.get(statusNum - 1);
            setRealStatus(lastStatus.getString("status_description"));
            return lastStatus.getString("status");
        }else{
            return null;
        }
    }

}
