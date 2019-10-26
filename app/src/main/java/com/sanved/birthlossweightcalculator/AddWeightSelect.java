package com.sanved.birthlossweightcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddWeightSelect extends AppCompatActivity implements View.OnClickListener {

    Button kg,lbs,lbsd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_unit);

        kg = findViewById(R.id.bKG);
        lbs = findViewById(R.id.bLBS);
        lbsd = findViewById(R.id.bLBSD);
        kg.setOnClickListener(this);
        lbs.setOnClickListener(this);
        lbsd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(AddWeightSelect.this, AddWeight.class);

        switch(v.getId()){

            case R.id.bKG:
                i.putExtra("unit",1);
                break;

            case R.id.bLBS:
                i.putExtra("unit",2);
                break;

            case R.id.bLBSD:
                i.putExtra("unit",3);
                break;

        }

        startActivity(i);
    }
}
