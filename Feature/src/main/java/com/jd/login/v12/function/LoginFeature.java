package com.jd.login.v12.function;

import com.jd.login.v6.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import static com.jd.login.Utils.timeDiff;

public class LoginFeature {
    public static ArrayList<Data> getRecentXLogin(ArrayList<Data> allLoginArrayList, Data thisLogin, int minutes) {
        String time2 = timeDiff(thisLogin.getTime(), minutes);
        ArrayList<Data> arrayList = new ArrayList<>();
        for (Data data : allLoginArrayList) {
            if (thisLogin.getTime().compareTo(data.getTime()) > 0 && data.getTime().compareTo(time2) >= 0) {
                arrayList.add(data);
            }
        }
        return arrayList;
    }

    public static int getThisLoginTimelong(Data thisLogin) {
        if (thisLogin.getTimelong() < 100) {
            return thisLogin.getTimelong() * 1000;
        } else {
            return thisLogin.getTimelong() > 0 ? thisLogin.getTimelong() : -99;
        }
    }

    public static int getThisLoginFrom(Data thisLogin, int x) {
        return thisLogin.getLog_from() == x ? 1 : 0;
    }

    public static int getThisLoginType(Data thisLogin, int x) {
        return thisLogin.getType() == x ? 1 : 0;
    }

    public static int getResultXFromYNum(ArrayList<Data> rencentXLogin, int result, int from) {
        if (rencentXLogin.size() == 0) {
            return -1;
        } else {
            int c = 0;
            if (result != -99) {
                for (Data data : rencentXLogin) {
                    if (data.getResult() != 1 && data.getResult() != 31) {
                        if (data.getLog_from() == from) {
                            c++;
                        }
                    }
                }
                return c;
            } else {
                for (Data data : rencentXLogin) {
                    if (data.getResult() == result) {
                        if (data.getLog_from() == from) {
                            c++;
                        }
                    }
                }
                return c;
            }
        }
    }

    public static int getThisLoginResultSuccessThenPasswdError(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes) {
        ArrayList<Data> arrayList = new ArrayList<>();
        String time2 = timeDiff(thisLogin.getTime(), minutes);
        for (Data data : allLoginArrayList) {
            if (data.getTime().compareTo(time2) >= 0 && thisLogin.getTime().compareTo(data.getTime()) > 0) {
                arrayList.add(data);
            }
        }
        Collections.sort(arrayList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        int var1 = 0;
        for (Data data : arrayList) {
            if (data.getResult() == 1) {
                var1 = 1;
            }
            if (var1 == 1 && data.getResult() != 1) {
                var1 = 2;
                break;
            }
        }
        return var1 == 2 ? 1 : 0;
    }

    public static int getThisLoginResultSuccessThenPasswdErrorDevice(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes) {
        ArrayList<Data> arrayList = new ArrayList<>();
        String time2 = timeDiff(thisLogin.getTime(), minutes);
        for (Data data : allLoginArrayList) {
            if (data.getTime().compareTo(time2) >= 0 && thisLogin.getTime().compareTo(data.getTime()) > 0) {
                arrayList.add(data);
            }
        }
        Collections.sort(arrayList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> hashSet1 = new HashSet<>();
        int var1 = 0;
        for (Data data : arrayList) {
            if (data.getResult() == 1) {
                var1 = 1;
                hashSet.add(data.getDevice());
            }
            if (var1 == 1 && data.getResult() != 1) {
                hashSet1.add(data.getDevice());
            }
        }
        int var2 = 0;
        for (Integer h : hashSet1) {
            if (hashSet.contains(h)) {
                var2 = 1;
                break;
            }
        }
        return var2;
    }
}
