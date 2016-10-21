package net.davidvan.accelerometerbasketballapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by David on 10/13/2016.
 */

public class SimulationView extends View implements SensorEventListener {

    private Sensor sensor;
    private SensorManager sensorManager;
    private Display display;

    private Bitmap mField;
    private Bitmap mBasket;
    private Bitmap mBitmap;
    private Particle mBall;
    private static final int BALL_SIZE = 256;
    private static final int BASKET_SIZE = 320;

    private float mXOrigin;
    private float mYOrigin;
    private float mSensorX;
    private float mSensorY;
    private float mSensorZ;
    private long mSensorTimeStamp;
    private float mHorizontalBound;
    private float mVerticalBound;


    public SimulationView(Context context) {
        super(context);
        mBall = new Particle();
        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        mBitmap = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, true);
        Bitmap basket = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        mBasket = Bitmap.createScaledBitmap(basket, BASKET_SIZE, BASKET_SIZE, true);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true; // Deprecated in API 24...
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mField = BitmapFactory.decodeResource(getResources(), R.drawable.field, opts);

        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = mWindowManager.getDefaultDisplay();

        mHorizontalBound = getWidth();
        mVerticalBound = getHeight();
        mXOrigin = mHorizontalBound;
        mYOrigin = mVerticalBound;

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        startSimulation();
    }

    public SimulationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBall = new Particle();
        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        mBitmap = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, true);
        Bitmap basket = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        mBasket = Bitmap.createScaledBitmap(basket, BASKET_SIZE, BASKET_SIZE, true);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true; // Deprecated in API 24...
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mField = BitmapFactory.decodeResource(getResources(), R.drawable.field, opts);

        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = mWindowManager.getDefaultDisplay();

        mHorizontalBound = getWidth();
        mVerticalBound = getHeight();
        mXOrigin = mHorizontalBound;
        mYOrigin = mVerticalBound;

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        startSimulation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mField, 0, 0, null);
        canvas.drawBitmap(mBasket, mXOrigin - BASKET_SIZE / 2, mYOrigin - BASKET_SIZE / 2, null);

        mBall.updatePosition(mSensorX, mSensorY, mSensorZ, mSensorTimeStamp);
        mBall.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);

        canvas.drawBitmap(mBitmap, (mXOrigin - BALL_SIZE / 2) + mBall.mPosX, (mYOrigin - BALL_SIZE / 2) - mBall.mPosY, null);

        invalidate();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        mXOrigin = width * .5f;
        mYOrigin = height * .5f;
        mHorizontalBound = (width - BALL_SIZE) * 0.5f;
        mVerticalBound = (height - BALL_SIZE) * 0.5f;
        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        mBitmap = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, true);
        Bitmap basket = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        mBasket = Bitmap.createScaledBitmap(basket, BASKET_SIZE, BASKET_SIZE, true);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true; // Deprecated in API 24...
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mField = BitmapFactory.decodeResource(getResources(), R.drawable.field, opts);

        WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        display = mWindowManager.getDefaultDisplay();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (display.getRotation() == Surface.ROTATION_0) {
                mSensorX = event.values[0];
                mSensorY = event.values[1];
            }
            else if (display.getRotation() == Surface.ROTATION_90) {
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
            }
            else if (display.getRotation() == Surface.ROTATION_270) {
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
            }
            mSensorZ = event.values[2];
            mSensorTimeStamp = event.timestamp;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startSimulation() {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stopSimulation() {
        sensorManager.unregisterListener(this);
    }

}
