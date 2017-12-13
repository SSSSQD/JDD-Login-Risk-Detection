package com.jd.login.v6;

import java.io.Serializable;

public class Data implements Serializable {
    String time;
    String row_name;
    String data_type;
    int is_risk;
    int timelong;
    int device;
    int log_from;
    int ip;
    int city;
    int result;
    int type;
    int is_scan;

    public Data(String time,
                String row_name,
                String data_type,
                int is_risk,
                int timelong,
                int device,
                int log_from,
                int ip,
                int city,
                int result,
                int type,
                int is_scan) {
        this.time = time;
        this.row_name = row_name;
        this.data_type = data_type;
        this.is_risk = is_risk;
        this.timelong = timelong;
        this.device = device;
        this.log_from = log_from;
        this.ip = ip;
        this.city = city;
        this.result = result;
        this.type = type;
        this.is_scan = is_scan;
    }

    public String getTime() {
        return time;
    }

    public String getRow_name() {
        return row_name;
    }

    public String getData_type() {
        return data_type;
    }

    public int getIs_risk() {
        return is_risk;
    }

    public int getTimelong() {
        return timelong;
    }

    public int getDevice() {
        return device;
    }

    public int getLog_from() {
        return log_from;
    }

    public int getIp() {
        return ip;
    }

    public int getCity() {
        return city;
    }

    public int getResult() {
        return result;
    }

    public int getType() {
        return type;
    }

    public int getIs_scan() {
        return is_scan;
    }

    @Override
    public String toString() {
        if (getData_type().equals("login")) {
            return "time: " + getTime() + " row_name: " + getRow_name() + " timelong: " + getTimelong()
                    + " device: " + getDevice() + " log_from: " + getLog_from() + " ip: " + getIp()
                    + " city: " + getCity() + " result: " + getResult() + " type: " + getType()
                    + " is_scan: " + getIs_scan();
        } else if (getData_type().equals("trade_train")) {
            return "time: " + getTime() + " row_name: " + getRow_name() + " is_risk: " + getIs_risk();
        } else {
            return "time: " + getTime() + " row_name: " + getRow_name();
        }
    }
}
