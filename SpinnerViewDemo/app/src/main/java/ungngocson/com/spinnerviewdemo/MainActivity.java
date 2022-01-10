package ungngocson.com.spinnerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button btnExit, btnView;
    TextView textView;
    String[] presidents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit= (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        public void ReadTextFile(View view) throws IOException {
            String string = "";
            StringBuilder stringBuilder = new StringBuilder();
            InputStream is = this.getResources().openRawResource(R.raw.sample);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (true) {
                try {
                    if ((string = reader.readLine()) == null) break;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuilder.append(string).append("\n");
                textView.setText(stringBuilder);
            }
            is.close();
            Toast.makeText(getBaseContext(), stringBuilder.toString(),
                    Toast.LENGTH_LONG).show();
        }
    //    btnView = (Button) findViewById(R.id.btnView);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        presidents =
                getResources().getStringArray(R.array.presidents_array);
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, presidents);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();

                try {
                    AssetManager am = getAssets();
                    //
                    InputStream is = am.open("1.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                    }
                    br.close();
                    is.close();
                } catch (Exception ex) {
                }

                Toast.makeText(getBaseContext(),
                        "You have selected item: " + presidents[index],
                Toast.LENGTH_SHORT) .show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }
}