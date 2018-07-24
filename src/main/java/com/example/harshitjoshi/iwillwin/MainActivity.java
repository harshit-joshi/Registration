package com.example.harshitjoshi.iwillwin;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputName;
    private TextInputLayout textInputConform;
    private static final Pattern PASSWORD_PATTERN=Pattern.compile("^"+".{6,20}"+"$");

    private TextView dob;
    private android.support.v7.widget.Toolbar toolbar;
    private DatePickerDialog.OnDateSetListener mDatasetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textInputName=findViewById(R.id.text_input_name);
        textInputEmail=findViewById(R.id.text_input_email);
        textInputPassword=findViewById(R.id.text_input_password);
        textInputConform=findViewById(R.id.text_input_conform);
        //For Date of Birth
        dob=findViewById(R.id.registerDob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,mDatasetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }


        });
        mDatasetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                //Month is counted from 0 january=0
                month=month+1;
                String date=month +"/" +dayOfMonth +"/" +year;
                dob.setTextColor(Color.BLACK);
                dob.setText(date);

            }
        };
    }
    private Boolean validateName()
    {
        String nameInput=textInputName.getEditText().getText().toString().trim();
        if(nameInput.isEmpty())
        {
            textInputName.setError("Fields can't be empty");
            return false;
        }

        else
        {
            textInputName.setError(null);
            return true;
        }
    }
    private Boolean validateEmail()
    {
        String emailInput=textInputEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty())
        {
            textInputEmail.setError("Fields can't be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        {
            textInputEmail.setError("enter a valid email Id");
            return false;
        }
        else
        {
            textInputEmail.setError(null);
            return true;
        }
    }
    private Boolean validatePassword()
    {
        String passwordInput=textInputPassword.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty())
        {
            textInputPassword.setError("Fields can't be empty");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches())
        {
            textInputPassword.setError("Password to short");
            return false;
        }
        else
        {
            textInputPassword.setError(null);
            return true;
        }
    }
    private Boolean validateConform()
    {
        String passwordInput=textInputPassword.getEditText().getText().toString().trim();
        String conformInput=textInputConform.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty())
        {
            textInputConform.setError("Fields can't be empty");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(conformInput).matches())
        {
            textInputConform.setError("Password to short");
            return false;
        }
        else if(!passwordInput.equals(conformInput))
        {
            textInputConform.setError("Password does'nt match");
            return false;
        }
        else
        {
            textInputConform.setError(null);
            return true;
        }
    }



    public void Submit(View view) {

        if(!validateName() | !validateEmail() | !validatePassword() | !validateConform())
        {
            Toast.makeText(this,"Something is wrong",Toast.LENGTH_LONG).show();
        }

    }
}
