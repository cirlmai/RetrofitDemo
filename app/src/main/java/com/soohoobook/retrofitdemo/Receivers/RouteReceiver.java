package com.soohoobook.retrofitdemo.Receivers;

/**
 * Created by Danny on 2015/10/6.
 */
public class RouteReceiver {

    private String TravelSight_Latitude;
    private String TravelSight_Longitude;
    private String Group;
    private String TravelSightId;
    private String SectionId;
    private String CategoryId;
    private String Title;
    private String Content;
    private String Focus;
    private String Pay;
    private String Money;
    private String Cover;
    private String CityId;
    private Double Distance;
    private String CreateTime;
    private String ExpertId;
    private String ExprertCode;

    public String getExprertCode() {
        return ExprertCode;
    }

    public String getExpertId() {
        return ExpertId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double distance) {
        Distance = distance;
    }

    public String getTravelSight_Latitude() {
        return TravelSight_Latitude;
    }

    public String getTravelSight_Longitude() {
        return TravelSight_Longitude;
    }



    public String getGroup() {
        return Group;
    }

    public String getTravelSightId() {
        return TravelSightId;
    }


    public String getCategoryId() {
        return CategoryId;
    }

    public String getContent() {
        return Content;
    }

    public String getCover() {
        return Cover;
    }

    public String getFocus() {
        return Focus;
    }

    public String getMoney() {
        return Money;
    }

    public String getCityId() {
        return CityId;
    }

    public String getPay() {
        return Pay;
    }

    public String getSectionId() {
        return SectionId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTravelSightId(String travelSightId) {
        TravelSightId = travelSightId;
    }
}
