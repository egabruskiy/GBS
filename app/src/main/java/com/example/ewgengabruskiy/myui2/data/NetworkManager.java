package com.example.ewgengabruskiy.myui2.data;

import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Keyword;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;
import com.example.ewgengabruskiy.myui2.data.retrofit.RetrofitClient;
import com.example.ewgengabruskiy.myui2.data.retrofit.RetrofitRequests;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class NetworkManager {

    private final String base_url2 = "http://54.154.158.193/";

    private Retrofit retrofitClient;
    private RetrofitRequests rr;
    private DataManager dataManager;


    public NetworkManager(DataManager dataManager) {

        retrofitClient = RetrofitClient.getClient(base_url2);
        rr = retrofitClient.create(RetrofitRequests.class);
        this.dataManager = dataManager;
        //getAllSites(rr);
        //getDataAllPerson(rr);
        //getDataKeywordsByPerson(rr,1);
        //addSite(rr,"forbes1.ru","http://forbes1.ru");
        //delSite(rr,22);
        //modifySite(rr,28,"forbes.com","http://forbes.com");
        //addPerson(rr,"Абракадабра");
        //modifyPerson(rr,12,"Кукабрака");
        //deletePerson(rr,12);
        //addKeyword(rr,"Собчакушка", 3);
        //modifyKeyword(rr,21,"крутяшечка", 3);
        //deleteKeyword(rr,21);
    }

    //Site requests
    void getDataAllSites() {

        Call<List<Site>> serverTimeCall = rr.getAllSites();
        serverTimeCall.enqueue(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                System.out.println("мы какой то ответ получили");
                dataManager.siteListFromServer(response.body());
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void modifySite(int id, String name, String url) {

        Call<String> serverTimeCall = rr.modifySiteById(id, name, url, true);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); //1 в случае успеха: 0 в случае неудачи
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void addSite(String name, String url) {

        Call<String> serverTimeCall = rr.addSiteToServer(name, url, true);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); //OK в случае успеха: null в случае неудачи

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void delSite(int id) {

        Call<String> serverTimeCall = rr.deleteSiteById(id);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); // 1 в случае успеха: 0 в случае неуспеха

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    //Person requests

    void getDataAllPerson(final boolean isAddingNewUser) {

        Call<List<Person>> serverTimeCall = rr.getAllPersons();
        serverTimeCall.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                System.out.println("мы какой то ответ получили");
                if (!isAddingNewUser) dataManager.personListFromServer(response.body());
                else dataManager.listOfPersonToAddKeywords(response.body());
//                if (response.body() != null)
//                    for (Person m : response.body()) {
//                        System.out.println(m);
//                    }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void addPerson(String name) {

        Call<String> serverTimeCall = rr.addPersonToServer(name, true);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("мы какой то ответ получили");
                //System.out.println(response.body()); //OK в случае успеха: null в случае неудачи
                dataManager.responseFromServerAddingPerson(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    //не возвращает 0, если данные не изменены
    void modifyPerson(int id, String name) {

        Call<String> serverTimeCall = rr.modifyPersonById(id, name, true);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); //1 в случае успеха: 0 в случае неудачи(не найден такой id)
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void deletePerson(int id) {
        Call<String> serverTimeCall = rr.deletePersonById(id);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); // 1 в случае успеха: 0 в случае неуспеха

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    //Keywords requests
    void getDataKeywordsByPerson(int id) {

        final int personid = id;

        Call<List<Keyword>> serverTimeCall = rr.getKeywordsByPersonId(id);
        serverTimeCall.enqueue(new Callback<List<Keyword>>() {
            @Override
            public void onResponse(Call<List<Keyword>> call, Response<List<Keyword>> response) {

                System.out.println("мы какой то ответ получили");
                dataManager.keywordsListFromServer(response.body(), personid);
//                if (response.body() != null)
//                    for (Keyword m : response.body()) {
//                        System.out.println(m);
//                    }
//                if (response.body().isEmpty()) System.out.println("пусто"); // коллекция слов, или пустая коллекция
            }

            @Override
            public void onFailure(Call<List<Keyword>> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void addKeyword(String name, int personid) {
        Call<String> serverTimeCall = rr.addKeywordToServer(name, personid);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); //OK в случае успеха: null в случае неудачи

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void modifyKeyword(int id, String name, int personid) {

        Call<String> serverTimeCall = rr.modifyKeywordById(id, name, personid);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); //1 в случае успеха: 0 в случае неудачи(не найден такой id)
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }

    void deleteKeyword(int id) {

        Call<String> serverTimeCall = rr.deleteKeywordById(id);
        serverTimeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                System.out.println("мы какой то ответ получили");
                System.out.println(response.body()); // 1 в случае успеха: 0 в случае неуспеха

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });
    }


    void getGeneralStatisticNetworkRequest(int id) {

        Call<List<CommonStatistic>> serverTimeCall = rr.getGeneralStatisticId(id);
        serverTimeCall.enqueue(new Callback<List<CommonStatistic>>() {
            @Override
            public void onResponse(Call<List<CommonStatistic>> call, Response<List<CommonStatistic>> response) {

                System.out.println("мы какой то ответ получили");
                dataManager.responseFromServerGeneralStatistic(response.body());
            }

            @Override
            public void onFailure(Call<List<CommonStatistic>> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });

    }
    void getDaylyStatisticNetworkRequest(int id, String datefrom, String dateto) {

        Call<List<DaylyStatistic>> serverTimeCall = rr.getDaylyStatistic(id,datefrom,dateto);
        serverTimeCall.enqueue(new Callback<List<DaylyStatistic>>() {
            @Override
            public void onResponse(Call<List<DaylyStatistic>> call, Response<List<DaylyStatistic>> response) {

                System.out.println("мы какой то ответ получили");
                dataManager.responseFromServerDayleStatistic(response.body());
            }

            @Override
            public void onFailure(Call<List<DaylyStatistic>> call, Throwable t) {
                System.out.println("ERROR " + t);
            }
        });


    }
}
