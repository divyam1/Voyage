package com.voyage;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.checked;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Bundle bundle;
    TextView txt1,txt2;
    int chBoxFort=0 ,chBoxWildLife =0,chBoxMuseum =0,chBoxReligious =0;
    ImageView checkBox1, checkBox2, checkBox3,checkBox4;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = getIntent().getExtras();
        String curTime = bundle.getString("curTime");
        String Uid = bundle.getString("andriodId");
        txt1 = (TextView) findViewById(R.id.textView1);
        txt2 = (TextView) findViewById(R.id.textView2);
        txt1.setText(curTime);
        txt2.setText(Uid);
        submit = (Button) findViewById(R.id.btsub);
        checkBox1 = (ImageView) findViewById(R.id.checkbox1);
        checkBox2 = (ImageView) findViewById(R.id.checkbox2);
        checkBox3 = (ImageView) findViewById(R.id.checkbox3);
        checkBox4 = (ImageView) findViewById(R.id.checkbox4);

        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkbox1:
                if(chBoxFort == 0) {
                    checkBox1.setImageDrawable(getResources().getDrawable( R.drawable.checked1));
                    chBoxFort =1;
                    break;
                }
                else {
                    checkBox1.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxFort =0;
                    break;
                }
            case R.id.checkbox2:
                if(chBoxWildLife == 0) {
                    checkBox2.setImageDrawable(getResources().getDrawable(R.drawable.checked1));
                    chBoxWildLife = 1;
                    break;
                }
                else {
                    checkBox2.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxWildLife = 0;
                    break;
                }
            case R.id.checkbox3:
                if(chBoxMuseum == 0) {
                    checkBox3.setImageDrawable(getResources().getDrawable(R.drawable.checked1));
                    chBoxMuseum = 1;
                    break;
                }
                else {
                    checkBox3.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxMuseum = 0;
                    break;
                }
            case R.id.checkbox4:
                if(chBoxReligious == 0) {
                    checkBox4.setImageDrawable(getResources().getDrawable(R.drawable.checked1));
                    chBoxReligious = 1;
                    break;
                }
                else {
                    checkBox4.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxReligious = 0;
                    break;
                }
            case R.id.btsub:
                Intent i = new Intent(MainActivity.this,Home.class);
                startActivity(i);
        }
    }
}
