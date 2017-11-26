package com.example.ewgengabruskiy.myui2.data.retrofit;


import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Keyword;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitRequests {

    //Sites
    @GET("/unauthorized/admin/ui/getAllSites/")
    Call<List<Site>> getAllSites();

    @GET("unauthorized/admin/ui/addSite")
    Call<String> addSiteToServer(@Query("name") String name, @Query("url") String url, @Query("active") boolean b);

    @GET("unauthorized/admin/ui/delSite")
    Call<String> deleteSiteById(@Query("id") int id);

    @GET("/unauthorized/admin/ui/modifySite")
    Call<String> modifySiteById(@Query("id") Integer id, @Query("name") String name, @Query("url") String url, @Query("active") boolean b);


    //Person
    @GET("/unauthorized/admin/ui/getAllPersons")
    Call<List<Person>> getAllPersons();

    @GET("/unauthorized/admin/ui/addPerson")
    Call<String> addPersonToServer(@Query("name") String name, @Query("active") boolean b);

    @GET("/unauthorized/admin/ui/delPerson")
    Call<String> deletePersonById(@Query("id") int id);

    @GET("/unauthorized/admin/ui/modifyPerson")
    Call<String> modifyPersonById(@Query("id") Integer id, @Query("name") String name, @Query("active") boolean b);


    //Keywords
    @GET("/unauthorized/user/ui/getKeywordsByPersonId")
    Call<List<Keyword>> getKeywordsByPersonId(@Query("id") Integer id);

    @GET("/unauthorized/admin/ui/addKeyword")
    Call<String> addKeywordToServer(@Query("name") String name, @Query("personId") int id);

    @GET("/unauthorized/admin/ui/delKeyword")
    Call<String> deleteKeywordById(@Query("id") int id);

    @GET("/unauthorized/admin/ui/modifyKeyword")
    Call<String> modifyKeywordById(@Query("id") Integer id, @Query("name") String name, @Query("personId") int personid);

    @GET("/unauthorized/user/ui/getGeneralStatistics")
    Call<List<CommonStatistic>> getGeneralStatisticId(@Query("siteID") Integer id);

    @GET("/unauthorized/user/ui/getDailyStatistics")
    Call<List<DaylyStatistic>> getDaylyStatistic(@Query("siteID") Integer id, @Query("datefrom") String datefrom, @Query("dateto") String dateto);


}
