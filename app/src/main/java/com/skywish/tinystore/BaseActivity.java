package com.skywish.tinystore;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.skywish.tinystore.config.Constants;

import cn.bmob.v3.Bmob;

/**
 * Created by skywish on 2015/3/17.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化Bmob
        Bmob.initialize(this, Constants.Bmob_APPID);
        // 没有title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d("BaseActivity",getClass().getSimpleName());
    }
}
