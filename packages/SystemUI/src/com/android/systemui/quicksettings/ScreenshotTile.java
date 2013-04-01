package com.android.systemui.quicksettings;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.ExtendedPropertiesUtils;
import android.util.ColorUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.TextView;

import com.android.systemui.R;
import com.android.systemui.statusbar.phone.QuickSettingsController;
import com.android.systemui.statusbar.phone.QuickSettingsContainerView;

public class ScreenshotTile extends QuickSettingsTile {

    public ScreenshotTile(Context context, LayoutInflater inflater,
            QuickSettingsContainerView container, QuickSettingsController qsc, Handler handler) {
        super(context, inflater, container, qsc);

        mLabel = mContext.getString(R.string.quick_settings_screenshot);
        mDrawable = R.drawable.ic_qs_screenshot;

        final Handler mHandler = new Handler();

	final Runnable mRunnable = new Runnable() {
                public void run() {
                Intent intent = new Intent(Intent.ACTION_SCREENSHOT);
                mContext.sendBroadcast(intent);
        }
    };

        mOnClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
	            mHandler.postDelayed(mRunnable, 500);
                    mQsc.mBar.collapseAllPanels(true);
            }
        };

        mOnLongClick = new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
	        int delay = Settings.System.getInt(mContext.getContentResolver(),
       		         Settings.System.SCREENSHOT_TOGGLE_DELAY, 5000);
        	final Toast toast = Toast.makeText(mContext,
                	String.format(mContext.getResources().getString(R.string.screenshot_toast),
                        	delay / 1000), Toast.LENGTH_SHORT);
	        toast.show();
        	// toast duration is not customizable, hack to show it only for 1 sec
        	mHandler.postDelayed(new Runnable() {
            	public void run() {
                toast.cancel();
            	}
	        }, 1000);
        	mHandler.postDelayed(mRunnable, delay);
                    mQsc.mBar.collapseAllPanels(true);
                return true;
            }
        };
    }
}
