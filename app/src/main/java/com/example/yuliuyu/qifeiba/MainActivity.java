package com.example.yuliuyu.qifeiba;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baidu.location.Address;
import com.example.yuliuyu.qifeiba.Bean.WeatherBean;
import com.example.yuliuyu.qifeiba.location.LocationHelp;
import com.example.yuliuyu.qifeiba.location.MyLocationListener;
import com.example.yuliuyu.qifeiba.net.QfbHttpUtil;
import java.util.List;
import org.simple.eventbus.EventBus;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import rx.Subscriber;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    LocationHelp mLocationHelp;
    @BindView(R.id.tv_text) TextView mTvText;


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //weatherIp()
        MainActivityPermissionsDispatcher.weatherWithCheck(this);
        //weatherIp();
    }


    @org.simple.eventbus.Subscriber(tag = MyLocationListener.LOACTIONCITY)
    public void loctionCity(Address getAddress) {
        String city = getAddress.city.substring(0, getAddress.city.length() - 1);
        String province = getAddress.district.substring(0, getAddress.district.length() - 1);
        QfbHttpUtil.getInstance().weather("ef6992f9ee79", province, city)
                   .subscribe(new Subscriber<List<WeatherBean>>() {
                       @Override
                       public void onCompleted() {

                       }


                       @Override
                       public void onError(Throwable e) {

                       }


                       @Override
                       public void onNext(List<WeatherBean> o) {

                           if(o.get(0).future.get(0).dayTime!=null){
                               mTvText.setText("上午:"+ o.get(0).future.get(0).dayTime + "下午:" +
                                       o.get(0).future.get(0).night);
                               Toast.makeText(MainActivity.this, o.get(0).future.get(0).dayTime + "下午:" +
                                       o.get(0).future.get(0).night, Toast.LENGTH_LONG).show();
                           }else{
                               mTvText.setText("下午:" +
                                       o.get(0).future.get(0).night);
                               Toast.makeText(MainActivity.this, "下午:" +
                                       o.get(0).future.get(0).night, Toast.LENGTH_LONG).show();
                           }
                       }
                   });
    }


    public void weatherIp() {
        QfbHttpUtil.getInstance().weather("ef6992f9ee79", "朝阳", "北京")
                   .subscribe(new Subscriber<List<WeatherBean>>() {
                       @Override
                       public void onCompleted() {

                       }


                       @Override
                       public void onError(Throwable e) {

                       }


                       @Override
                       public void onNext(List<WeatherBean> o) {
                           Toast.makeText(MainActivity.this, o.get(0).future.get(0).dayTime + "下午" +
                                   o.get(0).future.get(0).night, Toast.LENGTH_LONG).show();
                       }
                   });
    }


    private String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." +
                ((i >> 24) & 0xFF);
    }


    @NeedsPermission({ Manifest.permission.ACCESS_COARSE_LOCATION,
                             Manifest.permission.ACCESS_FINE_LOCATION,
                             Manifest.permission.READ_PHONE_STATE,
                             Manifest.permission.READ_EXTERNAL_STORAGE,
                             Manifest.permission.WRITE_EXTERNAL_STORAGE })
    public void weather() {
        mLocationHelp = new LocationHelp(getApplicationContext());
        mLocationHelp.start();
    }


    @OnShowRationale({ Manifest.permission.ACCESS_COARSE_LOCATION,
                             Manifest.permission.ACCESS_FINE_LOCATION,
                             Manifest.permission.READ_PHONE_STATE,
                             Manifest.permission.READ_EXTERNAL_STORAGE,
                             Manifest.permission.WRITE_EXTERNAL_STORAGE })
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this).setMessage("开启定位")
                                     .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                                         @Override
                                         public void onClick(DialogInterface dialogInterface, int i) {
                                             request.proceed();
                                         }
                                     })
                                     .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                                         @Override
                                         public void onClick(DialogInterface dialogInterface, int i) {
                                             request.cancel();
                                         }
                                     }).show();
    }


    @OnPermissionDenied({ Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE })
    void showDeniedForCamera() {
        Toast.makeText(this, "拒绝", Toast.LENGTH_SHORT).show();
    }


    @OnNeverAskAgain({ Manifest.permission.ACCESS_COARSE_LOCATION,
                             Manifest.permission.ACCESS_FINE_LOCATION,
                             Manifest.permission.READ_PHONE_STATE,
                             Manifest.permission.READ_EXTERNAL_STORAGE,
                             Manifest.permission.WRITE_EXTERNAL_STORAGE })
    void showNeverAskForCamera() {
        Toast.makeText(this, "语序", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationHelp != null) {
            mLocationHelp.onDestroy();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);


    }
}
