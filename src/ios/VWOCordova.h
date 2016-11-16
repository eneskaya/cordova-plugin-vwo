#import <Cordova/CDV.h>
#import <Cordova/CDVPlugin.h>

@interface VWOCordova : CDVPlugin

-(void)start: (CDVInvokedUrlCommand*)command;
-(void)getAllObject: (CDVInvokedUrlCommand*)command;
-(void)markConversionForGoal: (CDVInvokedUrlCommand*)command;

@end
