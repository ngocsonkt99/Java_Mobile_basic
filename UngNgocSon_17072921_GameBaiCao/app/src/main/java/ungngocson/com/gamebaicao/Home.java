package ungngocson.com.gamebaicao;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class Home  extends AppCompatActivity {
    Button btn_op,btn_pvc,btn_cvc,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        btn_op= (Button) findViewById(R.id.btn_op);
        btn_pvc=(Button) findViewById(R.id.btn_pvc);
        btn_cvc=(Button) findViewById(R.id.btn_cvc);
        btnExit=(Button) findViewById(R.id.btnExit);

        btn_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,MainActivity2.class);
                startActivityForResult(intent,9999);
            }
        });
        btn_pvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivityForResult(intent,9999);
            }
        });
        btn_cvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,MainActivity3.class);
                startActivityForResult(intent,9999);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}