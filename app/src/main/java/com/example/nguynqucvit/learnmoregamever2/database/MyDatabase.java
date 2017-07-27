package com.example.nguynqucvit.learnmoregamever2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGameFull;

import java.util.ArrayList;

import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.CONTENT_HTML;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.DATE;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.DETAILS_URL;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.IMAGE_URL;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.NAME;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.TABLE_GAME;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.TABLE_GAME_2;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.TYPE;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.VIEWS;

/**
 * Created by viet on 26/07/2017.
 */

public class MyDatabase {
    private static final String TAG = "MyDatabase";
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private Context context;

    public MyDatabase(Context context) {
        this.context = context;
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

    public long insertGame(ItemGame itemGame) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IMAGE_URL, itemGame.getImageUrl());
        contentValues.put(NAME, itemGame.getName());
        contentValues.put(TYPE, itemGame.getType());
        contentValues.put(DATE, itemGame.getDate());
        contentValues.put(VIEWS, itemGame.getViews());
        contentValues.put(DETAILS_URL, itemGame.getDetailsUrl());

        SQLiteDatabase database = mySQLiteOpenHelper.getWritableDatabase();
        return database.insert(TABLE_GAME, null, contentValues);
    }

    public long insertGameFull(ItemGame itemGame, String contentHtml) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IMAGE_URL, itemGame.getImageUrl());
        contentValues.put(NAME, itemGame.getName());
        contentValues.put(TYPE, itemGame.getType());
        contentValues.put(DATE, itemGame.getDate());
        contentValues.put(VIEWS, itemGame.getViews());
        contentValues.put(DETAILS_URL, itemGame.getDetailsUrl());
        contentValues.put(CONTENT_HTML, contentHtml);
        SQLiteDatabase database = mySQLiteOpenHelper.getWritableDatabase();
        return database.insert(TABLE_GAME_2, null, contentValues);
    }

    public ArrayList<ItemGame> getAllGame() {
        ArrayList<ItemGame> arrItemGame = new ArrayList<>();
        SQLiteDatabase database = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(TABLE_GAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String imageUrl = cursor.getString(cursor.getColumnIndex(IMAGE_URL));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String type = cursor.getString(cursor.getColumnIndex(TYPE));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String views = cursor.getString(cursor.getColumnIndex(VIEWS));
            String detailsUrl = cursor.getString(cursor.getColumnIndex(DETAILS_URL));
            ItemGame itemGame = new ItemGame(0, imageUrl, name, type, date, views, detailsUrl);
            Log.i(TAG, imageUrl + name + type + date + views + detailsUrl);
            arrItemGame.add(itemGame);
            cursor.moveToNext();
        }
        database.close();
        return arrItemGame;
    }

    public ArrayList<ItemGameFull> getAllGameFull() {
        ArrayList<ItemGameFull> arrItemGameFull = new ArrayList<>();
        SQLiteDatabase database = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(TABLE_GAME_2, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String imageUrl = cursor.getString(cursor.getColumnIndex(IMAGE_URL));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String type = cursor.getString(cursor.getColumnIndex(TYPE));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String views = cursor.getString(cursor.getColumnIndex(VIEWS));
            String detailsUrl = cursor.getString(cursor.getColumnIndex(DETAILS_URL));
            String htmlContent = cursor.getString(cursor.getColumnIndex(CONTENT_HTML));
            ItemGame itemGame = new ItemGame(0, imageUrl, name, type, date, views, detailsUrl);
            ItemGameFull itemGameFull = new ItemGameFull(itemGame, htmlContent);
            Log.i(TAG, imageUrl + name + type + date + views + detailsUrl);
            arrItemGameFull.add(itemGameFull);
            cursor.moveToNext();
        }
        database.close();
        return arrItemGameFull;
    }

    public void deleteAllGame() {
        SQLiteDatabase database = mySQLiteOpenHelper.getWritableDatabase();
        database.delete(TABLE_GAME, null, null);
        database.close();
    }

}
