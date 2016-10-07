package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Intent;

import java.util.List;

import br.com.mat_brandao.modalpayment.model.Payment;

public interface ModalInteractor {
    boolean isIntentValid(Intent intent);

    String formatPrice(float price, boolean includeSymbol);

    float getPriceFromString(String amount);

    Payment getMoneyPayment(List<Payment> paymentList);
}