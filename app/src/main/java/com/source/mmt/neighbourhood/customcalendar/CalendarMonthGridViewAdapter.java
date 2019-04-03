package com.source.mmt.neighbourhood.customcalendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.activity.SplashActivity;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class CalendarMonthGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list = new ArrayList<>();
    int year, month;
    Calendar calendar;


    class ViewHolder {

        TextView v;
        TextView dot;
    }


    public CalendarMonthGridViewAdapter(Context context, int resource, ArrayList<String> objects, int year, int month, Calendar calendar) {

        this.context = context;
        this.list = objects;
        this.year = year;
        this.month = month;
        this.calendar = calendar;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.calendar_list_item, null);
            holder = new ViewHolder();
            holder.v = (TextView) convertView.findViewById(R.id.text);
            holder.dot = (TextView) convertView.findViewById(R.id.dot);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String s = list.get(position);
        int a;

        try {
            holder.dot.setVisibility(View.INVISIBLE);
            holder.dot.setTextColor(calendar.getEventDotColor());
        }catch (Exception e) {
            Intent intent = new Intent(context, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
        try {
            a = Integer.parseInt(s);
        } catch (Exception e) {
            a = -1;
            holder.dot.setVisibility(View.INVISIBLE);
        }

        if (a != -1) {
            try {
                LocalDate currentSelectedDate = new LocalDate(this.year + "-" + this.month + "-" + s);
                if (calendar.getEventDates().contains(currentSelectedDate)) {
                    holder.dot.setVisibility(View.VISIBLE);
                } else {
                    holder.dot.setVisibility(View.INVISIBLE);
                }
                if (currentSelectedDate.isEqual(calendar.getSelected())) {
                    Drawable mDrawable = ContextCompat.getDrawable(context, R.drawable.logo);
                    try {
                    mDrawable.setColorFilter(new PorterDuffColorFilter(calendar.getExpandedCalendarSelectedColor(), PorterDuff.Mode.MULTIPLY));
                }catch (Exception e) {
                    Intent intent = new Intent(context, SplashActivity.class);
                    context.startActivity(intent);
                }
                    int sdk = android.os.Build.VERSION.SDK_INT;
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        convertView.setBackgroundDrawable(mDrawable);
                    } else {
                        convertView.setBackground(mDrawable);
                    }
                }
            } catch (Exception e) {
                Log.d("Exception", e.getStackTrace().toString());
            }

        } else {
            holder.dot.setVisibility(View.INVISIBLE);
        }
        try {
        holder.v.setText(s);
        holder.v.setTextColor(calendar.getExpandedCalendarTextColor());
        }catch (Exception e) {
            Intent intent = new Intent(context, SplashActivity.class);
            context.startActivity(intent);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}




