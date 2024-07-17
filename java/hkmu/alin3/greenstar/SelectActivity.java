package hkmu.alin3.greenstar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button select1button =findViewById(R.id.select1button);
        select1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this,RecogActivity.class));
            }
        });
        Button select2button =findViewById(R.id.select2button);
        select2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this,TupianActivity.class));
            }
        });

        Button select3button =findViewById(R.id.detailBtn);
        select3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this,ResultActivity.class));
            }
        });
    }
}
