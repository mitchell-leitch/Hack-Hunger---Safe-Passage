package entity;

import org.json.simple.JSONObject;

public class MetricedDepository {
    private Depository depository;
    private double engagementScore;
    private double nearestRouteDistance;

    public Depository getDepository() {
        return depository;
    }

    public void setDepository(Depository depository) {
        this.depository = depository;
    }

    public double getEngagementScore() {
        return engagementScore;
    }

    public void setEngagementScore(double engagementScore) {
        this.engagementScore = engagementScore;
    }

    public double getNearestRouteDistance() {
        return nearestRouteDistance;
    }

    public void setNearestRouteDistance(double nearestRouteDistance) {
        this.nearestRouteDistance = nearestRouteDistance;
    }

    public JSONObject asJsonObject(){
        JSONObject jObject = new JSONObject();
        jObject.put("name", depository.getName());
        jObject.put("city", depository.getCity());
        jObject.put("address", depository.getAddress());
        jObject.put("xCoordinate", depository.getxCoordinate());
        jObject.put("yCoordinate", depository.getyCoordinate());
        jObject.put("state", depository.getState());
        jObject.put("zip", depository.getZip());
        jObject.put("hasSupper", depository.isSupper());
        jObject.put("hasPmSnack", depository.isPmSnack());
        jObject.put("hasLunch", depository.isLunch());
        jObject.put("isSchool", depository.isSchool());
        jObject.put("hasBreakfast", depository.isBreakfast());
        jObject.put("engagementScore", getEngagementScore());
        jObject.put("nearestRouteDistance", getNearestRouteDistance());
        return jObject;
    }

}
