package com.voyage;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.voyage.Recommender.MyContext;
import com.voyage.Recommender.RecommenderSystem;
import com.voyage.Recommender.Sites;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class RecommendedPlaces extends AppCompatActivity{
    ArrayList<String>  categ,name,address,rating,imageLink,lat,lan,short_des,long_des,hours,reivewers,phone,site_id;
    private RecyclerView recyclerView;
    private PlaceAdapter adapter ;
    Context context=this;
    ArrayList<PlaceDetail> placeArrayList ;
    ArrayList<Sites> sitesArrayList;
    LayoutInflater inflater;
    HashMap<Integer,Sites> hashMapSites = new HashMap<Integer, Sites>();;
    ViewGroup container;
    RecommenderSystem rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new RecommendedPlaces.JSONTask().execute("http://mybucketlist.me:8000/getSites");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        sitesArrayList = new ArrayList<>();
        rec = new RecommenderSystem();
        try {
            MyContext myContext = setMyContext();
            rec.setContext(myContext);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        name = new ArrayList<String>();
        address = new ArrayList<String>();
        rating = new ArrayList<String>();
        imageLink = new ArrayList<String>();
        categ = new ArrayList<String>();
        lan = new ArrayList<String>();
        lat = new ArrayList<String>();
        short_des = new ArrayList<String>();
        long_des = new ArrayList<String>();
        hours = new ArrayList<String>();
        reivewers = new ArrayList<String>();
        phone = new ArrayList<String>();
        site_id = new ArrayList<String>();

     // webLink = getResources().getStringArray(R.array.place_weblink);*/

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(RecommendedPlaces.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        sitesArrayList = rec.newUserRecommendation();
        adapter = new PlaceAdapter(RecommendedPlaces.this,sitesArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new PlaceAdapter.RecyclerTouchListener(RecommendedPlaces.this, recyclerView, new PlaceAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hotelDetailArrayList.get(position).getLink()));
                startActivity(browserIntent);*/
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                BufferedReader reader = null;
                HttpURLConnection connection = null;

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String json = buffer.toString();
                JsonArray jArray = new JsonParser().parse(json).getAsJsonArray();
                RecommenderSystem rec = new RecommenderSystem();
                for (int i = 0; i < jArray.size(); i++) {
                    Sites s = new Sites();
                    JsonObject j = jArray.get(i).getAsJsonObject();
                    s.setAddress(j.get("Address").getAsString());
                    s.setCategory(j.get("category").getAsString());
                    s.setHours(j.get("Hours").getAsString());
                    s.setLatitude(j.get("lat").getAsDouble());
                    s.setLong_des(j.get("LongDes").getAsString());
                    s.setLongitude(j.get("long").getAsDouble());
                    s.setName(j.get("Name").getAsString());
                    s.setPhone(j.get("Phone").getAsString());
                    s.setRating(j.get("Rating").getAsDouble());
                    s.setReivewers(j.get("Reviewers").getAsInt());
                    s.setShort_des(j.get("SortDes").getAsString());
                    s.setSite_id(j.get("site_id").getAsInt());
                    //System.out.println(s.getSite_id());
                    rec.getSiteList().put(s.getSite_id(), s);

                    System.out.println(s.getName()+" lala "+i);
                }
                /*JSONArray jarray = new JSONArray(finaljson);
                //for(int i =0;)
                int arraylength =jarray.length();

                for(int i=0; i<arraylength;i++)
                {
                    JSONObject jobjects = jarray.getJSONObject(i);
                    Sites s=new Sites();
                    //JSONObject jobjects = jarray.getJSONObject(0);
                    categ.add(jobjects.getString("category"));
                    s.setAddress((String) jobjects.get("Address"));
                    s.setCategory((String) jobjects.get("category"));
                    s.setHours((String) jobjects.get("Hours"));
                    s.setLatitude((Double) jobjects.get("lat"));
                    s.setLong_des((String) jobjects.get("LongDes"));
                    s.setLongitude((Double) jobjects.get("long"));
                    s.setName((String) jobjects.get("Name"));
                    s.setPhone((String) jobjects.get("Phone"));
                 //   s.setRating((Double) jobjects.get("Rating"));
                    s.setReivewers((Integer) jobjects.get("Reviewers"));
                    s.setShort_des((String) jobjects.get("SortDes"));
                    s.setSite_id((Integer) jobjects.get("site_id"));
                    //System.out.println(s.getSite_id());
                    rec.getSiteList().put(s.getSite_id(), s);*/



/*
                   PlaceDetail placeDetail = new PlaceDetail(name.get(i), address.get(i),categ.get(i), rating.get(i),
                            short_des.get(i), long_des.get(i), lan.get(i), lat.get(i),);
                    placeArrayList.add(placeDetail);
*/
                //  hashMapUser.put(name,category+"?"+address+"?"+short_des+"?"+lat+"?"+lan);
                //  System.out.println(hashMapUser.get(email_id));


                return null;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // jsdata.setText(s);
        }
    }
    private MyContext setMyContext() throws ParseException {
        MyContext mContext = new MyContext();
        AppPreferences appPre = new AppPreferences();
        double lat = appPre.getLatitude(RecommendedPlaces.this);
        double lan = appPre.getLongitude(RecommendedPlaces.this);
        String interestPlaces = appPre.getInterestPlaces(RecommendedPlaces.this);
        String[] places = interestPlaces.split(",");
        mContext.setLatitude(lat);
        mContext.setLongitude(lan);
        for(String i:places)
            mContext.getChose_category().add(i);

        return mContext;
    }


}
