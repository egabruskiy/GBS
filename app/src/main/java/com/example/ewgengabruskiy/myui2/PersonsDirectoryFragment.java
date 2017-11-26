package com.example.ewgengabruskiy.myui2;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.ewgengabruskiy.myui2.data.DataManager;
import com.example.ewgengabruskiy.myui2.data.DataManagerListener;
import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PersonsDirectoryFragment extends Fragment implements DataManagerListener {

    private List<Person> personsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PersonsAdapter mAdapter;
    private DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_directory, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view_persons);

        mAdapter = new PersonsAdapter(personsList);


         dataManager = new DataManager(this);
        dataManager.getPersonList(false);


        // vertical RecyclerView
        // keep list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);



        FloatingActionButton fab =  rootView.findViewById(R.id.fab_add_person);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),AddPerson.class);
                intent.putExtra("AddPerson",new Person());
                startActivityForResult(intent,1);
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Person person =  personsList.get(position);
                Intent intent = new Intent(getActivity(),CreateEditActivity.class);
               intent.putExtra("test",person);
                startActivityForResult(intent,1);


            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Person person = data.getExtras().getParcelable("Back");
         dataManager = new DataManager(this);
        dataManager.addOrEditPerson(person);

    }

    @Override
    public void updateListOfSites(HashSet<Site> sites) {

    }

    @Override
    public void updateListOfPersons(HashSet<Person> sites) {


        personsList.clear();
        personsList.addAll(sites);
        mAdapter.notifyDataSetChanged();




    }

    @Override
    public void userCreatingResponse(String msg) {

    }

    @Override
    public void updateGeneralStatistic(ArrayList<CommonStatistic> commonStatistics) {

    }

    @Override
    public void updateDaylyStatistic(ArrayList<DaylyStatistic> daylyStatistics) {

    }
}

