package com.jd.login.v12.function;

import com.jd.login.v12.TradeAndLogin;
import com.jd.login.v6.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.jd.login.Utils.timeDiff;

/*
 xxx / yyy
 */
public class BaseUtil {
    public static int getPercent(int yyy, int xxx) {
        if (yyy == 0) {
            return -99;
        } else {
            return xxx * 100 / yyy;
        }
    }

    public static ArrayList<TradeAndLogin> getRangeTrade(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> thisIntervalTrade, int downBoundDays, int upperBoundDays) {
        String thisTradeTime = thisIntervalTrade.get(0).getTime();
        String downBoundTime = timeDiff(thisTradeTime, upperBoundDays*24*60);
        String upperBpundTime = timeDiff(thisTradeTime, downBoundDays*24*60);
        LinkedHashMap<ArrayList<Data>, Data> hashMap2 = new LinkedHashMap<>();
        ArrayList<TradeAndLogin> tradeAndLoginArrayList1 = new ArrayList<>();
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            if (downBoundTime.compareTo(map.getThisIntervalLogin().get(0).getTime()) > 0 && map.getThisIntervalLogin().get(0).getTime().compareTo(upperBpundTime) >= 0) {
//                hashMap2.put(map.getKey(), map.getValue());
                tradeAndLoginArrayList1.add(new TradeAndLogin(map.getThisIntervalLogin(), map.getThisLogin()));
            }
        }
        return tradeAndLoginArrayList1;
    }

    public static boolean getHasLastXTrade(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> thisIntervalTrade, int x) {
        int c = 0;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            if (map.getKey().get(0).getTime().equals(thisIntervalTrade.get(0).getTime())) {
                break;
            } else {
                c++;
            }
        }
        int var1 = c - x;
        if (var1 < 0) {
            return false;
        } else {
            return true;
        }
    }

    public static ArrayList<Data> getLastXTrade(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> thisIntervalTrade, int x) {
        int c = 0;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            if (map.getKey().get(0).getTime().equals(thisIntervalTrade.get(0).getTime())) {
                break;
            } else {
                c++;
            }
        }
        int var1 = c - x;
        int var2 = 0;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            if (var2 == var1) {
                return map.getKey();
            } else {
                var2++;
            }
        }
        return new ArrayList<>();
    }

    public static LinkedHashMap<ArrayList<Data>, Data> getHistoryIntervalTrade(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> thisIntervalTrade) {
        LinkedHashMap<ArrayList<Data>, Data> listDataLinkedHashMap = new LinkedHashMap<>();
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            if (thisIntervalTrade.get(0).getTime().compareTo(map.getKey().get(0).getTime()) > 0) {
                listDataLinkedHashMap.put(map.getKey(), map.getValue());
            }
        }
        return listDataLinkedHashMap;
    }

    public static Data getLastXTradeLogin(ArrayList<TradeAndLogin> tradeAndLoginArrayList, ArrayList<Data> thisIntervalTrade, int x) {
        int c = 0;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            if (map.getKey().get(0).getTime().equals(thisIntervalTrade.get(0).getTime())) {
                break;
            } else {
                c++;
            }
        }
        int var1 = c - x;
        int var2 = 0;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            if (var2 == var1) {
                return map.getValue();
            } else {
                var2++;
            }
        }
        return new Data("2014-12-31 00:00:00","","",-1,-1,-1,-1,-1,-1,-1,-1,-1);
    }
}
