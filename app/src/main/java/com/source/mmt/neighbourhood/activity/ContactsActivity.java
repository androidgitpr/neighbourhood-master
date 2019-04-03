package com.source.mmt.neighbourhood.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.adapter.ContactAdapter;
import com.source.mmt.neighbourhood.model.Contactinfo;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.GetContacts;

public class ContactsActivity extends SlidingMenuActivity {

	private ListView mDynamicListView;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_special_note_views);

		NeighbourHood nhInst = NeighbourHood.getInstance();

		if(nhInst.getContactDetail().size() == 0) {
			AsyncTask<String, Void, Integer> result = new GetContacts().execute(String.valueOf(nhInst.getUsrInfo().getAptId()));
			try {
				Integer code = result.get();
				if(code != 1)
					Toast.makeText(ContactsActivity.this, "Failed get contacts", Toast.LENGTH_LONG).show();

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(ContactsActivity.this, "Failed get contacts", Toast.LENGTH_LONG).show();
			}
		}
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View contentView = inflater.inflate(R.layout.activity_list_special_note_views, null, false);

		mDrawerLayout.addView(contentView, 0);

		mDynamicListView = (ListView) findViewById(R.id.dynamic_listview);

		setUpDragAndDropSocial();
	}


	private void setUpDragAndDropSocial() {
		NeighbourHood nhInst = NeighbourHood.getInstance();
		final ContactAdapter adapter = new ContactAdapter(
				this, nhInst.getContactDetail());
		mDynamicListView.setAdapter(adapter);


		mDynamicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int        position, long id) {

				switch(view.getId()) {

					case R.id.call:
						System.out.println("shiva call button clicked");
						break;
					case R.id.email:
						System.out.println("shiva email button clicked");
						break;
					case R.id.share:
						System.out.println("shiva share button clicked");
						break;
				}
			}

		} );
	}
}