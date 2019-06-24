package com.example.sawanatha;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


public class Hearing_loss_main extends AppCompatActivity {
    private Button button;


    LineChartView lineChartView;
    int[] axisData = {125,250,500,1000,2000,4000,8000};
    int[] yAxisData = {30, 40, 35, 45, 30, 25, 30};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        -----------------graph-----------------------------
        lineChartView = findViewById(R.id.chart);

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#000000"));

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(String.valueOf(axisData[i])));
        }

        for (int i =0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setName("f[Hz]");
        axis.setHasLines(true);
        axis.setLineColor(Color.parseColor("#000000"));
        axis.setTextSize(14);
        axis.setTextColor(Color.parseColor("#009688"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("/[dBHL]");
        yAxis.setHasLines(true);
        yAxis.setLineColor(Color.parseColor("#000000"));
        yAxis.setTextColor(Color.parseColor("#009688"));
        yAxis.setTextSize(14);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 120;
        viewport.bottom=0;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);

//---------------------------------------------------------------------------------------------------

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMored();

            }
        });
    }

    public void openActivityMored() {
        Intent intent = new Intent(this, more_dActivity.class);
        startActivity(intent);
    }

    //-------menu-----------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                return true;
            case R.id.settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    ---------------back_menu----------------------------------
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Hearing_loss_main.this);
        builder.setMessage("Are you sure you want to exit?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Hearing_loss_main.super.onBackPressed();
            }
        }).setNegativeButton("Cancel",null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
