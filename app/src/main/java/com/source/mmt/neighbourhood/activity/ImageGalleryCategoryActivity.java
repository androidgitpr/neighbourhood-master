package com.source.mmt.neighbourhood.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.adapter.ImageGalleryCategoryAdapter;
import com.source.mmt.neighbourhood.model.ImageGalleryCategoryModel;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.ImageGallery;

import java.util.Collections;

public class ImageGalleryCategoryActivity extends SlidingMenuActivity {

	private Integer key;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View contentView = inflater.inflate(R.layout.image_list_view, null, false);

		mDrawerLayout.addView(contentView, 0);

		NeighbourHood Nb = NeighbourHood.getInstance();
		if(Nb.getAcademicImages().size() == 0 || Nb.getDailyImages().size() == 0) {
			AsyncTask<String, Void, Integer> result = new ImageGallery().execute();
			try {
				Integer code = result.get();

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(ImageGalleryCategoryActivity.this, "Failed get Images", Toast.LENGTH_LONG).show();
			}
		}


        mListView = (ListView) findViewById(R.id.list_view);

		Intent mIntent = getIntent();
		key = mIntent.getIntExtra("Key", 0);
		setAdapter();
	}

	private void setAdapter() {
		BaseAdapter adapter = null;
		String title = "";
		NeighbourHood Nb = NeighbourHood.getInstance();
		System.out.println("shiva key"+key);
		if(key == 0) {

			Collections.sort(Nb.getAcademicImages());
			adapter = new ImageGalleryCategoryAdapter(this, Nb.getAcademicImages(), false);
		}
		else {
			for(int i = 0; i < Nb.getDailyImages().size(); i++) {
				ImageGalleryCategoryModel item = Nb.getDailyImages().get(i);
				for(int j = 0; j < item.getSubcategories().size(); j++) {
					//item.getSubcategories().get(j).setIsDownload(true);
				}
			}
			Collections.sort(Nb.getDailyImages());
			adapter = new ImageGalleryCategoryAdapter(this, Nb.getDailyImages(), false);
		}
			View headerView = getLayoutInflater().inflate(R.layout.header_image_gallery, mListView, false);
			((TextView) headerView.findViewById(R.id.header_title)).setText("");
			mListView.addHeaderView(headerView);

		mListView.setAdapter(adapter);
	}
}
