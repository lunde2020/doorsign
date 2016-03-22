package projekts.test.buttontest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import android.app.Activity;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Lundesan on 08.11.2015.
 */




public class newdoorsignActivity extends Activity {

    private EditText newdoorsign_name;
    private EditText newdoorsign_roomnumber;
    private EditText newdoorsign_telephonenumber;
    private EditText newdoorsign_emailadress;
    private EditText newdoorsign_status;
    private EditText newdoorsign_roll;
    private EditText newdoorsign_times;
    private EditText newdoorsign_information;

    private Vibrator myVib;

    //////////////////////////////////////kopiert
    private static final int SELECT_PICTURE = 1;
    private ImageButton enterbutton;
    private Button btnClose;
    private Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
    private ImageView imageView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdoorsign);


        newdoorsign_name = (EditText) findViewById(R.id.editText2);
        newdoorsign_roomnumber = (EditText) findViewById(R.id.editText5);
        newdoorsign_telephonenumber = (EditText) findViewById(R.id.editText3);
        newdoorsign_emailadress = (EditText) findViewById(R.id.editText4);
        newdoorsign_times = (EditText) findViewById(R.id.editText6);
        newdoorsign_roll = (EditText) findViewById(R.id.editText7);
        newdoorsign_status = (EditText) findViewById(R.id.editText8);
        newdoorsign_information = (EditText) findViewById(R.id.editText9);


        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        final Dialog nagDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        enterbutton = (ImageButton) findViewById(R.id.enterbutton);



        final SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(this);



        // Get String from Shared Preferences
        final String namefinal  = app_preferences.getString("name", "...");
        newdoorsign_name.setText(namefinal);

        final String roomnumberfinal  = app_preferences.getString("roomnumber", "...");
        newdoorsign_roomnumber.setText(roomnumberfinal);

        final String telephnoenumberfinal  = app_preferences.getString("telephonenumber", "...");
        newdoorsign_telephonenumber.setText(telephnoenumberfinal);

        final String emailadressfinal  = app_preferences.getString("emailadress", "...");
        newdoorsign_emailadress.setText(emailadressfinal);

        final String statusfinal  = app_preferences.getString("status", "...");
        newdoorsign_status.setText(statusfinal);

        final String roll  = app_preferences.getString("roll", "...");
        newdoorsign_roll.setText(roll);

        final String times  = app_preferences.getString("times", "...");
        newdoorsign_times.setText(times);

        final String informationfinal  = app_preferences.getString("information", "...");
        newdoorsign_information.setText(informationfinal);





        enterbutton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {



                                                    myVib.vibrate(50);


                                                    SharedPreferences.Editor SP_name = app_preferences.edit();
                                                    SP_name.putString("name", newdoorsign_name.getText().toString());
                                                    SP_name.commit();

                                                    SharedPreferences.Editor SP_telephonenumber = app_preferences.edit();
                                                    SP_telephonenumber.putString("telephonenumber", newdoorsign_telephonenumber.getText().toString());
                                                    SP_telephonenumber.commit();

                                                    SharedPreferences.Editor SP_emailadress = app_preferences.edit();
                                                    SP_emailadress.putString("emailadress", newdoorsign_emailadress.getText().toString());
                                                    SP_emailadress.commit();

                                                    SharedPreferences.Editor SP_roomnumber = app_preferences.edit();
                                                    SP_roomnumber.putString("roomnumber", newdoorsign_roomnumber.getText().toString());
                                                    SP_roomnumber.commit();

                                                    SharedPreferences.Editor SP_times = app_preferences.edit();
                                                    SP_times.putString("times", newdoorsign_times.getText().toString());
                                                    SP_times.commit();

                                                    SharedPreferences.Editor SP_roll = app_preferences.edit();
                                                    SP_roll.putString("roll", newdoorsign_roll.getText().toString());
                                                    SP_roll.commit();


                                                    SharedPreferences.Editor SP_status = app_preferences.edit();
                                                    SP_status.putString("status", newdoorsign_status.getText().toString());
                                                    SP_status.commit();

                                                    SharedPreferences.Editor SP_information = app_preferences.edit();
                                                    SP_information.putString("information", newdoorsign_information.getText().toString());
                                                    SP_information.commit();




                                                    Intent i = new Intent(getApplicationContext(), Main_Start.class);
                                                    startActivity(i);




                                                }
                                            }
        );


    }

}




