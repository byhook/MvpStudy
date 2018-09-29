package com.onzhou.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.onzhou.base.AbsBaseActivity;
import com.onzhou.hook.HookActivity;
import com.onzhou.plugin.PluginActivity;
import com.onzhou.utils.AssetsUtils;
import com.onzhou.plugin.IApkPlugin;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AbsBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPluginClick(View view) {
        PluginActivity.intentStart(this);
    }

    public void onHookClick(View view) {
        HookActivity.intentStart(this);
    }
}
