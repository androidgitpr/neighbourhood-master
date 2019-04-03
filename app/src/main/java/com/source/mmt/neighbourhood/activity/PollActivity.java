package com.source.mmt.neighbourhood.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.GetContacts;
import com.source.mmt.neighbourhood.service.GetSurveyQuestions;


public class PollActivity extends AppCompatActivity {

    Button prev, submit, next;
    TextView question, ans_a, ans_b, ans_c, ans_d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_activity);


        NeighbourHood nhInst = NeighbourHood.getInstance();

        initToolbar();

        AsyncTask<String, Void, Integer> result = new GetSurveyQuestions().execute(String.valueOf(nhInst.getUsrInfo().getAptId()));
        try {
            Integer code = result.get();
            if(code != 1)
                Toast.makeText(PollActivity.this, "Fail to get Survey Questions", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(PollActivity.this, "Failed get survey questions", Toast.LENGTH_LONG).show();
        }

        if(nhInst.getSurveyQIndex() == 0)
            prev.setVisibility(View.GONE);
        if(nhInst.getSurveyQList().size() == 1 || nhInst.getSurveyQList().size() == (nhInst.getSurveyQIndex() + 1))
            next.setVisibility(View.GONE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int index = nhInst.getSurveyQIndex();
               index++;
               nhInst.setSurveyQIndex(index);

               Intent intent = new Intent(PollActivity.this, PollActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
            }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = nhInst.getSurveyQIndex();
                index--;
                nhInst.setSurveyQIndex(index);

                Intent intent = new Intent(PollActivity.this, PollActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PollActivity.this, "submit clicked", Toast.LENGTH_LONG).show();
            }
        });

     //   question.setText(nhInst.getSurveyQList().get(nhInst.getSurveyQIndex()).getQuestion());

    }

    //region Init Toolbar

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Survey");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        } catch (Exception e) {
            Log.e("TEAMPS", "Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set display home as up enabled.");
        }

        prev = (Button)findViewById(R.id.prev);
        submit = (Button)findViewById(R.id.submit);
        next = (Button)findViewById(R.id.next);

        question = (TextView)findViewById(R.id.questionText);
        ans_a = (TextView)findViewById(R.id.answer_a);
        ans_b = (TextView)findViewById(R.id.answer_b);
        ans_c = (TextView)findViewById(R.id.answer_c);
        ans_d = (TextView)findViewById(R.id.answer_d);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion

}
