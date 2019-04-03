package com.source.mmt.neighbourhood.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.activity.ImageViewActivity;
import com.source.mmt.neighbourhood.model.ImageGallerySubcategoryModel;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Date;

public class ImageGallerySubcategoryAdapter extends BaseAdapter implements View.OnClickListener {

    private static final int TYPE_ONE_COLUMN = 0;
    private static final int TYPE_TWO_COLUMNS = 1;
    private static final int TYPE_MAX_COUNT = TYPE_TWO_COLUMNS + 1;
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<ImageGallerySubcategoryModel> mImageGallerySubcategories;
	private final boolean mIsLayoutOnTop;
	
	public ImageGallerySubcategoryAdapter(Context context,
                                          ArrayList<ImageGallerySubcategoryModel> imageGallerySubcategories, boolean isLayoutOnTop) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageGallerySubcategories = imageGallerySubcategories;
		mIsLayoutOnTop = isLayoutOnTop;
		mContext = context;
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(com.nostra13.universalimageloader.core.ImageLoaderConfiguration.createDefault(mContext));
	}
	
    @Override
    public int getItemViewType(int position) {
    	if ((position == mImageGallerySubcategories.size() / 2)
    			&& (mImageGallerySubcategories.size() % 2 == 1)) {
    		return TYPE_ONE_COLUMN;
    	} else {
    		return TYPE_TWO_COLUMNS;
    	}
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

	@Override
	public int getCount() {
		return (mImageGallerySubcategories.size() / 2) + (mImageGallerySubcategories.size() % 2);
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder.OneColumnViewHolder oneColumnViewHolder;
		final ViewHolder.TwoColumnsViewHolder twoColumnsViewHolder;
		int type = getItemViewType(position);
		if (type == TYPE_ONE_COLUMN) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.subcat_one_column, parent, false);
				oneColumnViewHolder = new ViewHolder.OneColumnViewHolder();
				oneColumnViewHolder.image1 = (ImageView) convertView.findViewById(R.id.list_item_image_1);
				oneColumnViewHolder.download = (TextView) convertView.findViewById(R.id.download);
				oneColumnViewHolder.share = (TextView) convertView.findViewById(R.id.share);
				oneColumnViewHolder.layoutTopBottom1 = (ViewGroup) convertView.findViewById(R.id.layout_top_bottom_1);
				oneColumnViewHolder.image1.setOnClickListener(this);
				oneColumnViewHolder.share.setOnClickListener(this);
				oneColumnViewHolder.download.setOnClickListener(this);
				convertView.setTag(oneColumnViewHolder);
			} else {
				oneColumnViewHolder = (ViewHolder.OneColumnViewHolder) convertView.getTag();
			}
			ImageGallerySubcategoryModel model1 = mImageGallerySubcategories.get(position * 2);

			oneColumnViewHolder.layoutTopBottom1.setVisibility(View.VISIBLE);
			if (!model1.getTitle().isEmpty() ) {
				System.out.println("shiva download "+model1.getTitle().length() + "image name "+model1.getTitle());
				oneColumnViewHolder.layoutTopBottom1.setVisibility(View.GONE);
			}
			ImageUtil.displayImage(oneColumnViewHolder.image1, model1.getUrl(), null);
			//Glide.with(mContext).load(model1.getUrl()).into(oneColumnViewHolder.image1);
			//Picasso.with(mContext).load(model1.getUrl()).into(oneColumnViewHolder.image1);
			oneColumnViewHolder.image1.setTag(position);
			oneColumnViewHolder.share.setTag(position);
			oneColumnViewHolder.download.setTag(position);
			Typeface sMaterialDesignIcons = Typeface.createFromAsset(mContext.getAssets(), "fonts/fa-solid-900.ttf");
			oneColumnViewHolder.share.setTypeface(sMaterialDesignIcons);
			oneColumnViewHolder.download.setTypeface(sMaterialDesignIcons);

			LayoutParams lp1 = (LayoutParams) oneColumnViewHolder.layoutTopBottom1.getLayoutParams();
			if (!mIsLayoutOnTop) {
				lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			} else {
				lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
			}
		} else if (type == TYPE_TWO_COLUMNS) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.subcat_colum_two, parent, false);
				twoColumnsViewHolder = new ViewHolder.TwoColumnsViewHolder();
				twoColumnsViewHolder.image1 = (ImageView) convertView.findViewById(R.id.list_item_image_1);
				//twoColumnsViewHolder.favoriteImage1 = (TextView) convertView.findViewById(R.id.list_item_favourite_1);
				twoColumnsViewHolder.download1 = (TextView) convertView.findViewById(R.id.download1);
				twoColumnsViewHolder.share1 = (TextView) convertView.findViewById(R.id.share1);
				twoColumnsViewHolder.layoutTopBottom1 = (ViewGroup) convertView.findViewById(R.id.layout_top_bottom_1);
				twoColumnsViewHolder.image2 = (ImageView) convertView.findViewById(R.id.list_item_image_2);
				twoColumnsViewHolder.download2 = (TextView) convertView.findViewById(R.id.download2);
				twoColumnsViewHolder.share2 = (TextView) convertView.findViewById(R.id.share2);
				twoColumnsViewHolder.layoutTopBottom2 = (ViewGroup) convertView.findViewById(R.id.layout_top_bottom_2);
				convertView.setTag(twoColumnsViewHolder);
			} else {
				twoColumnsViewHolder = (ViewHolder.TwoColumnsViewHolder) convertView.getTag();
			}
			
			ImageGallerySubcategoryModel model1 = mImageGallerySubcategories.get(position * 2);
			twoColumnsViewHolder.layoutTopBottom1.setVisibility(View.VISIBLE);
			if (!model1.getTitle().isEmpty()) {
				System.out.println("shiva download 1"+model1.getIsDownload() + "title"+model1.getTitle());
				twoColumnsViewHolder.layoutTopBottom1.setVisibility(View.GONE);
			}
			ImageUtil.displayImage(twoColumnsViewHolder.image1, model1.getUrl(), null);

			//Glide.with(mContext).load(model1.getUrl()).into(twoColumnsViewHolder.image1);
			//Picasso.with(mContext).load(model1.getUrl()).into(twoColumnsViewHolder.image1);
			ImageGallerySubcategoryModel model2 = mImageGallerySubcategories.get(position * 2 + 1);
			//System.out.println("shiva download 2 "+model2.isDownload()+" url "+model2.getUrl());
			twoColumnsViewHolder.layoutTopBottom2.setVisibility(View.VISIBLE);
			if (!model2.getTitle().isEmpty()) {
				System.out.println("shiva download 2 "+model2.getIsDownload());
				twoColumnsViewHolder.layoutTopBottom2.setVisibility(View.GONE);
			}
			ImageUtil.displayImage(twoColumnsViewHolder.image2, model2.getUrl(), null);

			//Glide.with(mContext).load(model2.getUrl()).into(twoColumnsViewHolder.image2);
			//Picasso.with(mContext).load(model2.getUrl()).into(twoColumnsViewHolder.image2);
			//Picasso .with(mContext) .load(UsageExampleListViewAdapter.eatFoodyImages[0]) .rotate(90f) .into(imageViewSimpleRotate);
			twoColumnsViewHolder.image1.setTag(position);
			twoColumnsViewHolder.image2.setTag(position);
			twoColumnsViewHolder.image1.setOnClickListener(this);
			twoColumnsViewHolder.image2.setOnClickListener(this);

			twoColumnsViewHolder.share1.setTag(position);
			twoColumnsViewHolder.share2.setTag(position);

			twoColumnsViewHolder.download1.setTag(position);
			twoColumnsViewHolder.download2.setTag(position);

			twoColumnsViewHolder.share1.setOnClickListener(this);
			twoColumnsViewHolder.share2.setOnClickListener(this);

			twoColumnsViewHolder.download1.setOnClickListener(this);
			twoColumnsViewHolder.download2.setOnClickListener(this);

			//twoColumnsViewHolder.numberOfImages1.setVisibility(View.GONE);
			Typeface sMaterialDesignIcons = Typeface.createFromAsset(mContext.getAssets(), "fonts/fa-solid-900.ttf");

			twoColumnsViewHolder.share2.setTypeface(sMaterialDesignIcons);
			twoColumnsViewHolder.share1.setTypeface(sMaterialDesignIcons);
			twoColumnsViewHolder.download1.setTypeface(sMaterialDesignIcons);
			twoColumnsViewHolder.download2.setTypeface(sMaterialDesignIcons);
			//twoColumnsViewHolder.numberOfImages2.setVisibility(View.GONE);
			LayoutParams lp1 = (LayoutParams) twoColumnsViewHolder.layoutTopBottom1.getLayoutParams();
			LayoutParams lp2 = (LayoutParams) twoColumnsViewHolder.layoutTopBottom2.getLayoutParams();
			if (!mIsLayoutOnTop) {
				lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
				lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			} else {
				lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
				lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
			}
			/*if (model1.isDownload() == false) {
				System.out.println("shiva download 1"+model1.isDownload() + "title"+model1.getTitle());
				twoColumnsViewHolder.layoutTopBottom1.setVisibility(View.GONE);
			}

			if (model2.isDownload() == false) {
				System.out.println("shiva download 1"+model1.isDownload() + "title"+model1.getTitle());
				twoColumnsViewHolder.layoutTopBottom2.setVisibility(View.GONE);
			}*/

		}
		return convertView;
	}
	
	/* We are not using favourite image here. If you really want to use it,
	 * remove commented lines related to favourite image. 
	 */
	private static class ViewHolder {
		public static class OneColumnViewHolder {
			public ImageView image1;
			//public TextView favoriteImage1;
			public TextView download;
			public TextView share;
			public ViewGroup layoutTopBottom1;
		}
		
		private static class TwoColumnsViewHolder {
			public ImageView image1;
			//public TextView favoriteImage1;
			public TextView download1;
			public TextView share1;
			public ViewGroup layoutTopBottom1;

			public ImageView image2;
			//public TextView favoriteImage2;
			public TextView download2;
			public TextView share2;
			public ViewGroup layoutTopBottom2;
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		int position = (int) v.getTag();
		NeighbourHood cubs = NeighbourHood.getInstance();
//System.out.println("shiva pos "+position);
		if (id == R.id.list_item_image_1) {
		    System.out.println("shiva pos old"+position);
			Intent intent = new Intent(mContext, ImageViewActivity.class);
			cubs.setCurrentImages(mImageGallerySubcategories);
            //intent.putExtra("URL", mImageGallerySubcategories.get(position * 2).getUrl());
			intent.putExtra("URL", (position * 2));
			mContext.startActivity(intent);
		}
		else if (id == R.id.list_item_image_2) {
		    System.out.println("shiva pos even"+position);
			Intent intent = new Intent(mContext, ImageViewActivity.class);
			cubs.setCurrentImages(mImageGallerySubcategories);
            //intent.putExtra("URL", mImageGallerySubcategories.get(position * 2 + 1).getUrl());
			intent.putExtra("URL",(position * 2 + 1));
			mContext.startActivity(intent);
		}

		else if(id == R.id.share || id == R.id.share1) {
			android.content.Intent sharingIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);
			String url = mImageGallerySubcategories.get(position * 2).getUrl();
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
			sharingIntent.setType("text/plain");
			mContext.startActivity(sharingIntent);
		}
        else if(id == R.id.share2) {
            android.content.Intent sharingIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);
            String url = mImageGallerySubcategories.get(position * 2 + 1).getUrl();
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
            sharingIntent.setType("text/plain");
            mContext.startActivity(sharingIntent);
        }
	}
}
