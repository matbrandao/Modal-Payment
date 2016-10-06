package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Context;
import android.content.Intent;

import br.com.mat_brandao.modalpayment.R;

public class ModalPresenterImpl implements ModalPresenter {

    private ModalInteractorImpl mInteractor;
    private Context mContext;
    private ModalView mView;
    private float mPrice;

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
        mPrice = 1100.0f;
        mView.setPriceText(mInteractor.formatPrice(mPrice));
        mView.setAmountLeft(mInteractor.formatPrice(mPrice));
        mView.setAmountPayingText(String.valueOf(mPrice));
    }

    private void handleIntent() {
        Intent intent = mView.getActivityIntent();
        if (mInteractor.isIntentValid(intent)) {
            if (intent.hasExtra(ModalInteractorImpl.PRICE_INTENT_KEY)) {
                mPrice = intent.getFloatExtra(ModalInteractorImpl.PRICE_INTENT_KEY, 0);
                if (mPrice == 0) {
                    mView.showToast(mContext.getString(R.string.price_error));
                    return;
                }
                mView.setPriceText(mInteractor.formatPrice(mPrice));
            } else {
                mView.showToast(mContext.getString(R.string.price_error));
            }
        } else {
            mView.showToast(mContext.getString(R.string.requisition_error));
            mView.finishActivity();
        }
    }
}