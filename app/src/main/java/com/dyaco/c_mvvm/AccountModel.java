package com.dyaco.c_mvvm;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountModel extends AndroidViewModel {
    private MutableLiveData<AccountBean> mAccount = new MutableLiveData<>();


    public AccountModel(@NonNull Application application) {
        super(application);
    }

    public void setAccount(String name, int age, String job) {
        mAccount.setValue(new AccountBean(name, age, job));
    }

    public MutableLiveData<AccountBean> getAccount() {
        return mAccount;
    }

    @Override
    protected void onCleared() {
        Log.v("hank", "AccountModel -> onCleared();");
        super.onCleared();
    }

    public static String getFormatContent(String name, int phone, String blog) {
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append("昵称:");
        mBuilder.append(name);
        mBuilder.append("\n手机:");
        mBuilder.append(phone);
        mBuilder.append("\n博客:");
        mBuilder.append(blog);
        return mBuilder.toString();
    }}
