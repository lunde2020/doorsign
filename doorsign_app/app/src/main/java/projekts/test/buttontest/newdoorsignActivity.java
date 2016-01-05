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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Lundesan on 08.11.2015.
 */




public class newdoorsignActivity extends Activity {

    private TextView newdoorsign_name;
    private TextView newdoorsign_roomnumber;
    private TextView newdoorsign_telephonenumber;
    private TextView newdoorsign_emailadress;

    //////////////////////////////////////kopiert
    private static final int SELECT_PICTURE = 1;
    private ImageButton enterbutton;
    private Button btnClose;
    private Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
    private ImageView imageView;
    private TypedValue displaywidthTV = new TypedValue();
    private TypedValue displayheightTV = new TypedValue();
//////////////////////////////////////kopiert

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdoorsign);
        newdoorsign_name = (TextView) findViewById(R.id.newdoorsign_name);
        newdoorsign_roomnumber = (TextView) findViewById(R.id.newdoorsign_roomnumber);
        newdoorsign_telephonenumber = (TextView) findViewById(R.id.newdoorsign_telephonenumber);
        newdoorsign_emailadress = (TextView) findViewById(R.id.newdoorsign_emailadress);

        //////////////////////////////////////kopiert

        final Dialog nagDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        enterbutton = (ImageButton) findViewById(R.id.enterbutton);


        getResources().getValue(R.dimen.display_width, displaywidthTV, true);
        final float displaywidth = displaywidthTV.getFloat();
        getResources().getValue(R.dimen.display_height, displayheightTV, true);
        final float displayheight = displayheightTV.getFloat();

        ////////////////////////////////////     kopiert ende




        SharedPreferences app_preferences =
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

        enterbutton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {


                                                    Bitmap.Config conf = Bitmap.Config.ARGB_4444;
                                                    Bitmap alteredBitmap = Bitmap.createBitmap(Math.round(displaywidth), Math.round(displayheight), conf);

                                                    float horizontalline1 = 50;
                                                    float horizontalline2 = 100;     // hier wird Rechteck drauf bezogen

                                                    float rechteckbreite = 250;



                                                    Paint drawline = new Paint();
                                                    drawline.setColor(Color.BLACK);
                                                    drawline.setStrokeWidth(10);


                                                    int textroomnumberheight = 80;
                                                    Paint textroomnumber = new Paint();
                                                    textroomnumber.setColor(Color.WHITE);
                                                    textroomnumber.setTextSize(textroomnumberheight);


                                                    int textnameheight = 30;
                                                    Paint textname = new Paint();
                                                    textname.setColor(Color.BLACK);
                                                    textname.setTextSize(textnameheight);
                                                    Canvas canvas = new Canvas(alteredBitmap);


                                                    //    public void drawLine(float startX, float startY, float stopX, float stopY,

                                                    canvas.drawLine(0, horizontalline1, displaywidth - rechteckbreite, horizontalline1, drawline);
                                                    canvas.drawLine(0, horizontalline2, displaywidth, horizontalline2, drawline);


                                                    //Kasten aussen rum
                                                    canvas.drawLine(0, displayheight, displaywidth, displayheight, drawline);
                                                    canvas.drawLine(0, 0, displaywidth, 0, drawline);
                                                    canvas.drawLine(displaywidth, 0, displaywidth, displayheight, drawline);
                                                    canvas.drawLine(0, 0, 0, displayheight, drawline);

                                                    // public void drawRect(float left, float top, float right, float bottom, @NonNull Paint paint)

                                                    canvas.drawRect(displaywidth - rechteckbreite, 0, displaywidth, horizontalline2, drawline);
                                                    canvas.drawText(roomnumberfinal, displaywidth - rechteckbreite + 10, horizontalline2 - (horizontalline2 - textroomnumberheight) / 2, textroomnumber);
                                                    canvas.drawText(namefinal, 10, horizontalline1 - (horizontalline1 - textnameheight) / 2, textname);
                                                    canvas.drawText(telephnoenumberfinal, 10, horizontalline2- ((horizontalline2 - horizontalline1 - textnameheight) / 2), textname);



                                                    String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                                                            "/Doorsign";
                                                    File dir = new File(file_path);

                                                    if (!dir.exists())
                                                        dir.mkdirs();

                                                    File file = new File(dir, "doorsign1.PNG");

                                                    FileOutputStream fOut = null;


                                                    try {
                                                        fOut = new FileOutputStream(file);
                                                        alteredBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                                        fOut.flush();
                                                        fOut.close();
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                    //einlesen von Bild
                                                    BitmapFactory.Options options = new BitmapFactory.Options();
                                                    options.inPreferredConfig = Bitmap.Config.ARGB_4444;

                                                    String inputfile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Doorsign/doorsign1.PNG";
                                                    Bitmap bitmap = BitmapFactory.decodeFile(inputfile, options);


                                                    //DIalog anzeigen
                                                    setContentView(R.layout.preview_image);
                                                    nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                    nagDialog.setCancelable(false);
                                                    nagDialog.setContentView(R.layout.preview_image);
                                                    Button btnClose = (Button) nagDialog.findViewById(R.id.btnIvClose);
                                                    ImageView ivPreview = (ImageView) nagDialog.findViewById(R.id.iv_preview_image);
                                                    ivPreview.setImageBitmap(bitmap);



                                                    nagDialog.show();

                                                    btnClose.setOnClickListener(new View.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(View v) {


                                                                                                Intent i = new Intent(getApplicationContext(), newdoorsignActivity.class);
                                                                                                startActivity(i);


                                                                                            }
                                                                                        }
                                                    );

                                                }
                                            }
        );


    }

}




