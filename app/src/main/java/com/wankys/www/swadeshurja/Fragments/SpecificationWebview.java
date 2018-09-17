package com.wankys.www.swadeshurja.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.wankys.www.swadeshurja.R;


/**
 * Created by Elakiya on 4/2/2018.
 */

public class SpecificationWebview extends Fragment {
//    WebView webView;
//    public static String urlstring;
//    ProgressBar progressBar;
//
//    public static SpecificationWebview newInstance(String url)
//    {
//        urlstring =url;
//        SpecificationWebview fragment = new SpecificationWebview();
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.webview,container,false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        progressBar=getView().findViewById(R.id.progress_bar_id);
//        webView = getView().findViewById(R.id.web_view_id);
//        webView.setWebViewClient(new MyWebViewClient());
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.loadUrl(urlstring);
//    }
//    public class MyWebViewClient extends WebViewClient {
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            progressBar.setVisibility(View.INVISIBLE);
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            progressBar.setVisibility(View.VISIBLE);
//            view.loadUrl(url);
//            return true;
//        }
//    }
}

