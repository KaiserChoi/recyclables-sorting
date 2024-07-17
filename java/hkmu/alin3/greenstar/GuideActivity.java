package hkmu.alin3.greenstar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);

//        ImageView guide = findViewById(R.id.guide_imgView);
//
//
//        Bitmap btm1 = BitmapFactory.decodeResource(GuideActivity.this.getResources(), R.mipmap.userguide);
//        //点击后变大的位图：
//        // 获得图片的宽高
//        int width = btm1.getWidth();
//        int height = btm1.getHeight();
//        // 计算缩放比例
//        float scaleWidth = ((float) 600) / width;
//        float scaleHeight = ((float) 25000) / height;
//        // 取得想要缩放的matrix参数
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        // 得到新的图片    www.2cto.com
//        Bitmap newbm = Bitmap.createBitmap(btm1, 0, 0, width, height, matrix, false);
//
//        BitmapDrawable bd2= new BitmapDrawable(newbm);
//        guide.setBackgroundDrawable(bd2);
        Button backBtn = findViewById(R.id.backBtn_userGuide);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
