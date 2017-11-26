package com.example.ewgengabruskiy.myui2;

import android.app.DatePickerDialog;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ewgengabruskiy.myui2.data.DataManager;
import com.example.ewgengabruskiy.myui2.data.DataManagerListener;
import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Person;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * Created by ewgengabruskiy on 11.11.17.
 */

public class DailyStatFragment extends Fragment implements DataManagerListener,View.OnClickListener {



        private RecyclerView recyclerViewDaily;
        private DailyStatAdapter mAdapter;
        private List<DaylyStatistic> statisticList = new ArrayList<>();
        private  List<Site> siteList = new ArrayList<>();
        private List<String> someList = new ArrayList<>();
        private  Calendar dateFrom= Calendar.getInstance();
        private  Calendar dateTo= Calendar.getInstance();
        private   Button from;
        private   Button to;
        private   Button update;
        private  ViewGroup viewGroup;
        private  ArrayAdapter dataAdapter;
        private  DataManager dataManager = new DataManager(DailyStatFragment.this);
        private  int pos;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_daily_stat, container, false);



                from = (Button) rootView.findViewById(R.id.dateButton);
                to = (Button) rootView.findViewById(R.id.dateButtonto);
                from.setOnClickListener(this);
                to.setOnClickListener(this);

                viewGroup = (ViewGroup) rootView.findViewById(R.id.informview);
                viewGroup.setVisibility(View.GONE);

                update = (Button) rootView.findViewById(R.id.update);
                update.setOnClickListener(this);


                recyclerViewDaily = rootView.findViewById(R.id.recycler_view_daily);
                mAdapter = new DailyStatAdapter(statisticList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerViewDaily.setLayoutManager(mLayoutManager);
                recyclerViewDaily.setAdapter(mAdapter);

                dataManager.getSiteList(false);


                Spinner spinner = rootView.findViewById(R.id.spinner_daily);
                dataAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, someList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                //Spinner
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                if (viewGroup.getVisibility() == View.GONE) viewGroup.setVisibility(View.VISIBLE);

                                pos = siteList.get(position).getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {


                        }
                });

                return rootView;
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



        }

        @Override
        public void updateDaylyStatistic(ArrayList<DaylyStatistic> daylyStatistics) {

                for(DaylyStatistic s: daylyStatistics){
                        Log.d("update",s.getName());
                }
                statisticList.clear();
                statisticList.addAll(daylyStatistics);
                mAdapter.notifyDataSetChanged();


        }

        @Override
        public void onClick(View view) {
                if (view == from || view == to) setDate(view);
                else if (view == update) updateStatistic();
        }

        private void updateStatistic() {
                viewGroup.setVisibility(View.GONE);
                dataManager.getDaylyStatistic(pos,from.getText().toString(),to.getText().toString());
//
                System.out.println("from " + from.getText().toString());
                System.out.println("to " + to.getText().toString());
                System.out.println("id " + String.valueOf(pos));

        }


        public void setDate(View v) {
                if (v == from) {
                        new DatePickerDialog(getActivity(), d,
                                dateFrom.get(Calendar.YEAR),
                                dateFrom.get(Calendar.MONTH),
                                dateFrom.get(Calendar.DAY_OF_MONTH))
                                .show();
                } if (v == to) {
                        new DatePickerDialog(getActivity(), d1,
                                dateTo.get(Calendar.YEAR),
                                dateTo.get(Calendar.MONTH),
                                dateTo.get(Calendar.DAY_OF_MONTH))
                                .show();
                }
        }


        DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //тут можем получить выбранный год-месяц-день из datePicker
                        //System.out.println(year + "-" + monthOfYear + "-" + dayOfMonth);
                        String s = year + "-" + monthOfYear + "-" + dayOfMonth;
                        from.setText(s);
                        dateFrom.set(Calendar.YEAR, year);
                        dateFrom.set(Calendar.MONTH, monthOfYear);
                        dateFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        if (viewGroup.getVisibility() == View.GONE) viewGroup.setVisibility(View.VISIBLE);

                }
        };


        DatePickerDialog.OnDateSetListener d1=new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //тут можем получить выбранный год-месяц-день из datePicker
                        System.out.println(year + "-" + monthOfYear + "-" + dayOfMonth);
                        String s = year + "-" + monthOfYear + "-" + dayOfMonth;
                        to.setText(s);
                        dateTo.set(Calendar.YEAR, year);
                        dateTo.set(Calendar.MONTH, monthOfYear);
                        dateTo.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        if (viewGroup.getVisibility() == View.GONE) viewGroup.setVisibility(View.VISIBLE);
                }
        };



}