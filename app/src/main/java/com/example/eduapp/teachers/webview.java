package com.example.eduapp.teachers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;


import com.example.eduapp.databinding.ActivityWebviewBinding;

public class webview extends AppCompatActivity {
ActivityWebviewBinding binding;
ProgressDialog progressDialog;
String weburl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWebviewBinding.inflate(getLayoutInflater());
        View v=binding.getRoot();
        setContentView(v);
progressDialog=new ProgressDialog(this);
progressDialog.setMessage("Loading...Please Wait");
        Intent intent=getIntent();
        weburl=intent.getStringExtra("url");
        checkconnection();
        binding.mywebview.loadUrl(weburl);
        binding.mywebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        binding.mywebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
               // binding.prgbar.setVisibility(View.VISIBLE);
                progressDialog.show();
                binding.toolbarwebview.setTitle("Loading.....");
                binding.prgbar.setProgress(newProgress);
                if(newProgress==100){
                  progressDialog.dismiss();
                    //  binding.prgbar.setVisibility(View.GONE);
                    binding.toolbarwebview.setTitle(view.getTitle());
                }

                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(binding.mywebview.canGoBack()){
            binding.mywebview.goBack();
        }
        else
        super.onBackPressed();
    }

    public void retry(View view) {
        checkconnection();

    }
    public  void checkconnection(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wifi.isConnected()){
            binding.mywebview.loadUrl(weburl);
            binding.mywebview.setVisibility(View.VISIBLE);
            binding.nonetlinear.setVisibility(View.GONE);
        }
        else if(mobile.isConnected()){
            binding.mywebview.loadUrl(weburl);
            binding.mywebview.setVisibility(View.VISIBLE);
            binding.nonetlinear.setVisibility(View.GONE);
        }
        else{
            binding.mywebview.setVisibility(View.GONE);
            binding.nonetlinear.setVisibility(View.VISIBLE);
        }
    }
}