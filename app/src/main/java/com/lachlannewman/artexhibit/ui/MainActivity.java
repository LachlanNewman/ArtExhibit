package com.lachlannewman.artexhibit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.lachlannewman.artexhibit.R;
import com.lachlannewman.artexhibit.models.Exhibition;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXHIBIT_ID = "Exhibit_Id";

    private Exhibition exhibtion;
    private TextView artExhibitTitle;
    private TextView artExhibitLocation;
    private TextView artExhibitDateTime;
    private TextView artExhibitDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        exhibtion = intent.getParcelableExtra(ExibitionActivity.EXHIBITION);
        artExhibitTitle = (TextView) findViewById(R.id.artExhibitTitle);
        artExhibitLocation = (TextView) findViewById(R.id.artExhibitLocation);
        artExhibitDateTime = (TextView) findViewById(R.id.artExhibitDateTime);
        artExhibitDesc = (TextView) findViewById(R.id.artExhibitDescription);

        artExhibitTitle.setText(exhibtion.getTitle());
        artExhibitLocation.setText(exhibtion.getLocation());
        artExhibitDateTime.setText(exhibtion.getDate() + " at " + exhibtion.getTime());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
 /*       GetExhibtion exhibtion = new GetExhibtion();
        try {
            String jsonData = exhibtion.execute().get();
            Log.d(TAG,jsonData);
            parseExibtion(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        */
    }

   /* private void parseExibtion(String jsonData) throws JSONException {
        JSONArray json = new JSONObject(jsonData).getJSONArray("items");
        JSONObject jsonExhibit = json.getJSONObject(0);
        exhibtion = new Exhibition();
        exhibtion.setTitle(jsonExhibit.getString("exibition"));
        exhibtion.setDate(jsonExhibit.getString("date"));
        exhibtion.setLocation(jsonExhibit.getString("location"));
*/



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startGalleryActivity();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startGalleryActivity() {
        Intent intent = new Intent(this,GalleryActivity.class);
        intent.putExtra(EXHIBIT_ID,exhibtion.getExhibit_id());
        startActivity(intent);
    }
/*
    private class GetExhibtion extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            String url = "https://artexhibitapplication.appspot.com/_ah/api/exhibitApiApi/v1/exhibitApi";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);
            String json = null;
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String jsonData) {
        }
    } */
}
