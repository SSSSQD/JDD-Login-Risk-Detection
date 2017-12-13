package com.jd.login.v12.function;

import com.jd.login.v6.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static com.jd.login.Utils.timeDiff;

public class DeviceUserFeature {
    public static int getDeviceTimeUserNum(HashMap<Integer, HashMap<Integer, ArrayList<Data>>> hashMapHashMap, Data thisLogin, int user_id, int downMin, int upperMin) {
        String thisLoginTime = thisLogin.getTime();
        int thisLoginDevice = thisLogin.getDevice();
        String upperTime = timeDiff(thisLoginTime, upperMin);
        String downTime = timeDiff(thisLoginTime, downMin);
        HashMap<Integer, ArrayList<Data>> hashMap = hashMapHashMap.get(thisLoginDevice);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Map.Entry<Integer, ArrayList<Data>> map : hashMap.entrySet()) {
            if (user_id != map.getKey()) {
                for (Data data : map.getValue()) {
                    if (data.getDevice() == thisLoginDevice && downTime.compareTo(data.getTime()) >= 0 && data.getTime().compareTo(upperTime) >= 0) {
                        hashSet.add(map.getKey());
                    }
                }
            }
        }
        
        return hashSet.size();
    }

    public static int getDeviceTimeResultUserNum(HashMap<Integer, HashMap<Integer, ArrayList<Data>>> hashMapHashMap, Data thisLogin, int user_id, int result, int downMin, int upperMin) {
        String thisLoginTime = thisLogin.getTime();
        int thisLoginDevice = thisLogin.getDevice();
        String upperTime = timeDiff(thisLoginTime, upperMin);
        String downTime = timeDiff(thisLoginTime, downMin);
        HashMap<Integer, ArrayList<Data>> hashMap = hashMapHashMap.get(thisLoginDevice);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Map.Entry<Integer, ArrayList<Data>> map : hashMap.entrySet()) {
            if (user_id != map.getKey()) {
                for (Data data : map.getValue()) {
                    if (data.getDevice() == thisLoginDevice && downTime.compareTo(data.getTime()) >= 0 && data.getTime().compareTo(upperTime) >= 0) {
                        if (result == 1) {
                            if (data.getResult() == 1) {
                                hashSet.add(map.getKey());
                            }
                        } else if (result == 31) {
                            if (data.getResult() == 31) {
                                hashSet.add(map.getKey());
                            }
                        } else if (result == -99) {
                            if (data.getResult() < 0) {
                                hashSet.add(map.getKey());
                            }
                        } else {
                            if (data.getResult() > 0 && data.getResult() != 31 && data.getResult() != 1) {
                                hashSet.add(map.getKey());
                            }
                        }
                    }
                }
            }
        }
        return hashSet.size();
    }

    public static int getThisLoginTimeDeviceNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes, boolean is_result, int result) {
        String time2 = timeDiff(thisLogin.getTime(), minutes);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Data data : allLoginArrayList) {
            if (thisLogin.getTime().compareTo(data.getTime()) > 0 && data.getTime().compareTo(time2) >= 0) {
                if (!is_result) {
                    hashSet.add(data.getDevice());
                } else {
                    if (result == 1) {
                        if (data.getResult() == 1) {
                            hashSet.add(data.getDevice());
                        }
                    } else if (result == 31) {
                        if (data.getResult() == 31) {
                            hashSet.add(data.getDevice());
                        }
                    } else if (result == -99) {
                        if (data.getResult() < 0) {
                            hashSet.add(data.getDevice());
                        }
                    } else {
                        if (data.getResult() > 0 && data.getResult() != 31 && data.getResult() != 1) {
                            hashSet.add(data.getDevice());
                        }
                    }
                }
            }
        }
        return hashSet.size();
    }
}
