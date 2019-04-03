package com.source.mmt.neighbourhood.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.adapter.ContactAdapter;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.GetContacts;
import com.source.mmt.neighbourhood.service.GetServiceList;
import com.source.mmt.neighbourhood.service.RaiseServiceRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;

public class RaiseTicketActivity extends SlidingMenuActivity {


	private Spinner mCatSpinner;
	private ArrayAdapter<String> mCatSpinnerAdapter;

	private Spinner mSubCatSpinner;
	private ArrayAdapter<String> mSubCatAdapter;

	private EditText mServiceDate;
	private EditText mServiceTime;
	private EditText mDesc;
	private TextView mSubmit;

	private int mDay;
	private int mMonth;
	private int mYear;


	private String SelTime = "0";

	private ArrayList<String> catList;
	private ArrayList<String> subCatList;
	private int catSel, subCatSel, serviceDate, serviceTime;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_special_note_views);

		NeighbourHood nhInst = NeighbourHood.getInstance();

		catSel = 0;
		subCatSel = 0;
		serviceDate = 0;
		serviceTime = 0;
		if(nhInst.getServiceCatList().size() == 0) {
			AsyncTask<String, Void, Integer> result = new GetServiceList().execute("1");
			try {
				Integer code = result.get();

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(RaiseTicketActivity.this, "Failed get Service List", Toast.LENGTH_LONG).show();
			}
		}


		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View contentView = inflater.inflate(R.layout.raise_ticket, null, false);

		mDrawerLayout.addView(contentView, 0);

		catList = new ArrayList<String>();

		//System.out.println("shiva total cat list "+nhInst.getServiceCatList().size());
		catList.clear();
//		catList.add("Select Category");
		for (int i = 0; i < nhInst.getServiceCatList().size(); i++) {
			catList.add(nhInst.getServiceCatList().get(i).getCatName());
		}

		mCatSpinner = (Spinner) findViewById(R.id.categorySpinner);
		mSubCatSpinner = (Spinner) findViewById(R.id.subCatSpinner);
		mCatSpinnerAdapter = new ArrayAdapter<String>(RaiseTicketActivity.this,
				android.R.layout.simple_spinner_item, catList);
		mCatSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		mCatSpinner.setAdapter(mCatSpinnerAdapter);

		mCatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

				catSel = position;
				if (position != 0) {

					subCatList = new ArrayList<String>();

					subCatList.clear();
					//System.out.println("shiva sub cat ID "+nhInst.getServiceCatList().get(position).getCatId());
					if(nhInst.getServiceSubCatMapping().get(nhInst.getServiceCatList().get(position).getCatId()) != null) {
						for (int i = 0; i < nhInst.getServiceSubCatMapping().get(nhInst.getServiceCatList().get(position).getCatId()).size(); i++) {
							subCatList.add(nhInst.getServiceSubCatMapping().get(nhInst.getServiceCatList().get(position).getCatId()).get(i).getCatName());
						}
					}
					mSubCatAdapter = new ArrayAdapter<String>(RaiseTicketActivity.this,
							android.R.layout.simple_spinner_item, subCatList);
					mSubCatAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
					mSubCatSpinner.setAdapter(mSubCatAdapter);

					mSubCatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
							subCatSel = position;
							//System.out.println("shiva position: " + position);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parentView) {
							//Toast.makeText(PersonalInfoActivity.this, "on not selected ", Toast.LENGTH_SHORT).show();
						}

					});
				}
			}
			@Override
			public void onNothingSelected (AdapterView < ? > parentView){
				//Toast.makeText(PersonalInfoActivity.this, "on not selected ", Toast.LENGTH_SHORT).show();
			}
		});


		mServiceDate = (EditText)findViewById(R.id.service_date);
		mServiceDate.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				final Calendar calendar = Calendar.getInstance();
				calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

				int selectedYear = calendar.get(Calendar.YEAR);
				int selectedMonth = calendar.get(Calendar.MONTH);
				int selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog datePickerDialog = new DatePickerDialog(RaiseTicketActivity.this,
						new DatePickerDialog.OnDateSetListener() {
							public void onDateSet(DatePicker view, int selectedYear,
												  int selectedMonth, int selectedDay) {
								mDay = selectedDay;
								mMonth = selectedMonth;
								mYear = selectedYear;
								StringBuilder Date = new StringBuilder("");
								String conver = Integer.toString(selectedDay);
								Date.append(conver);
								Date.append("/");
								conver = Integer.toString(selectedMonth);
								Date.append(conver);
								Date.append("/");
								conver = Integer.toString(selectedYear);
								Date.append(conver);

								mServiceDate.setText(Date.toString());
								serviceDate = 1;
							}
						}, mDay, mMonth, mYear);

				datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
				datePickerDialog.setTitle("Select Date");
				datePickerDialog.show();
			}
		});

		mServiceTime = findViewById(R.id.service_time);
		mServiceTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar mcurrentTime = Calendar.getInstance();
				final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
				int minute = mcurrentTime.get(Calendar.MINUTE);

				final TimePickerDialog mTimePicker;
				mTimePicker = new TimePickerDialog(RaiseTicketActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
						/*if(selectedHour < 12) {
							SelTime = selectedHour+":"+selectedMinute+":"+"AM";
						}
						/else*/
						SelTime = selectedHour+":"+selectedMinute+":"+"00";
						//SelTime = selectedHour+":"+selectedMinute;
						mServiceTime.setText(SelTime);
						serviceTime = 1;
					}
				}, hour, minute, true);
				mTimePicker.setTitle("Select Time");
				mTimePicker.show();

			}
		});

		mDesc = findViewById(R.id.desc);


		mSubmit = findViewById(R.id.submit);
		mSubmit.setOnClickListener(view -> {

                if (catSel == 0) {
                    Toast.makeText(RaiseTicketActivity.this, "Select Category", Toast.LENGTH_LONG).show();
                    return;
                }
                if (serviceDate == 0) {
                    Toast.makeText(RaiseTicketActivity.this, "Select Service Date", Toast.LENGTH_LONG).show();
                    return;
                }
                if (serviceTime == 0) {
                    Toast.makeText(RaiseTicketActivity.this, "Select Service Time", Toast.LENGTH_LONG).show();
                    return;
                }
                ProgressBar bar = findViewById(R.id.processBar);
                bar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            AsyncTask<String, Void, Integer> result1 = new RaiseServiceRequest().execute("1", catList.get(catSel), subCatList.get(subCatSel), mServiceDate.getText().toString(), mServiceTime.getText().toString(), mDesc.getText().toString());
                            int code = result1.get();
                            bar.setVisibility(View.GONE);
                            if (code == -1) {
                                mSubmit.setEnabled(true);
                                Toast.makeText(RaiseTicketActivity.this, "Fail to Register Service Request", Toast.LENGTH_LONG).show();
                            } else if (code == 1) {
                                Toast.makeText(RaiseTicketActivity.this, "Service Request Registered Successfully", Toast.LENGTH_LONG).show();

                                finish();
                            }
                        } catch (Exception e) {
                            bar.setVisibility(View.GONE);
                            mSubmit.setEnabled(true);
                            Toast.makeText(RaiseTicketActivity.this, "Fatal Fail to Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 100);
		});

	}
}
