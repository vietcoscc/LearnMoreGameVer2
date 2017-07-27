package com.example.nguynqucvit.learnmoregamever2.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by viet on 26/07/2017.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "learnmoregame";
    public static final String TABLE_GAME = "GAME";
    public static final String TABLE_GAME_2 = "GAME2";
    public static final String ID = "ID";
    public static final String IMAGE_URL = "IMAGE_URL";
    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String DATE = "DATE";
    public static final String VIEWS = "VIEWS";
    public static final String DETAILS_URL = "DETAILS_URL";
    public static final String CONTENT_HTML = "CONTENT_HTML";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE  TABLE " + TABLE_GAME + " (" +
                ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                IMAGE_URL + " VARCHAR NOT NULL ," +
                NAME + " VARCHAR NOT NULL , " +
                TYPE + " VARCHAR NOT NULL ," +
                DATE + " VARCHAR NOT NULL ," +
                VIEWS + " VARCHAR NOT NULL, " +
                DETAILS_URL + " VARCHAR NOT NULL )";
        sqLiteDatabase.execSQL(sql);
        String sql2 = "CREATE  TABLE " + TABLE_GAME_2 + " (" +
                ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                IMAGE_URL + " VARCHAR NOT NULL ," +
                NAME + " VARCHAR NOT NULL , " +
                TYPE + " VARCHAR NOT NULL ," +
                DATE + " VARCHAR NOT NULL ," +
                VIEWS + " VARCHAR NOT NULL, " +
                DETAILS_URL + " VARCHAR NOT NULL ," +
                CONTENT_HTML + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_2);
        onCreate(sqLiteDatabase);
    }
}
