//
//  AmazonOOHybridAdRegistration.h
//  AmazonMobileAdsSDK
//
//  Copyright (c) 2013-2015 Amazon.com. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AmazonOOHybridAdBridge : NSObject

+ (instancetype)sharedRegistration;

// Returns a dictionary of app information
// The returned dictionary is consumed by the MASH native layer and is populated to the return object of getAppInfo on the MASH javascript layer
// This provides a mechanism for a MASH enabled javascript to read native app info.
- (NSDictionary *)appInfo;

// Pushes an Mobile Ads view controller on top of the parent view controller
// The Mobile Ads view controller contains view necessary to launch the url and navigate the page
- (void)openInAppBrowserWithURLString:(NSString *)url parentViewController:(UIViewController *)parentViewController;

@end
