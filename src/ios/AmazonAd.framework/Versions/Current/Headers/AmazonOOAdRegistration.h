//
//  AmazonOOAdRegistration.h
//  AmazonMobileAdsSDK
//
//  Copyright (c) 2012-2014 Amazon.com. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum
{
    AmazonAdRegistrationErrorNoNetwork,
    AmazonAdRegistrationErrorTimeOut,
    AmazonAdRegistrationErrorWithinQuietPeriod,
    AmazonAdRegistrationErrorUnknown,
} AmazonAdRegistrationError;

@protocol AmazonOOAdRegistrationDelegate;
@class AmazonAdAppEvent;

// Ad registration class for Amazon Owned and Operated properties.
@interface AmazonOOAdRegistration : NSObject

@property (nonatomic, weak) id<AmazonOOAdRegistrationDelegate> delegate;

+ (instancetype)sharedRegistration;

// Set the applicationId, applicationName and user specified marketplace
// The user specified marketplace is a two-character country code ("us", "uk", "de", "jp", "it", "es", etc).
- (void)setApplicationId:(NSString *)applicationId
         applicationName:(NSString *)applicationName
userSpecifiedMarketplace:(NSString *)marketplace;

// Enable/Disable logging. Logging is turned off by default.
- (void)setLogging:(BOOL)isEnabled;

// Enable/Disable TLS mode. All network requests are made over https when TLS is enabled.
- (void)setEnableTLS:(BOOL)isEnabled;

// Register your device to track app downloads from mobile ad campaigns. 
// To add app download conversion tracking to your app, invoke the 
// setApplicationId and the registerDevice from your applicationDelegate's
// applicationDidBecomeActive protocol method.
// 
// Note: Calling loadAd automatically registers your device. 
// Only call this method if you are not using the Amazon Ads SDK to 
// display Ads, but to track app downloads from mobile ads campaigns.
//
// This call checks if your device is already been registered. If so, it then
// checks if the registration timestamp is recent (i.e. less than the time interval
// specified for the device registration frequency). If the timestamp is recent, then it does nothing.
// Otherwise, the registerDevice method makes a couple of network calls
// in the background to refresh the device information. These calls allow us to 
// create app segments for future download advertising campaigns. 
// For example you could target devices that don’t have your app installed. 
// Or when releasing a sequel you could target devices that both have the prequel app 
// installed and have made a register/refresh call in the last 30 days.
- (void)registerDevice;

// Sets how often the SDK registers the device information and dispatches app events to the server.
// By default, the frequency is every 24 hours. It is recommended to keep time interval at its default value.
// Only call this method if your app just uses registerDevice or you explicitly wish to set the frequncy for dispatching app events.
- (void)setRegistrationFrequencyTimeInterval:(NSTimeInterval)timeInterval;

// Gets the URL to the Ad Preferences Page.
- (NSString *)adPreferencesURL;

// Returns a dictionary of native device information.
- (NSDictionary *)deviceNativeData;

// Returns a dictionary of device information.
- (NSDictionary *)deviceData;

// Returns the current SDK version
- (NSString *)sdkVersion;

// Tracks application events e.g. first login, first purchase. The collected events are stored in a cache file, and periodically
// dispatched to the server.
- (void)registerEvent:(AmazonAdAppEvent *)appEvent;

@end

@protocol AmazonOOAdRegistrationDelegate <NSObject>

@optional

// These call backs are triggered when device registration finishes
- (void)amazonAdRegisterDeviceDidSucceed;
- (void)amazonAdRegisterDeviceDidFailWithError:(NSError*)error;

@end
