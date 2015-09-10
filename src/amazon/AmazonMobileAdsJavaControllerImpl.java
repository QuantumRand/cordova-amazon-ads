
/* 
* Copyright 2014 Amazon.com,
* Inc. or its affiliates. All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the
* "License"). You may not use this file except in compliance
* with the License. A copy of the License is located at
*
* http://aws.amazon.com/apache2.0/
*
* or in the "license" file accompanying this file. This file is
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
* CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and
* limitations under the License.
*/
package com.amazon.mas.cpt.ads;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.concurrent.SdkEvent;
import com.amazon.cptplugins.concurrent.SdkEventListener;
import com.amazon.cptplugins.concurrent.SdkCallbackListener;
import com.amazon.cptplugins.concurrent.Callback;
import com.amazon.cptplugins.exceptions.AmazonException;
import com.amazon.cptplugins.exceptions.jsonutils.AmazonError;
import com.amazon.cptplugins.json.JsonSerializer;
import com.amazon.cptplugins.json.JsonSerializerImpl;
import com.amazon.cptplugins.validation.Assert;

import com.google.gson.reflect.TypeToken;

public class AmazonMobileAdsJavaControllerImpl implements AmazonMobileAdsJavaController {
    private final static int MAX_BLOCKED_CONCURRENT_ASYNC_CALLS = 10;
    private final static String TAG = "AmazonMobileAdsJavaControllerImpl";
    private final JsonSerializer json;
    private final ExecutorService executor;
    private final AmazonMobileAds amazonMobileAds;
    private volatile Context context = null;
    private volatile SdkEventListener sdkEventListener = null;
    private volatile SdkCallbackListener sdkCallbackListener = null;
    private volatile static AndroidResources androidResources = null;

    /**
      * Creates a new controller (and a new java service)
      */
    public static AmazonMobileAdsJavaControllerImpl newInstance() {
        AmazonMobileAdsJavaControllerImpl instance = new AmazonMobileAdsJavaControllerImpl(new JsonSerializerImpl(), Executors.newFixedThreadPool(MAX_BLOCKED_CONCURRENT_ASYNC_CALLS));
        instance.amazonMobileAds.setAmazonMobileAdsJavaController(instance);
        return instance;
    }
    
    public static AmazonMobileAdsJavaControllerImpl newInstance(final Context context) {
        AmazonMobileAdsJavaControllerImpl instance = new AmazonMobileAdsJavaControllerImpl(new JsonSerializerImpl(), Executors.newFixedThreadPool(MAX_BLOCKED_CONCURRENT_ASYNC_CALLS));
        instance.setContext(context);
        return instance;
    }

    private AmazonMobileAdsJavaControllerImpl(final JsonSerializer json, final ExecutorService executor) {
        this.amazonMobileAds = new AmazonMobileAdsImpl();
        this.json = json;
        this.executor = executor;
    }

    /**
      * Registers the controller with the service
      */
    @Override
    public void registerToJavaService() {
        amazonMobileAds.setAmazonMobileAdsJavaController(this);
	}

    @Override
    public void setSdkEventListener(final SdkEventListener listener) {
        this.sdkEventListener = listener;
    }
    
    @Override
    public void setSdkCallbackListener(final SdkCallbackListener listener) {
        this.sdkCallbackListener = listener;
    }

