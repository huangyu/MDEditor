package com.huangyu.mdeditor.app;

import com.huangyu.library.app.BaseApplication;

import io.realm.Realm;

/**
 * Created by huangyu on 2017-4-17.
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
