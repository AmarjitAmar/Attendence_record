package com.cbitts.attendence_record;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Verification extends AppCompatActivity {

   private FirebaseAuth auth;
    Button verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        auth = FirebaseAuth.getInstance();

        verification = (Button)findViewById(R.id.send);

        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth.getCurrentUser()!=null)
                {
                    auth.getCurrentUser().sendEmailVerification();
                    finish();
                }
            }
        });
    }
}
