package entity;


import org.json.simple.JSONObject;

public class Depository {

    private String name;
    private String address;
    private String geometry;
    private boolean school;
    private boolean breakfast;
    private boolean lunch;
    private boolean supper;
    private boolean pmSnack;
    private String city;
    private String zip;
    private String state;
    private double xCoordinate;
    private double yCoordinate;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public boolean isSchool() {
        return school;
    }

    public void setSchool(boolean school) {
        this.school = school;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean isSupper() {
        return supper;
    }

    public void setSupper(boolean supper) {
        this.supper = supper;
    }

    public boolean isPmSnack() {
        return pmSnack;
    }

    public void setPmSnack(boolean pmSnack) {
        this.pmSnack = pmSnack;
    }

    public JSONObject asJsonObject(){
        JSONObject jObject = new JSONObject();
        jObject.put("name", getName());
        jObject.put("city", getCity());
        jObject.put("address", getAddress());
        jObject.put("xCoordinate", getxCoordinate());
        jObject.put("yCoordinate", getyCoordinate());
        jObject.put("state", getState());
        jObject.put("zip", getZip());
        jObject.put("hasSupper", isSupper());
        jObject.put("hasPmSnack", isPmSnack());
        jObject.put("hasLunch", isLunch());
        jObject.put("isSchool", isSchool());
        jObject.put("hasBreakfast", isBreakfast());
        return jObject;
    }
}

