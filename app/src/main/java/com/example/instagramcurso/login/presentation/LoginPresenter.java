package com.example.instagramcurso.login.presentation;

import android.os.Handler;

import com.example.instagramcurso.R;
import com.example.instagramcurso.common.model.UserAuth;
import com.example.instagramcurso.common.presenter.Presenter;
import com.example.instagramcurso.common.util.Strings;
import com.example.instagramcurso.login.datasource.LoginDataSource;

public class LoginPresenter implements Presenter<UserAuth> {
    private final LoginView view;
    private final LoginDataSource dataSource;

    public LoginPresenter(LoginView view, LoginDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void login(String email, String password) {
        if (!Strings.emailValid(email)) {
           view.onFailureForm(view.getContext().getString(R.string.invalid_email), null);
           return;
        }
        view.showProgressBar();
        dataSource.login(email, password, this);
    }

    @Override
    public void onSuccess(UserAuth userAuth) {
        view.onUserLogged();
    }

    @Override
    public void onError(String message) {
        view.onFailureForm(null, message);
    }

    @Override
    public void onComplete() {
        view.hideProgressBar();
    }
}
