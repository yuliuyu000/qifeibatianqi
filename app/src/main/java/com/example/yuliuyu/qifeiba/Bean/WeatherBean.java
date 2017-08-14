package com.example.yuliuyu.qifeiba.Bean;

import java.util.List;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/8/5 15:36
 * 修改人：yuliuyu
 * 修改时间：2017/8/5 15:36
 * 修改备注：
 */
public class WeatherBean {

    /**
     * airCondition : 优
     * city : 朝阳
     * coldIndex : 易发期
     * date : 2017-08-05
     * distrct : 朝阳
     * dressingIndex : 薄短袖类
     * exerciseIndex : 不适宜
     * future : [{"date":"2017-08-05","dayTime":"雷阵雨","night":"雷阵雨","temperature":"33°C / 23°C","week":"今天","wind":"南风 小于3级"},{"date":"2017-08-06","dayTime":"晴","night":"晴","temperature":"34°C / 24°C","week":"星期日","wind":"南风 小于3级"},{"date":"2017-08-07","dayTime":"晴","night":"晴","temperature":"34°C / 23°C","week":"星期一","wind":"南风 小于3级"},{"date":"2017-08-08","dayTime":"多云","night":"雷阵雨","temperature":"32°C / 24°C","week":"星期二","wind":"南风 小于3级"},{"date":"2017-08-09","dayTime":"雷阵雨","night":"多云","temperature":"29°C / 22°C","week":"星期三","wind":"南风 小于3级"},{"date":"2017-08-10","dayTime":"多云","night":"阴","temperature":"29°C / 23°C","week":"星期四","wind":"南风 小于3级"},{"date":"2017-08-11","dayTime":"雷阵雨","night":"雷阵雨","temperature":"30°C / 22°C","week":"星期五","wind":"南风 小于3级"},{"date":"2017-08-12","dayTime":"零散雷雨","night":"零散雷雨","temperature":"29°C / 22°C","week":"星期六","wind":"东北偏东风 2级"},{"date":"2017-08-13","dayTime":"局部多云","night":"零散雷雨","temperature":"31°C / 22°C","week":"星期日","wind":"东南偏东风 2级"},{"date":"2017-08-14","dayTime":"局部多云","night":"零散雷雨","temperature":"31°C / 22°C","week":"星期一","wind":"东南偏东风 2级"}]
     * humidity : 湿度：45%
     * pollutionIndex : 42
     * province : 北京
     * sunrise : 05:17
     * sunset : 19:24
     * temperature : 33℃
     * time : 14:40
     * updateTime : 20170805145234
     * washIndex : 比较适宜
     * weather : 多云
     * week : 周六
     * wind : 东风1级
     */

    public String airCondition;
    public String city;
    public String coldIndex;
    public String date;
    public String distrct;
    public String dressingIndex;
    public String exerciseIndex;
    public String humidity;
    public String pollutionIndex;
    public String province;
    public String sunrise;
    public String sunset;
    public String temperature;
    public String time;
    public String updateTime;
    public String washIndex;
    public String weather;
    public String week;
    public String wind;
    public List<FutureBean> future;

    public static class FutureBean {
        /**
         * date : 2017-08-05
         * dayTime : 雷阵雨
         * night : 雷阵雨
         * temperature : 33°C / 23°C
         * week : 今天
         * wind : 南风 小于3级
         */

        public String date;
        public String dayTime;
        public String night;
        public String temperature;
        public String week;
        public String wind;
    }
}
