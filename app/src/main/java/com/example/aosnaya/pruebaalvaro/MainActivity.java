package com.example.aosnaya.pruebaalvaro;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.aosnaya.menumain.InsertRegisterActivity;
import com.example.aosnaya.menumain.SearchRegisterActivity;
import com.example.aosnaya.menumain.SettingActivity;
import com.example.aosnaya.menumain.ViewGalleryActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aosnaya on 15/05/15.
 */
public class MainActivity extends ActionBarActivity {

    TextView txtJsonString, txtData, txtUser, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtJsonString = (TextView) findViewById(R.id.txt_json_string);
        txtData = (TextView) findViewById(R.id.txt_data);
        txtUser = (TextView) findViewById(R.id.txt_user);
        txtPass = (TextView) findViewById(R.id.txt_pass);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        intent.getExtras();
        String strJson = intent.getStringExtra("response");
        txtJsonString.setText(strJson);
        try {
            JSONObject jsonObject = new JSONObject(strJson);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            String strData = jsonObject1.toString();
            String strUser = jsonObject1.getString("user");
            String strPass = jsonObject1.getString("pass");
            txtData.setText(strData);
            txtUser.setText(strUser);
            txtPass.setText(strPass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_insert:
                startActivity(new Intent(MainActivity.this, InsertRegisterActivity.class));
                return true;

            case R.id.action_search:
                startActivity(new Intent(MainActivity.this, SearchRegisterActivity.class));
                return true;

            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                return true;

            case R.id.action_gallery:
                startActivity(new Intent(MainActivity.this, ViewGalleryActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
