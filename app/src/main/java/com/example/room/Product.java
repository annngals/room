package com.example.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "products",
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id"))
public class Product {

    @PrimaryKey
    @NonNull
    int id;
    String name;
    @NonNull
    int category_id;

    public Product(@NonNull int id, String name, @NonNull int category_id) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id='" + category_id + '\'' +
                '}';
    }
}
