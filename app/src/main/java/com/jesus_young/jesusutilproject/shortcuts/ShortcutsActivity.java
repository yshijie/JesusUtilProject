package com.jesus_young.jesusutilproject.shortcuts;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jesus_young.jesusutilproject.R;
import com.jesus_young.jesusutilproject.animation_test.layout_animation.LayoutAnimationActivity;
import com.jesus_young.jesusutilproject.share_card.ShareCardActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BooleanSupplier;

public class ShortcutsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcuts);

        findViewById(R.id.btn_set_dynamic_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDynamicShortcuts();
                Toast.makeText(ShortcutsActivity.this, "设置动态快捷方式成功", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_remove_dynamic_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDynamicShortcuts();
            }
        });

        findViewById(R.id.btn_set_pinned_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPinnedShortcuts();
            }
        });

        findViewById(R.id.btn_set_more_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMoreIntentShortcut();
            }
        });
    }

    /**
     * 创建动态快捷方式
     */
    @TargetApi(Build.VERSION_CODES.N_MR1)
    private ShortcutInfo createShortcutInfo_1() {
        return new ShortcutInfo.Builder(this, "dynamic_id_1")
                .setShortLabel(getString(R.string.dynamic_shortcut_short_label_1))
                .setLongLabel(getString(R.string.dynamic_shortcut_long_label_1))
                .setIcon(Icon.createWithResource(this, R.drawable.anim_menu_msg))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/")))
                .build();
    }

    /**
     * 创建动态快捷方式，设置字体颜色
     * // TODO 颜色未生效
     */
    @TargetApi(Build.VERSION_CODES.N_MR1)
    private ShortcutInfo createShortcutInfo_2() {
        Intent intent = new Intent(this, LayoutAnimationActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra("key", "fromDynamicShortcut");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
        String label = getResources().getString(R.string.dynamic_shortcut_short_label_2);
        SpannableStringBuilder colouredLabel = new SpannableStringBuilder(label);
        colouredLabel.setSpan(colorSpan, 0, label.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return new ShortcutInfo.Builder(this, "dynamic_id_2")
                .setShortLabel(colouredLabel)
                .setLongLabel(getString(R.string.dynamic_shortcut_long_label_2))
                .setIcon(Icon.createWithResource(this, R.drawable.anim_menu_setting))
                .setIntent(intent)
                .build();
    }

    /**
     * 创建动态快捷方式
     */
    private void setDynamicShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            List<ShortcutInfo> shortcutInfo = new ArrayList<>();
            shortcutInfo.add(createShortcutInfo_1());
            shortcutInfo.add(createShortcutInfo_2());
            if (shortcutManager != null) {
                shortcutManager.setDynamicShortcuts(shortcutInfo);
//                // 扩充一组动态快捷方式
//                shortcutManager.addDynamicShortcuts(shortcutInfo);
//                // 更新一组动态快捷方式
//                shortcutManager.updateShortcuts(shortcutInfo);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void removeDynamicShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
//            // 移除所有动态快捷方式
//            shortcutManager.removeAllDynamicShortcuts();
            // 移除一组动态快捷方式
            List<String> shortcutName = new ArrayList<>();
            shortcutName.add("dynamic_id_1");
            shortcutName.add("dynamic_id_2");
            shortcutManager.removeDynamicShortcuts(shortcutName);
        }
    }

    /**
     * 创建桌面快捷方式
     */
    private void createPinnedShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                Intent intent = new Intent(this, ShareCardActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.putExtra("key", "fromPinnedShortcut");
                ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(this, "pin_shortcut_id")
                        .setShortLabel(getString(R.string.dynamic_pinned_short_label_2))
                        .setLongLabel(getString(R.string.dynamic_pinned_long_label_2))
                        .setIcon(Icon.createWithResource(this, R.drawable.anim_menu_data))
                        .setIntent(intent)
                        .build();
                Intent pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(pinShortcutInfo);
                PendingIntent successCallback = PendingIntent.getBroadcast(this, 0, pinnedShortcutCallbackIntent, 0);
                boolean b = shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.getIntentSender());
                Toast.makeText(this, "set pinned shortcuts " + (b ? "success" : "failed") + "!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 创建多个Intent的Shortcut
     */
    @TargetApi(Build.VERSION_CODES.N_MR1)
    private ShortcutInfo createMoreShortcutInfo() {
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
        Intent intent = new Intent(this, LayoutAnimationActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra("key", "fromDynamicShortcut");
        return new ShortcutInfo.Builder(this, "dynamic_id_3")
                .setShortLabel(getString(R.string.dynamic_pinned_short_label_2))
                .setLongLabel(getString(R.string.dynamic_pinned_long_label_2))
                .setIcon(Icon.createWithResource(this, R.drawable.anim_menu_beach))
                .setIntents(new Intent[]{intent, intent1})
                .build();
    }

    /**
     * 添加多个Intent的Shortcut
     */
    private void createMoreIntentShortcut() {
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            List<ShortcutInfo> shortcutInfo = new ArrayList<>();
            shortcutInfo.add(createMoreShortcutInfo());
            if (shortcutManager != null) {
                shortcutManager.setDynamicShortcuts(shortcutInfo);
            }
        }
    }

    /**
     * 监听系统语言变化做适配
     *
     */
    private class MyReceiver extends BroadcastReceiver {
        private static final String TAG = "tag";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: " + intent);
            if (Intent.ACTION_LOCALE_CHANGED.equals(intent.getAction())) {
                // Refresh all shortcut to update the labels.
                // (Right now shortcut labels don't contain localized strings though.)
                if (isDeviceSupportShortcuts()) {
                    refreshShortcuts();
                }
            }
        }
    }

    public static boolean isDeviceSupportShortcuts() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1;
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    public void refreshShortcuts() {
        final ShortcutManager mShortcutManager = getSystemService(ShortcutManager.class);
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("tag", "refreshingShortcuts...");
                // Check all existing dynamic and pinned shortcut, and if their last refresh
                // time is older than a certain threshold, update them.
                final List<ShortcutInfo> updateList = new ArrayList<>();
                for (ShortcutInfo shortcut : getShortcuts()) {
                    Log.i("tag", shortcut.getId() + " is immutable: " + shortcut.isImmutable());
                    if (shortcut.isImmutable()) {
                        continue;
                    }
                    Log.i("tag", "Refreshing shortcut: " + shortcut.getId());
                    final ShortcutInfo.Builder b = new ShortcutInfo.Builder(ShortcutsActivity.this, shortcut.getId());
                    setSiteInformation(b, shortcut.getIntent().getData());
                    updateList.add(b.build());
                }
                // Call update.
                if (updateList.size() > 0) {
                    callShortcutManager(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            Log.i("tag", "update short cuts size: " + updateList.size());
                            return mShortcutManager.updateShortcuts(updateList);
                        }
                    });
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private void callShortcutManager(BooleanSupplier r) {
        try {
            if (!r.getAsBoolean()) {
                Toast.makeText(ShortcutsActivity.this, "Call to ShortcutManager is rate-limited", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("tag", "Caught Exception", e);
            Toast.makeText(ShortcutsActivity.this,  "Error while calling ShortcutManager: ", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public List<ShortcutInfo> getShortcuts() {
        ShortcutManager mShortcutManager = getSystemService(ShortcutManager.class);
        // Load mutable dynamic shortcuts and pinned shortcuts and put them into a single list
        // removing duplicates.
        final List<ShortcutInfo> ret = new ArrayList<>();
        final HashSet<String> seenKeys = new HashSet<>();

        // Check existing shortcuts shortcuts
        Log.d("tag", "getDynamicShortcuts size: " + mShortcutManager.getDynamicShortcuts().size());
        for (ShortcutInfo shortcut : mShortcutManager.getDynamicShortcuts()) {
            Log.d("tag", "dynamic shortcuts id: " + shortcut.getId());
            if (!shortcut.isImmutable()) {
                ret.add(shortcut);
                seenKeys.add(shortcut.getId());
            }
        }
        Log.d("tag", "getPinnedShortcuts size: " + mShortcutManager.getPinnedShortcuts().size());
        for (ShortcutInfo shortcut : mShortcutManager.getPinnedShortcuts()) {
            Log.d("tag", "pinned shortcuts id: " + shortcut.getId());
            if (!shortcut.isImmutable() && !seenKeys.contains(shortcut.getId())) {
                ret.add(shortcut);
                seenKeys.add(shortcut.getId());
            }
        }
        return ret;
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private ShortcutInfo.Builder setSiteInformation(ShortcutInfo.Builder b, Uri uri) {
        if (uri == null) return b;
        b.setShortLabel(uri.getHost());
        b.setLongLabel(uri.toString());

        Bitmap bmp = fetchFavicon(uri);
        if (bmp != null) {
            b.setIcon(Icon.createWithBitmap(bmp));
        } else {
            b.setIcon(Icon.createWithResource(ShortcutsActivity.this, R.drawable.anim_menu_beach));
        }

        return b;
    }

    private Bitmap fetchFavicon(Uri uri) {
        final Uri iconUri = uri.buildUpon().path("img/avatar.png").build();

        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            URLConnection conn = new URL(iconUri.toString()).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            return BitmapFactory.decodeStream(bis);
        } catch (IOException e) {
            return null;
        }
    }
}
