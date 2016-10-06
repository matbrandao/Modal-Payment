package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Context;
import android.content.Intent;

import br.com.mat_brandao.modalpayment.R;

public class ModalPresenterImpl implements ModalPresenter {

    private ModalInteractorImpl mInteractor;
    private Context mContext;
    private ModalView mView;
    private float mTotalPrice;

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

//        handleIntent
        mTotalPrice = 1100.0f;
        mView.setPriceText(mInteractor.formatPrice(mTotalPrice));
        mView.setAmountLeft(mInteractor.formatPrice(mTotalPrice));
        mView.setAmountPayingText(String.valueOf(mTotalPrice));
    }

    private void handleIntent() {
        Intent intent = mView.getActivityIntent();
        if (mInteractor.isIntentValid(intent)) {
            if (intent.hasExtra(ModalInteractorImpl.PRICE_INTENT_KEY)) {
                mTotalPrice = intent.getFloatExtra(ModalInteractorImpl.PRICE_INTENT_KEY, 0);
                if (mTotalPrice == 0) {
                    mView.showToast(mContext.getString(R.string.price_error));
                    return;
                }
                mView.setPriceText(mInteractor.formatPrice(mTotalPrice));
            } else {
                mView.showToast(mContext.getString(R.string.price_error));
            }
        } else {
            mView.showToast(mContext.getString(R.string.requisition_error));
            mView.finishActivity();
        }
    }

    @Override
    public void onMoneyButtonClick(String amount) {
        float amountPaid = mInteractor.getPriceFromString(amount);
        if (amountPaid > 0) {
            mTotalPrice -= amountPaid;
//            mView.setAmountLeft(mTotalPrice);
        } else {
            mView.showToast(mContext.getString(R.string.insert_value));
        }
    }

    @Override
    public void onCardButtonClick(String amount) {

    }
}