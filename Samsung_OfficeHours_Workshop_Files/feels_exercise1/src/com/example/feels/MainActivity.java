// Written by Peter van der Linden for the Samsung Event in Chicago, 
// Jan 7 2014.
// this is a basic app that randomizes screen color on button press

// My suggestion is that you add the gesture code, and when some specific
// gesture is recognized, call the Button handler.
// this means you can make the color change by performing the gesture.


package com.example.feels;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void handleButton(View v) {
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.mylayout);
		int r=0, g=0, b=0;
		Random ran = new Random();
		g = ran.nextInt(0x100);
		r = ran.nextInt(0x100);
		b = ran.nextInt(0x100);
		Toast.makeText(this, "r="+r+ ",g="+g + ",b="+b, Toast.LENGTH_SHORT).show();
        int randomColor = 0xFF000000 + (r<<24) + (g<<16) + (b);
		rl.setBackgroundColor( randomColor);
	}
	

}
