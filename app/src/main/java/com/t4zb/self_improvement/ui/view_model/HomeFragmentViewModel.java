package com.t4zb.self_improvement.ui.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeFragmentViewModel extends ViewModel {

    private MutableLiveData<Boolean> isSetEmotionValueSuccess = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsSetEmotionValueSuccess() {
        return isSetEmotionValueSuccess;
    }

    public void setIsSetEmotionValueSuccess(Boolean isSetEmotionValueSuccess) {
        this.isSetEmotionValueSuccess.setValue(isSetEmotionValueSuccess);
    }
}
