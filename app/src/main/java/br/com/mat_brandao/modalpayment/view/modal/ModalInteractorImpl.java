package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Context;
import android.content.Intent;

import java.util.List;
import java.util.Locale;

import br.com.mat_brandao.modalpayment.model.Payment;

public class ModalInteractorImpl implements ModalInteractor {
    public static final String PRICE_INTENT_KEY = "price_intent_key";
    private final Context mContext;

    public ModalInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public boolean isIntentValid(Intent intent) {
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                return true;
            }
        } else if (action == null && type == null && intent.hasExtra(PRICE_INTENT_KEY)) {
            return true;
        }

        return false;
    }

    @Override
    public String formatPrice(float price, boolean includeSymbol) {
        String result = "";
        if (includeSymbol) {
            result = String.format(Locale.getDefault(), "R$ %.2f", Math.abs(price));
            if (price < 0) {
                result += " (Troco)";
            }
        } else {
                result = String.format(Locale.getDefault(), "%.2f", Math.abs(price));
                if (price < 0) {
                    result += " (Troco)";
                }
        }
        return result;
    }

    @Override
    public float getPriceFromString(String amount) {
        return Float.valueOf(amount.replace("R$ ", "").replace(".", "").replace(",", "."));
    }

    @Override
    public Payment getMoneyPayment(List<Payment> paymentList) {
        Payment oldPayment = null;
        for (Payment payment : paymentList) {
            if (payment.getPaymentType().equals(Payment.MONEY_TYPE)) {
                oldPayment = payment;
            }
        }
        return oldPayment;
    }
}