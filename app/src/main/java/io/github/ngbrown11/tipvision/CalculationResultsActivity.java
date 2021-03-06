package io.github.ngbrown11.tipvision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

public class CalculationResultsActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "io.github.ngbrown11.tipvision.MESSAGE";
    private int[] configs;
    private double testBillTotal = 39.79;
    private static int percentage;
    private static int contributors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_results);

        Intent intent = getIntent();
        configs = intent.getExtras().getIntArray("io.github.ngbrown11.tipvision.camera.MESSAGE");
        percentage = configs[0];
        contributors = configs[1];

        TextView total = (TextView) findViewById(R.id.total);
        total.setText(String.valueOf(testBillTotal));

        TextView tip = (TextView) findViewById(R.id.tip);
        // Calculate tip
        double tipAmt = calculate(testBillTotal, percentage, contributors);
        tip.setText(String.valueOf(tipAmt));
    }

    private double calculate(double total, double tip, double contributors) {
        // Compute percentage of tip for each person
        double actualTipPct = tip / contributors;
        // Compute tip amount per contributor
        double actualTipAmt = (((total * 100) * actualTipPct) / 10000);
        // Leave two decimal spaces
        actualTipAmt = ((int) (actualTipAmt * 100)) / 100.0;
        return actualTipAmt;
    }
}
