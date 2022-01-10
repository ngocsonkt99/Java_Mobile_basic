package com.magda.aga.english_app_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {


    /****************************** Biến ******************************/

    // biến sử dụng chung
    protected static boolean isGoodAnswer;
    protected static List<EnglishWord> questionList;
    protected static int questionCounter;
    protected static int questionCountTotal;
    protected static EnglishWord currentQuestion;
    protected static DatabaseHelper db;
    protected static PolishWord correctAnswer;
    protected static int wordsType;
    protected static List<EnglishWord> wordList;
    protected static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // singleton - in any activity passing the context and use the singleton method
        db = DatabaseHelper.getInstance(this);
    }



    /****************************** PP thường dùng ******************************/

    // Shared preferences - questionCounter( bộ đếm câu hỏi)
    @Override
    protected void onDestroy(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("questionCounter", questionCounter);
        editor.commit();

        super.onDestroy();
    }


    // chuyển Activities
    protected void openActivity(Class c){
        // Intent - use to move between Activities, c = is an activity the Intent goes to
        Intent intent = new Intent(getApplicationContext(), c);
        startActivity(intent);
    }


    // toasts - creating custom toasts
    // tạo tùy chỉnh toasts
    protected void showToastGoodAnswer(){
        // retrieve the Layout Inflater and inflate the layout from xml - there is custom layout for toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_good_answer, (ViewGroup) findViewById(R.id.TOAST_GOOD_ANSWER));
        final Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,210);
        toast.setView(layout); // set the inflated layout, set the view to show
        toast.show();

        // specify delay here for toast
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);
    }

    protected void showToastBadAnswer(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_bad_answer, (ViewGroup) findViewById(R.id.TOAST_BAD_ANSWER));
        final Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,210);
        toast.setView(layout);
        toast.show();

        // specify delay here for toast( chậm toasts)
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);
    }


    // showing toast with exact message(hiện toasts với tin chính xác)
    protected void showToast(String msg){
        final Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER,0,600);
        toast.show();

        // specify delay here for toast( chỉ định chậm toasts)
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 600);
    }


    // show question
    protected void showQuestionInTextView(TextView textView){
        textView.setText(currentQuestion.getEn_word());
    }


    // show correct answer
    protected void showCorrectAnswerToTextView(TextView textView){
        textView.setText(correctAnswer.getPl_word());
    }


    // Thêm thanh công cụ để xem dữ liệu ngày hiện tại
    protected void addToolbar(){
        DateFormat df = new SimpleDateFormat("E dd.MM.yyyy");
        Date timeNow = Calendar.getInstance().getTime();
        String reportTime = df.format(timeNow);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setSubtitle(reportTime);
        toolbar.inflateMenu(R.menu.menu);

        toolbar.setOnMenuItemClickListener(this);
    }


    // dropdown menu click handler
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.MENU1:
                wordsType = 1;
                openActivity(ShowWordsActivity.class);
                return true;
            case R.id.MENU2:
                wordsType = 2;
                openActivity(ShowWordsActivity.class);
                return true;
            case R.id.MENU3:
                wordsType = 3;
                openActivity(ShowWordsActivity.class);
                return true;
            case R.id.MENU4:
                wordsType = 4;
                openActivity(ShowWordsActivity.class);
                return true;
            case R.id.MENU5:
                wordsType = 5;
                openActivity(ShowWordsActivity.class);
                return true;
            case R.id.MENU_NEXT_QUESTION:
                wordsType = 0;
                openActivity(MainActivity.class);
                return true;
        }
        return false;
    }

}





