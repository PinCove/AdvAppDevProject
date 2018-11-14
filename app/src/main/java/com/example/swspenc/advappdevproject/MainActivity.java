package com.example.swspenc.advappdevproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        EditText input1 = (EditText) findViewById(R.id.editTextUName);
        String UName = input1.getText().toString();
        EditText input2 = (EditText) findViewById(R.id.editTextPassword);
        String Password = input2.getText().toString();

        SqlStuff sql = new SqlStuff();
        sql.connectionclass();
    }

    public void RegisterBtn (View view) {

    }
}
