package com.dmcinfo.cutthroatpooltracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ottog on 3/15/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final  int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Cutthroat_Pool.sqlite3";
    protected static final String PLAYER_TABLE = "Players";

    private static final String CREATE_PLAYER_TABLE = "create table if not exists "
            + PLAYER_TABLE
            + " ( _id integer primary key autoincrement," +
            " FirstName TEXT NOT NULL," +
            " LastName TEXT NOT NULL);";

    private static final String DROP_PLAYER_TABLE = "drop table if exists " + PLAYER_TABLE;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME); Uncomment this if you need to delete the database
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER_TABLE);
    };

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //not sure what we want to do here, but this must be implemented
    };
};
