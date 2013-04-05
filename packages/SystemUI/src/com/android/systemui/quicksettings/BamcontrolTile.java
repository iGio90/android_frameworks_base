package com.android.systemui.quicksettings;

import android.app.AlertDialog;
import android.app.Profile;
import android.app.ProfileManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.android.server.ProfileManagerService;
import com.android.systemui.R;
import com.android.systemui.statusbar.phone.QuickSettingsContainerView;
import com.android.systemui.statusbar.phone.QuickSettingsController;

public class BamcontrolTile extends QuickSettingsTile{

    public BamcontrolTile(Context context, LayoutInflater inflater,
            QuickSettingsContainerView container, QuickSettingsController qsc) {
        super(context, inflater, container, qsc);

        mOnClick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.settings.BAM_CONTROL");
                startSettingsActivity(intent);
            }
        };
    }

    @Override
    void onPostCreate() {
        updateTile();
        super.onPostCreate();
    }

    @Override
    public void updateResources() {
        updateTile();
        super.updateResources();
    }

    private synchronized void updateTile() {
        mDrawable = R.drawable.ic_qs_settings;
        mLabel = mContext.getString(R.string.quick_settings_bamcontrol_label);
    }
}
