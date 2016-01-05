package projekts.test.buttontest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShowPictureActivity extends AppCompatActivity {


    private TypedValue displaywidthTV = new TypedValue();
    private TypedValue displayheightTV = new TypedValue();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_image);


        getResources().getValue(R.dimen.display_width, displaywidthTV, true);
        final float displaywidth = displaywidthTV.getFloat();

        getResources().getValue(R.dimen.display_width, displayheightTV, true);
        final float displayheight = displaywidthTV.getFloat();


        final Dialog nagDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setCancelable(false);
        nagDialog.setContentView(R.layout.preview_image);
        Button btnClose = (Button)nagDialog.findViewById(R.id.btnIvClose);
        ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;


        String file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Doorsign/doorsign1.PNG";


        Bitmap bitmap = BitmapFactory.decodeFile(file, options);
        ivPreview.setImageBitmap(bitmap);


        nagDialog.show();



        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                nagDialog.dismiss();
            }
        });




    }

}











