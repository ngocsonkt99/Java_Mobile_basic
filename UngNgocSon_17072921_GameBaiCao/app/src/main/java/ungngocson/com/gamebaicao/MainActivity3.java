package ungngocson.com.gamebaicao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity3 extends Activity {

    ImageView labai1,labai2,labai3,labai4,labai5,labai6,btn;
    TextView tv1,tv2,tv3;

    int sonut,sonut_player,sonut_computer=0;//de xet xem duoc may nut
    boolean batay1,batay2=true;//de xet truong hop 3 tay
    String chuoikq1,chuoikq2;



    ArrayList<String> cacladarut=new ArrayList<String>();
    ImageView mangimg[]=new ImageView[6];
    int manghinhbai[][]={
            {R.drawable.b2fv,
                    R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
                    R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
                    R.drawable.cj,R.drawable.cq,R.drawable.ck},

            {R.drawable.b2fv,
                    R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
                    R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
                    R.drawable.dj,R.drawable.dq,R.drawable.dk},

            {R.drawable.b2fv,
                    R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
                    R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
                    R.drawable.hj,R.drawable.hq,R.drawable.hk},

            {R.drawable.b2fv,
                    R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
                    R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
                    R.drawable.sj,R.drawable.sq,R.drawable.sk}
    };
    String mangtenbai[][]={
            {"rong",
                    "ach chuon","hai chuon","ba chuon","bon chuon","nam chuon",
                    "sau chuon","bay chuon","tam chuon","chin chuon","muoi chuon",
                    "boi chuon","dam chuon","gia chuon"},

            {"rong",
                    "ach ro","hai ro","ba ro","bon ro","nam ro",
                    "sau ro","bay ro","tam ro","chin ro","muoi ro",
                    "boi ro","dam ro","gia ro"},

            {"rong",
                    "ach co","hai co","ba co","bon co","nam co",
                    "sau co","bay co","tam co","chin co","muoi co",
                    "boi co","dam co","gia co"},

            {"rong",
                    "ach bich","hai bich","ba bich","bon bich","nam bich",
                    "sau bich","bay bich","tam bich","chin bich","muoi bich",
                    "boi bich","dam bich","gia bich"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_vs_com);

        LinearLayout layout = (LinearLayout) findViewById(R.id.root);

        layout.setBackgroundColor(Color.parseColor("#901000"));


        labai1=(ImageView) findViewById(R.id.imageView1);
        labai2=(ImageView) findViewById(R.id.imageView2);
        labai3=(ImageView) findViewById(R.id.imageView3);

        labai4=(ImageView) findViewById(R.id.imageView4);
        labai5=(ImageView) findViewById(R.id.imageView5);
        labai6=(ImageView) findViewById(R.id.imageView6);

        mangimg[0]=labai1;mangimg[1]=labai2;mangimg[2]=labai3;mangimg[3]=labai4;mangimg[4]=labai5;mangimg[5]=labai6;

        btn=(ImageView) findViewById(R.id.button1);

        tv1=(TextView) findViewById(R.id.textView1);
        tv2=(TextView) findViewById(R.id.textView2);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                    ChiaBai();
            }
        });

    }

    public void ChiaBai()
    {
        cacladarut.clear();
        //tao ngau nhien
        Random rd=new Random();
        int x,y;

        sonut=0;
        batay1=batay2=true;
        //tao ngau nhien bo vao ArrayList de xet xem co bi trung khong
        for(int i=0;i<mangimg.length;i++)
        {
            while(true)
            {
                x=rd.nextInt(4);//0->3 (b-a+1)+a;
                y=rd.nextInt(13)+1;//1->13
                if(cacladarut.contains(mangtenbai[x][y])==false)
                {
                    cacladarut.add(mangtenbai[x][y]);
                    break;
                }

            }

            mangimg[i].setImageResource(manghinhbai[x][y]);

            //chi can y<11 lap tuc khong con la batay nua
            if(y<11)
            {
                batay1=false;
                batay2=false;
            }

            sonut+=(y<10)?y:0;

            if(i==2)
            {
                sonut_player=sonut%10;
                if(batay1==true)
                {
                    chuoikq1="Computer 1 : Ba tây";
                    sonut_player=10;
                }
                else
                    chuoikq1="Computer 1 : "+sonut_player+" nút";

                batay2=true;
                sonut=0;
            }
            if(i==5)
            {
                sonut_computer=sonut%10;
                if(batay2==true)
                {
                    chuoikq2="Computer 2 : Ba tây";
                    sonut_computer=10;
                }
                else
                    chuoikq2="Computer 2 : "+sonut_computer+" nút";
            }

        }

        if(sonut_player>sonut_computer)
        {
            chuoikq1+="- Thắng";
            chuoikq2+="- Thua";
        }
        else if(sonut_player<sonut_computer)
        {
            chuoikq2+="- Thắng";
            chuoikq1+="- Thua";
        }
        else if(sonut_player==sonut_computer)
        {
            chuoikq1+="- Hòa";
            chuoikq2+="- Hòa";
        }

        tv1.setText(chuoikq1);
        tv2.setText(chuoikq2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
