package com.fengguncao.learnfit.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fengguncao.learnfit.R;
import com.fengguncao.learnfit.net.api.WanAndroidApi;
import com.fengguncao.learnfit.net.bean.ProjectBean;
import com.fengguncao.learnfit.net.bean.ProjectItem;
import com.fengguncao.learnfit.net.util.HttpUtil;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/**
 * name: UseActivity<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 5/27/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class UseActivity extends AppCompatActivity {
    private static final String TAG = "UseActivity";
    private WanAndroidApi api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use);
        api = HttpUtil.getOnlineCookieRetrofit().create(WanAndroidApi.class);
        //功能防抖 + 网络嵌套
        antiShakeActionUpdate();
    }

    /**
     * 功能防抖 + 网络嵌套 (解决嵌套的问题) flatMap
     */
    private void antiShakeActionUpdate() {
        Button btn_anti_shake = findViewById(R.id.bt_anti_shake);

        RxView.clicks(btn_anti_shake)
            .throttleLast(12000, TimeUnit.MILLISECONDS) //2秒 之内响应一次
            //只给下面的流异步
            .observeOn(Schedulers.io())
            .flatMap(new Function<Object, ObservableSource<ProjectBean>>() {
                @Override
                public ObservableSource<ProjectBean> apply(Object o) throws Exception {
                    return api.getProject();  //主数据
                }
            })
            .flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.DataBean>>() {
                @Override
                public ObservableSource<ProjectBean.DataBean> apply(ProjectBean projectBean) throws Exception {
                    return Observable.fromIterable(projectBean.getData());
                }
            })
            .flatMap(new Function<ProjectBean.DataBean, ObservableSource<ProjectItem>>() {
                @Override
                public ObservableSource<ProjectItem> apply(ProjectBean.DataBean dataBean) throws Exception {
                    return api.getProjectItem(1, dataBean.getId());
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ProjectItem>() {
                @Override
                public void accept(ProjectItem projectItem) throws Exception {
                    Log.e(TAG, "accept: " + projectItem );
                }
            });

    }
}
