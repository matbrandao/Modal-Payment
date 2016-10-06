package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Context;
import android.content.Intent;

import java.util.Locale;

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
        }

        return false;
    }

    @Override
    public String formatPrice(float price) {
        return String.format(Locale.getDefault(), "R$ %.2f", price);
    }
}