package com.source.mmt.neighbourhood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.model.Contactinfo;
import com.source.mmt.neighbourhood.util.ImageUtil;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<Contactinfo> mDummyModelList;

	public ContactAdapter(Context context,
						  ArrayList<Contactinfo> dummyModelList) {
		mContext = context;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDummyModelList = dummyModelList;
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getCount() {
		return mDummyModelList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDummyModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.list_item_special_note, parent, false);
			holder = new ViewHolder();

			holder.name = (TextView) convertView
					.findViewById(R.id.name);
			holder.designation = (TextView) convertView
					.findViewById(R.id.designation);

			holder.call = (TextView) convertView
					.findViewById(R.id.call);

			holder.mobile = (TextView) convertView
					.findViewById(R.id.mobile);
			holder.email = (TextView) convertView
					.findViewById(R.id.email);
			holder.share = (TextView) convertView
					.findViewById(R.id.share);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.call.setTag(position);
		holder.email.setTag(position);
		holder.share.setTag(position);

		holder.call.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ListView)parent).performItemClick(v,position,0);
			}
		});

		holder.email.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ListView)parent).performItemClick(v,position,0);
			}
		});

		holder.share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ListView)parent).performItemClick(v,position,0);
			}
		});

		Contactinfo dm = mDummyModelList.get(position);

		holder.name.setText(dm.getName());
		holder.designation.setText(dm.getRole());
        holder.mobile.setText(dm.getMobile());

		return convertView;
	}

	private static class ViewHolder {
		public/* Roboto */TextView name;
		public/* Roboto */TextView designation;
		public/* Fontello */TextView mobile;
		public/* Roboto */TextView call;
		public/* Roboto */TextView email;
		public/* Roboto */TextView share;

	}
}
