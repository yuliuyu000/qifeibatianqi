package com.example.yuliuyu.qifeiba.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.yuliuyu.qifeiba.app.AppManager;
import org.simple.eventbus.EventBus;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/7/19 16:58
 * 修改人：yuliuyu
 * 修改时间：2017/7/19 16:58
 * 修改备注：
 */
public abstract class BaseActivity extends AppCompatActivity {
    Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        AppManager.getAppManager().addActivity(this);
        EventBus.getDefault().register(this);
        setContentView(getLayoutResId());
        mUnbinder = ButterKnife.bind(this);
        initView();
        initDate();

    }


    protected abstract void initDate();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        EventBus.getDefault().unregister(this);
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @LayoutRes
    protected abstract int getLayoutResId() ;



}
