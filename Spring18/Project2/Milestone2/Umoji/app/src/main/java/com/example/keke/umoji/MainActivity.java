package com.example.keke.umoji;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_STORAGE_PERMISSION = 1;

    private static final String FILE_PROVIDER_AUTHORITY = "com.example.android.fileprovider";

    private ImageView mImageView;
    private Button mUmojiButton;
    private FloatingActionButton mShareFab;
    private FloatingActionButton mSaveFab;
    private FloatingActionButton mClearFab;
    private TextView mTitleTextView;
    private String mTempPhotoPath;
    private Bitmap mResultsBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind the views
        mImageView = findViewById(R.id.image_view);
        mUmojiButton = findViewById(R.id.umoji_button);
        mShareFab = findViewById(R.id.share_button);
        mSaveFab = findViewById(R.id.save_button);
        mClearFab= findViewById(R.id.clear_button);
        mTitleTextView = findViewById(R.id.title_text_view);
    }

    /**
     * Onclick method for "Get Your Emoji!" Button, launches the camera app
     */
    public void getEmoji(View view){
        //Check for the external storage permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //If you don't have permission, make a request
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_STORAGE_PERMISSION);
        } else {
            //Launch the camera if the permission exists
            launchCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
                                      int[] grantResults) {
        //Called when request permission to read and write to external storage
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION: {
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //If your get permission, launch the camera
                    launchCamera();
                }else{
                    //if you don't get permission, show a Toast
                    Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    /**
     * Creates a temporary image file and captures a picture to store in it
     */
    private void launchCamera() {

        //Create the capture image intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Ensure that there's a camera activity to handle the intent
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //Create the temporary File where the photo should go
            File photoFile = null;
            try {
                photoFile = BitmapUtils.createTempImageFile(this);
            }catch (IOException ex){
                //Error occurred while creating the File
                ex.printStackTrace();
            }
            //Continue if the File was successfully created
            if(photoFile != null){

                //Get the path of the temporary file
                mTempPhotoPath = photoFile.getAbsolutePath();

                //Get the content URI for the image file
                Uri photoURI = FileProvider.getUriForFile(this, FILE_PROVIDER_AUTHORITY, photoFile);

                //Add the URI so the camera can store the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                //Launch the camera activity
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        //If the image capture activity was called and was successful
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Process the image and set it to the TextView
            processAndSetImage();
        } else {
            //Delete the temporary image file
            BitmapUtils.deleteImageFile(this, mTempPhotoPath);
        }
    }

    /**
     * Method for processing the captured image and setting it to the TextView
     */

    private void processAndSetImage(){
       //Toggle Visibility of the views
        mUmojiButton.setVisibility(View.GONE);
        mTitleTextView.setVisibility(View.GONE);
        mSaveFab.setVisibility(View.VISIBLE);
        mSaveFab.setVisibility(View.VISIBLE);
        mClearFab.setVisibility(View.VISIBLE);

        //Reseample the saved image to fit the ImageView
        mResultsBitmap = BitmapUtils.resamplePic(this, mTempPhotoPath);
}

/**
 * OnClick method for the save button
 */

public void saveMe(View view){
    //Delete the temporary image file
    BitmapUtils.deleteImageFile(this, mTempPhotoPath);

    //Save the image
    BitmapUtils.saveImageFile(this, mResultsBitmap);
}

    /**
     * OnClick method for the share button
     */

    public void shareMe(View view){
        //Delete the temporary image file
        BitmapUtils.deleteImageFile(this, mTempPhotoPath);

        //Save the image
        BitmapUtils.saveImageFile(this, mResultsBitmap);

        //Share the image
        BitmapUtils.shareImageFile(this, mTempPhotoPath);
    }

/**
 * OnClick for the clear Button, resets the app to original state
 */

public void clearImage(View view) {
    //Clear the image and toggle the view visibility
    mImageView.setImageResource(0);
    mUmojiButton.setVisibility(View.VISIBLE);
    mTitleTextView.setVisibility(View.VISIBLE);
    mShareFab.setVisibility(View.GONE);
    mSaveFab.setVisibility(View.GONE);
    mClearFab.setVisibility(View.GONE);

    //Delete the temporary image file
    BitmapUtils.deleteImageFile(this, mTempPhotoPath);

    }
}
