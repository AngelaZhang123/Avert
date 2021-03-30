package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PopupActivity2 extends Activity {

    private ImageView photo;
    private RadioGroup group1, group2;
    private Button done, retake;
    private String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.8));

        photo = (ImageView) findViewById(R.id.photo);
        Intent intentIn = getIntent();
        Bundle extras = intentIn.getExtras();
        if(extras!=null) {
            byte[] byteArray = extras.getByteArray("photo");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            photo.setImageBitmap(bmp);
        }

        done = (Button) findViewById(R.id.done_button);
        done.setEnabled(false);
        done.setBackgroundResource(R.color.gray_green);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            done.setElevation(0);
        }
        retake = (Button) findViewById(R.id.retake_button);

        group1 = (RadioGroup) findViewById(R.id.radio_group);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton [] btns = new RadioButton [] {
                        (RadioButton) findViewById(R.id.r1c1), (RadioButton) findViewById(R.id.r2c1),
                        (RadioButton) findViewById(R.id.r3c1), (RadioButton) findViewById(R.id.r4c1),
                        (RadioButton) findViewById(R.id.r5c1)
                };
                for(RadioButton rb : btns) {
                    if(rb.isChecked()) {
                        group2.clearCheck();
                        done.setEnabled(true);
                        done.setBackgroundResource(R.color.green);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            done.setElevation(10);
                        }
                        selected = (String) rb.getText();
                        break;
                    }
                }
            }
        });

        group2 = (RadioGroup) findViewById(R.id.radio_group2);
        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton [] btns = new RadioButton [] {
                        (RadioButton) findViewById(R.id.r1c2), (RadioButton) findViewById(R.id.r2c2),
                        (RadioButton) findViewById(R.id.r3c2), (RadioButton) findViewById(R.id.r4c2),
                        (RadioButton) findViewById(R.id.r5c2)
                };
                for(RadioButton rb : btns) {
                    if(rb.isChecked()) {
                        group1.clearCheck();
                        done.setEnabled(true);
                        done.setBackgroundResource(R.color.green);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            done.setElevation(10);
                        }
                        selected = (String) rb.getText();
                        break;
                    }
                }
            }
        });

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopupActivity2.this, CheckListActivity.class);
                intent.putExtra("item", selected);
                startActivity(intent);
            }
        });

        retake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopupActivity2.this, CheckListActivity.class);
                intent.putExtra("open camera", true);
                startActivity(intent);
            }
        });
    }
}