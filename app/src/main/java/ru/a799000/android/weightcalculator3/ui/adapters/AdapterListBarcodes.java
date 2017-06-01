package ru.a799000.android.weightcalculator3.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;


public class AdapterListBarcodes extends RecyclerView.Adapter<AdapterListBarcodes.ViewHolder> implements RealmChangeListener {

    private final RealmList<Barcode> mList;

    private CallBackClickItem mCallBackClickItem;


    public AdapterListBarcodes(RealmList<Barcode> list, CallBackClickItem CallBackClickItem) {
        mList = list;
        mCallBackClickItem = CallBackClickItem;
        mList.addChangeListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_barcode, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        holder.setItem((Barcode) mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public void onChange(Object element) {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_id)
        TextView tvId;

        @BindView(R.id.tv_barcode)
        TextView tvBarcode;

        @BindView(R.id.tv_places)
        TextView tvPlaces;

        @BindView(R.id.tv_weight)
        TextView tvWeight;

        Barcode mItem;


        public void setItem(Barcode item) {
            mItem = item;

            tvId.setText(Long.toString(mItem.getId()));
            tvBarcode.setText(mItem.getBarcode());
            tvPlaces.setText(Integer.toString(mItem.getPlaces()));
            tvWeight.setText(Float.toString(mItem.getWeight()));

        }

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCallBackClickItem.click(Long.toString(mItem.getId()));
        }
    }

    public interface CallBackClickItem {
        void click(String id);
    }

}
