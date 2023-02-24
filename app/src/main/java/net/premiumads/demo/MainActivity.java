package net.premiumads.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdInspectorError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnAdInspectorClosedListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PremiumAds";
    private AdManagerAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init MobileAds SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        Log.d(TAG, "Google Mobile Ads SDK Version: " + MobileAds.getVersion());

        //Set test Device
        MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
                        .build());
        //Load ads
        adView = findViewById(R.id.ad_view);
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);

        findViewById(R.id.openAdInspector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobileAds.openAdInspector(MainActivity.this, new OnAdInspectorClosedListener() {
                    public void onAdInspectorClosed(@Nullable AdInspectorError error) {
                        // Error will be non-null if ad inspector closed due to an error.
                    }
                });
            }
        });
    }
}