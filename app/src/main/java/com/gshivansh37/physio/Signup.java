package com.gshivansh37.physio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Signup extends AppCompatActivity {

//    private GoogleSignInClient googleSignInClient;
//    private SignInButton googleSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button sign_in = findViewById(R.id.sign_in);
//        googleSignInButton = findViewById(R.id.sign_in_button);

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        googleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        googleSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signInIntent = googleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, 101);
//            }
//        });


                sign_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent next = new Intent(Signup.this, MainActivity.class);
                        startActivity(next);

                    }
                });
    }
}
