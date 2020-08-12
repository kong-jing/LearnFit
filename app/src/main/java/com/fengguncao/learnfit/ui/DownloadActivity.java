package com.fengguncao.learnfit.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fengguncao.learnfit.R;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * name: DownloadActivity<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 5/27/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class DownloadActivity extends AppCompatActivity {
    private static final String TAG = "DownloadActivity";
    // 网络图片的链接地址
    private final static String PATH = "http://pic1.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";
    // 弹出加载框
    private ProgressDialog progressDialog;
    // ImageView控件，用来显示结果图像
    private ImageView image;


    private final Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            image.setImageBitmap(bitmap);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        image = findViewById(R.id.image);
    }

    /**
     * 传统方法
     * @param view
     */
    public void downloadImageAction(View view) {
        Context context;
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("下载图片中");
        progressDialog.show();

        new Thread(()->{
            try {
                URL url = new URL(PATH);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                int respnseCode = httpURLConnection.getResponseCode();
                if (respnseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Message msg = mHandler.obtainMessage();
                    msg.obj = bitmap;
                    mHandler.sendMessage(msg);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // TODO *****************************************************  RxJava　思想思维编程

    /**
     * 封装操作
     * @param <UD>
     * @return
     */
    public final static <UD> ObservableTransformer<UD, UD> rxud() {
        return strem-> {
            return strem.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ud -> {
                    Log.d(TAG, "rxud: 我监听到了你，然后再执行");
                    return ud;
                });
        };
    }

    /**
     * 下载的方法
     */
    public void rxJavaDownloadImageAction(View view) {
        //起点
        Observable.just(PATH)  //内部会分发 PATH String //第二步

        //第三步
        .map(new Function<String, Bitmap>() {
            @Override
            public Bitmap apply(String s) throws Exception {
                URL url = new URL(PATH);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                int responseCode = httpURLConnection.getResponseCode(); // 才开始 request
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
                return null;
            }
        })
        .map(new Function<Bitmap, Bitmap>() {
            @Override
            public Bitmap apply(Bitmap bitmap) throws Exception {
                Paint paint = new Paint();
                paint.setTextSize(88);
                paint.setColor(Color.RED);
                return drawTextToBitmap(bitmap, "Hello World！", paint, 100, 100);
            }
        })
            //日志记录
        .map(new Function<Bitmap, Bitmap>() {
            @Override
            public Bitmap apply(Bitmap bitmap) throws Exception {
                Log.e(TAG, "apply: 这个时候下载了图片了 " + System.currentTimeMillis());
                return bitmap;
            }
        })
        .compose(rxud())
        //订阅 起点 和 终点 订阅起来
        .subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(Disposable d) {
                // 预备 开始 要分发
                // TODO 第一步
                progressDialog = new ProgressDialog(DownloadActivity.this);
                progressDialog.setTitle("download run");
                progressDialog.show();
            }

            @Override
            public void onNext(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    private final Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingRight) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();
        paint.setDither(true);
        paint.setFilterBitmap(true);
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, paddingLeft, paddingRight, paint);
        return bitmap;
    }
}
