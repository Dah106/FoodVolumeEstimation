package seniordesign.foodvolume;


import android.graphics.Bitmap;
import android.net.Uri;

public class mealRecordItem {

    private int imageId;
    private Bitmap image;
    private Uri imageUri;


    public mealRecordItem(int imageId, Bitmap image, Uri imageUri) {

        this.imageId = imageId;
        this.image = image;
        this.imageUri = imageUri;
    }

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Bitmap getImage() {
        return image;
    }
    public void setImage(int imageId) {
        this.image = image;
    }

    public Uri getImageUri(){return imageUri;}
    public void setImageUri(Uri imageUri){this.imageUri = imageUri;}

}
