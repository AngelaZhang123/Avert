package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.ByteArrayOutputStream;

public class PopupActivity extends Activity {

    private ImageView photo;
    private String [] names;
    private RadioButton [] radioButtons;
    private String selected;
    private Button done, none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.8));

        done = (Button) findViewById(R.id.done_button);
        none = (Button) findViewById(R.id.none_button);
        done.setEnabled(false);
        done.setBackgroundResource(R.color.gray_green);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            done.setElevation(0);
        }

        radioButtons = new RadioButton [] {
                (RadioButton) findViewById(R.id.rb1), (RadioButton) findViewById(R.id.rb2),
                (RadioButton) findViewById(R.id.rb3), (RadioButton) findViewById(R.id.rb4),
                (RadioButton) findViewById(R.id.rb5)
        };

        photo = (ImageView) findViewById(R.id.image);
        byte[] byteArray = new byte[0];
        Intent intentIn = getIntent();
        Bundle extras = intentIn.getExtras();
        if(extras!=null) {
            byteArray = extras.getByteArray("photo");
            names = extras.getStringArray("names");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            photo.setImageBitmap(bmp);
            for(int i = 0; i < names.length; i++) {
                radioButtons[i].setText(names[i]);
            }
        }

        for(int i = 0; i < radioButtons.length; i++) {
            final int x = i;
            radioButtons[x].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    selected = (String) radioButtons[x].getText();
                    done.setEnabled(true);
                    done.setBackgroundResource(R.color.green);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        done.setElevation(10);
                    }
                }
            });
        }

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopupActivity.this, CheckListActivity.class);
                intent.putExtra("object", selected);
                startActivity(intent);
            }
        });

        final byte[] arr = byteArray;
        none.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopupActivity.this, PopupActivity2.class);
                intent.putExtra("object", selected);
                intent.putExtra("photo", arr);
                startActivity(intent);
            }
        });
    }
}