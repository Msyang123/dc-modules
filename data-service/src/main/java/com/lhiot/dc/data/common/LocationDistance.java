package com.lhiot.dc.data.common;


import com.lhiot.dc.data.domain.LocationParam;

/**
 * Created by User .
 * Create Time:  2017/3/26
 * Modified by  User .
 * Modified Time: 2017/3/26
 * description:
 */

public interface LocationDistance {
    double EARTH_RADIUS = 6378.137;

    default double rad(double d) {
        return d * Math.PI / 180.0;
    }

    default double betweenDistance(double locationX, double locationY) {
        LocationParam locationParam = new LocationParam();
        locationParam.setLocationX(locationX);
        locationParam.setLocationY(locationY);
        return betweenDistance(locationParam);
    }


    default double betweenDistance(LocationDistance locationDistance) {
        double radLat1 = rad(getLocationX());
        double radLat2 = rad(locationDistance.getLocationX());
        double difference = radLat1 - radLat2;
        double mdifference = rad(getLocationY()) - rad(locationDistance.getLocationY());
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        return distance;
    }

    Double getLocationX();

    Double getLocationY();
}
