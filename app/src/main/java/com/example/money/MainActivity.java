package com.example.money;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private float apartmentPrice = 14_000;
    private int account = 1000;
    private float salary = 2_500;
    private int percentFree = 100;
    private float percentBack = 5;
    private float[] monthlyPayments = new float[120];

    private float apartmentPriceWithContribution() {
        return apartmentPrice - account;
    }

    private   float mortgageCosts(float amount, int percent) {
        return (amount * percent)/100;
    }

    public int countMonth(float total, float mortgageCosts,float percentBackYear) {

        float percentBankMonth = percentBackYear / 12;
        int count = 0;

        while (total > 0) {

            total = (total + (total * percentBankMonth) / 100) - mortgageCosts;
            if (total > mortgageCosts) {
                monthlyPayments[count] = mortgageCosts;
            } else {
                monthlyPayments[count] = total;
            }
            count++;
        }


        return count;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView countMonth = findViewById(R.id.countMonth);
        TextView payments = findViewById(R.id.payments);

        int count = countMonth(apartmentPriceWithContribution(), mortgageCosts(salary, percentFree), percentBack);
        countMonth.setText(count);

        String s = "";

        for (int i = 0; i < count; i++) {
            s += monthlyPayments[i] + "; ";
        }
        payments.setText(s);
    }

}