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
import com.t4zb.self_improvement.databinding.FragmentRegisterBinding;
import com.t4zb.self_improvement.ui.enums.PopupType;
import com.t4zb.self_improvement.ui.lıstener.PopupChangeListener;
import com.t4zb.self_improvement.ui.view_model.AuthViewModel;
import com.t4zb.self_improvement.utils.DeviceUtility;
import com.t4zb.self_improvement.utils.StringUtils;

import java.util.Objects;

public class RegisterFragment extends DialogFragment {

    private static final String TAG = RegisterFragment.class.getSimpleName();
    private final PopupChangeListener popupChangeListener;
    private FragmentRegisterBinding mBinging;
    private final AuthViewModel authViewModel;
    private Context mContext;
    private String username, mail, password;

    public RegisterFragment(PopupChangeListener popupChangeListener, AuthViewModel authViewModel){
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
        mBinging = FragmentRegisterBinding.inflate(dialog.getLayoutInflater());
        Point sizes = DeviceUtility.getWindowsize((AppCompatActivity) mContext);

        if (window != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        int x = (int) (sizes.x * 0.9);
        int y = (int) (sizes.y * 0.6);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(x, y);
        mBinging.mainView.setLayoutParams(layoutParams);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(mBinging.getRoot());

        initUI();

        return dialog;
    }

    private void initUI(){

        mBinging.loginButton.setOnClickListener(v -> {
            popupChangeListener.onChange(PopupType.LOGIN);
            dismiss();
        });

        mBinging.registerButton.setOnClickListener(view -> {
            username = Objects.requireNonNull(mBinging.userNameTextField.getText()).toString();
            mail = Objects.requireNonNull(mBinging.userMailTextField.getText()).toString();
            password = Objects.requireNonNull(mBinging.userPasswordTextField.getText()).toString();

            if (!StringUtils.registerValidation(username, mail, password)) {
                Toast.makeText(mContext, "Error : registerValidation", Toast.LENGTH_LONG).show();
                return;
            }
            mBinging.loadingView.setVisibility(View.VISIBLE);
            AppConfig.GMSAuth(authViewModel).registerWithEmail(username, mail, password);
        });

        authViewModel.getAuthsStatus().observe(this,authModel -> {
            if (!authModel.isAuthSuccess()){
                mBinging.loadingView.setVisibility(View.GONE);
                Toast.makeText(mContext,authModel.getAuthMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}