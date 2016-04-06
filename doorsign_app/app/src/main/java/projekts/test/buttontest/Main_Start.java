package projekts.test.buttontest;

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
import android.os.Vibrator;
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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by Lundesan on 03.11.2015.
 */


public class Main_Start extends Activity {

    private ImageButton imageButton;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private ImageButton imageButton6;
    private TextView homescreen_name;
    private ImageView contactphoto;
    final int RQS_PICKCONTACT = 1;
    private Vibrator myVib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton6 = (ImageButton) findViewById(R.id.imageButton6);
        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        homescreen_name = (TextView) findViewById(R.id.homescreen_name);
        contactphoto = (ImageView) findViewById(R.id.imageView3);

        // final send_all_data senddata;


        final SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        // Get String from Shared Preferences
        String namefinal = app_preferences.getString("name", "...");
        homescreen_name.setText(namefinal);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        String inputfile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Doorsign/Contactpictures/contactXY.PNG";
        Bitmap bitmap = BitmapFactory.decodeFile(inputfile, options);
        contactphoto.setImageBitmap(bitmap);


        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);


        imageButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               myVib.vibrate(50);
                                               Intent i = new Intent(getApplicationContext(), newdoorsignActivity.class);
                                               startActivity(i);
                                           }
                                       }


        );

        imageButton2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                myVib.vibrate(50);
                                                final Uri uriContact = ContactsContract.Contacts.CONTENT_URI;
                                                Intent intentPickContact = new Intent(Intent.ACTION_PICK, uriContact);
                                                startActivityForResult(intentPickContact, RQS_PICKCONTACT);

                                            }
                                        }
        );

// FAQ
        imageButton4.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                myVib.vibrate(50);
                                                Intent i = new Intent(getApplicationContext(), faqActivity.class);
                                                startActivity(i);

                                            }
                                        }
        );


        imageButton3.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
