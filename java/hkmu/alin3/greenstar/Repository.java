package hkmu.alin3.greenstar;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Repository extends Thread{
    private static final String TAG = "JsonHandlerThread";
    final static String urlSrt = "http:/47.243.66.25:8080/modelDetail/";

    public String content;

    private static String modelId = "summary.json";
    public Repository(String modelId){
        this.modelId = modelId;
    }
    public void findAll(){
        String schoolStr = request();
        if (schoolStr == null || schoolStr.length() == 0){
            Log.e(TAG, "Couldn't get model information from server.");
        }
        this.content = schoolStr;
    }

    public String getContent(){
        return this.content;
    }

    public static String request(){
        String response = null;
        try{
            URL url = new URL(urlSrt + modelId);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            System.out.println(con.getResponseCode());
            InputStream inputStream = new BufferedInputStream(con.getInputStream());
            response = inputStreamToString(inputStream);
        }catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        return response;
    }

    public static String inputStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }

    public void run(){
        findAll();
    }
}

