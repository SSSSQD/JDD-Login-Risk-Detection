package com.jd.login.v12.function;

import com.jd.login.v12.TradeAndLogin;
import com.jd.login.v6.Data;

import java.util.ArrayList;

import static com.jd.login.v12.function.CityIPFeature.getDeviceConnectCityTimeNum;
import static com.jd.login.v12.function.CityIPFeature.getDeviceConnectIPTimeNum;
import static com.jd.login.v12.function.DeviceFeature.getDeviceTimeResultLoginNum;

public class HistoryLoginFeature {
    public static int getHistoryTradeTimeResultLoginAvgNum(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> allLoginArrayList,
                                                           boolean is_result, int result_pos_neg, int result, int downTime, int upperTime) {
        if (tradeAndLoginArrayList.size() != 0) {
            int c = 0;
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                c += getDeviceTimeResultLoginNum(map.getValue(), allLoginArrayList, is_result, result_pos_neg, result, downTime, upperTime);
            }
            return c / tradeAndLoginArrayList.size();
        } else {
            return -1;
        }
    }

    public static int getHistoryDeviceConnectCityTimeNum(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> allLoginArrayList, int minutes) {
        if (tradeAndLoginArrayList.size() == 0) {
            return -1;
        } else {
            int c = 0;
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                c += getDeviceConnectCityTimeNum(map.getValue(), allLoginArrayList, minutes);
            }
            return c / tradeAndLoginArrayList.size();
        }
    }

    public static int getHistoryDeviceConnectIPTimeNum(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> allLoginArrayList, int minutes) {
        if (tradeAndLoginArrayList.size() == 0) {
            return -1;
        } else {
            int c = 0;
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                c += getDeviceConnectIPTimeNum(map.getValue(), allLoginArrayList, minutes);
            }
            return c / tradeAndLoginArrayList.size();
        }
    }
}
