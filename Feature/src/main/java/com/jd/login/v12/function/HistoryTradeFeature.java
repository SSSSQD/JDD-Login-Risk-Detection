package com.jd.login.v12.function;

import com.jd.login.v6.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.jd.login.v12.function.TradeFeature.getTradeLoginTimeDiff;

public class HistoryTradeFeature {
    public static int getHistoryTradeLoginTimeDiffAvg(LinkedHashMap<ArrayList<Data>, Data> historyIntervalTrade) {
        int c = 0;
        int n = 0;
        if (historyIntervalTrade.size() != 0) {
            for (Map.Entry<ArrayList<Data>, Data> map : historyIntervalTrade.entrySet()) {
                n++;
                c += getTradeLoginTimeDiff(map.getKey(), map.getValue());
            }
            return c / n;
        } else {
            return -1;
        }
        
    }
}
