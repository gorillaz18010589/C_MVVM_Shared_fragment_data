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

import com.dyaco.c_mvvm.databinding.FragmentLeftBinding;


public class LeftFragment extends Fragment {
    private FragmentLeftBinding fragmentLeftBinding;
    private AccountModel accountModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLeftBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_left,container,false);
        return fragmentLeftBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accountModel = new ViewModelProvider(getActivity()).get(AccountModel.class);


        fragmentLeftBinding.fragmentSetButton.setOnClickListener(v ->{
//            accountModel.setAccount("leftFragment", 1 ,"这段数据是从LeftFragment中Post出来的");
                accountModel.getAccount().postValue(new AccountBean("leftFragment", 1 ,"这段数据是从LeftFragment中Post出来的"));
        });


        accountModel.getAccount().observe(getActivity(), new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                fragmentLeftBinding.fragmentTextView.setText(AccountModel.getFormatContent(accountBean.getName(), accountBean.getAge(), accountBean.getJob()));
            }
        });
    }
}