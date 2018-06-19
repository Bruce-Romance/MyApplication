package yomix.yt.com.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import dialog.DialogUtils;
import dialog.MessageDialog;
import dialog.onMessageDialogClick;
import permission.PermissionsUtils;
import toast.ToastUtils;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
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
                ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        });
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
            }
        }
    }
}
