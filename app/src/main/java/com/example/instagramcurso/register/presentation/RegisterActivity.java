package com.example.instagramcurso.register.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.instagramcurso.R;
import com.example.instagramcurso.common.view.AbstractActivity;
import com.example.instagramcurso.main.presentation.MainActivity;
import com.example.instagramcurso.register.presentation.datasource.RegisterDataSource;
import com.example.instagramcurso.register.presentation.datasource.RegisterLocalDataSource;

import butterknife.BindView;

public class RegisterActivity extends AbstractActivity implements RegisterView{

    @BindView(R.id.register_scrollview)
    ScrollView scrollView;

    public static void launch(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setStatusBarDark();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void onInject() {
        RegisterDataSource dataSource = new RegisterLocalDataSource();
        presenter = new RegisterPresenter(dataSource);
        presenter.setRegisterView(this);

        showNextView(RegisterSteps.EMAIL);

    }

    @Override
    public void showNextView(RegisterSteps step) {
        Fragment frag = null;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) scrollView.getLayoutParams();

        switch (step) {
            case EMAIL:
                layoutParams.gravity = Gravity.BOTTOM;
                frag = RegisterEmailFragment.newInstance(presenter);
                break;
            case NAME_PASSWORD:
                layoutParams.gravity = Gravity.BOTTOM;
                frag = RegisterNamePasswordFragment.newInstance(presenter);
                break;
            case WELCOME:
                layoutParams.gravity = Gravity.BOTTOM;
                frag = RegisterWelcomeFragment.newInstance(presenter);
                break;
            case PHOTO:
                layoutParams.gravity = Gravity.TOP;
                frag = RegisterPhotoFragment.newInstance(presenter);
                break;
        }

        scrollView.setLayoutParams(layoutParams);
        
        //Precisamos pegar um cara que gerencia todos os fragmentos no android e dizer em qual lugar
        //devemos incorporar esses elementos
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (manager.findFragmentById(R.id.register_fragment) == null) {
            transaction.add(R.id.register_fragment, frag, step.name());
        } else {
            transaction.replace(R.id.register_fragment, frag, step.name());
            transaction.addToBackStack(step.name());
        }
        transaction.commit();
    }

    @Override
    public void onUserCreated() {
        MainActivity.launch(this);
    }
}