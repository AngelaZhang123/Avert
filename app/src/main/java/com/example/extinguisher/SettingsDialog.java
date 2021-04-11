package com.example.extinguisher;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SwitchCompat;

import static android.content.ContentValues.TAG;

public class SettingsDialog extends AppCompatDialogFragment {

    private EditText editNum, editYear, editMonth, editDay;
    private SettingsDialogListener listener;
    private SwitchCompat notifSwitch;
    private AppCompatSpinner spinner;
    private final int [] DAYS_IN_MONTH = new int [] {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    private int [] currDateArr;
    private int n, currentItem;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setTitle("Checklist Settings")
                .setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInputValid()) {
                    String num = editNum.getText().toString();
                    boolean on = notifSwitch.isChecked();
                    int item = spinner.getSelectedItemPosition();
                    String date = editYear.getText() + "-" + editMonth.getText().toString() +
                            "-" + editDay.getText().toString();
                    listener.applyInfo(Integer.parseInt(num), on, item);
                    listener.applyDate(date);
                    dismiss();
                }
            }
        });

        String date = String.valueOf(new java.sql.Date(System.currentTimeMillis()));
        String [] arr = date.split("-");
        currDateArr = new int [arr.length];
        for(int i = 0; i < arr.length; i++)
            currDateArr[i] = Integer.parseInt(arr[i]);

        PreferenceManager manager = PreferenceManager.getInstance();
        editNum = view.findViewById(R.id.enter_num);
        editNum.setText(manager.getNotifNum() + "");
        notifSwitch = view.findViewById(R.id.notif_switch);
        notifSwitch.setChecked(manager.isNotifsOn());
        spinner = view.findViewById(R.id.often_menu);
        spinner.setSelection(manager.getSpinnerItem());
        editMonth = view.findViewById(R.id.monthEditText);
        editDay = view.findViewById(R.id.dayEditText);
        editYear = view.findViewById(R.id.yearEditText);

        String d = manager.getDate();
        String [] dateArr = d.split("-");
        editMonth.setText(dateArr[1]);
        editDay.setText(dateArr[2]);
        editYear.setText(dateArr[0]);

        n = tryParseInt(editNum.getText().toString());
        editNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    int newNum = tryParseInt(editNum.getText().toString());
                    if(newNum != n && newNum > 0) {
                        n =  newNum;
                        updateDate();
                    }
                }
            }
        });

        currentItem = spinner.getSelectedItemPosition();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != currentItem) {
                    currentItem = position;
                    updateDate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        notifSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notifSwitch.isChecked()) {
                    editNum.setEnabled(true);
                    spinner.setEnabled(true);
                    editDay.setEnabled(true);
                    editMonth.setEnabled(true);
                    editYear.setEnabled(true);
                }
                else {
                    editNum.setEnabled(false);
                    spinner.setEnabled(false);
                    editDay.setEnabled(false);
                    editMonth.setEnabled(false);
                    editYear.setEnabled(false);
                }
            }
        });

        return dialog;
    }

    private void updateDate() {
        if(spinner.getSelectedItemPosition() == 0) {
            editYear.setText(currDateArr[0] + tryParseInt(editNum.getText().toString()) + "");
            editMonth.setText(currDateArr[1] + "");
            editDay.setText(currDateArr[2] + "");
        }
        else if(spinner.getSelectedItemPosition() == 1) {
            int newMonth = currDateArr[1] + tryParseInt(editNum.getText().toString());
            int newYear = currDateArr[0];
            if(newMonth > 12) {
                newMonth -= 12;
                newYear++;
            }
            editMonth.setText(newMonth + "");
            editYear.setText(newYear + "");
            editDay.setText(currDateArr[2] + "");
        }

    }

    private boolean isInputValid() {
        int y = tryParseInt(editYear.getText().toString());
        int m = tryParseInt(editMonth.getText().toString());
        int d = tryParseInt(editDay.getText().toString());
        int n = tryParseInt(editNum.getText().toString());

        boolean isGreaterYear = y > currDateArr[0];
        boolean isGreaterMonth = m > currDateArr[1];

        if ((y > 9999 || y < currDateArr[0]) ||
                ((m < currDateArr[1] && !isGreaterYear) || m > 12) ||
                ((d < currDateArr[2] && (!isGreaterMonth && !isGreaterYear)) || d > DAYS_IN_MONTH[m-1]) ||
                n < 1) {
            Toast.makeText(getContext(), "Sorry, one or more of your inputs is not valid.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (SettingsDialogListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement SettingsDialogListener");
        }
    }

    public interface SettingsDialogListener {
        void applyInfo(int num, boolean on, int item);
        void applyDate(String d);
    }
}
