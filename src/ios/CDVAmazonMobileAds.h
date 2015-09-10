
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
#import <UIKit/UIKit.h>
#import <Cordova/CDVPlugin.h>
#import "AMAZONAmazonMobileAdsObjectiveCControllerImpl.h"

@interface CDVAmazonMobileAds : CDVPlugin <AMAZONAmazonMobileAdsEventListenerDelegate>
{

}

- (void)setApplicationKey:(CDVInvokedUrlCommand*)command;
- (void)registerApplication:(CDVInvokedUrlCommand*)command;
- (void)enableLogging:(CDVInvokedUrlCommand*)command;
- (void)enableTesting:(CDVInvokedUrlCommand*)command;
- (void)enableGeoLocation:(CDVInvokedUrlCommand*)command;
- (void)createFloatingBannerAd:(CDVInvokedUrlCommand*)command;
- (void)createInterstitialAd:(CDVInvokedUrlCommand*)command;
- (void)loadAndShowFloatingBannerAd:(CDVInvokedUrlCommand*)command;
- (void)loadInterstitialAd:(CDVInvokedUrlCommand*)command;
- (void)showInterstitialAd:(CDVInvokedUrlCommand*)command;
- (void)closeFloatingBannerAd:(CDVInvokedUrlCommand*)command;
- (void)isInterstitialAdReady:(CDVInvokedUrlCommand*)command;
- (void)areAdsEqual:(CDVInvokedUrlCommand*)command;

@end

