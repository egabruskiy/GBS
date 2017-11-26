package com.example.ewgengabruskiy.myui2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewgengabruskiy on 18.11.17.
 */

public class Persons {
    private String person,keyWords;

    static String a =  "666";

    public static String givemeA () {
        return a;
    }


    public Persons (String person,String keyWords){
        this.person = person;
        this.keyWords = keyWords;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String name) {
        this.person = name;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String name) {
        this.keyWords = name;
    }

}


