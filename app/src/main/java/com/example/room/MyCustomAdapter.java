package com.example.room;

import android.content.Context;

import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCustomAdapter extends SimpleAdapter {

    public MyCustomAdapter(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
}
