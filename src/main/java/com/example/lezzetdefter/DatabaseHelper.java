package com.example.lezzetdefter;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "yemekler.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PLANS = "plans";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_YERAD = "day";
    public static final String COLUMN_İLAD = "plan";

    public static final String COLUMN_TARİHCE = "tarif";


    private static final String CREATE_TABLE_PLANS =
            "CREATE TABLE " + TABLE_PLANS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_YERAD + " TEXT," +
                    COLUMN_İLAD + "TEXT," +
                    COLUMN_TARİHCE + " TEXT)";



    // veri tabanını oluşturur ya da eişir.
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // tablo oluşturuyor
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLANS);
    }
    //Veritabanı sürümü değiştiğinde çağrılan metod. Eski tabloyu (TABLE_PLANS) kaldırır
// (DROP TABLE IF EXISTS) ve onCreate metodunu tekrar çağırarak yeni tabloyu oluşturur.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANS);
        onCreate(db);
    }
    // DatabaseHelper sınıfınıza veri çekme fonksiyonu ekleyelim


}