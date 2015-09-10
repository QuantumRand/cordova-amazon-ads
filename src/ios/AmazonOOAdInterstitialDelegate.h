//
//  AmazonOOAdInterstitialDelegate.h
//  AmazonMobileAdsSDK
//
//  Copyright (c) 2014-2015 Amazon.com. All rights reserved.
//

@protocol AmazonAdInterstitialDelegate;
@class AmazonAdInterstitial;

@protocol AmazonOOAdInterstitialDelegate <AmazonAdInterstitialDelegate>
@optional
// This callback is triggered when a native URL is clicked on an interstitial.
// This feature is currently exposed for O&O apps.
- (void)interstitialDidClick:(AmazonAdInterstitial *)interstitial onPrivateURL:(NSURL *)privateURL;
@end
