package com.hap.xyzreader.network.api;

import com.hap.xyzreader.persistence.model.XYZReaderResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by luis on 5/8/18.
 */

public interface XYZReaderApi {
    @GET("/xyz-reader-json")
    Observable<ArrayList<XYZReaderResponse>> getBooks();
}
