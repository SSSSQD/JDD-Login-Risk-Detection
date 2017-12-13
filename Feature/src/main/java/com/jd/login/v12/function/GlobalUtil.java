package com.jd.login.v12.function;

import com.jd.login.v6.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static com.jd.login.Utils.timeDiff;

public class GlobalUtil {
    public static int getDeviceIsTradeDeviceProb(Data thisLogin, ArrayList<Data> allLoginArrayList) {
        String time1 = timeDiff(thisLogin.getTime(), 90);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Data data : allLoginArrayList) {
            if (data.getResult() != 31 && data.getTime().compareTo(time1) >= 0 && thisLogin.getTime().compareTo(data.getTime()) >= 0) {
                if (hashMap.containsKey(data.getDevice())) {
                    hashMap.put(data.getDevice(), hashMap.get(data.getDevice())+1);
                } else {
                    hashMap.put(data.getDevice(), 1);
                }
            }
        }
//        System.out.println(thisLogin.toString());
        int var1 = hashMap.get(thisLogin.getDevice());
        double var2 = Math.log(Math.exp(1)-1 + hashMap.size());
        return (int) (100/var2 - var1);
    }

    public static int getUserGlobalOnlyThisIntervalTrade(ArrayList<Data> allActionArrayList) {
        Collections.sort(allActionArrayList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        Data[] arr = allActionArrayList.toArray(new Data[allActionArrayList.size()]);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].getData_type().contains("trade")) {
                arrayList.add(i);
            }
        }
        if (arrayList.size() >= 2) {
            Integer[] arr1 = arrayList.toArray(new Integer[arrayList.size()]);
            for (int i = 1; i < arr1.length; ++i) {
                if (arr1[i] - arr1[i-1] > 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
        return 1;
    }

    public static int getUserGlobalActionWithin24h(ArrayList<Data> allActionArrayList) {
        Collections.sort(allActionArrayList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        String time1 = allActionArrayList.get(0).getTime();
        String time2 = timeDiff(time1, -60*24);
        for (Data data : allActionArrayList) {
            if (data.getTime().compareTo(time2) > 0) {
                return 0;
            }
        }
        return 1;
    }
}
