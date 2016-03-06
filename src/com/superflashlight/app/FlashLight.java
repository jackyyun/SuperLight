package com.superflashlight.app;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class FlashLight extends BaseActivity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mImageViewFlashLight.setTag(false);
		Point point = new Point();
		getWindowManager().getDefaultDisplay().getSize(point);
		
		LayoutParams laParams = (LayoutParams)mImageViewFlashLightController.getLayoutParams();
		laParams.height = point.y*3/4;
		laParams.width = point.x/3;
		mImageViewFlashLightController.setLayoutParams(laParams);
	}
	
	public void onClick_Flashlight(View view){
		if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
			Toast.makeText(this,"当前设备没有闪光灯",Toast.LENGTH_SHORT).show();
			return;
		}
		if(((Boolean)mImageViewFlashLight.getTag())==false){
			TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();
			drawable.startTransition(200);
			openFlashlight();
		}else{
			TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();
			drawable.reverseTransition(200);
			closeFlashlight();
		}
	}
	
	public void openFlashlight(){
		//TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();
		//drawable.startTransition(200);
		mImageViewFlashLight.setTag(true);
		
		try{
			mCamera = Camera.open();
			int textureId = 0;
			mCamera.setPreviewTexture(new SurfaceTexture(textureId));
			mCamera.startPreview();
			
			mParameters = mCamera.getParameters();
			mParameters.setFlashMode(mParameters.FLASH_MODE_TORCH);
			mCamera.setParameters(mParameters);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void closeFlashlight(){
		//TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();
		if((Boolean)mImageViewFlashLight.getTag()){
			//drawable.reverseTransition(200);
			mImageViewFlashLight.setTag(false);
			if(mCamera != null){
				mParameters = mCamera.getParameters();
				mParameters.setFlashMode(mParameters.FLASH_MODE_OFF);
				mCamera.setParameters(mParameters);
				mCamera.stopPreview();
				mCamera.release();
				mCamera = null;
			}
		}
		
	}
	
	public void onPause(){
		super.onPause();
		closeFlashlight();
	}
	
}
