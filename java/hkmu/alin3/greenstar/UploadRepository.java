package hkmu.alin3.greenstar;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadRepository extends Thread{
    private static final String TAG = "UploadHandlerThread";
    final static String urlStr = "http:/47.243.66.25:8080/upload";
    public String content;
    private static String filePath;
    public static File xmlFile;
    public UploadRepository(String filePath, File xmlFile) {
        this.filePath = filePath;
        this.xmlFile = xmlFile;
    }

    public boolean request()  throws IOException{
        File pngFile = new File(filePath);
        System.out.println("TAG: " +pngFile.getAbsolutePath());
        OkHttpClient client = new OkHttpClient();
//        RequestBody requestXmlBody = new MultipartBody.Builder()
//                .addFormDataPart("title", "png")
//                .addFormDataPart("file", xmlFile.getName(), RequestBody.create(MediaType.get("multipart/form-data"), xmlFile)).build();
//        RequestBody requestPngBody = new MultipartBody.Builder()
//                .addFormDataPart("title", "png")
//                .addFormDataPart("file", pngFile.getName(), RequestBody.create(MediaType.get("multipart/form-data"), pngFile)).build();

        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("pngfile", pngFile.getName(), RequestBody.create(MediaType.get("multipart/form-data"), pngFile))
                .addFormDataPart("xmlfile", xmlFile.getName(), RequestBody.create(MediaType.get("multipart/form-data"), xmlFile))
                .build();

        Request request = new Request.Builder().url(urlStr).post(requestBody).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("response: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void run(){
        try {
            request();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

