package com.dyaco.c_mvvm;
//目的一個activity兩個Framgnent,同時共享數據
//ex1.Activity btnAddUser setValue時 LeftFragment,RightFramgnet有監聽都會改變值同一個ViewModel
//ex2 leftFragment - >observe 監聽了值有改變都會變
//3.new ViewModelProvider(getActivity()) 一定要getActivity代表相依於同一個activity
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.dyaco.c_mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private AccountModel accountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.取得model
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        accountModel = new ViewModelProvider(this).get(AccountModel.class);

        //2.堪入兩個Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fr1, new LeftFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fr2, new RightFragment()).commit();

        //3.setValue
        activityMainBinding.btnAddUser.setOnClickListener(v -> {
            accountModel.setAccount("hank", 18, "MainActivity -> setValue");
        });

        //4.監聽改變值
        accountModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(AccountBean accountBean) {
                activityMainBinding.tvName.setText(accountBean.getName());
                activityMainBinding.tvAge.setText(String.valueOf(accountBean.getAge()));
                activityMainBinding.tvJob.setText(accountBean.getJob());
            }
        });

    }


}