package com.fengguncao.learnfit;

import android.app.Application;
import android.content.Context;

/**
 * name: App<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 6/18/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //进行热修复 java -jar  dx.jar --dex --output=output.dex  input.jar
        //Hotfix.installPatch(this, new File("/sdcard/output.dex"));
    }
}
