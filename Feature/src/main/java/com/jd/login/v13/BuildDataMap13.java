package com.jd.login.v13;

import com.jd.login.v6.Data;

import java.io.*;
import java.util.*;

public class BuildDataMap13 {
    public static void main(String[] args) {
        try {
            HashMap<Integer, ArrayList<Data>> hashMap = new HashMap<>();

            hashMap = putLogin2Map(hashMap, "/Users/admin/Documents/K/login/v_login.csv");
            hashMap = putLogin2Map(hashMap, "/Users/admin/Documents/K/login/v_login_test.csv");
            hashMap = putTrade2Map(hashMap, "/Users/admin/Documents/K/login/v_trade.csv");
            hashMap = putTradeTest2Map(hashMap, "/Users/admin/Documents/K/login/v_trade_test.csv");

            HashMap<Integer, ArrayList<Data>> hashMap1 = new HashMap<>();
            for (Map.Entry<Integer, ArrayList<Data>> map : hashMap.entrySet()) {
                int key = map.getKey();
                ArrayList<Data> value = map.getValue();
                Collections.sort(value, new Comparator<Data>() {
                    @Override
                    public int compare(Data o1, Data o2) {
                        return o1.getTime().compareTo(o2.getTime());
                    }
                });
                hashMap1.put(key, value);
            }
            File file = new File("/Users/admin/Documents/K/login/1205/1205_data_merge.hashmap");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashMap1);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, ArrayList<Data>> putLogin2Map(HashMap<Integer, ArrayList<Data>> hashMap, String fileName) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        int i = 0;
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            i++;
            if (i == 1) {
                continue;
            } else {
                if (i % 1000 == 0) {
                    System.out.println(i);
                }
                strLine = strLine.replace(".0","");
                int timelong = Integer.valueOf(strLine.split(",")[2]);
                int device = Integer.valueOf(strLine.split(",")[3]);
                int log_from = Integer.valueOf(strLine.split(",")[4]);
                int ip = Integer.valueOf(strLine.split(",")[5]);
                int city = Integer.valueOf(strLine.split(",")[6]);
                int result = Integer.valueOf(strLine.split(",")[7]);
                int type = Integer.valueOf(strLine.split(",")[9]);
                Integer id = Integer.valueOf(strLine.split(",")[10]);
                int is_scan = transformBoolean(strLine.split(",")[11]);
                String time = strLine.split(",")[13];
                String row_name = strLine.split(",")[14];
                Data data = new Data(time, row_name, "login", -2, timelong,
                        device, log_from, ip, city, result, type, is_scan);
                if (device != 835072) {
                    if (hashMap.containsKey(id)) {
                        hashMap.get(id).add(data);
                    } else {
                        hashMap.put(id, new ArrayList<>());
                        hashMap.get(id).add(data);
                    }
                }
            }
        }
        bufferedReader.close();
        fileInputStream.close();
        return hashMap;
    }

    public static HashMap<Integer, ArrayList<Data>> putTrade2Map(HashMap<Integer, ArrayList<Data>> hashMap, String fileName) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        int i = 0;
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            i++;
            if (i == 1) {
                continue;
            } else {
                if (i % 1000 == 0) {
                    System.out.println(i);
                }
                String time = strLine.split(",")[1];
                Integer id = Integer.valueOf(strLine.split(",")[2]);
                int is_risk = Integer.valueOf(strLine.split(",")[3]);
                String row_name = strLine.split(",")[4];
                Data data = new Data(time, row_name, "trade_train", is_risk, -1,
                        -1,-1,-1,-1,-1,-1,-1);
                if (hashMap.containsKey(id)) {
                    hashMap.get(id).add(data);
                } else {
                    hashMap.put(id, new ArrayList<>());
                    hashMap.get(id).add(data);
                }
            }
        }
        bufferedReader.close();
        fileInputStream.close();
        return hashMap;
    }

    public static HashMap<Integer, ArrayList<Data>> putTradeTest2Map(HashMap<Integer, ArrayList<Data>> hashMap, String fileName) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        int i = 0;
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            i++;
            if (i == 1) {
                continue;
            } else {
                if (i % 1000 == 0) {
                    System.out.println(i);
                }
                String time = strLine.split(",")[1];
                Integer id = Integer.valueOf(strLine.split(",")[2]);
                String row_name = strLine.split(",")[3];
                Data data = new Data(time, row_name, "trade_test", -1,
                        -1,-1,-1,-1,-1,-1,-1,-1);
                if (hashMap.containsKey(id)) {
                    hashMap.get(id).add(data);
                } else {
                    hashMap.put(id, new ArrayList<>());
                    hashMap.get(id).add(data);
                }
            }
        }
        bufferedReader.close();
        fileInputStream.close();
        return hashMap;
    }

    public static int transformBoolean(String s) {
        s = s.toLowerCase();
        if (s.equals("true")) {
            return 1;
        } else {
            return 0;
        }
    }
}
