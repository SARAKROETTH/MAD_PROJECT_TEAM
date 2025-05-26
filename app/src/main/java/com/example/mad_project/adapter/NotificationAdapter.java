package com.example.mad_project.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.databinding.ItemNotificationBinding;
import com.example.mad_project.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {

    private List<Notification> notificationList;

    public NotificationAdapter(){
        this.notificationList = new ArrayList<>();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        viewHolder holder = new viewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.viewHolder viewHolder, int i) {




    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

       protected ItemNotificationBinding binding;

        public viewHolder(@NonNull ItemNotificationBinding binding)  {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
