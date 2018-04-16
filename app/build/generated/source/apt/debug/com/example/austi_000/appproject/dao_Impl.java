package com.example.austi_000.appproject;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;

public class dao_Impl implements dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfdate_entity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateEvent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteEvent;

  public dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfdate_entity = new EntityInsertionAdapter<date_entity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `dates`(`id`,`date`,`title`,`des`,`startTime`,`endTime`,`allDay`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, date_entity value) {
        stmt.bindLong(1, value.id);
        if (value.date == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.date);
        }
        if (value.title == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.title);
        }
        if (value.des == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.des);
        }
        if (value.startTime == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.startTime);
        }
        if (value.endTime == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.endTime);
        }
        if (value.allDay == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.allDay);
        }
      }
    };
    this.__preparedStmtOfUpdateEvent = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE dates SET title =?, des =?, startTime =?, endTime =?, allDay =? WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteEvent = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM dates WHERE id =?";
        return _query;
      }
    };
  }

  @Override
  public long insertDate(date_entity toInsert) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfdate_entity.insertAndReturnId(toInsert);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateEvent(String title, String des, String start, String end, String allday,
      int givenid) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateEvent.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (title == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, title);
      }
      _argIndex = 2;
      if (des == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, des);
      }
      _argIndex = 3;
      if (start == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, start);
      }
      _argIndex = 4;
      if (end == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, end);
      }
      _argIndex = 5;
      if (allday == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, allday);
      }
      _argIndex = 6;
      _stmt.bindLong(_argIndex, givenid);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateEvent.release(_stmt);
    }
  }

  @Override
  public int deleteEvent(int givenid) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteEvent.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, givenid);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteEvent.release(_stmt);
    }
  }

  @Override
  public date_entity[] loadAllDates() {
    final String _sql = "SELECT * FROM dates";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDes = _cursor.getColumnIndexOrThrow("des");
      final int _cursorIndexOfStartTime = _cursor.getColumnIndexOrThrow("startTime");
      final int _cursorIndexOfEndTime = _cursor.getColumnIndexOrThrow("endTime");
      final int _cursorIndexOfAllDay = _cursor.getColumnIndexOrThrow("allDay");
      final date_entity[] _result = new date_entity[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final date_entity _item;
        _item = new date_entity();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _item.date = _cursor.getString(_cursorIndexOfDate);
        _item.title = _cursor.getString(_cursorIndexOfTitle);
        _item.des = _cursor.getString(_cursorIndexOfDes);
        _item.startTime = _cursor.getString(_cursorIndexOfStartTime);
        _item.endTime = _cursor.getString(_cursorIndexOfEndTime);
        _item.allDay = _cursor.getString(_cursorIndexOfAllDay);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public date_entity[] loadAllEvents(String selectedDate) {
    final String _sql = "SELECT * FROM dates where date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (selectedDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, selectedDate);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDes = _cursor.getColumnIndexOrThrow("des");
      final int _cursorIndexOfStartTime = _cursor.getColumnIndexOrThrow("startTime");
      final int _cursorIndexOfEndTime = _cursor.getColumnIndexOrThrow("endTime");
      final int _cursorIndexOfAllDay = _cursor.getColumnIndexOrThrow("allDay");
      final date_entity[] _result = new date_entity[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final date_entity _item;
        _item = new date_entity();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _item.date = _cursor.getString(_cursorIndexOfDate);
        _item.title = _cursor.getString(_cursorIndexOfTitle);
        _item.des = _cursor.getString(_cursorIndexOfDes);
        _item.startTime = _cursor.getString(_cursorIndexOfStartTime);
        _item.endTime = _cursor.getString(_cursorIndexOfEndTime);
        _item.allDay = _cursor.getString(_cursorIndexOfAllDay);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int dateQuery(String selectedDate) {
    final String _sql = "SELECT * FROM dates WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (selectedDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, selectedDate);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
