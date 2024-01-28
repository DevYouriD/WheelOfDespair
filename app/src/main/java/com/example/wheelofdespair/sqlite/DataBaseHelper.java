package com.example.wheelofdespair.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATA_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_INPUT = "INPUT";

    public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE " + DATA_TABLE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INPUT + " TEXT)";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "input.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE_STATEMENT); }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { }

    public boolean addData(DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INPUT, dataModel.getInput());

        long insert = db.insert(DATA_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteData (DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + DATA_TABLE + " WHERE " + COLUMN_ID + " = " + dataModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            db.execSQL(queryString);
        }

        cursor.close();
        return true;
    }

    public List<DataModel> getAllData() {
        List<DataModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + DATA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int inputId = cursor.getInt(0);
                String input = cursor.getString(1);

                DataModel newData = new DataModel(inputId, input);
                returnList.add(newData);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  returnList;
    }

    public List<String> getInputData() {
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT " + COLUMN_INPUT + " FROM " + DATA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String input = cursor.getString(0);
                returnList.add(input);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }

    public void clearDb() {
        String dropTableStatement = "DROP TABLE " + DATA_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(dropTableStatement);
        db.execSQL(CREATE_TABLE_STATEMENT);
        db.close();
    }
}
