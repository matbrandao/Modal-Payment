package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.mat_brandao.modalpayment.R;
import br.com.mat_brandao.modalpayment.util.MaskUtil;
import br.com.mat_brandao.modalpayment.view.base.BaseActivity;
import br.com.mat_brandao.modalpayment.view.base.BasePresenter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModalActivity extends BaseActivity implements ModalView {
    private static final String TAG = "ModalActivity";
    @Bind(R.id.total_price_text)
    TextView totalPriceText;
    @Bind(R.id.amount_left_text)
    TextView amountLeftText;
    @Bind(R.id.amount_paying_edit_text)
    EditText amountPayingEditText;
    @Bind(R.id.money_button)
    Button moneyButton;
    @Bind(R.id.card_button)
    Button cardButton;
    @Bind(R.id.cancel_text)
    TextView cancelText;
    @Bind(R.id.ok_text)
    TextView okText;
    @Bind(R.id.amount_paid_money_text)
    TextView amountPaidMoneyText;
    @Bind(R.id.money_paid_layout)
    LinearLayout moneyPaidLayout;
    @Bind(R.id.amount_paid_credit_text)
    TextView amountPaidCreditText;
    @Bind(R.id.credit_paid_layout)
    LinearLayout creditPaidLayout;

    private ModalPresenterImpl mPresenter;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_modal);
        ButterKnife.bind(this);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        amountPayingEditText.addTextChangedListener(MaskUtil.insertMoney(amountPayingEditText));

        mPresenter = new ModalPresenterImpl(this, this);
    }

    @OnClick(R.id.cancel_text)
    void onCancelTextClick() {
        finishActivity();
    }

    @Override
    public void showToast(String text) {
        super.showToast(text);
    }

    @Override
    public void goToActivity(Class<?> activity) {
        super.goToActivity(activity);
    }

    @Override
    public void goToActivity(Intent intent) {
        super.goToActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showProgressDialog(String message) {
        super.showProgressDialog(this, message);
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
    }

    @Override
    public Intent getActivityIntent() {
        return getIntent();
    }

    @Override
    public void setPriceText(String priceText) {
        Log.d(TAG, "setPriceText() called with: priceText = [" + priceText + "]");
    }

    @Override
    public void setAmountPayingText(String price) {
        amountPayingEditText.setText(price + "0");
    }

    @Override
    public void setAmountLeft(String amountLeft) {
        amountLeftText.setText(amountLeft);
    }
}