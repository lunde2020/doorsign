package projekts.test.buttontest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ButtonActivity2 extends AppCompatActivity {




    //////////////////////////////////////kopiert
    private static final int SELECT_PICTURE = 1;
    private Button but2_enterButton;
    private Button btnClose;
    private Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
    private ImageView imageView;
      private TypedValue displaywidthTV = new TypedValue();
    private TypedValue displayheightTV = new TypedValue();
//////////////////////////////////////kopiert









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

//////////////////////////////////////kopiert

        final Dialog nagDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        but2_enterButton = (Button) findViewById(R.id.enterbutton);
        getResources().getValue(R.dimen.display_width, displaywidthTV, true);
        final float displaywidth = displaywidthTV.getFloat();
        getResources().getValue(R.dimen.display_width, displayheightTV, true);
        final float displayheight = displaywidthTV.getFloat();

   ////////////////////////////////////     kopiert ende

        but2_enterButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {


                                                }
                                            }
        );






    }

    public void onBackPressed(Dialog nagDialogfunc)
    {
        nagDialogfunc = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        ButtonActivity2.super.onBackPressed();
        setContentView(R.layout.activity_button);
        nagDialogfunc.dismiss();
//


    }









}

