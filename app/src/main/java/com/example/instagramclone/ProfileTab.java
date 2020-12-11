package com.example.instagramclone;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileSport = view.findViewById(R.id.edtProfileSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null){
            edtProfileName.setText("");
        }else {
            edtProfileName.setText(parseUser.get("profileName")+"");
        }
        if (parseUser.get("profileBio")==null){
            edtProfileBio.setText("");
        }else {
            edtProfileBio.setText(parseUser.get("profileBio")+"");
        }
        if (parseUser.get("profileProfession")==null){
            edtProfileProfession.setText("");
        }else {
            edtProfileProfession.setText(parseUser.get("profileProfession")+"");
        }
        if (parseUser.get("profileHobbies")==null){
        }else {
            edtProfileHobbies.setText(parseUser.get("profileHobbies")+"");
        }
        if (parseUser.get("profileSport")==null){
            edtProfileSport.setText("");
        }else {
            edtProfileSport.setText(parseUser.get("profileSport")+"");
        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("profileBio",edtProfileBio.getText().toString());
                parseUser.put("profileProfession",edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies",edtProfileHobbies.getText().toString());
                parseUser.put("profileSport",edtProfileSport.getText().toString());
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating Info");
                progressDialog.show();

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){

                            FancyToast.makeText(getContext(), "Info Updated",
                                    Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                        }else {
                            FancyToast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });
        return view;

    }
}