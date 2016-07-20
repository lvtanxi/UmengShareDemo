package com.sotao.esf;

import android.app.Application;

/**
 * User: 吕勇
 * Date: 2016-07-20
 * Time: 09:24
 * Description:
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UmengShare.initUmengData();
    }
}
