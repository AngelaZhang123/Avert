package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MedsActivity extends AppCompatActivity {

    private Button addButton, editButton;
    private TextView medDI1, medI1, medDI2, medDI3, medI2, medI3, medT1, desc1, medT2, desc2, medT3, desc3;
    private EditText med1, medD1, med2, med3, medD2, medD3;
    private ImageButton homeB;
    boolean clicked=false;
    SharedPreferences meds;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meds);

        addButton = (Button) findViewById(R.id.add_Button);
        editButton = (Button) findViewById(R.id.editB);
        medI1 = (TextView) findViewById(R.id.medI1);
        medDI1 = (TextView) findViewById(R.id.medDI1);
        medD1 = (EditText) findViewById(R.id.medD1);
        med1 = (EditText) findViewById(R.id.med1);
        medI2 = (TextView) findViewById(R.id.medI2);
        medDI2 = (TextView) findViewById(R.id.medDI2);
        medD2 = (EditText) findViewById(R.id.medD2);
        med2 = (EditText) findViewById(R.id.med2);
        medI3 = (TextView) findViewById(R.id.medI3);
        medDI3 = (TextView) findViewById(R.id.medDI3);
        medD3 = (EditText) findViewById(R.id.medD3);
        med3 = (EditText) findViewById(R.id.med3);

        medT1 = (TextView) findViewById(R.id.medT1);
        medT2 = (TextView) findViewById(R.id.medT2);
        medT3 = (TextView) findViewById(R.id.medT3);
        desc1 = (TextView) findViewById(R.id.desc1);
        desc2 = (TextView) findViewById(R.id.desc2);
        desc3 = (TextView) findViewById(R.id.desc3);
        homeB = (ImageButton) findViewById(R.id.logoM);


        meds = getSharedPreferences("medication", Context.MODE_PRIVATE);
        editor = this.meds.edit();
        setData();
        changeEdit(1);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked==false) {
                    editButton.setText("Done");
                    changeEdit(0);
                }
                else {
                    editButton.setText("Edit");
                    changeEdit(1);
                    setData();
                }
                clicked=!clicked;
            }
        });

        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = meds.getString("medsNum", "0");
                if(number.equals("0"))
                    editor.putString("medsNum", "1");
                else if(number.equals("1"))
                    editor.putString("medsNum", "2");
                else if(number.equals("2"))
                    editor.putString("medsNum", "3");
                editor.apply();
                changeEdit(0);
            }
        });
    }

    public void changeEdit(int num){

        if(num==1){
            med1.setEnabled(false);
            medD1.setEnabled(false);
            addButton.setVisibility(View.INVISIBLE);
            med1.setVisibility(View.INVISIBLE);
            medD1.setVisibility(View.INVISIBLE);
            med2.setEnabled(false);
            medD2.setEnabled(false);
            med2.setVisibility(View.INVISIBLE);
            medD2.setVisibility(View.INVISIBLE);
            med3.setEnabled(false);
            medD3.setEnabled(false);
            med3.setVisibility(View.INVISIBLE);
            medD3.setVisibility(View.INVISIBLE);

            medDI3.setVisibility(View.VISIBLE);
            medI3.setVisibility(View.VISIBLE);
            medDI1.setVisibility(View.VISIBLE);
            medI1.setVisibility(View.VISIBLE);
            medDI2.setVisibility(View.VISIBLE);
            medI2.setVisibility(View.VISIBLE);
            if(meds.getString("medsNum", "0").equals("2")){
                medDI3.setVisibility(View.INVISIBLE);
                medI3.setVisibility(View.INVISIBLE);
            }
            else if(meds.getString("medsNum", "0").equals("1")){
                medDI3.setVisibility(View.INVISIBLE);
                medI3.setVisibility(View.INVISIBLE);
                medDI2.setVisibility(View.INVISIBLE);
                medI2.setVisibility(View.INVISIBLE);
            }
            else if(meds.getString("medsNum", "0").equals("0")){
                medDI3.setVisibility(View.INVISIBLE);
                medI3.setVisibility(View.INVISIBLE);
                medDI2.setVisibility(View.INVISIBLE);
                medI2.setVisibility(View.INVISIBLE);
                medDI1.setVisibility(View.INVISIBLE);
                medI1.setVisibility(View.INVISIBLE);
            }
        }
        else{
            addButton.setVisibility(View.VISIBLE);
            medDI1.setVisibility(View.INVISIBLE);
            medI1.setVisibility(View.INVISIBLE);
            medDI2.setVisibility(View.INVISIBLE);
            medI2.setVisibility(View.INVISIBLE);
            medDI3.setVisibility(View.INVISIBLE);
            medI3.setVisibility(View.INVISIBLE);
            if(meds.getString("medsNum", "0").equals("3")){
                med1.setVisibility(View.VISIBLE);
                medD1.setVisibility(View.VISIBLE);
                med1.setEnabled(true);
                medD1.setEnabled(true);
                med2.setVisibility(View.VISIBLE);
                medD2.setVisibility(View.VISIBLE);
                med2.setEnabled(true);
                medD2.setEnabled(true);
                med3.setVisibility(View.VISIBLE);
                medD3.setVisibility(View.VISIBLE);
                med3.setEnabled(true);
                medD3.setEnabled(true);
                addButton.setVisibility(View.INVISIBLE);
            }
            else if(meds.getString("medsNum", "0").equals("2")){
                med1.setVisibility(View.VISIBLE);
                medD1.setVisibility(View.VISIBLE);
                med1.setEnabled(true);
                medD1.setEnabled(true);
                med2.setVisibility(View.VISIBLE);
                medD2.setVisibility(View.VISIBLE);
                med2.setEnabled(true);
                medD2.setEnabled(true);
                med3.setVisibility(View.INVISIBLE);
                medD3.setVisibility(View.INVISIBLE);
                med3.setEnabled(false);
                medD3.setEnabled(false);

            }
            else if(meds.getString("medsNum", "0").equals("1")){
                med1.setVisibility(View.VISIBLE);
                medD1.setVisibility(View.VISIBLE);
                med1.setEnabled(true);
                medD1.setEnabled(true);
                med2.setVisibility(View.INVISIBLE);
                medD2.setVisibility(View.INVISIBLE);
                med2.setEnabled(false);
                medD2.setEnabled(false);
                med3.setVisibility(View.INVISIBLE);
                medD3.setVisibility(View.INVISIBLE);
                med3.setEnabled(false);
                medD3.setEnabled(false);
            }
            else if(meds.getString("medsNum", "0").equals("0")){
                med1.setVisibility(View.INVISIBLE);
                medD1.setVisibility(View.INVISIBLE);
                med1.setEnabled(false);
                medD1.setEnabled(false);
                med2.setVisibility(View.INVISIBLE);
                medD2.setVisibility(View.INVISIBLE);
                med2.setEnabled(false);
                medD2.setEnabled(false);;
                med3.setVisibility(View.INVISIBLE);
                medD3.setVisibility(View.INVISIBLE);
                med3.setEnabled(false);
                medD3.setEnabled(false);
            }
        }
        medT1.setVisibility(View.VISIBLE);
        medT2.setVisibility(View.VISIBLE);
        medT3.setVisibility(View.VISIBLE);
        desc1.setVisibility(View.VISIBLE);
        desc2.setVisibility(View.VISIBLE);
        desc3.setVisibility(View.VISIBLE);
        if(meds.getString("medsNum", "0").equals("0")){
            medT1.setVisibility(View.INVISIBLE);
            medT2.setVisibility(View.INVISIBLE);
            medT3.setVisibility(View.INVISIBLE);
            desc1.setVisibility(View.INVISIBLE);
            desc2.setVisibility(View.INVISIBLE);
            desc3.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.VISIBLE);
        }
        else if(meds.getString("medsNum", "0").equals("1")){
            medT2.setVisibility(View.INVISIBLE);
            medT3.setVisibility(View.INVISIBLE);
            desc2.setVisibility(View.INVISIBLE);
            desc3.setVisibility(View.INVISIBLE);
        }
        else if(meds.getString("medsNum", "0").equals("2")){
            medT3.setVisibility(View.INVISIBLE);
            desc3.setVisibility(View.INVISIBLE);
        }
    }

    public void setData(){
        setText(1, med1, medI1);
        setDesc(1, medD1, medDI1);
        setText(2, med2, medI2);
        setDesc(2, medD2, medDI2);
        setText(3, med3, medI3);
        setDesc(3, medD3, medDI3);
    }

    public void setText(int num, EditText med, TextView medI){
        String medic = meds.getString("med"+num, "");
        String newMed = med.getText().toString();
        boolean notSame = true;
        if(medic.equals(newMed) || medic.equals("") || newMed.equals(""))
            notSame = false;

        if(medic.equals("") && newMed.equals("")) {
            medI.setText("Add Name");
        }
        else if (medic.equals("")|| notSame) {
            editor.putString("med" + num, newMed);
            medI.setText(newMed);
            editor.apply();
        }
        else{
            editor.putString("med"+num, medic);
            editor.apply();
            medI.setText(medic);
        }
        medic = meds.getString("med"+num, "");
        med.setText(medic);
    }

    public void setDesc(int num, EditText medD, TextView medDI){
        String medDesc = meds.getString("medD"+num, "");
        String newMedD = medD.getText().toString();
        boolean notSameD = true;
        if(medDesc.equals(newMedD) || medDesc.equals("") || newMedD.equals(""))
            notSameD = false;

        if(medDesc.equals("") && newMedD.equals("")) {
            medDI.setText("None");
        }
        else if (medDesc.equals("")|| notSameD) {
            editor.putString("medD" + num, newMedD);
            medDI.setText(newMedD);
            editor.apply();
        }
        else{
            editor.putString("medD"+num, medDesc);
            editor.apply();
            medDI.setText(medDesc);
        }
        medDesc = meds.getString("medD"+num, "");
        medD.setText(medDesc);
        changeEdit(1);

    }

}