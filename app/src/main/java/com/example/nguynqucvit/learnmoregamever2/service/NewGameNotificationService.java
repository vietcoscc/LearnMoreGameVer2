package com.example.nguynqucvit.learnmoregamever2.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.asynctask.JsoupParserAsyncTask;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by viet on 28/07/2017.
 */

public class NewGameNotificationService extends Service {
    private ArrayList<ItemGame> mArrItemGame = new ArrayList<>();
    private boolean mIsRunning = true;
    private int mId = 0;

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread(runnable);
        thread.start();
        return START_STICKY;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (mIsRunning) {
                JsoupParserAsyncTask jsoupParserAsyncTask = new JsoupParserAsyncTask();
                jsoupParserAsyncTask.setmOnCompleteParsingListener(new JsoupParserAsyncTask.OnCompleteParsingListener() {
                    @Override
                    public void onComplete(ArrayList<ItemGame> itemGames) {
                        try {
                            if (mArrItemGame.isEmpty()) {
                                mArrItemGame.addAll(itemGames);
                            } else {
                                ItemGame itemGameOld = mArrItemGame.get(0);
                                ItemGame itemGameNew = itemGames.get(0);
                                if (!itemGameOld.getName().equals(itemGameNew.getName())) {
                                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    manager.notify(mId, buildNotification(itemGameNew));
                                    mId++;
                                    Toast.makeText(NewGameNotificationService.this, mId + "", Toast.LENGTH_SHORT).show();
                                    mArrItemGame.clear();
                                    mArrItemGame.addAll(itemGames);
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
                jsoupParserAsyncTask.execute("https://linkneverdie.com/f1/Action-Games/?page=1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private Notification buildNotification(ItemGame itemGame) {
        String imageUrl = itemGame.getImageUrl();
        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.drawable.ic_menu_gallery);
            builder.setLargeIcon(bitmap);
            builder.setContentTitle(itemGame.getName());
            builder.setContentText(itemGame.getType());
            Notification notification = builder.build();
            return notification;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
