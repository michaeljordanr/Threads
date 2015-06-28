package com.example.michael.threads;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView textView;
    private Button btnProcessar;
    //private Handler handler = new Handler();

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txtStatus);
        btnProcessar = (Button) findViewById(R.id.btnProcessar);

        handler = new MyHandler(textView, btnProcessar);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void processar(View view) {
        textView.setText(getString(R.string.processing));
        btnProcessar.setEnabled(false);
        executarAltoDemorado();
    }

    private void executarAltoDemorado() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(getString(R.string.processed));
//                        btnProcessar.setEnabled(false);
//                    }
//                });

//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(getString(R.string.processed));
//                            btnProcessar.setEnabled(true);
//                    }
//                });

                Message msg = Message.obtain();
                msg.what = MyHandler.MSG_UPDATE_UI;
                handler.sendMessage(msg);
            }
        }).start();
    }
}
