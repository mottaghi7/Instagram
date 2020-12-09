package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLoginEmail , edtLoginPassword;
    private Button logIn , signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOG IN");
        edtLoginEmail = findViewById(R.id.edtLogInEmail);
        edtLoginPassword = findViewById(R.id.edtLogInpassword);
        logIn = findViewById(R.id.btnLogInActivity);
        signUp = findViewById(R.id.btnSignUpActivity);

        logIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

        if (ParseUser.getCurrentUser()!=null) {
            ParseUser.getCurrentUser();
            ParseUser.logOut();
        }

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btnLogInActivity:
               ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                   @Override
                   public void done(ParseUser user, ParseException e) {
                       if (user!= null && e==null) {
                           FancyToast.makeText(LoginActivity.this,user.getUsername() + "LogIn Successfully" ,
                                   Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                           transitionActivity();
                       }
                   }
               });
               break;
           case R.id.btnSignUpActivity:
               break;
       }

    }
    private void transitionActivity(){
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}