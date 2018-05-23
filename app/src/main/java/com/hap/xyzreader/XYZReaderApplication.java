package com.hap.xyzreader;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.hap.xyzreader.dagger.component.DaggerXYZReaderAppComponent;
import com.hap.xyzreader.dagger.component.XYZReaderAppComponent;
import com.hap.xyzreader.dagger.module.ContextModule;
import com.hap.xyzreader.dagger.module.DatabaseModule;
import com.hap.xyzreader.dagger.module.NetworkModule;

/**
 * Created by luis on 5/8/18.
 */

public class XYZReaderApplication extends Application {
    private static XYZReaderApplication INSTANCE;
    private XYZReaderAppComponent xyzReaderAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        XYZReaderApplication.INSTANCE = this;

        xyzReaderAppComponent = DaggerXYZReaderAppComponent.builder()
                .networkModule(new NetworkModule())
                .contextModule(new ContextModule(this))
                .databaseModule(new DatabaseModule())
                .build();

        Stetho.initializeWithDefaults(this);

        Fresco.initialize(this);
    }

    public XYZReaderAppComponent getXyzReaderAppComponent() {
        return xyzReaderAppComponent;
    }

    public static XYZReaderApplication getInstance() {
        return (XYZReaderApplication) XYZReaderApplication.INSTANCE.getApplicationContext();
    }
}
