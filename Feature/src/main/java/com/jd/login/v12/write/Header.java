package com.jd.login.v12.write;

public class Header {
    public static String getHeader() {
        StringBuffer stringBuffer1 = new StringBuffer();
        stringBuffer1.append("row_name,id,time,pesudo_label1,pesudo_label2,actual_label,");
        stringBuffer1.append("本次登录与本段交易时间间隔,");
        stringBuffer1.append("本段交易时间0-3点,");
        stringBuffer1.append("本段交易时间4-10点,");
        stringBuffer1.append("本段交易时间11-12点,");
        stringBuffer1.append("本段交易时间13-17点,");
        stringBuffer1.append("本段交易时间18-20点,");
        stringBuffer1.append("本段交易时间21-23点,");
        stringBuffer1.append("本段交易为周一,");
        stringBuffer1.append("本段交易为周二,");
        stringBuffer1.append("本段交易为周三,");
        stringBuffer1.append("本段交易为周四,");
        stringBuffer1.append("本段交易为周五,");
        stringBuffer1.append("本段交易为周六,");
        stringBuffer1.append("本段交易为周日,");
        stringBuffer1.append("本段交易是否为节假日,");
        stringBuffer1.append("本段交易是否为节假日前一天,");
        stringBuffer1.append("3天内交易段数,");
        stringBuffer1.append("3天登录与交易的平均时间间隔,");
        stringBuffer1.append("3天内交易平均间隔天数,");
        stringBuffer1.append("3天登录与交易的最大时间间隔,");
        stringBuffer1.append("3天内交易最大间隔天数,");
        stringBuffer1.append("3天登录与交易的最小时间间隔,");
        stringBuffer1.append("3天内交易最小间隔天数,");
        stringBuffer1.append("3天内交易时间0-3点次数,");
        stringBuffer1.append("3天内交易时间4-10点次数,");
        stringBuffer1.append("3天内交易时间11-12点次数,");
        stringBuffer1.append("3天内交易时间13-17点次数,");
        stringBuffer1.append("3天内交易时间18-20点次数,");
        stringBuffer1.append("3天内交易时间21-23点次数,");
        stringBuffer1.append("3天内交易时间0-3点占比,");
        stringBuffer1.append("3天内交易时间4-10点占比,");
        stringBuffer1.append("3天内交易时间11-12点占比,");
        stringBuffer1.append("3天内交易时间13-17点占比,");
        stringBuffer1.append("3天内交易时间18-20点占比,");
        stringBuffer1.append("3天内交易时间21-23点占比,");
        stringBuffer1.append("3天内交易为周一,");
        stringBuffer1.append("3天内交易为周二,");
        stringBuffer1.append("3天内交易为周三,");
        stringBuffer1.append("3天内交易为周四,");
        stringBuffer1.append("3天内交易为周五,");
        stringBuffer1.append("3天内交易为周六,");
        stringBuffer1.append("3天内交易为周日,");
        stringBuffer1.append("3天内交易是否为节假日,");
        stringBuffer1.append("3天内交易是否为节假日前一天,");
        stringBuffer1.append("3_30天内交易段数,");
        stringBuffer1.append("3_30天登录与交易的平均时间间隔,");
        stringBuffer1.append("3_30天内交易平均间隔天数,");
        stringBuffer1.append("3_30天登录与交易的最大时间间隔,");
        stringBuffer1.append("3_30天内交易最大间隔天数,");
        stringBuffer1.append("3_30天登录与交易的最小时间间隔,");
        stringBuffer1.append("3_30天内交易最小间隔天数,");
        stringBuffer1.append("3_30天内交易时间0-3点次数,");
        stringBuffer1.append("3_30天内交易时间4-10点次数,");
        stringBuffer1.append("3_30天内交易时间11-12点次数,");
        stringBuffer1.append("3_30天内交易时间13-17点次数,");
        stringBuffer1.append("3_30天内交易时间18-20点次数,");
        stringBuffer1.append("3_30天内交易时间21-23点次数,");
        stringBuffer1.append("3_30天内交易时间0-3点占比,");
        stringBuffer1.append("3_30天内交易时间4-10点占比,");
        stringBuffer1.append("3_30天内交易时间11-12点占比,");
        stringBuffer1.append("3_30天内交易时间13-17点占比,");
        stringBuffer1.append("3_30天内交易时间18-20点占比,");
        stringBuffer1.append("3_30天内交易时间21-23点占比,");
        stringBuffer1.append("3_30天内交易为周一,");
        stringBuffer1.append("3_30天内交易为周二,");
        stringBuffer1.append("3_30天内交易为周三,");
        stringBuffer1.append("3_30天内交易为周四,");
        stringBuffer1.append("3_30天内交易为周五,");
        stringBuffer1.append("3_30天内交易为周六,");
        stringBuffer1.append("3_30天内交易为周日,");
        stringBuffer1.append("3_30天内交易是否为节假日,");
        stringBuffer1.append("3_30天内交易是否为节假日前一天,");
        stringBuffer1.append("30_60天内交易段数,");
        stringBuffer1.append("30_60天登录与交易的平均时间间隔,");
        stringBuffer1.append("30_60天内交易平均间隔天数,");
        stringBuffer1.append("30_60天登录与交易的最大时间间隔,");
        stringBuffer1.append("30_60天内交易最大间隔天数,");
        stringBuffer1.append("30_60天登录与交易的最小时间间隔,");
        stringBuffer1.append("30_60天内交易最小间隔天数,");
        stringBuffer1.append("30_60天内交易时间0-3点次数,");
        stringBuffer1.append("30_60天内交易时间4-10点次数,");
        stringBuffer1.append("30_60天内交易时间11-12点次数,");
        stringBuffer1.append("30_60天内交易时间13-17点次数,");
        stringBuffer1.append("30_60天内交易时间18-20点次数,");
        stringBuffer1.append("30_60天内交易时间21-23点次数,");
        stringBuffer1.append("30_60天内交易时间0-3点占比,");
        stringBuffer1.append("30_60天内交易时间4-10点占比,");
        stringBuffer1.append("30_60天内交易时间11-12点占比,");
        stringBuffer1.append("30_60天内交易时间13-17点占比,");
        stringBuffer1.append("30_60天内交易时间18-20点占比,");
        stringBuffer1.append("30_60天内交易时间21-23点占比,");
        stringBuffer1.append("30_60天内交易为周一,");
        stringBuffer1.append("30_60天内交易为周二,");
        stringBuffer1.append("30_60天内交易为周三,");
        stringBuffer1.append("30_60天内交易为周四,");
        stringBuffer1.append("30_60天内交易为周五,");
        stringBuffer1.append("30_60天内交易为周六,");
        stringBuffer1.append("30_60天内交易为周日,");
        stringBuffer1.append("30_60天内交易是否为节假日,");
        stringBuffer1.append("30_60天内交易是否为节假日前一天,");
        stringBuffer1.append("历史内交易段数,");
        stringBuffer1.append("历史登录与交易的平均时间间隔,");
        stringBuffer1.append("历史内交易平均间隔天数,");
        stringBuffer1.append("历史登录与交易的最大时间间隔,");
        stringBuffer1.append("历史内交易最大间隔天数,");
        stringBuffer1.append("历史登录与交易的最小时间间隔,");
        stringBuffer1.append("历史内交易最小间隔天数,");
        stringBuffer1.append("历史内交易时间0-3点次数,");
        stringBuffer1.append("历史内交易时间4-10点次数,");
        stringBuffer1.append("历史内交易时间11-12点次数,");
        stringBuffer1.append("历史内交易时间13-17点次数,");
        stringBuffer1.append("历史内交易时间18-20点次数,");
        stringBuffer1.append("历史内交易时间21-23点次数,");
        stringBuffer1.append("历史内交易时间0-3点占比,");
        stringBuffer1.append("历史内交易时间4-10点占比,");
        stringBuffer1.append("历史内交易时间11-12点占比,");
        stringBuffer1.append("历史内交易时间13-17点占比,");
        stringBuffer1.append("历史内交易时间18-20点占比,");
        stringBuffer1.append("历史内交易时间21-23点占比,");
        stringBuffer1.append("历史内交易为周一,");
        stringBuffer1.append("历史内交易为周二,");
        stringBuffer1.append("历史内交易为周三,");
        stringBuffer1.append("历史内交易为周四,");
        stringBuffer1.append("历史内交易为周五,");
        stringBuffer1.append("历史内交易为周六,");
        stringBuffer1.append("历史内交易为周日,");
        stringBuffer1.append("历史内交易是否为节假日,");
        stringBuffer1.append("历史内交易是否为节假日前一天,");
        stringBuffer1.append("是否今年初次交易,");
        stringBuffer1.append("上段交易登录与交易的时间间隔,");
        stringBuffer1.append("上段交易内交易时间0-3点,");
        stringBuffer1.append("上段交易内交易时间4-10点,");
        stringBuffer1.append("上段交易内交易时间11-12点,");
        stringBuffer1.append("上段交易内交易时间13-17点,");
        stringBuffer1.append("上段交易内交易时间18-20点,");
        stringBuffer1.append("上段交易内交易时间21-23点,");
        stringBuffer1.append("上段交易内交易为周一,");
        stringBuffer1.append("上段交易内交易为周二,");
        stringBuffer1.append("上段交易内交易为周三,");
        stringBuffer1.append("上段交易内交易为周四,");
        stringBuffer1.append("上段交易内交易为周五,");
        stringBuffer1.append("上段交易内交易为周六,");
        stringBuffer1.append("上段交易内交易为周日,");
        stringBuffer1.append("上段交易内交易是否为节假日,");
        stringBuffer1.append("上段交易内交易是否为节假日前一天,");
        stringBuffer1.append("上上段交易登录与交易的时间间隔,");
        stringBuffer1.append("上上段交易内交易时间0-3点,");
        stringBuffer1.append("上上段交易内交易时间4-10点,");
        stringBuffer1.append("上上段交易内交易时间11-12点,");
        stringBuffer1.append("上上段交易内交易时间13-17点,");
        stringBuffer1.append("上上段交易内交易时间18-20点,");
        stringBuffer1.append("上上段交易内交易时间21-23点,");
        stringBuffer1.append("上上段交易内交易为周一,");
        stringBuffer1.append("上上段交易内交易为周二,");
        stringBuffer1.append("上上段交易内交易为周三,");
        stringBuffer1.append("上上段交易内交易为周四,");
        stringBuffer1.append("上上段交易内交易为周五,");
        stringBuffer1.append("上上段交易内交易为周六,");
        stringBuffer1.append("上上段交易内交易为周日,");
        stringBuffer1.append("上上段交易内交易是否为节假日,");
        stringBuffer1.append("上上段交易内交易是否为节假日前一天,");
        stringBuffer1.append("上上上段交易登录与交易的时间间隔,");
        stringBuffer1.append("上上上段交易内交易时间0-3点,");
        stringBuffer1.append("上上上段交易内交易时间4-10点,");
        stringBuffer1.append("上上上段交易内交易时间11-12点,");
        stringBuffer1.append("上上上段交易内交易时间13-17点,");
        stringBuffer1.append("上上上段交易内交易时间18-20点,");
        stringBuffer1.append("上上上段交易内交易时间21-23点,");
        stringBuffer1.append("上上上段交易内交易为周一,");
        stringBuffer1.append("上上上段交易内交易为周二,");
        stringBuffer1.append("上上上段交易内交易为周三,");
        stringBuffer1.append("上上上段交易内交易为周四,");
        stringBuffer1.append("上上上段交易内交易为周五,");
        stringBuffer1.append("上上上段交易内交易为周六,");
        stringBuffer1.append("上上上段交易内交易为周日,");
        stringBuffer1.append("上上上段交易内交易是否为节假日,");
        stringBuffer1.append("上上上段交易内交易是否为节假日前一天,");
        stringBuffer1.append("历史交易登录与交易的平均时间间隔,");
        stringBuffer1.append("本次登录时长,");
        stringBuffer1.append("本次登录类型是否为1,");
        stringBuffer1.append("本次登录类型是否为2,");
        stringBuffer1.append("本次登录类型是否为3,");
        stringBuffer1.append("本次登录来源为10,");
        stringBuffer1.append("本次登录来源为1,");
        stringBuffer1.append("本次登录来源为2,");
        stringBuffer1.append("本次登录来源为11,");
        stringBuffer1.append("本次登录来源为21,");
        stringBuffer1.append("本次登录来源为5,");
        stringBuffer1.append("本次登录来源为8,");
        stringBuffer1.append("本次登录来源为16,");
        stringBuffer1.append("本次登录来源为18,");
        stringBuffer1.append("本次登录来源为3,");
        stringBuffer1.append("本次登录来源为12,");
        stringBuffer1.append("1h内登录结果为1登录来源为10次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为1次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为2次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为11次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为21次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为5次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为8次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为16次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为18次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为3次数,");
        stringBuffer1.append("1h内登录结果为1登录来源为12次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为10次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为1次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为2次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为11次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为21次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为5次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为8次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为16次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为18次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为3次数,");
        stringBuffer1.append("1h内登录结果为31登录来源为12次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为10次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为1次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为2次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为11次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为21次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为5次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为8次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为16次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为18次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为3次数,");
        stringBuffer1.append("1h内登录结果为其他登录来源为12次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为10次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为1次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为2次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为11次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为21次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为5次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为8次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为16次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为18次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为3次数,");
        stringBuffer1.append("2h内登录结果为1登录来源为12次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为10次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为1次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为2次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为11次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为21次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为5次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为8次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为16次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为18次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为3次数,");
        stringBuffer1.append("2h内登录结果为31登录来源为12次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为10次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为1次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为2次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为11次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为21次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为5次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为8次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为16次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为18次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为3次数,");
        stringBuffer1.append("2h内登录结果为其他登录来源为12次数,");
        stringBuffer1.append("本次登录设备是否新设备,");
        stringBuffer1.append("本次登录设备是否成功新设备,");
        stringBuffer1.append("本次登录设备在10min内登录次数,");
        stringBuffer1.append("本次登录设备在30min内登录次数,");
        stringBuffer1.append("本次登录设备在1h内登录次数,");
        stringBuffer1.append("本次登录设备在1_2h内登录次数,");
        stringBuffer1.append("本次登录设备在2h_1d内登录次数,");
        stringBuffer1.append("本次登录设备在1_3d内登录次数,");
        stringBuffer1.append("本次登录设备在3_7d内登录次数,");
        stringBuffer1.append("本次登录设备在7_14d内登录次数,");
        stringBuffer1.append("本次登录设备在14_30d内登录次数,");
        stringBuffer1.append("本次登录设备在30_45d内登录次数,");
        stringBuffer1.append("本次登录设备在45_60d内登录次数,");
        stringBuffer1.append("本次登录设备在历史内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在10min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在20min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在30min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在1h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在1_2h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在2h_1d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在1_3d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在3_7d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在7_14d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在14_30d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在30_45d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在45_60d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为1在历史内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在10min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在20min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在30min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在1h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在1_2h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在2h_1d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在1_3d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在3_7d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在7_14d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在14_30d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在30_45d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在45_60d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为31在历史内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在10min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在20min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在30min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在1h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在1_2h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在2h_1d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在1_3d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在3_7d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在7_14d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在14_30d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在30_45d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在45_60d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为负数在历史内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在10min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在20min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在30min内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在1h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在1_2h内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在2h_1d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在1_3d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在3_7d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在7_14d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在14_30d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在30_45d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在45_60d内登录次数,");
        stringBuffer1.append("本次登录设备登录结果为正数在历史内登录次数,");
        stringBuffer1.append("上段交易登录设备在10min内登录次数,");
        stringBuffer1.append("上段交易登录设备在30min内登录次数,");
        stringBuffer1.append("上段交易登录设备在1h内登录次数,");
        stringBuffer1.append("上段交易登录设备在1_2h内登录次数,");
        stringBuffer1.append("上段交易登录设备在2h_1d内登录次数,");
        stringBuffer1.append("上段交易登录设备在1_3d内登录次数,");
        stringBuffer1.append("上段交易登录设备在3_7d内登录次数,");
        stringBuffer1.append("上段交易登录设备在7_14d内登录次数,");
        stringBuffer1.append("上段交易登录设备在14_30d内登录次数,");
        stringBuffer1.append("上段交易登录设备在30_45d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在10min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在20min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在30min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在1h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在1_2h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在2h_1d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在1_3d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在3_7d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在7_14d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在14_30d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为1在30_45d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在10min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在20min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在30min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在1h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在1_2h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在2h_1d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在1_3d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在3_7d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在7_14d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在14_30d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为31在30_45d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在10min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在20min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在30min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在1h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在1_2h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在2h_1d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在1_3d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在3_7d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在7_14d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在14_30d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为负数在30_45d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在10min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在20min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在30min内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在1h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在1_2h内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在2h_1d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在1_3d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在3_7d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在7_14d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在14_30d内登录次数,");
        stringBuffer1.append("上段交易登录设备登录结果为正数在30_45d内登录次数,");
        stringBuffer1.append("上上段交易登录设备在10min内登录次数,");
        stringBuffer1.append("上上段交易登录设备在30min内登录次数,");
        stringBuffer1.append("上上段交易登录设备在1h内登录次数,");
        stringBuffer1.append("上上段交易登录设备在1_2h内登录次数,");
        stringBuffer1.append("上上段交易登录设备在2h_1d内登录次数,");
        stringBuffer1.append("上上段交易登录设备在1_3d内登录次数,");
        stringBuffer1.append("上上段交易登录设备在3_7d内登录次数,");
        stringBuffer1.append("上上段交易登录设备在7_14d内登录次数,");
        stringBuffer1.append("上上段交易登录设备在14_30d内登录次数,");
        stringBuffer1.append("上上段交易登录设备在30_45d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在10min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在20min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在30min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在1h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在1_2h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在2h_1d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在1_3d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在3_7d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在7_14d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在14_30d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为1在30_45d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在10min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在20min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在30min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在1h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在1_2h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在2h_1d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在1_3d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在3_7d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在7_14d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在14_30d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为31在30_45d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在10min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在20min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在30min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在1h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在1_2h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在2h_1d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在1_3d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在3_7d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在7_14d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在14_30d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为负数在30_45d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在10min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在20min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在30min内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在1h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在1_2h内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在2h_1d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在1_3d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在3_7d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在7_14d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在14_30d内登录次数,");
        stringBuffer1.append("上上段交易登录设备登录结果为正数在30_45d内登录次数,");
        stringBuffer1.append("历史交易登录设备在10min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在30min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在1h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在1_2h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在2h_1d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在1_3d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在3_7d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在7_14d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在14_30d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备在30_45d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在10min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在20min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在30min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在1h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在1_2h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在2h_1d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在1_3d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在3_7d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在7_14d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在14_30d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为1在30_45d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在10min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在20min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在30min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在1h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在1_2h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在2h_1d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在1_3d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在3_7d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在7_14d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在14_30d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为31在30_45d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在10min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在20min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在30min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在1h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在1_2h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在2h_1d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在1_3d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在3_7d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在7_14d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在14_30d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为负数在30_45d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在10min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在20min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在30min内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在1h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在1_2h内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在2h_1d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在1_3d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在3_7d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在7_14d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在14_30d内平均登录次数,");
        stringBuffer1.append("历史交易登录设备登录结果为正数在30_45d内平均登录次数,");
        stringBuffer1.append("本次登录设备3min内登录其他用户次数,");
        stringBuffer1.append("本次登录设备10min内登录其他用户次数,");
        stringBuffer1.append("本次登录设备20min内登录其他用户次数,");
        stringBuffer1.append("本次登录设备30min内登录其他用户次数,");
        stringBuffer1.append("本次登录设备1h内登录其他用户次数,");
        stringBuffer1.append("本次登录设备2h内登录其他用户次数,");
        stringBuffer1.append("本次登录设备2_6h内登录其他用户次数,");
        stringBuffer1.append("本次登录设备6_12h内登录其他用户次数,");
        stringBuffer1.append("本次登录设备12h_1d内登录其他用户次数,");
        stringBuffer1.append("本次登录设备1_2d内登录其他用户次数,");
        stringBuffer1.append("本次登录设备2_7d内登录其他用户次数,");
        stringBuffer1.append("本次登录设备7_14d内登录其他用户次数,");
        stringBuffer1.append("本次登录设备14_30d内登录其他用户次数,");
        stringBuffer1.append("本次登录设备30_45d内登录其他用户次数,");
        stringBuffer1.append("本次登录设备45_60d内登录其他用户次数,");
        stringBuffer1.append("本次登录设备历史内登录其他用户次数,");
        stringBuffer1.append("本次登录设备3min内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备10min内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备20min内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备30min内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备1h内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备2h内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备2_6h内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备6_12h内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备12h_1d内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备1_2d内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备2_7d内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备7_14d内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备14_30d内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备30_45d内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备45_60d内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备历史内登录其他用户次数结果为1,");
        stringBuffer1.append("本次登录设备3min内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备10min内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备20min内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备30min内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备1h内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备2h内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备2_6h内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备6_12h内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备12h_1d内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备1_2d内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备2_7d内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备7_14d内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备14_30d内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备30_45d内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备45_60d内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备历史内登录其他用户次数结果为31,");
        stringBuffer1.append("本次登录设备3min内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备10min内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备20min内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备30min内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备1h内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备2h内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备2_6h内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备6_12h内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备12h_1d内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备1_2d内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备2_7d内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备7_14d内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备14_30d内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备30_45d内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备45_60d内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备历史内登录其他用户次数结果为负数,");
        stringBuffer1.append("本次登录设备3min内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备10min内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备20min内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备30min内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备1h内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备2h内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备2_6h内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备6_12h内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备12h_1d内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备1_2d内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备2_7d内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备7_14d内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备14_30d内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备30_45d内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备45_60d内登录其他用户次数结果为正数,");
        stringBuffer1.append("本次登录设备历史内登录其他用户次数结果为正数,");
        stringBuffer1.append("10min内登录不同设备数,");
        stringBuffer1.append("30min内登录不同设备数,");
        stringBuffer1.append("2h内登录不同设备数,");
        stringBuffer1.append("6h内登录不同设备数,");
        stringBuffer1.append("1d内登录不同设备数,");
        stringBuffer1.append("10min内登录设备中新设备数量且结果为1,");
        stringBuffer1.append("30min内登录设备中新设备数量且结果为1,");
        stringBuffer1.append("2h内登录设备中新设备数量且结果为1,");
        stringBuffer1.append("6h内登录设备中新设备数量且结果为1,");
        stringBuffer1.append("1d内登录设备中新设备数量且结果为1,");
        stringBuffer1.append("10min内登录不同设备数结果为1,");
        stringBuffer1.append("30min内登录不同设备数结果为1,");
        stringBuffer1.append("2h内登录不同设备数结果为1,");
        stringBuffer1.append("6h内登录不同设备数结果为1,");
        stringBuffer1.append("1d内登录不同设备数结果为1,");
        stringBuffer1.append("10min内登录不同设备数结果为31,");
        stringBuffer1.append("30min内登录不同设备数结果为31,");
        stringBuffer1.append("2h内登录不同设备数结果为31,");
        stringBuffer1.append("6h内登录不同设备数结果为31,");
        stringBuffer1.append("1d内登录不同设备数结果为31,");
        stringBuffer1.append("10min内登录不同设备数结果为负数,");
        stringBuffer1.append("30min内登录不同设备数结果为负数,");
        stringBuffer1.append("2h内登录不同设备数结果为负数,");
        stringBuffer1.append("6h内登录不同设备数结果为负数,");
        stringBuffer1.append("1d内登录不同设备数结果为负数,");
        stringBuffer1.append("10min内登录不同设备数结果为正数,");
        stringBuffer1.append("30min内登录不同设备数结果为正数,");
        stringBuffer1.append("2h内登录不同设备数结果为正数,");
        stringBuffer1.append("6h内登录不同设备数结果为正数,");
        stringBuffer1.append("1d内登录不同设备数结果为正数,");
        stringBuffer1.append("本次登录是否为新城市,");
        stringBuffer1.append("本次登录城市在0_30min内被登录次数,");
        stringBuffer1.append("本次登录城市在30min_1h内被登录次数,");
        stringBuffer1.append("本次登录城市在1_2h内被登录次数,");
        stringBuffer1.append("本次登录城市在2h_1d内被登录次数,");
        stringBuffer1.append("本次登录城市在1_3d内被登录次数,");
        stringBuffer1.append("本次登录城市在3_7d内被登录次数,");
        stringBuffer1.append("本次登录城市在7_14d内被登录次数,");
        stringBuffer1.append("本次登录城市在14_30d内被登录次数,");
        stringBuffer1.append("本次登录城市在30_45d内被登录次数,");
        stringBuffer1.append("本次登录城市在45_60d内被登录次数,");
        stringBuffer1.append("本次登录城市在历史内被登录次数,");
        stringBuffer1.append("10min内登录不同城市数,");
        stringBuffer1.append("30min内登录不同城市数,");
        stringBuffer1.append("2h内登录不同城市数,");
        stringBuffer1.append("6h内登录不同城市数,");
        stringBuffer1.append("1d内登录不同城市数,");
        stringBuffer1.append("10min内登录不同城市数结果为1,");
        stringBuffer1.append("30min内登录不同城市数结果为1,");
        stringBuffer1.append("2h内登录不同城市数结果为1,");
        stringBuffer1.append("6h内登录不同城市数结果为1,");
        stringBuffer1.append("1d内登录不同城市数结果为1,");
        stringBuffer1.append("10min内登录不同城市数结果为31,");
        stringBuffer1.append("30min内登录不同城市数结果为31,");
        stringBuffer1.append("2h内登录不同城市数结果为31,");
        stringBuffer1.append("6h内登录不同城市数结果为31,");
        stringBuffer1.append("1d内登录不同城市数结果为31,");
        stringBuffer1.append("10min内登录不同城市数结果为负数,");
        stringBuffer1.append("30min内登录不同城市数结果为负数,");
        stringBuffer1.append("2h内登录不同城市数结果为负数,");
        stringBuffer1.append("6h内登录不同城市数结果为负数,");
        stringBuffer1.append("1d内登录不同城市数结果为负数,");
        stringBuffer1.append("10min内登录不同城市数结果为正数,");
        stringBuffer1.append("30min内登录不同城市数结果为正数,");
        stringBuffer1.append("2h内登录不同城市数结果为正数,");
        stringBuffer1.append("6h内登录不同城市数结果为正数,");
        stringBuffer1.append("1d内登录不同城市数结果为正数,");
        stringBuffer1.append("本次登录是否为新IP,");
        stringBuffer1.append("本次登录IP在0_30min内被登录次数,");
        stringBuffer1.append("本次登录IP在30min_1h内被登录次数,");
        stringBuffer1.append("本次登录IP在1_2h内被登录次数,");
        stringBuffer1.append("本次登录IP在2h_1d内被登录次数,");
        stringBuffer1.append("本次登录IP在1_3d内被登录次数,");
        stringBuffer1.append("本次登录IP在3_7d内被登录次数,");
        stringBuffer1.append("本次登录IP在7_14d内被登录次数,");
        stringBuffer1.append("本次登录IP在14_30d内被登录次数,");
        stringBuffer1.append("本次登录IP在30_45d内被登录次数,");
        stringBuffer1.append("本次登录IP在45_60d内被登录次数,");
        stringBuffer1.append("本次登录IP在历史内被登录次数,");
        stringBuffer1.append("10min内登录不同IP数,");
        stringBuffer1.append("30min内登录不同IP数,");
        stringBuffer1.append("2h内登录不同IP数,");
        stringBuffer1.append("6h内登录不同IP数,");
        stringBuffer1.append("1d内登录不同IP数,");
        stringBuffer1.append("10min内登录不同IP数结果为1,");
        stringBuffer1.append("30min内登录不同IP数结果为1,");
        stringBuffer1.append("2h内登录不同IP数结果为1,");
        stringBuffer1.append("6h内登录不同IP数结果为1,");
        stringBuffer1.append("1d内登录不同IP数结果为1,");
        stringBuffer1.append("10min内登录不同IP数结果为31,");
        stringBuffer1.append("30min内登录不同IP数结果为31,");
        stringBuffer1.append("2h内登录不同IP数结果为31,");
        stringBuffer1.append("6h内登录不同IP数结果为31,");
        stringBuffer1.append("1d内登录不同IP数结果为31,");
        stringBuffer1.append("10min内登录不同IP数结果为负数,");
        stringBuffer1.append("30min内登录不同IP数结果为负数,");
        stringBuffer1.append("2h内登录不同IP数结果为负数,");
        stringBuffer1.append("6h内登录不同IP数结果为负数,");
        stringBuffer1.append("1d内登录不同IP数结果为负数,");
        stringBuffer1.append("10min内登录不同IP数结果为正数,");
        stringBuffer1.append("30min内登录不同IP数结果为正数,");
        stringBuffer1.append("2h内登录不同IP数结果为正数,");
        stringBuffer1.append("6h内登录不同IP数结果为正数,");
        stringBuffer1.append("1d内登录不同IP数结果为正数,");
        stringBuffer1.append("10min内登录结果为1次数,");
        stringBuffer1.append("10min内登录结果为31次数,");
        stringBuffer1.append("10min内登录结果为正数次数,");
        stringBuffer1.append("10min内登录结果为负数次数,");
        stringBuffer1.append("20min内登录结果为1次数,");
        stringBuffer1.append("20min内登录结果为31次数,");
        stringBuffer1.append("20min内登录结果为正数次数,");
        stringBuffer1.append("20min内登录结果为负数次数,");
        stringBuffer1.append("45min内登录结果为1次数,");
        stringBuffer1.append("45min内登录结果为31次数,");
        stringBuffer1.append("45min内登录结果为正数次数,");
        stringBuffer1.append("45min内登录结果为负数次数,");
        stringBuffer1.append("90min内登录结果为1次数,");
        stringBuffer1.append("90min内登录结果为31次数,");
        stringBuffer1.append("90min内登录结果为正数次数,");
        stringBuffer1.append("90min内登录结果为负数次数,");
        stringBuffer1.append("120min内登录结果为1次数,");
        stringBuffer1.append("120min内登录结果为31次数,");
        stringBuffer1.append("120min内登录结果为正数次数,");
        stringBuffer1.append("120min内登录结果为负数次数,");
        stringBuffer1.append("本次登录设备5min内连接IP次数,");
        stringBuffer1.append("本次登录设备15min内连接IP次数,");
        stringBuffer1.append("本次登录设备30min内连接IP次数,");
        stringBuffer1.append("本次登录设备60min内连接IP次数,");
        stringBuffer1.append("上段交易登录设备5min内连接IP次数,");
        stringBuffer1.append("上段交易登录设备15min内连接IP次数,");
        stringBuffer1.append("上段交易登录设备30min内连接IP次数,");
        stringBuffer1.append("上段交易登录设备60min内连接IP次数,");
        stringBuffer1.append("上上段登录设备5min内连接IP次数,");
        stringBuffer1.append("上上段登录设备15min内连接IP次数,");
        stringBuffer1.append("上上段登录设备30min内连接IP次数,");
        stringBuffer1.append("上上段登录设备60min内连接IP次数,");
        stringBuffer1.append("历史登录设备5min内连接IP平均次数,");
        stringBuffer1.append("历史登录设备15min内连接IP平均次数,");
        stringBuffer1.append("历史登录设备30min内连接IP平均次数,");
        stringBuffer1.append("历史登录设备60min内连接IP平均次数,");
        stringBuffer1.append("本次登录设备5min内连接城市次数,");
        stringBuffer1.append("本次登录设备15min内连接城市次数,");
        stringBuffer1.append("本次登录设备30min内连接城市次数,");
        stringBuffer1.append("本次登录设备60min内连接城市次数,");
        stringBuffer1.append("上段交易登录设备5min内连接城市次数,");
        stringBuffer1.append("上段交易登录设备15min内连接城市次数,");
        stringBuffer1.append("上段交易登录设备30min内连接城市次数,");
        stringBuffer1.append("上段交易登录设备60min内连接城市次数,");
        stringBuffer1.append("上上段登录设备5min内连接城市次数,");
        stringBuffer1.append("上上段登录设备15min内连接城市次数,");
        stringBuffer1.append("上上段登录设备30min内连接城市次数,");
        stringBuffer1.append("上上段登录设备60min内连接城市次数,");
        stringBuffer1.append("历史登录设备5min内连接城市平均次数,");
        stringBuffer1.append("历史登录设备15min内连接城市平均次数,");
        stringBuffer1.append("历史登录设备30min内连接城市平均次数,");
        stringBuffer1.append("历史登录设备60min内连接城市平均次数,");
        stringBuffer1.append("本次登录10min内登录存在登录结果为1后结果为其他的情况,");
        stringBuffer1.append("本次登录20min内登录存在登录结果为1后结果为其他的情况,");
        stringBuffer1.append("本次登录10min内登录存在登录结果为1后结果为其他的情况且设备不同,");
        stringBuffer1.append("本次登录20min内登录存在登录结果为1后结果为其他的情况且设备不同,");
        stringBuffer1.append("用户全局是否只有本段交易,");
        stringBuffer1.append("用户全局行为是否全在前后24h内,");
        stringBuffer1.append("本次设备是交易设备概率,");
        stringBuffer1.append("本段交易数量,");
        stringBuffer1.append("over");
        return stringBuffer1.toString();
    }
}
