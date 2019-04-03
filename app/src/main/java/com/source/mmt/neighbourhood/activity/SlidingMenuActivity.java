package com.source.mmt.neighbourhood.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.adapter.DrawerMediaAdapter;
import com.source.mmt.neighbourhood.dao.CustomerDetailsDao;
import com.source.mmt.neighbourhood.font.RobotoTextView;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.util.DbHelper;

public class SlidingMenuActivity extends AppCompatActivity {

	private ListView mDrawerList;
	protected DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sliding);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Neighbourhood");
//		setSupportActionBar(toolbar);

		//getSupportActionBar().setTitle("Vadna");
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mTitle = mDrawerTitle = getTitle();
		mDrawerList = (ListView) findViewById(R.id.list_view);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		View headerView = getLayoutInflater().inflate(
				R.layout.header_navigation_drawer_media, mDrawerList, false);


		RobotoTextView name = (RobotoTextView)headerView.findViewById(R.id.header_navigation_drawer_media_username);
		RobotoTextView mobile = (RobotoTextView)headerView.findViewById(R.id.header_navigation_drawer_media_email);
		//System.out.println("shiva username "+icp.getUserName());
        //name.setText(icp.getUserName());
		//System.out.println("shiva mobile "+icp.getUserMobile());
        //mobile.setText(icp.getUserMobile());
		mDrawerList.addHeaderView(headerView);// Add header before adapter (for
												// pre-KitKat)
		mDrawerList.setAdapter(new DrawerMediaAdapter(this));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerList
				.setBackgroundResource(R.drawable.background_media);
		mDrawerList.getLayoutParams().width = (int) getResources()
				.getDimension(R.dimen.drawer_width_media);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
				R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
			//	getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
//				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.addDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {


			switch (position) {
				case 1:

					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;

				case 5:
					NeighbourHood nh_inst = NeighbourHood.getInstance();
					DbHelper.createVendorDbHelper(SlidingMenuActivity.this);
					CustomerDetailsDao vendorDetailsDao = new CustomerDetailsDao();
					vendorDetailsDao.deleteVendorDetails(nh_inst.getUsrInfo().getMobileNum());

					nh_inst.getUsrInfo().setUsrId(-1);
					Intent intent = new Intent(SlidingMenuActivity.this, UserLoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
					break;
				case 6:
					break;

				case 7:
					break;

				case 8:
					break;

				case 9:
					break;
			}
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	@Override
	public void setTitle(int titleId) {
		setTitle(getString(titleId));
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		//System.out.println("shiva "+title);
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
