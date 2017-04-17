package com.huangyu.library.app;

import android.os.Environment;

import java.io.File;

/**
 * 常量类
 * Created by huangyu on 2017-4-10.
 */
public class BaseConstants {

    public static final String APP_NAME = "MDEditor";

    public static final String ROOT_PATH = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_NAME + File.separator : BaseApplication.getAppContext().getCacheDir() + File.separator;

    public static final String LOG_PATH = ROOT_PATH + "Log" + File.separator;

    public static final String API_URL = "";

}
