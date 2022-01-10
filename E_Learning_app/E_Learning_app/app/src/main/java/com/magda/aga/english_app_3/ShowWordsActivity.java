package com.magda.aga.english_app_3;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShowWordsActivity extends BaseActivity {

    /****************************** VARIABLES(Biến) ******************************/

    // elements of View
    private TextView textViewWords;


    /****************************** XỬ LÝ APPLICATION ******************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_words);

        // ràng buộc chế độ xem xml với java bằng id của nó
        textViewWords = (TextView) findViewById(R.id.TEXT_VIEW_WORDS);

        // cho phép cuộn chế độ xem văn bản
        textViewWords.setMovementMethod(new ScrollingMovementMethod());

        // adding toolbar - method from BaseActivity
        addToolbar();

        showWordsByType(wordsType, textViewWords);
    }


    @Override
    public void onClick(View view) {}


    /****************************** CÁC PHƯƠNG PHÁP SỬ DỤNG TRÊN(METHODS USE ABOVE) ******************************/

    // các phương thức sử dụng để hiển thị các từ trong Show AdWordsActivity
    private void showWordsByType(int type, TextView textView){
        if (type == 1){
            wordList = db.getAllBadAnswer();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 2){
            wordList = db.getAllGoodAnswer();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 3){
            wordList = db.getAllKnownWords();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 4){
            wordList = db.getAllUncertainedWords();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 5){
            wordList = db.getAllUnknownWords();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
    }


    private void showWordsInTextView(List<EnglishWord> wordList, TextView textView){
        String text = "";
        if (wordList.size() == 0){
            textView.setText("----");
        }
        else{
            for (EnglishWord englishWord : wordList ){
                text = text + englishWord.getEn_word() + "\n";
            }
        }
        textView.setText(text);
    }
}
