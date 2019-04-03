package com.source.mmt.neighbourhood.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.ApplicationFormSubmit;
import com.source.mmt.neighbourhood.service.GetServiceList;
import com.source.mmt.neighbourhood.service.RaiseServiceRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class SubmitApplicationActivity extends SlidingMenuActivity {

	private EditText Subject;
	private EditText Descption;
	private TextView mSubmit;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_special_note_views);

		NeighbourHood nhInst = NeighbourHood.getInstance();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View contentView = inflater.inflate(R.layout.application_form, null, false);

		mDrawerLayout.addView(contentView, 0);

		Subject = findViewById(R.id.subject);
		Descption = findViewById(R.id.desc);


		mSubmit = findViewById(R.id.submit);
		mSubmit.setOnClickListener(view -> {
			try {
				mSubmit.setEnabled(false);
				AsyncTask<String, Void, Integer> result1 = new ApplicationFormSubmit().execute(Subject.getText().toString(), Descption.getText().toString(), "Complaint");
				int code = result1.get();
				if(code == -1) {
					mSubmit.setEnabled(true);
					Toast.makeText(SubmitApplicationActivity.this, "Fail to Submit Request", Toast.LENGTH_LONG).show();
				}
				else if(code == 1) {
					Toast.makeText(SubmitApplicationActivity.this, " Request Registered Successfully", Toast.LENGTH_LONG).show();
					finish();
				}
			}catch (Exception e) {
				mSubmit.setEnabled(true);
				Toast.makeText(SubmitApplicationActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
			}
		});

	}
}
