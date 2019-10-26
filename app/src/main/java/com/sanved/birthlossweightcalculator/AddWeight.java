package com.sanved.birthlossweightcalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AddWeight extends AppCompatActivity {

    int unit;
    TextView kg,lbsoz,lbsdec,tvunit,tv5per,tv10per,tv15per;
    Button insert;
    EditText wt;

    private static int MODE = 0;
    private final static int KG_MODE = 1;
    private final static int POOZ_MODE = 2;
    private final static int PODC_MODE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_weight);

        if(savedInstanceState == null){

            Bundle extras = getIntent().getExtras();

            MODE = extras.getInt("unit");

        }else {

            MODE = savedInstanceState.getInt("unit");

        }

        //Toast.makeText(this, "this - " + unit, Toast.LENGTH_SHORT).show();

        initVals();

        kg.setText("--");
        lbsoz.setText("--");
        lbsdec.setText("--");

        tv5per.setText("--");
        tv10per.setText("--");
        tv15per.setText("--");

    }

    public void initVals(){

        kg = findViewById(R.id.tvkg);
        lbsoz = findViewById(R.id.tvlbsoz);
        lbsdec = findViewById(R.id.tvlbsdec);
        tvunit = findViewById(R.id.tvUnit);
        tv5per = findViewById(R.id.tv5per);
        tv10per = findViewById(R.id.tv10per);
        tv15per = findViewById(R.id.tv15per);

        insert = findViewById(R.id.bInsert);

        wt = findViewById(R.id.etWeight);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int we = Integer.parseInt(wt.getText().toString());
                if(we == 3) {
                    kg.setText("" + wt.getText().toString());

                    double pdec = kgToPounds(we);
                    lbsdec.setText("" + pdec);

                    int ozz = lbsToOz(pdec);
                    int po = ozz % 16;
                    int oz = ozz / 16;
                    lbsoz.setText(po + "," + oz);

                    double temp = we * 0.05;
                    tv5per.setText("" + temp);
                    temp = we * 0.10;
                    tv10per.setText("" + temp);
                    temp = we * 0.15;
                    tv15per.setText("" + temp);

                    /*kg.setText("3");
                    lbsoz.setText("6, 9.8");
                    lbsdec.setText("6.61");

                    tv5per.setText("0.15 kgs");
                    tv10per.setText("0.3 kgs");
                    tv15per.setText("0.45 kgs");*/
                }
            }
        });

        wt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getPercentage(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void getPercentage(String snum){
        try {
            float num = Float.parseFloat(snum);
            float per5 = (num/100)*5;
            float per10 = (num/100)*10;
            float per15 = (num/100)*15;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            tv5per.setText("" + df.format(per5));
            tv10per.setText("" + df.format(per10));
            tv15per.setText("" + df.format(per15));

            switch(MODE){
                case KG_MODE:
                    tv5per.setText(tv5per.getText() + " Kgs");
                    tv10per.setText(tv10per.getText() + " Kgs");
                    tv15per.setText(tv15per.getText() + " Kgs");
                    break;
                case POOZ_MODE:
                    tv5per.setText(tv5per.getText() + " Lb/Oz");
                    tv10per.setText(tv10per.getText() + " Lb/Oz");
                    tv15per.setText(tv15per.getText() + " Lb/Oz");
                    break;

                case PODC_MODE:
                    tv5per.setText(tv5per.getText() + " Lb");
                    tv10per.setText(tv10per.getText() + " Lb");
                    tv15per.setText(tv15per.getText() + " Lb");
                    break;
            }

        }catch (NumberFormatException ne){
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
        }
    }

    public double kgToPounds(double kg){
        double pounds = kg * 2.20462262;
        return pounds;
    }

    public int lbsToOz(double lbs){
        Log.e("",""+ lbs);
        int ounces = 1;
        double oz = Math.ceil(lbs);
        double extra = Math.floor(lbs);
        ounces = (int)oz * 16;
        ounces += extra * 0.12;
        return ounces;
    }

}
