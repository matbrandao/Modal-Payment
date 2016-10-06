package br.com.mat_brandao.modalpayment.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;

/**
 * Created by Mateus Brandão on 05-Oct-16.
 */

public class MaskUtil {
    private static String current = "";
    public static TextWatcher insertMoney(final EditText editTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current) && s.length() > 0) {
                    editTxt.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[R$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100)).replaceAll("[R$]", "");

                    current = formatted;
                    editTxt.setText("R$ " + formatted);
                    editTxt.setSelection(formatted.length());
                    editTxt.addTextChangedListener(this);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void afterTextChanged(Editable s) {
            }
        };
    }

}
