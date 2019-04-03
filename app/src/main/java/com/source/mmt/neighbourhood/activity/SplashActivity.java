package com.source.mmt.neighbourhood.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.dao.CustomerDetailsDao;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.UserRegister;
import com.source.mmt.neighbourhood.util.DbHelper;

public class SplashActivity extends Activity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);


        DbHelper.createVendorDbHelper(this);
        CustomerDetailsDao vendorDetailsDao = new CustomerDetailsDao();
        vendorDetailsDao.getVendorDetails();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NeighbourHood nh_inst = NeighbourHood.getInstance();
                if(nh_inst.getUsrInfo().getUsrId() == -1) {

                    Intent intent = new Intent(SplashActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, HomeScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }

}
