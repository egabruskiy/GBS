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

import com.example.ewgengabruskiy.myui2.data.DataManager;
import com.example.ewgengabruskiy.myui2.data.DataManagerListener;
import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SitesDirectoryFragment extends Fragment implements DataManagerListener {

    private List<Site> sitesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SiteAdapter mAdapter;

    DataManager dataManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sites_directory, container, false);


        recyclerView = rootView.findViewById(R.id.recycler_view_sites);




        mAdapter = new SiteAdapter(sitesList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

//
                Intent intent  = new Intent(getContext(), CreateEditSiteActivity.class);
                Site site =  sitesList.get(position);
                intent.putExtra("test site",site);
                startActivityForResult(intent,1);

            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));

        FloatingActionButton fabSite =  rootView.findViewById(R.id.fab_add_site);
        fabSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),AddSiteActivity.class);

                intent.putExtra("AddSite",new Site());
                startActivityForResult(intent,1);
            }
        });

       dataManager = new DataManager(this);
        dataManager.getSiteList(false);

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Site site = data.getExtras().getParcelable("Back Site");
        dataManager = new DataManager(this);
        dataManager.addOrEditSite(site);

    }

    @Override
    public void updateListOfSites(HashSet<Site> sites) {
        sitesList.clear();
        sitesList.addAll(sites);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void updateListOfPersons(HashSet<Person> sites) {
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
