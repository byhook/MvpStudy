package com.onzhou.hook;

import android.app.Activity;
import android.app.Fragment;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.onzhou.base.AbsBaseActivity;
import com.onzhou.main.R;
import com.onzhou.plugin.PluginActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @anchor: Andy
 * @date: 2018-09-29
 * @description:
 */
public class HookActivity extends AbsBaseActivity {

    private static final String TAG = "HookActivity";

    public static void intentStart(Context context) {
        Intent intent = new Intent(context, HookActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
    }

    public void onStartClick(View view) {
        PluginActivity.intentStart(this);
    }



}
