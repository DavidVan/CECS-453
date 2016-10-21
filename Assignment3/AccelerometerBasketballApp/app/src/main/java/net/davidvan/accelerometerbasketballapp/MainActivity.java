package net.davidvan.accelerometerbasketballapp;

import android.app.Activity;
import android.os.PowerManager;
import android.os.Bundle;

public class MainActivity extends Activity {

    private static final String TAG = "net.davidvan.accelerometerbasketballapp.MainActivity";
    private PowerManager.WakeLock mWakeLock;
    private SimulationView simulationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
        setContentView(R.layout.activity_main);
        simulationView = (SimulationView) findViewById(R.id.simulation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWakeLock.acquire();
        simulationView.startSimulation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWakeLock.release();
        simulationView.stopSimulation();
    }

}
