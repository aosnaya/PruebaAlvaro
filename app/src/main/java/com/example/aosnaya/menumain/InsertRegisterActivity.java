package com.example.aosnaya.menumain;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aosnaya.DataBase.DataBase;
import com.example.aosnaya.pruebaalvaro.R;

/**
 * Created by aosnaya on 19/05/15.
 */
public class InsertRegisterActivity extends ActionBarActivity {

    EditText etxName, etxLastName, etxScore;
    Button btnInsert, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_register);
        setTitle(R.string.insert_regiser_activity);

        etxName = (EditText) findViewById(R.id.etx_name);
        etxLastName = (EditText) findViewById(R.id.etx_last_name);
        etxScore = (EditText) findViewById(R.id.etx_score);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnClear = (Button) findViewById(R.id.btn_clear);

        // Insert a register into database
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_insert:
                        String name = etxName.getText().toString();
                        String last_name = etxLastName.getText().toString();
                        String score = etxScore.getText().toString();
                        DataBase dataBase = new DataBase(InsertRegisterActivity.this, "SCORE", null, 1);
                        boolean bResult = dataBase.storeData(name, last_name, score);
                        if (bResult) {
                            Toast.makeText(InsertRegisterActivity.this, getResources().getString(R.string.insert_ok), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(InsertRegisterActivity.this, getResources().getString(R.string.insert_fail), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                        break;
                }
            }
        });

        // Clear EditTexts fields
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_clear:
                        etxName.setText("");
                        etxLastName.setText("");
                        etxScore.setText("");
                        break;
                }
            }
        });

    }
}
