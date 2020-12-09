package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.LoginActivity;
import com.example.instagramclone.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName, edtEmail, edtPassword;
    private Button btnLogIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        edtName = findViewById(R.id.edtName);
//        txtData =(TextView) findViewById(R.id.txtDataaa);
//        btnData = findViewById(R.id.btnData);
//        txtBB = findViewById(R.id.txtBB);
//        btnTransition = findViewById(R.id.btnTransition);
//        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
//        edtPunchPower = findViewById(R.id.edtPunchPower);
//        edtKickSpeed = findViewById(R.id.edtKickSpeed);
//        edtKickPower = findViewById(R.id.edtKickPower);
//        txtData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
//                parseQuery.getInBackground("83LrWyoN4I", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if (object != null && e == null) {
//                            txtData.setText(object.get("name") + "-" + object.get("punchPower"));
//                        }
//                    }
//                });
//            }
//        });
//        btnData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                allKickBoxers = "";
//                ParseQuery<ParseObject> allData = ParseQuery.getQuery("KickBoxer");
//                allData.whereGreaterThan("punchPower" , 100);
//                //allData.whereGreaterThanOrEqualTo("punchPower" , 3000);
//                allData.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//                        if (e == null) {
//                            if (objects.size()>0){
//                                for (ParseObject kickboxer : objects){
//                                    allKickBoxers = allKickBoxers + kickboxer.get("name")  +"\n" ;
//                                    txtBB.setText(kickboxer.get("punchPower")+"\n"+"");
//                                }
//                                FancyToast.makeText(MainActivity.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
//
//                            }else {
//                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
//
//                            }
//                        }
//                    }
//                });
//            }
//        });
//
//
//        btn = findViewById(R.id.btn);
//        btn.setOnClickListener(MainActivity.this);
//
//        btnTransition.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,SignupLoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        try {
//
//
//            final ParseObject KickBoxer = new ParseObject("KickBoxer");
//            KickBoxer.put("name", edtName.getText().toString());
//            KickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
//            KickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
//            KickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
//            KickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
//            KickBoxer.saveInBackground(new SaveCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if (e == null) {
//                        FancyToast.makeText(MainActivity.this, KickBoxer.get("name") + "is Saved to Server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
//                    } else {
//                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
//
//                    }
//                }
//            });
//        } catch (Exception e) {
//            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
//
//        }
//
//
        setTitle("Sign Up");
        edtEmail = findViewById(R.id.edtTextEmail);
        edtUserName = findViewById(R.id.edtTextUsername);
        edtPassword = findViewById(R.id.edtTextPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);


        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser();
//            ParseUser.logOut();
            transitionActivity();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSignUp:
                final ParseUser parseUser = new ParseUser();
                parseUser.setEmail(edtEmail.getText().toString());
                parseUser.setUsername(edtUserName.getText().toString());
                parseUser.setPassword(edtPassword.getText().toString());
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing Up" + edtUserName.getText().toString());
                progressDialog.show();
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(MainActivity.this, parseUser.getUsername() + "Is Signed Up",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            transitionActivity();
                        } else {
                            FancyToast.makeText(MainActivity.this, "There was An Error" + e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                        progressDialog.dismiss();
                    }
                });
                break;
            case R.id.btnLogIn:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
    private void transitionActivity(){
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}