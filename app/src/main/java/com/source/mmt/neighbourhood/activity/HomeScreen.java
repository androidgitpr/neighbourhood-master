package com.source.mmt.neighbourhood.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.adapter.HomeFeatureAdapter;
import com.source.mmt.neighbourhood.customcalendar.CalenderActivity;
import com.source.mmt.neighbourhood.dummy.DirectoryHome7Repository;
import com.source.mmt.neighbourhood.model.HomeFeatures;

import java.util.ArrayList;

public class HomeScreen extends SlidingMenuActivity {

    private ArrayList<HomeFeatures> catArrayList;
    private HomeFeatureAdapter mAdapter;
    private RecyclerView recyclerView;
    int numberOfColumns = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.home_features);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.home_features, null, false);

        mDrawerLayout.addView(contentView, 0);

        initData();

        initUI();

        initDataBinding();

        initActions();
    }


    //region Init Functions
    private void initData()
    {

        catArrayList = DirectoryHome7Repository.getCategoryList();

    }

    private void initUI() {
        mAdapter = new HomeFeatureAdapter(catArrayList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initDataBinding() {
        // get data and adapter
        recyclerView.setAdapter(mAdapter);
    }

    private void initActions()
    {

        mAdapter.setOnItemClickListener((view, obj, position) -> {
            //System.out.println("shiva clicked pos "+position);
            switch (position) {
                case 3:
                    Intent intent = new Intent(HomeScreen.this, RaiseTicketActivity.class);
                    startActivity(intent);
                    break;

                case 4:
                    intent = new Intent(HomeScreen.this, CalenderActivity.class);
                    startActivity(intent);
                    break;

                case 5:
                    intent = new Intent(HomeScreen.this, PollActivityNew.class);
                    startActivity(intent);
                    break;
                case 6:
                    intent = new Intent(HomeScreen.this, ImageGalleryCategoryActivity.class);
                    startActivity(intent);
                    break;

                case 8:
                    intent = new Intent(HomeScreen.this, ContactsActivity.class);
                    startActivity(intent);
                    break;

                case 10:
                    intent = new Intent(HomeScreen.this, SubmitApplicationActivity.class);
                    startActivity(intent);
                    break;

                case 11:
                    intent = new Intent(HomeScreen.this, DiscussionListViewActivity.class);
                    startActivity(intent);
                    break;
            }
        });

    }
}
