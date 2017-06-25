package in.screenbiz.gstapply2017;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by harsh singh on 21-06-2017.
 */

public class gstRegitrationTips extends Fragment {


    View gstRegitrationTipsView;
    private WebView mWebView;
    public int counter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        gstRegitrationTipsView = inflater.inflate(R.layout.fragment_regitrationtips, container, false);


        AdView adview = (AdView) gstRegitrationTipsView.findViewById(R.id.adView_regitrationtips);
        AdRequest adrequest = new AdRequest.Builder().build();
        adview.loadAd(adrequest);


        mWebView = (WebView) gstRegitrationTipsView.findViewById(R.id.webView_regitrationtips);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);


        Toast.makeText(getActivity().getBaseContext(), "Loading...", Toast.LENGTH_LONG).show();

        if (!DetectConnection.checkInternetConnection(getActivity().getBaseContext())) {
            Toast.makeText(getActivity().getBaseContext(), "Problem With the Internet!", Toast.LENGTH_LONG).show();
        }
        else{
            mWebView.loadUrl("https://www.indiafilings.com/learn/get-gst-registration-online/");
            Toast.makeText(getActivity().getBaseContext(),"Please wait...",Toast.LENGTH_LONG).show();
            counter = 0;
            mWebView.setWebViewClient((new HelloWebViewClient()));
        }



        mWebView.setOnKeyListener(new View.OnKeyListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {

                    Toast.makeText(getActivity().getBaseContext(), "Going Back...wait", Toast.LENGTH_SHORT).show();

                    mWebView.goBack();
                    return true;
                } else if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.getUrl() == mWebView.getOriginalUrl()) {

                    //     getFragmentManager().beginTransaction().detach(whatIsGst.this).commit();
                    return false;
                }
                return false;
            }
        });


        return gstRegitrationTipsView;
    }


    private class HelloWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView webview, String url) {

            if (!DetectConnection.checkInternetConnection(getActivity().getBaseContext())) {
                Toast.makeText(getActivity().getBaseContext(), "Problem With the Internet!", Toast.LENGTH_LONG).show();
                Snackbar.make(getView(), "Problem With the Internet!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            } else {
                if (url == mWebView.getOriginalUrl().toString()) {
                    counter = 0;
                    Toast.makeText(getActivity().getBaseContext(), "Loading...", Toast.LENGTH_LONG).show();
                    webview.loadUrl(url);
                    return true;
                } else {
                    counter = 1;
                    Toast.makeText(getActivity().getBaseContext(), "Loading...", Toast.LENGTH_LONG).show();
                    webview.loadUrl(url);
                    return true;
                }

            }


        }
    }
}
