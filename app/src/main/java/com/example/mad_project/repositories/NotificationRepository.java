package com.example.mad_project.repositories;

import android.content.Context;

import com.example.mad_project.Dao.AppDatabase;
import com.example.mad_project.Dao.MyRoomDao;
import com.example.mad_project.models.InformationRupp;
import com.example.mad_project.models.Student;

import java.util.List;
import java.util.concurrent.Executors;

public class NotificationRepository {

    private Context context;

    private MyRoomDao myRoomDao;

    public NotificationRepository(Context context){
        this.context = context;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        myRoomDao = appDatabase.userDao();
    }

    public void saveNotificationToLocal(InformationRupp informationRupp){
        SaveNotificationToLocal(informationRupp);
    }

    private void SaveNotificationToLocal(InformationRupp informationRupp) {
        Executors.newSingleThreadExecutor().execute(() -> {
            myRoomDao.insertNotification(informationRupp);
        });

    }

    public void getALLNotification(IApiCallback<List<InformationRupp>> callback){
        Executors.newSingleThreadExecutor().execute(() -> {
            List<InformationRupp> informationRupp = myRoomDao.getALLNotification();
            callback.onSuccess(informationRupp);
        });
    }



}
