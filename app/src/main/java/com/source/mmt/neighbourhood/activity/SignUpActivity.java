package com.source.mmt.neighbourhood.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.dao.CustomerDetailsDao;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.GetServiceList;
import com.source.mmt.neighbourhood.service.UserRegister;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;


public class SignUpActivity extends AppCompatActivity {

    TextView createTextView;
    Button registerButton;

    private EditText mobile;
    private EditText pwd;
    private EditText email;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_signup_activity);


        NeighbourHood nhInst = NeighbourHood.getInstance();

/*
        if(nhInst.getServiceCatList().size() == 0) {
            AsyncTask<String, Void, Integer> result = new GetServiceList().execute("1");
            try {
                Integer code = result.get();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(SignUpActivity.this, "Failed get Apartment List", Toast.LENGTH_LONG).show();
            }
        }
*/
        mobile = (EditText)findViewById(R.id.mobile);
        pwd = (EditText)findViewById(R.id.Password);
        email = (EditText)findViewById(R.id.Email);
        registerButton = (Button) findViewById(R.id.registerButton);
        mName = (EditText)findViewById(R.id.UserName);

        initUI();

        initDataBindings();

        initActions();

    }

    //region Init Functions
    private void initUI() {

        createTextView = findViewById(R.id.createTextView);

        registerButton = findViewById(R.id.registerButton);

    }

    private void initDataBindings() {
        //int id = R.drawable.login_background;
        //Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        registerButton.setOnClickListener(view -> {

            if(mName.getText().length() == 0) {
                mName.setError("Invalid Name");
                return;
            }

            if(mobile.getText().length() != 10 ) {
                mobile.setError("Invalid Mobile Number");
                return;
            }

            Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(email.getText().toString());
            if(!matcher.matches())
            {
                email.setError("Invalid Email Address");
                return;
            }

            if(pwd.getText().length() == 0) {
                pwd.setError("Enter Password");
                return;
            }
            try {
                registerButton.setEnabled(false);
                AsyncTask<String, Void, Integer> result = new UserRegister().execute(mName.getText().toString(), mobile.getText().toString(), email.getText().toString(), pwd.getText().toString());
                int code = result.get();
                if(code == 0) {
                    //mProcessing.setVisibility(View.INVISIBLE);
                    registerButton.setEnabled(true);
                    mobile.setError("Fail to register");
                    return;
                }
                else if(code == 1) {

                    NeighbourHood nh_inst = NeighbourHood.getInstance();
                    CustomerDetailsDao vendorDetailsDao = new CustomerDetailsDao();
                    vendorDetailsDao.addVendorDetails(nh_inst.getUsrInfo().getUsrId(), nh_inst.getUsrInfo().getAptId(), nh_inst.getUsrInfo().getMobileNum(), nh_inst.getUsrInfo().getEmailId(), nh_inst.getUsrInfo().getType(), nh_inst.getUsrInfo().getUserName());

                    Intent intent = new Intent(SignUpActivity.this, HomeScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }catch (Exception e) {
                registerButton.setEnabled(true);
                //mProcessing.setVisibility(View.INVISIBLE);
                Toast.makeText(SignUpActivity.this, "Fail to Register", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //endregion
}
