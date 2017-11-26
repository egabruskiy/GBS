package com.example.ewgengabruskiy.myui2.data.datas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("active")
    @Expose
    private Boolean active;

    ArrayList<Keyword> keywordList = new ArrayList<>();

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", keywordList=" + keywordList +
                '}';
    }

//    public void setKeywordList(List<Keyword> l){
//        if (!keywordList.isEmpty()) keywordList.clear();
//        keywordList.addAll(l);
//    }

public void setKeywordList(List<Keyword> l){
    if (keywordList == null) keywordList = new ArrayList<>();
    if (l!=null) {
        if (!keywordList.isEmpty()) keywordList.clear();
        keywordList.addAll(l);
    }
}

    public ArrayList<Keyword> getKeywordList(){
        return keywordList;
    }

    public void addKeyword(Keyword k){
        if (keywordList == null) keywordList = new ArrayList<>();
        keywordList.add(k);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id != null ? id.equals(person.id) : person.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }



    public Person(){}
    protected Person(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        byte activeVal = in.readByte();
        active = activeVal == 0x02 ? null : activeVal != 0x00;
        if (in.readByte() == 0x01) {
            keywordList = new ArrayList<Keyword>();
            in.readList(keywordList, Keyword.class.getClassLoader());
        } else {
            keywordList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (active == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (active ? 0x01 : 0x00));
        }
        if (keywordList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(keywordList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };


}
