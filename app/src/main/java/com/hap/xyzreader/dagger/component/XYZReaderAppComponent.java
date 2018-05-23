package com.hap.xyzreader.dagger.component;

import com.hap.xyzreader.dagger.module.ContextModule;
import com.hap.xyzreader.dagger.module.DatabaseModule;
import com.hap.xyzreader.dagger.module.NetworkModule;
import com.hap.xyzreader.dagger.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by luis on 5/8/18.
 */
@ApplicationScope
@Component(modules = {NetworkModule.class, DatabaseModule.class, ContextModule.class})
public interface XYZReaderAppComponent extends AppGraph {
}
