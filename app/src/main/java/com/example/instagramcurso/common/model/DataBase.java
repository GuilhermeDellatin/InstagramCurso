package com.example.instagramcurso.common.model;

import android.os.Handler;

import java.util.HashSet;
import java.util.Set;

//Vamos usar o padrão Singleton
public class DataBase {

    private static Set<UserAuth> usersAuth;
    private static Set<User> users;
    private static DataBase INSTANCE;

    static {
        usersAuth = new HashSet<>();
        users = new HashSet<>();
        //usersAuth.add(new UserAuth("user1@gmail.com", "1234"));
        //usersAuth.add(new UserAuth("user2@gmail.com", "12345"));
        //usersAuth.add(new UserAuth("user3@gmail.com", "123456"));
        //usersAuth.add(new UserAuth("user4@gmail.com", "1234567"));
        //usersAuth.add(new UserAuth("user5@gmail.com", "12345678"));
        //usersAuth.add(new UserAuth("user6@gmail.com", "123456789"));
    }

    private UserAuth userAuth;
    private OnSuccessListener onSuccessListener;
    private OnFailureListener onFailureListener;
    private OnCompleteListener onCompleteListener;

    public static DataBase getInstance() {
        if (INSTANCE == null) INSTANCE = new DataBase();
        return INSTANCE;
    }

    public <T> DataBase addOnSuccessListener(OnSuccessListener<T> listener) {
        this.onSuccessListener = listener;
        return this;
    }

    public DataBase addOnFailureListener(OnFailureListener listener) {
        this.onFailureListener = listener;
        return this;
    }

    public DataBase addOnCompleteListener(OnCompleteListener listener) {
        this.onCompleteListener = listener;
        return this;
    }

    public DataBase createUser(String name, String email, String password) {
        timeout(() -> {
            UserAuth userAuth = new UserAuth();
            userAuth.setEmail(email);
            userAuth.setPassword(password);

            usersAuth.add(userAuth);

            User user = new User();
            user.setEmail(email);
            user.setName(name);

            boolean added = users.add(user);
            if (added) {
                this.userAuth = userAuth;
                onSuccessListener.onSuccess(userAuth);
            } else {
                this.userAuth = null;
                onFailureListener.onFailure(new IllegalArgumentException("Usuário já existe"));
            }
            onCompleteListener.onComplete();
        });
        return this;
    }

    //Vamos utilizar o conceito de Builder
    //Esse método está imitando um select * from user where email =? and password = ?
    public DataBase login(String email, String password) {
        timeout(() -> {
            UserAuth userAuth = new UserAuth();
            userAuth.setEmail(email);
            userAuth.setPassword(password);

            if (usersAuth.contains(userAuth)) {
                this.userAuth = userAuth;
                onSuccessListener.onSuccess(userAuth);
            } else {
                this.userAuth = null;
                onFailureListener.onFailure(new IllegalArgumentException("Usuário não encontrado"));
            }

            onCompleteListener.onComplete();
        });
        return this;
    }

    private void timeout(Runnable r) {
        new Handler().postDelayed(r, 2000);
    }

    public interface OnSuccessListener<T> {
        void onSuccess(T response);
    }

    public interface OnFailureListener {
        void onFailure(Exception e);
    }

    public interface OnCompleteListener {
        void onComplete();
    }

}
