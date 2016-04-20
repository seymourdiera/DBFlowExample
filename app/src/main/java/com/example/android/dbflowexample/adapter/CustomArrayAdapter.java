package com.example.android.dbflowexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.dbflowexample.entity.Organization;

import java.util.List;

/**
 * Created by mac on 4/20/16.
 */
public class CustomArrayAdapter extends ArrayAdapter<Organization> {

    List<Organization> organizations;
    Context context;

    public CustomArrayAdapter(Context context, int resource, List<Organization> objects) {
        super(context, resource, objects);
        this.organizations = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView outcome = new TextView(context);
        outcome.setText(organizations.get(position).getName());
        return outcome;
    }
}
