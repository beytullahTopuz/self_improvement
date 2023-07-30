package com.t4zb.self_improvement.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.t4zb.self_improvement.R;
import com.t4zb.self_improvement.ui.enums.PopupType;
import com.t4zb.self_improvement.ui.fragment.LoginFragment;
import com.t4zb.self_improvement.ui.fragment.RegisterFragment;
import com.t4zb.self_improvement.ui.lıstener.PopupChangeListener;
import com.t4zb.self_improvement.ui.view_model.AuthViewModel;

public class AuthActivity extends AppCompatActivity implements PopupChangeListener {
    private static final String TAG = AuthActivity.class.getSimpleName();
    private AuthViewModel authViewModel;
    private PopupType mPopupType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // todo : read sharedPreff User
        /*        if (APP.Config.getUser() == null) {
            openRegisterFrragment();
        }*/
        openLoginFragment();

        readViewObsever();
    }

    private void readViewObsever(){

        authViewModel.getAuthsStatus().observe(this, authModel -> {
            if (authModel.isAuthSuccess()){
                startActivity(MainActivity.class);
            }
        });
    }

    private void startActivity(Class<?> cls){
        Intent intent = new Intent(AuthActivity.this,cls);
        startActivity(intent);
        this.finish();
    }


    private void openRegisterFragment() {

        mPopupType = PopupType.REGISTER;
        RegisterFragment registerFragment = new RegisterFragment(this, authViewModel);
        registerFragment.show(getSupportFragmentManager(), TAG);
    }

    private void openLoginFragment() {

        mPopupType = PopupType.LOGIN;
        LoginFragment loginFragment = new LoginFragment(this, authViewModel);
        loginFragment.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void onChange(PopupType type) {
        switch (type) {
            case REGISTER:
                openRegisterFragment();
                break;
            case LOGIN:
                openLoginFragment();
                break;
        }
    }
}