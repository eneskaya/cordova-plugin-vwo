package de.aboutyou.vwocordova;

import android.app.Application;

import com.vwo.mobile.Vwo;
import com.vwo.mobile.events.VwoStatusListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class VWOCordova extends CordovaPlugin {

  Application _app;
  boolean _vwoStarted;
  String TAG = "VWOCordovaPlugin";

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView)
  {
    super.initialize(cordova, webView);
    this._app = cordova.getActivity().getApplication();
  }

  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

    if (action.equals("start")) {
      String appKey = (String) args.get(0);

      Vwo.startAsync(appKey, this._app, new VwoStatusListener() {
        @Override
        public void onVwoLoaded() {
          callbackContext.success();
          _vwoStarted = true;
        }

        @Override
        public void onVwoLoadFailure() {
          callbackContext.error("VWO not started yet. Please call the start method with the application key.");
          _vwoStarted = false;
        }
      });

      return true;
    } else if (action.equals("getAllObject")) {

      if (!this._vwoStarted) {
        callbackContext.error("VWO not started");
        return true;
      }

      callbackContext.success(Vwo.getAllObject().toString());

      return true;
    } else if (action.equals("markConversionForGoal")) {

      if (!this._vwoStarted) {
        callbackContext.error("VWO not started");
        return true;
      }

      String goalIdentifier = args.getString(0);

      // Check if a second argument is present, which is the value
      if (args.isNull(1)) {
        Vwo.markConversionForGoal(goalIdentifier);
      } else {
        Vwo.markConversionForGoal(goalIdentifier, args.getDouble(1));
      }

      callbackContext.success();
    }

    return false;  // Returning false results in a "MethodNotFound" error.
  }

}
