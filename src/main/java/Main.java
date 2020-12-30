


import com.google.gson.JsonObject;
import com.google.gson.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

class serverHelper{
    static class JSONgenerator{
        static HashMap<String, String> output = new HashMap<>();
        static String getAsJsonString(){
            Gson gson = new Gson();
            String out = gson.toJson(output);
            output.clear();
            return out;
        }
    }

    static boolean testCon(){
        try {
            System.out.println("running test command");
            JSONgenerator.output.put("Test", "Test");
            String out = JSONgenerator.getAsJsonString();
            sendJSONStringViaPost(out);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static boolean sendJSONStringViaPost(String out){
        try {
            System.out.println("trying to send: " + out);
            URL url = new URL ("http://192.168.178.28:5001/PostOffer");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = out.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot sendJSONViaPost()");
            return false;
        }

    }

}



public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        serverHelper.testCon();
    }
}
