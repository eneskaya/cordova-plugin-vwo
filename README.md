# cordova-plugin-vwo
VWO Cordova plugin for Cordova and iOS

## Installation
In order for the plugin to work properly with a cordova project, you have to also install `cordova-plugin-cocoapod-support`. 
This makes sure, that the the right pods are installed for iOS.

### Additional steps for iOS

Add the VWOAppKey entry to your plist via a hook. See also http://stackoverflow.com/questions/22769111/add-entry-to-ios-plist-file-via-cordova-config-xml

For Android, that's not neccessary. Instead, you pass the app key via the `Vwo.start()`

## Usage

After adding the plugin, there is a VWO object on the window.
Available methods are:

```
window.Vwo.start(String appKey, func successCallback, func errorCallback)
window.Vwo.getAllObject(func successCallback(json))
window.Vwo.markConversionForGoal(String goalIdentifier [, Double value])
```
