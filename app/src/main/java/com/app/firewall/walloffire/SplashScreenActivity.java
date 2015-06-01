package com.app.firewall.walloffire;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;



/**
 * Created by Tyrell on 5/27/2015.
 */
public class SplashScreenActivity extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // final Intent mainIntent = new Intent(LaunchActivity.this, HomeActivity.class);
                //LaunchActivity.this.startActivity(mainIntent);
                //LaunchActivity.this.finish();
                // Toast.makeText(getApplicationContext(), "start now", Toast.LENGTH_SHORT).show();
                Intent MainActivitys = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(MainActivitys);
                finish();
            }
        }, 5000);



        // Toast.makeText(getApplicationContext(), "start now", Toast.LENGTH_SHORT).show();
    }


}
