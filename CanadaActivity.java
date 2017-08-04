package com.example.juanm.garitasexpress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

// GARITAS EXPRESS - by Juan Padilla 8/2/2017
public class CanadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // model with data
        Model m = new Model(getIntent().getStringExtra("country"));
        m.generateData();
        ArrayList<Port> portList = m.getPortsList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canada);

        // buttons in layout fashion
        LinearLayout layout = (LinearLayout)findViewById(R.id.container);
        for(int i = 0; i < portList.size(); i++) {
            final Button button = new Button(this);
            button.setId(i);
            final String text;
            final String hours;
            final String date;
            final String portStatus;
            final String passengerMax;
            final String pedestrianMax;
            final String[] opStatus;
            final String[] updateTime;
            final String[] lanesOpen;
            final String[] delayMinutes;
            // TEXT
            // yes crossing name
            if(portList.get(i).getCrossingName().trim() != "N/A") {
                text = portList.get(i).getPortName().trim() + " "
                        + "(" + portList.get(i).getCrossingName().trim() + ")";
            }
            // no crossing name
            else {
                text = portList.get(i).getPortName().trim();
            }
            // hours
            hours = portList.get(i).getHours();
            // date
            date = portList.get(i).getDate();
            // port status
            portStatus = portList.get(i).getPortStatus();
            // passenger max
            passengerMax = portList.get(i).getPassengerMax();
            // pedestrian max
            pedestrianMax = portList.get(i).getPedestrianMax();
            // opStatus
            opStatus = portList.get(i).getOpStatus();
            // updateTime
            updateTime = portList.get(i).getUpdateTime();
            // get lanes open
            lanesOpen = portList.get(i).getLanesOpen();
            // delay Mintues
            delayMinutes = portList.get(i).getDelayMinutes();
            // buttons
            button.setText(text);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CanadaActivity.this, PortActivity.class);
                    intent.putExtra("name", button.getText().toString());
                    intent.putExtra("hours", hours);
                    intent.putExtra("date", date);
                    intent.putExtra("port_status", portStatus);
                    intent.putExtra("passenger_max", passengerMax);
                    intent.putExtra("pedestrian_max", pedestrianMax);
                    intent.putExtra("op_status", opStatus);
                    intent.putExtra("update_time", updateTime);
                    intent.putExtra("lanes_open", lanesOpen);
                    intent.putExtra("delay_minutes", delayMinutes);
                    startActivity(intent);
                }
            });
            layout.addView(button);
        }
    }
}
