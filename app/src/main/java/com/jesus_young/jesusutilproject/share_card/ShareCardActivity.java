package com.jesus_young.jesusutilproject.share_card;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jesus_young.jesusutilproject.R;

public class ShareCardActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 禁止对应用进行屏幕截图
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_share_card);

        this.imageView = findViewById(R.id.share_card_iv_show);

        this.findViewById(R.id.share_card_btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createShareCardImg();
            }
        });
    }

    private void createShareCardImg() {
        View view = LayoutInflater.from(this).inflate(R.layout.share_card_layout, null);
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        imageView.setVisibility(View.VISIBLE);
        this.imageView.setImageBitmap(bitmap);
    }

}
