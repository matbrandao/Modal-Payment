package br.com.mat_brandao.modalpayment.view.base;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Mateus Brandão on 04-Apr-16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override protected void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override protected void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override protected void onDestroy() {
        getPresenter().onDestroy();
        super.onDestroy();
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showToast(String text) {
        Toast toast = Toast.makeText(BaseActivity.this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    protected void goToActivity(Class<?> activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            try {
                startActivity(new Intent(this, activity), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } catch (Exception e) {
                startActivity(new Intent(this, activity));
            }
        } else {
            startActivity(new Intent(this, activity));
        }
    }

    protected void goToActivity(Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    protected void showProgressDialog(Context context, String message) {
        if (mProgressDialog == null)
            mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        try {
            mProgressDialog.dismiss();
        } catch (Exception e) {}
    }

    protected abstract BasePresenter getPresenter();
}