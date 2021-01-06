package com.example.instagramcurso.register.presentation.datasource;

import com.example.instagramcurso.common.presenter.Presenter;

public interface RegisterDataSource {

    void createUser(String name, String email, String password, Presenter presenter);
}
