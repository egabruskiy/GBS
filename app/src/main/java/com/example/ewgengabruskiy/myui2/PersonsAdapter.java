package com.example.ewgengabruskiy.myui2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ewgengabruskiy.myui2.data.DataManagerListener;
import com.example.ewgengabruskiy.myui2.data.datas.Keyword;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ewgengabruskiy on 18.11.17.
 */

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.MyViewHolder> {
    private List<Person> personsList;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView personText, keyWordsText;

        public MyViewHolder (View view){
            super(view);
            personText = view.findViewById(R.id.person);
            keyWordsText = view.findViewById(R.id.key_words);

        }
    }

    public PersonsAdapter (List<Person> personsList){
        this.personsList = personsList;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.persons_list_row, parent, false);

        return new MyViewHolder(itemView);

    }



    @Override

    public void onBindViewHolder(PersonsAdapter.MyViewHolder holder, int position) {
        Person persons = personsList.get(position);
        holder.personText.setText(persons.getName());
        holder.keyWordsText.setText(persons.getKeywordList().toString());
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }
}



