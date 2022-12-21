package com.example.service_novigrad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ServiceHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "service_database";
    //Database Table name
    private static final String TABLE_NAME = "SERVICE";
    //Table Columns
    public static final String ID = "id";
    public static final String NAME = "name";
    private SQLiteDatabase sqLiteDatabase;

    //create table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL);";

    //Constructor
    public ServiceHelperClass(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Service Data
    public void addService(ServiceModelClass serviceModelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ServiceHelperClass.NAME, serviceModelClass.getName());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(ServiceHelperClass.TABLE_NAME, null, contentValues);
    }

    public List<ServiceModelClass> getServiceList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<ServiceModelClass> storeService = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                storeService.add(new ServiceModelClass(id,name));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeService;
    }

    public void updateService(ServiceModelClass serviceModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ServiceHelperClass.NAME,serviceModelClass.getName());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?" , new String[]
                {String.valueOf(serviceModelClass.getId())});
    }

    public void deleteService(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }



}
