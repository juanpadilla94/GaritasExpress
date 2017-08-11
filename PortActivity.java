package com.garitasexpress.app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

// GARITAS EXPRESS - by Juan Padilla 8/2/2017
public class PortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);
        // TEXTS
        // port name
        TextView portNameText = (TextView) findViewById(R.id.port_name_text);
        portNameText.setText(getIntent().getStringExtra("name"));
        portNameText.setTextColor(Color.parseColor("#ffffff"));
        portNameText.setBackgroundColor(Color.parseColor("#4272b7"));

        // hours/date
        TextView hoursDateText = (TextView) findViewById(R.id.hours_date_text);
        hoursDateText.setText("Hours: " + getIntent().getStringExtra("hours") + " - "
                + getIntent().getStringExtra("date"));
        hoursDateText.setTextColor(Color.parseColor("#000000"));
        hoursDateText.setBackgroundColor(Color.parseColor("#ffffff"));

        // DYNAMICALLY ADDED VIEWS
        // layout for dynamically adde views
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        LinearLayout layout = (LinearLayout)findViewById(R.id.port_layout);

        // PASSENGER TAB
        TextView passengerView = new TextView(this);
        passengerView.setLayoutParams(params);
        passengerView.setGravity(Gravity.CENTER);
        passengerView.setTextColor(Color.parseColor("#ffffff"));
        passengerView.setBackgroundColor(Color.parseColor("#4272b7"));
        passengerView.setTextSize(25);
        passengerView.setText("VEHICLES");
        layout.addView(passengerView);

        // NAMES OF LANES TO DISPLAY
        String[] nameArr = {"Standard Lanes", "Sentri Lanes", "Ready Lanes"};
        // TEXTS ADDED
        if(!"N/A".equals(getIntent().getStringExtra("passenger_max"))
                && !"Lanes Closed".equals(getIntent().getStringExtra("passenger_max"))) {
            // count of lanes/ports/open
            int count = 0;
            // NAME
            for(int i = 0; i < 3; i++) {
                // STANDARD; READYLANE; SENTRI
                if (getIntent().getStringArrayExtra("op_status") != null
                        && getIntent().getStringArrayExtra("op_status")[i] != null
                        && !"N/A".equals(getIntent().getStringArrayExtra("op_status")[i].trim())
                        && !"Update Pending".equals(getIntent().getStringArrayExtra("op_status")[i].trim())
                        && !"Lanes Closed".equals(getIntent().getStringArrayExtra("op_status")[i].trim())) {
                    count++;
                    // NAME
                    TextView standardView = new TextView(this);
                    standardView.setLayoutParams(params);
                    standardView.setText(nameArr[i]);
                    standardView.setTextColor(Color.parseColor("#000000"));
                    standardView.setBackgroundColor(Color.parseColor("#ffffff"));
                    standardView.setGravity(Gravity.CENTER);
                    standardView.setTextSize(20);
                    standardView.setBackgroundResource(R.drawable.bordertop);
                    layout.addView(standardView);
                    // TIMES
                    TextView timeView = new TextView(this);
                    timeView.setLayoutParams(params);
                    timeView.setText("  " + getIntent().getStringArrayExtra("update_time")[i]);
                    timeView.setTextColor(Color.parseColor("#000000"));
                    timeView.setBackgroundColor(Color.parseColor("#ffffff"));
                    timeView.setGravity(Gravity.LEFT);
                    timeView.setTextSize(20);
                    layout.addView(timeView);
                    // DELAY
                    TextView delayView = new TextView(this);
                    delayView.setLayoutParams(params);
                    delayView.setText("  delay of: " +
                            getIntent().getStringArrayExtra("delay_minutes")[i] + " min.");
                    delayView.setBackgroundColor(Color.parseColor("#ffffff"));
                    delayView.setTextColor(Color.parseColor("#000000"));
                    delayView.setGravity(Gravity.LEFT);
                    delayView.setTextSize(20);
                    layout.addView(delayView);
                    // LANES OPEN
                    TextView lanesView = new TextView(this);
                    lanesView.setLayoutParams(params);
                    lanesView.setText("  " + getIntent().getStringArrayExtra("lanes_open")[i] +
                            " lanes open");
                    lanesView.setTextColor(Color.parseColor("#000000"));
                    lanesView.setBackgroundColor(Color.parseColor("#ffffff"));
                    lanesView.setGravity(Gravity.LEFT);
                    lanesView.setTextSize(20);
                    lanesView.setBackgroundResource(R.drawable.borderbottom);
                    layout.addView(lanesView);
                }
                // UPDATE PENDING - NOTE to USER
                else if(getIntent().getStringArrayExtra("op_status") != null
                        && getIntent().getStringArrayExtra("op_status")[i] != null
                        && "Update Pending".equals(getIntent().getStringArrayExtra("op_status")[i].trim())) {
                    passengerView.setText("VEHICLES - CURRENTLY UPDATING");
                }
            }
            // count of times views passed
            if(count == 0)
                passengerView.setText("VEHICLES - CLOSED");
        }
        // CLOSED
        else {
            passengerView.setText("VEHICLES - CLOSED");
        }

        // PEDESTRIAN
        TextView pedestrianView = new TextView(this);
        pedestrianView.setLayoutParams(params);
        // GRAY
        pedestrianView.setGravity(Gravity.CENTER);
        pedestrianView.setTextSize(25);
        pedestrianView.setText("PEDESTRIANS");
        pedestrianView.setTextColor(Color.parseColor("#ffffff"));
        pedestrianView.setBackgroundColor(Color.parseColor("#4272b7"));
        layout.addView(pedestrianView);
        // TEXTS ADD

        // SWITHC LANES
        nameArr[1] = "Ready Lanes";
        // OPEN
        if(!"N/A".equals(getIntent().getStringExtra("pedestrian_max"))
                && !"Lanes Closed".equals(getIntent().getStringExtra("pedestrian_max"))) {
            // count of number of times views passed
            int count = 0;
            for(int i = 0; i < 2; i++) {
                // STANDARD; READYLANE;
                if (getIntent().getStringArrayExtra("op_status") != null
                        && getIntent().getStringArrayExtra("op_status")[i + 3] != null
                        && !"N/A".equals(getIntent().getStringArrayExtra("op_status")[i + 3].trim())
                        && !"Update Pending".equals(getIntent().getStringArrayExtra("op_status")[i + 3].trim())
                        && !"Lanes Closed".equals(getIntent().getStringArrayExtra("op_status")[i + 3].trim())) {
                    count++;
                    // NAME
                    TextView standardView = new TextView(this);
                    standardView.setLayoutParams(params);
                    standardView.setText(nameArr[i]);
                    standardView.setBackgroundColor(Color.parseColor("#ffffff"));
                    standardView.setTextColor(Color.parseColor("#000000"));
                    standardView.setGravity(Gravity.CENTER);
                    standardView.setTextSize(20);
                    standardView.setBackgroundResource(R.drawable.bordertop);
                    layout.addView(standardView);
                    // TIMES
                    TextView timeView = new TextView(this);
                    timeView.setLayoutParams(params);
                    timeView.setText("  " + getIntent().getStringArrayExtra("update_time")[i + 3]);
                    timeView.setBackgroundColor(Color.parseColor("#ffffff"));
                    timeView.setTextColor(Color.parseColor("#000000"));
                    timeView.setGravity(Gravity.LEFT);
                    timeView.setTextSize(20);
                    layout.addView(timeView);
                    // DELAY
                    TextView delayView = new TextView(this);
                    delayView.setLayoutParams(params);
                    delayView.setText("  delay of: " +
                            getIntent().getStringArrayExtra("delay_minutes")[i + 3] + " min.");
                    delayView.setBackgroundColor(Color.parseColor("#ffffff"));
                    delayView.setTextColor(Color.parseColor("#000000"));
                    delayView.setGravity(Gravity.LEFT);
                    delayView.setTextSize(20);
                    layout.addView(delayView);
                    // LANES OPEN
                    TextView lanesView = new TextView(this);
                    lanesView.setLayoutParams(params);
                    lanesView.setText("  " + getIntent().getStringArrayExtra("lanes_open")[i + 3] +
                            " lanes open");
                    lanesView.setBackgroundColor(Color.parseColor("#ffffff"));
                    lanesView.setTextColor(Color.parseColor("#000000"));
                    lanesView.setGravity(Gravity.LEFT);
                    lanesView.setTextSize(20);
                    lanesView.setBackgroundResource(R.drawable.borderbottom);
                    layout.addView(lanesView);
                }
                // UPDATE PENDING - NOTE to USER
                else if(getIntent().getStringArrayExtra("op_status") != null
                        && getIntent().getStringArrayExtra("op_status")[i + 3] != null
                        && "Update Pending".equals(getIntent().getStringArrayExtra("op_status")[i + 3].trim())) {
                    pedestrianView.setText("PEDESTRIANS - CURRENTLY UPDATING");
                }
            }
            // if no views added, check ped closed
            if(count == 0)
                pedestrianView.setText("PEDESTRIANS - CLOSED");
        }
        else {
            pedestrianView.setText("PEDESTRIANS - CLOSED");
        }

        // port status
        TextView portStatus = (TextView) findViewById(R.id.port_status);
        // white
        portStatus.setTextColor(Color.parseColor("#ffffff"));
        // CLOSED
        if("VEHICLES - CLOSED".equals(passengerView.getText())
                && "PEDESTRIANS - CLOSED".equals(pedestrianView.getText())) {
            // CLOSED
            portStatus.setText("CLOSED");
            // RED
            portStatus.setBackgroundColor(Color.parseColor("#ff0000"));
        }
        // OPEN/CURRENTLY UPDATING
        else {
            portStatus.setText("OPEN");
            // GREEN
            portStatus.setBackgroundColor(Color.parseColor("#00ff00"));
        }
    }
}
