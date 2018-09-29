package com.onzhou.hook;

import android.app.Application;
import android.app.Instrumentation;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @anchor: Andy
 * @date: 2018-09-29
 * @description:
 */
public class HookManager {

    private static HookManager sInstance;

    public static HookManager getInstance() {
        if (sInstance == null) {
            sInstance = new HookManager();
        }
        return sInstance;
    }

    public void init(Application application) {
        Class<?> activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);

            Field instrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
            instrumentationField.setAccessible(true);
            Instrumentation originInstrumentation = (Instrumentation) instrumentationField.get(currentActivityThread);

            HookInstrumentation hookInstrumentation = new HookInstrumentation(originInstrumentation);
            instrumentationField.set(currentActivityThread, hookInstrumentation);

            Toast.makeText(application, "hook success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
