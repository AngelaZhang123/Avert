package com.example.extinguisher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckListActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    private int points;
    private ImageView mImageView;
    private ImageButton foodButton, maskButton, medsButton, firstAidButton, flashlightButton, radioButton, docsButton, cashButton, cameraButton;
    private TextView foodText, maskText, medsText, firstAidText, flashlightText, radioText, docsText, cashText;
    //private ConstraintLayout cLayout;

    Uri image_uri;
    SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        //cLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        maskButton = (ImageButton) findViewById(R.id.maskButton);
        foodButton = (ImageButton) findViewById(R.id.foodButton);
        firstAidButton = (ImageButton) findViewById(R.id.firstAidButton);
        medsButton = (ImageButton) findViewById(R.id.medsButton);
        flashlightButton = (ImageButton) findViewById(R.id.flashlightButton);
        radioButton = (ImageButton) findViewById(R.id.radioButton);
        docsButton = (ImageButton) findViewById(R.id.docsButton);
        cashButton = (ImageButton) findViewById(R.id.cashButton);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        foodText = (TextView) findViewById(R.id.foodText);
        maskText = (TextView) findViewById(R.id.maskText);
        medsText = (TextView) findViewById(R.id.medsText);
        firstAidText = (TextView) findViewById(R.id.firstAidText);
        flashlightText = (TextView) findViewById(R.id.flashlightText);
        radioText = (TextView) findViewById(R.id.radioText);
        docsText = (TextView) findViewById(R.id.docsText);
        cashText = (TextView) findViewById(R.id.cashText);
        mImageView = (ImageView) findViewById(R.id.imageView);
        setInvisible(0);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    }
                    else {
                        openCamera();
                    }
                }
                else {
                    openCamera();
                }
            }
        });

        /*cLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setInvisible(0);
            }
        });*/

        maskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setInvisible(2);
            }
        });
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setInvisible(1);
            }
        });
        medsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setInvisible(3);
            }
        });
        firstAidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible(4);
            }
        });
        flashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible(5);
            }
        });
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible(6);
            }
        });
        docsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible(7);
            }
        });
        cashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible(8);
            }
        });

    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        openCamera();
                }
                else{
                    String text = "Permission denied...";
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mImageView.setImageURI(image_uri);
        }
    }

    public void setInvisible(int num){
        foodText.setVisibility(View.INVISIBLE);
        maskText.setVisibility(View.INVISIBLE);
        medsText.setVisibility(View.INVISIBLE);
        firstAidText.setVisibility(View.INVISIBLE);
        flashlightText.setVisibility(View.INVISIBLE);
        radioText.setVisibility(View.INVISIBLE);
        docsText.setVisibility(View.INVISIBLE);
        cashText.setVisibility(View.INVISIBLE);
        if(num==1)
            foodText.setVisibility(View.VISIBLE);
        else if(num==2)
            maskText.setVisibility(View.VISIBLE);
        else if(num==3)
            medsText.setVisibility(View.VISIBLE);
        else if(num==4)
            firstAidText.setVisibility(View.VISIBLE);
        else if(num==5)
            flashlightText.setVisibility(View.VISIBLE);
        else if(num==6)
            radioText.setVisibility(View.VISIBLE);
        else if(num==7)
            docsText.setVisibility(View.VISIBLE);
        else if(num==8)
            cashText.setVisibility(View.VISIBLE);
    }
}

