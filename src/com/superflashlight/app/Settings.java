package com.superflashlight.app;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Settings extends PoliceLight implements OnSeekBarChangeListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mSeekBarPoliceLight.setOnSeekBarChangeListener(this);
		mSeekBarWarningLight.setOnSeekBarChangeListener(this);
		
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		switch(seekBar.getId()){
		case R.id.seekbar_warning_light:
			mCurrentWarningLightInterval = progress + 100;
			break;
		case R.id.seekbar_police_light:
			mCurrentPoliceLightInterval = progress + 50;
			break;
		default:
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

		
//	private boolean shortcutInScreen() {
//		Cursor cursor = getContentResolver()
//				.query(Uri
//						.parse("content://com.yunlong.android.launcher3.settings/favorites?notify=true"),
//						null,
//						"intent like ?",
//						new String[] { "%component=com.superflashlight.app/.MainActivity%" },
//						null);
//
//		if (cursor.getCount() > 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	public void onClick_AddShortcut(View view) {
//			if(!shortcutInScreen()){
			Intent installShortcut = new Intent(
					"com.android.launcher.action.INSTALL_SHORTCUT");
			installShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "�����ֵ�Ͳ");
			Parcelable icon = Intent.ShortcutIconResource.fromContext(this,
					R.drawable.icon);

		
			Intent flashlightIntent = new Intent();
			flashlightIntent.setClassName("com.superflashlight.app",
					"com.superflashlight.app.MainActivity");
			flashlightIntent.setAction(Intent.ACTION_MAIN);
			flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			
			
			installShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
					flashlightIntent);

			sendBroadcast(installShortcut);
			
			Toast.makeText(this, "�ѳɹ�����ݷ�ʽ��ӵ����棡", Toast.LENGTH_LONG).show();
//			}else {
//				Toast.makeText(this, "��ݷ�ʽ�Ѿ���ӵ������ϣ��޷�����ӿ�ݷ�ʽ!", Toast.LENGTH_LONG)
//				.show();
//			}
	}

	public void onClick_RemoveShortcut(View view) {
//		if(shortcutInScreen()){
		Intent uninstallShortcut = new Intent(
				"com.android.launcher.action.UNINSTALL_SHORTCUT");
		uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "�����ֵ�Ͳ");

		Intent flashlightIntent = new Intent();
		flashlightIntent.setClassName("com.superflashlight.app",
				"com.superflashlight.app.MainActivity");
		flashlightIntent.setAction(Intent.ACTION_MAIN);
		flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		
		uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
				flashlightIntent);

		

		sendBroadcast(uninstallShortcut);
//		}else {
//			Toast.makeText(this, "û�п�ݷ�ʽ���޷�ɾ��", Toast.LENGTH_LONG).show();
//		}
	}

}
