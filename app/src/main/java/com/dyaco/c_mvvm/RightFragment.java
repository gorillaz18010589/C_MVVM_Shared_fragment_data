package com.dyaco.c_mvvm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyaco.c_mvvm.databinding.FragmentRightBinding;


public class RightFragment extends Fragment {
    private AccountModel mModel;
    private FragmentRightBinding rightBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rightBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_right, container, false);
        return rightBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mModel = new ViewModelProvider(getActivity()).get(AccountModel.class);

        mModel.getAccount().observe(getActivity(), new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                rightBinding.fragmentTextView.setText(AccountModel.getFormatContent(accountBean.getName(), accountBean.getAge(), accountBean.getJob()));
            }
        });
    }
}