
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

import android.content.Context;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.amazon.cptplugins.AndroidResources;
import com.amazon.cptplugins.CrossPlatformTool;
import com.amazon.cptplugins.SdkEvent;


import android.app.Activity;

import com.amazon.mas.cpt.ads.AmazonMobileAdsJavaController;
import com.amazon.mas.cpt.ads.AmazonMobileAdsJavaControllerImpl;

import com.amazon.cptplugins.concurrent.SdkEventListener;

public class AmazonMobileAdsPlugin extends CordovaPlugin implements AndroidResources,  SdkEventListener {
    private static final String TAG = "AmazonMobileAds";
    private static final String ERROR = "error";
    private static final String CALLER_ID = "callerId";
    private static final String RESPONSE = "response";
    private static final String PLUGIN = "AmazonMobileAds";

    private volatile CordovaInterface cordova;
    private AmazonMobileAdsJavaControllerImpl sdkController = null;
    
    public AmazonMobileAdsPlugin() {
            
    }
    
    public AmazonMobileAdsJavaControllerImpl getSdkController() {
        return this.sdkController;
    }
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.cordova = cordova;
        Context context = ((CordovaActivity)cordova.getActivity()).getApplicationContext();
        sdkController = AmazonMobileAdsJavaControllerImpl.newInstance(context);
        this.sdkController.setAndroidResources((AndroidResources)this);
        this.sdkController.setSdkEventListener((SdkEventListener)this);
        this.sdkController.registerToJavaService();        
    }

    @Override
    public Activity getCurrentAndroidActivity() {
        return this.cordova.getActivity();
    }
    
    @Override
    public CrossPlatformTool getCrossPlatformTool() {
        return CrossPlatformTool.CORDOVA;
    }

    private enum OPERATIONS {
            SETAPPLICATIONKEY {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing SetApplicationKey...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().setApplicationKey(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            },
            REGISTERAPPLICATION {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing RegisterApplication...");
                    String result;
                    result = plugin.getSdkController().registerApplication(args.toString());
                    return plugin.sendPluginResult(result,callbackContext,false);
                }
            },
            ENABLELOGGING {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing EnableLogging...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().enableLogging(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            },
            ENABLETESTING {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing EnableTesting...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().enableTesting(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            },
            ENABLEGEOLOCATION {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing EnableGeoLocation...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().enableGeoLocation(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            },
            CREATEFLOATINGBANNERAD {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing CreateFloatingBannerAd...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().createFloatingBannerAd(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            },
            CREATEINTERSTITIALAD {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing CreateInterstitialAd...");
                    String result;
                    result = plugin.getSdkController().createInterstitialAd(args.toString());
                    return plugin.sendPluginResult(result,callbackContext,false);
                }
            },
            LOADANDSHOWFLOATINGBANNERAD {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing LoadAndShowFloatingBannerAd...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().loadAndShowFloatingBannerAd(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            },
            LOADINTERSTITIALAD {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing LoadInterstitialAd...");
                    String result;
                    result = plugin.getSdkController().loadInterstitialAd(args.toString());
                    return plugin.sendPluginResult(result,callbackContext,false);
                }
            },
            SHOWINTERSTITIALAD {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing ShowInterstitialAd...");
                    String result;
                    result = plugin.getSdkController().showInterstitialAd(args.toString());
                    return plugin.sendPluginResult(result,callbackContext,false);
                }
            },
            CLOSEFLOATINGBANNERAD {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing CloseFloatingBannerAd...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().closeFloatingBannerAd(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            },
            ISINTERSTITIALADREADY {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing IsInterstitialAdReady...");
                    String result;
                    result = plugin.getSdkController().isInterstitialAdReady(args.toString());
                    return plugin.sendPluginResult(result,callbackContext,false);
                }
            },
            AREADSEQUAL {
                @Override
                public boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin) {
                    LOG.d(TAG,"Executing AreAdsEqual...");
                    String result;
                    try {
                        JSONObject inputJson = args.getJSONObject(0);
                        result = plugin.getSdkController().areAdsEqual(inputJson.toString());
                        return plugin.sendPluginResult(result,callbackContext,false);
                    } catch(JSONException e) {
                        LOG.d(TAG,e.getMessage());
                            return false;
                    }
                }
            };

        public abstract boolean execute(JSONArray args, CallbackContext callbackContext, AmazonMobileAdsPlugin plugin);
    }
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            return OPERATIONS.valueOf(action.toUpperCase()).execute(args, callbackContext, this);
        } catch (IllegalArgumentException e) {
            LOG.d(TAG,"Invalid action - " + action);
            return false;
        }
        //return true;
    }

    @Override
    public void fireSdkEvent(String jsonEventResponse) {
        LOG.d(TAG,"Sdk event was fired");
        final String escapedJsonEventResponse = jsonEventResponse.replaceAll("'", "\\'");
        this.webView.sendJavascript("javascript:" + PLUGIN + ".fire('" + escapedJsonEventResponse + "')");
    }


    public boolean sendPluginResult(final String result,final CallbackContext callbackContext,final boolean keepCallback) {
        boolean ret = false;
        PluginResult resultToSend = null;
        try {
            if (result == null || result.equals("null")) {
                String errorMsg = "error: null response from plugin";
                resultToSend = new PluginResult(Status.ERROR, errorMsg);
                ret = false;
            } else {
                JSONObject resultJO = new JSONObject(result);
                if (resultJO.has(RESPONSE)) {
                    JSONObject responseJO = resultJO.getJSONObject(RESPONSE);
                    if (responseJO.has(ERROR)) {
                        String errorMsg = "error : " + (String)responseJO.get(ERROR);
                        resultToSend = new PluginResult(Status.ERROR,errorMsg);
                        ret = false;
                    } else {
                        resultToSend = new PluginResult(Status.OK,responseJO);
                         if (keepCallback) {
                                                resultToSend.setKeepCallback(true);
                                        }
                        ret = true;
                    }
                } else { //TODO need to remove this else block once we refactor response JSON
                    if (resultJO.has(ERROR)) {
                        String errorMsg = "error : " + (String)resultJO.get(ERROR);
                        resultToSend = new PluginResult(Status.ERROR,errorMsg);
                        ret = false;
                    } else {
                        resultToSend = new PluginResult(Status.OK,resultJO);
                        if (keepCallback) {
                                                    resultToSend.setKeepCallback(true);
                                            }
                        ret = true;
                    }            
                }
            }    
            if (resultToSend != null) {
                callbackContext.sendPluginResult(resultToSend);
            }
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
            ret = false;
        }
        return ret;
    }
}
