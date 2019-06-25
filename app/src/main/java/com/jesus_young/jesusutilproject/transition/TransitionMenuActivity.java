package com.jesus_young.jesusutilproject.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.jesus_young.jesusutilproject.R;

public class TransitionMenuActivity extends AppCompatActivity {

    private Button btnShare;
    private Button btnShare2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_menu);

//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setExitTransition(new Explode());

        this.btnShare = this.findViewById(R.id.transition_shared_activity);
        this.btnShare2 = this.findViewById(R.id.transition_shared_fragment);

        this.findViewById(R.id.transition_activity_change).setOnClickListener(listener);
        this.findViewById(R.id.transition_shared_activity).setOnClickListener(listener);
        this.findViewById(R.id.transition_shared_fragment).setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.transition_activity_change:
                    doTransitionActivity();
                    break;
                case R.id.transition_shared_activity:
                    doActivityShare();
                    break;
                case R.id.transition_shared_fragment:
            }
        }
    };

    /**
     * 转场动画
     */
    private void doTransitionActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition

            // xml转场动画布局
//            Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
//            getWindow().setExitTransition(slide);


            // 代码设置转场动画
            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);
            Explode explode = new Explode();
            explode.setDuration(1000);
            getWindow().setReenterTransition(explode);

            Intent intent = new Intent(this, Demo1activity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(TransitionMenuActivity.this).toBundle());
        }
    }

    /**
     * 界面共享元素动画
     */
    private void doActivityShare() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            Intent intent = new Intent(this, SharedDemoActivity.class);
            View sharedView = btnShare;
            View sharedView2 = btnShare2;
            String transName = getString(R.string.transition_share);
            String transName2 = getString(R.string.transition_share2);
            // 单个元素共享
//            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
//                  TransitionMenuActivity.this, sharedView, transName);

            // 多个元素共享
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                    TransitionMenuActivity.this,
                    Pair.create(sharedView, transName),
                    Pair.create(sharedView2, transName2));
            startActivity(intent, activityOptions.toBundle());
        }
    }
}
