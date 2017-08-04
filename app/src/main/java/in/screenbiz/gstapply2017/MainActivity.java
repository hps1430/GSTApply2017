package in.screenbiz.gstapply2017;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {



    DrawerLayout drawer;
    int acceptterms=1;

    public InterstitialAd mInterstitialAd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2990813587526663/3687215030");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());                    //setup for interstitial ads

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });






        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now

//            acceptterms=0;
//
//            if(acceptterms==0) {
//                new AlertDialog.Builder(this).setTitle("Terms & Conditions")
//                        .setMessage("1.GST Apply 2017 app does  ")
//                        .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                                System.exit(0);
//                            }
//                        }).setPositiveButton("I Accept", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        acceptterms = 1;
//                        Toast.makeText(getApplicationContext(), "Thank You for Accepting Terms!", Toast.LENGTH_SHORT).show();
//
//                        dialogInterface.dismiss();
//
//
//                    }
//                }).setNeutralButton("Open Link", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        String url = "https://www.screenbiz.in/";
//
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse(url));
//                        startActivity(intent);
//
//
//                    }
//                }).show();
//
//            }

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest               //runtime permissions ask
                    .permission.CALL_PHONE}, 1);


        }




            MobileAds.initialize(this);
            AdView adview = (AdView) findViewById(R.id.adView_mainactivity_banner);
            AdRequest adrequest = new AdRequest.Builder().build();
            adview.loadAd(adrequest);


            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_viewer, new whatIsGst()).commit();


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mailtohelpdesk();
                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


    }

    private void mailtohelpdesk() {


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {"helpdesk@gst.gov.in"};
        intent.putExtra(intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding GST");
        intent.setType("message/rcf822");
        startActivity(Intent.createChooser(intent, "Email Helpdesk"));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


            new AlertDialog.Builder(this).setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                Log.d("TAG", "The interstitial wasn't loaded yet.");
                            }
                            finish();
                            System.exit(0);
                        }
                    }).setNegativeButton("No", null).show();


        }


    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_applynow) {

            getprovisionalidfirst_dialog();

            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer, new Applyforgst()).commit();

            return true;
        } else if (id == R.id.action_mailhelpdesk) {

            mailtohelpdesk();
            return true;


        } else if (id == R.id.action_callhelpdesk) {




            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:01204888999"));
            Toast.makeText(this, "Calling GST Help Desk ", Toast.LENGTH_LONG).show();

            if (acceptterms == 1) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Check Permissions Now


                    ActivityCompat.requestPermissions(this, new String[]{Manifest               //runtime permissions ask
                            .permission.CALL_PHONE}, 1);
                }


            }



            startActivity(intent);


            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }



            }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.gst_item_calculator) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new gstFragment()).commit();


        } else if (id == R.id.nav_whatisgst) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new whatIsGst()).commit();





        } else if (id == R.id.nav_registrationtips) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new gstRegitrationTips()).commit();




        } else if (id == R.id.nav_whyregister) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new WhyRegister()).commit();




        }else if (id == R.id.nav_getprovisionalids) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new GetProvisionalids()).commit();




        } else if (id == R.id.nav_applyforgst) {


            getprovisionalidfirst_dialog();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new Applyforgst()).commit();







        }else if (id == R.id.nav_gstnews) {


            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new gstnews()).commit();



        }else if (id == R.id.nav_contact) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_viewer,new Contact()).commit();




        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getprovisionalidfirst_dialog() {


        new AlertDialog.Builder(this).setTitle("Get Provisional ID First")
                .setMessage("1.Before Applying for GSTIN you must have Provisional IDs for Registration if you are registered tax payer.\n\n" +
                        "2. You can either get it by selecting 'Get Provional ID' option on App or your provional ID will come from concerned Tax Officer.\n\n" +
                        "3. Read FAQs for more clearity.").setNegativeButton("Okay,Got It", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                dialogInterface.dismiss();

            }
        }).setNeutralButton("Registration FAQ's", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String url = "https://www.aces.gov.in/Documents/faq-on-mig-to-gst.pdf";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        }).show();







    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){

            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                // Permission was denied or request was cancelled


            }


        }


    }





}

