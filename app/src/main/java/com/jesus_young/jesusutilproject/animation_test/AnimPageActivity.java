package com.jesus_young.jesusutilproject.animation_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jesus_young.jesusutilproject.R;
import com.jesus_young.jesusutilproject.animation_test.layout_animation.LayoutAnimationActivity;
import com.jesus_young.jesusutilproject.animation_test.property_animation.AnimationActivity;
import com.jesus_young.jesusutilproject.animation_test.property_animation.PropertyActivity;

public class AnimPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_page);

        this.findViewById(R.id.anim_btn_property).setOnClickListener(onClickListener);
        this.findViewById(R.id.anim_btn_property2).setOnClickListener(onClickListener);
        this.findViewById(R.id.anim_btn_layout).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class clz = null;
            switch (v.getId()) {
                case R.id.anim_btn_property:
                    clz = AnimationActivity.class;
                    break;
                case R.id.anim_btn_property2:
                    clz = PropertyActivity.class;
                    break;
                case R.id.anim_btn_layout:
                    clz = LayoutAnimationActivity.class;
                    break;
            }
            if (clz != null) {
                Intent intent = new Intent(AnimPageActivity.this, clz);
                startActivity(intent);
            }
        }
    };
}
