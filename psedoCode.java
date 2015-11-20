//CaptureRequest created
//CameraDeivce created, mCameraID assigned, mStateCallback assigned
//CameraManager created
//CameraCaptureSession created

CaptureRequest.Builder captureBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
setUpCameraOutputs(width,height);

protected void setUpCameraOutputs(int width, int height)
{	
	//Set up target surface to hold preview images
	//Attributes such as size and format is set within this function
	setupSurfaceTecture();

}

//mCameraId indicates which camera(front or back i.e)
//mStateback receives updates about the state of a cameraDevice
manager.openCamera(mCameraId, mStateCallback, null);


//Whenever surfaceTexture is available
protected void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height)
{
	openCamera(width, height);

	//Background thread is started 
	//Image saver thread is started
	startBackgroundThread();
	startImageSaverThread();
}

//Whenever surfaceTexture size changed
//i.e user rotates phone from portrait to landscape
protected void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
{
	//Recalculate the size of the preview surfaceTexture
	configureTransform(width, height);
}

//Whenever screen goes into idle state
//Or user switches to other application
//Prevent memory leak, stop threads
protected void onPause()
{
	if(background thread is running)
	stopBackgroundThread();
	if(image saver thread is running)
	stopImageSaverThread();
}

//Whenever the screen exits idel state
//Or user switches back to the application
//Resume and continue camera preview
protected void onResume()
{
	if(background thread is not running)
	startBackgroundThread();
	if(image saver thread is not running)
	startImageSaverThread();
}

//camera button pressed
takePicture()
{	
	//Put image onto the image process queue
	enqueueImage();

	//Create imageReader
	initalizeImageReader();

	//Create folder that contains the saved image
	createImageFolderIfNotExists();

	//Create image information: name, timestamp and other metadata
	createImageMetaData();

	if(ImageReader image is ready and available)
	{
		//save picture in byte array
		void save(byte[] bytes)

		//Rescan Android Gallery
		//The picture just saved is ready to be viewed and accessed through Android Gallery
		void scanGallery();
	}

	if(ImageReader image has been saved)
	{	
		//Remove the saved image from the queue
		dequeueImage();
	}
}



