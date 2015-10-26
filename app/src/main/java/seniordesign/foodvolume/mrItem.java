package seniordesign.foodvolume;


import android.graphics.Bitmap;

public class mrItem {

    private int imageId;
    private Bitmap image;


    public mrItem(int imageId, Bitmap image) {
        this.image = image;

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


}
