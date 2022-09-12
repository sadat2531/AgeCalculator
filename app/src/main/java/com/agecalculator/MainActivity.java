package com.agecalculator;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, exitButton;
    private TextView textview1, textview2, textview3;
    public DatePickerDialog datePickerDialog1, datePickerDialog2;
    private Typeface typeface1;
    private AlertDialog.Builder alertDialougBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview1 = (TextView) findViewById(R.id.textviewid1);
        textview2 = (TextView) findViewById(R.id.textviewid2);
        textview3 = (TextView) findViewById(R.id.textviewid3);

        typeface1 = Typeface.createFromAsset(getAssets(),"fonts/alpha_echo.ttf");
        textview3.setTypeface(typeface1);

        button1 = (Button) findViewById(R.id.buttonid1);
        button2 = (Button) findViewById(R.id.buttonid2);
        button3 = (Button) findViewById(R.id.buttonid3);
        exitButton = (Button) findViewById(R.id.buttonid4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker datePicker = new DatePicker(MainActivity.this);
                int birthDay = datePicker.getDayOfMonth();
                int birthMonth = datePicker.getMonth();
                int birthYear = datePicker.getYear();

                datePickerDialog1 = new DatePickerDialog(MainActivity.this,

                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                textview1.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                            }
                        },birthYear, birthMonth, birthDay);

                datePickerDialog1.show();

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePicker datePicker = new DatePicker(MainActivity.this);
                int calculateDay = datePicker.getDayOfMonth();
                int calculateMonth = datePicker.getMonth();
                int calculateYear = datePicker.getYear();


                datePickerDialog2 = new DatePickerDialog(MainActivity.this,

                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                textview2.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                            }
                        },calculateYear, calculateMonth, calculateDay);

                datePickerDialog2.show();


            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bDate = textview1.getText().toString();
                String cDate = textview2.getText().toString();

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    // converting it to date format
                    Date date1 = simpleDateFormat1.parse(bDate);
                    Date date2 = simpleDateFormat1.parse(cDate);

                    long startdate = date1.getTime();
                    long endDate = date2.getTime();

                    // condition
                    if (startdate <= endDate) {
                        org.joda.time.Period period = new Period(startdate, endDate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();

                        // show the final output
                        textview3.setText(years + " Years |" + months + " Months |" + days + " Days");
                        Toast.makeText(MainActivity.this, R.string.ToastTxt1, Toast.LENGTH_SHORT).show();
                    } else {
                        // show message
                        Toast.makeText(MainActivity.this, R.string.ToastTxt2, Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    Toast.makeText(MainActivity.this, R.string.ToastTxt3, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });



        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialougBuilder = new AlertDialog.Builder(MainActivity.this);

                //for setting title
                alertDialougBuilder.setTitle(R.string.AlertTitle);

                //for setting message
                alertDialougBuilder.setMessage(R.string.AlertMessage);

                //for setting image
                alertDialougBuilder.setIcon(R.drawable.warning);

                //for setting button
                alertDialougBuilder.setPositiveButton(R.string.PositiveButtonTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,R.string.ToastTxt4,Toast.LENGTH_SHORT).show();
                        //exit
                        finish();
                    }
                });

                alertDialougBuilder.setNegativeButton(R.string.NegativeButtonTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,R.string.ToastTxt5,Toast.LENGTH_SHORT).show();

                    }
                });

                alertDialougBuilder.setNeutralButton(R.string.NeutralButtonTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,R.string.ToastTxt5,Toast.LENGTH_SHORT).show();
                    }
                });


                //for showing alert dialog
                AlertDialog alertDialog = alertDialougBuilder.create();
                alertDialog.show();

            }
        });

    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder alartDialogBuilder;
        alartDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alartDialogBuilder.setIcon(R.drawable.warning);
        alartDialogBuilder.setTitle(R.string.AlertTitle);
        alartDialogBuilder.setMessage(R.string.AlertMessage);
        alartDialogBuilder.setPositiveButton(R.string.PositiveButtonTxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this,R.string.ToastTxt4,Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        alartDialogBuilder.setNegativeButton(R.string.NegativeButtonTxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this,R.string.ToastTxt5,Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });

        AlertDialog alertDialog = alartDialogBuilder.create();
        alertDialog.show();


    }


}



