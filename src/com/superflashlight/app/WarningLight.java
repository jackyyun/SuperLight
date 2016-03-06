package com.superflashlight.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WarningLight extends FlashLight {
	
	protected boolean mWarningLightFlicker; // true false
	protected boolean mWarningLightState; //true on-off, false: off-on
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mWarningLightFlicker = true;
	}
	
	class WarningLightThread extends Thread{
		public void run(){
			
			mWarningLightFlicker = true;
			while(mWarningLightFlicker){
				try{
					Thread.sleep(mCurrentWarningLightInterval);
					mWarningHandler.sendEmptyMessage(0);
				}catch(Exception e){
					//
				}
			}
		}
	}
	
	private Handler mWarningHandler = new Handler(){
		public void handleMessage(Message msg){
			Log.d("Message","switch warning light in WarningLight");
			if(mWarningLightState){
				mImageViewWarningLight1.setImageResource(R.drawable.warning_light_on);
				mImageViewWarningLight2.setImageResource(R.drawable.warning_light_off);
				mWarningLightState = false;
			}else {
				mImageViewWarningLight1.setImageResource(R.drawable.warning_light_off);
				mImageViewWarningLight2.setImageResource(R.drawable.warning_light_on);
				mWarningLightState = true;
			}
		}
	};
	
}
