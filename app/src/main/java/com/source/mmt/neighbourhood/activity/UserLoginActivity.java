package com.source.mmt.neighbourhood.activity;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.source.mmt.neighbourhood.R;
import com.source.mmt.neighbourhood.dao.CustomerDetailsDao;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.service.UserLogin;
import com.source.mmt.neighbourhood.service.UserRegister;

import java.util.regex.Matcher;

public class UserLoginActivity extends AppCompatActivity {

    TextView createTextView;
    Button loginButton, forgotButton;
    ImageView bgImageView;
    private EditText mobile;
    private EditText pwd;
    private TextView login;

    //region override functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);

        mobile = (EditText)findViewById(R.id.UserName);
        pwd = (EditText)findViewById(R.id.Password);
        login = (TextView)findViewById(R.id.loginButton);

        initUI();

        initDataBindings();

        initActions();
    }
    //endregion

    //region Init Functions
    private void initUI() {

        forgotButton = findViewById(R.id.forgotButton);
        createTextView = findViewById(R.id.createTextView);


        loginButton = findViewById(R.id.loginButton);

        //bgImageView = findViewById(R.id.bgImageView);

    }

    private void initDataBindings() {
        //int id = R.drawable.login_background;
        //Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {

        createTextView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Create Account", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserLoginActivity.this, SignUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        });

        loginButton.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(), "Clicked Login", Toast.LENGTH_SHORT).show();

            if(pwd.getText().length() == 0) {
                pwd.setError("Enter Password");
                return;
            }
            if(mobile.getText().length() != 10) {
                mobile.setError("Invalid Mobile Number or Email Id");
                return;
            }

            AsyncTask<String, Void, Integer> result =new UserLogin().execute(mobile.getText().toString(), pwd.getText().toString());

            try {
                int code = result.get();
                if (code == 0) {
                    //mProcessing.setVisibility(View.INVISIBLE);
                    loginButton.setEnabled(true);
                    mobile.setError("Fail to register");
                    return;
                } else if (code == 1) {
                    NeighbourHood nh_inst = NeighbourHood.getInstance();
                    CustomerDetailsDao vendorDetailsDao = new CustomerDetailsDao();
                    vendorDetailsDao.addVendorDetails(nh_inst.getUsrInfo().getUsrId(), nh_inst.getUsrInfo().getAptId(), nh_inst.getUsrInfo().getMobileNum(), nh_inst.getUsrInfo().getEmailId(), nh_inst.getUsrInfo().getType(), nh_inst.getUsrInfo().getUserName());

                    Intent intent = new Intent(UserLoginActivity.this, HomeScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }catch (Exception e) {

            }
        });

    }
}
