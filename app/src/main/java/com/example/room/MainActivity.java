package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProductsDB db;
    ListView listView;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        context = getApplicationContext(); // for async tasks
        db = ProductsDB.getInstance(context);


        LoadCategories loadCategories = new LoadCategories();
        loadCategories.execute();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LoadProducts loadProducts = new LoadProducts();
                        loadProducts.execute(i);
                    }
                }
        );
    }


    class LoadProducts extends AsyncTask<Integer, Void, List<Product>> {

        @Override
        protected List<Product> doInBackground(Integer... index) {
            List<Product> products;
            if (index[0] == -1) {
                products = db.dao().getProducts();
            } else {
                products = db.dao().getProductsByCategoriesId(index[0] + 1);
            }
            Log.d("mytag", "Products" + products);
            return products;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);

            ArrayList<HashMap<String, String>> list = new ArrayList();
            for (Product p : products) {
                HashMap<String, String> record = new HashMap();
                record.put("id", Integer.toString(p.id));
                record.put("name", p.name);
                list.add(record);
                Log.d("mytag", "line: " + record);
            }

            MyCustomAdapter adapter = new MyCustomAdapter(context, list, R.layout.item,
                    new String[]{"id", "name"},
                    new int[]{R.id.id, R.id.name});
            listView.setAdapter(adapter);
        }
    }

    class LoadCategories extends AsyncTask<Void, Void, List<Category>> {

        @Override
        protected List<Category> doInBackground(Void... voids) {
            List<Category> categories = db.dao().getCategories();
            return categories;
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            super.onPostExecute(categories);

            ArrayList<HashMap<String, String>> list = new ArrayList();
            for (Category c : categories) {
                HashMap<String, String> record = new HashMap();
                record.put("id", Integer.toString(c.id));
                record.put("name", c.name);
                list.add(record);
            }

            MyCustomAdapter adapter = new MyCustomAdapter(context, list, R.layout.item,
                    new String[]{"id", "name"},
                    new int[]{R.id.id, R.id.name});
            listView.setAdapter(adapter);
        }
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.viewCategories: {
                LoadCategories gc = new LoadCategories();
                gc.execute();
                break;
            }
            case R.id.viewProducts: {
                LoadProducts gp = new LoadProducts();
                gp.execute(-1);
                break;
            }
        }
    }


}
