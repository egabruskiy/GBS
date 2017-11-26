package com.example.ewgengabruskiy.myui2.data;


import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.ArrayList;
import java.util.HashSet;

public interface DataManagerListener {

    void updateListOfSites(HashSet<Site> sites);
    void updateListOfPersons(HashSet<Person> sites);
    void userCreatingResponse(String msg);
    void updateGeneralStatistic(ArrayList<CommonStatistic> commonStatistics);
    void updateDaylyStatistic(ArrayList<DaylyStatistic> daylyStatistics);

}
