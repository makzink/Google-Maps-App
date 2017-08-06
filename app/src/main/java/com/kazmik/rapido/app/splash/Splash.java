package com.kazmik.rapido.app.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kazmik.rapido.app.R;
import com.kazmik.rapido.app.home.Home;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent home = new Intent(Splash.this, Home.class);
        startActivity(home);
    }
}
