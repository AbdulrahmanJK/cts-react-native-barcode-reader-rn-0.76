package com.barcodereader;

import android.util.Log;
import android.content.IntentFilter;
import android.content.Context;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNAndroidBarcodeBroadcastModule extends ReactContextBaseJavaModule {

    public static ReactApplicationContext reactContext;
    private static final String ACTION_BARCODE_DATA = "android.intent.ACTION_DECODE_DATA";
    private BarcodeBroadcastReceiver receiver;

    public RNAndroidBarcodeBroadcastModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.receiver = new BarcodeBroadcastReceiver();
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            reactContext.registerReceiver(receiver, 
                                       new IntentFilter(ACTION_BARCODE_DATA), 
                                       Context.RECEIVER_EXPORTED);
        } else {
            reactContext.registerReceiver(receiver, new IntentFilter(ACTION_BARCODE_DATA));
        }
    }

    @Override
    public String getName() {
        return "RNAndroidBarcodeBroadcast";
    }

    @Override
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        if (receiver != null) {
            reactContext.unregisterReceiver(receiver);
        }
    }
}