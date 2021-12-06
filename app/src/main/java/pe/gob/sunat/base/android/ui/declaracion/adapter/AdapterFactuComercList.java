package pe.gob.sunat.base.android.ui.declaracion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.DocumentoTransporte;
import pe.gob.sunat.base.android.model.FacturaComercial;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterFactuComercList extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<FacturaComercial> facturaComercialList;
    private Context context;
    private int animationType = 0;


    public AdapterFactuComercList(List<FacturaComercial> facturaComercialList, Context context, int animationType) {
        this.facturaComercialList = facturaComercialList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_factucomerc_list, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdapterFactuComercList.OriginalViewHolder) {

            OriginalViewHolder view = (OriginalViewHolder) holder;
            FacturaComercial factuComerc = facturaComercialList.get(position);
            view.numFactuComerc.setText(factuComerc.getNumFactuComerc());

            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return facturaComercialList.size();
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animationType);
            lastPosition = position;
        }
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView numFactuComerc;

        public OriginalViewHolder(View v) {
            super(v);
            numFactuComerc = (TextView) v.findViewById(R.id.text_view_num_factucomerc);
        }
    }
}
