package alanh.android.tipcalc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alan2 on 2/06/2016.
 */
public class TipRecord {
    private double bill;

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    private int tipPercentage;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    private Date timeStamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double getTip(){
        return bill*(tipPercentage/100d);
    }
    public String getDateFormatted(){
        SimpleDateFormat simple=new SimpleDateFormat("MMM dd,yyyy HH:mm");
        return simple.format(timeStamp);
    }
}
