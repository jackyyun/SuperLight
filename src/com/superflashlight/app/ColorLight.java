package com.superflashlight.app;

import com.superflashlight.app.ColorPickerDialog.OnColorChangedListener;
import com.superflashlight.app.widget.HideTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class ColorLight extends Bulb implements OnColorChangedListener {
	
	protected int mCurrentColorLight = Color.RED;
	protected HideTextView mHideTextViewColorLight;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mHideTextViewColorLight = (HideTextView) findViewById(R.id.textview_hide_color_light);
		
	}
	
	public void colorChanged(int color) {
		mUIColorLight.setBackgroundColor(color);
		mCurrentColorLight = color;
		
	}
	      
	public void onClick_ShowColorPicker(View view){
		new ColorPickerDialog(this, this, Color.RED).show();
	}
}
