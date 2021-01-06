package com.example.instagramcurso.register.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramcurso.R;
import com.example.instagramcurso.common.view.AbstractFragment;
import com.example.instagramcurso.common.view.CustomDialog;
import com.example.instagramcurso.common.view.LoadingButton;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterPhotoFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.PhotoView{

    @BindView(R.id.register_button_next)
    LoadingButton buttonNext;

    public static RegisterPhotoFragment newInstance(RegisterPresenter presenter) {
        RegisterPhotoFragment fragment = new RegisterPhotoFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }

    public RegisterPhotoFragment() {}

    @Override
    protected int getLayout() {
        return R.layout.fragment_register_photo;
    }

    //É executado após a view ser criada
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonNext.setEnabled(true);
        /*
         CustomDialog customDialog = new CustomDialog.Builder(getContext())
                .setTitle(R.string.define_photo_profile)
                 .addButton((v) -> {
                     switch (v.getId()) {
                         case R.string.take_picture:
                             Log.i("Teste", "take pic");
                             break;

                         case R.string.search_gallery:
                             Log.i("Teste", "gallery");
                             break;
                     }
                 }, R.string.take_picture, R.string.search_gallery)
                 .build();
         customDialog.show();*/
    }

    @OnClick(R.id.register_button_next)
    public void onButtonNextClick() {
        //TODO: Implementar depois
    }

    @OnClick(R.id.register_button_jump)
    public void onButtonJumpClick() {
        presenter.jumpRegistration();
    }
}
