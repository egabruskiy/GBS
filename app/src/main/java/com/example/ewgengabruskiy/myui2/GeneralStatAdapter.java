package com.example.ewgengabruskiy.myui2;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ewgengabruskiy.myui2.data.datas.CommonStatistic;
import com.example.ewgengabruskiy.myui2.data.datas.Site;

import java.util.List;

/**
 * Created by ewgengabruskiy on 20.11.17.
 */

public class GeneralStatAdapter extends RecyclerView.Adapter<GeneralStatAdapter.MyViewHolder> {
    private List<CommonStatistic> commonStatisticList;
    private List<Site> sitesList;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView personGeneralStatText, rankGeneralText;

        public MyViewHolder (View view){
            super(view);
            personGeneralStatText = view.findViewById(R.id.person_general_stat);
            rankGeneralText = view.findViewById(R.id.rank_general_stat);

        }
    }

    public GeneralStatAdapter(List<CommonStatistic> commonStatisticList ){
        this.commonStatisticList = commonStatisticList;
    }


    @Override
    public GeneralStatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.general_stat_list_row, parent, false);

        return new GeneralStatAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GeneralStatAdapter.MyViewHolder holder, int position) {
        CommonStatistic commonStatistic = commonStatisticList.get(position);
        holder.personGeneralStatText.setText(commonStatistic.getName());
        holder.rankGeneralText.setText(commonStatistic.getGeneralRank().toString());

    }

    @Override
    public int getItemCount() {
        return commonStatisticList.size();
    }
}



