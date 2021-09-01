package com.example.akshant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class loginEnglish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String language = getIntent().getStringExtra("language");
        System.out.println(language);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_english);

        final EditText inputMobile = findViewById(R.id.phoneNumber);
        final EditText name = findViewById(R.id.name);
        final EditText age = findViewById(R.id.age);
        Button buttonGetOtp = findViewById(R.id.getOtp);

        buttonGetOtp.setOnClickListener(v -> {
            if (inputMobile.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                return;
            }
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                     "+91" + inputMobile.getText().toString(),
                    60,
                    TimeUnit.SECONDS,
                    loginEnglish.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        @Override
                        public void onVerificationCompleted(@NonNull @org.jetbrains.annotations.NotNull PhoneAuthCredential phoneAuthCredential) {

                        }

                        @Override
                        public void onVerificationFailed(@NonNull @org.jetbrains.annotations.NotNull FirebaseException e) {
                            Toast.makeText(loginEnglish.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull @org.jetbrains.annotations.NotNull String s, @NonNull @org.jetbrains.annotations.NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            Intent intent = new Intent(getApplicationContext(), optSubmitEnglish.class);
                            intent.putExtra("mobile", inputMobile.getText().toString());
                            intent.putExtra("verificationId", s);
                            intent.putExtra("language", language);
                            intent.putExtra("name", name.getText().toString());
                            intent.putExtra("age", age.getText().toString());
                            startActivity(intent);
                        }
                    }
            );
        });
    }
}