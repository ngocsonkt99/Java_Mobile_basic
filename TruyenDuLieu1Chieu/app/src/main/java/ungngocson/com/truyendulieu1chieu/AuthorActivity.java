package ungngocson.com.truyendulieu1chieu;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText et_idauthor, et_name, et_address, et_email;
    Button bt_save, bt_select, bt_exit, bt_update, bt_delete;
    GridView gv_display;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        et_idauthor = (EditText)findViewById(R.id.edittext_idauthor);
        et_name = (EditText)findViewById(R.id.edittext_name);
        et_address = (EditText)findViewById(R.id.edittext_address);
        et_email = (EditText)findViewById(R.id.edittext_email);
        gv_display = (GridView)findViewById(R.id.gridview_display);

        dbHelper = new DBHelper(this);

        bt_exit = (Button)findViewById(R.id.button_exit);
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_select = (Button)findViewById(R.id.button_select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Author> list_author = new ArrayList<>();
                ArrayList<String> list_string = new ArrayList<>();

                if(et_idauthor.getText().toString().equals(""))
                    list_author = dbHelper.getAllAuthors();
                else
                    list_author = dbHelper.getIDAuthors(Integer.parseInt(et_idauthor.getText().toString()));
                if(list_author.size()>0) {
                    for (Author author : list_author) {
                        list_string.add(author.getId_author()+"");
                        list_string.add(author.getName());
                        list_string.add(author.getAddress());
                        list_string.add(author.getEmail());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AuthorActivity.this, android.R.layout.simple_list_item_1, list_string);
                    gv_display.setAdapter(adapter);
                }else {
                    Toast.makeText(getApplicationContext(), "Co so du lieu rong", Toast.LENGTH_LONG).show();
                }
            }
        });

        bt_save = (Button)findViewById(R.id.button_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(et_idauthor.getText().toString()));
                author.setName(et_name.getText().toString());
                author.setAddress((et_address.getText().toString()));
                author.setEmail(et_email.getText().toString());
                if(dbHelper.insertAuthor(author) > 0)
                    Toast.makeText(getApplicationContext(),"Da luu thanh cong", Toast.LENGTH_LONG ).show();
                else
                    Toast.makeText(getApplicationContext(), "Luu khong thanh cong", Toast.LENGTH_LONG).show();
                et_idauthor.setText("");
                et_name.setText("");
                et_address.setText("");
                et_email.setText("");
            }
        });


    }

}
