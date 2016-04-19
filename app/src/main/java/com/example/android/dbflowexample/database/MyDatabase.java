package com.example.android.dbflowexample.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by mac on 4/18/16.
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {

    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
