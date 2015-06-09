package com.example.charlesburks.countrycabinrentals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MyAlert extends DialogFragment {
    String dates;
    String lastDay;
    int nights;
    int totalCost;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        if(nights < 3){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Bookings must be at least 3 nights");
            builder.setNegativeButton("Let me try that again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });

            Dialog dialog = builder.create();
            return dialog;
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Total cost: $" + totalCost);
            builder.setMessage("Checking in:  " + dates + "\nChecking out: " + lastDay);
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Trip canceled", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Enjoy your stay", Toast.LENGTH_SHORT).show();
                }
            });
            Dialog dialog = builder.create();
            return dialog;
        }
    }

    public void setDates(String booking, String last, int myNight, int price){
        dates = booking;
        lastDay = last;
        nights = myNight;
        totalCost = price;
    }

}
