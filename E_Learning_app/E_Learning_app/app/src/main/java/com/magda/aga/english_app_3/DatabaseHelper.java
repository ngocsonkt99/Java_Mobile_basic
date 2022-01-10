package com.magda.aga.english_app_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    /******************************Biến******************************/

    // Database
    private SQLiteDatabase db;
    private static DatabaseHelper sInstance; //singleton Instance

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "words.db";

    // Table Names
    private static final String TABLE_PL_WORD = "POLISH_WORD";
    private static final String TABLE_EN_WORD = "ENGLISH_WORD";
    private static final String TABLE_PL_EN = "PL_EN_TRANSLATION";

    // Common column names thường dùng
    private static final String ID = "id";

    // PolishWord table - column names
    private static final String PL_WORD = "pl_word";

    // EnglishWord table - column names
    private static final String EN_WORD = "en_word";
    private static final String BAD_ANSWER = "bad_answer";
    private static final String KNOWN_WORD = "known_word";
    private static final String UNCERTAINED_WORD = "uncertained_word";
    private static final String UNKNOWN_WORD = "unknown_word";

    // Polish-English table - column name
    private static final String FK_PL_ID = "fk_pl_id";
    private static final String FK_EN_ID = "fk_en_id";
    private static final String MAIN_TRANSLATION = "main_translation";

    // Lệnh tạo bảng
    // Câu lệnh tạo bảng PolishWord
    private static final String CREATE_TABLE_PL_WORD = "CREATE TABLE " +
            TABLE_PL_WORD + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            PL_WORD + " TEXT NOT NULL UNIQUE" +  ")";


    // lệnh tạo bảng EnglishWord
    private static final String CREATE_TABLE_EN_WORD = "CREATE TABLE " +
            TABLE_EN_WORD + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            EN_WORD + " TEXT NOT NULL UNIQUE," +
            BAD_ANSWER + " INTEGER NOT NULL," +
            KNOWN_WORD + " INTEGER NOT NULL," +
            UNCERTAINED_WORD + " INTEGER NOT NULL," +
            UNKNOWN_WORD + " INTEGER NOT NULL" + ")";

    // Polish-English lệnh tạo bảng
    private static final String CREATE_TABLE_TRANSLATION = "CREATE TABLE " +
            TABLE_PL_EN + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FK_PL_ID + " INTEGER NOT NULL," +
            FK_EN_ID + " INTEGER NOT NULL," +
            MAIN_TRANSLATION + " INTEGER NOT NULL," +
            "FOREIGN KEY(" + FK_PL_ID + ") REFERENCES " + TABLE_PL_WORD + "("+ ID +"),"+
            "FOREIGN KEY(" + FK_EN_ID + ") REFERENCES " + TABLE_EN_WORD + "("+ ID +")"+
            ")";


    /****************************** phương thức cơ bản ******************************/

    // không cho phép lớp khác tạo một thể hiện mới của lớp
    // Thay vào đó hãy gọi phương thức tĩnh "getInstance ()".
    private DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // getInstance () - gọi hàm tạo bằng phương thức tĩnh,
    // singleton - chỉ một thể hiện của lớp tồn tại trong máy ảo java
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    // được gọi khi cơ sở dữ liệu được tạo cho FIRST
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        // create tables
        db.execSQL(CREATE_TABLE_PL_WORD);
        db.execSQL(CREATE_TABLE_EN_WORD);
        db.execSQL(CREATE_TABLE_TRANSLATION);

        // điền vào cơ sở dữ liệu với các giá trị
        fillDatabase();
    }

    // Được gọi khi cơ sở dữ liệu cần được nâng cấp, tồn tại trên đĩa, DATABASE_VERSION thì khác
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // khi nâng cấp thả cơ sở dữ liệu cũ hơn
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PL_WORD);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EN_WORD);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PL_EN);

            // create new database
            onCreate(db);
        }
    }


    public void fillDatabase(){
        // creating PolishWord
        PolishWord pl_1 = new PolishWord ("sông băng");

        PolishWord pl_2 = new PolishWord ("đẹp");
        PolishWord pl_3 = new PolishWord ("beautiful");

        PolishWord pl_4 = new PolishWord ("nguyên tử");

        PolishWord pl_5 = new PolishWord ("mệt mỏi");

        PolishWord pl_6 = new PolishWord ("nhọn");
        PolishWord pl_7 = new PolishWord ("sharp");

        PolishWord pl_8 = new PolishWord ("phân biệt");
        PolishWord pl_9 = new PolishWord ("discernment");

        PolishWord pl_10 = new PolishWord ("dài");
        PolishWord pl_11 = new PolishWord ("verbose");

        PolishWord pl_12 = new PolishWord ("thưởng thức");
        PolishWord pl_13 = new PolishWord ("taste");
        PolishWord pl_14 = new PolishWord ("relish");


        // thêm PolishWord vào cơ sở dữ liệu - TABLE_PL_WORD
        long pl_id1 = addPolishWord(pl_1);
        long pl_id2 = addPolishWord(pl_2);
        long pl_id3 = addPolishWord(pl_3);
        long pl_id4 = addPolishWord(pl_4);
        long pl_id5 = addPolishWord(pl_5);
        long pl_id6 = addPolishWord(pl_6);
        long pl_id7 = addPolishWord(pl_7);
        long pl_id8 = addPolishWord(pl_8);
        long pl_id9 = addPolishWord(pl_9);
        long pl_id10 = addPolishWord(pl_10);
        long pl_id11 = addPolishWord(pl_11);
        long pl_id12 = addPolishWord(pl_12);
        long pl_id13 = addPolishWord(pl_13);
        long pl_id14 = addPolishWord(pl_14);

        // creating EnglishWord
        EnglishWord en_1 = new EnglishWord("glacier",0,0,0,0);
        EnglishWord en_2 = new EnglishWord("pretty",0,0,0,0);
        EnglishWord en_3 = new EnglishWord("atomic",0,0,0,0);
        EnglishWord en_4 = new EnglishWord("weary",0,0,0,0);
        EnglishWord en_5 = new EnglishWord("acute",0,0,0,0);
        EnglishWord en_6 = new EnglishWord("discern",0,0,0,0);
        EnglishWord en_7 = new EnglishWord("lenghty",0,0,0,0);
        EnglishWord en_8 = new EnglishWord("enjoy",0,0,0,0);

        // Add EnglishWord to database - TABLE_EN_WORD
        long en_id1 = addEnglishWord(en_1);
        long en_id2 = addEnglishWord(en_2);
        long en_id3 = addEnglishWord(en_3);
        long en_id4 = addEnglishWord(en_4);
        long en_id5 = addEnglishWord(en_5);
        long en_id6 = addEnglishWord(en_6);
        long en_id7 = addEnglishWord(en_7);
        long en_id8 = addEnglishWord(en_8);

        // add translation to database - TABLE_PL_EN
        addTranslation(en_id1, pl_id1, 1);
        addTranslation(en_id2, pl_id2,1 );
        addTranslation(en_id2, pl_id3,0 );
        addTranslation(en_id3, pl_id4,1 );
        addTranslation(en_id4, pl_id5,1 );
        addTranslation(en_id5, pl_id6,1 );
        addTranslation(en_id5, pl_id7,0 );
        addTranslation(en_id6, pl_id8,1 );
        addTranslation(en_id6, pl_id9,0 );
        addTranslation(en_id7, pl_id10,1 );
        addTranslation(en_id7, pl_id11,0 );
        addTranslation(en_id8, pl_id12,1 );
        addTranslation(en_id8, pl_id13,0 );
        addTranslation(en_id8, pl_id14,0 );

    }


    /****************************** POLISH_WORD ******************************/

    // chọn từ đánh bóng từ cơ sở dữ liệu và trả về
    public PolishWord selectPolishWord(String selectQuery){
        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        PolishWord polishWord = new PolishWord();
        polishWord.setId(c.getLong(c.getColumnIndex(ID)));
        polishWord.setPl_word(c.getString(c.getColumnIndex(PL_WORD)));

        c.close();
        db.close();

        return polishWord;
    }


    // thêm PolishWord vào cơ sở dữ liệu
    public long addPolishWord (PolishWord pl_word){

        ContentValues values = new ContentValues();
        values.put(PL_WORD, pl_word.getPl_word());

        // insert row
        long pl_id = db.insert(TABLE_PL_WORD, null, values);

        return pl_id;
    }


    // lấy một PolishWord duy nhất là một câu trả lời đúng
    public PolishWord getCorrectAnswer(long en_id){
        db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PL_WORD + " PL, " + TABLE_PL_EN + " PLEN " +
                "WHERE PL." + ID + " = PLEN." + FK_PL_ID + " AND PLEN." + MAIN_TRANSLATION + " = 1 AND PLEN." +
                FK_EN_ID + "=" + en_id;

        PolishWord polishWord = selectPolishWord(selectQuery);

        db.close();
        return polishWord;
    }


    // lấy một RANDOM PolishWord - ghi đè vài lần
    public PolishWord getRandomPolishWord(long pl_id){
        db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PL_WORD + " PL, " + TABLE_PL_EN + " PLEN WHERE PL." + ID + " = PLEN." + FK_PL_ID +
                " AND PLEN." + MAIN_TRANSLATION + "=1 AND PL." + ID + " NOT IN (" + pl_id + ") ORDER BY random() LIMIT 1;";
        PolishWord polishWord = selectPolishWord(selectQuery);

        db.close();
        return polishWord;
    }

    public PolishWord getRandomPolishWord(long pl_id1, long pl_id2){
        db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PL_WORD + " PL, " + TABLE_PL_EN + " PLEN WHERE PL." + ID + " = PLEN." + FK_PL_ID +
                " AND PLEN." + MAIN_TRANSLATION + "=1 AND PL." + ID + " NOT IN (" + pl_id1 + "," + pl_id2 + ")" + " ORDER BY random() LIMIT 1;";
        PolishWord polishWord = selectPolishWord(selectQuery);

        db.close();
        return polishWord;
    }

    public PolishWord getRandomPolishWord(long pl_id1, long pl_id2, long pl_id3){
        db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PL_WORD + " PL, " + TABLE_PL_EN + " PLEN WHERE PL." + ID + " = PLEN." + FK_PL_ID +
                " AND PLEN." + MAIN_TRANSLATION + "=1 AND PL." + ID + " NOT IN (" + pl_id1 + "," + pl_id2 + "," + pl_id3 + ")" + " ORDER BY random() LIMIT 1;";
        PolishWord polishWord = selectPolishWord(selectQuery);

        db.close();
        return polishWord;
    }


    // lấy danh sách từ tiếng Việt là bản dịch khác với bản dịch CHÍNH// get a list of PolishWords which is other translation than MAIN translation
    public List<PolishWord> getOtherPolishTranslations(long en_id){
        List<PolishWord> polishWords = new ArrayList<PolishWord>();
        String selectQuery = "SELECT * FROM " + TABLE_PL_EN + " PLEN, " + TABLE_PL_WORD + " PL WHERE PL." + ID + "=" + "PLEN." + FK_PL_ID +
                " AND PLEN." + MAIN_TRANSLATION + "= 0" + " AND PLEN." + FK_EN_ID + "=" + en_id;

        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PolishWord polishWord = new PolishWord();
                polishWord.setId(c.getLong(c.getColumnIndex(ID)));
                polishWord.setPl_word(c.getString(c.getColumnIndex(PL_WORD)));

                // adding to englishwords list
                polishWords.add(polishWord);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return polishWords;
    }

    /****************************** ENGLISH_WORD ******************************/

    // chọn từ tiếng Anh từ cơ sở dữ liệu và trả về
    public EnglishWord selectEnglishWord(String selectQuery){
        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        EnglishWord englishWord = new EnglishWord();
        englishWord.setId(c.getLong(c.getColumnIndex(ID)));
        englishWord.setEn_word(c.getString(c.getColumnIndex(EN_WORD)));
        englishWord.setBad_answer(c.getInt(c.getColumnIndex(BAD_ANSWER)));
        englishWord.setKnown_word(c.getInt(c.getColumnIndex(KNOWN_WORD)));
        englishWord.setUncertained_word(c.getInt(c.getColumnIndex(UNCERTAINED_WORD)));
        englishWord.setUnknown_word(c.getInt(c.getColumnIndex(UNKNOWN_WORD)));

        c.close();
        db.close();
        return englishWord;
    }


    // add an EnglishWord
    public long addEnglishWord (EnglishWord en_word){

        ContentValues values = new ContentValues();
        values.put(EN_WORD, en_word.getEn_word());
        values.put(BAD_ANSWER,0);
        values.put(KNOWN_WORD,0);
        values.put(UNCERTAINED_WORD,0);
        values.put(UNKNOWN_WORD,0);

        // insert row
        long en_id = db.insert(TABLE_EN_WORD, null, values);

        return en_id;
    }


    // get a single EnglishWord
    public EnglishWord getEnglishWord(long en_id){
        db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_EN_WORD + " WHERE " + ID + " = " + en_id;
        EnglishWord englishWord = selectEnglishWord(selectQuery);

        db.close();
        return englishWord;
    }


    // nhận tất cả các từ tiếng Anh
    public List<EnglishWord> getAllEnglishWords(){
        List<EnglishWord> englishWords = new ArrayList<EnglishWord>();
        String selectQuery = "SELECT * FROM " + TABLE_EN_WORD;

        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // lặp qua tất cả các hàng và thêm vào danh sách
        if (c.moveToFirst()) {
            do {
                EnglishWord englishWord = new EnglishWord();
                englishWord.setId(c.getLong(c.getColumnIndex(ID)));
                englishWord.setEn_word(c.getString(c.getColumnIndex(EN_WORD)));

                // adding to englishwords list
                englishWords.add(englishWord);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return englishWords;
    }


    /****************************** VN_EN_TRANSLATION ******************************/

    // add VN-English Translation
    public long addTranslation(long fk_en_id, long fk_pl_id, int main_translation){
        ContentValues values = new ContentValues();
        values.put(FK_PL_ID, fk_pl_id);
        values.put(FK_EN_ID, fk_en_id);
        // main translation can be 0 - false or 1 - true
        values.put(MAIN_TRANSLATION, main_translation);

        long id = db.insert(TABLE_PL_EN, null, values);

        return id;
    }


    // đặt bad_answer là 1 - cập nhật Bản dịch Việt-Anh
    public void setBadAnswer(long id){
        db = getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_EN_WORD +
                " SET " + BAD_ANSWER + " = 1 WHERE " + ID + " = " + '"' + id + '"';
        db.execSQL(updateQuery);
        db.close();
    }


    // đặt god_answer là 2 - cập nhật Bản dịch tiếng Việt-Anh
    public void setGoodAnswer(long id){
        db = getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_EN_WORD +
                " SET " + BAD_ANSWER + " = 2 WHERE " + ID + " = " + '"' + id + '"';
        db.execSQL(updateQuery);
        db.close();
    }


    // set known_word - cập nhật Bản dịch tiếng Việt-tiếng Anh
    public void setUnknownWord(long id){
        db = getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_EN_WORD +
                " SET " + UNKNOWN_WORD +"= 1 , " +
                KNOWN_WORD + " = 0, " + UNCERTAINED_WORD + " = 0 WHERE " +
                ID + " = " + '"' + id + '"';
        db.execSQL(updateQuery);
        db.close();
    }


    // đặt câu lệnh không chắc chắn - cập nhật Bản dịch tiếng Việt-tiếng Anh
    public void setUncertainedWord(long id){
        db = getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_EN_WORD +
                " SET " + UNCERTAINED_WORD +"= 1 , " +
                KNOWN_WORD + " = 0, " + UNKNOWN_WORD + " = 0 WHERE " +
                ID + " = " + '"' + id + '"';
        db.execSQL(updateQuery);
        db.close();
    }

    // set known_word - cập nhật Bản dịch Việt-Anh
    public void setKnownWord(long id){
        db = getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_EN_WORD +
                " SET " + KNOWN_WORD +"= 1 , " +
                UNKNOWN_WORD + " = 0, " + UNCERTAINED_WORD + " = 0 WHERE " +
                ID + " = " + '"' + id + '"';
        db.execSQL(updateQuery);
        db.close();
    }



    /****************************** Phương thức khác ******************************/

    // nhận được tất cả bad_answer
    public List<EnglishWord> getAllBadAnswer(){
        List<EnglishWord> englishWords = new ArrayList<EnglishWord>();
        String selectQuery = "SELECT * FROM " + TABLE_EN_WORD + " WHERE " + BAD_ANSWER + " = 1" ;

        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // lặp qua tất cả các hàng và thêm vào danh sách
        if (c.moveToFirst()) {
            do {
                EnglishWord englishWord = new EnglishWord();
                englishWord.setId(c.getLong(c.getColumnIndex(ID)));
                englishWord.setEn_word(c.getString(c.getColumnIndex(EN_WORD)));

                // thêm vào danh sách englishwords
                englishWords.add(englishWord);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return englishWords;
    }


    // nhận tất cả good_answers
    public List<EnglishWord> getAllGoodAnswer(){
        List<EnglishWord> englishWords = new ArrayList<EnglishWord>();
        String selectQuery = "SELECT * FROM " + TABLE_EN_WORD + " WHERE " + BAD_ANSWER + " = 2" ;

        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // lặp qua tất cả các hàng và thêm vào danh sách
        if (c.moveToFirst()) {
            do {
                EnglishWord englishWord = new EnglishWord();
                englishWord.setId(c.getLong(c.getColumnIndex(ID)));
                englishWord.setEn_word(c.getString(c.getColumnIndex(EN_WORD)));

                // adding to englishwords list
                englishWords.add(englishWord);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return englishWords;
    }


    // getting all unknown words(nhận tất cả các từ không xác định)
    public List<EnglishWord> getAllUnknownWords(){
        List<EnglishWord> englishWords = new ArrayList<EnglishWord>();
        String selectQuery = "SELECT * FROM " + TABLE_EN_WORD + " WHERE " + UNKNOWN_WORD + " = 1" ;

        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list( lặp qua tất cả các hàng và thêm vào danh sách)
        if (c.moveToFirst()) {
            do {
                EnglishWord englishWord = new EnglishWord();
                englishWord.setId(c.getLong(c.getColumnIndex(ID)));
                englishWord.setEn_word(c.getString(c.getColumnIndex(EN_WORD)));

                // adding to englishwords list
                englishWords.add(englishWord);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return englishWords;
    }


    // getting all known words(từ xác định)
    public List<EnglishWord> getAllKnownWords(){
        List<EnglishWord> englishWords = new ArrayList<EnglishWord>();
        String selectQuery = "SELECT * FROM " + TABLE_EN_WORD + " WHERE " + KNOWN_WORD + " = 1" ;

        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list( lặp qua tất cả các hàng và thêm vào danh sách)
        if (c.moveToFirst()) {
            do {
                EnglishWord englishWord = new EnglishWord();
                englishWord.setId(c.getLong(c.getColumnIndex(ID)));
                englishWord.setEn_word(c.getString(c.getColumnIndex(EN_WORD)));

                // adding to englishwords list
                englishWords.add(englishWord);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return englishWords;
    }


    // getting all uncertained words(nhận tất cả các từ không chắc chắn)
    public List<EnglishWord> getAllUncertainedWords(){
        List<EnglishWord> englishWords = new ArrayList<EnglishWord>();
        String selectQuery = "SELECT * FROM " + TABLE_EN_WORD + " WHERE " + UNCERTAINED_WORD + " = 1" ;

        db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list( lặp qua tất cả các hàng và thêm vào danh sách)
        if (c.moveToFirst()) {
            do {
                EnglishWord englishWord = new EnglishWord();
                englishWord.setId(c.getLong(c.getColumnIndex(ID)));
                englishWord.setEn_word(c.getString(c.getColumnIndex(EN_WORD)));

                // adding to englishwords list
                englishWords.add(englishWord);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return englishWords;
    }
}
