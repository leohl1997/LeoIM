package com.example.leoim.Net;

import com.example.leoim.Net.NetApi.IMApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建时间: 2022/02/09 15:00 <br>
 * 作者: leo <br>
 * 描述: IM网络接口封装
 */
public class IMNetManager {

  private volatile static IMNetManager sInstance = null;
  private Retrofit mRetrofit;
  private IMApi imApi;

  private IMNetManager() {
    OkHttpClient client = new OkHttpClient.Builder().build();
    mRetrofit =
        new Retrofit.Builder().baseUrl(getBaseUrl())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create())
            .client(client)
            .build();
  }

  public static IMNetManager getInstance() {
    if (sInstance == null) {
      synchronized (IMNetManager.class) {
        if (sInstance == null) {
          sInstance = new IMNetManager();
        }
      }
    }
    return sInstance;
  }

  public synchronized IMApi createImClient() {
    if (imApi == null) {
      return imApi = mRetrofit.create(IMApi.class);
    } else {
      return imApi;
    }
  }

  private String getBaseUrl() {
    return "http://www.baidu.com";
  }
}
