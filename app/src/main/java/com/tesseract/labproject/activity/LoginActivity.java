package com.tesseract.labproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tesseract.labproject.ProductListActivity;
import com.tesseract.labproject.R;

public class LoginActivity extends AppCompatActivity {

    public static String  username="No Name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText etUsername=(EditText) findViewById(R.id.etUsername);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=etUsername.getText().toString();
                startActivity(new Intent(getApplicationContext(),ProductListActivity.class));
                finish();
            }
        });

    }
}
