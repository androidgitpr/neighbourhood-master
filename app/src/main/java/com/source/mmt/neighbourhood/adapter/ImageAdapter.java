package com.source.mmt.neighbourhood.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.source.mmt.neighbourhood.model.ImageGallerySubcategoryModel;
import com.source.mmt.neighbourhood.util.ImageUtil;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context context;
    ArrayList<ImageGallerySubcategoryModel> ImageList;
    public ImageAdapter(Context context, ArrayList<ImageGallerySubcategoryModel> currentImages){
        this.context=context;

        this.ImageList = currentImages;
    }
    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
       // int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        //imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(com.nostra13.universalimageloader.core.ImageLoaderConfiguration.createDefault(context));

        ImageUtil.displayImage(imageView, ImageList.get(position).getUrl(), null);

        //imageView.setImageResource(GalImages[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
