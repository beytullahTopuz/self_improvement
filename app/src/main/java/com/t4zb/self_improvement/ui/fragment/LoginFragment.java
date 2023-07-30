package com.t4zb.self_improvement.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.t4zb.self_improvement.app.AppConfig;
import com.t4zb.self_improvement.databinding.FragmentLoginBinding;
import com.t4zb.self_improvement.ui.enums.PopupType;
import com.t4zb.self_improvement.ui.lıstener.PopupChangeListener;
import com.t4zb.self_improvement.ui.view_model.AuthViewModel;
import com.t4zb.self_improvement.utils.DeviceUtility;
import com.t4zb.self_improvement.utils.StringUtils;

import java.util.Objects;

public class LoginFragment extends DialogFragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private final PopupChangeListener popupChangeListener;
    private FragmentLoginBinding mBinding;
    private final AuthViewModel authViewModel;
    private Context mContext;
    private String mail, password;

    public LoginFragment(PopupChangeListener popupChangeListener, AuthViewModel authViewModel){
        this.popupChangeListener = popupChangeListener;
        this.authViewModel = authViewModel;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = new Dialog(mContext);
        Window window = dialog.getWindow();
        mBinding = FragmentLoginBinding.inflate(dialog.getLayoutInflater());
        Point sizes = DeviceUtility.getWindowsize((AppCompatActivity) mContext);

        if (window != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        dialog.setCanceledOnTouchOutside(false);
        setCancelable(false);

        int x = (int) (sizes.x * 0.9);
        int y = (int) (sizes.y * 0.6);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(x, y);
        mBinding.mainView.setLayoutParams(layoutParams);
        dialog.setContentView(mBinding.getRoot());

        initUI();

        return dialog;
    }

    private void initUI(){

        mBinding.registerButton.setOnClickListener(v -> {
            popupChangeListener.onChange(PopupType.REGISTER);
            dismiss();
        });

        mBinding.loginButton.setOnClickListener(v -> {

            mail = Objects.requireNonNull(mBinding.userMailText.getText()).toString();
            password = Objects.requireNonNull(mBinding.userPasswordText.getText()).toString();

            if (!StringUtils.loginValidation(mail, password)) {
                Toast.makeText(mContext, "Validation Error", Toast.LENGTH_LONG).show();
                return;
            }
            mBinding.loadingView.setVisibility(View.VISIBLE);
            mBinding.loadingView.setOnClickListener(v1 -> Log.i(TAG, "loading view"));
            AppConfig.GMSAuth(authViewModel).LoginEmailPassword(mail, password);
        });

        authViewModel.getAuthsStatus().observe(this,authModel -> {
            if (!authModel.isAuthSuccess()){
                mBinding.loadingView.setVisibility(View.GONE);
                Toast.makeText(mContext,authModel.getAuthMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}