//                                               Thread networkConnectionThread = new Thread(new Runnable() {
//                                                   @Override
//                                                   public void run() {
//                                                       try {
//                                                           // URL url = new URL("http://10.0.2.2/pixelserver/SaveConfig.php");
//
//                                                           URL url = new URL("http://10.0.2.2/pixelserver/GetMacs.php");
//                                                           HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                                                           urlConnection.setRequestMethod("GET");
//
//                                                           if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                                                               Log.d("URL-CONNECTION", "HTTP REQUEST OK!");
//
//                                                               InputStreamReader in = new InputStreamReader((InputStream) urlConnection.getContent());
//                                                               BufferedReader buff = new BufferedReader(in);
//
//                                                               String content = "";
//                                                               do {
//                                                                   content += buff.readLine();
//                                                               } while (content != null);
//
//                                                               Log.d("CONTENT", content);
//                                                           } else {
//                                                               Log.d("URL-CONNECTION", "HTTP REQUEST ERROR!");
//                                                           }
//
//                                                           urlConnection.disconnect();
//
//                                                       } catch (MalformedURLException e) {
//                                                           e.printStackTrace();
//                                                       } catch (IOException e) {
//                                                           e.printStackTrace();
//                                                       }
//                                                   }
//                                               });
//
//                                               networkConnectionThread.start();
                                                myVib.vibrate(50);

                                                Intent i = new Intent(getApplicationContext(), newconfigActivity.class);
                                                startActivity(i);
                                            }
                                        }
        );


        imageButton6.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {


                                                // Get String from Shared Preferences
                                                final String ipadress = app_preferences.getString("ipadress", "...");

                                                final String makeURL = ("http://" + ipadress + "/pixelserver/SaveConfig.php");
                                                // "http://192.168.178.81/pixelserver/SaveConfig.php"


                                                SharedPreferences.Editor SP_URL = app_preferences.edit();
                                                SP_URL.putString("URL", makeURL.toString());
                                                SP_URL.commit();


                                                Thread networkConnectionThread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            // URL url = new URL("http://10.0.2.2/pixelserver/SaveConfig.php");

                                                            URL url = new URL(makeURL);
                                                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                                            urlConnection.setRequestMethod("POST");

                                                            OutputStreamWriter writer = new OutputStreamWriter(
                                                                    urlConnection.getOutputStream());

                                                            writer.write("json=" + Main_Start.this.getConfig().toString());
                                                            writer.close();
                                                            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                                                Log.d("URL-CONNECTION", "HTTP REQUEST OK!");

                                                                SharedPreferences.Editor SP_delete = app_preferences.edit();
                                                                SP_delete.putBoolean("delete", false);
                                                                SP_delete.commit();

                                                                myVib.vibrate(50);
                                                            } else {
                                                                Log.d("URL-CONNECTION", "HTTP REQUEST ERROR!");
                                                            }
                                                        } catch (MalformedURLException e) {
                                                            e.printStackTrace();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });

                                                networkConnectionThread.start();


                                            }
                                        }
        );

    }

    private JSONObject getConfig() {
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        JSONObject configObject = new JSONObject();
        try {
            configObject.put("mac", app_preferences.getString("mac", "18:fe:34:d6:1c:7e"));
            configObject.put("pin", app_preferences.getString("pin", "1234"));
            configObject.put("name", app_preferences.getString("name", "..."));
            configObject.put("roomnumber", app_preferences.getString("roomnumber", "..."));
            configObject.put("telephonenumber", app_preferences.getString("telephonenumber", "..."));
            configObject.put("emailadress", app_preferences.getString("emailadress", "..."));
            configObject.put("status", app_preferences.getString("status", "..."));
            configObject.put("roll", app_preferences.getString("roll", "..."));
            configObject.put("information", app_preferences.getString("information", "..."));
            configObject.put("times", app_preferences.getString("times", "..."));
            configObject.put("template", app_preferences.getString("template", "..."));
            configObject.put("mode", app_preferences.getString("mode", "..."));
            configObject.put("delete", app_preferences.getBoolean("delete", false));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return configObject;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


// TODO Auto-generated method stub
        if (resultCode == RESULT_OK) if (requestCode == RQS_PICKCONTACT) {
            Uri returnUri = data.getData();                         // URI auf Kontakt
            Cursor cursor = getContentResolver().query(returnUri, null, null, null, null);


            //savevursor to file
//                SharedPreferences app_preferences =
//                        PreferenceManager.getDefaultSharedPreferences(this);
//
//                SharedPreferences.Editor editor = app_preferences.edit();
//                editor.put("ID_Cursor", cursor);
//                editor.commit(); // Very important


            if (cursor.moveToNext()) {
                int columnIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactID = cursor.getString(columnIndex_ID);
                long longcontactID = Long.parseLong(contactID);


                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                //ImageView contactphoto2=(ImageView)findViewById(R.id.imageView3);


                Bitmap contactphoto1 = BitmapFactory.decodeStream(openPhoto(longcontactID));
                contactphoto.setImageBitmap(contactphoto1);


                //             Bitmap contactphoto1 = BitmapFactory.decodeStream(openDisplayPhoto(longcontactID));
                //           contactphoto.setImageBitmap(contactphoto1);


                String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/Doorsign/Contactpictures";
                File dir = new File(file_path);
                if (!dir.exists())
                    dir.mkdirs();

                File file = new File(dir, "contactXY.PNG");
                FileOutputStream fOut = null;
                try {
                    fOut = new FileOutputStream(file);
                    contactphoto1.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                int columnIndex_HASPHONENUMBER = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                String stringHasPhoneNumber = cursor.getString(columnIndex_HASPHONENUMBER);

                String telephonenumber = "";


                if (stringHasPhoneNumber.equalsIgnoreCase("1")) {
                    Cursor cursorNum = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID,
                            null,
                            null);

                    //Get the first phone number
                    if (cursorNum.moveToNext()) {
                        int columnIndex_number = cursorNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        String stringNumber = cursorNum.getString(columnIndex_number);
                        telephonenumber = stringNumber;
                    }

                    String emailadress = "";

                    Cursor cursorMail = getContentResolver().query(


                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{contactID}, null);
                    while (cursorMail.moveToNext()) {
                        // This would allow you get several email addresses
                        // if the email addresses were stored in an array


                        String stringEmail = cursorMail.getString(
                                cursorMail.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        emailadress = stringEmail;

                        String emailType = cursorMail.getString(
                                cursorMail.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                    }
                    cursorMail.close();

                    String tempinformation = "";


                    String noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                    String[] noteWhereParams = new String[]{contactID,
                            ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE};
                    Cursor noteCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
                    if (noteCur.moveToFirst()) {
                        String stringNote = noteCur.getString(noteCur.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
                        tempinformation = stringNote;

                    }


                    noteCur.close();

                    //Adresse auslesen

                    String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                    String[] addrWhereParams = new String[]{contactID,
                            ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};


                    Cursor cursorAdress = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                            null, addrWhere, addrWhereParams, null);

                    String roomnumber = "";

                    String times = "";
                    String roll = "";

                    while (cursorAdress.moveToNext()) {
                        String poBox = cursorAdress.getString(
                                cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));

                        roomnumber = poBox;

                        String street = cursorAdress.getString(
                                cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                        times = street;
                        String city = cursorAdress.getString(
                                cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                        String state = cursorAdress.getString(
                                cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                        String postalCode = cursorAdress.getString(
                                cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                        String country = cursorAdress.getString(
                                cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));

                        roll = country;
                        String type = cursorAdress.getString(
                                cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));

                    }
                    cursorAdress.close();


                    SharedPreferences app_preferences =
                            PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor SP_name = app_preferences.edit();
                    SP_name.putString("name", name);
                    SP_name.commit();


                    homescreen_name.setText(name);


//                    String roll = "";
//                    String times = "";
//                    String information = "";
//
//                    int index_roll = tempinformation.indexOf("#roll=");
//                    int index_times = tempinformation.indexOf("#times=");
//                    int index_information = tempinformation.indexOf("#information=");
//
//
//                    if (index_roll >= 0)
//                            {
//                                int index_roll_s = index_roll + "#roll=".length();
//                                roll = tempinformation.substring(index_roll_s, index_times);
//                            }
//                    else
//                            {
//                                roll = "nicht angegeben";
//                            }
//
//                    if (index_times >= 0)
//                    {
//                        int index_times_s = index_times + "#times=".length();
//                        times = tempinformation.substring(index_times_s, index_information);
//                    }
//                    else
//                    {
//                        times = "nicht angegeben";
//                    }
//
//                    if (index_information >= 0)
//                    {
//                        int index_information_s = index_information + "#information=".length();
//                        information = tempinformation.substring(index_information_s, tempinformation.length());
//                    }
//                    else
//                    {
//                        information = "nicht angegeben";
//                    }
//
//


                    SharedPreferences.Editor SP_information = app_preferences.edit();
                    SP_information.putString("information", tempinformation);
                    SP_information.commit();

                    SharedPreferences.Editor SP_raumnummer = app_preferences.edit();
                    SP_name.putString("roomnumber", roomnumber);
                    SP_name.commit();

                    SharedPreferences.Editor SP_telephonenumber = app_preferences.edit();
                    SP_name.putString("telephonenumber", telephonenumber);
                    SP_name.commit();


                    SharedPreferences.Editor SP_emailadress = app_preferences.edit();
                    SP_name.putString("emailadress", emailadress);
                    SP_name.commit();

                    SharedPreferences.Editor SP_times = app_preferences.edit();
                    SP_times.putString("times", times);
                    SP_times.commit();

                    SharedPreferences.Editor SP_roll = app_preferences.edit();
                    SP_roll.putString("roll", roll);
                    SP_roll.commit();


                    SharedPreferences.Editor SP_status = app_preferences.edit();
                    SP_status.putString("status", "Status / Notiz");
                    SP_status.commit();


                } else {
                    Toast.makeText(getApplicationContext(), "NO data!", Toast.LENGTH_LONG).show();
                }


            }
        }
    }


    //Kontaktphoto auslesen Klasse
    public static Bitmap loadContactPhoto(ContentResolver cr, long id) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);

        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
        if (input == null) {
            return null;
        }
        return BitmapFactory.decodeStream(input);
    }

    public InputStream openDisplayPhoto(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
        try {
            AssetFileDescriptor fd =
                    getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");

            return fd.createInputStream();
        } catch (IOException e) {
            return null;
        }
    }


    public InputStream openPhoto(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = getContentResolver().query(photoUri,
                new String[]{ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                byte[] data = cursor.getBlob(0);
                if (data != null) {
                    return new ByteArrayInputStream(data);
                }
            }
        } finally {
            cursor.close();
        }
        return null;
    }


}







