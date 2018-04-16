package com.example.austi_000.appproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by austi_000 on 3/15/2018.
 */

@Database(entities = date_entity.class, version = 2)
public abstract class balanceDb extends RoomDatabase {

    private static balanceDb INSTANCE;

    public abstract dao dateDao();

    public static balanceDb getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), balanceDb.class, "date-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}