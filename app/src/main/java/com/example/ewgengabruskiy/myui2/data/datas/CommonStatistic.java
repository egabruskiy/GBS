package com.example.ewgengabruskiy.myui2.data.datas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CommonStatistic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("generalRank")
    @Expose
    private Integer generalRank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGeneralRank() {
        return generalRank;
    }

    public void setGeneralRank(Integer generalRank) {
        this.generalRank = generalRank;
    }


    @Override
    public String toString() {
        return "CommonStatistic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", generalRank=" + generalRank +
                '}';
    }
}
