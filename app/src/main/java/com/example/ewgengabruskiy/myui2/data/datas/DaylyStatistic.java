package com.example.ewgengabruskiy.myui2.data.datas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DaylyStatistic {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rankDate")
    @Expose
    private String rankDate;
    @SerializedName("dailyRank")
    @Expose
    private Integer dailyRank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public Integer getDailyRank() {
        return dailyRank;
    }

    public void setDailyRank(Integer dailyRank) {
        this.dailyRank = dailyRank;
    }

    @Override
    public String toString() {
        return "DaylyStatistic{" +
                "name='" + name + '\'' +
                ", rankDate='" + rankDate + '\'' +
                ", dailyRank=" + dailyRank +
                '}';
    }
}
