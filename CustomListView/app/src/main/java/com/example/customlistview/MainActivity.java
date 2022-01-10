package com.example.customlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.textView);
        registerForContextMenu(tv);

        ArrayList<Country> mylist = new ArrayList<>();
        Country c1 = new Country("C-1", "shv1", 1878);
        Country c2 = new Country("C-2", "shv2", 28787);
        Country c3 = new Country("C-3", "shv3", 398787);
        Country c4 = new Country("C-4", "shv4", 4827827);
        Country c5 = new Country("C-5", "shv5", 58782797);
        Country c6 = new Country("C-6", "shv6", 628787971);
        mylist.add(c1);
        mylist.add(c2);
        mylist.add(c3);
        mylist.add(c4);
        mylist.add(c5);
        mylist.add(c6);

        final ListView listView = (ListView) findViewById(R.id.listView_item);
        listView.setAdapter(new CustomListAdapter(this, mylist));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                Country country = (Country)o;
                Toast.makeText(MainActivity.this, "Selected: " + " " + country, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Chon ten: ");
        menu.add(0, v.getId(), 0, "Hoa Mai");
        menu.add(0, v.getId(), 0, "Hoa Hong");
        menu.add(0, v.getId(), 0, "Hoa Lan");
        menu.add(0, v.getId(), 0, "Hoa Ly");
    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Ban da chon em nay: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}