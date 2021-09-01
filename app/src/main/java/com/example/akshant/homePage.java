package com.example.akshant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class homePage extends AppCompatActivity {

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        FileInputStream fis = null;
        try {
            fis = openFileInput("example.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine())!= null) {
                sb.append(text).append("\n");
            }
            System.out.println("Yo!");
            System.out.println(text);
            if (text == null) {
                Intent activity2Intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2Intent);
            } else {
                Intent activity2Intent = new Intent(getApplicationContext(), MainActivityHindi.class);
                startActivity(activity2Intent);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Present");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        button1 = findViewById(R.id.englishStart);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String language = "english";
                Intent activity2Intent = new Intent(getApplicationContext(), loginEnglish.class);
                System.out.println(language);
                activity2Intent.putExtra("language", language);
                startActivity(activity2Intent);
            }
        });

        button2 = findViewById(R.id.hindiStart);
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent activity2Intent = new Intent(getApplicationContext(), loginHindi.class);
                startActivity(activity2Intent);
            }
        });
    }
}
