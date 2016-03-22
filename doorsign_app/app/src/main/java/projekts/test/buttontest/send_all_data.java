//package projekts.test.buttontest;
//
//import android.content.BroadcastReceiver;
//import android.content.ComponentName;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.IntentSender;
//import android.content.ServiceConnection;
//import android.content.SharedPreferences;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.content.res.AssetManager;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.database.DatabaseErrorHandler;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.UserHandle;
//import android.preference.PreferenceManager;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.Display;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * Created by Lundesan on 24.02.2016.
// */
//public class send_all_data {
//
//
//
//
//     public send_all_data() {
//
//         Thread networkConnectionThread;
//
//
//         networkConnectionThread = new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 try {
//                     //URL url = new URL("http://10.0.2.2/pixelserver/SaveConfig.php");
//                     URL url = new URL("http://192.168.178.81/pixelserver/SaveConfig.php");
//                     HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                     urlConnection.setRequestMethod("POST");
//
//                     OutputStreamWriter writer = new OutputStreamWriter(
//                             urlConnection.getOutputStream());
//
//                     writer.write("json="+send_all_data.this.getConfig().toString());
//                     writer.close();
//                     if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                         Log.d("URL-CONNECTION", "HTTP REQUEST OK!");
//                     } else {
//                         Log.d("URL-CONNECTION", "HTTP REQUEST ERROR!");
//                     }
//                 } catch (MalformedURLException e) {
//                     e.printStackTrace();
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//         });
//         networkConnectionThread.start();
//     }
//
//
//
//
//    private JSONObject getConfig() {
//        SharedPreferences app_preferences;
//      // app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        JSONObject configObject = new JSONObject();
//        try {
////         //   configObject.put("mac", app_preferences.getString("mac", "18:fe:34:d6:1c:7e"));
////            configObject.put("pin", app_preferences.getString("pin", "1234"));
////            configObject.put("name", app_preferences.getString("name", "..."));
////            configObject.put("roomnumber", app_preferences.getString("roomnumber", "..."));
////            configObject.put("telephonenumber", app_preferences.getString("telephonenumber", "..."));
////            configObject.put("emailadress", app_preferences.getString("emailadress", "..."));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return configObject;
//    }
//
//
//
//
//}
//
//
//
//
//
//
//
