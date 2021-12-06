package pe.gob.sunat.base.android.ui.series.comprobantes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterComprobantesPago extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<TestComprobantesPago> comprobantesPagoList;
    private Context context;
    private int animationType = 0;

    //Para los Onclick de los items del Recycler
    private View.OnClickListener itemListener;

    public AdapterComprobantesPago(List<TestComprobantesPago> comprobantesPagoList, Context context, int animationType){
        this.comprobantesPagoList = comprobantesPagoList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_serie_comprobante, parent, false);
        //Listener InClick Recycler
        view.setOnClickListener(this);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder){
            OriginalViewHolder view = (OriginalViewHolder) holder;
            TestComprobantesPago testComprobantesPago = comprobantesPagoList.get(position);
            view.numPago.setText(comprobantesPagoList.get(position).getNumeroPago());
            view.emisor.setText(comprobantesPagoList.get(position).getEmisor());
            view.tipoComprobante.setText(comprobantesPagoList.get(position).getTipoComprobante());
            view.tipoOperacion.setText(comprobantesPagoList.get(position).getTipoOperacion());
            view.detalleEmisor.setText(comprobantesPagoList.get(position).getDetalleEmisor());
            view.fechaEmision.setText(comprobantesPagoList.get(position).getFechaEmision());
            view.incoterm.setText(comprobantesPagoList.get(position).getIncoterm());

            //Visibilidad Detalle Comprobante
            boolean visible = comprobantesPagoList.get(position).isVisible();
            view.detalleComprobanteLayout.setVisibility(visible ? View.VISIBLE : View.GONE);

            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return comprobantesPagoList.size();
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animationType);
            lastPosition = position;
        }
    }

    //Método setOnClick Recycler
    public void setOnClickListener(View.OnClickListener itemListener){
        this.itemListener = itemListener;
    }

    //Método OnClick Recycler
    @Override
    public void onClick(View v) {
        if (itemListener!=null){
            itemListener.onClick(v);
        }
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder{
        public TextView numPago, emisor, tipoComprobante,
                tipoOperacion, detalleEmisor, fechaEmision, incoterm;
        public ImageView ivComprobanteDetalle;
        //Detalle Item Visibilidad
        LinearLayout detalleComprobanteLayout;

        public OriginalViewHolder(@NonNull View comprobanteView) {
            super(comprobanteView);
            numPago = (TextView) comprobanteView.findViewById(R.id.tv_num_comprobante_pago);
            emisor = (TextView) comprobanteView.findViewById(R.id.tv_emisor_comprobante);
            tipoComprobante = (TextView) comprobanteView.findViewById(R.id.tv_tipo_comprobante);
            tipoOperacion = (TextView) comprobanteView.findViewById(R.id.tv_tipo_operacion);
            detalleEmisor = (TextView) comprobanteView.findViewById(R.id.tv_emisor_comprobante_detalle);
            fechaEmision = (TextView) comprobanteView.findViewById(R.id.tv_fecha_emision_comprobante);
            incoterm = (TextView) comprobanteView.findViewById(R.id.tv_incoterm_comprobante);
            ivComprobanteDetalle = (ImageView) comprobanteView.findViewById(R.id.iv_comprobante_detalle);
            //Visibilidad Detalle Item
            detalleComprobanteLayout = (LinearLayout) comprobanteView.findViewById(R.id.detalleComprobantePago);

            ivComprobanteDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestComprobantesPago testComprobantesPago = comprobantesPagoList.get(getAdapterPosition());
                    testComprobantesPago.setVisible(!testComprobantesPago.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
