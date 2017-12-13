package com.jd.login.v12.function;

import com.jd.login.v12.TradeAndLogin;
import com.jd.login.v6.Data;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.jd.login.Utils.timeDiff;

public class TradeFeature {
    public static int getTradeLoginTimeDiff(ArrayList<Data> thisIntervalTrade, Data thisLogin) {
        return (int) timeDiff(thisIntervalTrade.get(0).getTime(), thisLogin.getTime(), "minute");
    }

    public static int getRangeIntervalTradeNum(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        return tradeAndLoginArrayList.size();
    }

    public static int getRangeIntervalTradeLoginTimeDiffAvg(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        double c = 0;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            c += getTradeLoginTimeDiff(map.getThisIntervalLogin(), map.getThisLogin());
        }
        if (c != 0) {
            return (int) c / tradeAndLoginArrayList.size();
        } else {
            return 0;
        }
    }

    public static int getRangeIntervalTradeLoginTimeDiffMax(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        int c = Integer.MIN_VALUE;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            int c1 = getTradeLoginTimeDiff(map.getThisIntervalLogin(), map.getThisLogin());
            if (c1 >= c) {
                c = c1;
            }
        }
        if (c != Integer.MIN_VALUE) {
            return c;
        } else {
            return -1;
        }
    }

    public static int getRangeIntervalTradeLoginTimeDiffMin(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        int c = Integer.MAX_VALUE;
        for (TradeAndLogin map : tradeAndLoginArrayList) {
            int c1 = getTradeLoginTimeDiff(map.getKey(), map.getValue());
            if (c1 <= c) {
                c = c1;
            }
        }
        if (c != Integer.MAX_VALUE) {
            return c;
        } else {
            return -1;
        }
    }

    public static int getRangeIntervalTradeDayDiffAvg(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        if (tradeAndLoginArrayList.size() < 2) {
            return -1;
        } else {
            ArrayList<Data> arrayList = new ArrayList<>();
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                arrayList.add(map.getValue());
            }
            Data[] arr = arrayList.toArray(new Data[arrayList.size()]);
            double c = 0;
            for (int i = 1; i < arr.length; ++i) {
                c += timeDiff(arr[i].getTime(), arr[i-1].getTime(), "day");
            }
            return (int) c / arr.length-1;
        }
    }

    public static int getRangeIntervalTradeDayDiffMax(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        if (tradeAndLoginArrayList.size() < 2) {
            return -1;
        } else {
            ArrayList<Data> arrayList = new ArrayList<>();
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                arrayList.add(map.getValue());
            }
            Data[] arr = arrayList.toArray(new Data[arrayList.size()]);
            double c = Integer.MIN_VALUE;
            for (int i = 1; i < arr.length; ++i) {
                double c1 = timeDiff(arr[i].getTime(), arr[i-1].getTime(), "day");
                if (c1 >= c) {
                    c = c1;
                }
            }
            return (int) c;
        }
    }

    public static int getRangeIntervalTradeDayDiffMin(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        if (tradeAndLoginArrayList.size() < 2) {
            return -1;
        } else {
            ArrayList<Data> arrayList = new ArrayList<>();
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                arrayList.add(map.getValue());
            }
            Data[] arr = arrayList.toArray(new Data[arrayList.size()]);
            double c = Integer.MAX_VALUE;
            for (int i = 1; i < arr.length; ++i) {
                double c1 = timeDiff(arr[i].getTime(), arr[i-1].getTime(), "day");
                if (c1 <= c) {
                    c = c1;
                }
            }
            return (int) c;
        }
    }

    public static int getTradeTimeRangeNum(ArrayList<Data> thisIntervalTrade, int start, int end) {
        int c = 0;
        for (Data tradeDataV5 : thisIntervalTrade) {
            String hourStr = tradeDataV5.getTime().split(" ")[1].split(":")[0];
            int hour;
            if (hourStr.startsWith("0")) {
                hour = Integer.valueOf(String.valueOf(hourStr.toCharArray()[1]));
            } else {
                hour = Integer.valueOf(hourStr);
            }
            if (hour <= end && hour >= start) {
                c++;
            }
        }
        return c > 0 ? 1 : 0;
    }

    public static int getRangeIntervalTradeTimeRangeNum(ArrayList<TradeAndLogin> tradeAndLoginArrayList, int start, int end) {
        if (tradeAndLoginArrayList.size() == 0) {
            return -1;
        } else {
            int c = 0;
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                c += getTradeTimeRangeNum(map.getKey(), start, end);
            }
            return c;
        }
    }

    public static int getTradeDayOfWeek(ArrayList<Data> thisIntervalTrade, int x) {
        try {
            for (Data tradeDataV5 : thisIntervalTrade) {
                String time = tradeDataV5.getTime().split(" ")[0];
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(time);
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(date);
                return cal.get(Calendar.DAY_OF_WEEK)-1 == x ? 1 : 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getRangeIntervalTradeDayOfWeek(ArrayList<TradeAndLogin> tradeAndLoginArrayList, int x) {
        if (tradeAndLoginArrayList.size() == 0) {
            return -1;
        } else {
            int c = 0;
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                c += getTradeDayOfWeek(map.getKey(), x);
            }
            return c;
        }
    }

    public static int getTradeIsFestival(ArrayList<Data> thisIntervalTrade) {
        String[] feastivalArr = new String[] {
                "2015-01-01","2015-01-02","2015-01-03","2015-01-10","2015-01-11","2015-01-17","2015-01-18","2015-01-24",
                "2015-01-25","2015-01-31","2015-02-01","2015-02-07","2015-02-08","2015-02-14","2015-02-18","2015-02-19",
                "2015-02-20","2015-02-21","2015-02-22","2015-02-23","2015-02-24","2015-03-01","2015-03-07","2015-03-08",
                "2015-03-14","2015-03-15","2015-03-21","2015-03-22","2015-03-28","2015-03-29","2015-04-04","2015-04-05",
                "2015-04-06","2015-04-11","2015-04-12","2015-04-18","2015-04-19","2015-04-25","2015-04-26","2015-05-01",
                "2015-05-02","2015-05-03","2015-05-09","2015-05-10","2015-05-16","2015-05-17","2015-05-23","2015-05-24",
                "2015-05-30","2015-05-31","2015-06-06","2015-06-07","2015-06-13","2015-06-14","2015-06-18","2015-06-19",
                "2015-06-20","2015-06-21","2015-06-22","2015-06-27","2015-06-28","2015-07-04","2015-07-05","2015-07-11",
                "2015-07-12","2015-07-18","2015-07-19","2015-07-25","2015-07-26"
        };
        HashSet<String> hashSet = new HashSet<>();
        for (String ss : feastivalArr) {
            hashSet.add(ss);
        }
        for (Data data : thisIntervalTrade) {
            String day = data.getTime().split(" ")[0];
            if (hashSet.contains(day)) {
                return 1;
            }
        }
        return 0;
    }

    public static int getRangeIntervalTradeIsFestival(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        if (tradeAndLoginArrayList.size() == 0) {
            return -1;
        } else {
            int c = 0;
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                c += getTradeIsFestival(map.getKey());
            }
            return c;
        }
    }

    public static int getTradeIsFestivalBefore1Day(ArrayList<Data> thisIntervalTrade) {
        String[] feastivalArr = new String[] {
                "2015-01-09","2015-01-16","2015-01-23","2015-01-30","2015-02-06","2015-02-13","2015-02-17","2015-02-27",
                "2015-03-06","2015-03-13","2015-03-20","2015-03-27","2015-04-03","2015-04-10","2015-04-17","2015-04-24",
                "2015-04-30","2015-05-08","2015-05-15","2015-05-22","2015-05-29","2015-06-05","2015-06-12","2015-06-19",
                "2015-06-26","2015-07-03","2015-07-10","2015-07-17","2015-07-24"
        };
        HashSet<String> hashSet = new HashSet<>();
        for (String ss : feastivalArr) {
            hashSet.add(ss);
        }
        for (Data data : thisIntervalTrade) {
            String day = data.getTime().split(" ")[0];
            if (hashSet.contains(day)) {
                return 1;
            }
        }
        return 0;
    }

    public static int getRangeIntervalTradeIsFestivalBefore1Day(ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        if (tradeAndLoginArrayList.size() == 0) {
            return -1;
        } else {
            int c = 0;
            for (TradeAndLogin map : tradeAndLoginArrayList) {
                c += getTradeIsFestivalBefore1Day(map.getKey());
            }
            return c;
        }
    }

    public static int getTradeIsYearFirstTrade(ArrayList<Data> thisIntervalTrade, ArrayList<Data> allTradeArrayList) {
        String firstTradeTime = thisIntervalTrade.get(0).getTime();
        int c = 1;
        for (Data data : allTradeArrayList) {
            if (firstTradeTime.compareTo(data.getTime()) > 0) {
                c = 0;
                break;
            }
        }
        return c;
    }

    public static int getOrderOfThisYearTrade(ArrayList<Data> thisIntervalTrade, ArrayList<TradeAndLogin> tradeAndLoginArrayList) {
        String time1 = thisIntervalTrade.get(0).getTime();
        int k = 0;
        for (TradeAndLogin tradeAndLogin : tradeAndLoginArrayList) {
            if (time1.compareTo(tradeAndLogin.getThisIntervalLogin().get(0).getTime()) > 0) {
                k++;
            }
        }
        return k+1;
    }
}
