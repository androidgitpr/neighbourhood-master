package com.source.mmt.neighbourhood.customcalendar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.activity.SlidingMenuActivity;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.Events;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;

public class CalenderActivity extends SlidingMenuActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calender);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_calender, null, false);

        mDrawerLayout.addView(contentView, 0);

        NeighbourHood cubs = NeighbourHood.getInstance();
        if(cubs.getEvent().size() == 0) {
            AsyncTask<String, Void, Integer> result = new Events().execute();
            try {
                Integer code = result.get();

            } catch (Exception e) {
                e.printStackTrace();
                //       Toast.makeText(CalenderActivity.this, "Failed get Notes", Toast.LENGTH_LONG).show();
            }
        }
        Calendar c = (Calendar) findViewById(R.id.customCalendar);
        c.addEvent(new LocalDate(), CalenderActivity.this);

        System.out.println("shiva number of events present "+cubs.getEvent().size());
        for(int i = 0; i < cubs.getEvent().size(); i++) {

            System.out.println("shiva event " + cubs.getEvent().get(i).getEventDate());
            String[] fn = cubs.getEvent().get(i).getEventDate().split("-");
            try {

              //  Date date = format.parse(cubs.getEvent().get(i).getDate());
                System.out.println("shiva date printing  year "+fn[0]+"month "+fn[1]+"day "+fn[2]);
                LocalDate a = new LocalDate(Integer.parseInt(fn[0]), Integer.parseInt(fn[1]), Integer.parseInt(fn[2]), null);
                c.addEvent(a, CalenderActivity.this);
                //highlightDate(fragments.get(Months.monthsBetween(startDate, d).getMonths()), "" + d.getDayOfMonth(), context);
            } catch (Exception e) {

            }
        }

        values = new ArrayList<String>();

        LocalDate today = new LocalDate();
        values.clear();
        System.out.println("shiva this month "+today.getMonthOfYear());
        for(int i = 0; i < cubs.getEvent().size(); i++) {
            String[] dates_list = cubs.getEvent().get(i).getEventDate().split("-");
            int foo = Integer.parseInt(dates_list[1]);
            if(foo == today.getMonthOfYear()) {

                values.add("Date : " + dates_list[2] +"-"+dates_list[1]+"-"+dates_list[0] + " \nTitle : " + cubs.getEvent().get(i).getSubject() + "\nDetails : " + cubs.getEvent().get(i).getDetail());
            }
        }

        System.out.println("shiva events size"+values.size());
        ListView listView = (ListView) findViewById(R.id.dynamic_listview);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);


    }
    void updateEvents(int yr, int month) {
        System.out.println("shiva update event caleld ");

        NeighbourHood cubs = NeighbourHood.getInstance();
        values.clear();
        for(int i = 0; i < cubs.getEvent().size(); i++) {

            String[] dates_list = cubs.getEvent().get(i).getEventDate().split("-");
            int foo = Integer.parseInt(dates_list[1]);
            int year = Integer.parseInt(dates_list[0]);
            if(foo == month && year == yr)
                values.add("Date : "+dates_list[2]+"-"+dates_list[1]+"-"+dates_list[0]+" \nTitle : "+cubs.getEvent().get(i).getSubject()+"\nDetails : "+cubs.getEvent().get(i).getDetail());
        }
        adapter.notifyDataSetChanged();

    }
}
