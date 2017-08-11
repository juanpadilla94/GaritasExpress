package com.garitasexpress.app;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by JuanM on 7/28/2017.
 */

// GARITAS EXPRESS - by Juan Padilla 8/2/2017
public class Model {


    private ArrayList<Port> portsList;

    private final String countryMarker;

    public Model(String countryMarker) {
        // canada = true, mexico = false
        this.countryMarker = countryMarker;
        // final list of ports
        portsList = new ArrayList<Port>();
    }

    public void generateData() {
        try {
            portsList = new Data().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Data AsyncTask returns list of ports
    private class Data extends AsyncTask<Void, Void, ArrayList<Port>> {
        String url = "https://apps.cbp.gov/bwt/bwt.xml";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<Port> doInBackground(Void... params) {
            ArrayList<Port> portList = new ArrayList<Port>();
            try {
                // xml code
                String xml = Jsoup.connect(url).get().html();
                Scanner scanner = new Scanner(xml);
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if(line.contains("<port>")) {
                        while(scanner.hasNextLine() && !line.contains("</port>")) {
                            line = scanner.nextLine().trim();
                            // check if matches country
                            if(line.contains(countryMarker)) {
                                Port borderPort = new Port();
                                while(scanner.hasNextLine() && !line.contains("</port>")) {
                                    line = scanner.nextLine().trim();
                                    if (line.contains("<port_name>")) {
                                        line = scanner.nextLine().trim();
                                        borderPort.setPortName(line);
                                    }
                                    if (line.contains("<crossing_name>")) {
                                        if (!line.contains("<crossing_name></crossing_name>")) {
                                            line = scanner.nextLine().trim();
                                            borderPort.setCrossingName(line);
                                        }
                                    }
                                    if (line.contains("<hours>")) {
                                        line = scanner.nextLine().trim();
                                        borderPort.setHours(line);
                                    }
                                    if (line.contains("<date>")) {
                                        line = scanner.nextLine().trim();
                                        borderPort.setDate(line);
                                    }
                                    if (line.contains("<port_status>")) {
                                        line = scanner.nextLine().trim();
                                        borderPort.setPortStatus(line);
                                    }
                                    // PASSENGER LANES
                                    if (line.contains("<passenger_vehicle_lanes>")) {
                                        while (scanner.hasNextLine() && !line.contains("</passenger_vehicle_lanes>")) {
                                            line = scanner.nextLine().trim();
                                            if (line.contains("<maximum_lanes>")) {
                                                line = scanner.nextLine().trim();
                                                borderPort.setPassengerMax(line);
                                            }
                                            if (line.contains("<standard_lanes>")) {
                                                while (scanner.hasNextLine() && !line.contains("</standard_lanes>")) {
                                                    line = scanner.nextLine().trim();
                                                    if (line.contains("<operational_status>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setOpStatus(0, line);
                                                    }
                                                    if (line.contains("<update_time>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setUpdateTime(0, line);
                                                    }
                                                    if (line.contains("<lanes_open>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setLanesOpen(0, line);

                                                    }
                                                    if (line.contains("<delay_minutes>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setDelayMinutes(0, line);
                                                    }
                                                }
                                            }
                                            if (line.contains("<NEXUS_SENTRI_lanes>")) {
                                                while (scanner.hasNextLine() && !line.contains("</NEXUS_SENTRI_lanes>")) {
                                                    line = scanner.nextLine().trim();
                                                    if (line.contains("<operational_status>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setOpStatus(1, line);
                                                    }
                                                    if (line.contains("<update_time>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setUpdateTime(1, line);
                                                    }
                                                    if (line.contains("<lanes_open>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setLanesOpen(1, line);

                                                    }
                                                    if (line.contains("<delay_minutes>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setDelayMinutes(1, line);
                                                    }
                                                }
                                            }
                                            if (line.contains("<ready_lanes>")) {
                                                while (scanner.hasNextLine() && !line.contains("</ready_lanes>")) {
                                                    line = scanner.nextLine().trim();
                                                    if (line.contains("<operational_status>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setOpStatus(2, line);
                                                    }
                                                    if (line.contains("<update_time>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setUpdateTime(2, line);
                                                    }
                                                    if (line.contains("<lanes_open>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setLanesOpen(2, line);

                                                    }
                                                    if (line.contains("<delay_minutes>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setDelayMinutes(2, line);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // PEDESTRIAN LANES
                                    if (line.contains("<pedestrian_lanes>")) {
                                        while (scanner.hasNextLine() && !line.contains("</pedestrian_lanes>")) {
                                            line = scanner.nextLine().trim();
                                            if (line.contains("<maximum_lanes>")) {
                                                line = scanner.nextLine().trim();
                                                borderPort.setPedestrianMax(line);
                                            }
                                            if (line.contains("<standard_lanes>")) {
                                                while (scanner.hasNextLine() && !line.contains("</standard_lanes>")) {
                                                    line = scanner.nextLine().trim();
                                                    if (line.contains("<operational_status>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setOpStatus(3, line);
                                                    }
                                                    if (line.contains("<update_time>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setUpdateTime(3, line);
                                                    }
                                                    if (line.contains("<lanes_open>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setLanesOpen(3, line);

                                                    }
                                                    if (line.contains("<delay_minutes>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setDelayMinutes(3, line);
                                                    }
                                                }
                                            }
                                            if (line.contains("<ready_lanes>")) {
                                                while (scanner.hasNextLine() && !line.contains("</ready_lanes>")) {
                                                    line = scanner.nextLine().trim();
                                                    if (line.contains("<operational_status>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setOpStatus(4, line);
                                                    }
                                                    if (line.contains("<update_time>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setUpdateTime(4, line);
                                                    }
                                                    if (line.contains("<lanes_open>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setLanesOpen(4, line);

                                                    }
                                                    if (line.contains("<delay_minutes>")) {
                                                        line = scanner.nextLine().trim();
                                                        borderPort.setDelayMinutes(4, line);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                portList.add(borderPort);
                            }
                        }
                    }
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return portList;
        }
    }

    // GETTER METHOD FOR PORTS
    public ArrayList<Port> getPortsList() {
        return portsList;
    }

}
