package com.onzhou.main;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.widget.Toast;

import com.onzhou.hook.HookInstrumentation;
import com.onzhou.hook.HookManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @anchor: Andy
 * @date: 2018-09-29
 * @description:
 */
public class MainApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        HookManager.getInstance().init(this);
    }

    public static Context getContext() {
        return sContext;
    }
}
