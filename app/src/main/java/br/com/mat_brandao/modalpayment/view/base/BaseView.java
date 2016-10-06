package br.com.mat_brandao.modalpayment.view.base;

import android.content.Intent;

/**
 * Created by Mateus Brandão on 04-Apr-16.
 */
public interface BaseView {
    void showToast(String text);

    void showProgressDialog(String message);

    void dismissProgressDialog();

    void goToActivity(Class<?> activity);

    void goToActivity(Intent intent);

    void finishActivity();
}