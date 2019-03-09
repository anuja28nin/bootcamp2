package com.dsc.android.bootcamp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etName, etPassword, etEmail, etConfirmPassword;
    private Button btnRegister,btnLogin;
    private String name, email, password, confirmPassword;
    private TinyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new TinyDB(this);
        initView();

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    //Initialising the UI Components here
    private void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                getInfo();
                register();
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    private void getInfo() {
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString();
        confirmPassword = etConfirmPassword.getText().toString();
    }

    private void register() {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this,"Empty Fields", Toast.LENGTH_LONG).show();
        }
        else {
            if (password.equals(confirmPassword)) {
                db.putString("name",name);
                db.putString("email",email);
                db.putString("password",password);
                Toast.makeText(this, "User Registered", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this,"Password did not Match!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
