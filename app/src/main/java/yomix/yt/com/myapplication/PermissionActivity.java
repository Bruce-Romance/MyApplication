package yomix.yt.com.myapplication;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import permission.PermissionsUtils;

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
                PermissionsUtils.with(PermissionActivity.this).addPermission(Manifest.permission.CAMERA).initPermission();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
