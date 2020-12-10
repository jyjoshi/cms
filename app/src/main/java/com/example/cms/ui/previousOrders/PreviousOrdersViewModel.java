package com.example.cms.ui.previousOrders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreviousOrdersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PreviousOrdersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}