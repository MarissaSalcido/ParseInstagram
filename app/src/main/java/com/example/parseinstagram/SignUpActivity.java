package com.example.parseinstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";

    EditText etNewUsername;
    EditText etNewEmail;
    EditText etNewPassword;
    Button btnRegisterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNewUsername = findViewById(R.id.etNewUsername);
        etNewEmail = findViewById(R.id.etNewEmail);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnRegisterUser = findViewById(R.id.btnRegisterUser);

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNewEmail.getText() == null || etNewPassword.getText() == null || etNewUsername.getText() == null){
                    Toast.makeText(SignUpActivity.this, "Looks like you forgot to fill out a field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser user = new ParseUser();
                user.setUsername(etNewUsername.getText().toString());
                user.setPassword(etNewPassword.getText().toString());
                user.setEmail(etNewEmail.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            Log.d(TAG, "Error in signing up user");
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
