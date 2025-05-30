package com.example.mad_project.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.databinding.ItemNotificationBinding;
import com.example.mad_project.models.InformationRupp;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {

    private List<InformationRupp> informationRupps;

    public NotificationAdapter(){
        this.informationRupps = new ArrayList<>();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.viewHolder holder, int i) {
        InformationRupp informationRupp = informationRupps.get(i);
        holder.binding.showcontent.setText(informationRupp.getContent());
        holder.binding.showdate.setText(informationRupp.getTimeSent());
    }

    @Override
    public int getItemCount() {

        return informationRupps.size();
    }

    public void setData(List<InformationRupp> informationRupps){
        this.informationRupps = informationRupps;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
       protected ItemNotificationBinding binding;
        public viewHolder(@NonNull ItemNotificationBinding binding)  {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
