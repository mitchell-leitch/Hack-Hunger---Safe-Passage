package entity;

import org.json.simple.JSONObject;

public class Crime {

    private double xCoordinate;
    private double yCoordinate;
    private String crimeTime;

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getCrimeTime() {
        return crimeTime;
    }

    public void setCrimeTime(String crimeTime) {
        this.crimeTime = crimeTime;
    }

    public JSONObject asJsonObject(){
        JSONObject jObject = new JSONObject();
        jObject.put("xCoordinate", getxCoordinate());
        jObject.put("yCoordinate", getyCoordinate());
        jObject.put("crimetime", getCrimeTime());
        return jObject;
    }
}
