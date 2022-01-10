package com.magda.aga.english_app_3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends BaseActivity {

    /****************************** VARIABLES (Biến) ******************************/

    // elements of View (Phần tử)
    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private Button [] buttonsOption = new Button[4];
    private Button buttonCheck;
    private PolishWord answer1, answer2, answer3;
    private ArrayList<PolishWord> answers = new ArrayList<PolishWord>();
    private Button [] buttonsMenu = new Button[5];


    /****************************** Xử lý app ******************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adding toolbar - method from BaseActivity
        addToolbar();
        toolbar.getMenu().findItem(R.id.MENU1).setVisible(false);
        toolbar.getMenu().findItem(R.id.MENU2).setVisible(false);
        toolbar.getMenu().findItem(R.id.MENU3).setVisible(false);
        toolbar.getMenu().findItem(R.id.MENU4).setVisible(false);
        toolbar.getMenu().findItem(R.id.MENU5).setVisible(false);
        toolbar.getMenu().findItem(R.id.MENU_NEXT_QUESTION).setVisible(false);

        // ràng buộc chế độ xem xml view với java bằng id của nó
        textViewQuestion = findViewById(R.id.TEXT_VIEW_QUESTION);
        textViewQuestionCount = findViewById(R.id.TEXT_VIEW_QUESTION_COUNT);

        buttonsOption[0] = findViewById(R.id.BUTTON_OPTION0);
        buttonsOption[1] = findViewById(R.id.BUTTON_OPTION1);
        buttonsOption[2] = findViewById(R.id.BUTTON_OPTION2);
        buttonsOption[3] = findViewById(R.id.BUTTON_OPTION3);
        buttonCheck = findViewById(R.id.BUTTON_CHECK);

        // BaseActivity implements View.OnClickListener, do đó ta không phải viết onClickListener mới cho mọi nút
        buttonsOption[0].setOnClickListener(this); // nó chuyển hướng cuộc gọi đến phương thức onClick ()
        buttonsOption[1].setOnClickListener(this);
        buttonsOption[2].setOnClickListener(this);
        buttonsOption[3].setOnClickListener(this);
        buttonCheck.setOnClickListener(this);

        questionList = db.getAllEnglishWords(); // question is english word
        questionCountTotal = questionList.size(); // number of questions

        // Shared preferences - questionCounter
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        this.questionCounter = sharedPref.getInt("questionCounter", 0);

        showNextQuestion();

    }

    // Shared preferences - questionCounter(Tùy chọn được chia sẻ - Bộ đếm câu hỏi)
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("questionCounter", questionCounter);
        editor.commit();
    }

    // phương thức onClick từ View.OnClickListener
    // gọi các phương thức sau khi nhấp vào nút cụ thể
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.BUTTON_OPTION0:
                buttonSelected(buttonsOption[0]);
                break;

            case R.id.BUTTON_OPTION1:
                buttonSelected(buttonsOption[1]);
                break;

            case R.id.BUTTON_OPTION2:
                buttonSelected(buttonsOption[2]);
                break;

            case R.id.BUTTON_OPTION3:
                buttonSelected(buttonsOption[3]);
                break;

            case R.id.BUTTON_CHECK:
                // nếu bất kỳ nút nào được chọn, hãy kiểm tra câu trả lời sau khi nhấp
                if (buttonsOption[0].isSelected() || buttonsOption[1].isSelected() || buttonsOption[2].isSelected() || buttonsOption[3].isSelected()) {
                    checkAnswer();
                    openActivity(CheckAnswerActivity.class);
                }
                else {
                    showToast("Vui lòng chọn một câu trả lời.");
                }
                break;
        }
    }

    /****************************** CÁC PHƯƠNG PHÁP SỬ DỤNG TRÊN (METHODS USE ABOVE) ******************************/

    private void showNextQuestion () {
        // cho đến khi còn câu hỏi - hiển thị câu hỏi tiếp theo trong MainActivity
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            showQuestionInTextView(textViewQuestion);

            // nhận câu trả lời đúng và 3 từ ngẫu nhiên từ cơ sở dữ liệu
            correctAnswer = db.getCorrectAnswer(currentQuestion.getId());
            answer1 = db.getRandomPolishWord(correctAnswer.getId());
            answer2 = db.getRandomPolishWord(correctAnswer.getId(), answer1.getId());
            answer3 = db.getRandomPolishWord(correctAnswer.getId(), answer1.getId(), answer2.getId());

            // xóa mảng sau câu hỏi cuối cùng và thêm câu trả lời mới
            answers.clear();
            answers.addAll(Arrays.asList(correctAnswer, answer1, answer2, answer3));

            // xáo trộn các câu trả lời theo thứ tự ngẫu nhiên
            Collections.shuffle(answers);

            // đặt câu trả lời theo thứ tự ngẫu nhiên trong các nút
            for (int i = 0; i<buttonsOption.length; i++){
                buttonsOption[i].setText(answers.get(i).getPl_word());
            }

            // thêm số vào questionCounter và hiển thị số câu hỏi
            questionCounter++;

            textViewQuestionCount.setText("Câu hỏi: " + questionCounter + "/" + questionCountTotal);
        }

        else {
            // đóng hoạt động này và chạy hoạt động khác nếu tồn tại
            finish();
        }
    }


    // kiểm tra câu trả lời - lưu vào cơ sở dữ liệu và hiển thị toasts
    private void checkAnswer(){
        for (Button b: buttonsOption){
            // nếu nút cụ thể được chọn và văn bản trên đó là câu trả lời đúng thì hãy chọn đó là câu trả lời chính xác
            if (b.isSelected() && (correctAnswer.getPl_word() == b.getText())){
                db.setGoodAnswer(currentQuestion.getId());
                isGoodAnswer = true;
                showToastGoodAnswer();
            }
            else if (b.isSelected() && (correctAnswer.getPl_word() != b.getText())) {
                db.setBadAnswer(currentQuestion.getId());
                isGoodAnswer = false;
                showToastBadAnswer();
            }
        }
    }


    // nút được chọn - thay đổi trạng thái cho nút đã chọn - và bằng cách đó trong chế độ xem thay đổi mã xml của nút đó
    public void buttonSelected(Button button){
        for (int i = 0; i<4; i++){
            if(button == buttonsOption[i])
                button.setSelected(true);
            else
                buttonsOption[i].setSelected(false);
        }
    }
}



