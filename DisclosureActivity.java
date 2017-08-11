package com.garitasexpress.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DisclosureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclosure);

        // TEXTS
        // disclosure
        TextView disclosureText = (TextView) findViewById(R.id.terms_content_text);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("terms.txt")));
            String line = reader.readLine();
            String output = "";
            while (line != null) {
                output = output + line + "\n";
                line = reader.readLine();
            }
            disclosureText.setText(output);
        }
        catch (IOException ex) {
            System.out.println("io exception: error in terms and conditions reader");
        }
    }
}
