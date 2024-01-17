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
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";

    public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE " + USER_TABLE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_PASSWORD + " INT)";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE_STATEMENT); }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { }

    public boolean addUser(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userModel.getName());
        cv.put(COLUMN_USER_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteUser (UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USER_TABLE + " WHERE " + COLUMN_ID + " = " + userModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            db.execSQL(queryString);
        }

        cursor.close();
        return true;
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int userId = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userPassword = cursor.getString(2);

                UserModel newUser = new UserModel(userId, userName, userPassword);
                returnList.add(newUser);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  returnList;
    }

    public void clearDb() {
        String dropTableStatement = "DROP TABLE " + USER_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(dropTableStatement);
        db.execSQL(CREATE_TABLE_STATEMENT);
        db.close();
    }
}
