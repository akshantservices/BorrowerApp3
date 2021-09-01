package com.example.akshant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

public class optSubmitEnglish extends AppCompatActivity {

    private EditText otpInput;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_submit_english);

        setupOTPInputs();
        String language = getIntent().getStringExtra("language");
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String phoneNumber = getIntent().getStringExtra("mobile");

        final Button buttonVerify = findViewById(R.id.submitOtp);
        otpInput = findViewById(R.id.otpEnglish);
        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otpInput.getText().toString().isEmpty()) {
                    Toast.makeText(optSubmitEnglish.this, "Enter valid OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (verificationId != null) {
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            otpInput.getText().toString()
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("mobile", phoneNumber);
                                intent.putExtra("language", language);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(optSubmitEnglish.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void setupOTPInputs() {
    }
}