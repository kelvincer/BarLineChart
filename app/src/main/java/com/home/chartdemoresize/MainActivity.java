package com.home.chartdemoresize;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private CombinedChart chart;
    private final int count = 12;
    String[] labels = new String[]{"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8",
            "a9", "a10", "a11", "a12", "a13", "a14", "a15", "a16", "a17", "a18", "a19", "a20"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = findViewById(R.id.chart1);
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d(TAG, "value " + value);
                return labels[(int) value];
            }
        });
        xAxis.setTextSize(12);
        xAxis.setLabelRotationAngle(270f);
        xAxis.setLabelCount(10);

        CombinedData data = new CombinedData();

        data.setData(generateBarData());
        data.setData(generateLineData());

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        /*chart.setMaxVisibleValueCount(40); // if more than 40 entries are displayed in the chart, no values will be drawn
        chart.setPinchZoom(true);      // scaling can now only be done on x- and y-axis separately
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(false);
        chart.setHighlightFullBarEnabled(false);
        chart.setDoubleTapToZoomEnabled(true);
        chart.setTouchEnabled(false);
        chart.setVisibleXRangeMaximum(5f);
        chart.setHighlightPerTapEnabled(false);*/

        //chart.setVisibleXRangeMaximum(20f);
        //chart.setVisibleXRangeMinimum(3f);

        chart.animateXY(800, 800);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setAxisMinimum(-0.5f);

        chart.setData(data);
        chart.invalidate();
    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        ArrayList<BarEntry> entries4 = new ArrayList<>();
        ArrayList<BarEntry> entries5 = new ArrayList<>();

        int fcount = 4;
        for (int index = 0; index < fcount; index++) {
            entries1.add(new BarEntry(index, getRandom(25, 25)));
        }

        for (int index = 4; index < 8; index++) {
            entries2.add(new BarEntry(index, getRandom(25, 25)));
        }

        for (int index = 8; index < 12; index++) {
            entries3.add(new BarEntry(index, getRandom(25, 25)));
        }

        for (int index = 12; index < 16; index++) {
            entries4.add(new BarEntry(index, getRandom(25, 25)));
        }

        for (int index = 16; index < 20; index++) {
            entries5.add(new BarEntry(index, getRandom(25, 25)));
        }

        BarDataSet set1 = new BarDataSet(entries1, "P074");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(entries2, "TMR");
        set2.setColors(Color.rgb(61, 165, 255));
        set2.setValueTextColor(Color.rgb(61, 165, 255));
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set3 = new BarDataSet(entries3, "INTRA");
        set3.setColor(Color.rgb(160, 120, 178));
        set3.setValueTextColor(Color.rgb(60, 220, 78));
        set3.setValueTextSize(10f);
        set3.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set4 = new BarDataSet(entries4, "PECOP");
        set4.setColor(Color.rgb(40, 80, 108));
        set4.setValueTextColor(Color.rgb(60, 220, 78));
        set4.setValueTextSize(10f);
        set4.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set5 = new BarDataSet(entries5, "QUIP");
        set5.setColor(Color.rgb(140, 110, 188));
        set5.setValueTextColor(Color.rgb(60, 220, 78));
        set5.setValueTextSize(10f);
        set5.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2, set3, set4, set5);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        // d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();

        for (int index = 0; index < 20; index++)
            entries.add(new Entry(index, getRandom(15, 5)));

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    protected float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }
}
