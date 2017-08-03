package com.example.juanm.garitasexpress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

// GARITAS EXPRESS - by Juan Padilla 8/2/2017
// minimum API 21: Android 5.0 (Lollipop)
public class MainActivity extends AppCompatActivity {

    private RadioGroup radioCountryGroup;
    private RadioButton radioCountryButton;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioCountryGroup =(RadioGroup)findViewById(R.id.radioGroup);
        radioCountryButton =(RadioButton)findViewById(R.id.radioButton);
        next =(Button)findViewById(R.id.button);

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
    }
}
