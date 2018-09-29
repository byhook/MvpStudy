package com.onzhou.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.onzhou.main.MainApplication;

import java.lang.reflect.Method;

public class HookInstrumentation extends Instrumentation {

    Instrumentation mInstrumentation;

    public HookInstrumentation(Instrumentation instrumentation) {
        this.mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        Toast.makeText(MainApplication.getContext(), "hook一下", Toast.LENGTH_SHORT).show();
        try {
            Method method = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            return (ActivityResult) method.invoke(mInstrumentation, who, contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            throw new RuntimeException("execStartActivity failed");
        }
    }

}