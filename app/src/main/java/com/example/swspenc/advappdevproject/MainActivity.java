package com.example.swspenc.advappdevproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginBtn (View view){
        //Delete Me
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);

        SqlStuff sql = new SqlStuff();
        sql.connectionclass();
    }

    public void RegisterBtn (View view) {

    }
}
