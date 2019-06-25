package com.jesus_young.jesusutilproject.animation_test.property_animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.jesus_young.jesusutilproject.R;

public class AnimationActivity extends AppCompatActivity {

    private ImageButton ibMenu;
    private ImageButton ibBeach;
    private ImageButton ibData;
    private ImageButton ibHome;
    private ImageButton ibMsg;
    private ImageButton ibSetting;

    private boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        this.ibMenu = findViewById(R.id.anim_menu);
        this.ibBeach = findViewById(R.id.anim_menu_beach);
        this.ibData = findViewById(R.id.anim_menu_data);
        this.ibHome = findViewById(R.id.anim_menu_home);
        this.ibMsg = findViewById(R.id.anim_menu_msg);
        this.ibSetting = findViewById(R.id.anim_menu_setting);
        this.ibMenu.setOnClickListener(listener);
        this.ibBeach.setOnClickListener(listener);
        this.ibData.setOnClickListener(listener);
        this.ibHome.setOnClickListener(listener);
        this.ibMsg.setOnClickListener(listener);
        this.ibSetting.setOnClickListener(listener);


//        ObjectAnimator.ofFloat(ibMenu, "translationX", 0, 300).setDuration(500).start();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.anim_menu:
                    if (!isMenuOpen) {
                        doMenuOpen(ibBeach, 0);
                        doMenuOpen(ibData, 1);
                        doMenuOpen(ibHome, 2);
                        doMenuOpen(ibMsg, 3);
                        doMenuOpen(ibSetting, 4);
                    } else {
                        doMenuClose(ibBeach, 0);
                        doMenuClose(ibData, 1);
                        doMenuClose(ibHome, 2);
                        doMenuClose(ibMsg, 3);
                        doMenuClose(ibSetting, 4);
                    }
                    isMenuOpen = !isMenuOpen;
                    break;
                case R.id.anim_menu_beach:
                case R.id.anim_menu_data:
                case R.id.anim_menu_home:
                case R.id.anim_menu_msg:
                case R.id.anim_menu_setting:
                    Toast.makeText(AnimationActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void doMenuOpen(final View view, int index) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = (5 - index) * (Math.PI / (5 + 1));
        Log.e("tag", "第" + index + "个view的角度为：" + degree);
        int translationX = (int) (380 * Math.cos(degree));
        int translationY = (int) (-380 * Math.sin(degree));
        Log.e("tag", "X: " + translationX);
        Log.e("tag","translationX: " + translationX + "      translationY: " + translationY);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        );
        final Animation anima = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                view.startAnimation(anima);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setDuration(500).start();
    }

    private void doMenuClose(final View view, int index) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = (5 - index) * (Math.PI / (5 + 1));
        Log.e("tag", "第" + index + "个view的角度为：" + degree);
        Log.e("tag", Math.cos(0.5) + "");

        int translationX = (int) (380 * Math.cos(degree));
        int translationY = (int) (-380 * Math.sin(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        );
        final Animation anima = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                view.startAnimation(anima);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setDuration(500).start();
    }
}
