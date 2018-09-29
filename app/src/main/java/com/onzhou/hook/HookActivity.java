package com.onzhou.hook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.onzhou.base.AbsBaseActivity;

/**
 * @anchor: Andy
 * @date: 2018-09-29
 * @description:
 */
public class HookActivity extends AbsBaseActivity {


    public static void intentStart(Context context) {
        Intent intent = new Intent(context, HookActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
