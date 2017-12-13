package com.jd.login.v12;

import com.jd.login.v6.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class TradeAndLogin implements Serializable {
    Data thisLogin;
    ArrayList<Data> thisIntervalLogin;

    public TradeAndLogin(ArrayList<Data> thisIntervalLogin, Data thisLogin) {
        this.thisLogin = thisLogin;
        this.thisIntervalLogin = thisIntervalLogin;
    }

    public Data getThisLogin() {
        return thisLogin;
    }

    public Data getValue() {
        return thisLogin;
    }

    public ArrayList<Data> getThisIntervalLogin() {
        return thisIntervalLogin;
    }

    public ArrayList<Data> getKey() {
        return thisIntervalLogin;
    }
<<<<<<< HEAD
    
=======
>>>>>>> d405891e2a1c8b7f1bda13012ca2b06ce2e520de
}
