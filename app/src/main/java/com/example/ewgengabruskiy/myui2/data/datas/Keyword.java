package com.example.ewgengabruskiy.myui2.data.datas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword  implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("personId")
    @Expose
    private Integer personId;
    @SerializedName("name")
    @Expose
    private String name;

    private boolean toDelete;

    public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
//        return "Keyword{" +
//                "personId=" + personId +
//                ", name='" + name + '\'' +
//                '}';
    }

    public Keyword(){}
    public Keyword(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        personId = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        toDelete = in.readByte() != 0x00;
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
        if (personId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(personId);
        }
        dest.writeString(name);
        dest.writeByte((byte) (toDelete ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Keyword> CREATOR = new Parcelable.Creator<Keyword>() {
        @Override
        public Keyword createFromParcel(Parcel in) {
            return new Keyword(in);
        }

        @Override
        public Keyword[] newArray(int size) {
            return new Keyword[size];
        }
    };

}