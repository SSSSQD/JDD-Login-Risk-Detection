package com.jd.login.v14;

import com.jd.login.v12.TradeAndLogin;
import com.jd.login.v6.Data;

import java.io.*;
import java.util.*;

import static com.jd.login.Utils.timeDiff;

public class MLInstance14C {
    public static void main(String[] args) {
        try {
            File file = new File("/Users/admin/Documents/K/login/1205/1205_data_merge.hashmap");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<Integer, ArrayList<Data>> hashMap = (HashMap<Integer, ArrayList<Data>>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            HashMap<Integer, ArrayList<TradeAndLogin>> hashMapHashMap = new HashMap<>();

            int k = 0;
            for (Map.Entry<Integer, ArrayList<Data>> map : hashMap.entrySet()) {
                ArrayList<TradeAndLogin> tradeAndLogins = new ArrayList<>();
                k++;
<<<<<<< HEAD
                
=======
>>>>>>> d405891e2a1c8b7f1bda13012ca2b06ce2e520de
                if (k % 300 == 0) {
                    System.out.println(k);
                }
                int user_id = map.getKey();
                Data[] arr = map.getValue().toArray(new Data[map.getValue().size()]);
//                LinkedHashMap<ArrayList<Data>, Data> hashMap1 = new LinkedHashMap<>();
                HashSet<ArrayList<Data>> hashSet = new HashSet<>();
                ArrayList<Data> arrayList = new ArrayList<>();
                for (Data data : arr) {
                    if (data.getData_type().contains("trade")) {
                        arrayList.add(data);
                    }
                }
                Data[] arr1 = arrayList.toArray(new Data[arrayList.size()]);
                if (arr1.length == 1) {
                    ArrayList<Data> thisIntervalTrade = new ArrayList<>();
                    thisIntervalTrade.add(arr1[0]);
                    hashSet.add(thisIntervalTrade);
                } else {
                    ArrayList<Data> thisIntervalTrade = new ArrayList<>();
                    thisIntervalTrade.add(arr1[0]);
                    for (int j = 1; j < arr1.length; ++j) {
                        if (timeDiff(arr1[j].getTime(), arr1[j-1].getTime(), "minute") <= 20) {
                            thisIntervalTrade.add(arr1[j]);
                        } else {
                            hashSet.add(thisIntervalTrade);
                            thisIntervalTrade = new ArrayList<>();
                            thisIntervalTrade.add(arr1[j]);
                        }
                    }
                    hashSet.add(thisIntervalTrade);
                }

                for (ArrayList<Data> h : hashSet) {
                    ArrayList<Data> arrayList1 = new ArrayList<>();
                    for (Data data : map.getValue()) {
                        if (data.getData_type().contains("login") && data.getResult() != 31 && timeDiff(h.get(0).getTime(), data.getTime(), "second") <= 75*60 && timeDiff(h.get(0).getTime(), data.getTime(), "second") > 0) {
                            arrayList1.add(data);
                        }
                    }
                    Collections.sort(arrayList1, new Comparator<Data>() {
                        @Override
                        public int compare(Data o1, Data o2) {
                            return o2.getTime().compareTo(o1.getTime());
                        }
                    });

                    if (arrayList1.size() != 0) {
                        String time1 = arrayList1.get(0).getTime();
                        for (Data data : arrayList1) {
                            if (time1.equals(data.getTime())) {
//                                hashMap1.put(h, data);
                                tradeAndLogins.add(new TradeAndLogin(h, data));
                            }
                        }
                    }
//                    if (arrayList1.size() != 0) {
//                        tradeAndLogins.add(new TradeAndLogin(h, arrayList1.get(0)));
//                    }
                }

                hashMapHashMap.put(user_id, tradeAndLogins);
            }

            File file2 = new File("/Users/admin/Documents/K/login/1206/1206_ml_instance_c.hashmap");
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashMapHashMap);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
