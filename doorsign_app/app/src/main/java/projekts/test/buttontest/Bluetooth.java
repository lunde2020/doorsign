package projekts.test.buttontest;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;
import java.io.File;
import java.util.List;

/**
 * Created by Rejo on 04.01.2016.
 */

public class Bluetooth extends AppCompatActivity {

    private Context context;
    private BluetoothAdapter btAdapter;
    private final static int BLUETOOTH_REQUEST = 1;

    public Bluetooth(String file_path, Context context)
    {
        this.context = context;
        // check if device supports Bluetooth
        check_BT_status();
        // successfully turned on Bluetooth --> file transfer
        send_file_via_BT(file_path);
        // back to MainActivity
        finish();
    }

    public void check_BT_status() {
        try {
            btAdapter = BluetoothAdapter.getDefaultAdapter();
            if (btAdapter != null) {
                if (btAdapter.isEnabled()) {
                    // device supports bluetooth and bluetooth is already enabled
                }
                else {
                    // device supports bluetooth but bluetooth is disabled
                    turn_on_BT();
                }
            }
            else {
                //device doesn't support bluetooth;
                Toast.makeText(context, "Device doesn't support Bluetooth...", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            String s = "Fatal error in check_BT_status...\n" + e;
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            thread.start();
        }

    }

    public void turn_on_BT() {
        try {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivityForResult();
        } catch (Exception e) {
            String s = "Fatal error in turn_on_BT...\n" + e;
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            thread.start();
        }
    }

    public void send_file_via_BT(String path_to_file)
    {
        try {
            File sourceFile = new File(path_to_file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(sourceFile));

            PackageManager pm = context.getPackageManager();
            List<ResolveInfo> appsList = pm.queryIntentActivities(intent, 0);

            if (appsList.size() > 0) {
                String packageName = null;
                String className = null;
                boolean found = false;

                for (ResolveInfo info : appsList) {
                    packageName = info.activityInfo.packageName;
                    // chose file_transfer via bluetooth
                    if (packageName.equals("com.android.bluetooth")) {
                        className = info.activityInfo.name;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Toast.makeText(context, "File transfer via Bluetooth not supported...", Toast.LENGTH_LONG).show();
                    thread.start();
                }
                else {
                    // file transfer via Bluetooth --> start activity
                    intent.setClassName(packageName, className);
                    context.startActivity(intent);
                }

            }
            else {
                Toast.makeText(context, "File transfer canceled...", Toast.LENGTH_LONG).show();
                thread.start();
            }
        } catch (Exception e) {
            String s = "Fatal error in send_file_via_BT...\n" + e;
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            thread.start();
        }
    }


    protected void onActivityResult(int request_code, int result_code, Intent data)
    {
        //super.onActivityResult(request_code, result_code, data);
        if (request_code == BLUETOOTH_REQUEST) {
            if (result_code == RESULT_OK) {
                Toast.makeText(context, "Bluetooth successfully turned on...", Toast.LENGTH_SHORT).show();
            }
            if (result_code == RESULT_CANCELED) {
                Toast.makeText(context, "Turn on Bluetooth canceled...", Toast.LENGTH_LONG).show();
                thread.start();
            }
        }
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(Toast.LENGTH_LONG); // As I am using LENGTH_LONG in Toast
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


} // end of class