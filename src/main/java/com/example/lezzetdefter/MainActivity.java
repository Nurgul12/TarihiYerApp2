package com.example.lezzetdefter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAd, editTextİlAd,editTextTarihce;
    private Button buttonKaydet, buttonGoster, buttonSil;



    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAd = findViewById(R.id.EditTextAd);
        editTextİlAd = findViewById(R.id.EditTextİlAd);
        editTextTarihce = findViewById(R.id.EditTextTarihce);
        buttonKaydet = findViewById(R.id.buttonKaydet);
        buttonGoster = findViewById(R.id.buttonGoster);
        buttonSil = findViewById(R.id.buttonSil);

        databaseHelper = new DatabaseHelper(this);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveYemek();
            }
        });

        buttonGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYemek();
            }
        });

        buttonSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil();
            }
        });

    }

    private void saveYemek() {
        String Ad = editTextAd.getText().toString();
        String İlAd = editTextİlAd.getText().toString();
        String Tarihce = editTextTarihce.getText().toString();

        // Veritabanına planı ekleme
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_YERAD, Ad);
        values.put(DatabaseHelper.COLUMN_İLAD, İlAd);
        values.put(DatabaseHelper.COLUMN_TARİHCE,Tarihce);
        db.insert(DatabaseHelper.TABLE_PLANS, null, values);
        db.close();

        // Giriş ekranındaki EditText'leri temizleme
        editTextAd.setText("");
        editTextİlAd.setText("");
        editTextTarihce.setText("");
    }

    private void showYemek() {
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }

    private void sil() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_PLANS, null, null);
        db.close();
    }

}