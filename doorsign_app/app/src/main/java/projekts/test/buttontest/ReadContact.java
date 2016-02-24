package projekts.test.buttontest;

/**
 * Created by Lundesan on 06.11.2015.
 */


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class ReadContact extends Activity {

    Button buttonReadContact;
    TextView textPhone;
    TextView textEmail;
    TextView textStreet;
    TextView textcity;
    TextView textstate;
    TextView textpostalCode;
    TextView textpostalCountry;
    TextView textPostBox;
    TextView textNote;


    final int RQS_PICKCONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readcontact);

        buttonReadContact = (Button) findViewById(R.id.readcontact);
        textPhone = (TextView) findViewById(R.id.phone);
        textEmail = (TextView) findViewById(R.id.email);
        textPostBox = (TextView) findViewById(R.id.postbox);
        textStreet = (TextView) findViewById(R.id.street);
        textcity = (TextView) findViewById(R.id.city);
        textstate = (TextView) findViewById(R.id.state);
        textpostalCode = (TextView) findViewById(R.id.code);
        textpostalCountry = (TextView) findViewById(R.id.country);
        textNote = (TextView) findViewById(R.id.note);


        buttonReadContact.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Start activity to get contact
                final Uri uriContact = ContactsContract.Contacts.CONTENT_URI;
                Intent intentPickContact = new Intent(Intent.ACTION_PICK, uriContact);
                startActivityForResult(intentPickContact, RQS_PICKCONTACT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


// TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {
            if (requestCode == RQS_PICKCONTACT) {
                Uri returnUri = data.getData();                         // URI auf Kontakt
                Cursor cursor = getContentResolver().query(returnUri, null, null, null, null);


                if (cursor.moveToNext()) {
                    int columnIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                    String contactID = cursor.getString(columnIndex_ID);
                    long longcontactID = Long.parseLong(contactID);


                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                    //Kontaktbild auslesen


//                                       ImageView contactphoto= (ImageView)findViewById(R.id.friend);
//                    ContentResolver CRcontactphoto = getContentResolver();
//                   Bitmap contactphoto1 = loadContactPhoto(CRcontactphoto, cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                   contactphoto.setImageBitmap(contactphoto1);


                    ImageView contactphoto = (ImageView) findViewById(R.id.friend);

                    //ImageView contactphoto2=(ImageView)findViewById(R.id.imageView3);


                    Bitmap contactphoto1 = BitmapFactory.decodeStream(openPhoto(longcontactID));
                    contactphoto.setImageBitmap(contactphoto1);

                    contactphoto1 = BitmapFactory.decodeStream(openDisplayPhoto(longcontactID));
                    contactphoto.setImageBitmap(contactphoto1);

                    //contactphoto2.setImageBitmap(contactphoto1);


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
                            textPhone.setText(stringNumber);
                        }


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

                            textEmail.setText(stringEmail);

                            String emailType = cursorMail.getString(
                                    cursorMail.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                        }
                        cursorMail.close();

                        String noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                        String[] noteWhereParams = new String[]{contactID,
                                ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE};
                        Cursor noteCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
                        if (noteCur.moveToFirst()) {
                            String stringNote = noteCur.getString(noteCur.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));

                            textNote.setText(stringNote);

                        }
                        noteCur.close();


//                     String noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
//                       String[] noteWhereParams = new String[]{contactID,
//                                ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE};
//
//                       Cursor cursorNote = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
//                       if (cursorNote.moveToFirst()) {
//                            String note = cursorNote.getString(cursorNote.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
//                        }
//                        cursorNote.close();
//


                        //Adresse auslesen

                        String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                        String[] addrWhereParams = new String[]{contactID,
                                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};


                        Cursor cursorAdress = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                                null, addrWhere, addrWhereParams, null);
                        while (cursorAdress.moveToNext()) {
                            String poBox = cursorAdress.getString(
                                    cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                            String street = cursorAdress.getString(
                                    cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                            String city = cursorAdress.getString(
                                    cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                            String state = cursorAdress.getString(
                                    cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                            String postalCode = cursorAdress.getString(
                                    cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                            String country = cursorAdress.getString(
                                    cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                            String type = cursorAdress.getString(
                                    cursorAdress.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));

                            textPostBox.setText(poBox);

                            textStreet.setText(street);
                            textcity.setText(city);
                            textstate.setText(state);
                            textpostalCode.setText(postalCode);
                            textpostalCountry.setText(country);

                        }
                        cursorAdress.close();


                    } else {
                        Toast.makeText(getApplicationContext(), "NO data!", Toast.LENGTH_LONG).show();
                    }


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









