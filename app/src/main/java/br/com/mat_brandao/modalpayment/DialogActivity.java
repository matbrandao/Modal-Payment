package br.com.mat_brandao.modalpayment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }
}
