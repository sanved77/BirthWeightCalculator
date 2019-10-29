package com.sanved.birthlossweightcalculator;

import java.lang.reflect.WildcardType;

public class WeightEntry {

    double kilo, lbsdec, pound, ounces;

    public WeightEntry(double kilo, AddWeight.PoundsOz p, double lbsdec){
        this.kilo = kilo;
        this.lbsdec = lbsdec;
        this.pound = p.getPounds();
        this.ounces = p.getOunces();
    }

    public WeightEntry(double kilo, double pounds, double ounces, double lbsdec){
        this.kilo = kilo;
        this.lbsdec = lbsdec;
        this.pound = pounds;
        this.ounces = ounces;
    }

    public double getKilo() {
        return kilo;
    }

    public double getLbsdec() {
        return lbsdec;
    }

    public double getPound() {
        return pound;
    }

    public double getOunces() {
        return ounces;
    }

    public void setKilo(double kilo) {
        this.kilo = kilo;
    }

    public void setLbsdec(double lbsdec) {
        this.lbsdec = lbsdec;
    }

    public void setPound(double pound) {
        this.pound = pound;
    }

    public void setOunces(double ounces) {
        this.ounces = ounces;
    }
}
