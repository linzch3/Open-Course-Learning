package com.linzch3.lab8;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by linzch3 on 17/12/19.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "Contacts.db";
    public static final int DB_VERSION = 1;

    public DataBaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Contacts (id integer PRIMARY KEY AUTOINCREMENT, " +
                                                       "name text UNIQUE NOT NULL, " +
                                                       "birthday text, " +
                                                       "gift text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**********增删改查*****************/
    private void executeSQL(String SQL){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL(SQL);
        writableDatabase.close();
    }
    public void insertContact(String name, String birthday, String gift){
        String insertSQL = String.format("INSERT INTO Contacts(name, birthday, gift) values('%s', '%s', '%s');", name, birthday, gift);
        executeSQL(insertSQL);
    }

    public void deleteContact(String name){
        String deleteSQL = String.format("DELETE FROM Contacts WHERE name = '%s';", name);
        executeSQL(deleteSQL);
    }

    public void updateContact(String name, String birthday, String gift){
        String updateSQL = String.format("UPDATE Contacts SET birthday = '%s', gift = '%s' WHERE name = '%s';", birthday, gift, name);
        executeSQL(updateSQL);
    }

   public Cursor queryAllContact(){
       SQLiteDatabase writableDatabase = getWritableDatabase();
       return writableDatabase.rawQuery("SELECT * FROM Contacts;", null);
   }

   public boolean queryIfNameExist(String name){
       SQLiteDatabase writableDatabase = getWritableDatabase();
       String querySQL = String.format("SELECT * FROM Contacts WHERE name = '%s';", name);
       Cursor cursor = writableDatabase.rawQuery(querySQL, null);
       if(cursor.getCount()==1)
           return true;
       else
           return false;
   }
    /**********增删改查*****************/
}
