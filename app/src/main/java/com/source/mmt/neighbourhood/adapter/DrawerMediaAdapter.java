package com.source.mmt.neighbourhood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.model.SlidingItemContent;
import com.source.mmt.neighbourhood.model.SlidingItemModel;

import java.util.List;

public class DrawerMediaAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<SlidingItemModel> mDrawerItems;
	private LayoutInflater mInflater;

	public DrawerMediaAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDrawerItems = SlidingItemContent.getDrawerMediaDummyList();
		mContext = context;
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));
	}

	@Override
	public int getCount() {
		return mDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mDrawerItems.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.list_view_item_navigation_drawer_media, parent,
					false);
			holder = new ViewHolder();
			holder.icon = (TextView) convertView
					.findViewById(R.id.list_item_navigation_drawer_media_icon);
			holder.title = (TextView) convertView.findViewById(R.id.list_item_navigation_drawer_media_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		SlidingItemModel item = mDrawerItems.get(position);
		holder.icon.setText(mContext.getString(item.getIconRes()));
		holder.title.setText(item.getText());

		return convertView;
	}

	private static class ViewHolder {
		public TextView icon;
		public/* Roboto */TextView title;
	}
}
