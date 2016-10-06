package br.com.mat_brandao.modalpayment.view.modal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.com.mat_brandao.modalpayment.R;
import br.com.mat_brandao.modalpayment.model.Payment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.PaymentsViewHolder> {
    private Context mContext;
    private List<Payment> mList;
    public int mCardCounter = 0;

    public PaymentsAdapter(Context context, List<Payment> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public PaymentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_payment_layout, viewGroup, false);
        return new PaymentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaymentsViewHolder holder, int position) {
        Payment payment = mList.get(position);
        holder.paymentTypeText.setText(payment.getPaymentType());
        holder.amountPaidText.setText(formatPrice(payment.getPrice()));

        if (payment.getPaymentType().equals(Payment.CARD_TYPE)) {
            holder.paymentTypeText.append(" " + (mCardCounter + 1));
            mCardCounter++;
        }
    }

    public String formatPrice(float price) {
        return String.format(Locale.getDefault(), "R$ %.2f", price);
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    public class PaymentsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.payment_type_text)
        TextView paymentTypeText;
        @Bind(R.id.amount_paid_text)
        TextView amountPaidText;

        public PaymentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
