package com.example.instagramcurso.login.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.instagramcurso.R;
import com.example.instagramcurso.common.view.AbstractActivity;
import com.example.instagramcurso.common.view.LoadingButton;
import com.example.instagramcurso.login.datasource.LoginDataSource;
import com.example.instagramcurso.login.datasource.LoginLocalDataSource;
import com.example.instagramcurso.main.presentation.MainActivity;
import com.example.instagramcurso.register.presentation.RegisterActivity;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

//Era a MainActivity
public class LoginActivity extends AbstractActivity implements LoginView {
    //Importante o ButterKnife só funciona com métodos que não sejam privatos
    //Porque ele é encapsulado privado então não consegue injetar
    @BindView(R.id.login_edit_text_email) EditText editTextEmail;
    @BindView(R.id.login_edit_text_password) EditText editTextPassword;
    @BindView(R.id.login_edit_text_email_input) TextInputLayout inputLayoutEmail;
    @BindView(R.id.login_edit_text_password_input) TextInputLayout inputLayoutPassword;
    @BindView(R.id.login_button_enter) LoadingButton buttonEnter;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Watcher é a classe que escuta eventos de input num editor de texto

        setStatusBarDark();

    }

    @Override
    protected void onInject() {
        LoginDataSource dataSource = new LoginLocalDataSource();
        presenter = new LoginPresenter(this, dataSource);
    }

    @Override
    public void showProgressBar() {
        buttonEnter.showProgress(true);
    }

    @Override
    public void hideProgressBar() {
        buttonEnter.showProgress(false);
    }

    @Override
    public void onFailureForm(String emailError, String passwordError) {
        if (emailError != null) {
            inputLayoutEmail.setError(emailError);
            editTextEmail.setBackground(findDrawable(R.drawable.edit_text_error));
        }
        if (passwordError != null) {
            inputLayoutPassword.setError(passwordError);
            editTextPassword.setBackground(findDrawable(R.drawable.edit_text_error));
        }
    }

    @Override
    public void onUserLogged() {
        MainActivity.launch(this);
    }

    //O ButterKnife facilita fazer o OnClick
    @OnClick(R.id.login_button_enter)
    public void onButtonEnterClick() {
        presenter.login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
    }

    @OnClick(R.id.login_text_view_register)
    public void onTextViewRegisterClick() {
        RegisterActivity.launch(this);
    }

    @OnTextChanged({R.id.login_edit_text_email, R.id.login_edit_text_password})
    public void onTextChanged(CharSequence s) {
        buttonEnter.setEnabled(
                !editTextEmail.getText().toString().isEmpty() &&
                        !editTextPassword.getText().toString().isEmpty());

        if (s.hashCode() == editTextEmail.getText().hashCode()) {
            editTextEmail.setBackground(findDrawable(R.drawable.edit_text_background));
            inputLayoutEmail.setError(null);
            inputLayoutEmail.setErrorEnabled(false);
        }
        else if (s.hashCode() == editTextPassword.getText().hashCode()){
            editTextPassword.setBackground(findDrawable(R.drawable.edit_text_background));
            inputLayoutPassword.setError(null);
            inputLayoutPassword.setErrorEnabled(false);
        }
    }

    //Watcher é a classe que escuta eventos de input num editor de texto

    @Override
    protected int getLayout() {
        return 0;
    }
}