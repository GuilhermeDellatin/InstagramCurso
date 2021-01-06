package com.example.instagramcurso.login.datasource;


import com.example.instagramcurso.common.model.DataBase;
import com.example.instagramcurso.common.model.UserAuth;
import com.example.instagramcurso.common.presenter.Presenter;

public class LoginLocalDataSource implements LoginDataSource {
    @Override
    public void login(String email, String password, Presenter presenter) {
        DataBase.getInstance().login(email, password)
                .addOnSuccessListener((DataBase.OnSuccessListener<UserAuth>) response -> presenter.onSuccess(response))
                .addOnFailureListener(e -> presenter.onError(e.getMessage()))
                .addOnCompleteListener(() -> presenter.onComplete());

    }
}
