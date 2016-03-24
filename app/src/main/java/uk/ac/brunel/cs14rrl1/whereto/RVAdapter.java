package uk.ac.brunel.cs14rrl1.whereto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SiteListHolder> {
    private List<Site> siteItemList;
    private Context mContext;

    public static class SiteListHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected TextView map;

        public SiteListHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.image);
            this.textView = (TextView) view.findViewById(R.id.name);
            this.map = (TextView) view.findViewById(R.id.showMap);
        }
    }

    public RVAdapter(Context context, List<Site> siteItemList) {
        this.siteItemList = siteItemList;
        this.mContext = context;
    }

    @Override
    public SiteListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sitetest,viewGroup,false);

        SiteListHolder viewHolder = new SiteListHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SiteListHolder customViewHolder, int i) {
        //Site site = new Site();
        final Site site = siteItemList.get(i);

        //Set the site image
        customViewHolder.imageView.setImageBitmap(site.getImage());

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(site.getName()));

        customViewHolder.map.setOnClickListener(clickListener);

        customViewHolder.map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LatLng ords = new LatLng(site.getLat(), site.getLong());
                Bundle cood = new Bundle();
                cood.putParcelable("ords",ords);
                cood.putString("name", site.getName());

                Intent i = new Intent(view.getContext(),maps.class);
                i.putExtra("bundle", cood);
                view.getContext().startActivity(i);

            }
        });

        customViewHolder.textView.setTag(customViewHolder);
        customViewHolder.imageView.setTag(customViewHolder);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Site site = new Site();
            Toast.makeText(mContext, site.getName(), Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    public int getItemCount() {
        return (null != siteItemList ? siteItemList.size() : 0);
    }
}