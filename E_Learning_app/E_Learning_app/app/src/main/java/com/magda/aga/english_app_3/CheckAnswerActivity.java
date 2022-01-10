package com.magda.aga.english_app_3;

import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CheckAnswerActivity extends BaseActivity {

    /****************************** Biến ******************************/

    private TextView textViewMainTranslation;
    private TextView textViewOtherTranslation;
    private TextView textViewQuestion;
    private Button buttonNext;
    private Button buttonKnownWord;
    private Button buttonUncertainedWord;
    private Button buttonUnknownWord;
    private Button buttonDefinition;
    private List<PolishWord> otherPolishTranslations = new ArrayList<PolishWord>();


    /****************************** xử lý ứng dụng ******************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answer);

        // thêm thanh công cụ - phương thức từ BaseActivity
        addToolbar();
        toolbar.getMenu().findItem(R.id.MENU_NEXT_QUESTION).setVisible(false);

        // ràng buộc chế độ xem xml với java bằng cách sử dụng id của nó
        textViewMainTranslation = (TextView) findViewById(R.id.TEXT_VIEW_MAIN_TRANSLATION);
        textViewOtherTranslation = (TextView) findViewById(R.id.TEXT_VIEW_OTHER_TRANSLATION);
        textViewQuestion = (TextView) findViewById(R.id.TEXT_VIEW_QUESTION_2);
        buttonNext = (Button) findViewById(R.id.BUTTON_NEXT);
        buttonKnownWord = (Button) findViewById(R.id.BUTTON_KNOWN_WORD);
        buttonUncertainedWord = (Button) findViewById(R.id.BUTTON_UNCERTAINED_WORD);
        buttonUnknownWord = (Button) findViewById(R.id.BUTTON_UNKNOWN_WORD);
        buttonDefinition = (Button) findViewById(R.id.BUTTON_DEFINITION);

        // BaseActivity implements View.OnClickListener, không phải viết onClickListener mới cho mọi nút
        buttonNext.setOnClickListener(this); // chuyển hướng cuộc gọi đến phương thức onClick
        buttonKnownWord.setOnClickListener(this);
        buttonUncertainedWord.setOnClickListener(this);
        buttonUnknownWord.setOnClickListener(this);
        buttonNext.setOnClickListener(this);

        // Nếu đây là câu hỏi cuối cùng thì hãy thay đổi nút văn bản thành "Hoàn thành"
        if (questionCounter == questionCountTotal){
            buttonNext.setText("Hoàn thành");
        }

        // hiển thị câu hỏi, câu trả lời hay (Main) và bản dịch khác (not main)
        showQuestionInTextView(textViewQuestion);
        showCorrectAnswerToTextView(textViewMainTranslation);
        showOtherTranlation();
    }


    // phương thức onClick từ View.OnClickListener
    // gọi các phương thức sau khi nhấp vào nút cụ thể
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BUTTON_KNOWN_WORD:
                db.setKnownWord(currentQuestion.getId());
                showToast("Đã thêm vào các từ nổi bật");
                break;
            case R.id.BUTTON_UNCERTAINED_WORD:
                db.setUncertainedWord(currentQuestion.getId());
                showToast("Đã thêm vào các từ không chắc chắn.");
                break;
            case R.id.BUTTON_UNKNOWN_WORD:
                db.setUnknownWord(currentQuestion.getId());
                showToast("Đã thêm vào các từ không xác định.");
                break;
            case R.id.BUTTON_NEXT:
                showNextQuestion();
                break;
        }
    }


    /****************************** CÁC PHƯƠNG PHÁP SỬ DỤNG  ******************************/

    // Nếu còn câu hỏi, hãy mở MainActivity, nếu không - đóng ứng dụng
    private void showNextQuestion(){
        if (questionCounter < questionCountTotal) {
            openActivity(MainActivity.class);
        }
        else {
            if (questionCounter == questionCountTotal){
                questionCounter = 0;
                ActivityCompat.finishAffinity(CheckAnswerActivity.this);
            }
        }
    }


    //thêm bản dịch  để xem
    private void showOtherTranlation(){
        otherPolishTranslations = db.getOtherPolishTranslations(currentQuestion.getId());
        String text = "";
        if (otherPolishTranslations.size() == 0){
            textViewOtherTranslation.setText("----");
        }
        else{
            for (PolishWord polishWord : otherPolishTranslations ){
                if (text == ""){
                    text = text + polishWord.getPl_word() + "\n";
                }
                else {
                    text = text + "\n" + polishWord.getPl_word();
                }
            }
            textViewOtherTranslation.setText(text);
        }
    }



}
