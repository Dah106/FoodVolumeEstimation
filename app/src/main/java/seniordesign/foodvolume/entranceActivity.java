package seniordesign.foodvolume;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class entranceActivity extends Activity {

    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private ImageView mPhotoCapturedImageView;
    private String mImageFileLocation = "";

    public final static String FVCAMERA_PREVIEW_MESSAGE ="com.foodPics.fvcameraPreview.message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        mPhotoCapturedImageView = (ImageView) findViewById(R.id.capturePhotoImageView);
    }


    public void getDefaultCameraPreview(View view) {

        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile = null;
        try {

            photoFile = createImageFile();

        } catch (IOException e) {

            e.printStackTrace();
        }

        callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

        startActivityForResult(callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK)
        {
            //Toast.makeText(this, "Picture taken successfully!", Toast.LENGTH_SHORT).show();
            //Bundle extras = data.getExtras();
            //Bitmap photoCapturedBitmap= (Bitmap) extras.get("data");
            //mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);
            //Bitmap photoCapturedBitmap = BitmapFactory.decodeFile(mImageFileLocation);
            //mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);
            setReducedImageSize();
        }
    }

     File createImageFile() throws IOException{

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);
        mImageFileLocation = image.getAbsolutePath();

        return image;
    }

    private void setReducedImageSize() {

        int targetImageViewWidth = mPhotoCapturedImageView.getMaxWidth();
        int targetImageViewHeight = mPhotoCapturedImageView.getMaxHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        int cameraImageWidth = bmOptions.outWidth;
        int cameraImageHeight = bmOptions.outHeight;

        int scaleFactor = Math.min(cameraImageWidth/targetImageViewWidth, cameraImageHeight/targetImageViewHeight);
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inJustDecodeBounds = false;

        Bitmap photoReducedSizeBitmap = BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        mPhotoCapturedImageView.setImageBitmap(photoReducedSizeBitmap);
    }

    public void checkMealRecord(View view)
    {
        Toast.makeText(this, "Your meal record will be displayed shortly, features under development!", Toast.LENGTH_SHORT).show();
    }

    public void getFvCameraPreview(View view)
    {
        Intent intent = new Intent(this, fvCameraActivity.class);
        String fvCameraSampleMessage = "Here is the camera preview interface";
        intent.putExtra(FVCAMERA_PREVIEW_MESSAGE, fvCameraSampleMessage);
        startActivity(intent);
    }
}
