package com.example.instagramcurso.common.view;

import android.content.Context;

public interface View {
    Context getContext();
    void showProgressBar();
    void hideProgressBar();
    void setStatusBarDark();
}
