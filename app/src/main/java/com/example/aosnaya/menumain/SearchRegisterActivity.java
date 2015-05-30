package com.example.aosnaya.menumain;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aosnaya.DataBase.DataBase;
import com.example.aosnaya.pruebaalvaro.R;

import java.util.Vector;

/**
 * Created by aosnaya on 19/05/15.
 */
public class SearchRegisterActivity extends Activity {

    RadioGroup rgpSearch;
    RadioButton rbtId, rbtName, rbtLastName, rbtScore;
    EditText etxField;
    Button btnSearch;
    ListView lvwRegisters;
    Vector<String> vecResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_register);

        rgpSearch = (RadioGroup) findViewById(R.id.rgp_search);
        rbtId = (RadioButton) findViewById(R.id.rbt_id);
        rbtName = (RadioButton) findViewById(R.id.rbt_name);
        rbtLastName = (RadioButton) findViewById(R.id.rbt_last_name);
        rbtScore = (RadioButton) findViewById(R.id.rbt_score);
        etxField = (EditText) findViewById(R.id.etx_field);
        btnSearch = (Button) findViewById(R.id.btn_search);
        lvwRegisters = (ListView) findViewById(R.id.lvw_registers);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_search:
                        vecResult = new Vector<>();
                        DataBase dataBase = new DataBase(SearchRegisterActivity.this, "SCORE", null, 1);
                        if (rbtId.isChecked()) {
                            String id = etxField.getText().toString();
                            vecResult = dataBase.vecSearchByID(Long.parseLong(id));
                        } else if (rbtName.isChecked()) {
                            String name = etxField.getText().toString();
                            vecResult = dataBase.vecSearchByName(name);
                        } else if (rbtLastName.isChecked()) {
                            String lastName = etxField.getText().toString();
                            vecResult = dataBase.vecSearchByLastName(lastName);
                        } else if (rbtScore.isChecked()) {
                            String score = etxField.getText().toString();
                            vecResult = dataBase.vecSearchByScore(Integer.parseInt(score));
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchRegisterActivity.this, android.R.layout.simple_expandable_list_item_1, vecResult);
                        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                        lvwRegisters.setAdapter(adapter);
                        break;
                }
            }
        });

    }
}
