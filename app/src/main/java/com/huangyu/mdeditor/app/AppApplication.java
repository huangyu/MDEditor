package com.huangyu.mdeditor.app;

import com.huangyu.library.app.BaseApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by huangyu on 2017-4-17.
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("MDEditor.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

}
