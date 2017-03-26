package io.github.ngbrown11.tipvision;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import io.github.ngbrown11.tipvision.camera.CameraActivity;

public class TipConfigsActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "io.github.ngbrown11.tipvision.MESSAGE";
    private int[] configs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_configs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Create the adapter for the gratuity spinner
         * */
        List<Integer> gratuityPercentages = new ArrayList<>();
        for(int i = 1; i <= 100; i++)
            gratuityPercentages.add(i);
        ArrayAdapter<Integer> gratuityAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, gratuityPercentages);
        gratuityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner percentage = (Spinner) findViewById(R.id.gratuitySpinner);
        percentage.setAdapter(gratuityAdapter);

        /**
         * Create the adapter for the contributors spinner
         * */
        List<Integer> numOfContributors = new ArrayList<>();
        for(int i = 1; i <= 10; i++)
            numOfContributors.add(i);
        ArrayAdapter<Integer> contributorsAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, numOfContributors);
        gratuityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner contributors = (Spinner) findViewById(R.id.contributorsSpinner);
        contributors.setAdapter(contributorsAdapter);

        /**
         * TODO define the method to operate camera activity on click
         * */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setAlpha(0.25f);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configs = new int[2];
                configs[0] = Integer.valueOf(percentage.getSelectedItem().toString());
                configs[1] = Integer.valueOf(contributors.getSelectedItem().toString());
                Intent intent = new Intent(TipConfigsActivity.this, CameraActivity.class);
                intent.putExtra(EXTRA_MESSAGE, configs);
                startActivity(intent);
                TipConfigsActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_configs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
