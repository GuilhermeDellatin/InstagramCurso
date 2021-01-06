package com.example.instagramcurso.login.datasource;

import com.example.instagramcurso.common.presenter.Presenter;

public interface LoginDataSource {
    void login(String email, String password, Presenter presenter);
}
