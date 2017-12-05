package com.example.star.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver extends BroadcastReceiver {
    //Intent intent=getIntent();

    @Override
    public void onReceive(Context context, Intent intent) {//알람 시간이 되었을때 onReceive를 호출함
        //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고


        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, LoginActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.icon)
                .setTicker("Fooriend")
                .setWhen(System.currentTimeMillis())
                .setNumber(1)
                .setContentTitle("Fooriend")
                .setContentText("예약 시간 30분 전입니다!")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

                Notification.InboxStyle inboxStyle = new Notification.InboxStyle(builder);
                inboxStyle.addLine("바비레드");
                inboxStyle.addLine("한땀 한땀");
                inboxStyle.addLine("고의 고의");
                inboxStyle.addLine("적어드립니다.");
                inboxStyle.setSummaryText("더 보기");
                builder.setStyle(inboxStyle);

                notificationmanager.notify(1, builder.build());
    }
}