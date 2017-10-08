package com.lachlannewman.artexhibit.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lachlannewman.artexhibit.R;
import com.lachlannewman.artexhibit.adapters.ExhibitionAdapter;
import com.lachlannewman.artexhibit.models.Exhibition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExibitionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = ExibitionActivity.class.getSimpleName();

    public static final String EXHIBITION = "Exhibtion";

    private Exhibition[] exhibitions;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibition);
        mListView = (ListView) findViewById(R.id.list_item);
        GetExhibtions getExhibtions = new GetExhibtions();
        try {
            String jsonData = getExhibtions.execute().get();
            Log.d(TAG,jsonData);
            parseExhibtions(jsonData);
            ExhibitionAdapter adapter = new ExhibitionAdapter(this,exhibitions);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseExhibtions(String jsonData) throws JSONException {

        Log.d(TAG,jsonData);
        JSONObject json = new JSONObject(jsonData);
        JSONArray jsonExhibitions = json.getJSONArray("Items");
        exhibitions = new Exhibition[jsonExhibitions.length()];
        JSONObject jsonExhibition;
        Exhibition exhibition;
        for(int i = 0; i< exhibitions.length; i++){
            jsonExhibition = jsonExhibitions.getJSONObject(i);
            exhibition = new Exhibition(
                    jsonExhibition.getJSONObject("exhibit_id").getString("S"),
                    jsonExhibition.getJSONObject("title").getString("S"),
                    jsonExhibition.getJSONObject("location").getString("S"),
                    jsonExhibition.getJSONObject("date").getString("S"),
                    jsonExhibition.getJSONObject("time").getString("S")
            );
            exhibitions[i] = exhibition;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Exhibition exhibition = exhibitions[i];
        startMainActivity(exhibition);
    }

    private void startMainActivity(Exhibition exhibition) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(EXHIBITION,exhibition);
        startActivity(intent);
    }


    private class GetExhibtions extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            String url = "https://d6iblsogna.execute-api.us-east-2.amazonaws.com/test/exhibit";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);
            String json =null;
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()){
                    json = response.body().string();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String jsonData){
        }
    }
}
