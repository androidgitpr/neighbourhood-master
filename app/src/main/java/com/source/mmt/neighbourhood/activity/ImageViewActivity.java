package com.source.mmt.neighbourhood.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.adapter.ImageAdapter;
import com.source.mmt.neighbourhood.model.NeighbourHood;

public class ImageViewActivity extends SlidingMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_image_view, null, false);

        mDrawerLayout.addView(contentView, 0);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("URL");

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        NeighbourHood cubs = NeighbourHood.getInstance();
        ImageAdapter adapter = new ImageAdapter(this, cubs.getCurrentImages());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

/*
		com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(com.nostra13.universalimageloader.core.ImageLoaderConfiguration.createDefault(ImageViewActivity.this));
		Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("URL");
        android.widget.ImageView image = (android.widget.ImageView)findViewById(R.id.list_item_image_1);
        com.source.mmt.util.ImageUtil.displayImage(image, url, null);


        android.widget.TextView share = (android.widget.TextView)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                android.content.Intent sharingIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
                sharingIntent.setType("text/plain");
                startActivity(sharingIntent);

			}
		});

        android.widget.TextView download = (android.widget.TextView)findViewById(R.id.download);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageViewActivity.this, "Image Started Downloading", Toast.LENGTH_LONG).show();
                /*AsyncTask<String, Void, Integer> result =  new DownloadFile().execute(url, "mmt", "mmt");
                try {
                    Integer code = result.get();
                    if(code == 1) {
                        Toast.makeText(ImageViewActivity.this, "Image Download Successful", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ImageViewActivity.this, "Image Download Failed", Toast.LENGTH_LONG).show();
                }
*

                Picasso.with(ImageViewActivity.this)
                        .load(url)
                        .into(new Target() {
                                  @Override
                                  public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                      try {
                                          String root = Environment.getExternalStorageDirectory().toString();
                                          File myDir = new File(root + "/Cubs");

                                          if (!myDir.exists()) {
                                              myDir.mkdirs();
                                          }
                                          System.out.println("shiva download path"+myDir.getAbsolutePath());
                                          String name = new Date().toString() + ".jpg";
                                          myDir = new File(myDir, name);
                                          System.out.println("shiva download path new "+myDir.getAbsolutePath());

                                          FileOutputStream out = new FileOutputStream(myDir);
                                          bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

                                          out.flush();
                                          out.close();
                                      } catch(Exception e){
                                          System.out.println("shiva download failed"+e.getMessage());
                                      }
                                  }

                                  @Override
                                  public void onBitmapFailed(Drawable errorDrawable) {
                                  }

                                  @Override
                                  public void onPrepareLoad(Drawable placeHolderDrawable) {
                                  }
                              }
                        );
            }
        });

*/
    }


}
