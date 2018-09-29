package com.onzhou.plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.onzhou.base.AbsBaseActivity;
import com.onzhou.hook.HookActivity;
import com.onzhou.main.*;
import com.onzhou.utils.AssetsUtils;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * @anchor: Andy
 * @date: 2018-09-29
 * @description:
 */
public class PluginActivity extends AbsBaseActivity {

    private static final String PLUGIN_NAME = "skin-plugin-debug.apk";

    private DexClassLoader mDexClassLoader;

    public static void intentStart(Context context) {
        Intent intent = new Intent(context, PluginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final File pluginFile = new File(getExternalCacheDir(), PLUGIN_NAME);
        if (!pluginFile.exists()) {
            AssetsUtils.extractAssets(this, getExternalCacheDir().getAbsolutePath(), PLUGIN_NAME);
        }
        String dexPath = getDir("dex", Context.MODE_PRIVATE).getAbsolutePath();
        mDexClassLoader = new DexClassLoader(pluginFile.getAbsolutePath(), dexPath, null, getClassLoader());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.onzhou.main.R.layout.activity_plugin);
    }

    public void onLoadPlugin(View view) {
        try {
            Class<?> pluginClass = mDexClassLoader.loadClass("com.onzhou.skinplugin.SkinPlugin");
            IApkPlugin apkPlugin = (IApkPlugin) pluginClass.newInstance();
            Toast.makeText(this, "hello: " + apkPlugin.getPluginName(), Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
