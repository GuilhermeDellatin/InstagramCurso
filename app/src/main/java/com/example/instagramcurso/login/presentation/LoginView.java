package com.example.instagramcurso.login.presentation;

import android.content.Context;

import com.example.instagramcurso.common.view.View;

public interface LoginView extends View {
    void onFailureForm(String emailError, String passwordError);
    void onUserLogged();
}
