package com.example.android.dbflowexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android.dbflowexample.adapter.CustomArrayAdapter;
import com.example.android.dbflowexample.entity.Organization;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class OrganizationsActivity extends AppCompatActivity {

    Organization selectedOrganization;
    ListView listView;
    EditText organizationNameEditText;
    Button saveButton;
    Button addButton;
    Button deleteButton;
    Button editButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations);

        listView = (ListView) findViewById(R.id.activity_organizations_listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedOrganization = (Organization) listView.getAdapter().getItem(position);
                populateFields();
                editButton.setVisibility(View.VISIBLE);
            }
        });

        refreshList();

        organizationNameEditText = (EditText) findViewById(R.id.activity_organizations_name_edittext);

        saveButton = (Button) findViewById(R.id.activity_organizations_save_button);

        deleteButton = (Button) findViewById(R.id.activity_organizations_delete_button);

        editButton = (Button) findViewById(R.id.activity_organizations_edit_button);

        addButton = (Button) findViewById(R.id.activity_organizations_new_button);

        cancelButton = (Button) findViewById(R.id.activity_organizations_cancel_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(organizationNameEditText.getText().toString().trim().isEmpty()){
                    organizationNameEditText.setError("Mandatory");
                }else {
                    // Create organization
                    Organization organization = new Organization();
                    organization.setName(organizationNameEditText.getText().toString());
                    organization.save();
                    refreshList();
                    setInitialState();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                organizationNameEditText.getText().clear();
                organizationNameEditText.setEnabled(Boolean.TRUE);
                organizationNameEditText.requestFocus();
                addButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                editButton.setVisibility(View.GONE);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInitialState();
            }
        });


    }

    private void populateFields(){
        organizationNameEditText.setText(selectedOrganization.getName());
    }

    private void setInitialState() {
        organizationNameEditText.getText().clear();
        organizationNameEditText.setEnabled(Boolean.FALSE);
        addButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        editButton.setVisibility(View.GONE);
    }

    private void refreshList() {
        List<Organization> organizations = new Select().from(Organization.class).queryList();
        listView.setAdapter(new CustomArrayAdapter(this, android.R.layout.simple_list_item_1, organizations));
    }


}
