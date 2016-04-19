package com.example.android.dbflowexample.entity;

import com.example.android.dbflowexample.database.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by mac on 4/18/16.
 */
@Table(database = MyDatabase.class)
public class User extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String name;

    // The saveForeignKeyModel denotes whether to update the foreign key if the entry is also updated
    @Column
    @ForeignKey(saveForeignKeyModel = false)
    Organization organization;



}
