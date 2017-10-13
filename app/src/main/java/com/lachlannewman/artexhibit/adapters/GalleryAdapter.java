package com.lachlannewman.artexhibit.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lachlannewman.artexhibit.R;
import com.lachlannewman.artexhibit.models.Artwork;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lachlan Newman on 4/10/2017.
 */

public class GalleryAdapter extends BaseAdapter {

    private Artwork[] mArtworks;
    private Context mContext;

    public GalleryAdapter(Context context,Artwork[] artworks){
        this.mContext = context;
        this.mArtworks = artworks;}

    @Override
    public int getCount() {
        return mArtworks.length;
    }

    @Override
    public Object getItem(int i) {
        return mArtworks[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GalleryViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.gallery_item,null);
            viewHolder = new GalleryViewHolder();
            viewHolder.artworkImg = (ImageView) view.findViewById(R.id.galleryImg);
            viewHolder.title = (TextView) view.findViewById(R.id.galleryTitle);
            viewHolder.artist = (TextView) view.findViewById(R.id.galleryArtist);

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (GalleryViewHolder) view.getTag();
        }
        Artwork artwork = mArtworks[i];
        Picasso.with(mContext).load(artwork.getImgUrl()).into(viewHolder.artworkImg);
        viewHolder.title.setText(artwork.getTitle());
        viewHolder.artist.setText(artwork.getArtist());

        return view;
    }


    public static class GalleryViewHolder{

        ImageView artworkImg;
        TextView title;
        TextView artist;

    }
}
