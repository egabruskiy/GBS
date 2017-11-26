package com.example.ewgengabruskiy.myui2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ewgengabruskiy.myui2.data.datas.Site;
import java.util.List;

/**
 * Created by ewgengabruskiy on 19.11.17.
 */

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.MyViewHolder> {
    private List<Site> sitesList;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView siteText,URLText;

        public MyViewHolder (View view){
            super(view);
            siteText = view.findViewById(R.id.site);
            URLText = view.findViewById(R.id.url);

        }
    }

    public SiteAdapter (List<Site> sitesList){
        this.sitesList = sitesList;
    }




    @Override
    public SiteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.site_list_row, parent, false);

        return new SiteAdapter.MyViewHolder(itemView);

    }



    @Override

    public void onBindViewHolder(SiteAdapter.MyViewHolder holder, int position) {
        Site site = sitesList.get(position);
        holder.siteText.setText(site.getName());

        holder.URLText.setText(site.getUrl().toString());
    }

    @Override
    public int getItemCount() {
        return sitesList.size();
    }
}