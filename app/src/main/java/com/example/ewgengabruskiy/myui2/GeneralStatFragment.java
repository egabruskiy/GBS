package com.example.ewgengabruskiy.myui2;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.ewgengabruskiy.myui2.data.DataManager;
import com.example.ewgengabruskiy.myui2.data.DataManagerListener;
import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class GeneralStatFragment extends Fragment implements DataManagerListener {


    private CommonStatistic commonStatistic;
    private RecyclerView recyclerViewGeneral;
    private GeneralStatAdapter mAdapter;
    private ArrayAdapter dataAdapter;
    private PieChart chart;
    private List<PieEntry> pieEntries = new ArrayList<>();
    private List<Site> siteList = new ArrayList<>();
    private List<CommonStatistic> statisticList = new ArrayList<>();
    private List<String> someList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_general_stat, container, false);
        chart = rootView.findViewById(R.id.chart);
        recyclerViewGeneral = rootView.findViewById(R.id.recycler_view_general);

        mAdapter = new GeneralStatAdapter(statisticList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        recyclerViewGeneral.setLayoutManager(mLayoutManager);
        recyclerViewGeneral.setAdapter(mAdapter);


        final DataManager dataManager = new DataManager(GeneralStatFragment.this);
        dataManager.getSiteList(false);




        Spinner spinner = rootView.findViewById(R.id.spinner_general);


        dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, someList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        //Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dataManager.getGeneralStatistic(position);


                clearChart();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return rootView;
    }


    private void setupPieChart(List<CommonStatistic> statisticList) {

        for (int i = 0; i<statisticList.size();i++){

           commonStatistic = statisticList.get(i);
            String key = commonStatistic.getName();
            Integer value= commonStatistic.getGeneralRank();
            pieEntries.add(new PieEntry(value, key));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(156,254,230));
        colors.add(Color.rgb(159,185,235));
        colors.add(Color.rgb(143,231,161));
        colors.add(Color.rgb(160,239,136));
        colors.add(Color.rgb(200,246,139));
        colors.add(Color.rgb(176,219,233));
        colors.add(Color.rgb(183,176,253));

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
//        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setValueLinePart1OffsetPercentage(880.f);

        dataSet.setValueLinePart1Length(0.1f);
        // get chart

        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
        dataSet.setValueTextSize(16f);
        dataSet.setSliceSpace(1f);

//        dataSet.setValueLinePart1OffsetPercentage(8.f);
        chart.setEntryLabelTextSize(0);
    }


    private void clearChart() {
        pieEntries.clear();
    }


    @Override
    public void updateListOfSites(HashSet<Site> sites) {
        siteList.clear();
        siteList.addAll(sites);

        for (int i = 0; i < sites.size(); i++) {
            someList.add(siteList.get(i).getName());
        }
        dataAdapter.notifyDataSetChanged();
    }




    @Override
    public void updateListOfPersons(HashSet<Person> sites) {

    }

    @Override
    public void userCreatingResponse(String msg) {

    }

    @Override
    public void updateGeneralStatistic(ArrayList<CommonStatistic> commonStatistics) {

        statisticList.clear();
        statisticList.addAll(commonStatistics);
        mAdapter.notifyDataSetChanged();
        setupPieChart(statisticList);


    }

    @Override
    public void updateDaylyStatistic(ArrayList<DaylyStatistic> daylyStatistics) {

    }


}

