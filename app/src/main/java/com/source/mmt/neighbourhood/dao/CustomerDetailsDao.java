package com.source.mmt.neighbourhood.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.util.DbHelper;

public class CustomerDetailsDao {

    SQLiteDatabase db;

    public CustomerDetailsDao() {
        if(DbHelper.getInstance() != null){
            db = DbHelper.getInstance().getWritableDatabase();
            db.execSQL("CREATE TABLE IF NOT EXISTS VendorDetails(usrId INT, aptId INT, mobileNumber VARCHAR(20),email VARCHAR(30),type VARCHAR(30),username VARCHAR(30));");
        }else {
            throw new RuntimeException("DBHelper not initialized");
        }
    }

    public void addVendorDetails(Integer usrId, Integer aptId, String mobileNumber, String email, String type, String username) {
        if( (mobileNumber != null || !mobileNumber.isEmpty())) {
            db.execSQL("INSERT INTO VendorDetails VALUES('"+usrId+"','"+aptId+"','"+mobileNumber+"','"+email+"','"+type+"','"+username+"');");
        }else {
            Log.e("Error", "Invalid vendor details, details not persisted");
        }
    }


    public void deleteVendorDetails(String mobileNumber) {
        System.out.println("shiva delete called "+mobileNumber);
        if((mobileNumber != null && !mobileNumber.isEmpty())) {
            int code = db.delete("VendorDetails", "mobileNumber=?", new String[]{mobileNumber});
            System.out.println("shiva delete called "+code);
        }else {
            Log.e("Error", "Invalid vendor details, details not persisted");
        }
    }

    public void getVendorDetails() {
        Cursor cursor = db.rawQuery("Select * from VendorDetails", new String[]{});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            NeighbourHood nh_inst = NeighbourHood.getInstance();
            nh_inst.getUsrInfo().setUsrId(cursor.getInt(0));
            nh_inst.getUsrInfo().setAptId(cursor.getInt(1));
            nh_inst.getUsrInfo().setMobileNum(cursor.getString(2));
            nh_inst.getUsrInfo().setEmailId(cursor.getString(3));
            nh_inst.getUsrInfo().setType(cursor.getString(4));
            nh_inst.getUsrInfo().setUserName(cursor.getString(5));
        }
        else {
            NeighbourHood nh_inst = NeighbourHood.getInstance();
            nh_inst.getUsrInfo().setUsrId(-1);
        }
    }
}
