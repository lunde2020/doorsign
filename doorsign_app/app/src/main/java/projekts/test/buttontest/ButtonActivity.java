package projekts.test.buttontest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import android.graphics.Color;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class ButtonActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private Button but1_changeColorButton;
    private Button but2_enterButton;
    private Button btnClose;
    private Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
    private ImageView imageView;
    private TextView tView1_enteringstuff;
    private EditText Et1_Name;
    private EditText Et2_Raumnummer;

    private TypedValue displaywidthTV = new TypedValue();
    private TypedValue displayheightTV = new TypedValue();










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        final Dialog nagDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);




        tView1_enteringstuff = (TextView) findViewById(R.id.tView1);
        but2_enterButton = (Button) findViewById(R.id.but2);
        Et1_Name = (EditText) findViewById(R.id.et1);

        getResources().getValue(R.dimen.display_width, displaywidthTV, true);
        final float displaywidth = displaywidthTV.getFloat();

        getResources().getValue(R.dimen.display_width, displayheightTV, true);
        final float displayheight = displaywidthTV.getFloat();





        but2_enterButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String name = Et1_Name.getText().toString();
                                                    String a = " Hallo";
                                                    String b = name + a;
                                                    tView1_enteringstuff.setText(b);


                                                    Bitmap.Config conf = Bitmap.Config.ARGB_4444;
                                                    Bitmap alteredBitmap = Bitmap.createBitmap(Math.round(displaywidth), Math.round(displayheight), conf);

                                                    float horizontalline1 = 50;
                                                    float horizontalline2 = 100;     // hier wird Rechteck drauf bezogen

                                                    float rechteckbreite = 250;
                                                    int textheight = 80;


                                                    Paint drawline = new Paint();
                                                    drawline.setColor(Color.BLACK);
                                                    drawline.setStrokeWidth(10);

                                                    Paint textroomnumber = new Paint();
                                                    textroomnumber.setColor(Color.WHITE);
                                                    textroomnumber.setTextSize(textheight);


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

                                                    canvas.drawText("F203", displaywidth - rechteckbreite + 10, horizontalline2 - (horizontalline2 - textheight) / 2, textroomnumber);


                                                    imageView.setImageBitmap(alteredBitmap);


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
                                                    Button btnClose = (Button)nagDialog.findViewById(R.id.btnIvClose);
                                                    ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
                                                    ivPreview.setImageBitmap(bitmap);


                                                    nagDialog.show();

                                                }
                                            }
        );




        imageView = (ImageView) findViewById(R.id.imageView);
        photoPickerIntent.setType("image/*");


        // Button laden
        but1_changeColorButton = (Button) findViewById(R.id.but1);


        but1_changeColorButton.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), SELECT_PICTURE);
                                                      }
                                                  }
        );


    }

    public void onBackPressed(Dialog nagDialogfunc)
    {
        nagDialogfunc = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        ButtonActivity.super.onBackPressed();
        setContentView(R.layout.activity_button);
        nagDialogfunc.dismiss();
//


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                try {
                    Bitmap imageBitMap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                    imageView.setImageBitmap(imageBitMap);
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }

                Log.d("MeineApp", selectedImageUri.getPath());
            }
        }

    }





}

