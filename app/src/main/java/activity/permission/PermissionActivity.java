package activity.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dialog.DialogUtils;
import dialog.MessageDialog;
import dialog.onMessageDialogClick;
import permission.PermissionsUtils;
import toast.ToastUtils;
import permission.FileProvider7;
import yomix.yt.com.myapplication.R;

public class PermissionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;
    private String mCurrentPhotoPath;
    private ImageView mIvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mIvPhoto = findViewById(R.id.mIvPhoto);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加手机权限
                PermissionsUtils.with(PermissionActivity.this).addPermission(Manifest.permission.CALL_PHONE).initPermission();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加存储权限
                PermissionsUtils.with(PermissionActivity.this).addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).initPermission();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //访问位置
                PermissionsUtils.with(PermissionActivity.this).addPermission(Manifest.permission.ACCESS_FINE_LOCATION).initPermission();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照
                if (ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    takePhoto();
                }
            }
        });
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA).format(new Date()) + ".png";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();
            Uri fileUri = FileProvider7.getUriForFile(this, file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
            mIvPhoto.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
//            mIvPhoto.setImageURI(Uri.parse(mCurrentPhotoPath));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                    //表明用户没有彻底禁止弹出权限请求
                    ToastUtils.error("拒绝权限");
                } else {
                    new DialogUtils().messageDialog(PermissionActivity.this, "申请权限", "是否去设置中开启权限?", true, new onMessageDialogClick() {
                        @Override
                        public void confirm(MessageDialog dialog) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        }

                        @Override
                        public void cancel(MessageDialog dialog) {

                        }
                    });
                }
            } else {
                ToastUtils.success("允许权限");
                if (i == grantResults.length - 1) {
                    takePhoto();
                }
            }
        }

    }
}
