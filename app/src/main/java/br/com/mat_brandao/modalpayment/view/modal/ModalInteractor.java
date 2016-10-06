package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Intent;

public interface ModalInteractor {
    boolean isIntentValid(Intent intent);

    String formatPrice(float price);

    float getPriceFromString(String amount);
}