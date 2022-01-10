package com.example.listviewdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgBtnAdd;
    private ListView lvContact;
    private ArrayList<Contact> contacts;
    private CustomAdapter adapter;
    private static final int ADD = 999;
    private static final int EDIT = 888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lv_contact);
        imgBtnAdd = findViewById(R.id.img_btn_add);

        contacts = new ArrayList<>();
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));
        contacts.add(new Contact("Andy", "annguyen3520@gmail.com", "0849824889"));

        adapter = new CustomAdapter(MainActivity.this, R.layout.custom_listview, contacts);
        lvContact.setAdapter(adapter);
        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent,ADD);
            }
        });

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contacts.get(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", contact);
                intent.putExtra("detail", bundle);
                startActivity(intent);
            }
        });
        registerForContextMenu(lvContact);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("add");
                Contact contact = (Contact) bundle.getSerializable("contact");
                contacts.add(contact);
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == EDIT) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("edit");
                int pos = bundle.getInt("pos");
                Contact contact = (Contact) bundle.getSerializable("contact");
                Contact needEdit = contacts.get(pos);
                needEdit.setName(contact.getName());
                needEdit.setEmail(contact.getEmail());
                needEdit.setPhone(contact.getPhone());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lv_contact) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.custom_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent,ADD);
                break;
            case R.id.edit:
                int pos = menuInfo.position;
                Contact contact = contacts.get(pos);
                Intent intent1 = new Intent(MainActivity.this, FormActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", contact);
                bundle.putInt("pos",pos);
                intent1.putExtra("edit", bundle);
                startActivityForResult(intent1,EDIT);
                break;
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete?");
                builder.setMessage("Do you want to delete this contact?");
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contacts.remove(menuInfo.position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
