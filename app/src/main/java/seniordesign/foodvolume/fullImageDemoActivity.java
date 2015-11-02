package seniordesign.foodvolume;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by dah106 on 10/26/15.
 */
public class fullImageDemoActivity extends Activity {

    private final static String TAG = "fullPictureDemo";

    private ImageView fullImageDemo;

    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_picutre_demo);


        goBack = (Button)findViewById(R.id.backFromFullPictureDemo);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Quiting full picture demo...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Fetch screen height and width, to use as our max size when loading images as this
        // activity runs full screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Log.d(TAG, "Current screen width is: " + width + " height is: " + height);
        Uri imageUri = getIntent().getData();
        Bitmap bitmap;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            Log.d(TAG, "image uri is: " + imageUri);
            fullImageDemo = (ImageView)findViewById(R.id.fullImageDemo);

            fullImageDemo.setImageBitmap(Bitmap.createScaledBitmap(bitmap, width, height, false));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
