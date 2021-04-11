package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private final int [] text = new int [] {
            R.string.wound1, R.string.wound2, R.string.wound3, R.string.wound4,
            R.string.wound5, R.string.whatif1, R.string.whatif2, R.string.whatif3,
            R.string.whatif4
    };

    private boolean[] on = new boolean[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button [] buttons = new Button[]{
                (Button) findViewById(R.id.wound1), (Button) findViewById(R.id.wound2),
                (Button) findViewById(R.id.wound3), (Button) findViewById(R.id.wound4),
                (Button) findViewById(R.id.wound5), (Button) findViewById(R.id.whatif1),
                (Button) findViewById(R.id.whatif2), (Button) findViewById(R.id.whatif3),
                (Button) findViewById(R.id.whatif4)
        };

        final TextView [] textViews = new TextView [] {
                (TextView) findViewById(R.id.wound_text1), (TextView) findViewById(R.id.wound_text2),
                (TextView) findViewById(R.id.wound_text3), (TextView) findViewById(R.id.wound_text4),
                (TextView) findViewById(R.id.wound_text5), (TextView) findViewById(R.id.whatif_text1),
                (TextView) findViewById(R.id.whatif_text2), (TextView) findViewById(R.id.whatif_text3),
                (TextView) findViewById(R.id.whatif_text4)
        };

        for(int i = 0; i < buttons.length; i++) {
            final int x = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!on[x])
                        textViews[x].setText(text[x]);
                    else
                        textViews[x].setText("");
                    on[x] = !on[x];
                }
            });
        }
    }
}