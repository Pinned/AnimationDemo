package cn.knero.animationdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.knero.customview.WaterWaveView;


public class MainActivity extends ActionBarActivity {

    private WaterWaveView mWaterWaveView;
    private TextView mWaterHeight;
    private TextView mDistance;
    private TextView mAdjust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mWaterWaveView = (WaterWaveView) this.findViewById(R.id.water_wave_view);
        this.mWaterHeight = (TextView) this.findViewById(R.id.water_height);
        this.mDistance = (TextView) this.findViewById(R.id.distance);
        this.mAdjust = (TextView) this.findViewById(R.id.adj);
        setInfo();
    }

    private void setInfo() {
        this.mWaterHeight.setText(String.valueOf(mWaterWaveView.getWaterHeight()));
        this.mDistance.setText(String.valueOf(mWaterWaveView.getDistance()));
        this.mAdjust.setText(String.valueOf(mWaterWaveView.getAdjustAmplitude()));
    }

    public void subWaterHeight(View view) {
        this.mWaterWaveView.setWaterHeight(this.mWaterWaveView.getWaterHeight() - 10);
        setInfo();
    }

    public void addWaterHeight(View view) {
        this.mWaterWaveView.setWaterHeight(this.mWaterWaveView.getWaterHeight() + 10);
        setInfo();
    }

    public void subDistance(View view) {
        this.mWaterWaveView.setDistance(this.mWaterWaveView.getDistance() - 10);
        setInfo();
    }
    public void addDistance(View view) {
        this.mWaterWaveView.setDistance(this.mWaterWaveView.getDistance() +10);
        setInfo();
    }
    public void subAdj(View view) {
        this.mWaterWaveView.setAdjustAmplitude(mWaterWaveView.getAdjustAmplitude() - 10);
        setInfo();
    }
    public void addAdj(View view) {
        this.mWaterWaveView.setAdjustAmplitude(mWaterWaveView.getAdjustAmplitude() + 10);
        setInfo();
    }


}
