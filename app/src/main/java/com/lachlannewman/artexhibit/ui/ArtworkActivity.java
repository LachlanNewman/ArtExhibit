package com.lachlannewman.artexhibit.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;


import com.lachlannewman.artexhibit.R;
import com.lachlannewman.artexhibit.models.Artwork;

import java.io.InputStream;
import java.net.URL;

public class ArtworkActivity extends AppCompatActivity {

    private Artwork mArtwork;
    private TextView artworkTitle;
    private TextView artworkArtist;
    private TextView artworkStyle;
    private TextView artworkPeriod;
    private TextView forSaleText;
    private TextView artworkCost;
    private ImageView artworkImage;
    private Button purchaseButton;
    private Button directionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);
        Intent intent = getIntent();
        mArtwork = intent.getParcelableExtra(GalleryActivity.ARTWORK);
        artworkTitle = (TextView) findViewById(R.id.artworkTitle);
        artworkArtist = (TextView) findViewById(R.id.artworkArtist);
        artworkStyle = (TextView) findViewById(R.id.artworkStyle);
        artworkPeriod = (TextView) findViewById(R.id.artworkPeriod);
        forSaleText = (TextView) findViewById(R.id.forSaleText);
        artworkCost = (TextView) findViewById(R.id.artworkCost);
        purchaseButton = (Button) findViewById(R.id.purchaseButton);
        artworkImage = (ImageView) findViewById(R.id.artworkImage);
        setView();
    }

    private void setView() {
        artworkTitle.setText(mArtwork.getTitle());
        artworkArtist.setText(mArtwork.getArtist());
        artworkStyle.setText(mArtwork.getArtStyle());
        artworkPeriod.setText(mArtwork.getArtPeriod());
        artworkImage.setImageDrawable(LoadImageFromWebOperations(mArtwork.getImgUrl()));
        if(mArtwork.isForSale()){
            forSaleText.setBackgroundColor(getResources().getColor(R.color.forSale));
            forSaleText.setText("For Sale");
            artworkCost.setText("" + mArtwork.getCost());
        }
        else{
            forSaleText.setVisibility(View.INVISIBLE);
            artworkCost.setVisibility(View.INVISIBLE);
            purchaseButton.setVisibility(View.INVISIBLE);

        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
