package com.jd.login;

import com.jd.login.v12.TradeAndLogin;
import com.jd.login.v6.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.jd.login.Utils.*;

public class WhiteList {
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

            int i = 0;

            for (Map.Entry<Integer, ArrayList<TradeAndLogin>> map : hashMapHashMap.entrySet()) {
                i++;
<<<<<<< HEAD
                
=======
>>>>>>> d405891e2a1c8b7f1bda13012ca2b06ce2e520de
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
                    ArrayList<Data> thisIntervalTrade = map2.getThisIntervalLogin();
                    Data thisLogin = map2.getThisLogin();
                    String time1 = timeDiff(thisLogin.getTime(), 3*24*60);
                    String time2 = timeDiff(thisLogin.getTime(), -3*24*60);
                    int thisLoginCity = thisLogin.getCity();
                    int var1 = 0;
                    int var2 = 0;
                    for (Data data : allLoginArrayList) {
                        if (data.getTime().compareTo(time2) >= 0) {
                            if (data.getCity() == thisLoginCity && data.getResult() == 1) {
                                var1++;
                            }
                        }
                        if (time1.compareTo(data.getTime()) >= 0) {
                            if (data.getCity() == thisLoginCity && data.getResult() == 1) {
                                var2++;
                            }
                        }
                    }
                    if (var1 >= 1 && var2 >= 1) {
                        for (Data data : thisIntervalTrade) {
                            System.out.println(user_id + "," + data.getRow_name());
                        }
                        System.out.println("--------------------");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
