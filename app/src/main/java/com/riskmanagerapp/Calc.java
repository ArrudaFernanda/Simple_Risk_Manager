package com.riskmanagerapp;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calc {
    private Context context;
    private EditText edtPosit, edtTarget, edtStop, edtCapital;
    private String sPosit, sTarget, sStop, sCapital;
    private BigDecimal position, target, stop, capital, n100;
    private static final String N100 = "100";
    private DecimalFormat df = new DecimalFormat("0.##");

    public Calc(Context context, EditText edtPosit, EditText edtTarget, EditText edtStop, EditText edtCapital) {
        this.context = context;
        this.edtPosit = edtPosit;
        this.edtTarget = edtTarget;
        this.edtStop = edtStop;
        this.edtCapital = edtCapital;

        sPosit = edtPosit.getText().toString();
        sTarget = edtTarget.getText().toString();
        sStop = edtStop.getText().toString();
        sCapital = edtCapital.getText().toString();

        n100 = new BigDecimal(N100);

        isNull();

        if (!sCapital.equals("") && !sCapital.equals("0")){
            capital = new BigDecimal(sCapital);
        }else {
            capital = null;
        }

    }

    public boolean isNull(){
        if (!sPosit.equals("") && !sPosit.equals("0") && !sTarget.equals("") && !sTarget.equals("0") && !sStop.equals("") && !sStop.equals("0")){
            position = new BigDecimal(sPosit);
            target = new BigDecimal(sTarget);
            stop = new BigDecimal(sStop);

            return false;
        }else {
            position = null;
            target = null;
            sStop = null;

            return true;
        }
    }

    public BigDecimal calcRR(){
        BigDecimal result = target.divide(stop,3, RoundingMode.HALF_EVEN);

        return result;
    }
    public BigDecimal calcProfit(){
        BigDecimal percent = target.divide(n100, 3, RoundingMode.HALF_EVEN);
        BigDecimal result = position.multiply(percent);

        return result;
    }
    public BigDecimal calcLoss(){
        BigDecimal percent = stop.divide(n100, 3, RoundingMode.HALF_EVEN);
        BigDecimal result = position.multiply(percent);

        return result;
    }
    public void calcAccountRisk(TextView accRisk){
        if (capital != null){
            BigDecimal percentP = (n100.divide(capital,3,RoundingMode.HALF_EVEN)).multiply(calcProfit());
            BigDecimal percentL = (n100.divide(capital,3,RoundingMode.HALF_EVEN)).multiply(calcLoss());

            accRisk.setText("+ " + df.format(percentP) + "% " + "|" + " - " + df.format(percentL) + "%");
        }else {
            accRisk.setText("");
        }
    }
}
