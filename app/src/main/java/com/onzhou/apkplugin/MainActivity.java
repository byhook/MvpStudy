package com.onzhou.apkplugin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.onzhou.common.utils.AssetsUtils;
import com.onzhou.plugin.IApkPlugin;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String PLUGIN_NAME = "skin-plugin-debug.apk";

    private DexClassLoader mDexClassLoader;

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
        setContentView(R.layout.activity_main);
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
