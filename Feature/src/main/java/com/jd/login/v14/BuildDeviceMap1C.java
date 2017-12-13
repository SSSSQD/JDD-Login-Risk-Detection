package com.jd.login.v14;

import com.jd.login.v12.TradeAndLogin;
import com.jd.login.v6.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuildDeviceMap1C {
    public static void main(String[] args) {
        try {
            File file2 = new File("/Users/admin/Documents/K/login/1206/1206_ml_instance_c.hashmap");
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            HashMap<Integer, ArrayList<TradeAndLogin>> hashMapHashMap = (HashMap<Integer, ArrayList<TradeAndLogin>>) objectInputStream2.readObject();
            objectInputStream2.close();
            fileInputStream2.close();

            // <device, <user_id, data>>
            HashMap<Integer, HashMap<Integer, ArrayList<Data>>> hashMap = new HashMap<>();
            for (Map.Entry<Integer, ArrayList<TradeAndLogin>> map : hashMapHashMap.entrySet()) {
                int user_id = map.getKey();
                for (TradeAndLogin tradeAndLogin : map.getValue()) {
                    Data data = tradeAndLogin.getThisLogin();
                    if (hashMap.containsKey(data.getDevice())) {
                        if (hashMap.get(data.getDevice()).containsKey(user_id)) {
                            hashMap.get(data.getDevice()).get(user_id).add(data);
                        } else {
                            hashMap.get(data.getDevice()).put(user_id, new ArrayList<>());
                            hashMap.get(data.getDevice()).get(user_id).add(data);
                        }
                    } else {
                        hashMap.put(data.getDevice(), new HashMap<>());
                        hashMap.get(data.getDevice()).put(user_id, new ArrayList<>());
                        hashMap.get(data.getDevice()).get(user_id).add(data);
                    }
                }
            }

            File file3 = new File("/Users/admin/Documents/K/login/1206/1206_device_c.hashmap1");
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashMap);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
