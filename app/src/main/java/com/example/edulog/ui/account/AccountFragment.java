package com.example.edulog.ui.account;

import androidx.lifecycle.ViewModelProvider;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edulog.R;
import com.example.edulog.databinding.FragmentAccountBinding;
import com.example.edulog.ui.mark.MarkActivity;
import com.example.edulog.utils.AnimationView;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        assignButton();


        return root;
    }

    Button showMore, showMark;
    LinearLayout moreLayout;
    private void assignButton() {
        showMore = binding.showMore;
        moreLayout = binding.moreLayout;
        showMark = binding.showMark;
        // Start collapsed
        moreLayout.setVisibility(View.GONE);
        showMore.setOnClickListener(v -> {
            if (showMore.getText().equals("Show More")) {
                showMore.setText("Show Less");
                AnimationView.expandView(moreLayout);
            } else {
                showMore.setText("Show More");
                AnimationView.collapseView(moreLayout);
            }
        });
        showMark.setOnClickListener(v -> {
            getContext().startActivity(new Intent(getContext(), MarkActivity.class));
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}