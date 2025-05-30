package com.example.mad_project.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mad_project.R;
import com.example.mad_project.databinding.FragmentHomeBinding;
import com.example.mad_project.models.InformationRupp;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private InformationFragment informationFragment = new InformationFragment();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.showNews.setOnClickListener(v -> {
            LoadFragment(informationFragment);

        });
        return binding.getRoot();
    }

    private void LoadFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, fragment)  // fragment_container is MainActivity's FrameLayout
                .addToBackStack(null)
                .commit();
    }
}