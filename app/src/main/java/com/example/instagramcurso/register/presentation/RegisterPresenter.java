package com.example.instagramcurso.register.presentation;

import com.example.instagramcurso.R;
import com.example.instagramcurso.common.model.UserAuth;
import com.example.instagramcurso.common.presenter.Presenter;
import com.example.instagramcurso.common.util.Strings;
import com.example.instagramcurso.register.presentation.datasource.RegisterDataSource;

public class RegisterPresenter implements Presenter<UserAuth> {

    private RegisterView registerView;
    private RegisterView.EmailView emailView;
    private RegisterView.NamePasswordView namePasswordView;

    private final RegisterDataSource dataSource;

    private String email;
    private String name;
    private String password;

    public RegisterPresenter(RegisterDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void setEmailView(RegisterView.EmailView emailView) {
        this.emailView = emailView;
    }

    public void setNamePasswordView(RegisterView.NamePasswordView namePasswordView) {
        this.namePasswordView = namePasswordView;
    }

    public void setEmail(String email) {
        if (!Strings.emailValid(email)) {
            emailView.onFailureForm(emailView.getContext().getString(R.string.invalid_email));
            return;
        }

        this.email = email;
        registerView.showNextView(RegisterSteps.NAME_PASSWORD);
    }

    public void setNameAndPassword(String name, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            namePasswordView.onFailureForm(null, namePasswordView.getContext().getString(R.string.password_not_equal));
            return;
        }

        this.name = name;
        this.password = password;

        namePasswordView.showProgressBar();
        dataSource.createUser(this.name, this.email, this.password, this);
    }

    public String getName() {
        return name;
    }

    @Override
    public void onSuccess(UserAuth response) {
        registerView.showNextView(RegisterSteps.WELCOME);
    }

    @Override
    public void onError(String message) {
        namePasswordView.onFailureCreateUser(message);
    }

    @Override
    public void onComplete() {
        namePasswordView.hideProgressBar();
    }

    public void showPhotoView() {
        registerView.showNextView(RegisterSteps.PHOTO);
    }

    //Para pular o passo de criar usuário sem foto de perfil
    public void jumpRegistration() {
        registerView.onUserCreated();
    }
}
