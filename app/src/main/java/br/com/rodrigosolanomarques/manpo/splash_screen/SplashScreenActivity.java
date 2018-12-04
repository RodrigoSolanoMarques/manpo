package br.com.rodrigosolanomarques.manpo.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.rodrigosolanomarques.manpo.PrincipalActivity;
import br.com.rodrigosolanomarques.manpo.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
