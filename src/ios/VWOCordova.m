#import "VWOCordova.h"
#import <VWO/VWO.h>
#import "NSDictionary+BVJSONString.h"

@implementation VWOCordova {
    BOOL started;
}

-(void)start: (CDVInvokedUrlCommand*)command {
    [VWO launchVWOWithCallback:^{
        started = YES;
        [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK]
                                    callbackId:command.callbackId];
    }];
}

-(void)getAllObject: (CDVInvokedUrlCommand*)command {
    CDVPluginResult* pluginResult = nil;

    if (!started) {
        [VWO launchVWO];
    }

    NSDictionary *object = [VWO allObjects];

    if (object != nil) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[object bv_jsonStringWithPrettyPrint:NO]];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
    }

    [self.commandDelegate sendPluginResult:pluginResult
                                callbackId:command.callbackId];
}

-(void)markConversionForGoal: (CDVInvokedUrlCommand*)command {
    CDVPluginResult* pluginResult = nil;

    if (!started) {
        [VWO launchVWO];
    }

    NSString *goalIdentifier = [command argumentAtIndex:0];
    id value = [command argumentAtIndex:1];

    if (value != nil) {
        [VWO markConversionForGoal:goalIdentifier withValue: [value doubleValue]];
        NSLog(@"markConversionForGoal with %@ and value %f", goalIdentifier, [value doubleValue]);
    } else {
        [VWO markConversionForGoal:goalIdentifier];
        NSLog(@"markConversionForGoal with %@", goalIdentifier);
    }

    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
