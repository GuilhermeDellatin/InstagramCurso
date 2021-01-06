package com.example.instagramcurso.register.presentation;


import android.widget.EditText;
import com.example.instagramcurso.R;
import com.example.instagramcurso.common.view.AbstractFragment;
import com.example.instagramcurso.common.view.LoadingButton;
import com.google.android.material.textfield.TextInputLayout;


import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class RegisterEmailFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.EmailView {

    @BindView(R.id.register_edit_text_email_input)
    TextInputLayout inputLayoutEmail;

    @BindView(R.id.register_edit_text_email)
    EditText editTextEmail;

    @BindView(R.id.register_button_next)
    LoadingButton buttonNext;

    //Todos os fragmentos precisam ter um construtor padrão
    public RegisterEmailFragment() {}

    public static RegisterEmailFragment newInstance(RegisterPresenter presenter) {
        RegisterEmailFragment fragment = new RegisterEmailFragment();

        fragment.setPresenter(presenter);
        presenter.setEmailView(fragment);

        return fragment;
    }

    @Override
    public void showProgressBar() {
        buttonNext.showProgress(true);
    }

    @Override
    public void hideProgressBar() {
        buttonNext.showProgress(false);
    }

    @Override
    public void onFailureForm(String emailError) {
        inputLayoutEmail.setError(emailError);
        editTextEmail.setBackground(findDrawable(R.drawable.edit_text_error));
    }

    //Fragmentos também tem seu ciclo de vida, ele precisa ser inflado dentro de uma activity

    @Override
    protected int getLayout() {
        return R.layout.fragment_register_email;
    }

    @OnClick(R.id.register_text_view_email_login)
    public void onTextViewLoginClick() {
        //Para matar a activity e voltar pra tela de login normalmente,
        //Devido a interrupções que possam ser feitas no ciclo de vida,
        //Por uma chamada telefonica por exemplo
        if (isAdded() && getActivity() != null) getActivity().finish();
    }

    @OnClick(R.id.register_button_next)
    public void onButtonNextClick() {
        presenter.setEmail(editTextEmail.getText().toString());
    }

    @OnTextChanged(R.id.register_edit_text_email)
    public void onTextChanged(CharSequence s) {
        buttonNext.setEnabled(!editTextEmail.getText().toString().isEmpty());
        editTextEmail.setBackground(findDrawable(R.drawable.edit_text_background));
        inputLayoutEmail.setError(null);
        inputLayoutEmail.setErrorEnabled(false);
    }

}
