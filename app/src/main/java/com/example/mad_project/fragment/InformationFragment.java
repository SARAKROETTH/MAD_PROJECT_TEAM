package com.example.mad_project.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mad_project.R;
import com.example.mad_project.adapter.NotificationAdapter;
import com.example.mad_project.databinding.FragmentHomeBinding;
import com.example.mad_project.databinding.FragmentInformationBinding;
import com.example.mad_project.models.InformationRupp;
import com.example.mad_project.repositories.IApiCallback;
import com.example.mad_project.repositories.NotificationRepository;

import java.util.List;


public class InformationFragment extends Fragment {

    private FragmentInformationBinding binding;

    private NotificationRepository notificationRepository;

    private NotificationAdapter adapter;

    public InformationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInformationBinding.inflate(inflater, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.containerNotification.setLayoutManager(layoutManager);
        notificationRepository = new NotificationRepository(getContext());
        adapter = new NotificationAdapter();
        binding.containerNotification.setAdapter(adapter);

        LoadNotification();

        return binding.getRoot();
    }

    private void LoadNotification() {
        notificationRepository.getALLNotification(new IApiCallback<List<InformationRupp>>() {
            @Override
            public void onSuccess(List<InformationRupp> result) {
                requireActivity().runOnUiThread(() -> {
                    adapter.setData(result); // now runs safely on main thread
                });
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}