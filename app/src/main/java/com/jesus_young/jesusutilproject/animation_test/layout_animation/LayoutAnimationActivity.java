package com.jesus_young.jesusutilproject.animation_test.layout_animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jesus_young.jesusutilproject.R;

public class LayoutAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);

//        // 代码添加方式
//        final LinearLayout linearLayout = this.findViewById(R.id.layout_animation_ll_);
//        linearLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                addLayoutAnimation(linearLayout);
//            }
//        });
    }

    /**
     * 通过代码添加LayoutAnimation动画
     *
     * @param view 根布局
     */
    private void addLayoutAnimation(ViewGroup view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tran_left);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setDelay(0.3f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        view.setLayoutAnimation(layoutAnimationController);
        view.setLayoutAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(LayoutAnimationActivity.this, "end!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
