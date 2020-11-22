package com.example.instagramclone;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupLoginActivity extends AppCompatActivity {
    private EditText edtUserNameSignUp,edtPasswordSignUp,edtUserNameLogin,edtPasswordLogin , edtEmailSignUp ;
    private Button btnSignUp,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtUserNameSignUp =findViewById(R.id.edtUserNameSignUp);
        edtPasswordSignUp =findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin =findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin =findViewById(R.id.edtPasswordLogin);
        btnSignUp =findViewById(R.id.btnSignUp);
        btnLogin =findViewById(R.id.btnLogin);
        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.setEmail(edtEmailSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null) {
                            FancyToast.makeText(SignupLoginActivity.this, appUser.get("username") + " is SignUp Successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        }else {
                            FancyToast.makeText(SignupLoginActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                        }

                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null & e == null){
                            FancyToast.makeText(SignupLoginActivity.this, user.get("username") + " is Logged In Successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        }else {
                            FancyToast.makeText(SignupLoginActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                        }
                    }
                });
            }
        });
    }
}
