package com.example.edulog.ui.people;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edulog.R;
import com.example.edulog.databinding.FragmentHomeWorkBinding;
import com.example.edulog.databinding.FragmentPeopleBinding;
import com.example.edulog.ui.homework.HomeWorkViewModel;

public class PeopleFragment extends Fragment {



    FragmentPeopleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PeopleViewModel peopleViewModel =
                new ViewModelProvider(this).get(PeopleViewModel.class);

        binding = com.example.edulog.databinding.FragmentPeopleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}