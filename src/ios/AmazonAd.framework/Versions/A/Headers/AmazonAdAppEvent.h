//
//  AmazonOOAppEvent.h
//  AmazonMobileAdsSDK
//
//  Copyright (c) 2014-2015 Amazon.com. All rights reserved.
//

#import <Foundation/Foundation.h>

extern NSString * const AmazonAdAppEventFirstLogin;
extern NSString * const AmazonAdAppEventFirstPurchase;

@interface AmazonAdAppEvent : NSObject

// Get/set the event name
@property (nonatomic, readonly) NSString *eventName;
@property (nonatomic, readonly) NSTimeInterval timestamp;

// Returns an instance of AmazonOOAppEvent with an event name and a timestamp
+ (instancetype)appEventWithName:(NSString *)name timestamp:(NSTimeInterval)ts;

// Instantiate an instance of AmazonOOAppEvent an event name and a timestamp
- (instancetype)initWithName:(NSString *)name timestamp:(NSTimeInterval)timestamp;

@end
