package com.jd.login.v14;

import com.jd.login.v12.TradeAndLogin;
import com.jd.login.v6.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.jd.login.v12.function.BaseUtil.*;
import static com.jd.login.v12.function.CityIPFeature.*;
import static com.jd.login.v12.function.DeviceFeature.*;
import static com.jd.login.v12.function.DeviceUserFeature.*;
import static com.jd.login.v12.function.GlobalUtil.*;
import static com.jd.login.v12.function.HistoryLoginFeature.*;
import static com.jd.login.v12.function.HistoryTradeFeature.getHistoryTradeLoginTimeDiffAvg;
import static com.jd.login.v12.function.LoginFeature.*;
import static com.jd.login.v12.function.ResultFeature.getResultTimeNum;
import static com.jd.login.v12.function.TradeFeature.*;
import static com.jd.login.v14.write.HeaderC.getHeader;

public class Function14C {
    public static void main(String[] args) {
        try {
            File file = new File("/Users/admin/Documents/K/login/1205/1205_data_merge.hashmap");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<Integer, ArrayList<Data>> hashMap = (HashMap<Integer, ArrayList<Data>>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            File file2 = new File("/Users/admin/Documents/K/login/1206/1206_ml_instance_c.hashmap");
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            HashMap<Integer, ArrayList<TradeAndLogin>> hashMapHashMap = (HashMap<Integer, ArrayList<TradeAndLogin>>) objectInputStream2.readObject();
            objectInputStream2.close();
            fileInputStream2.close();

            File file3 = new File("/Users/admin/Documents/K/login/1206/1206_device_c.hashmap1");
            FileInputStream fileInputStream3 = new FileInputStream(file3);
            ObjectInputStream objectInputStream3 = new ObjectInputStream(fileInputStream3);
            HashMap<Integer, HashMap<Integer, ArrayList<Data>>> deviceMap1 = (HashMap<Integer, HashMap<Integer, ArrayList<Data>>>) objectInputStream3.readObject();
            objectInputStream3.close();
            fileInputStream3.close();

            File file4 = new File("/Users/admin/Documents/K/login/1205/1205_device.hashmap2");
            FileInputStream fileInputStream4 = new FileInputStream(file4);
            ObjectInputStream objectInputStream4 = new ObjectInputStream(fileInputStream4);
            HashMap<Integer, HashMap<Integer, ArrayList<Data>>> deviceMap2 = (HashMap<Integer, HashMap<Integer, ArrayList<Data>>>) objectInputStream4.readObject();
            objectInputStream4.close();
            fileInputStream4.close();

            int i = 0;

            FileWriter fileWriter = new FileWriter("/Users/admin/Documents/K/login/1206/1206_feature_c.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(getHeader() + "\r\n");

            for (Map.Entry<Integer, ArrayList<TradeAndLogin>> map : hashMapHashMap.entrySet()) {
                i++;
                
                if (i % 300 == 0) {
                    System.out.println(i);
                }
                int user_id = map.getKey();
                ArrayList<TradeAndLogin> tradeAndLoginArrayList = map.getValue();
                ArrayList<Data> allActionArrayList = hashMap.get(user_id);
                ArrayList<Data> allLoginArrayList = new ArrayList<>();
                for (Data data : allActionArrayList) {
                    if (data.getData_type().equals("login")) {
                        allLoginArrayList.add(data);
                    }
                }
                ArrayList<Data> allTradeArrayList = new ArrayList<>();
                for (Data data : allActionArrayList) {
                    if (!data.getData_type().equals("login")) {
                        allTradeArrayList.add(data);
                    }
                }
                for (TradeAndLogin map2 : tradeAndLoginArrayList) {
                    StringBuffer stringBuffer = new StringBuffer();
                    ArrayList<Data> thisIntervalTrade = map2.getThisIntervalLogin();
                    Data thisLogin = map2.getThisLogin();
                    
                    // 本次登录与本段交易时间间隔
                    stringBuffer.append(getTradeLoginTimeDiff(thisIntervalTrade, thisLogin)).append(",");                    
                    // 本段交易时间0-3点
                    stringBuffer.append(getTradeTimeRangeNum(thisIntervalTrade, 0, 3)).append(",");                    
                    // 本段交易时间4-10点
                    stringBuffer.append(getTradeTimeRangeNum(thisIntervalTrade, 4, 10)).append(",");                    
                    // 本段交易时间11-12点
                    stringBuffer.append(getTradeTimeRangeNum(thisIntervalTrade, 11, 12)).append(",");                    
                    // 本段交易时间13-17点
                    stringBuffer.append(getTradeTimeRangeNum(thisIntervalTrade, 13, 17)).append(",");                    
                    // 本段交易时间18-20点
                    stringBuffer.append(getTradeTimeRangeNum(thisIntervalTrade, 18, 20)).append(",");                    
                    // 本段交易时间21-23点
                    stringBuffer.append(getTradeTimeRangeNum(thisIntervalTrade, 21, 23)).append(",");                    
                    // 本段交易为周一
                    stringBuffer.append(getTradeDayOfWeek(thisIntervalTrade, 0)).append(",");                    
                    // 本段交易为周二
                    stringBuffer.append(getTradeDayOfWeek(thisIntervalTrade, 1)).append(",");                    
                    // 本段交易为周三
                    stringBuffer.append(getTradeDayOfWeek(thisIntervalTrade, 2)).append(",");                    
                    // 本段交易为周四
                    stringBuffer.append(getTradeDayOfWeek(thisIntervalTrade, 3)).append(",");                    
                    // 本段交易为周五
                    stringBuffer.append(getTradeDayOfWeek(thisIntervalTrade, 4)).append(",");                    
                    // 本段交易为周六
                    stringBuffer.append(getTradeDayOfWeek(thisIntervalTrade, 5)).append(",");                    
                    // 本段交易为周日
                    stringBuffer.append(getTradeDayOfWeek(thisIntervalTrade, 6)).append(",");                    
                    // 本段交易是否为节假日
                    stringBuffer.append(getTradeIsFestival(thisIntervalTrade)).append(",");                    
                    // 本段交易是否为节假日前一天
                    stringBuffer.append(getTradeIsFestivalBefore1Day(thisIntervalTrade)).append(",");                    
                    // 3天内交易段数
                    ArrayList<TradeAndLogin> range0_3Trade = getRangeTrade(tradeAndLoginArrayList, thisIntervalTrade, 0, 3);
                    stringBuffer.append(getRangeIntervalTradeNum(range0_3Trade)).append(",");                    
                    // 3天登录与交易的平均时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffAvg(range0_3Trade)).append(",");                    
                    // 3天内交易平均间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffAvg(range0_3Trade)).append(",");
                    // 3天登录与交易的最大时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMax(range0_3Trade)).append(",");
                    // 3天内交易最大间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMax(range0_3Trade)).append(",");
                    // 3天登录与交易的最小时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMin(range0_3Trade)).append(",");
                    // 3天内交易最小间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMin(range0_3Trade)).append(",");
                    // 3天内交易时间0-3点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range0_3Trade, 0, 3)).append(",");
                    // 3天内交易时间4-10点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range0_3Trade, 4, 10)).append(",");
                    // 3天内交易时间11-12点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range0_3Trade, 11, 12)).append(",");
                    // 3天内交易时间13-17点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range0_3Trade, 13, 17)).append(",");
                    // 3天内交易时间18-20点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range0_3Trade, 18, 20)).append(",");
                    // 3天内交易时间21-23点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range0_3Trade, 21, 23)).append(",");
                    // 3天内交易时间0-3点占比
                    stringBuffer.append(getPercent(range0_3Trade.size(),getRangeIntervalTradeTimeRangeNum(range0_3Trade, 0, 3))).append(",");
                    // 3天内交易时间4-10点占比
                    stringBuffer.append(getPercent(range0_3Trade.size(),getRangeIntervalTradeTimeRangeNum(range0_3Trade, 4, 10))).append(",");
                    // 3天内交易时间11-12点占比
                    stringBuffer.append(getPercent(range0_3Trade.size(),getRangeIntervalTradeTimeRangeNum(range0_3Trade, 11, 12))).append(",");
                    // 3天内交易时间13-17点占比
                    stringBuffer.append(getPercent(range0_3Trade.size(),getRangeIntervalTradeTimeRangeNum(range0_3Trade, 13, 17))).append(",");
                    // 3天内交易时间18-20点占比
                    stringBuffer.append(getPercent(range0_3Trade.size(),getRangeIntervalTradeTimeRangeNum(range0_3Trade, 18, 20))).append(",");
                    // 3天内交易时间21-23点占比
                    stringBuffer.append(getPercent(range0_3Trade.size(),getRangeIntervalTradeTimeRangeNum(range0_3Trade, 21, 23))).append(",");
                    // 3天内交易为周一
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range0_3Trade, 0)).append(",");
                    // 3天内交易为周二
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range0_3Trade, 1)).append(",");
                    // 3天内交易为周三
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range0_3Trade, 2)).append(",");
                    // 3天内交易为周四
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range0_3Trade, 3)).append(",");
                    // 3天内交易为周五
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range0_3Trade, 4)).append(",");
                    // 3天内交易为周六
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range0_3Trade, 5)).append(",");
                    // 3天内交易为周日
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range0_3Trade, 6)).append(",");
                    // 3天内交易是否为节假日
                    stringBuffer.append(getRangeIntervalTradeIsFestival(range0_3Trade)).append(",");
                    // 3天内交易是否为节假日前一天
                    stringBuffer.append(getRangeIntervalTradeIsFestivalBefore1Day(range0_3Trade)).append(",");
                    // 3_14天内交易段数
                    ArrayList<TradeAndLogin> range3_14Trade = getRangeTrade(tradeAndLoginArrayList, thisIntervalTrade, 3, 14);
                    stringBuffer.append(getRangeIntervalTradeNum(range3_14Trade)).append(",");
                    // 3_14天登录与交易的平均时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffAvg(range3_14Trade)).append(",");
                    // 3_14天内交易平均间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffAvg(range3_14Trade)).append(",");
                    // 3_14天登录与交易的最大时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMax(range3_14Trade)).append(",");
                    // 3_14天内交易最大间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMax(range3_14Trade)).append(",");
                    // 3_14天登录与交易的最小时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMin(range3_14Trade)).append(",");
                    // 3_14天内交易最小间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMin(range3_14Trade)).append(",");
                    // 3_14天内交易时间0-3点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_14Trade, 0, 3)).append(",");
                    // 3_14天内交易时间4-10点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_14Trade, 4, 10)).append(",");
                    // 3_14天内交易时间11-12点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_14Trade, 11, 12)).append(",");
                    // 3_14天内交易时间13-17点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_14Trade, 13, 17)).append(",");
                    // 3_14天内交易时间18-20点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_14Trade, 18, 20)).append(",");
                    // 3_14天内交易时间21-23点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_14Trade, 21, 23)).append(",");
                    // 3_14天内交易时间0-3点占比
                    stringBuffer.append(getPercent(range3_14Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_14Trade, 0, 3))).append(",");
                    // 3_14天内交易时间4-10点占比
                    stringBuffer.append(getPercent(range3_14Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_14Trade, 4, 10))).append(",");
                    // 3_14天内交易时间11-12点占比
                    stringBuffer.append(getPercent(range3_14Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_14Trade, 11, 12))).append(",");
                    // 3_14天内交易时间13-17点占比
                    stringBuffer.append(getPercent(range3_14Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_14Trade, 13, 17))).append(",");
                    // 3_14天内交易时间18-20点占比
                    stringBuffer.append(getPercent(range3_14Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_14Trade, 18, 20))).append(",");
                    // 3_14天内交易时间21-23点占比
                    stringBuffer.append(getPercent(range3_14Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_14Trade, 21, 23))).append(",");
                    // 3_14天内交易为周一
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_14Trade, 0)).append(",");
                    // 3_14天内交易为周二
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_14Trade, 1)).append(",");
                    // 3_14天内交易为周三
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_14Trade, 2)).append(",");
                    // 3_14天内交易为周四
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_14Trade, 3)).append(",");
                    // 3_14天内交易为周五
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_14Trade, 4)).append(",");
                    // 3_14天内交易为周六
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_14Trade, 5)).append(",");
                    // 3_14天内交易为周日
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_14Trade, 6)).append(",");
                    // 3_14天内交易是否为节假日
                    stringBuffer.append(getRangeIntervalTradeIsFestival(range3_14Trade)).append(",");
                    // 3_14天内交易是否为节假日前一天
                    stringBuffer.append(getRangeIntervalTradeIsFestivalBefore1Day(range3_14Trade)).append(",");
                    // 14_30天内交易段数
                    ArrayList<TradeAndLogin> range14_30Trade = getRangeTrade(tradeAndLoginArrayList, thisIntervalTrade, 14, 30);
                    stringBuffer.append(getRangeIntervalTradeNum(range14_30Trade)).append(",");
                    // 14_30天登录与交易的平均时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffAvg(range14_30Trade)).append(",");
                    // 14_30天内交易平均间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffAvg(range14_30Trade)).append(",");
                    // 14_30天登录与交易的最大时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMax(range14_30Trade)).append(",");
                    // 14_30天内交易最大间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMax(range14_30Trade)).append(",");
                    // 14_30天登录与交易的最小时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMin(range14_30Trade)).append(",");
                    // 14_30天内交易最小间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMin(range14_30Trade)).append(",");
                    // 14_30天内交易时间0-3点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range14_30Trade, 0, 3)).append(",");
                    // 14_30天内交易时间4-10点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range14_30Trade, 4, 10)).append(",");
                    // 14_30天内交易时间11-12点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range14_30Trade, 11, 12)).append(",");
                    // 14_30天内交易时间13-17点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range14_30Trade, 13, 17)).append(",");
                    // 14_30天内交易时间18-20点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range14_30Trade, 18, 20)).append(",");
                    // 14_30天内交易时间21-23点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range14_30Trade, 21, 23)).append(",");
                    // 14_30天内交易时间0-3点占比
                    stringBuffer.append(getPercent(range14_30Trade.size(),getRangeIntervalTradeTimeRangeNum(range14_30Trade, 0, 3))).append(",");
                    // 14_30天内交易时间4-10点占比
                    stringBuffer.append(getPercent(range14_30Trade.size(),getRangeIntervalTradeTimeRangeNum(range14_30Trade, 4, 10))).append(",");
                    // 14_30天内交易时间11-12点占比
                    stringBuffer.append(getPercent(range14_30Trade.size(),getRangeIntervalTradeTimeRangeNum(range14_30Trade, 11, 12))).append(",");
                    // 14_30天内交易时间13-17点占比
                    stringBuffer.append(getPercent(range14_30Trade.size(),getRangeIntervalTradeTimeRangeNum(range14_30Trade, 13, 17))).append(",");
                    // 14_30天内交易时间18-20点占比
                    stringBuffer.append(getPercent(range14_30Trade.size(),getRangeIntervalTradeTimeRangeNum(range14_30Trade, 18, 20))).append(",");
                    // 14_30天内交易时间21-23点占比
                    stringBuffer.append(getPercent(range14_30Trade.size(),getRangeIntervalTradeTimeRangeNum(range14_30Trade, 21, 23))).append(",");
                    // 14_30天内交易为周一
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range14_30Trade, 0)).append(",");
                    // 14_30天内交易为周二
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range14_30Trade, 1)).append(",");
                    // 14_30天内交易为周三
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range14_30Trade, 2)).append(",");
                    // 14_30天内交易为周四
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range14_30Trade, 3)).append(",");
                    // 14_30天内交易为周五
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range14_30Trade, 4)).append(",");
                    // 14_30天内交易为周六
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range14_30Trade, 5)).append(",");
                    // 14_30天内交易为周日
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range14_30Trade, 6)).append(",");
                    // 14_30天内交易是否为节假日
                    stringBuffer.append(getRangeIntervalTradeIsFestival(range14_30Trade)).append(",");
                    // 14_30天内交易是否为节假日前一天
                    stringBuffer.append(getRangeIntervalTradeIsFestivalBefore1Day(range14_30Trade)).append(",");
                    // 30_60天内交易段数
                    ArrayList<TradeAndLogin> range30_60Trade = getRangeTrade(tradeAndLoginArrayList, thisIntervalTrade, 30, 60);
                    stringBuffer.append(getRangeIntervalTradeNum(range30_60Trade)).append(",");
                    // 30_60天登录与交易的平均时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffAvg(range30_60Trade)).append(",");
                    // 30_60天内交易平均间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffAvg(range30_60Trade)).append(",");
                    // 30_60天登录与交易的最大时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMax(range30_60Trade)).append(",");
                    // 30_60天内交易最大间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMax(range30_60Trade)).append(",");
                    // 30_60天登录与交易的最小时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMin(range30_60Trade)).append(",");
                    // 30_60天内交易最小间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMin(range30_60Trade)).append(",");
                    // 30_60天内交易时间0-3点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range30_60Trade, 0, 3)).append(",");
                    // 30_60天内交易时间4-10点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range30_60Trade, 4, 10)).append(",");
                    // 30_60天内交易时间11-12点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range30_60Trade, 11, 12)).append(",");
                    // 30_60天内交易时间13-17点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range30_60Trade, 13, 17)).append(",");
                    // 30_60天内交易时间18-20点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range30_60Trade, 18, 20)).append(",");
                    // 30_60天内交易时间21-23点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range30_60Trade, 21, 23)).append(",");
                    // 30_60天内交易时间0-3点占比
                    stringBuffer.append(getPercent(range30_60Trade.size(),getRangeIntervalTradeTimeRangeNum(range30_60Trade, 0, 3))).append(",");
                    // 30_60天内交易时间4-10点占比
                    stringBuffer.append(getPercent(range30_60Trade.size(),getRangeIntervalTradeTimeRangeNum(range30_60Trade, 4, 10))).append(",");
                    // 30_60天内交易时间11-12点占比
                    stringBuffer.append(getPercent(range30_60Trade.size(),getRangeIntervalTradeTimeRangeNum(range30_60Trade, 11, 12))).append(",");
                    // 30_60天内交易时间13-17点占比
                    stringBuffer.append(getPercent(range30_60Trade.size(),getRangeIntervalTradeTimeRangeNum(range30_60Trade, 13, 17))).append(",");
                    // 30_60天内交易时间18-20点占比
                    stringBuffer.append(getPercent(range30_60Trade.size(),getRangeIntervalTradeTimeRangeNum(range30_60Trade, 18, 20))).append(",");
                    // 30_60天内交易时间21-23点占比
                    stringBuffer.append(getPercent(range30_60Trade.size(),getRangeIntervalTradeTimeRangeNum(range30_60Trade, 21, 23))).append(",");
                    // 30_60天内交易为周一
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range30_60Trade, 0)).append(",");
                    // 30_60天内交易为周二
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range30_60Trade, 1)).append(",");
                    // 30_60天内交易为周三
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range30_60Trade, 2)).append(",");
                    // 30_60天内交易为周四
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range30_60Trade, 3)).append(",");
                    // 30_60天内交易为周五
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range30_60Trade, 4)).append(",");
                    // 30_60天内交易为周六
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range30_60Trade, 5)).append(",");
                    // 30_60天内交易为周日
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range30_60Trade, 6)).append(",");
                    // 30_60天内交易是否为节假日
                    stringBuffer.append(getRangeIntervalTradeIsFestival(range30_60Trade)).append(",");
                    // 30_60天内交易是否为节假日前一天
                    stringBuffer.append(getRangeIntervalTradeIsFestivalBefore1Day(range30_60Trade)).append(",");
                    // 历史内交易段数
                    ArrayList<TradeAndLogin> range3_365Trade = getRangeTrade(tradeAndLoginArrayList, thisIntervalTrade, 3, 365);
                    stringBuffer.append(getRangeIntervalTradeNum(range3_365Trade)).append(",");
                    // 历史天登录与交易的平均时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffAvg(range3_365Trade)).append(",");
                    // 历史天内交易平均间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffAvg(range3_365Trade)).append(",");
                    // 历史天登录与交易的最大时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMax(range3_365Trade)).append(",");
                    // 历史天内交易最大间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMax(range3_365Trade)).append(",");
                    // 历史天登录与交易的最小时间间隔
                    stringBuffer.append(getRangeIntervalTradeLoginTimeDiffMin(range3_365Trade)).append(",");
                    // 历史天内交易最小间隔天数
                    stringBuffer.append(getRangeIntervalTradeDayDiffMin(range3_365Trade)).append(",");
                    // 历史天内交易时间0-3点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_365Trade, 0, 3)).append(",");
                    // 历史天内交易时间4-10点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_365Trade, 4, 10)).append(",");
                    // 历史天内交易时间11-12点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_365Trade, 11, 12)).append(",");
                    // 历史天内交易时间13-17点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_365Trade, 13, 17)).append(",");
                    // 历史天内交易时间18-20点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_365Trade, 18, 20)).append(",");
                    // 历史天内交易时间21-23点次数
                    stringBuffer.append(getRangeIntervalTradeTimeRangeNum(range3_365Trade, 21, 23)).append(",");
                    // 历史天内交易时间0-3点占比
                    stringBuffer.append(getPercent(range3_365Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_365Trade, 0, 3))).append(",");
                    // 历史天内交易时间4-10点占比
                    stringBuffer.append(getPercent(range3_365Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_365Trade, 4, 10))).append(",");
                    // 历史天内交易时间11-12点占比
                    stringBuffer.append(getPercent(range3_365Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_365Trade, 11, 12))).append(",");
                    // 历史天内交易时间13-17点占比
                    stringBuffer.append(getPercent(range3_365Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_365Trade, 13, 17))).append(",");
                    // 历史天内交易时间18-20点占比
                    stringBuffer.append(getPercent(range3_365Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_365Trade, 18, 20))).append(",");
                    // 历史天内交易时间21-23点占比
                    stringBuffer.append(getPercent(range3_365Trade.size(),getRangeIntervalTradeTimeRangeNum(range3_365Trade, 21, 23))).append(",");
                    // 历史天内交易为周一
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_365Trade, 0)).append(",");
                    // 历史天内交易为周二
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_365Trade, 1)).append(",");
                    // 历史天内交易为周三
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_365Trade, 2)).append(",");
                    // 历史天内交易为周四
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_365Trade, 3)).append(",");
                    // 历史天内交易为周五
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_365Trade, 4)).append(",");
                    // 历史天内交易为周六
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_365Trade, 5)).append(",");
                    // 历史天内交易为周日
                    stringBuffer.append(getRangeIntervalTradeDayOfWeek(range3_365Trade, 6)).append(",");
                    // 历史天内交易是否为节假日
                    stringBuffer.append(getRangeIntervalTradeIsFestival(range3_365Trade)).append(",");
                    // 历史天内交易是否为节假日前一天
                    stringBuffer.append(getRangeIntervalTradeIsFestivalBefore1Day(range3_365Trade)).append(",");
                    // 是否今年初次交易
                    stringBuffer.append(getTradeIsYearFirstTrade(thisIntervalTrade, allTradeArrayList)).append(",");
                    // 今年第几段交易
                    stringBuffer.append(getOrderOfThisYearTrade(thisIntervalTrade, tradeAndLoginArrayList)).append(",");
                    // 上段交易登录与交易的时间间隔
                    boolean hasLast1Trade = getHasLastXTrade(tradeAndLoginArrayList, thisIntervalTrade, 1);
                    ArrayList<Data> last1Trade = getLastXTrade(tradeAndLoginArrayList, thisIntervalTrade, 1);
                    Data last1TradeLogin = getLastXTradeLogin(tradeAndLoginArrayList, thisIntervalTrade, 1);
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeLoginTimeDiff(last1Trade, last1TradeLogin)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易时间0-3点
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last1Trade, 0, 3)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易时间4-10点
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last1Trade, 4, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易时间11-12点
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last1Trade, 11, 12)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易时间13-17点
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last1Trade, 13, 17)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易时间18-20点
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last1Trade, 18, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易时间21-23点
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last1Trade, 21, 23)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易为周一
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last1Trade, 0)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易为周二
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last1Trade, 1)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易为周三
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last1Trade, 2)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易为周四
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last1Trade, 3)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易为周五
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last1Trade, 4)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易为周六
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last1Trade, 5)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易为周日
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last1Trade, 6)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易是否为节假日
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeIsFestival(last1Trade)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易内交易是否为节假日前一天
                    if (hasLast1Trade) {
                        stringBuffer.append(getTradeIsFestivalBefore1Day(last1Trade)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录与交易的时间间隔
                    boolean hasLast2Trade = getHasLastXTrade(tradeAndLoginArrayList, thisIntervalTrade, 2);
                    ArrayList<Data> last2Trade = getLastXTrade(tradeAndLoginArrayList, thisIntervalTrade, 2);
                    Data last2TradeLogin = getLastXTradeLogin(tradeAndLoginArrayList, thisIntervalTrade, 2);
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeLoginTimeDiff(last2Trade, last2TradeLogin)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易时间0-3点
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last2Trade, 0, 3)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易时间4-10点
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last2Trade, 4, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易时间11-12点
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last2Trade, 11, 12)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易时间13-17点
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last2Trade, 13, 17)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易时间18-20点
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last2Trade, 18, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易时间21-23点
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeTimeRangeNum(last2Trade, 21, 23)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易为周一
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last2Trade, 0)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易为周二
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last2Trade, 1)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易为周三
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last2Trade, 2)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易为周四
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last2Trade, 3)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易为周五
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last2Trade, 4)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易为周六
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last2Trade, 5)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易为周日
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeDayOfWeek(last2Trade, 6)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易是否为节假日
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeIsFestival(last2Trade)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易内交易是否为节假日前一天
                    if (hasLast2Trade) {
                        stringBuffer.append(getTradeIsFestivalBefore1Day(last2Trade)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 历史交易登录与交易的平均时间间隔
                    LinkedHashMap<ArrayList<Data>, Data> historyIntervalTrade = getHistoryIntervalTrade(tradeAndLoginArrayList, thisIntervalTrade);
                    stringBuffer.append(getHistoryTradeLoginTimeDiffAvg(historyIntervalTrade)).append(",");
                    // 本次登录时长
                    stringBuffer.append(getThisLoginTimelong(thisLogin)).append(",");
                    // 本次登录类型是否为1
                    stringBuffer.append(getThisLoginType(thisLogin,1)).append(",");
                    // 本次登录类型是否为2
                    stringBuffer.append(getThisLoginType(thisLogin,2)).append(",");
                    // 本次登录类型是否为3
                    stringBuffer.append(getThisLoginType(thisLogin,3)).append(",");
                    // 本次登录来源为10
                    stringBuffer.append(getThisLoginFrom(thisLogin, 10)).append(",");
                    // 本次登录来源为1
                    stringBuffer.append(getThisLoginFrom(thisLogin, 1)).append(",");
                    // 本次登录来源为2
                    stringBuffer.append(getThisLoginFrom(thisLogin, 2)).append(",");
                    // 本次登录来源为11
                    stringBuffer.append(getThisLoginFrom(thisLogin, 11)).append(",");
                    // 本次登录来源为21
                    stringBuffer.append(getThisLoginFrom(thisLogin, 21)).append(",");
                    // 本次登录来源为5
                    stringBuffer.append(getThisLoginFrom(thisLogin, 5)).append(",");
                    // 本次登录来源为8
                    stringBuffer.append(getThisLoginFrom(thisLogin, 8)).append(",");
                    // 本次登录来源为16
                    stringBuffer.append(getThisLoginFrom(thisLogin, 16)).append(",");
                    // 本次登录来源为18
                    stringBuffer.append(getThisLoginFrom(thisLogin, 18)).append(",");
                    // 本次登录来源为3
                    stringBuffer.append(getThisLoginFrom(thisLogin, 3)).append(",");
                    // 本次登录来源为12
                    stringBuffer.append(getThisLoginFrom(thisLogin, 12)).append(",");
                    // 20min内登录结果为1登录来源为10次数
                    ArrayList<Data> recent20minLogin = getRecentXLogin(allLoginArrayList, thisLogin,20);
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 10)).append(",");
                    // 20min内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 1)).append(",");
                    // 20min内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 2)).append(",");
                    // 20min内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 11)).append(",");
                    // 20min内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 21)).append(",");
                    // 20min内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 5)).append(",");
                    // 20min内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 8)).append(",");
                    // 20min内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 16)).append(",");
                    // 20min内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 18)).append(",");
                    // 20min内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 3)).append(",");
                    // 20min内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 1, 12)).append(",");
                    // 20min内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 10)).append(",");
                    // 20min内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 1)).append(",");
                    // 20min内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 2)).append(",");
                    // 20min内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 11)).append(",");
                    // 20min内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 21)).append(",");
                    // 20min内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 5)).append(",");
                    // 20min内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 8)).append(",");
                    // 20min内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 16)).append(",");
                    // 20min内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 18)).append(",");
                    // 20min内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 3)).append(",");
                    // 20min内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, 31, 12)).append(",");
                    // 20min内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 10)).append(",");
                    // 20min内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 1)).append(",");
                    // 20min内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 2)).append(",");
                    // 20min内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 11)).append(",");
                    // 20min内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 21)).append(",");
                    // 20min内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 5)).append(",");
                    // 20min内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 8)).append(",");
                    // 20min内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 16)).append(",");
                    // 20min内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 18)).append(",");
                    // 20min内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 3)).append(",");
                    // 20min内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent20minLogin, -99, 12)).append(",");
                    // 1h内登录结果为1登录来源为10次数
                    ArrayList<Data> recent1hLogin = getRecentXLogin(allLoginArrayList, thisLogin,60);
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 10)).append(",");
                    // 1h内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 1)).append(",");
                    // 1h内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 2)).append(",");
                    // 1h内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 11)).append(",");
                    // 1h内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 21)).append(",");
                    // 1h内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 5)).append(",");
                    // 1h内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 8)).append(",");
                    // 1h内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 16)).append(",");
                    // 1h内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 18)).append(",");
                    // 1h内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 3)).append(",");
                    // 1h内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 1, 12)).append(",");
                    // 1h内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 10)).append(",");
                    // 1h内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 1)).append(",");
                    // 1h内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 2)).append(",");
                    // 1h内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 11)).append(",");
                    // 1h内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 21)).append(",");
                    // 1h内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 5)).append(",");
                    // 1h内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 8)).append(",");
                    // 1h内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 16)).append(",");
                    // 1h内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 18)).append(",");
                    // 1h内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 3)).append(",");
                    // 1h内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, 31, 12)).append(",");
                    // 1h内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 10)).append(",");
                    // 1h内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 1)).append(",");
                    // 1h内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 2)).append(",");
                    // 1h内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 11)).append(",");
                    // 1h内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 21)).append(",");
                    // 1h内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 5)).append(",");
                    // 1h内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 8)).append(",");
                    // 1h内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 16)).append(",");
                    // 1h内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 18)).append(",");
                    // 1h内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 3)).append(",");
                    // 1h内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent1hLogin, -99, 12)).append(",");
                    // 2h内登录结果为1登录来源为10次数
                    ArrayList<Data> recent2hLogin = getRecentXLogin(allLoginArrayList, thisLogin,120);
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 10)).append(",");
                    // 2h内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 1)).append(",");
                    // 2h内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 2)).append(",");
                    // 2h内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 11)).append(",");
                    // 2h内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 21)).append(",");
                    // 2h内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 5)).append(",");
                    // 2h内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 8)).append(",");
                    // 2h内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 16)).append(",");
                    // 2h内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 18)).append(",");
                    // 2h内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 3)).append(",");
                    // 2h内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 1, 12)).append(",");
                    // 2h内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 10)).append(",");
                    // 2h内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 1)).append(",");
                    // 2h内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 2)).append(",");
                    // 2h内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 11)).append(",");
                    // 2h内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 21)).append(",");
                    // 2h内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 5)).append(",");
                    // 2h内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 8)).append(",");
                    // 2h内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 16)).append(",");
                    // 2h内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 18)).append(",");
                    // 2h内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 3)).append(",");
                    // 2h内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, 31, 12)).append(",");
                    // 2h内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 10)).append(",");
                    // 2h内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 1)).append(",");
                    // 2h内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 2)).append(",");
                    // 2h内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 11)).append(",");
                    // 2h内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 21)).append(",");
                    // 2h内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 5)).append(",");
                    // 2h内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 8)).append(",");
                    // 2h内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 16)).append(",");
                    // 2h内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 18)).append(",");
                    // 2h内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 3)).append(",");
                    // 2h内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recent2hLogin, -99, 12)).append(",");
                    // 上段20min内登录结果为1登录来源为10次数
                    ArrayList<Data> recentLast20minLogin = getRecentXLogin(allLoginArrayList, last1TradeLogin,20);
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 10)).append(",");
                    // 上段20min内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 1)).append(",");
                    // 上段20min内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 2)).append(",");
                    // 上段20min内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 11)).append(",");
                    // 上段20min内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 21)).append(",");
                    // 上段20min内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 5)).append(",");
                    // 上段20min内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 8)).append(",");
                    // 上段20min内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 16)).append(",");
                    // 上段20min内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 18)).append(",");
                    // 上段20min内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 3)).append(",");
                    // 上段20min内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 1, 12)).append(",");
                    // 上段20min内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 10)).append(",");
                    // 上段20min内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 1)).append(",");
                    // 上段20min内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 2)).append(",");
                    // 上段20min内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 11)).append(",");
                    // 上段20min内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 21)).append(",");
                    // 上段20min内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 5)).append(",");
                    // 上段20min内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 8)).append(",");
                    // 上段20min内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 16)).append(",");
                    // 上段20min内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 18)).append(",");
                    // 上段20min内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 3)).append(",");
                    // 上段20min内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, 31, 12)).append(",");
                    // 上段20min内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 10)).append(",");
                    // 上段20min内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 1)).append(",");
                    // 上段20min内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 2)).append(",");
                    // 上段20min内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 11)).append(",");
                    // 上段20min内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 21)).append(",");
                    // 上段20min内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 5)).append(",");
                    // 上段20min内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 8)).append(",");
                    // 上段20min内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 16)).append(",");
                    // 上段20min内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 18)).append(",");
                    // 上段20min内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 3)).append(",");
                    // 上段20min内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast20minLogin, -99, 12)).append(",");
                    // 上段1h内登录结果为1登录来源为10次数
                    ArrayList<Data> recentLast1hLogin = getRecentXLogin(allLoginArrayList, last1TradeLogin,60);
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 10)).append(",");
                    // 上段1h内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 1)).append(",");
                    // 上段1h内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 2)).append(",");
                    // 上段1h内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 11)).append(",");
                    // 上段1h内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 21)).append(",");
                    // 上段1h内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 5)).append(",");
                    // 上段1h内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 8)).append(",");
                    // 上段1h内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 16)).append(",");
                    // 上段1h内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 18)).append(",");
                    // 上段1h内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 3)).append(",");
                    // 上段1h内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 1, 12)).append(",");
                    // 上段1h内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 10)).append(",");
                    // 上段1h内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 1)).append(",");
                    // 上段1h内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 2)).append(",");
                    // 上段1h内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 11)).append(",");
                    // 上段1h内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 21)).append(",");
                    // 上段1h内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 5)).append(",");
                    // 上段1h内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 8)).append(",");
                    // 上段1h内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 16)).append(",");
                    // 上段1h内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 18)).append(",");
                    // 上段1h内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 3)).append(",");
                    // 上段1h内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, 31, 12)).append(",");
                    // 上段1h内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 10)).append(",");
                    // 上段1h内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 1)).append(",");
                    // 上段1h内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 2)).append(",");
                    // 上段1h内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 11)).append(",");
                    // 上段1h内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 21)).append(",");
                    // 上段1h内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 5)).append(",");
                    // 上段1h内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 8)).append(",");
                    // 上段1h内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 16)).append(",");
                    // 上段1h内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 18)).append(",");
                    // 上段1h内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 3)).append(",");
                    // 上段1h内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast1hLogin, -99, 12)).append(",");
                    // 上段2h内登录结果为1登录来源为10次数
                    ArrayList<Data> recentLast2hLogin = getRecentXLogin(allLoginArrayList, last1TradeLogin,120);
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 10)).append(",");
                    // 上段2h内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 1)).append(",");
                    // 上段2h内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 2)).append(",");
                    // 上段2h内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 11)).append(",");
                    // 上段2h内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 21)).append(",");
                    // 上段2h内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 5)).append(",");
                    // 上段2h内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 8)).append(",");
                    // 上段2h内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 16)).append(",");
                    // 上段2h内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 18)).append(",");
                    // 上段2h内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 3)).append(",");
                    // 上段2h内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 1, 12)).append(",");
                    // 上段2h内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 10)).append(",");
                    // 上段2h内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 1)).append(",");
                    // 上段2h内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 2)).append(",");
                    // 上段2h内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 11)).append(",");
                    // 上段2h内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 21)).append(",");
                    // 上段2h内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 5)).append(",");
                    // 上段2h内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 8)).append(",");
                    // 上段2h内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 16)).append(",");
                    // 上段2h内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 18)).append(",");
                    // 上段2h内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 3)).append(",");
                    // 上段2h内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, 31, 12)).append(",");
                    // 上段2h内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 10)).append(",");
                    // 上段2h内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 1)).append(",");
                    // 上段2h内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 2)).append(",");
                    // 上段2h内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 11)).append(",");
                    // 上段2h内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 21)).append(",");
                    // 上段2h内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 5)).append(",");
                    // 上段2h内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 8)).append(",");
                    // 上段2h内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 16)).append(",");
                    // 上段2h内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 18)).append(",");
                    // 上段2h内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 3)).append(",");
                    // 上段2h内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLast2hLogin, -99, 12)).append(",");
                    // 上上段20min内登录结果为1登录来源为10次数
                    ArrayList<Data> recentLastLast20minLogin = getRecentXLogin(allLoginArrayList, last2TradeLogin,20);
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 10)).append(",");
                    // 上上段20min内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 1)).append(",");
                    // 上上段20min内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 2)).append(",");
                    // 上上段20min内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 11)).append(",");
                    // 上上段20min内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 21)).append(",");
                    // 上上段20min内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 5)).append(",");
                    // 上上段20min内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 8)).append(",");
                    // 上上段20min内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 16)).append(",");
                    // 上上段20min内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 18)).append(",");
                    // 上上段20min内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 3)).append(",");
                    // 上上段20min内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 1, 12)).append(",");
                    // 上上段20min内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 10)).append(",");
                    // 上上段20min内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 1)).append(",");
                    // 上上段20min内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 2)).append(",");
                    // 上上段20min内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 11)).append(",");
                    // 上上段20min内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 21)).append(",");
                    // 上上段20min内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 5)).append(",");
                    // 上上段20min内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 8)).append(",");
                    // 上上段20min内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 16)).append(",");
                    // 上上段20min内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 18)).append(",");
                    // 上上段20min内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 3)).append(",");
                    // 上上段20min内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, 31, 12)).append(",");
                    // 上上段20min内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 10)).append(",");
                    // 上上段20min内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 1)).append(",");
                    // 上上段20min内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 2)).append(",");
                    // 上上段20min内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 11)).append(",");
                    // 上上段20min内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 21)).append(",");
                    // 上上段20min内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 5)).append(",");
                    // 上上段20min内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 8)).append(",");
                    // 上上段20min内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 16)).append(",");
                    // 上上段20min内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 18)).append(",");
                    // 上上段20min内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 3)).append(",");
                    // 上上段20min内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast20minLogin, -99, 12)).append(",");
                    // 上上段1h内登录结果为1登录来源为10次数
                    ArrayList<Data> recentLastLast1hLogin = getRecentXLogin(allLoginArrayList, last2TradeLogin,60);
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 10)).append(",");
                    // 上上段1h内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 1)).append(",");
                    // 上上段1h内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 2)).append(",");
                    // 上上段1h内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 11)).append(",");
                    // 上上段1h内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 21)).append(",");
                    // 上上段1h内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 5)).append(",");
                    // 上上段1h内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 8)).append(",");
                    // 上上段1h内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 16)).append(",");
                    // 上上段1h内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 18)).append(",");
                    // 上上段1h内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 3)).append(",");
                    // 上上段1h内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 1, 12)).append(",");
                    // 上上段1h内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 10)).append(",");
                    // 上上段1h内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 1)).append(",");
                    // 上上段1h内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 2)).append(",");
                    // 上上段1h内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 11)).append(",");
                    // 上上段1h内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 21)).append(",");
                    // 上上段1h内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 5)).append(",");
                    // 上上段1h内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 8)).append(",");
                    // 上上段1h内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 16)).append(",");
                    // 上上段1h内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 18)).append(",");
                    // 上上段1h内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 3)).append(",");
                    // 上上段1h内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, 31, 12)).append(",");
                    // 上上段1h内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 10)).append(",");
                    // 上上段1h内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 1)).append(",");
                    // 上上段1h内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 2)).append(",");
                    // 上上段1h内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 11)).append(",");
                    // 上上段1h内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 21)).append(",");
                    // 上上段1h内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 5)).append(",");
                    // 上上段1h内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 8)).append(",");
                    // 上上段1h内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 16)).append(",");
                    // 上上段1h内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 18)).append(",");
                    // 上上段1h内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 3)).append(",");
                    // 上上段1h内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast1hLogin, -99, 12)).append(",");
                    // 上上段2h内登录结果为1登录来源为10次数
                    ArrayList<Data> recentLastLast2hLogin = getRecentXLogin(allLoginArrayList, last2TradeLogin,120);
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 10)).append(",");
                    // 上上段2h内登录结果为1登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 1)).append(",");
                    // 上上段2h内登录结果为1登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 2)).append(",");
                    // 上上段2h内登录结果为1登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 11)).append(",");
                    // 上上段2h内登录结果为1登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 21)).append(",");
                    // 上上段2h内登录结果为1登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 5)).append(",");
                    // 上上段2h内登录结果为1登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 8)).append(",");
                    // 上上段2h内登录结果为1登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 16)).append(",");
                    // 上上段2h内登录结果为1登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 18)).append(",");
                    // 上上段2h内登录结果为1登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 3)).append(",");
                    // 上上段2h内登录结果为1登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 1, 12)).append(",");
                    // 上上段2h内登录结果为31登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 10)).append(",");
                    // 上上段2h内登录结果为31登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 1)).append(",");
                    // 上上段2h内登录结果为31登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 2)).append(",");
                    // 上上段2h内登录结果为31登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 11)).append(",");
                    // 上上段2h内登录结果为31登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 21)).append(",");
                    // 上上段2h内登录结果为31登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 5)).append(",");
                    // 上上段2h内登录结果为31登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 8)).append(",");
                    // 上上段2h内登录结果为31登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 16)).append(",");
                    // 上上段2h内登录结果为31登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 18)).append(",");
                    // 上上段2h内登录结果为31登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 3)).append(",");
                    // 上上段2h内登录结果为31登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, 31, 12)).append(",");
                    // 上上段2h内登录结果为其他登录来源为10次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 10)).append(",");
                    // 上上段2h内登录结果为其他登录来源为1次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 1)).append(",");
                    // 上上段2h内登录结果为其他登录来源为2次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 2)).append(",");
                    // 上上段2h内登录结果为其他登录来源为11次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 11)).append(",");
                    // 上上段2h内登录结果为其他登录来源为21次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 21)).append(",");
                    // 上上段2h内登录结果为其他登录来源为5次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 5)).append(",");
                    // 上上段2h内登录结果为其他登录来源为8次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 8)).append(",");
                    // 上上段2h内登录结果为其他登录来源为16次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 16)).append(",");
                    // 上上段2h内登录结果为其他登录来源为18次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 18)).append(",");
                    // 上上段2h内登录结果为其他登录来源为3次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 3)).append(",");
                    // 上上段2h内登录结果为其他登录来源为12次数
                    stringBuffer.append(getResultXFromYNum(recentLastLast2hLogin, -99, 12)).append(",");
                    // 本次登录设备是否新设备
                    stringBuffer.append(getDeviceIsNew(thisLogin, allLoginArrayList, false, 1)).append(",");
                    // 本次登录设备是否成功新设备
                    stringBuffer.append(getDeviceIsNew(thisLogin, allLoginArrayList, true, 1)).append(",");
                    // 本次登录设备在10min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 0, 10)).append(",");
                    // 本次登录设备在30min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 0, 30)).append(",");
                    // 本次登录设备在1h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 0, 60)).append(",");
                    // 本次登录设备在1_2h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 60, 120)).append(",");
                    // 本次登录设备在2h_1d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 120, 60*24)).append(",");
                    // 本次登录设备在1_3d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 60*24, 3*60*24)).append(",");
                    // 本次登录设备在3_7d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 3*60*24, 7*60*24)).append(",");
                    // 本次登录设备在7_14d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 7*60*24, 14*60*24)).append(",");
                    // 本次登录设备在14_30d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 14*60*24, 30*60*24)).append(",");
                    // 本次登录设备在30_45d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 30*60*24, 45*60*24)).append(",");
                    // 本次登录设备在45_60d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 45*60*24, 60*60*24)).append(",");
                    // 本次登录设备在历史内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, false, 0, 1, 3*60*24, 365*60*24)).append(",");
                    // 本次登录设备登录结果为1在10min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 0, 10)).append(",");
                    // 本次登录设备登录结果为1在20min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 0, 20)).append(",");
                    // 本次登录设备登录结果为1在30min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 0, 30)).append(",");
                    // 本次登录设备登录结果为1在1h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 0, 60)).append(",");
                    // 本次登录设备登录结果为1在1_2h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 60, 120)).append(",");
                    // 本次登录设备登录结果为1在2h_1d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 60*2, 60*24)).append(",");
                    // 本次登录设备登录结果为1在1_3d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 60*24, 3*60*24)).append(",");
                    // 本次登录设备登录结果为1在3_7d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 3*60*24, 7*60*24)).append(",");
                    // 本次登录设备登录结果为1在7_14d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 7*60*24, 14*60*24)).append(",");
                    // 本次登录设备登录结果为1在14_30d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 14*60*24, 30*60*24)).append(",");
                    // 本次登录设备登录结果为1在30_45d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 30*60*24, 45*60*24)).append(",");
                    // 本次登录设备登录结果为1在45_60d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 45*60*24, 60*60*24)).append(",");
                    // 本次登录设备登录结果为1在历史内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 1, 3*60*24, 365*60*24)).append(",");
                    // 本次登录设备登录结果为31在10min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 0, 10)).append(",");
                    // 本次登录设备登录结果为31在20min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 0, 20)).append(",");
                    // 本次登录设备登录结果为31在30min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 0, 30)).append(",");
                    // 本次登录设备登录结果为31在1h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 0, 60)).append(",");
                    // 本次登录设备登录结果为31在1_2h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 60, 120)).append(",");
                    // 本次登录设备登录结果为31在2h_1d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 60*2, 60*24)).append(",");
                    // 本次登录设备登录结果为31在1_3d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 60*24, 3*60*24)).append(",");
                    // 本次登录设备登录结果为31在3_7d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 3*60*24, 7*60*24)).append(",");
                    // 本次登录设备登录结果为31在7_14d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 7*60*24, 14*60*24)).append(",");
                    // 本次登录设备登录结果为31在14_30d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 14*60*24, 30*60*24)).append(",");
                    // 本次登录设备登录结果为31在30_45d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 30*60*24, 45*60*24)).append(",");
                    // 本次登录设备登录结果为31在45_60d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 45*60*24, 60*60*24)).append(",");
                    // 本次登录设备登录结果为31在历史内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 0, 31, 3*60*24, 365*60*24)).append(",");
                    // 本次登录设备登录结果为负数在10min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 0, 10)).append(",");
                    // 本次登录设备登录结果为负数在20min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 0, 20)).append(",");
                    // 本次登录设备登录结果为负数在30min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 0, 30)).append(",");
                    // 本次登录设备登录结果为负数在1h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 0, 60)).append(",");
                    // 本次登录设备登录结果为负数在1_2h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 60, 120)).append(",");
                    // 本次登录设备登录结果为负数在2h_1d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 60*2, 60*24)).append(",");
                    // 本次登录设备登录结果为负数在1_3d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 60*24, 3*60*24)).append(",");
                    // 本次登录设备登录结果为负数在3_7d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 3*60*24, 7*60*24)).append(",");
                    // 本次登录设备登录结果为负数在7_14d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 7*60*24, 14*60*24)).append(",");
                    // 本次登录设备登录结果为负数在14_30d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 14*60*24, 30*60*24)).append(",");
                    // 本次登录设备登录结果为负数在30_45d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 30*60*24, 45*60*24)).append(",");
                    // 本次登录设备登录结果为负数在45_60d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 45*60*24, 60*60*24)).append(",");
                    // 本次登录设备登录结果为负数在历史内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, -1, 1, 3*60*24, 365*60*24)).append(",");
                    // 本次登录设备登录结果为正数在10min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 0, 10)).append(",");
                    // 本次登录设备登录结果为正数在20min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 0, 20)).append(",");
                    // 本次登录设备登录结果为正数在30min内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 0, 30)).append(",");
                    // 本次登录设备登录结果为正数在1h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 0, 60)).append(",");
                    // 本次登录设备登录结果为正数在1_2h内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 60, 120)).append(",");
                    // 本次登录设备登录结果为正数在2h_1d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 60*2, 60*24)).append(",");
                    // 本次登录设备登录结果为正数在1_3d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 60*24, 3*60*24)).append(",");
                    // 本次登录设备登录结果为正数在3_7d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 3*60*24, 7*60*24)).append(",");
                    // 本次登录设备登录结果为正数在7_14d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 7*60*24, 14*60*24)).append(",");
                    // 本次登录设备登录结果为正数在14_30d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 14*60*24, 30*60*24)).append(",");
                    // 本次登录设备登录结果为正数在30_45d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 30*60*24, 45*60*24)).append(",");
                    // 本次登录设备登录结果为正数在45_60d内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 45*60*24, 60*60*24)).append(",");
                    // 本次登录设备登录结果为正数在历史内登录次数
                    stringBuffer.append(getDeviceTimeResultLoginNum(thisLogin, allLoginArrayList, true, 1, 1, 3*60*24, 365*60*24)).append(",");
                    // 上段交易登录设备在10min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在30min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在1h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在1_2h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在2h_1d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 120, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在1_3d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在3_7d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在7_14d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在14_30d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备在30_45d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, false, 0, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在10min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在20min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在30min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在1h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在1_2h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在2h_1d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在1_3d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在3_7d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在7_14d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在14_30d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为1在30_45d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在10min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在20min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在30min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在1h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在1_2h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在2h_1d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在1_3d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在3_7d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在7_14d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在14_30d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为31在30_45d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 0, 31, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在10min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在20min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在30min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在1h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在1_2h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在2h_1d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在1_3d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在3_7d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在7_14d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在14_30d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为负数在30_45d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, -1, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在10min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在20min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在30min内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在1h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在1_2h内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在2h_1d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在1_3d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在3_7d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在7_14d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在14_30d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上段交易登录设备登录结果为正数在30_45d内登录次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last1TradeLogin, allLoginArrayList, true, 1, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在10min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在30min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在1h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在1_2h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在2h_1d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 120, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在1_3d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在3_7d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在7_14d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在14_30d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备在30_45d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, false, 0, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在10min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在20min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在30min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在1h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在1_2h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在2h_1d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在1_3d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在3_7d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在7_14d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在14_30d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为1在30_45d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在10min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在20min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在30min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在1h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在1_2h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在2h_1d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在1_3d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在3_7d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在7_14d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在14_30d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为31在30_45d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 0, 31, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在10min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在20min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在30min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在1h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在1_2h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在2h_1d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在1_3d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在3_7d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在7_14d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在14_30d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为负数在30_45d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, -1, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在10min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 0, 10)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在20min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 0, 20)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在30min内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 0, 30)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在1h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 0, 60)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在1_2h内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 60, 120)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在2h_1d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 60*2, 60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在1_3d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 60*24, 3*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在3_7d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 3*60*24, 7*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在7_14d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 7*60*24, 14*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在14_30d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 14*60*24, 30*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 上上段交易登录设备登录结果为正数在30_45d内登录次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceTimeResultLoginNum(last2TradeLogin, allLoginArrayList, true, 1, 1, 30*60*24, 45*60*24)).append(",");
                    } else {
                        stringBuffer.append("-1").append(",");
                    }
                    // 历史交易登录设备在10min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 0, 10)).append(",");
                    // 历史交易登录设备在30min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 0, 30)).append(",");
                    // 历史交易登录设备在1h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 0, 60)).append(",");
                    // 历史交易登录设备在1_2h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 60, 120)).append(",");
                    // 历史交易登录设备在2h_1d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 120, 24*60)).append(",");
                    // 历史交易登录设备在1_3d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 60*24, 3*60*24)).append(",");
                    // 历史交易登录设备在3_7d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 3*60*24, 7*60*24)).append(",");
                    // 历史交易登录设备在7_14d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 7*60*24, 14*60*24)).append(",");
                    // 历史交易登录设备在14_30d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 14*60*24, 30*60*24)).append(",");
                    // 历史交易登录设备在30_45d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, false, 0, 1, 30*60*24, 45*60*24)).append(",");
                    // 历史交易登录设备登录结果为1在10min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 0, 10)).append(",");
                    // 历史交易登录设备登录结果为1在20min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 0, 20)).append(",");
                    // 历史交易登录设备登录结果为1在30min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 0, 30)).append(",");
                    // 历史交易登录设备登录结果为1在1h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 0, 60)).append(",");
                    // 历史交易登录设备登录结果为1在1_2h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 60, 120)).append(",");
                    // 历史交易登录设备登录结果为1在2h_1d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 120, 24*60)).append(",");
                    // 历史交易登录设备登录结果为1在1_3d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 60*24, 3*60*24)).append(",");
                    // 历史交易登录设备登录结果为1在3_7d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 3*60*24, 7*60*24)).append(",");
                    // 历史交易登录设备登录结果为1在7_14d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 7*60*24, 14*60*24)).append(",");
                    // 历史交易登录设备登录结果为1在14_30d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 14*60*24, 30*60*24)).append(",");
                    // 历史交易登录设备登录结果为1在30_45d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 30*60*24, 45*60*24)).append(",");
                    // 历史交易登录设备登录结果为31在10min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 1, 0, 10)).append(",");
                    // 历史交易登录设备登录结果为31在20min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 0, 20)).append(",");
                    // 历史交易登录设备登录结果为31在30min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 0, 30)).append(",");
                    // 历史交易登录设备登录结果为31在1h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 0, 60)).append(",");
                    // 历史交易登录设备登录结果为31在1_2h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 60, 120)).append(",");
                    // 历史交易登录设备登录结果为31在2h_1d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 120, 24*60)).append(",");
                    // 历史交易登录设备登录结果为31在1_3d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 60*24, 3*60*24)).append(",");
                    // 历史交易登录设备登录结果为31在3_7d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 3*60*24, 7*60*24)).append(",");
                    // 历史交易登录设备登录结果为31在7_14d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 7*60*24, 14*60*24)).append(",");
                    // 历史交易登录设备登录结果为31在14_30d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 14*60*24, 30*60*24)).append(",");
                    // 历史交易登录设备登录结果为31在30_45d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 0, 31, 30*60*24, 45*60*24)).append(",");
                    // 历史交易登录设备登录结果为负数在10min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 0, 10)).append(",");
                    // 历史交易登录设备登录结果为负数在20min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 0, 20)).append(",");
                    // 历史交易登录设备登录结果为负数在30min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 0, 30)).append(",");
                    // 历史交易登录设备登录结果为负数在1h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 0, 60)).append(",");
                    // 历史交易登录设备登录结果为负数在1_2h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 60, 120)).append(",");
                    // 历史交易登录设备登录结果为负数在2h_1d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 120, 24*60)).append(",");
                    // 历史交易登录设备登录结果为负数在1_3d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 60*24, 3*60*24)).append(",");
                    // 历史交易登录设备登录结果为负数在3_7d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 3*60*24, 7*60*24)).append(",");
                    // 历史交易登录设备登录结果为负数在7_14d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 7*60*24, 14*60*24)).append(",");
                    // 历史交易登录设备登录结果为负数在14_30d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 14*60*24, 30*60*24)).append(",");
                    // 历史交易登录设备登录结果为负数在30_45d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, -1, 1, 30*60*24, 45*60*24)).append(",");
                    // 历史交易登录设备登录结果为正数在10min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 0, 10)).append(",");
                    // 历史交易登录设备登录结果为正数在20min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 0, 20)).append(",");
                    // 历史交易登录设备登录结果为正数在30min内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 0, 30)).append(",");
                    // 历史交易登录设备登录结果为正数在1h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 0, 60)).append(",");
                    // 历史交易登录设备登录结果为正数在1_2h内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 60, 120)).append(",");
                    // 历史交易登录设备登录结果为正数在2h_1d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 120, 24*60)).append(",");
                    // 历史交易登录设备登录结果为正数在1_3d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 60*24, 3*60*24)).append(",");
                    // 历史交易登录设备登录结果为正数在3_7d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 3*60*24, 7*60*24)).append(",");
                    // 历史交易登录设备登录结果为正数在7_14d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 7*60*24, 14*60*24)).append(",");
                    // 历史交易登录设备登录结果为正数在14_30d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 14*60*24, 30*60*24)).append(",");
                    // 历史交易登录设备登录结果为正数在30_45d内平均登录次数
                    stringBuffer.append(getHistoryTradeTimeResultLoginAvgNum(range3_365Trade, allLoginArrayList, true, 1, 1, 30*60*24, 45*60*24)).append(",");
                    // 本次登录设备3min内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 0, 3)).append(",");
                    // 本次登录设备10min内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 0, 10)).append(",");
                    // 本次登录设备20min内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 0, 20)).append(",");
                    // 本次登录设备30min内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 0, 30)).append(",");
                    // 本次登录设备1h内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 0, 60)).append(",");
                    // 本次登录设备2h内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 0, 120)).append(",");
                    // 本次登录设备2_6h内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 2*60, 6*60)).append(",");
                    // 本次登录设备6_12h内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 6*60, 12*60)).append(",");
                    // 本次登录设备12h_1d内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 12*60, 24*60)).append(",");
                    // 本次登录设备1_2d内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 24*60, 2*24*60)).append(",");
                    // 本次登录设备2_7d内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 2*24*60, 7*24*60)).append(",");
                    // 本次登录设备7_14d内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 7*24*60, 14*24*60)).append(",");
                    // 本次登录设备14_30d内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 14*24*60, 30*24*60)).append(",");
                    // 本次登录设备30_45d内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 30*24*60, 45*24*60)).append(",");
                    // 本次登录设备45_60d内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 45*24*60, 60*24*60)).append(",");
                    // 本次登录设备历史内登录其他用户次数
                    stringBuffer.append(getDeviceTimeUserNum(deviceMap1, thisLogin, user_id, 3*24*60, 365*24*60)).append(",");
                    // 本次登录设备3min内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 0, 3)).append(",");
                    // 本次登录设备10min内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 0, 10)).append(",");
                    // 本次登录设备20min内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 0, 20)).append(",");
                    // 本次登录设备30min内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 0, 30)).append(",");
                    // 本次登录设备1h内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 0, 60)).append(",");
                    // 本次登录设备2h内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 0, 120)).append(",");
                    // 本次登录设备2_6h内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 2*60, 6*60)).append(",");
                    // 本次登录设备6_12h内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 6*60, 12*60)).append(",");
                    // 本次登录设备12h_1d内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 12*60, 24*60)).append(",");
                    // 本次登录设备1_2d内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 24*60, 2*24*60)).append(",");
                    // 本次登录设备2_7d内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 2*24*60, 7*24*60)).append(",");
                    // 本次登录设备7_14d内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 7*24*60, 14*24*60)).append(",");
                    // 本次登录设备14_30d内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 14*24*60, 30*24*60)).append(",");
                    // 本次登录设备30_45d内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 30*24*60, 45*24*60)).append(",");
                    // 本次登录设备45_60d内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 45*24*60, 60*24*60)).append(",");
                    // 本次登录设备历史内登录其他用户次数结果为1
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 1, 3*24*60, 365*24*60)).append(",");
                    // 本次登录设备3min内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 0, 3)).append(",");
                    // 本次登录设备10min内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 0, 10)).append(",");
                    // 本次登录设备20min内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 0, 20)).append(",");
                    // 本次登录设备30min内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 0, 30)).append(",");
                    // 本次登录设备1h内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 0, 60)).append(",");
                    // 本次登录设备2h内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 0, 120)).append(",");
                    // 本次登录设备2_6h内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 2*60, 6*60)).append(",");
                    // 本次登录设备6_12h内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 6*60, 12*60)).append(",");
                    // 本次登录设备12h_1d内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 12*60, 24*60)).append(",");
                    // 本次登录设备1_2d内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 24*60, 2*24*60)).append(",");
                    // 本次登录设备2_7d内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 2*24*60, 7*24*60)).append(",");
                    // 本次登录设备7_14d内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 7*24*60, 14*24*60)).append(",");
                    // 本次登录设备14_30d内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 14*24*60, 30*24*60)).append(",");
                    // 本次登录设备30_45d内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 30*24*60, 45*24*60)).append(",");
                    // 本次登录设备45_60d内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 45*24*60, 60*24*60)).append(",");
                    // 本次登录设备历史内登录其他用户次数结果为31
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 31, 3*24*60, 365*24*60)).append(",");
                    // 本次登录设备3min内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 0, 3)).append(",");
                    // 本次登录设备10min内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 0, 10)).append(",");
                    // 本次登录设备20min内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 0, 20)).append(",");
                    // 本次登录设备30min内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 0, 30)).append(",");
                    // 本次登录设备1h内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 0, 60)).append(",");
                    // 本次登录设备2h内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 0, 120)).append(",");
                    // 本次登录设备2_6h内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 2*60, 6*60)).append(",");
                    // 本次登录设备6_12h内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 6*60, 12*60)).append(",");
                    // 本次登录设备12h_1d内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 12*60, 24*60)).append(",");
                    // 本次登录设备1_2d内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 24*60, 2*24*60)).append(",");
                    // 本次登录设备2_7d内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 2*24*60, 7*24*60)).append(",");
                    // 本次登录设备7_14d内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 7*24*60, 14*24*60)).append(",");
                    // 本次登录设备14_30d内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 14*24*60, 30*24*60)).append(",");
                    // 本次登录设备30_45d内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 30*24*60, 45*24*60)).append(",");
                    // 本次登录设备45_60d内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 45*24*60, 60*24*60)).append(",");
                    // 本次登录设备历史内登录其他用户次数结果为负数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, -99, 3*24*60, 365*24*60)).append(",");
                    // 本次登录设备3min内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 0, 3)).append(",");
                    // 本次登录设备10min内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 0, 10)).append(",");
                    // 本次登录设备20min内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 0, 20)).append(",");
                    // 本次登录设备30min内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 0, 30)).append(",");
                    // 本次登录设备1h内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 0, 60)).append(",");
                    // 本次登录设备2h内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 0, 120)).append(",");
                    // 本次登录设备2_6h内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 2*60, 6*60)).append(",");
                    // 本次登录设备6_12h内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 6*60, 12*60)).append(",");
                    // 本次登录设备12h_1d内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 12*60, 24*60)).append(",");
                    // 本次登录设备1_2d内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 24*60, 2*24*60)).append(",");
                    // 本次登录设备2_7d内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 2*24*60, 7*24*60)).append(",");
                    // 本次登录设备7_14d内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 7*24*60, 14*24*60)).append(",");
                    // 本次登录设备14_30d内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 14*24*60, 30*24*60)).append(",");
                    // 本次登录设备30_45d内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 30*24*60, 45*24*60)).append(",");
                    // 本次登录设备45_60d内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 45*24*60, 60*24*60)).append(",");
                    // 本次登录设备历史内登录其他用户次数结果为正数
                    stringBuffer.append(getDeviceTimeResultUserNum(deviceMap2, thisLogin, user_id, 99, 3*24*60, 365*24*60)).append(",");
                    // 10min内登录不同设备数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 10, false, 1)).append(",");
                    // 30min内登录不同设备数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 30, false, 1)).append(",");
                    // 2h内登录不同设备数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 120, false, 1)).append(",");
                    // 6h内登录不同设备数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 6*60, false, 1)).append(",");
                    // 1d内登录不同设备数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 24*60, false, 1)).append(",");
                    // 10min内登录设备中新设备数量且结果为1
                    stringBuffer.append(getWithinTimeNewDeviceNum(thisLogin, allLoginArrayList, 10, 3*24*60, 1)).append(",");
                    // 30min内登录设备中新设备数量且结果为1
                    stringBuffer.append(getWithinTimeNewDeviceNum(thisLogin, allLoginArrayList, 30, 3*24*60, 1)).append(",");
                    // 2h内登录设备中新设备数量且结果为1
                    stringBuffer.append(getWithinTimeNewDeviceNum(thisLogin, allLoginArrayList, 120, 3*24*60, 1)).append(",");
                    // 6h内登录设备中新设备数量且结果为1
                    stringBuffer.append(getWithinTimeNewDeviceNum(thisLogin, allLoginArrayList, 6*60, 3*24*60, 1)).append(",");
                    // 1d内登录设备中新设备数量且结果为1
                    stringBuffer.append(getWithinTimeNewDeviceNum(thisLogin, allLoginArrayList, 24*60, 3*24*60, 1)).append(",");
                    // 10min内登录不同设备数结果为1
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 10, true, 1)).append(",");
                    // 30min内登录不同设备数结果为1
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 30, true, 1)).append(",");
                    // 2h内登录不同设备数结果为1
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 120, true, 1)).append(",");
                    // 6h内登录不同设备数结果为1
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 6*60, true, 1)).append(",");
                    // 1d内登录不同设备数结果为1
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 24*60, true, 1)).append(",");
                    // 10min内登录不同设备数结果为31
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 10, true, 31)).append(",");
                    // 30min内登录不同设备数结果为31
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 30, true, 31)).append(",");
                    // 2h内登录不同设备数结果为31
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 120, true, 31)).append(",");
                    // 6h内登录不同设备数结果为31
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 6*60, true, 31)).append(",");
                    // 1d内登录不同设备数结果为31
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 24*60, true, 31)).append(",");
                    // 10min内登录不同设备数结果为负数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 10, true, -99)).append(",");
                    // 30min内登录不同设备数结果为负数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 30, true, -99)).append(",");
                    // 2h内登录不同设备数结果为负数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 120, true, -99)).append(",");
                    // 6h内登录不同设备数结果为负数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 6*60, true, -99)).append(",");
                    // 1d内登录不同设备数结果为负数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 24*60, true, -99)).append(",");
                    // 10min内登录不同设备数结果为正数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 10, true, 99)).append(",");
                    // 30min内登录不同设备数结果为正数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 30, true, 99)).append(",");
                    // 2h内登录不同设备数结果为正数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 120, true, 99)).append(",");
                    // 6h内登录不同设备数结果为正数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 6*60, true, 99)).append(",");
                    // 1d内登录不同设备数结果为正数
                    stringBuffer.append(getThisLoginTimeDeviceNum(thisLogin, allLoginArrayList, 24*60, true, 99)).append(",");
                    // 本次登录是否为新城市
                    stringBuffer.append(getThisIsNewCity(thisLogin, allLoginArrayList)).append(",");
                    // 本次登录城市在0_30min内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 0, 30)).append(",");
                    // 本次登录城市在30min_1h内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 30, 60)).append(",");
                    // 本次登录城市在1_2h内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 60, 120)).append(",");
                    // 本次登录城市在2h_1d内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 120, 24*60)).append(",");
                    // 本次登录城市在1_3d内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 24*60, 3*24*60)).append(",");
                    // 本次登录城市在3_7d内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 3*24*60, 7*24*60)).append(",");
                    // 本次登录城市在7_14d内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 7*24*60, 14*24*60)).append(",");
                    // 本次登录城市在14_30d内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 14*24*60, 30*24*60)).append(",");
                    // 本次登录城市在30_45d内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 30*24*60, 45*24*60)).append(",");
                    // 本次登录城市在45_60d内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 45*24*60, 60*24*60)).append(",");
                    // 本次登录城市在历史内被登录次数
                    stringBuffer.append(getCitySuccessLoginNum(thisLogin, allLoginArrayList, 3*24*60, 365*24*60)).append(",");
                    // 10min内登录不同城市数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 10, false, 1)).append(",");
                    // 30min内登录不同城市数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 30, false, 1)).append(",");
                    // 2h内登录不同城市数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 120, false, 1)).append(",");
                    // 6h内登录不同城市数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 6*60, false, 1)).append(",");
                    // 1d内登录不同城市数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 24*60, false, 1)).append(",");
                    // 10min内登录不同城市数结果为1
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 10, true, 1)).append(",");
                    // 30min内登录不同城市数结果为1
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 30, true, 1)).append(",");
                    // 2h内登录不同城市数结果为1
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 120, true, 1)).append(",");
                    // 6h内登录不同城市数结果为1
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 6*60, true, 1)).append(",");
                    // 1d内登录不同城市数结果为1
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 24*60, true, 1)).append(",");
                    // 10min内登录不同城市数结果为31
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 10, true, 31)).append(",");
                    // 30min内登录不同城市数结果为31
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 30, true, 31)).append(",");
                    // 2h内登录不同城市数结果为31
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 120, true, 31)).append(",");
                    // 6h内登录不同城市数结果为31
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 6*60, true, 31)).append(",");
                    // 1d内登录不同城市数结果为31
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 24*60, true, 31)).append(",");
                    // 10min内登录不同城市数结果为负数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 10, true, -99)).append(",");
                    // 30min内登录不同城市数结果为负数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 30, true, -99)).append(",");
                    // 2h内登录不同城市数结果为负数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 120, true, -99)).append(",");
                    // 6h内登录不同城市数结果为负数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 6*60, true, -99)).append(",");
                    // 1d内登录不同城市数结果为负数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 24*60, true, -99)).append(",");
                    // 10min内登录不同城市数结果为正数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 10, true, 99)).append(",");
                    // 30min内登录不同城市数结果为正数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 30, true, 99)).append(",");
                    // 2h内登录不同城市数结果为正数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 120, true, 99)).append(",");
                    // 6h内登录不同城市数结果为正数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 6*60, true, 99)).append(",");
                    // 1d内登录不同城市数结果为正数
                    stringBuffer.append(getThisLoginTimeCityNum(thisLogin, allLoginArrayList, 24*60, true, 99)).append(",");
                    // 本次登录是否为新IP
                    stringBuffer.append(getThisIsNewIP(thisLogin, allLoginArrayList)).append(",");
                    // 本次登录IP在0_30min内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 0, 30)).append(",");
                    // 本次登录IP在30min_1h内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 30, 60)).append(",");
                    // 本次登录IP在1_2h内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 60, 120)).append(",");
                    // 本次登录IP在2h_1d内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 120, 24*60)).append(",");
                    // 本次登录IP在1_3d内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 24*60, 3*24*60)).append(",");
                    // 本次登录IP在3_7d内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 3*24*60, 7*24*60)).append(",");
                    // 本次登录IP在7_14d内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 7*24*60, 14*24*60)).append(",");
                    // 本次登录IP在14_30d内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 14*24*60, 30*24*60)).append(",");
                    // 本次登录IP在30_45d内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 30*24*60, 45*24*60)).append(",");
                    // 本次登录IP在45_60d内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 45*24*60, 60*24*60)).append(",");
                    // 本次登录IP在历史内被登录次数
                    stringBuffer.append(getIPSuccessLoginNum(thisLogin, allLoginArrayList, 3*24*60, 365*24*60)).append(",");
                    // 10min内登录不同IP数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 10, false, 1)).append(",");
                    // 30min内登录不同IP数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 30, false, 1)).append(",");
                    // 2h内登录不同IP数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 120, false, 1)).append(",");
                    // 6h内登录不同IP数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 6*60, false, 1)).append(",");
                    // 1d内登录不同IP数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 24*60, false, 1)).append(",");
                    // 10min内登录不同IP数结果为1
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 10, true, 1)).append(",");
                    // 30min内登录不同IP数结果为1
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 30, true, 1)).append(",");
                    // 2h内登录不同IP数结果为1
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 120, true, 1)).append(",");
                    // 6h内登录不同IP数结果为1
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 6*60, true, 1)).append(",");
                    // 1d内登录不同IP数结果为1
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 24*60, true, 1)).append(",");
                    // 10min内登录不同IP数结果为31
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 10, true, 31)).append(",");
                    // 30min内登录不同IP数结果为31
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 30, true, 31)).append(",");
                    // 2h内登录不同IP数结果为31
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 120, true, 31)).append(",");
                    // 6h内登录不同IP数结果为31
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 6*60, true, 31)).append(",");
                    // 1d内登录不同IP数结果为31
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 24*60, true, 31)).append(",");
                    // 10min内登录不同IP数结果为负数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 10, true, -99)).append(",");
                    // 30min内登录不同IP数结果为负数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 30, true, -99)).append(",");
                    // 2h内登录不同IP数结果为负数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 120, true, -99)).append(",");
                    // 6h内登录不同IP数结果为负数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 6*60, true, -99)).append(",");
                    // 1d内登录不同IP数结果为负数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 24*60, true, -99)).append(",");
                    // 10min内登录不同IP数结果为正数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 10, true, 99)).append(",");
                    // 30min内登录不同IP数结果为正数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 30, true, 99)).append(",");
                    // 2h内登录不同IP数结果为正数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 120, true, 99)).append(",");
                    // 6h内登录不同IP数结果为正数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 6*60, true, 99)).append(",");
                    // 1d内登录不同IP数结果为正数
                    stringBuffer.append(getThisLoginTimeIPNum(thisLogin, allLoginArrayList, 24*60, true, 99)).append(",");
                    // 10min内登录结果为1次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 10, 1)).append(",");
                    // 10min内登录结果为31次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 10, 31)).append(",");
                    // 10min内登录结果为正数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 10, 99)).append(",");
                    // 10min内登录结果为负数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 10, -99)).append(",");
                    // 20min内登录结果为1次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 20, 1)).append(",");
                    // 20min内登录结果为31次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 20, 31)).append(",");
                    // 20min内登录结果为正数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 20, 99)).append(",");
                    // 20min内登录结果为负数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 20, -99)).append(",");
                    // 45min内登录结果为1次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 45, 1)).append(",");
                    // 45min内登录结果为31次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 45, 31)).append(",");
                    // 45min内登录结果为正数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 45, 99)).append(",");
                    // 45min内登录结果为负数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 45, -99)).append(",");
                    // 90min内登录结果为1次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 90, 1)).append(",");
                    // 90min内登录结果为31次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 90, 31)).append(",");
                    // 90min内登录结果为正数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 90, 99)).append(",");
                    // 90min内登录结果为负数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 90, -99)).append(",");
                    // 120min内登录结果为1次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 120, 1)).append(",");
                    // 120min内登录结果为31次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 120, 31)).append(",");
                    // 120min内登录结果为正数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 120, 99)).append(",");
                    // 120min内登录结果为负数次数
                    stringBuffer.append(getResultTimeNum(thisLogin, allLoginArrayList, 120, -99)).append(",");
                    // 本次登录设备5min内连接IP次数
                    stringBuffer.append(getDeviceConnectIPTimeNum(thisLogin, allLoginArrayList, 5)).append(",");
                    // 本次登录设备15min内连接IP次数
                    stringBuffer.append(getDeviceConnectIPTimeNum(thisLogin, allLoginArrayList, 15)).append(",");
                    // 本次登录设备30min内连接IP次数
                    stringBuffer.append(getDeviceConnectIPTimeNum(thisLogin, allLoginArrayList, 30)).append(",");
                    // 本次登录设备60min内连接IP次数
                    stringBuffer.append(getDeviceConnectIPTimeNum(thisLogin, allLoginArrayList, 60)).append(",");
                    // 上段交易登录设备5min内连接IP次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last1TradeLogin, allLoginArrayList, 5)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上段交易登录设备15min内连接IP次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last1TradeLogin, allLoginArrayList, 15)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上段交易登录设备30min内连接IP次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last1TradeLogin, allLoginArrayList, 30)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上段交易登录设备60min内连接IP次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last1TradeLogin, allLoginArrayList, 60)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备5min内连接IP次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last2TradeLogin, allLoginArrayList, 5)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备15min内连接IP次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last2TradeLogin, allLoginArrayList, 15)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备30min内连接IP次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last2TradeLogin, allLoginArrayList, 30)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备60min内连接IP次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectIPTimeNum(last2TradeLogin, allLoginArrayList, 60)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 历史登录设备5min内连接IP平均次数
                    stringBuffer.append(getHistoryDeviceConnectIPTimeNum(range3_365Trade, allLoginArrayList, 5)).append(",");
                    // 历史登录设备15min内连接IP平均次数
                    stringBuffer.append(getHistoryDeviceConnectIPTimeNum(range3_365Trade, allLoginArrayList, 15)).append(",");
                    // 历史登录设备30min内连接IP平均次数
                    stringBuffer.append(getHistoryDeviceConnectIPTimeNum(range3_365Trade, allLoginArrayList, 30)).append(",");
                    // 历史登录设备60min内连接IP平均次数
                    stringBuffer.append(getHistoryDeviceConnectIPTimeNum(range3_365Trade, allLoginArrayList, 60)).append(",");
                    // 本次登录设备5min内连接城市次数
                    stringBuffer.append(getDeviceConnectCityTimeNum(thisLogin, allLoginArrayList, 5)).append(",");
                    // 本次登录设备15min内连接城市次数
                    stringBuffer.append(getDeviceConnectCityTimeNum(thisLogin, allLoginArrayList, 15)).append(",");
                    // 本次登录设备30min内连接城市次数
                    stringBuffer.append(getDeviceConnectCityTimeNum(thisLogin, allLoginArrayList, 30)).append(",");
                    // 本次登录设备60min内连接城市次数
                    stringBuffer.append(getDeviceConnectCityTimeNum(thisLogin, allLoginArrayList, 60)).append(",");
                    // 上段交易登录设备5min内连接城市次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last1TradeLogin, allLoginArrayList, 5)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上段交易登录设备15min内连接城市次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last1TradeLogin, allLoginArrayList, 15)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上段交易登录设备30min内连接城市次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last1TradeLogin, allLoginArrayList, 30)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上段交易登录设备60min内连接城市次数
                    if (hasLast1Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last1TradeLogin, allLoginArrayList, 60)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备5min内连接城市次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last2TradeLogin, allLoginArrayList, 5)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备15min内连接城市次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last2TradeLogin, allLoginArrayList, 15)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备30min内连接城市次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last2TradeLogin, allLoginArrayList, 30)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 上上段登录设备60min内连接城市次数
                    if (hasLast2Trade) {
                        stringBuffer.append(getDeviceConnectCityTimeNum(last2TradeLogin, allLoginArrayList, 60)).append(",");
                    } else {
                        stringBuffer.append(-1).append(",");
                    }
                    // 历史登录设备5min内连接城市平均次数
                    stringBuffer.append(getHistoryDeviceConnectCityTimeNum(range3_365Trade, allLoginArrayList, 5)).append(",");
                    // 历史登录设备15min内连接城市平均次数
                    stringBuffer.append(getHistoryDeviceConnectCityTimeNum(range3_365Trade, allLoginArrayList, 15)).append(",");
                    // 历史登录设备30min内连接城市平均次数
                    stringBuffer.append(getHistoryDeviceConnectCityTimeNum(range3_365Trade, allLoginArrayList, 30)).append(",");
                    // 历史登录设备60min内连接城市平均次数
                    stringBuffer.append(getHistoryDeviceConnectCityTimeNum(range3_365Trade, allLoginArrayList, 60)).append(",");
                    // 本次登录10min内登录存在登录结果为1后结果为其他的情况
                    stringBuffer.append(getThisLoginResultSuccessThenPasswdError(thisLogin, allLoginArrayList, 10)).append(",");
                    // 本次登录20min内登录存在登录结果为1后结果为其他的情况
                    stringBuffer.append(getThisLoginResultSuccessThenPasswdError(thisLogin, allLoginArrayList, 20)).append(",");
                    // 本次登录10min内登录存在登录结果为1后结果为其他的情况且设备不同
                    stringBuffer.append(getThisLoginResultSuccessThenPasswdErrorDevice(thisLogin, allLoginArrayList, 10)).append(",");
                    // 本次登录20min内登录存在登录结果为1后结果为其他的情况且设备不同
                    stringBuffer.append(getThisLoginResultSuccessThenPasswdErrorDevice(thisLogin, allLoginArrayList, 20)).append(",");

                    // 用户全局是否只有本段交易
                    stringBuffer.append(getUserGlobalOnlyThisIntervalTrade(allActionArrayList)).append(",");
                    // 用户全局行为是否全在前后24h内
                    stringBuffer.append(getUserGlobalActionWithin24h(allActionArrayList)).append(",");
                    // 本次设备是交易设备概率
                    stringBuffer.append(getDeviceIsTradeDeviceProb(thisLogin, allLoginArrayList)).append(",");
                    // 本段交易数量
                    stringBuffer.append(thisIntervalTrade.size()).append(",");

                    stringBuffer.append("over");

                    int pesudo_label_1;
                    int pesudo_label_2;

                    HashMap<Integer, Integer> hashMap2 = new HashMap<>();
                    for (Data data : thisIntervalTrade) {
                        if (hashMap2.containsKey(data.getIs_risk())) {
                            hashMap2.put(data.getIs_risk(), hashMap2.get(data.getIs_risk())+1);
                        } else {
                            hashMap2.put(data.getIs_risk(),1);
                        }
                    }
                    if (hashMap2.containsKey(1) && hashMap2.containsKey(0)) {
                        if (hashMap2.get(1) >= hashMap2.get(0)*5) {
                            pesudo_label_1 = 1;
                            pesudo_label_2 = 2;
                        } else {
                            pesudo_label_1 = 0;
                            pesudo_label_2 = 2;
                        }
                    } else {
                        if (hashMap2.containsKey(1)) {
                            pesudo_label_1 = 1;
                            pesudo_label_2 = 1;
                        } else {
                            pesudo_label_1 = 0;
                            pesudo_label_2 = 0;
                        }
                    }

                    for (Data data : thisIntervalTrade) {
                        String row_name = data.getRow_name();
                        String time = data.getTime();
                        int actual_label = data.getIs_risk();
                        bufferedWriter.write(row_name + "," + user_id + "," + time + "," + pesudo_label_1 + ","
                                + pesudo_label_2 + "," + actual_label + "," + stringBuffer.toString() + "\r\n");
                    }

                }
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
