package in.screenbiz.gstapply2017;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by harsh singh on 22-06-2017.
 */

public class Applyforgst extends Fragment {


    View applyforgstview;
    private WebView mWebView;
    public int counter;
    String websiteurl="https://www.gst.gov.in/"; //initialising the url


    Button gstregister,gstlogin,gstquicklinks,gstpayments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        applyforgstview = inflater.inflate(R.layout.fragment_applyforgst,container,false);

        AdView adview = (AdView) applyforgstview.findViewById(R.id.adView_applyforgst);
        AdRequest adrequest = new AdRequest.Builder().build();
        adview.loadAd(adrequest);





        gstregister = (Button) applyforgstview.findViewById(R.id.button_gstregister);
        gstregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                PackageManager pm = getActivity().getPackageManager();
                boolean chromeisinstalled = isPackageInstalled("com.android.chrome",pm);

                if(chromeisinstalled == false){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://reg.gst.gov.in/registration/"));
                    startActivity(intent);



                }
                else{


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://reg.gst.gov.in/registration/"));
                    intent.setPackage("com.android.chrome");  // package of SafeBrowser App
                    startActivity(intent);





                }


                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_SHORT).show();

                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_LONG).show();


            }
        });


        gstlogin = (Button) applyforgstview.findViewById(R.id.button_gstlogin);
        gstlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PackageManager pm = getActivity().getPackageManager();
                boolean chromeisinstalled = isPackageInstalled("com.android.chrome",pm);

                if(chromeisinstalled == false){


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://services.gst.gov.in/services/login"));
                    startActivity(intent);



                }
                else{


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://services.gst.gov.in/services/login"));
                    intent.setPackage("com.android.chrome");  // package of SafeBrowser App
                    startActivity(intent);
                }



                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_LONG).show();




            }
        });





        gstquicklinks = (Button) applyforgstview.findViewById(R.id.button_gstregisterquicklinks);
        gstquicklinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                PackageManager pm = getActivity().getPackageManager();
                boolean chromeisinstalled = isPackageInstalled("com.android.chrome",pm);

                if(chromeisinstalled == false){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://services.gst.gov.in/services/quicklinks/registration"));
                    startActivity(intent);



                }
                else{


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://services.gst.gov.in/services/quicklinks/registration"));
                    intent.setPackage("com.android.chrome");  // package of SafeBrowser App
                    startActivity(intent);





                }

                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_LONG).show();


            }
        });



        gstpayments = (Button) applyforgstview.findViewById(R.id.button_Payments);
        gstpayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PackageManager pm = getActivity().getPackageManager();
                boolean chromeisinstalled = isPackageInstalled("com.android.chrome",pm);

                if(chromeisinstalled == false){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://services.gst.gov.in/services/quicklinks/payments"));
                    startActivity(intent);



                }
                else{


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://services.gst.gov.in/services/quicklinks/payments"));
                    intent.setPackage("com.android.chrome");  // package of SafeBrowser App
                    startActivity(intent);





                }

                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity().getBaseContext(),"Transferred to Browser due to security reasons!! ",Toast.LENGTH_LONG).show();


            }
        });




        mWebView = (WebView) applyforgstview.findViewById(R.id.webView_applyforgst);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);



        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);


        Toast.makeText(getActivity().getBaseContext(),"Loading...",Toast.LENGTH_LONG).show();


        if (!DetectConnection.checkInternetConnection(getActivity().getBaseContext())) {
            Toast.makeText(getActivity().getBaseContext(), "Problem With the Internet!", Toast.LENGTH_LONG).show();

        }
        else
        {





            mWebView.loadUrl(websiteurl);
            counter = 0;
            Toast.makeText(getActivity().getBaseContext(),"Please wait...",Toast.LENGTH_LONG).show();
            mWebView.setWebViewClient((new HelloWebViewClient()));
        }
        mWebView.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {

                    Toast.makeText(getActivity().getBaseContext(),"Going Back...wait",Toast.LENGTH_SHORT).show();

                    mWebView.goBack();
                    return true;
                }
                else
                if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.getUrl() == mWebView.getOriginalUrl())
                {

                    //     getFragmentManager().beginTransaction().detach(whatIsGst.this).commit();
                    return false;
                }
                return false;
            }
        });


        return applyforgstview;
    }


    private boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packagename, 0);          //used for finding chrome browser
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }





    private class HelloWebViewClient extends WebViewClient {


    public boolean shouldOverrideUrlLoading(WebView webview, String url) {

        if (!DetectConnection.checkInternetConnection(getActivity().getBaseContext())) {
            Toast.makeText(getActivity().getBaseContext(), "Problem With the Internet!", Toast.LENGTH_LONG).show();

            Snackbar.make(getView(), "Problem With the Internet!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();    return true;
        } else {
            if (url == mWebView.getOriginalUrl().toString()) {
                counter = 0;
                Toast.makeText(getActivity().getBaseContext(), "Loading...", Toast.LENGTH_LONG).show();
                webview.loadUrl(url);
                return true;
            }

            else {
                counter = 1;
                Toast.makeText(getActivity().getBaseContext(), "Loading...", Toast.LENGTH_LONG).show();
                webview.loadUrl(url);
                return true;
            }

        }





    }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

            //controlled by diverting to web browser





        }




    }
}
