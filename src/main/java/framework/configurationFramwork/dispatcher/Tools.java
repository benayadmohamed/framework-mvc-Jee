package framework.configurationFramwork.dispatcher;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tools {
    private String convertFormDataToJson(String formData) {
        String[] params = formData.split("&");
        StringBuffer jsonData = new StringBuffer();

        for (String s : params) {
            jsonData.append("\"" + s.replace("=", "\":\"") + "\"" + ",");
        }
        return "{" + jsonData.substring(0, jsonData.length() - 1) + "}";
    }

    public Object inputStreamToObject(InputStream inputStream, Class aClass) {
        BufferedReader bR = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";

        StringBuilder responseStrBuilder = new StringBuilder();
        while (true) {
            try {
                if (!((line = bR.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            responseStrBuilder.append(line);
        }
        String data = responseStrBuilder.toString();
        if (!data.startsWith("{"))
            data = convertFormDataToJson(data);
        System.out.println("inputStream = [" + data + "]");
        JSONObject result = new JSONObject(data);
        Gson gson = new Gson();
        return gson.fromJson(result.toString(), aClass);
    }
}
