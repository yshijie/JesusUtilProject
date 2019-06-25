package com.jesus_young.jesusutilproject.transition;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;

import com.jesus_young.jesusutilproject.R;

public class Demo1activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1activity);

//        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
//        getWindow().setEnterTransition(fade);

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }
}
