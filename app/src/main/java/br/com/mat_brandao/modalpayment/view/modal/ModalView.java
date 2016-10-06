package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Intent;

import br.com.mat_brandao.modalpayment.view.base.BaseView;

interface ModalView extends BaseView {
    Intent getActivityIntent();

    void setPriceText(String priceText);

    void setAmountPayingText(String price);

    void setAmountLeft(String amountLeft);

    void setPaymentsAdater(PaymentsAdapter adater);

    void enableOkButton();
}