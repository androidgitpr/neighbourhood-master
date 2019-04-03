package com.source.mmt.neighbourhood.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.adapter.ImageGallerySubcategoryAdapter;
import com.source.mmt.neighbourhood.model.ImageGallerySubcategoryModel;
import com.source.mmt.neighbourhood.model.NeighbourHood;

import java.util.ArrayList;

public class ImageGallerySubcategoryActivity extends SlidingMenuActivity {

	public static final String IMAGE_GALLERY_SUBCATEGORY = "com.csform.android.uiapptemplate.ImageGallerySubcategoryActivity";
	public static final String IMAGE_GALLERY_LAYOUT_ON_TOP = "com.csform.android.uiapptemplate.ImageGallerySubcategoryActivity.layoutOnTop";
	private ArrayList<ImageGallerySubcategoryModel> mSubcategories;

	private ListView mListView;
	private boolean mIsLayoutOnTop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.list_view);

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View contentView = inflater.inflate(R.layout.image_list_view, null, false);

		mDrawerLayout.addView(contentView, 0);

		mListView = (ListView) findViewById(R.id.list_view);

		if(!isStoragePermissionGranted()) {
			System.out.println("shiva permission successful");
		}

//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.containsKey(IMAGE_GALLERY_SUBCATEGORY)) {
				mSubcategories = extras.getParcelableArrayList(IMAGE_GALLERY_SUBCATEGORY);
			} else {
				mSubcategories = new ArrayList<>();
			}
		/*	if (extras.containsKey(IMAGE_GALLERY_LAYOUT_ON_TOP)) {
				mIsLayoutOnTop = extras.getBoolean(IMAGE_GALLERY_LAYOUT_ON_TOP, false);
			}*/
		}
		setAdapter();
	}



	public  boolean isStoragePermissionGranted() {
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
					== PackageManager.PERMISSION_GRANTED) {
				Log.v("shiva","Permission is granted");
				return true;
			} else {

				Log.v("shiva","Permission is revoked");
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
				return false;
			}
		}
		else { //permission is automatically granted on sdk<23 upon installation
			Log.v("shiva","Permission is granted");
			return true;
		}
	}

	private void setAdapter() {
		boolean addHeaderView = false;//You can request list view header by setting this variable to true
		String title = "";
		NeighbourHood cubs = NeighbourHood.getInstance();

		BaseAdapter adapter = new ImageGallerySubcategoryAdapter(this, mSubcategories, mIsLayoutOnTop);
		View headerView = getLayoutInflater().inflate(R.layout.header_image_gallery, mListView, false);
		((TextView) headerView.findViewById(R.id.header_title)).setText("");
		mListView.addHeaderView(headerView);

		mListView.setAdapter(adapter);
	}

}
