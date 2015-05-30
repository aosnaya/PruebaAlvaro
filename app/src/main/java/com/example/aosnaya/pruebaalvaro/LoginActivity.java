package com.example.aosnaya.pruebaalvaro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity {

    Button btnLogin;
    EditText  etxUser, etxPass;


//    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etxUser = (EditText) findViewById(R.id.etx_user);
        etxPass = (EditText) findViewById(R.id.etx_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_login:
                        String strUser = etxUser.getText().toString();
                        String strPass = etxPass.getText().toString();
                        String strResponse = parseJSON(strUser, strPass);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("response", strResponse);
                        intent.putExtras(bundle);
                        startActivity(intent);
//                        scanQR();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        etxUser.setText("");
        etxPass.setText("");
    }

    private String parseJSON(String user, String pass) {
        String strResponse = "";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pass", pass);
            jsonObject.put("user", user);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("data", jsonObject);
            strResponse = jsonObject1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return strResponse;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void scanBar(View v) {
//        try {
//            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
//            Intent intent = new Intent(ACTION_SCAN);
//            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
//            startActivityForResult(intent, 0);
//        } catch (ActivityNotFoundException anfe) {
//            //on catch, show the download dialog
//            showDialog(LoginActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
//        }
//    }
//
//    //product qr code mode
//    public void scanQR(View v) {
//        try {
//            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
//            Intent intent = new Intent(ACTION_SCAN);
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//            startActivityForResult(intent, 0);
//        } catch (ActivityNotFoundException anfe) {
//            //on catch, show the download dialog
//            showDialog(LoginActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
//        }
//    }
//
//    //alert dialog for downloadDialog
//    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
//        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
//        downloadDialog.setTitle(title);
//        downloadDialog.setMessage(message);
//        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                try {
//                    act.startActivity(intent);
//                } catch (ActivityNotFoundException anfe) {
//                }
//            }
//        });
//        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//            }
//        });
//        return downloadDialog.show();
//    }
//
//    //on ActivityResult method
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                //get the extras that are returned from the intent
//                String contents = intent.getStringExtra("SCAN_RESULT");
//                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
//                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
//                toast.show();
//            }
//        }
//    }

}
