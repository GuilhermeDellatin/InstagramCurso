package com.example.instagramcurso.register.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramcurso.R;
import com.example.instagramcurso.common.view.AbstractFragment;
import com.example.instagramcurso.common.view.LoadingButton;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterWelcomeFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.WelcomeView {

    @BindView(R.id.register_button_next)
    LoadingButton buttonNext;
    @BindView(R.id.register_text_view_welcome)
    TextView textViewWelcome;

    public static RegisterWelcomeFragment newInstance(RegisterPresenter presenter) {
        RegisterWelcomeFragment fragment = new RegisterWelcomeFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }

    public RegisterWelcomeFragment() {}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonNext.setEnabled(true);
        textViewWelcome.setText(getString(R.string.welcome_to_instagram, presenter.getName()));
    }

    @OnClick(R.id.register_button_next)
    public void onButtonNextClick() {
        presenter.showPhotoView();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_register_welcome;
    }
}
