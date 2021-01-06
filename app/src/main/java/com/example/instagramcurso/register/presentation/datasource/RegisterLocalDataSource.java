package com.example.instagramcurso.register.presentation.datasource;

import com.example.instagramcurso.common.model.DataBase;
import com.example.instagramcurso.common.model.UserAuth;
import com.example.instagramcurso.common.presenter.Presenter;

public class RegisterLocalDataSource implements RegisterDataSource {
    @Override
    public void createUser(String name, String email, String password, Presenter presenter) {
        DataBase.getInstance().createUser(name, email, password)
                .addOnSuccessListener((DataBase.OnSuccessListener<UserAuth>) presenter::onSuccess)
                .addOnFailureListener(e -> presenter.onError(e.getMessage()))
                .addOnCompleteListener(presenter::onComplete);
    }
}
