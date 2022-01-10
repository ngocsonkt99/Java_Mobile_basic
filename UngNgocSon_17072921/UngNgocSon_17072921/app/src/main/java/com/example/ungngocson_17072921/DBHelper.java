package com.example.ungngocson_17072921;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "db.sqlite",  null,  1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Nhanvienn("+
                "manv integer primary key,"+
                "tennv text,"+
                "gioitinh text,"+
                "phongban text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Nhanvienn");
        onCreate(sqLiteDatabase);
    }
    public int insertNhanvien(Nhanvien nhanvien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("manv", nhanvien.getMaso());
        contentValues.put("tennv", nhanvien.getTennv());
        contentValues.put("gioitinh", nhanvien.getGioitinh());
        contentValues.put("phongban", nhanvien.getPhongban());
        int result = (int)db.insert("Nhanvienn", null, contentValues);
        db.close();
        return result;
    }
}
