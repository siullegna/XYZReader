package com.hap.xyzreader.dagger.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hap.xyzreader.dagger.scope.ApplicationScope;
import com.hap.xyzreader.network.api.XYZReaderApi;
import com.hap.xyzreader.network.service.XYZReaderService;
import com.hap.xyzreader.util.XYXReaderSettings;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luis on 5/8/18.
 */
@Module
public class NetworkModule {
    private <T> T getApiAdapter(final OkHttpClient okHttpClient, final Class<T> clazz) {
        return new Retrofit.Builder()
                .baseUrl(XYXReaderSettings.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(clazz);
    }

    @Provides
    @ApplicationScope
    protected OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new StethoInterceptor());
        return builder.build();
    }

    @Provides
    @ApplicationScope
    protected XYZReaderService provideXYZReaderService(final OkHttpClient okHttpClient) {
        final XYZReaderApi restApi = getApiAdapter(okHttpClient, XYZReaderApi.class);
        return new XYZReaderService(restApi);
    }
}
