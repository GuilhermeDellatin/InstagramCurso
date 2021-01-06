package com.example.instagramcurso.common.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.instagramcurso.R;
import com.example.instagramcurso.common.util.Colors;
import com.example.instagramcurso.common.util.Drawables;
import com.example.instagramcurso.login.presentation.LoginActivity;

import butterknife.ButterKnife;

public abstract class AbstractActivity extends AppCompatActivity implements View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        onInject();
    }
    //Vamos criar um método abstrato para que todos mundo que estender essa classe
    // tenha que obrigatoriamente implementa-lo, por isso ele vai ser protected
    //@LayoutRes para não deixar passar inteiros que não sejam arquivos de recurso
    protected abstract @LayoutRes int getLayout();

    //Vamos encapsular a chamada da cor vermelha dos erros no email e senha
    public Drawable findDrawable(@DrawableRes int drawableId) {
        return Drawables.getDrawable(this, drawableId);
    }

    public int findColor(@ColorRes int colorId) {
        return Colors.getColor(this, colorId);
    }

    @Override
    public Context getContext() {
        return getBaseContext();
    }

    @Override
    public void showProgressBar() {}

    @Override
    public void hideProgressBar() {}

    @Override
    public void setStatusBarDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(findColor(R.color.black));
        }
    }

    protected abstract void onInject();
}
