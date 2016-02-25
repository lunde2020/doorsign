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
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Lundesan on 08.11.2015.
 */




public class newconfigActivity extends Activity {

    private EditText newconfig_ipadress;
    private EditText newconfig_pin;
    private EditText newconfig_mac;
    private TextView newconfig_URL;





    private ImageButton enterbuttonconfig;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newconfig);


        newconfig_ipadress = (EditText) findViewById(R.id.editText2);
        newconfig_pin = (EditText) findViewById(R.id.editText3);
        newconfig_mac = (EditText) findViewById(R.id.editText4);
        newconfig_URL = (TextView) findViewById(R.id.editText5);


        //final Dialog nagDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        enterbuttonconfig = (ImageButton) findViewById(R.id.enterbuttonconfig);







        final SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        final String ipadressfinal  = app_preferences.getString("ipadress", "10.0.2.2");
        newconfig_ipadress.setText(ipadressfinal);



       final String pinfinal  = app_preferences.getString("pin", "0000");
        newconfig_pin.setText(pinfinal);


        final String macfinal  = app_preferences.getString("mac", "18:fe:34:d6:1c:7e");
        newconfig_mac.setText(macfinal);

        final String URLfinal  = app_preferences.getString("URL", "");
        newconfig_URL.setText(URLfinal);







        enterbuttonconfig.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {





                                                    SharedPreferences.Editor SP_ipadress = app_preferences.edit();
                                                    SP_ipadress.putString("ipadress", newconfig_ipadress.getText().toString());
                                                    SP_ipadress.commit();


                                                    SharedPreferences.Editor SP_pin = app_preferences.edit();
                                                    SP_pin.putString("pin", newconfig_pin.getText().toString());
                                                    SP_pin.commit();

                                                    SharedPreferences.Editor SP_mac = app_preferences.edit();
                                                    SP_mac.putString("mac", newconfig_mac.getText().toString());
                                                    SP_mac.commit();




                                                }
                                            }
        );


    }

}




