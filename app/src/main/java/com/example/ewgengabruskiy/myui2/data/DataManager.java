package com.example.ewgengabruskiy.myui2.data;

import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Keyword;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DataManager {

    public static final String SUCCESS_ADDING = "success_adding";
    public static final String FAILED_ADDING = "failed_adding";

    private HashSet<Person> personList = new HashSet<>();
    private HashSet<Site> siteList = new HashSet<>();

    private NetworkManager networkManager = new NetworkManager(this);
    DataManagerListener dataManagerListener;

    public DataManager(DataManagerListener dataManagerListener){
        this.dataManagerListener = dataManagerListener;
    }

    public void getSiteList(boolean wasUpdated){
        if (siteList.isEmpty()|| wasUpdated) networkManager.getDataAllSites();
        else dataManagerListener.updateListOfSites(siteList);
    }

    void siteListFromServer(List<Site> sites){
        if (!sites.isEmpty()) {
            if(!siteList.isEmpty()) siteList.clear();
            siteList.addAll(sites);
            dataManagerListener.updateListOfSites(siteList);
        }
        else dataManagerListener.updateListOfSites(siteList);
    }

    public void addOrEditSite(Site site)
    {
        if (site.getId() == null) networkManager.addSite(site.getName(),site.getUrl());
        else {
            networkManager.modifySite(site.getId(),site.getName(),site.getUrl());
        }
        getSiteList(true);
    }



    public void deleteSite(Site site){
        networkManager.delSite(site.getId());
        getSiteList(true);
    }



    public void getPersonList(boolean wasUpdated){
        if (personList.isEmpty()|| wasUpdated) networkManager.getDataAllPerson(false);
        else dataManagerListener.updateListOfPersons(personList);
    }

    private HashMap<Integer,Person> bufPersonList = new HashMap<>();

    void personListFromServer(List<Person> persons){
        if (!persons.isEmpty()) {
            if (!personList.isEmpty()) personList.clear();
            for (Person p : persons) {
                bufPersonList.put(p.getId(), p);
                networkManager.getDataKeywordsByPerson(p.getId());
            }
        }
        else dataManagerListener.updateListOfPersons(personList);
    }

    synchronized void keywordsListFromServer(List<Keyword> keywords, int personId){
        Person p = bufPersonList.get(personId);
        p.setKeywordList(keywords);
        personList.add(p);
        bufPersonList.remove(personId);
        if (bufPersonList.isEmpty()) dataManagerListener.updateListOfPersons(personList);
    }

    private Person bufPerson;

    public void addOrEditPerson(Person person){
        if (person.getId() == null) {
            bufPerson = person;
            networkManager.addPerson(person.getName());
        }
        else {
            networkManager.modifyPerson(person.getId(),person.getName());
            ArrayList<Keyword> arr = person.getKeywordList();
            if (!arr.isEmpty()){
                for(Keyword k: arr){
                    if (k.isToDelete() && k.getId()!=null) networkManager.deleteKeyword(k.getId());
                    else if (k.getId()!=null) networkManager.modifyKeyword(k.getId(),k.getName(),person.getId());
                    else networkManager.addKeyword(k.getName(),person.getId());
                }
            }
            getPersonList(true);
        }

    }

    void responseFromServerAddingPerson(String response){
        if (response == null) {
            bufPerson = null;
            dataManagerListener.userCreatingResponse(FAILED_ADDING);
            return;
        }
        networkManager.getDataAllPerson(true);
    }

    void listOfPersonToAddKeywords(List<Person> persons){
        if (persons.isEmpty()) {
            dataManagerListener.userCreatingResponse(FAILED_ADDING);
            return;
        }

        int neededFuckingId = 0;
        for (Person p : persons) {
            if (p.getName().equals(bufPerson.getName())) {
                neededFuckingId = p.getId();
                break;
            }
        }
        ArrayList<Keyword> arr = bufPerson.getKeywordList();
        if (!arr.isEmpty() && neededFuckingId!=0) {
            for(Keyword k: arr){
                networkManager.addKeyword(k.getName(),neededFuckingId);
            }
        }
        dataManagerListener.userCreatingResponse(SUCCESS_ADDING);
        bufPerson = null;
        getPersonList(true);
    }

    public void deletePerson(Person person){
        ArrayList<Keyword> arr = person.getKeywordList();
        if (!arr.isEmpty())
            for (Keyword k : arr) {
                if(k.getId()!=null) networkManager.deleteKeyword(k.getId());
            }
        networkManager.deletePerson(person.getId());
        getPersonList(true);
    }

    public void getGeneralStatistic(int id){
        networkManager.getGeneralStatisticNetworkRequest(id);
    }

    void responseFromServerGeneralStatistic(List<CommonStatistic> commonStatistics){

        ArrayList<CommonStatistic> arr = new ArrayList<>();
        arr.addAll(commonStatistics);
        dataManagerListener.updateGeneralStatistic(arr);
    }
    public void getDaylyStatistic(int siteID,String datefrom, String dateto){
        networkManager.getDaylyStatisticNetworkRequest(siteID,datefrom,dateto);
    }


    void responseFromServerDayleStatistic(List<DaylyStatistic> daylyStatisticcs){
        ArrayList<DaylyStatistic> arr = new ArrayList<>();
        arr.addAll(daylyStatisticcs);
        dataManagerListener.updateDaylyStatistic(arr);
    }
}
