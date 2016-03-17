package com.dmcinfo.cutthroatpooltracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ottog on 3/15/2016.
 */
public class PlayerDB extends DBHelper {
    public PlayerDB (Context context) {
        super(context);
    }

    private static final String COL_ID = "_id";
    private static final String COL1 = "FirstName";
    private static final String COL2 = "LastName";
    private static final String COL3 = "Wins";

    public void addPlayer(String FirstName, String LastName ){
        if(!playerExists(FirstName,LastName)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COL1, FirstName);
            values.put(COL2, LastName);
            db.insert(PLAYER_TABLE, null, values);
            db.close();
        }
    };

    public String getPlayer(int Row){
        String name = "none";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+ PLAYER_TABLE +" WHERE _id=" + Row;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){

            do {
                name = cursor.getString(1);
            } while (cursor.moveToNext());
        }

        db.close();

        return name;
    }

    public boolean playerExists(String FName, String LName){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + PLAYER_TABLE + " WHERE FirstName = ? AND LastName = ?";
        Cursor c = db.rawQuery(query, new String[] {FName,LName});
        if (c.moveToFirst())
        {
            return c.getInt(0) > 0;
        }
        else {
            return false;
        }
    }
}
