package com.lachlannewman.artexhibit;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lachlannewman.artexhibit.adapters.GalleryAdapter;
import com.lachlannewman.artexhibit.models.Artwork;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by ozojiegerald on 10/17/17.
 */

public class GalleryAdpaterTest extends AndroidTestCase {
    Artwork art1;
    Artwork art2;

    GalleryAdapter adapter;
    public void setUp(){
        String url = "http://stuff.com";

        Artwork art1 = new Artwork("photo1", "emma", "medieval", "renaissance", 0, true, 3.45, url);
        Artwork art2 = new Artwork("photo2", "emma", "medieval", "renaissance", 0, true, 3.45, url);

        Artwork[] artworks = {art1, art2};

        adapter = new GalleryAdapter(getContext(), artworks);
    }

    public void testGetItem(){
        assertEquals("photo1 expected", art1.getTitle(), ((Artwork)(adapter.getItem(0))).getTitle());
    }

    public void testGetItemId(){
        assertEquals("Wrong ID.", 0, adapter.getItemId(0));
    }

    public void testGetCount(){
        assertEquals("Contacts amount incorrect.", 2, adapter.getCount());
    }

    public void testGetView() {
        View view = adapter.getView(0, null, null);

        TextView title = (TextView) view
                .findViewById(R.id.galleryTitle);

        TextView artist = (TextView) view
                .findViewById(R.id.galleryArtist);

        ImageView photo = (ImageView) view
                .findViewById(R.id.galleryImg);

        //On this part you will have to test it with your own views/data
        assertNotNull("View is null. ", view);
        assertNotNull("Title TextView is null. ", title);
        assertNotNull("Artist TextView is null. ", artist);
        assertNotNull("Photo ImageView is null. ", photo);

        assertEquals("Title doesn't match.", art1.getTitle(), title.getText());
        assertEquals("Artist doesn't match.", art1.getArtist(),
                artist.getText());
    }

}
