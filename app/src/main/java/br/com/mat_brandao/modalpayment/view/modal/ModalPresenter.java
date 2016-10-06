package br.com.mat_brandao.modalpayment.view.modal;

import br.com.mat_brandao.modalpayment.view.base.BasePresenter;

public interface ModalPresenter extends BasePresenter {
    void onMoneyButtonClick(String amount);

    void onCardButtonClick(String amount);
}