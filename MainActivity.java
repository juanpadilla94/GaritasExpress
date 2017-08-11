package com.garitasexpress.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

// GARITAS EXPRESS - by Juan Padilla 8/2/2017 - Mobile Android App
// tracks border wait times of all Canada->USA and Mexico->USA ports of entries
// minimum API 21: Android 5.0 (Lollipop)
public class MainActivity extends AppCompatActivity {

    // group for both countries
    private RadioGroup radioCountryGroup;
    // country button selected
    private RadioButton radioCountryButton;
    // button that takes you to country activity based on selection
    private Button next;
    // terms and conditions/copyright button
    private Button disclosure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioCountryGroup =(RadioGroup)findViewById(R.id.radioGroup);
        radioCountryButton =(RadioButton)findViewById(R.id.radioButton);
        // button for next ports screen
        next =(Button)findViewById(R.id.button);
        // disclosure
        disclosure = (Button) findViewById(R.id.button2);

        // controller - user chooses option
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedID = radioCountryGroup.getCheckedRadioButtonId();
                radioCountryButton = (RadioButton)findViewById(selectedID);
                Intent intent;
                // check if a radio button is checked; otherwise wait till selection and then
                // user can select next
                if(radioCountryButton != null) {
                    // user checked canada radio button and clicked next
                    if ("Canada".equals(radioCountryButton.getText())) {
                        intent = new Intent(MainActivity.this, CanadaActivity.class);
                        // canada
                        intent.putExtra("country", "Canadian");
                        startActivity(intent);
                    }
                    // user checked Mexico radio button and clicked next
                    else if ("Mexico".equals(radioCountryButton.getText())) {
                        intent = new Intent(MainActivity.this, MexicoActivity.class);
                        // mexico
                        intent.putExtra("country", "Mexican");
                        startActivity(intent);
                    }
                }
            }
        });
        // disclosure with terms of service and copyright
        disclosure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisclosureActivity.class);
                startActivity(intent);
            }
        });
    }
}
