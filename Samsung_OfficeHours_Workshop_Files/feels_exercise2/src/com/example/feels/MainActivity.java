// Written by Peter van der Linden for the Samsung Event in Chicago, 
// Jan 7 2014.
// Modified by Anna Schaller January 14, 2014.
// this is a basic app that randomizes screen color on button press
// Added code to support gestures.  When detected will change 
// color on screen.


package com.example.feels;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.gesture.Sgesture;
import com.samsung.android.sdk.gesture.SgestureHand;

public class MainActivity extends Activity {

	RelativeLayout rl;
	Sgesture mGesture;	
    private SgestureHand mSgestureHand;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rl = (RelativeLayout) findViewById(R.id.mylayout);
		
		// initialize gesture
		initialize();
	}

	@Override
	protected void onStop(){
	     super.onDestroy();
	     mSgestureHand.stop();
	}
	
	public void handleButton(View v) {
		changeBackgroundColor();
	}
	
	public void changeBackgroundColor() {
		int r=0, g=0, b=0;
		Random ran = new Random();
		g = ran.nextInt(0x100);
		r = ran.nextInt(0x100);
		b = ran.nextInt(0x100);
		Toast.makeText(this, "r="+r+ ",g="+g + ",b="+b, Toast.LENGTH_SHORT).show();
        int randomColor = 0xFF000000 + (r<<24) + (g<<16) + (b);
		rl.setBackgroundColor( randomColor);
	}
	
	/**
	 * Verify that gesture is available on 4.3 Samsung device and set up listener for movement
	 */
	
	public void initialize() {
        mGesture = new Sgesture();
        try {
            mGesture.initialize(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Toast.makeText(this, "This device is not supported", Toast.LENGTH_LONG).show();
            finish();
        } catch (SsdkUnsupportedException e) {
            e.printStackTrace();
            Toast.makeText(this, "This device is not supported", Toast.LENGTH_LONG).show();
            finish();
        }
        
        if(mGesture.isFeatureEnabled(Sgesture.TYPE_HAND_PRIMITIVE)){
            mSgestureHand = new SgestureHand(Looper.getMainLooper(),mGesture);
            mSgestureHand.start(Sgesture.TYPE_HAND_PRIMITIVE,changeHandListener);
            
        } 
	}
	
    private SgestureHand.ChangeListener changeHandListener = new SgestureHand.ChangeListener() {    
        @Override
        public void onChanged(SgestureHand.Info info) {
        	changeBackgroundColor();
        }
        
    };

}
