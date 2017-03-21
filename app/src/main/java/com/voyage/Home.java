package com.voyage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Home extends AppCompatActivity{
    String contact[], name[], address[], rating[], imageLink[];
    private RecyclerView recyclerView;
    private PlaceAdapter adapter ;
    Context context=this;
    ArrayList<PlaceDetail> placeArrayList ;
    LayoutInflater inflater;
    ViewGroup container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        placeArrayList = new ArrayList<>();
        adapter = new PlaceAdapter(Home.this,placeArrayList);
        name = getResources().getStringArray(R.array.place_name);
        address = getResources().getStringArray(R.array.place_sub);
        rating = getResources().getStringArray(R.array.place_rating);
        imageLink = getResources().getStringArray(R.array.place_image);
     //   webLink = getResources().getStringArray(R.array.place_weblink);

        for (int i = 0; i < name.length; i++) {
            PlaceDetail placeDetail = new PlaceDetail(name[i], address[i], imageLink[i], rating[i]);
            placeArrayList.add(placeDetail);
        }
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Home.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new PlaceAdapter.RecyclerTouchListener(Home.this, recyclerView, new PlaceAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               /* Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hotelDetailArrayList.get(position).getLink()));
                startActivity(browserIntent);*/
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}
