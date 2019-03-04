package com.zavosh.software.dentist.dentist.Activities.MVP_SelectImages;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.dentist.dentist.Activities.MVP_HomePatient.HomePatientActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_Login.LoginActivity;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyEditText;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.R;

import java.io.File;
import java.io.IOException;

public class SelectImagesActivity extends AppCompatActivity implements Contract_SelectImages.View{

    private MyImageView image1,image2,image3,iv_opg ,iv_back;
    private Presenter_SelectImages presenter ;
    private AlertDialog show;
    private Uri imageUri;
    private int permissionCamera =1000;
    private int permissionCameraOpg =1002;
    private int permissionGallery =1001;
    private int permissionGalleryOpg =1003;
    private int PIC_CROP = 2000;
    private int PIC_CROP_OPG = 2001;
    private ContentValues values;
    private MyButton btn_send,btn_tooth,btn_opg;
    private MyEditText ev_description;

    private LinearLayout myProgressBar;
    private LoadingDots loaderDots;
    private MyButton btn_retry;
    private MyTextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_images_actvity);
        reference();
        listeners();

    }

    private void reference() {

        //try {
        //    photo.createNewFile();
        //    Log.i("kk", "sakht");
        //} catch (IOException ex) {
        //    Log.i("kk", "nasakht");
        //}
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        iv_opg = findViewById(R.id.iv_opg);
        iv_back = findViewById(R.id.iv_back);
        btn_tooth = findViewById(R.id.tooth_sample);
        btn_opg = findViewById(R.id.opg_sample);
        btn_send = findViewById(R.id.btn_send);
        ev_description = findViewById(R.id.ev_description);
        presenter = new Presenter_SelectImages();
        presenter.attachView(this,SelectImagesActivity.this);

        tv_message = findViewById(R.id.tv_message);
        myProgressBar = findViewById(R.id.myProgressBar);
        loaderDots = findViewById(R.id.loaderDots);
        btn_retry = findViewById(R.id.retry);
    }

    private void listeners(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.imageClicked(1);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.imageClicked(2);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.imageClicked(3);
            }
        });

        iv_opg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.opgClicked();
            }
        });

        btn_tooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sampleClicked(1);
            }
        });

        btn_opg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sampleClicked(2);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendClicked(ev_description.text());
            }
        });
    }
    @Override
    public void setImage(ThreeImages images) {
        if (images != null) {
            if (images.getImageOne() != null) {
                image1.setImageBitmap(images.getImageOne());
            }
            if (images.getImageTwo() != null) {
                image2.setImageBitmap(images.getImageTwo());
            }
            if (images.getImageThree() != null) {
                image3.setImageBitmap(images.getImageThree());
            }
        }

        int plus = images.getCount()+ 1;
        switch (plus){
            case 1:
                image1.setImageResource(R.mipmap.add_icon);
                image1.setBackgroundResource(R.drawable.bg_button_red);
                image2.setImageDrawable(null);
                image2.setBackgroundDrawable(null);
                image3.setImageDrawable(null);
                image3.setBackgroundDrawable(null);
                break;
            case 2:
                image2.setImageResource(R.mipmap.add_icon);
                image2.setBackgroundResource(R.drawable.bg_button_red);
                image3.setImageDrawable(null);
                image3.setBackgroundDrawable(null);
                break;
            case 3:
                image3.setImageResource(R.mipmap.add_icon);
                image3.setBackgroundResource(R.drawable.bg_button_red);
                break;
        }
    }

    @Override
    public void showDialogCameraAndGallery(int id) {
        showDialogAddImage(id);
    }

    @Override
    public void showDialogDelete(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_delete_image, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        MyTextView tv_yes = rootView.findViewById(R.id.tv_delete);
        MyTextView tv_exit = rootView.findViewById(R.id.tv_exit);

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteClicked(id);
                show.dismiss();
            }
        });
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
    }

    @Override
    public void showDialogCameraAndGalleryOpg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_choose_image, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout ll_camera = rootView.findViewById(R.id.ll_camera);
        LinearLayout ll_gallery = rootView.findViewById(R.id.ll_gallery);

        ll_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SelectImagesActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionCameraOpg);
                }else {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyTooth");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, permissionCameraOpg);
                    show.dismiss();
                }
            }
        });

        ll_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SelectImagesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},permissionGalleryOpg);

                }else{
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, permissionGalleryOpg);
                    show.dismiss();
                }
            }
        });
    }

    @Override
    public void setOpg(Bitmap opg) {
        iv_opg.setImageBitmap(opg);
    }

    @Override
    public void showSampleDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_sample, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        MyImageView image1 = rootView.findViewById(R.id.image1);
        MyImageView image2 = rootView.findViewById(R.id.image2);
        MyImageView image3 = rootView.findViewById(R.id.image3);
        MyImageView image4 = rootView.findViewById(R.id.image4);

        MyTextView tv_description = rootView.findViewById(R.id.tv_description);
        MyTextView tv_exit = rootView.findViewById(R.id.tv_exit);

        if (id == 1){
            image1.setImageResource(R.drawable.mini);
            image2.setImageResource(R.drawable.mini);
            image3.setImageResource(R.drawable.mini);
            image4.setImageResource(R.drawable.mini);
        }else {
            image1.setImageResource(R.drawable.mini);
            image2.setImageResource(R.drawable.mini);
            image3.setImageResource(R.drawable.mini);
            image4.setImageResource(R.drawable.mini);
        }

        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });

    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(SelectImagesActivity.this,message);
    }

    private void showDialogAddImage(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_choose_image, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout ll_camera = rootView.findViewById(R.id.ll_camera);
        LinearLayout ll_gallery = rootView.findViewById(R.id.ll_gallery);

        ll_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SelectImagesActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionCamera);
                }else {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyTooth");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, permissionCamera);
                    show.dismiss();
                }
            }
        });

        ll_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SelectImagesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},permissionGallery);

                }else{
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, permissionGallery);
                    show.dismiss();
                }
            }
        });
    }

    private void performCrop(Uri uri,int key) {
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(uri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 400);
            cropIntent.putExtra("outputY", 400);

            File f = new File(Environment.getExternalStorageDirectory(),"/temporary_holder.jpg");
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Log.e("kk", ex.getMessage());
            }

            uri = Uri.fromFile(f);

            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

            startActivityForResult(cropIntent, key);

        } //respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == permissionCamera){

            if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SelectImagesActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionCamera);
            }else {
                values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "MyTooth");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, permissionCamera);
            }
        }else if (requestCode ==permissionGallery){
            if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SelectImagesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},permissionGallery);

            }else{
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, permissionGallery);
            }
        }
        //----opg
        if (requestCode == permissionCameraOpg){

            if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SelectImagesActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionCameraOpg);
            }else {
                values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "MyTooth");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, permissionCameraOpg);
            }
        }else if (requestCode ==permissionGalleryOpg){
            if (ActivityCompat.checkSelfPermission(SelectImagesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SelectImagesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},permissionGalleryOpg);

            }else{
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, permissionGalleryOpg);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == permissionCamera && resultCode == RESULT_OK){

            try {
                performCrop(imageUri,PIC_CROP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == permissionGallery && resultCode == RESULT_OK){
            Uri uri = data.getData();
            performCrop(uri,PIC_CROP);
        }

        if (requestCode == PIC_CROP && resultCode == RESULT_OK){
            if (data != null) {
                Log.i("kk","oomad");
                String filePath = Environment.getExternalStorageDirectory()+ "/temporary_holder.jpg";
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);

                if (bitmap.getWidth() != 400 | bitmap.getHeight() != 400){
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 400, 400);
                    presenter.addImage(resizedBitmap);
                    Log.i("kk",resizedBitmap.getHeight() +"  resize  "+ resizedBitmap.getWidth());
                }else {
                    presenter.addImage(bitmap);
                    Log.i("kk",bitmap.getHeight() +"  *  "+ bitmap.getWidth());
                }




            }
        }

        //-----opg-----

        if (requestCode == permissionCameraOpg && resultCode == RESULT_OK){

            try {
                performCrop(imageUri,PIC_CROP_OPG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == permissionGalleryOpg && resultCode == RESULT_OK){
            Uri uri = data.getData();
            performCrop(uri,PIC_CROP_OPG);
        }

        if (requestCode == PIC_CROP_OPG && resultCode == RESULT_OK){
            if (data != null) {
                String filePath = Environment.getExternalStorageDirectory()+ "/temporary_holder.jpg";
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);

                if (bitmap.getWidth() != 400 | bitmap.getHeight() != 400){
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 400, 400);
                    presenter.addOpg(resizedBitmap);
                    Log.i("kk",bitmap.getHeight() +"  resize  "+ bitmap.getWidth());
                }else {
                    presenter.addOpg(bitmap);
                    Log.i("kk",bitmap.getHeight() +"  *  "+ bitmap.getWidth());
                }
            }
        }
    }

    @Override
    public void showProgressBar() {
        myProgressBar.setVisibility(View.VISIBLE);
        loaderDots.setVisibility(View.VISIBLE);
        btn_retry.setVisibility(View.INVISIBLE);
        tv_message.setText("");
    }

    @Override
    public void hideProgressBarr() {
        myProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMessageInMonitor(String message) {
        loaderDots.setVisibility(View.INVISIBLE);
        btn_retry.setVisibility(View.VISIBLE);
        tv_message.setText(message);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(SelectImagesActivity.this, HomePatientActivity.class);
        try {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch (Exception e){}

        startActivity(intent);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
