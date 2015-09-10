//
//  AmazonOOAdViewDelegate.h
//  AmazonMobileAdsSDK
//
//  Copyright (c) 2012-2015 Amazon.com. All rights reserved.
//

@protocol AmazonAdViewDelegate;
@class AmazonAdView;

@protocol AmazonOOAdViewDelegate <AmazonAdViewDelegate>
@optional
// This callback is triggered when a native URL is clicked on an Ad. 
// This feature is currently exposed for the Amazon Shopping app.
- (void)adViewDidClick:(AmazonAdView *)view onURL:(NSURL *)URL;
@end

@interface AmazonAdView (OO)
@property (nonatomic, readonly) NSString *requestId;
@end
