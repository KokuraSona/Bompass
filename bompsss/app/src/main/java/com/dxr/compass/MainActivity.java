package com.dxr.compass;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.hardware.*;
import android.view.animation.*;

public class MainActivity extends Activity
    implements SensorEventListener
{
    private ImageView compassImage; 
	private float currentDegree = 0f; 
	private SensorManager mSensorManager;

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		compassImage = (ImageView) findViewById(R.id.compass);
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

	@Override
	protected void onResume()
	{
		super.onResume();
		mSensorManager.registerListener(this,
		    mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
			SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause()
	{
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		int sensorType = event.sensor.getType();
		swift (sensorType);
		{
			float degree = event.values[0];
			RotateAnimation ra = new RotateAnimation (currentDegree,
			   -degree,Animation.RELATIVE_TO_SELF, 0.5f,
			   Animation.RELATIVE_TO_SELF, 0.5f);
			   ra.setDuration(200);
			   compassImage.startAnimation(ra);
			   currentDegree = -degree;
		}
	}

	private void swift(int sensorType)
	{
	}
    @Override
	public void onAccuracyChanged(Sensor Sensor, int accuracy)
	{
	}
}

