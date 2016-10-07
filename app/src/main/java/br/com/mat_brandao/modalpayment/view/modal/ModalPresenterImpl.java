package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import br.com.mat_brandao.modalpayment.R;
import br.com.mat_brandao.modalpayment.model.Payment;

public class ModalPresenterImpl implements ModalPresenter {

    private ModalInteractorImpl mInteractor;
    private Context mContext;
    private ModalView mView;
    private float mTotalPrice, mAmountLeft;
    private PaymentsAdapter mPaymentAdapter;
    private List<Payment> mPaymentList = new ArrayList<>();
    private boolean canPayMore = true;

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    public ModalPresenterImpl(ModalView view, Context context) {
        mInteractor = new ModalInteractorImpl(context);
        mContext = context;
        mView = view;

        handleIntent();
    }

    private void init(float value) {
        mTotalPrice = value;
        mAmountLeft = value;
        mView.setPriceText(mInteractor.formatPrice(mTotalPrice, true));
        mView.setAmountLeft(mInteractor.formatPrice(mAmountLeft, false));
        mView.setAmountPayingText(String.valueOf(mTotalPrice));

        mPaymentAdapter = new PaymentsAdapter(mContext, mPaymentList);
        mView.setPaymentsAdater(mPaymentAdapter);
    }

    private void handleIntent() {
        Intent intent = mView.getActivityIntent();
        if (mInteractor.isIntentValid(intent)) {
            if (intent.hasExtra(ModalInteractorImpl.PRICE_INTENT_KEY)) {
                float value = intent.getFloatExtra(ModalInteractorImpl.PRICE_INTENT_KEY, 0);
                if (value == 0) {
                    showErrorAndFinish(mContext.getString(R.string.price_error));
                    return;
                }
                init(value);
            } else if (intent.hasExtra(Intent.EXTRA_TEXT)) {
                try {
                    float value = Float.valueOf(intent.getStringExtra(Intent.EXTRA_TEXT));
                    if (value == 0) {
                        showErrorAndFinish(mContext.getString(R.string.price_error));
                        return;
                    }
                    init(value);
                } catch (Exception e) {
                    showErrorAndFinish(mContext.getString(R.string.price_error));
                }
            } else {
                showErrorAndFinish(mContext.getString(R.string.price_error));
            }
        } else {
            showErrorAndFinish(mContext.getString(R.string.requisition_error));
        }
    }

    private void showErrorAndFinish(String error) {
        mView.showToast(error);
        mView.finishActivity();
    }

    private void updateUi() {
        mPaymentAdapter.mCardCounter = 0;
        mPaymentAdapter.notifyDataSetChanged();
        mView.setAmountLeft(mInteractor.formatPrice(mAmountLeft, false));
    }

    private void setFinishedPayment() {
        canPayMore = false;
        mView.enableOkButton();
        mAmountLeft = 0;
    }

    @Override
    public void onMoneyButtonClick(String amount) {
        float amountPaid = mInteractor.getPriceFromString(amount);
        if (canPayMore) {
            if (amountPaid > 0) {
                if (amountPaid > mAmountLeft + 100) {
                    mView.showToast(mContext.getString(R.string.error_greater_value));
                }  else {
                    mAmountLeft -= amountPaid;
                    Payment oldPayment = mInteractor.getMoneyPayment(mPaymentList);
                    if (oldPayment != null) {
                        oldPayment.setPrice(oldPayment.getPrice() + amountPaid);
                    } else {
                        mPaymentList.add(new Payment(Payment.MONEY_TYPE, amountPaid));
                    }
                    updateUi();
                    if (mAmountLeft <= 0) {
                        setFinishedPayment();
                    }
                }
            } else {
                mView.showToast(mContext.getString(R.string.insert_value));
            }
        } else {
            mView.showToast(mContext.getString(R.string.error_greater_value));
        }
    }

    @Override
    public void onCardButtonClick(String amount) {
        float amountPaid = mInteractor.getPriceFromString(amount);
        if (canPayMore) {
            if (amountPaid > 0) {
                if (amountPaid > mAmountLeft) {
                    mView.showToast(mContext.getString(R.string.error_greater_value));
                } else {
                    mAmountLeft -= amountPaid;
                    mPaymentList.add(new Payment(Payment.CARD_TYPE, amountPaid));
                    updateUi();
                    if (mAmountLeft <= 0) {
                        setFinishedPayment();
                    }
                }
            } else {
                mView.showToast(mContext.getString(R.string.insert_value));
            }
        } else {
            mView.showToast(mContext.getString(R.string.error_greater_value));
        }
    }

    @Override
    public void onOkTextClick() {
        mView.showToast(mContext.getString(R.string.paying));
        mView.finishActivity();
    }
}