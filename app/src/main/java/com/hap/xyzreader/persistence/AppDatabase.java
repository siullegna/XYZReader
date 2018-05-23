package com.hap.xyzreader.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.hap.xyzreader.XYZReaderApplication;
import com.hap.xyzreader.persistence.converter.DateConverter;
import com.hap.xyzreader.persistence.dao.ArticleDao;
import com.hap.xyzreader.persistence.entity.ArticleEntity;

/**
 * Created by luis on 5/8/18.
 */
@Database(entities = {ArticleEntity.class}, version = DatabaseInfo.DB_VERSION, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract ArticleDao articleDao();

    public static AppDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(XYZReaderApplication.getInstance().getApplicationContext(), AppDatabase.class, DatabaseInfo.DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
