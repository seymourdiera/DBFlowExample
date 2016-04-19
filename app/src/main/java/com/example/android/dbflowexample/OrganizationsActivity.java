package com.example.android.dbflowexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android.dbflowexample.entity.Organization;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class OrganizationsActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    EditText organizationNameEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations);

        listView = (ListView) findViewById(R.id.activity_organizations_listview);

        refreshList();

        organizationNameEditText = (EditText) findViewById(R.id.activity_organizations_name_edittext);

        saveButton = (Button) findViewById(R.id.activity_organizations_save_button);

        saveButton.setOnClickListener(this);


    }

    private void refreshList(){
        List<Organization> organizations = new Select().from(Organization.class).queryList();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, organizations));
    }

    @Override
    public void onClick(View v) {
        // Create organization
        Organization organization = new Organization();
        organization.setName(organizationNameEditText.getText().toString());
        organization.save();
        refreshList();
    }
}
