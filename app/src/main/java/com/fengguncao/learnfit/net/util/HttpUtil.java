package com.fengguncao.learnfit.net.util;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * name: HttpUtil<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 5/27/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class HttpUtil {
    private static final String TAG = "HttpUtil";

    public static String BASE_URL = "https://www.wanandroid.com/";

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static Retrofit getOnlineCookieRetrofit() {
        OkHttpClient.Builder httBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httBuilder
            .addNetworkInterceptor(new StethoInterceptor())
            .readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build();

        return new Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();
    }
}
