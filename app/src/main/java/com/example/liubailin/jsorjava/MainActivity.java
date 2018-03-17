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
       webView.addJavascriptInterface(this, "JavaObj");
    }

/**
 * 相当于添加一个js回调接口，然后给这个起一个别名，
 * 我这里起的名字jswritecard（js写卡）。
 * @android.webkit.JavascriptInterface 为了解
 * 决addJavascriptInterface漏洞的，在4.2以后才有的。
 */
    @android.webkit.JavascriptInterface
    public void showDate(final String data){

        Log.e(TAG, "js 调用 Java de showDate！！");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               textView.setText("来自js中的数据:"+data);
            }
        });
    }
    @android.webkit.JavascriptInterface
    public void showDate(){

        Log.e(TAG, "js 调用 Java de showDate！！");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("来自js中的调用");
            }
        });
    }
    /*这个函数去调用javascript中函数*/
    @android.webkit.JavascriptInterface
    void callJs(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:setDate('call from java')");
            }
        });
    }


    @android.webkit.JavascriptInterface
    String getDate(){
       return "hello,javascript";
    }

}
