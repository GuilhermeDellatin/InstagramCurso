package com.example.instagramcurso.common.presenter;

import com.example.instagramcurso.common.model.UserAuth;

public interface Presenter<T> {
    void onSuccess(T response);
    void onError(String message);
    void onComplete();
}
