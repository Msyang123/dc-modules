/**
 * Copyright © 2016 SGSL
 * 湖南绿航恰果果农产品有限公司
 * http://www.sgsl.com
 * All rights reserved.
 */
package com.lhiot.dc.data.common.util;

/**
 * @author User
 * @version 1.0 2016年11月12日下午2:39:59
 */
public class DistUtil {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据经纬度计算距离
     *
     * @param lat1 坐标1纬度
     * @param lng1 坐标1经度
     * @param lat2 坐标2纬度
     * @param lng2 坐标2经度
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        return distance;
    }

//	public static void main(String[] args) {
//		System.out.println(getDistance(28.236447, 112.914096, 28.123026, 113.021028));
//	}


//	public static void main(String[] args) {
//		List list = new ArrayList<>();
//		list.add(51);
//		list.add(52);
//		list.add(18);
//		list.add(36);
//		Collections.sort(list);
//		for(int i =0;i<list.size();i++){
//			System.out.println(list.get(i));
//		}
//	}
}
