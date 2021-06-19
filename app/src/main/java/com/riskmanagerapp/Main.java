package com.riskmanagerapp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.text.DecimalFormat;

public class Main extends AppCompatActivity {
    private EditText edtPosit, edtTarget, edtStop, edtCapital;
    private Button btnClean, btnCalc;
    private TextView rProfit, rLoss, resRR, accRisk;
    private DecimalFormat df = new DecimalFormat("0.##");
    private DecimalFormat df2 = new DecimalFormat("0.########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPosit = findViewById(R.id.edtPosit);
        edtTarget = findViewById(R.id.edtTarget);
        edtStop = findViewById(R.id.edtStop);
        edtCapital = findViewById(R.id.edtCapital);
        btnClean = findViewById(R.id.btnClean);
        btnCalc = findViewById(R.id.btnCalc);
        rProfit = findViewById(R.id.resProfit);
        rLoss = findViewById(R.id.resLoss);
        resRR = findViewById(R.id.resRR);
        accRisk = findViewById(R.id.resCap);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calc calc = new Calc(getApplicationContext(),edtPosit,edtTarget,edtStop,edtCapital);
                try {
                    resRR.setText(df.format(calc.calcRR()));
                    rProfit.setText(df2.format(calc.calcProfit()));
                    rLoss.setText(df2.format(calc.calcLoss()));
                    calc.calcAccountRisk(accRisk);
                }catch (NumberFormatException | NullPointerException exception){
                    exception.printStackTrace();
                    Toast.makeText(getApplicationContext(),R.string.fillAll,Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
    }
    public void clean(){
        edtPosit.setText("");
        edtTarget.setText("");
        edtStop.setText("");
        edtCapital.setText("");
        rProfit.setText("");
        rLoss.setText("");
        resRR.setText("");
        accRisk.setText("");
    }
}