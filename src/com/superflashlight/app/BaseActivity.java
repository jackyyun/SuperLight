package com.superflashlight.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class BaseActivity extends Activity {
		protected enum UIType{
			UI_TYPE_MAIN, UI_TYPE_FLASHLIGHT, UI_TYPE_WARNINGLIGHT,UI_TYPE_MORSE,UI_TYPE_BULB,UI_TYPE_COLOR,UI_TYPE_POLICELIGHT,UI_TYPE_SETTINGS;
		}
		
		protected int mCurrentWarningLightInterval = 500;
		protected int mCurrentPoliceLightInterval = 100;
		
		protected ImageView mImageViewWarningLight1;
		protected ImageView mImageViewWarningLight2;
		protected ImageView mImageViewFlashLight;
		protected ImageView mImageViewFlashLightController;
		protected Camera mCamera;
		protected Parameters mParameters;
		protected EditText mEditTextMorseCode;
		protected ImageView mImageViewBulb;
		
		
		protected FrameLayout mUIFlashlight;
		protected LinearLayout mUIMain;
		protected LinearLayout mUIWarningLight;
		protected LinearLayout mUIMorse;
		protected FrameLayout mUIBulb;
		protected FrameLayout mUIColorLight;
		protected FrameLayout mUIPoliceLight;
		protected LinearLayout mUISettings;
		
		protected UIType mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
		protected UIType mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
		
		protected int mDefaultScreenBrightness;
		
		protected int mFinishCount = 0;
		
		protected SeekBar mSeekBarWarningLight;
		protected SeekBar mSeekBarPoliceLight;
		protected Button mButtonAddShortcut;
		protected Button mButtonRemoveShortcut;
		
		protected SharedPreferences mSharedPreferences;
		
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        mUIFlashlight = (FrameLayout) findViewById(R.id.framelayout_flashlight);
	        mUIMain =(LinearLayout) findViewById(R.id.linearlayout_main);
	        mUIWarningLight = (LinearLayout) findViewById(R.id.linearlayout_warning_light);
	        mUIMorse = (LinearLayout) findViewById(R.id.linearlayout_morse);
	        mUIBulb = (FrameLayout) findViewById(R.id.framelayout_bulb);
	        mUIColorLight = (FrameLayout) findViewById(R.id.framelayout_color_light);
	        mUIPoliceLight = (FrameLayout) findViewById(R.id.framelayout_police_light);
	        mUISettings = (LinearLayout) findViewById(R.id.linearlayout_settings);
	        
	        mImageViewFlashLight=(ImageView) findViewById(R.id.imageview_flashlight);
	        mImageViewFlashLightController = (ImageView) findViewById(R.id.imageview_flashlight_controller);
	        mImageViewWarningLight1 =(ImageView) findViewById(R.id.imageview_warning_light1);
	        mImageViewWarningLight2 =(ImageView) findViewById(R.id.imageview_warning_light2);
	        mImageViewBulb = (ImageView) findViewById(R.id.imageview_bulb);
	       
	        mSeekBarWarningLight = (SeekBar) findViewById(R.id.seekbar_warning_light);
	        mSeekBarPoliceLight = (SeekBar) findViewById(R.id.seekbar_police_light);
	      
	        
	        mEditTextMorseCode = (EditText) findViewById(R.id.editext_morse_code);
	        mDefaultScreenBrightness = getScreenBrightness();
	        
	        mSharedPreferences = getSharedPreferences("config",Context.MODE_PRIVATE);
	        
			mCurrentWarningLightInterval = mSharedPreferences.getInt(
					"warning_light_interval", 200);
			mCurrentPoliceLightInterval = mSharedPreferences.getInt(
					"police_light_interval", 100);
			
			mSeekBarPoliceLight.setProgress(mCurrentPoliceLightInterval-50);
	        mSeekBarWarningLight.setProgress(mCurrentWarningLightInterval-100);
	        mButtonAddShortcut = (Button) findViewById(R.id.button_add_shortcut);
			mButtonRemoveShortcut = (Button) findViewById(R.id.button_remove_shortcut);
	        
;	    }
		
		protected void hideAllUI(){
			mUIMain.setVisibility(View.GONE);
			mUIFlashlight.setVisibility(View.GONE);
			mUIWarningLight.setVisibility(View.GONE);
			mUIMorse.setVisibility(View.GONE);
			mUIBulb.setVisibility(View.GONE);
			mUIColorLight.setVisibility(View.GONE);
			mUIPoliceLight.setVisibility(View.GONE);
			mUISettings.setVisibility(View.GONE);
		}
		
		protected void screenBrightness(float value){
			try{
				WindowManager.LayoutParams layout = getWindow().getAttributes();
				layout.screenBrightness = value;
				getWindow().setAttributes(layout);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		protected int getScreenBrightness(){
			int value = 0;
			try{
				value = android.provider.Settings.System.getInt(getContentResolver(),android.provider.Settings.System.SCREEN_BRIGHTNESS);
			}catch(Exception e){
				
			}
			return value;
		}
		
		public boolean dispatchTouchEvent(MotionEvent ev) {
			mFinishCount=0;
			
			return super.dispatchTouchEvent(ev);
		}
		
		public void finish()
		{
			mFinishCount++;
			if(mFinishCount == 1)
			{
				Toast.makeText(this, "�ٰ�һ���˳���", Toast.LENGTH_LONG).show();
			}
			else if(mFinishCount == 2)
			{
				super.finish();
			}
		}
}
