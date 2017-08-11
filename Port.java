package com.garitasexpress.app;

/**
 * Created by JuanM on 7/28/2017.
 */

// GARITAS EXPRESS - by Juan Padilla 8/2/2017
public class Port {

    private String portName;

    private String crossingName;

    private String hours;

    private String date;

    private String portStatus;

    private String passengerMax;

    private String pedestrianMax;

    private String[] opStatus;

    private String[] updateTime;

    private String[] lanesOpen;

    private String[] delayMinutes;

    public Port() {
        opStatus = new String[5];
        updateTime = new String[5];
        lanesOpen = new String[5];
        delayMinutes = new String[5];
    }

    // SETTERS

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public void setCrossingName(String crossingName) {
        this.crossingName = crossingName;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPortStatus(String portStatus) {
        this.portStatus = portStatus;
    }

    public void setPassengerMax(String passengerMax) {this.passengerMax
            = passengerMax;}

    public void setPedestrianMax(String pedestrianMax) {this.pedestrianMax
            = pedestrianMax;}

    public void setOpStatus(int index, String opStatus) {this.opStatus[index]
            = opStatus;}

    public void setUpdateTime(int index, String updateTime) {this.updateTime[index]
            = updateTime;}

    public void setLanesOpen(int index, String lanesOpen) {this.lanesOpen[index]
            = lanesOpen;}

    public void setDelayMinutes(int index, String delayMinutes) {this.delayMinutes[index]
            = delayMinutes;}

    // GETTERS

    public String getPortName() {
        if(portName == null)
            return "N/A";
        return portName;
    }

    public String getCrossingName() {
        if(crossingName == null)
            return "N/A";
        return crossingName;
    }

    public String getHours() {
        if(hours == null)
            return "N/A";
        return hours;
    }

    public String getDate() {
        if(date == null)
            return "N/A";
        return date;
    }

    public String getPortStatus() {
        if(portStatus == null)
            return "N/A";
        return portStatus;
    }

    public String getPassengerMax() {
        if(passengerMax == null)
            return "N/A";
        return passengerMax;
    }

    public String getPedestrianMax() {
        if(passengerMax == null)
            return "N/A";
        return pedestrianMax;
    }

    public String[] getOpStatus() {
        return opStatus;
    }

    public String[] getUpdateTime() {
        return updateTime;
    }

    public String[] getLanesOpen() {
        return lanesOpen;
    }

    public String[] getDelayMinutes() {
        return delayMinutes;
    }

}
