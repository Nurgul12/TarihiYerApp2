package com.example.lezzetdefter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    private ListView listele;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listele = findViewById(R.id.listele);

        databaseHelper = new DatabaseHelper(this);

        ekran();


    }

    private void ekran() {
        ArrayList<String> plansList = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_PLANS,
                new String[]{DatabaseHelper.COLUMN_YERAD, DatabaseHelper.COLUMN_İLAD,DatabaseHelper.COLUMN_TARİHCE},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            String yerad = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_YERAD));
            String ilad = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_İLAD));
            String tarihce = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TARİHCE));

            // Her öğeyi ayrı ayrı satırlara ekle
            plansList.add(yerad + "\n" + ilad + "\n" + tarihce);
        }


        cursor.close();
        db.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plansList);
        listele.setAdapter(arrayAdapter);
    }
}