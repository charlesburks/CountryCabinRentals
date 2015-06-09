package com.example.charlesburks.countrycabinrentals;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CabinTwo extends ActionBarActivity {

    private int currentYear;
    private int currentMonth;
    private int currentDay;
    static final int DATE_DIALOG_ID = 0;
    private Button btDate2;
    private TextView reservation;
    int myNights;


    public void newPageClick(View view){
        RadioButton cabin1  = (RadioButton) findViewById(R.id.cabin2RB1);
        RadioButton cabin2  = (RadioButton) findViewById(R.id.cabin2RB2);
        boolean checked = ((RadioButton)view).isChecked();

        if (cabin1.isChecked()){
            startActivity(new Intent(CabinTwo.this, MainActivity.class));
            cabin2.setChecked(false);
        } else if (cabin1.isChecked()){
            cabin2.setChecked(false);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabin_two);
        RadioButton cabin1  = (RadioButton) findViewById(R.id.cabin2RB1);
        RadioButton cabin2  = (RadioButton) findViewById(R.id.cabin2RB2);
        cabin1.setChecked(false);
        cabin2.setChecked(false);
        btDate2 = (Button) findViewById(R.id.btnDate2);
        reservation = (TextView) findViewById(R.id.txtReservation2);
        btDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);

    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, reservationDate, currentYear, currentMonth, currentDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener reservationDate =
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {

                    MyAlert myalert = new MyAlert();
                    myalert.show(getFragmentManager(), "Your Scheduled Time");
                    reservation.setText("Your reservations is set for " + (month + 1) + ("-") + day + ("-") + year);
                    String dayOne = "" + (month + 1) + ("-") + day + ("-") + year;
                    String lastDay = dateAdding((month + 1) + "-" + day + "-"+ year);
                    int price = (myNights * 125);
                    myalert.setDates(dayOne, lastDay, myNights, price);

                }
            };

    String dateAdding(String Date)
    {
        EditText nights = (EditText)findViewById(R.id.nightsEdit2);
        myNights = Integer.parseInt(nights.getText().toString());
        int Days = myNights;
        String finaldate="";
        try
        {
            java.util.Date date = null;
            String str = Date;
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            date = formatter.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,Days );
            finaldate= formatter.format(calendar.getTime());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return finaldate;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cabin_two, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
