package com.jd.login.v12.function;

import com.jd.login.v6.Data;

import java.util.ArrayList;

import static com.jd.login.Utils.timeDiff;

public class ResultFeature {
    public static int getResultTimeNum(Data thisLogin, ArrayList<Data> allLoginArrayList, int minutes, int result) {
        int c = 0;
        String time1 = timeDiff(thisLogin.getTime(), minutes);
        for (Data data : allLoginArrayList) {
            if (data.getTime().compareTo(time1) >= 0 && thisLogin.getTime().compareTo(data.getTime()) > 0) {
                if (result == -99) {
                    if (data.getResult() < 0) {
                        c++;
                    }
                } else if (result == 99) {
                    if (data.getResult() > 0 && data.getResult() != 1 && data.getResult() != 31) {
                        c++;
                    }
                } else if (result == 1) {
                    if (data.getResult() == 1) {
                        c++;
                    }
                } else if (result == 31) {
                    if (data.getResult() == 31) {
                        c++;
                    }
                }
            }
        }
        
        return c;
    }
}
