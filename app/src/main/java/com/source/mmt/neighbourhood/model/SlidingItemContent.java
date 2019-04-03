package com.source.mmt.neighbourhood.model;

import com.source.mmt.neighbourhood.R;

import java.util.ArrayList;
import java.util.Random;

public class SlidingItemContent {

	
	//TODO Change to media
	public static ArrayList<SlidingItemModel> getDrawerMediaDummyList() {
		ArrayList<SlidingItemModel> list = new ArrayList<>();
		list.add(new SlidingItemModel(0, "", "Home", R.string.material_icon_home));
		list.add(new SlidingItemModel(1, "", "Notification", R.string.material_icon_home));
		list.add(new SlidingItemModel(1, "", "MyProfile", R.string.material_icon_home));
		list.add(new SlidingItemModel(2, "", "Settings", R.string.material_icon_home));
		list.add(new SlidingItemModel(3, "", "Logout", R.string.material_icon_home));
        list.add(new SlidingItemModel(5, "", "AdminPage", R.string.material_icon_account));
		list.add(new SlidingItemModel(5, "", "Vendor Page", R.string.material_icon_home));
        list.add(new SlidingItemModel(6, "", "Share", R.string.material_icon_home));
        list.add(new SlidingItemModel(7, "", "Contact Us", R.string.material_icon_home));
        list.add(new SlidingItemModel(8, "", "How It Works", R.string.material_icon_home));
		return list;
	}

}
