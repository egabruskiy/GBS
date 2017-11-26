package com.example.ewgengabruskiy.myui2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.DaylyStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.List;

/**
 * Created by ewgengabruskiy on 21.11.17.
 */

public class DailyStatAdapter extends RecyclerView.Adapter<DailyStatAdapter.MyViewHolder> {
    private List<DaylyStatistic> commonStatisticList;
    private List<Site> sitesList;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView personDailyStatText, rankDailyText;

        public MyViewHolder (View view){
            super(view);
            personDailyStatText = view.findViewById(R.id.person_daily_stat);
            rankDailyText = view.findViewById(R.id.rank_daily_stat);

        }
    }

    public DailyStatAdapter(List<DaylyStatistic> commonStatisticList ){
        this.commonStatisticList = commonStatisticList;
    }


    @Override
    public DailyStatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_stat_row, parent, false);

        return new DailyStatAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DailyStatAdapter.MyViewHolder holder, int position) {
        DaylyStatistic daylyStatistic = commonStatisticList.get(position);
//        Site site = sitesList.get(position);
        holder.personDailyStatText.setText(daylyStatistic.getName());
        holder.rankDailyText.setText(daylyStatistic.getDailyRank().toString());

    }

    @Override
    public int getItemCount() {
        return commonStatisticList.size();
    }
}
