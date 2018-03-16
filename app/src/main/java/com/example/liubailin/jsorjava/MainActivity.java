package com.example.liubailin.jsorjava;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="javascript" ;
    private WebView webView = null;
    private TextView textView = null;   /*主要用来显示状态*/

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化函数
     * 主要是初始化webview,textview
     */

    void init(){
       webView = findViewById(R.id.webView);
       textView = findViewById(R.id.textView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       webView.loadUrl("file:///android_asset/button.html");
       webView.addJavascriptInterface(this, "jswritecard");
    }

/**
 * 相当于添加一个js回调接口，然后给这个起一个别名，
 * 我这里起的名字jswritecard（js写卡）。
 * @android.webkit.JavascriptInterface 为了解
 * 决addJavascriptInterface漏洞的，在4.2以后才有的。
 */

    @android.webkit.JavascriptInterface
    public void getICCID(){
        //js 调用Java 方法  无参
        Log.e(TAG, "js 调用 Java de getICCID！！");
        //js若想更改Activity 需要使其运行在主线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // getCardICCID();
            }
        });

    }
    @android.webkit.JavascriptInterface

    public void get2F99(){

        Log.e(TAG, "js 调用 Java de get2F99！！");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getCard2F99();
            }
        });
    }
    @android.webkit.JavascriptInterface
    public void writeCard(final String indata){
        //js调用Java有参
        Log.e(TAG, "js 调用 Java de writeCard ,indata : "+indata);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                writeJavaCrad(indata);
            }
        });
    }

    private void getCard2F99() {
        textView.setText("call getCard2F99:");
    }

    private void writeJavaCrad(String indata) {
        textView.setText(" sent msg:"+indata);
    }


}
