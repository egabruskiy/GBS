package com.example.ewgengabruskiy.myui2;

import android.content.Intent;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ewgengabruskiy.myui2.data.datas.Person;

/**
 * Created by ewgengabruskiy on 20.11.17.
 */

public class GridPersonAdapter extends ArrayAdapter<String> {



    private static final String[] mContacts = {};

    Context mContext;

    // Конструктор
    public GridPersonAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setText(mContacts[position]);
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mContacts[position];
    }

}