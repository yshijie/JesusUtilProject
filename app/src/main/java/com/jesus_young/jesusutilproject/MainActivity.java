package com.jesus_young.jesusutilproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jesus_young.jesusutilproject.animation_test.AnimPageActivity;
import com.jesus_young.jesusutilproject.share_card.ShareCardActivity;
import com.jesus_young.jesusutilproject.shortcuts.ShortcutsActivity;
import com.jesus_young.jesusutilproject.transition.SharedDemoActivity;
import com.jesus_young.jesusutilproject.transition.TransitionMenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_btn_share_card).setOnClickListener(onClickListener);
        findViewById(R.id.main_btn_animation_test).setOnClickListener(onClickListener);
        findViewById(R.id.main_btn_transition_anim).setOnClickListener(onClickListener);
        findViewById(R.id.main_btn_shortcuts).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class clz = null;
            switch (v.getId()) {
                case R.id.main_btn_share_card:
                    clz = ShareCardActivity.class;
                    break;
                case R.id.main_btn_animation_test:
                    clz = AnimPageActivity.class;
                    break;
                case R.id.main_btn_transition_anim:
                    clz = TransitionMenuActivity.class;
                    break;
                case R.id.main_btn_shortcuts:
                    clz = ShortcutsActivity.class;
                    break;
            }
            if (clz != null) {
                Intent intent = new Intent(MainActivity.this, clz);
                startActivity(intent);
            }
        }
    };
}
