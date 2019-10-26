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
    TextView tvkg,tvlbsoz,tvlbsdec,tvunit,tv5per,tv10per,tv15per;
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

        setLabels();

        PoundsOz p = lbsToOz(kgToPounds(3.294214589));
        Toast.makeText(this, "lbs - " + lbsOzTolbsDec(p), Toast.LENGTH_SHORT).show();

    }

    public void setLabels(){
        tvkg.setText("--");
        tvlbsoz.setText("--");
        tvlbsdec.setText("--");

        tv5per.setText("--");
        tv10per.setText("--");
        tv15per.setText("--");

        switch(MODE){
            case KG_MODE:
                tvunit.setText("Kgs");
                break;
            case POOZ_MODE:
                tvunit.setText("lbs & oz");
                break;
            case PODC_MODE:
                tvunit.setText("lbs dec");
                break;
        }
    }

    public void initVals(){

        tvkg = findViewById(R.id.tvkg);
        tvlbsoz = findViewById(R.id.tvlbsoz);
        tvlbsdec = findViewById(R.id.tvlbsdec);
        tvunit = findViewById(R.id.tvUnit);
        tv5per = findViewById(R.id.tv5per);
        tv10per = findViewById(R.id.tv10per);
        tv15per = findViewById(R.id.tv15per);

        insert = findViewById(R.id.bInsert);

        wt = findViewById(R.id.etWeight);

        wt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getPercentage(s.toString());
                convertToOthers(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void convertToOthers(String snum){
        try {
            double num = Double.parseDouble(snum);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            switch(MODE){
                case KG_MODE:
                    tvkg.setText(""+df.format(num));
                    double pounds = kgToPounds(num);
                    PoundsOz p = lbsToOz(pounds);
                    tvlbsoz.setText("" + p.getPounds() + ", " + df.format(p.getOunces()));
                    tvlbsdec.setText(df.format(pounds));
                    break;
                case POOZ_MODE:
//                    tv5per.setText(tv5per.getText() + " Lb/Oz");
//                    tv10per.setText(tv10per.getText() + " Lb/Oz");
//                    tv15per.setText(tv15per.getText() + " Lb/Oz");
                    break;

                case PODC_MODE:
//                    tv5per.setText(tv5per.getText() + " Lb");
//                    tv10per.setText(tv10per.getText() + " Lb");
//                    tv15per.setText(tv15per.getText() + " Lb");
                    break;
            }

        }catch (NumberFormatException ne){
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
        }
    }

    public void getPercentage(String snum){
        try {
            double num = Double.parseDouble(snum);
            double per5 = (num/100)*5;
            double per10 = (num/100)*10;
            double per15 = (num/100)*15;
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
            tvkg.setText("--");
            tvlbsoz.setText("--");
            tvlbsdec.setText("--");
        }
    }

    public double kgToPounds(double kg){
        double pounds = kg * 2.20462262;
        return pounds;
    }

    public double poundsToKgs(double pounds){
        double kg = pounds / 2.20462262;
        return kg;
    }

    public PoundsOz lbsToOz(double lbs){
        double floor = Math.floor(lbs);
        double leftover = lbs - floor;
        double lbsnew = floor;
        double ounces = leftover * 16;
        return new PoundsOz(lbsnew, ounces);
    }

    public double lbsOzTolbsDec(PoundsOz p){
        return (p.getPounds() + (p.getOunces()/16));
    }

    public class PoundsOz{

        double pounds;
        double ounces;

        PoundsOz(double pounds, double ounces){
            this.pounds = pounds;
            this.ounces = ounces;
        }

        public double getOunces() {
            return ounces;
        }

        public double getPounds() {
            return pounds;
        }
    }

}
