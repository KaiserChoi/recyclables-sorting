package hkmu.alin3.greenstar;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ResultActivity extends AppCompatActivity {
    private static final String RESULTTAG = "detail";
    private static final String SUMMARY = "summary.json";
    private static final String MODEL1 = "yolov7-tiny.json";
    private static final String MODEL2 = "yolov7-v2.json";
    private static final String MODEL3 = "yolov7-v1.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView showDetail = (TextView) findViewById(R.id.detail);
        showDetail.setMovementMethod(ScrollingMovementMethod.getInstance());
        showDetail.setText(showDetail(SUMMARY));


        Button model1Btn = findViewById(R.id.model1_detail);
        model1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail.setText(showDetail(MODEL1));
            }
        });

        Button model2Btn = findViewById(R.id.model2_detail);
        model2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDetail.setText(showDetail(MODEL2));
            }
        });

        Button model3Btn = findViewById(R.id.model3_detail);
        model3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail.setText(showDetail(MODEL3));
            }
        });

        Button summaryBtn = findViewById(R.id.summary_detail);
        summaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail.setText(showDetail(SUMMARY));
            }
        });

        Button backBtn = findViewById(R.id.backbutton2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String showDetail(String modelId){
        String out = "";

        Repository repo = new Repository(modelId);
        repo.start();
        try {
            repo.join();
            JSONArray jsonArray = new JSONArray(repo.getContent());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = jsonObject.getString(key);

                    out += String.format("    %-18s", key) + " : " + value + "\n";
                    System.out.println(String.format("    %-18s", key) + " :" + value + "\n");
                }
            }
        } catch (InterruptedException ex) {
            Log.e(RESULTTAG, "InterruptedException: " + ex.getMessage());
        } catch (JSONException ex) {
            Log.e(RESULTTAG, "JSONException: " + ex.getMessage());
        }
        return out;
    }
}

