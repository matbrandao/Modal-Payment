package br.com.mat_brandao.modalpayment.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import br.com.mat_brandao.modalpayment.R;
import br.com.mat_brandao.modalpayment.view.modal.ModalActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.payment_button)
    Button paymentButton;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.payment_button)
    void onPaymentButtonClick() {
        startActivity(new Intent(this, ModalActivity.class));
    }
}
