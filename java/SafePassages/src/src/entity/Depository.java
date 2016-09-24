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
        return jObject;
    }
}

