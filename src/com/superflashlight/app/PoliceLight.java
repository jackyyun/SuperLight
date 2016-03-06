package com.superflashlight.app;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.superflashlight.app.widget.HideTextView;

public class PoliceLight extends ColorLight {
		protected boolean mPoliceState;
		protected HideTextView mHideTextViewPoliceLight;
		
		protected void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			mHideTextViewPoliceLight = (HideTextView) findViewById(R.id.textview_hide_police_light);
		}
		
		
		
		class PoliceThread extends Thread{
			
			private void sleep(int t){
				try{
					Thread.sleep(t);
				}catch(Exception e){
					
				}
			}
			
			public void run(){
				mPoliceState = true;
				while(mPoliceState){
					mHandler.sendEmptyMessage(Color.BLUE);
					sleep(mCurrentPoliceLightInterval);
					mHandler.sendEmptyMessage(Color.BLACK);
					sleep(mCurrentPoliceLightInterval);
					mHandler.sendEmptyMessage(Color.RED);
					sleep(mCurrentPoliceLightInterval);
					mHandler.sendEmptyMessage(Color.BLACK);
					sleep(mCurrentPoliceLightInterval);
				}
			}
		}
		
		private Handler mHandler = new Handler(){
			public void handleMessage(Message message){
				int color = message.what;
				mUIPoliceLight.setBackgroundColor(color);
			}
		};
}
