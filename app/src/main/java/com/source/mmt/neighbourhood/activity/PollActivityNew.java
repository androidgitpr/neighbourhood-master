package com.source.mmt.neighbourhood.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.source.mmt.neighbourhood.R;

public class PollActivityNew extends SlidingMenuActivity {

    SeekBar seekBar1, seekBar2, seekBar3;
    TextView textView1,textView2,textView3;
    CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.poll_activity_new);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.poll_activity_new, null, false);

        mDrawerLayout.addView(contentView, 0);

        seekBar1=contentView.findViewById(R.id.SeekBarId1);
        textView1=contentView.findViewById(R.id.textView);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            public void onStopTrackingTouch(SeekBar bar)
            {
                int value = bar.getProgress(); // the value of the seekBar progress
            }

            public void onStartTrackingTouch(SeekBar bar)
            {

            }

            public void onProgressChanged(SeekBar bar,
                                          int paramInt, boolean paramBoolean)
            {
                textView1.setText("" + paramInt + "%"); // here in textView the percent will be shown
            }
        });

        seekBar2 = contentView.findViewById(R.id.SeekBarId2);
        textView2=contentView.findViewById(R.id.textView1);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            public void onStopTrackingTouch(SeekBar bar)
            {
                int value = bar.getProgress(); // the value of the seekBar progress
            }

            public void onStartTrackingTouch(SeekBar bar)
            {

            }

            public void onProgressChanged(SeekBar bar,
                                          int paramInt, boolean paramBoolean)
            {
                textView2.setText("" + paramInt + "%"); // here in textView the percent will be shown
            }
        });

        seekBar3 = contentView.findViewById(R.id.SeekBarId3);
        textView3=contentView.findViewById(R.id.textView2);
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onStopTrackingTouch(SeekBar bar)
            {
                int value = bar.getProgress(); // the value of the seekBar progress
            }

            public void onStartTrackingTouch(SeekBar bar)
            {

            }

            public void onProgressChanged(SeekBar bar,int paramInt, boolean paramBoolean)
            {
                textView3.setText("" + paramInt + "%"); // here in textView the percent will be shown
            }
        });

        cardView=contentView.findViewById(R.id.card);
        cardView.setBackgroundResource(R.drawable.download);
    }
}
