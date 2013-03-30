package com.android.systemui.quicksettings;

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
import android.widget.TextView;

import com.android.systemui.R;
import com.android.systemui.statusbar.phone.QuickSettingsController;
import com.android.systemui.statusbar.phone.QuickSettingsContainerView;

public class BamControlTile extends QuickSettingsTile {

    private static final String BAMCONTROL_PKG = "com.android.settings";

    private String mDefaultLabel;
    private String mPackageName;
    private String mSourceDir;
    private String mStatus;

    private PackageManager mPm;

    public BamControlTile(Context context, LayoutInflater inflater,
            QuickSettingsContainerView container, QuickSettingsController qsc, Handler handler) {
        super(context, inflater, container, qsc);

        mLabel = mContext.getString(R.string.quick_settings_bamcontrol);
        mDrawable = R.drawable.ic_settings_bamcontrol;

        mPm = context.getPackageManager();

        mOnClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(new ComponentName(BAMCONTROL_PKG,
                            BAMCONTROL_PKG + ".jellybam.BAMControl"));
                    mContext.startActivity(intent);
                    mQsc.mBar.collapseAllPanels(true);
                } catch(NullPointerException e) {
                    // No intent found for activity component
                }
            }
        };

        mOnLongClick = new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(new ComponentName(BAMCONTROL_PKG,
                            BAMCONTROL_PKG + ".jellybam.BAMControl"));
                    mContext.startActivity(intent);
                    mQsc.mBar.collapseAllPanels(true);
                } catch(NullPointerException e) {
                    // No intent found for activity component
                }
                return true;
            }
        };
        qsc.registerObservedContent(Settings.System.getUriFor(
                Settings.System.FOREGROUND_APP), this);
    }
}
