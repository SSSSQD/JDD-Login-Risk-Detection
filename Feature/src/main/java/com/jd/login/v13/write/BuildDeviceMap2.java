package com.jd.login.v13.write;

import com.jd.login.v6.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuildDeviceMap2 {
    public static void main(String[] args) {
        try {
            File file = new File("/Users/admin/Documents/K/login/1205/1205_data_merge.hashmap");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<Integer, ArrayList<Data>> hashMap2 = (HashMap<Integer, ArrayList<Data>>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            // <device, <user_id, data>>
            HashMap<Integer, HashMap<Integer, ArrayList<Data>>> hashMap = new HashMap<>();
            for (Map.Entry<Integer, ArrayList<Data>> map : hashMap2.entrySet()) {
                int user_id = map.getKey();
                ArrayList<Data> arrayList = map.getValue();
                for (Data data : arrayList) {
                    if (data.getData_type().contains("login")) {
                        int device = data.getDevice();
                        if (hashMap.containsKey(device)) {
                            if (hashMap.get(data.getDevice()).containsKey(user_id)) {
                                hashMap.get(data.getDevice()).get(user_id).add(data);
                            } else {
                                hashMap.get(data.getDevice()).put(user_id, new ArrayList<>());
                                hashMap.get(data.getDevice()).get(user_id).add(data);
                            }
                        } else {
                            hashMap.put(device, new HashMap<>());
                            hashMap.get(device).put(user_id, new ArrayList<>());
                            hashMap.get(device).get(user_id).add(data);
                        }
                    }
                }
            }

            File file3 = new File("/Users/admin/Documents/K/login/1205/1205_device.hashmap2");
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
