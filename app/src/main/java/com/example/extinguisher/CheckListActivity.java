package com.example.extinguisher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class CheckListActivity extends AppCompatActivity implements SettingsDialog.SettingsDialogListener {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    private int points, notifNum, spinnerItem;
    private ImageView mImageView;
    private ImageButton cameraButton, backButton, infoButton, settingsButton;
    private ImageButton [] imgBtnArr;
    private TextView [] textArr;
    private TextView infoText;
    private ImageView [] checks;
    private boolean [] checked;
    private boolean notifsOn;
    private boolean showInfo;
    private ConstraintLayout cLayout;
    private ImageClassifier imageClassifier;
    private final String CHANNEL_ID = "kit notification";
    private String date;
    private final String [][] POSSIBLE_LABELS = new String [][] {
            {"gasmask", "mask", "ski mask", "handkerchief"},
            {"French loaf", "bagel", "pretzel", "cheeseburger", "hotdog", "mashed potato",
                    "head cabbage", "broccoli", "cauliflower", "zucchini", "spaghetti squash",
                    "acorn squash", "butternut squash", "cucumber", "artichoke", "bell pepper",
                    "mushroom", "Granny Smith", "strawberry", "orange", "lemon", "fig", "pineapple",
                    "banana", "jackfruit", "custard apple", "pomegranate", "carbonara", "meat loaf",
                    "pizza", "potpie", "burrito"},
            {"pill bottle"}, {"Band Aid"}, {}, {"radio", "tape player", "cassette player", "CD player"},
            {"document"}, {"money"}, {"hand sanitizer", "paper towel", "bath towel"},
            {"water bottle", "water jug"}
    };
    private final String [] CHECKLIST_ITEMS = new String [] {
            "masks", "food", "medicine", "first aid kit", "flashlight", "radio", "important documents",
            "cash", "sanitation items", "water"
    };
    private boolean alarmSet;

    Uri image_uri;
    PreferenceManager manager;
    SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        createNotificationChannel();

        manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());
        points = manager.getListPoints();
        userData = this.getSharedPreferences("List Preferences", 0);
        notifsOn = manager.isNotifsOn();
        alarmSet = userData.getBoolean("alarm is set", true);
        date = manager.getDate();
        updateAlarm(notifsOn);

        cLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        mImageView = (ImageView) findViewById(R.id.imageView);
        backButton = (ImageButton) findViewById(R.id.home_button);
        infoButton = (ImageButton) findViewById(R.id.about_button);
        settingsButton = (ImageButton) findViewById(R.id.settings_button);

        imgBtnArr = new ImageButton [] {
                findViewById(R.id.maskButton), findViewById(R.id.foodButton), findViewById(R.id.firstAidButton),
                findViewById(R.id.medsButton), findViewById(R.id.flashlightButton), findViewById(R.id.radioButton),
                findViewById(R.id.docsButton), findViewById(R.id.cashButton), findViewById(R.id.sanitationButton),
                findViewById(R.id.waterButton)
        };

        textArr = new TextView [] {
                findViewById(R.id.maskText), findViewById(R.id.foodText), findViewById(R.id.firstAidText),
                findViewById(R.id.medsText), findViewById(R.id.flashlightText),
                findViewById(R.id.radioText), findViewById(R.id.docsText), findViewById(R.id.cashText),
                findViewById(R.id.sanitationText), findViewById(R.id.waterText)
        };

        checks = new ImageView [] {
                findViewById(R.id.check1), findViewById(R.id.check2), findViewById(R.id.check3),
                findViewById(R.id.check4), findViewById(R.id.check5), findViewById(R.id.check6),
                findViewById(R.id.check7), findViewById(R.id.check8), findViewById(R.id.check9),
                findViewById(R.id.check10)
        };

        setInvisible(checks.length);

        checked = new boolean [checks.length];
        for(int i = 0; i < checked.length; i++) {
            checked[i] = userData.getBoolean("checked" + i, false);
        }

        try {
            imageClassifier = new ImageClassifier(this);
        }
        catch (IOException e) {
            Log.e("Image Classifier Error", "ERROR: " + e);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.setListPoints(points);
                Intent intent = new Intent(CheckListActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraClicked();
            }
        });

        showInfo = true;
        infoText = (TextView) findViewById(R.id.info_text);
        infoText.setVisibility(View.GONE);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showInfo) {
                    infoText.setVisibility(View.VISIBLE);
                    infoText.setText(R.string.checklist_info_text);
                    showInfo = false;
                }
                else {
                    infoText.setVisibility(View.GONE);
                    showInfo = true;
                }
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckListActivity.this, ChecklistSettingsActivity.class);
                openDialog();
            }
        });

        cLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setInvisible(8);
            }
        });

        for(int i = 0; i < imgBtnArr.length; i++) {
            final int x = i;
            imgBtnArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setInvisible(x);
                }
            });
        }

        Intent intentIn = getIntent();
        Bundle extras = intentIn.getExtras();
        setExtras(extras);
        setCheckBoxes();
    }

    private void setExtras(Bundle extras) {
        if(extras !=  null) {
            if(extras.getString("object", null) != null) {
                String object = extras.getString("object");
                int item = checkCategory(object);
                if (item != -1) {
                    Toast.makeText(CheckListActivity.this,
                            "Great job!", Toast.LENGTH_SHORT).show();
                    points += 2;
                }
                else {
                    Toast.makeText(CheckListActivity.this,
                            "Sorry, that's not a kit item.", Toast.LENGTH_SHORT).show();
                }
            }
            else if(extras.getString("item", null) != null) {
                String item = extras.getString("item");
                for(int i = 0; i < CHECKLIST_ITEMS.length; i++) {
                    if(item.equals(CHECKLIST_ITEMS[i])) {
                        if(checked[i]) {
                            Toast.makeText(CheckListActivity.this,
                                    "You already have that item.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        points += 2;
                        checked[i] = true;
                        break;
                    }
                }
            }
            else if(extras.getBoolean("open camera", false)) cameraClicked();
        }
    }

    private void openDialog() {
        SettingsDialog dialog = new SettingsDialog();
        dialog.show(getSupportFragmentManager(), "settings dialog");
    }

    private void updateAlarm(boolean on) {
        String [] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]) - 1;
        int day = Integer.parseInt(dateArr[2]);
        long time = System.currentTimeMillis() + 30 * 1000;

        if(manager.isNotified()) {
            time += 30 * 1000;
            if (spinnerItem == 0) year += notifNum;
            else if (spinnerItem == 1) month += notifNum;
            manager.setNotified(false);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long alarmTime = calendar.getTimeInMillis();

        Intent alarmIntent = new Intent(CheckListActivity.this, NotificationReceiver.class);
        AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(CheckListActivity.this, 0,
                alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (on && System.currentTimeMillis() < time)
            alarmMgr.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        else alarmMgr.cancel(pendingIntent);

        manager.setDate(year + "-" + (month + 1) + "-" + day);
        alarmSet = true;
        SharedPreferences.Editor editor = userData.edit();
        editor.putBoolean("alarm is set", alarmSet);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notifMgr = getSystemService(NotificationManager.class);
            notifMgr.createNotificationChannel(channel);
        }
    }

    private void cameraClicked() {
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

    private int checkCategory(String object) {
        for(int i = 0; i < POSSIBLE_LABELS.length; i++) {
            for(int j = 0; j < POSSIBLE_LABELS[i].length; j++) {
                if(object.equals(POSSIBLE_LABELS[i][j])) {
                    if(checked[i]) {
                        Toast.makeText(CheckListActivity.this,
                                "You already have that item.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    checked[i] = true;
                    return i;
                }
            }
        }
        return -1;
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mImageView.layout(0, 0, 500, 400);
            mImageView.setImageURI(image_uri);
            mImageView.setDrawingCacheEnabled(true);
            Bitmap bmap = mImageView.getDrawingCache();
            List<ImageClassifier.Recognition> predictions = imageClassifier.recognizeImage(
                    bmap, 0);
            String [] predictionsArr = new String [5];

            for(int i = 0; i < predictions.size(); i++) {
                predictionsArr[i] = predictions.get(i).getName();
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent = new Intent(CheckListActivity.this, PopupActivity.class);
            intent.putExtra("photo", byteArray);
            intent.putExtra("names", predictionsArr);
            startActivity(intent);
        }
    }

    public void setInvisible(int num){
        for (int i = 0; i < checks.length; i++) {
            if(i == num) textArr[i].setVisibility(View.VISIBLE);
            else textArr[i].setVisibility(View.INVISIBLE);
        }
    }

    public void setCheckBoxes() {
        SharedPreferences.Editor editor = userData.edit();
        for(int i = 0; i < checks.length; i++) {
            if(checked[i])
                checks[i].setVisibility(View.VISIBLE);
            else checks[i].setVisibility(View.INVISIBLE);
            editor.putBoolean("checked" + i, checked[i]);
            editor.commit();
        }
        TextView pointsText = (TextView) findViewById(R.id.points);
        pointsText.setText("Points: " + points);
        manager.setListPoints(points);
    }

    @Override
    public void applyInfo(int num, boolean on, int item) {
        notifNum = num;
        notifsOn = on;
        spinnerItem = item;
        manager.setNotifInfo(num, on, item);
        spinnerItem = item;
        updateAlarm(notifsOn);
    }

    public void applyDate(String d) {
        date = d;
        manager.setDate(d);
    }
}

