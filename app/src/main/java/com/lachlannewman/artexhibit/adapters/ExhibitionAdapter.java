package com.lachlannewman.artexhibit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lachlannewman.artexhibit.R;
import com.lachlannewman.artexhibit.models.Exhibition;

/**
 * Created by Lachlan Newman on 5/10/2017.
 */

public class ExhibitionAdapter extends BaseAdapter{

    private Exhibition[] mExhibitions;
    private Context mContext;

    public ExhibitionAdapter(Context context,Exhibition[] exhibitions) {
        mExhibitions = exhibitions;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mExhibitions.length;
    }

    @Override
    public Object getItem(int i) {
        return mExhibitions[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ExhibitionViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.exhibtion_item,null);
            viewHolder = new ExhibitionViewHolder();
            viewHolder.exhibtionTitle = (TextView) view.findViewById(R.id.exhibtionTitle);
            viewHolder.exhibtionLocation = (TextView) view.findViewById(R.id.exhibtionLocation);
            viewHolder.exhibitionDateTime = (TextView) view.findViewById(R.id.exhibtionDateTime);

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ExhibitionViewHolder) view.getTag();
        }

        Exhibition exhibition = mExhibitions[i];
        viewHolder.exhibtionTitle.setText(exhibition.getTitle());
        viewHolder.exhibtionLocation.setText(exhibition.getLocation());
        viewHolder.exhibitionDateTime.setText(exhibition.getDate() + " at " + exhibition.getTime());

        return view;
    }

    public static class ExhibitionViewHolder{

        TextView exhibtionTitle;
        TextView exhibtionLocation;
        TextView exhibitionDateTime;
    }
}
