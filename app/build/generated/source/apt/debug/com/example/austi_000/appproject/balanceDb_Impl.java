package com.example.austi_000.appproject;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class balanceDb_Impl extends balanceDb {
  private volatile dao _dao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `dates` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT, `title` TEXT, `des` TEXT, `startTime` TEXT, `endTime` TEXT, `allDay` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"4db43e066b30d27386abec330a405ca9\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `dates`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDates = new HashMap<String, TableInfo.Column>(7);
        _columnsDates.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDates.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsDates.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsDates.put("des", new TableInfo.Column("des", "TEXT", false, 0));
        _columnsDates.put("startTime", new TableInfo.Column("startTime", "TEXT", false, 0));
        _columnsDates.put("endTime", new TableInfo.Column("endTime", "TEXT", false, 0));
        _columnsDates.put("allDay", new TableInfo.Column("allDay", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDates = new TableInfo("dates", _columnsDates, _foreignKeysDates, _indicesDates);
        final TableInfo _existingDates = TableInfo.read(_db, "dates");
        if (! _infoDates.equals(_existingDates)) {
          throw new IllegalStateException("Migration didn't properly handle dates(com.example.austi_000.appproject.date_entity).\n"
                  + " Expected:\n" + _infoDates + "\n"
                  + " Found:\n" + _existingDates);
        }
      }
    }, "4db43e066b30d27386abec330a405ca9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "dates");
  }

  @Override
  public dao dateDao() {
    if (_dao != null) {
      return _dao;
    } else {
      synchronized(this) {
        if(_dao == null) {
          _dao = new dao_Impl(this);
        }
        return _dao;
      }
    }
  }
}
