package com.example.room;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM products")
    List<Product> getProducts();

    @Query("SELECT * FROM categories")
    List<Category> getCategories();

    @Query("SELECT * FROM products WHERE category_id like :id")
    List<Product> getProductsByCategoriesId(int id);
}
