package com.jd.login.v12.function;

import com.jd.login.v6.Data;

import java.util.ArrayList;
import java.util.HashSet;

import static com.jd.login.Utils.timeDiff;

public class CityIPFeature {
    public static int getThisLoginTimeCityNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes, boolean is_result, int result) {
        String time2 = timeDiff(thisLogin.getTime(), minutes);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Data data : allLoginArrayList) {
            if (thisLogin.getTime().compareTo(data.getTime()) > 0 && data.getTime().compareTo(time2) >= 0) {
                if (!is_result) {
                    hashSet.add(data.getCity());
                } else {
                    if (result == 1) {
                        if (data.getResult() == 1) {
                            hashSet.add(data.getCity());
                        }
                    } else if (result == 31) {
                        if (data.getResult() == 31) {
                            hashSet.add(data.getCity());
                        }
                    } else if (result == -99) {
                        if (data.getResult() < 0) {
                            hashSet.add(data.getCity());
                        }
                    } else {
                        if (data.getResult() > 0 && data.getResult() != 31 && data.getResult() != 1) {
                            hashSet.add(data.getCity());
                        }
                    }
                }
            }
        }
        
        return hashSet.size();
    }

    public static int getThisLoginTimeIPNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes, boolean is_result, int result) {
        String time2 = timeDiff(thisLogin.getTime(), minutes);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Data data : allLoginArrayList) {
            if (thisLogin.getTime().compareTo(data.getTime()) > 0 && data.getTime().compareTo(time2) >= 0) {
                if (!is_result) {
                    hashSet.add(data.getIp());
                } else {
                    if (result == 1) {
                        if (data.getResult() == 1) {
                            hashSet.add(data.getIp());
                        }
                    } else if (result == 31) {
                        if (data.getResult() == 31) {
                            hashSet.add(data.getIp());
                        }
                    } else if (result == -99) {
                        if (data.getResult() < 0) {
                            hashSet.add(data.getIp());
                        }
                    } else {
                        if (data.getResult() > 0 && data.getResult() != 31 && data.getResult() != 1) {
                            hashSet.add(data.getIp());
                        }
                    }
                }
            }
        }
        return hashSet.size();
    }

    public static int getThisIsNewCity(Data thisLogin, ArrayList<Data> allLoginArrayList) {
        String thisLoginTime = thisLogin.getTime();
        for (Data data : allLoginArrayList) {
            if (thisLoginTime.compareTo(data.getTime()) > 0 && data.getResult() == 1 && thisLogin.getCity() == data.getCity()) {
                return 0;
            }
        }
        return 1;
    }

    public static int getThisIsNewIP(Data thisLogin, ArrayList<Data> allLoginArrayList) {
        String thisLoginTime = thisLogin.getTime();
        for (Data data : allLoginArrayList) {
            if (thisLoginTime.compareTo(data.getTime()) > 0 && data.getResult() == 1 && thisLogin.getIp() == data.getIp()) {
                return 0;
            }
        }
        return 1;
    }

    public static int getCitySuccessLoginNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int downMin, int upperMin) {
        String upperTime = timeDiff(thisLogin.getTime(), upperMin);
        String downTime = timeDiff(thisLogin.getTime(), downMin);
        int c = 0;
        for (Data data : allLoginArrayList) {
            if (data.getResult() == 1) {
                if (downTime.compareTo(data.getTime()) > 0 && data.getTime().compareTo(upperTime) >= 0 && data.getCity() == thisLogin.getCity()) {
                    c++;
                }
            }
        }
        return c;
    }

    public static int getIPSuccessLoginNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int downMin, int upperMin) {
        String upperTime = timeDiff(thisLogin.getTime(), upperMin);
        String downTime = timeDiff(thisLogin.getTime(), downMin);
        int c = 0;
        for (Data data : allLoginArrayList) {
            if (data.getResult() == 1) {
                if (downTime.compareTo(data.getTime()) > 0 && data.getTime().compareTo(upperTime) >= 0 && data.getIp() == thisLogin.getIp()) {
                    c++;
                }
            }
        }
        return c;
    }

    public static int getDeviceConnectIPTimeNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes) {
        String time1 = timeDiff(thisLogin.getTime(), minutes);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Data data : allLoginArrayList) {
            if (thisLogin.getDevice() == data.getDevice()) {
                if (thisLogin.getTime().compareTo(data.getTime()) > 0 && data.getTime().compareTo(time1) >= 0) {
                    hashSet.add(data.getIp());
                }
            }
        }
        return hashSet.size();
    }

    public static int getDeviceConnectCityTimeNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes) {
        String time1 = timeDiff(thisLogin.getTime(), minutes);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Data data : allLoginArrayList) {
            if (thisLogin.getDevice() == data.getDevice()) {
                if (thisLogin.getTime().compareTo(data.getTime()) > 0 && data.getTime().compareTo(time1) >= 0) {
                    hashSet.add(data.getCity());
                }
            }
        }
        return hashSet.size();
    }
}