    @Override
    public String setApplicationKey(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<ApplicationKey>() { }.getType();
            final ApplicationKey input = json.fromJson(jsonMsg, typeOfT);
            this.amazonMobileAds.setApplicationKey(input);
            return "{}";
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String registerApplication(final String jsonMsg) {
        try {
            this.amazonMobileAds.registerApplication();
            return "{}";
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String enableLogging(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<ShouldEnable>() { }.getType();
            final ShouldEnable input = json.fromJson(jsonMsg, typeOfT);
            this.amazonMobileAds.enableLogging(input);
            return "{}";
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String enableTesting(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<ShouldEnable>() { }.getType();
            final ShouldEnable input = json.fromJson(jsonMsg, typeOfT);
            this.amazonMobileAds.enableTesting(input);
            return "{}";
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String enableGeoLocation(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<ShouldEnable>() { }.getType();
            final ShouldEnable input = json.fromJson(jsonMsg, typeOfT);
            this.amazonMobileAds.enableGeoLocation(input);
            return "{}";
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String createFloatingBannerAd(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<Placement>() { }.getType();
            final Placement input = json.fromJson(jsonMsg, typeOfT);
            final Ad result = this.amazonMobileAds.createFloatingBannerAd(input);
            return json.toJson(result);
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String createInterstitialAd(final String jsonMsg) {
        try {
            final Ad result = this.amazonMobileAds.createInterstitialAd();
            return json.toJson(result);
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String loadAndShowFloatingBannerAd(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<Ad>() { }.getType();
            final Ad input = json.fromJson(jsonMsg, typeOfT);
            final LoadingStarted result = this.amazonMobileAds.loadAndShowFloatingBannerAd(input);
            return json.toJson(result);
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String loadInterstitialAd(final String jsonMsg) {
        try {
            final LoadingStarted result = this.amazonMobileAds.loadInterstitialAd();
            return json.toJson(result);
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String showInterstitialAd(final String jsonMsg) {
        try {
            final AdShown result = this.amazonMobileAds.showInterstitialAd();
            return json.toJson(result);
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String closeFloatingBannerAd(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<Ad>() { }.getType();
            final Ad input = json.fromJson(jsonMsg, typeOfT);
            this.amazonMobileAds.closeFloatingBannerAd(input);
            return "{}";
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String isInterstitialAdReady(final String jsonMsg) {
        try {
            final IsReady result = this.amazonMobileAds.isInterstitialAdReady();
            return json.toJson(result);
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }
    @Override
    public String areAdsEqual(final String jsonMsg) {
        try {
            final Type typeOfT = new TypeToken<AdPair>() { }.getType();
            final AdPair input = json.fromJson(jsonMsg, typeOfT);
            final IsEqual result = this.amazonMobileAds.areAdsEqual(input);
            return json.toJson(result);
        } catch (final Exception e) {
            return json.toJson(new AmazonError(e));
        }
    }

    @Override
    public void fireAdCollapsed(final Ad adObject) {
        final SdkEvent<Ad> sdkEvent = new SdkEvent<Ad>("adCollapsed", adObject);
        final String sdkEventJson = json.toJson(sdkEvent);
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }
    @Override
    public void fireAdDismissed(final Ad adObject) {
        final SdkEvent<Ad> sdkEvent = new SdkEvent<Ad>("adDismissed", adObject);
        final String sdkEventJson = json.toJson(sdkEvent);
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }
    @Override
    public void fireAdExpanded(final Ad adObject) {
        final SdkEvent<Ad> sdkEvent = new SdkEvent<Ad>("adExpanded", adObject);
        final String sdkEventJson = json.toJson(sdkEvent);
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }
    @Override
    public void fireAdFailedToLoad(final Ad adObject) {
        final SdkEvent<Ad> sdkEvent = new SdkEvent<Ad>("adFailedToLoad", adObject);
        final String sdkEventJson = json.toJson(sdkEvent);
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }
    @Override
    public void fireAdLoaded(final Ad adObject) {
        final SdkEvent<Ad> sdkEvent = new SdkEvent<Ad>("adLoaded", adObject);
        final String sdkEventJson = json.toJson(sdkEvent);
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }
    @Override
    public void fireAdResized(final AdPosition adPosition) {
        final SdkEvent<AdPosition> sdkEvent = new SdkEvent<AdPosition>("adResized", adPosition);
        final String sdkEventJson = json.toJson(sdkEvent);
        if (this.sdkEventListener != null) {
            this.sdkEventListener.fireSdkEvent(sdkEventJson);
        }
    }

    @Override
    public Activity getCurrentAndroidActivity() {
        return AmazonMobileAdsJavaControllerImpl.delegateGetCurrentAndroidActivity();
    }

    @Override
    public void setAndroidResources(final AndroidResources androidResources) {
        AmazonMobileAdsJavaControllerImpl.androidResources = androidResources;
    }

    @Override
    public Context getContext() {
        return (Context) this.context;
    }

    @Override
    public void setContext(final Context androidContext) {
        this.context = androidContext;
    }

    @Override
    public void handleSdkCallback(final String responseJSON) {
        if (this.sdkCallbackListener != null) {
            this.sdkCallbackListener.handleSdkCallback(responseJSON);
        }
    }
    
    @Override
    public boolean runningOnUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
    
    private static Activity delegateGetCurrentAndroidActivity() {
        Assert.notNull(androidResources, "androidResoures");
        return androidResources.getCurrentAndroidActivity();
    }
    
    public CrossPlatformTool getCrossPlatformTool(){
        return androidResources.getCrossPlatformTool();
    }
}
