package com.example.nomoretrash.signalements;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PhotoFragment extends Fragment {

    private SignalementObject signalementObject;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    private ImageView mImageView;
    private Bitmap bitmap;
    private Button mPhotoButton;
    private  Uri image_uri;


    public PhotoFragment() {
        //on récupère l'objet signalemnt
        this.signalementObject = DescriptionFragment.getSignalementObject();
    }

    public static PhotoFragment newInstance() {
        return (new PhotoFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.photo_fragment, container, false);

        mImageView = rootView.findViewById(R.id.photo);
        mPhotoButton = rootView.findViewById(R.id.boutonPhoto);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(PhotoFragment.this.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                            || ContextCompat.checkSelfPermission(PhotoFragment.this.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //Permission non accordée, on demande de nouveau la permission
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //POP UP
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission ok, on ouvre la caméra
                        openCamera();
                    }
                }
            }
        });

        //Affichage de la photo
        if (this.signalementObject.getPhoto() != null) {
            mImageView = rootView.findViewById(R.id.photo);
            mImageView.setImageBitmap(this.signalementObject.getPhoto());
            mImageView.setRotation(90);
        }

        return rootView;

    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera");
        image_uri = this.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        // Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults ){
        switch(requestCode){
            case PERMISSION_CODE: {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this.getContext(), "Permission refusée...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== -1){
            try {
                InputStream is = getActivity().getContentResolver().openInputStream(image_uri);
                bitmap = BitmapFactory.decodeStream(is);
                signalementObject.setPhoto(bitmap);//on enregistre la photo dans l'objet signalement
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            mImageView.setImageBitmap(bitmap);//Mise à jour de la photo affichée
            mImageView.setRotation(90);
        }

    }

}