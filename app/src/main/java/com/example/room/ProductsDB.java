package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Category.class}, version = 1)
public abstract class ProductsDB extends RoomDatabase {

    abstract DAO dao();

    private static final String DB_NAME = "products.db";
    private static ProductsDB instance = null;

    public synchronized static ProductsDB getInstance(Context ctxt) {

        if (instance == null) {
            instance = create(ctxt, false);
        }
        return (instance);
    }

    static ProductsDB create(Context context, boolean memoryOnly) {
        RoomDatabase.Builder<ProductsDB> b = null;
        if (!memoryOnly) {
            b = Room.databaseBuilder(context.getApplicationContext(), ProductsDB.class, DB_NAME)
                    .createFromAsset("databases/"+DB_NAME);
        }
        return (b.build());
    }

}
