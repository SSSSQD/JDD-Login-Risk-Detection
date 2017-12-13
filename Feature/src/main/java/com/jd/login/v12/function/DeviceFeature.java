package com.jd.login.v12.function;

import com.jd.login.v6.Data;

import java.util.ArrayList;
import java.util.HashSet;

import static com.jd.login.Utils.timeDiff;

public class DeviceFeature {
    public static int getDeviceIsNew(Data thisLogin, ArrayList<Data> allLoginArrayList, boolean is_result, int result) {
        String thisLoginTime = thisLogin.getTime();
        for (Data data : allLoginArrayList) {
            if (is_result) {
                if (thisLoginTime.compareTo(data.getTime()) > 0 && data.getResult() == result && thisLogin.getDevice() == data.getDevice()) {
                    return 0;
                }
            } else {
                if (thisLoginTime.compareTo(data.getTime()) > 0 && thisLogin.getDevice() == data.getDevice()) {
                    return 0;
                }
            }
        }
        
        return 1;
    }

    public static int getWithinTimeNewDeviceNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int withinMin, int outHistotyMin, int withinResult) {
        String time1 = timeDiff(thisLogin.getTime(), outHistotyMin);
        String time2 = timeDiff(thisLogin.getTime(), withinMin);
        int c = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> hashSet1 = new HashSet<>();
        for (Data data : allLoginArrayList) {
            if (time1.compareTo(data.getTime()) >= 0 && data.getResult() == 1) {
                hashSet.add(data.getDevice());
            }
            if (thisLogin.getTime().compareTo(data.getTime()) > 0 && data.getTime().compareTo(time2) >= 0 && data.getResult() == withinResult) {
                hashSet1.add(data.getDevice());
            }
        }
        for (Integer h : hashSet1) {
            if (hashSet.contains(h)) {
                c++;
            }
        }
        return hashSet1.size() - c;
    }

    public static int getDeviceTimeResultLoginNum(Data thisLogin, ArrayList<Data> allLoginArrayList, boolean is_result,
                                                  int result_pos_neg, int result, int downTime, int upperTime) {
        String upperTime2 = timeDiff(thisLogin.getTime(), upperTime);
        String downTime2 = timeDiff(thisLogin.getTime(), downTime);
        int c = 0;
        for (Data data : allLoginArrayList) {
            if (result_pos_neg == 0) {
                if (!is_result) {
                    if (downTime2.compareTo(data.getTime()) > 0 && data.getTime().compareTo(upperTime2) >= 0) {
                        if (thisLogin.getDevice() == data.getDevice()) {
                            c++;
                        }
                    }
                } else {
                    if (downTime2.compareTo(data.getTime()) > 0 && data.getTime().compareTo(upperTime2) >= 0) {
                        if (thisLogin.getDevice() == data.getDevice()) {
                            if (data.getResult() == result) {
                                c++;
                            }
                        }
                    }
                }
            } else if (result_pos_neg == 1) {
                if (downTime2.compareTo(data.getTime()) > 0 && data.getTime().compareTo(upperTime2) >= 0) {
                    if (thisLogin.getDevice() == data.getDevice()) {
                        if (data.getResult() > 0 && data.getResult() != 1 && data.getResult() != 31) {
                            c++;
                        }
                    }
                }
            } else {
                if (downTime2.compareTo(data.getTime()) > 0 && data.getTime().compareTo(upperTime2) >= 0) {
                    if (thisLogin.getDevice() == data.getDevice()) {
                        if (data.getResult() < 0) {
                            c++;
                        }
                    }
                }
            }
        }
        return c;
    }
}
