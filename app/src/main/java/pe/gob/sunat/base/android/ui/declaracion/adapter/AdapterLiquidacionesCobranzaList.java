package pe.gob.sunat.base.android.ui.declaracion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pe.gob.sunat.base.android.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.gob.sunat.base.android.model.LiquidacionCobranza;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterLiquidacionesCobranzaList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LiquidacionCobranza> liquidacionCobranzaList;
    private Context context;
    private int animationType = 0;

    public AdapterLiquidacionesCobranzaList(List<LiquidacionCobranza> liquidacionCobranzaList,
                                            Context context, int animationType) {
        this.liquidacionCobranzaList = liquidacionCobranzaList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_liquidaciones_cobranza_list,
                parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof OriginalViewHolder){
            OriginalViewHolder view = (OriginalViewHolder) holder;
            LiquidacionCobranza liquidacionCobranza = liquidacionCobranzaList.get(position);
            view.numLc.setText(liquidacionCobranza.getNumLc());
            view.tipoLc.setText(liquidacionCobranza.getTipoLc());
            view.estadoLc.setText(liquidacionCobranza.getEstadoLc());
            view.fechaCancelacion.setText(liquidacionCobranza.getFechaCancelacion());
            view.fechaLiquidacion.setText(liquidacionCobranza.getFechaLiquidacion());
            view.monto.setText("US$ "+liquidacionCobranza.getMonto());
            view.sustento.setText(liquidacionCobranza.getSustento());
            //Visibilidad del detalle del contenedor
            boolean visible = liquidacionCobranzaList.get(position).isVisible();
            view.detalleLcLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return liquidacionCobranzaList.size();
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

        public TextView numLc;
        public TextView tipoLc;
        public TextView estadoLc;
        public TextView fechaCancelacion;
        public TextView fechaLiquidacion;
        public TextView monto;
        public TextView sustento;
        public ImageView detalleLc;
        //Detalle visible
        LinearLayout detalleLcLayout;

        public OriginalViewHolder(View v) {
            super(v);
            numLc = (TextView) v.findViewById(R.id.text_view_num_lc);
            tipoLc = (TextView) v.findViewById(R.id.text_view_tipo_lc);
            estadoLc = (TextView) v.findViewById(R.id.text_view_lc_estado);
            fechaCancelacion = (TextView) v.findViewById(R.id.text_view_lc_fecha_cancelcion);
            fechaLiquidacion = (TextView) v.findViewById(R.id.text_view_lc_fecha_liquidacion);
            monto = (TextView) v.findViewById(R.id.text_view_lc_monto);
            sustento = (TextView) v.findViewById(R.id.text_view_lc_sustento);
            detalleLc = (ImageView) v.findViewById(R.id.image_button_deploy_detalle_liquidacion_cobranza);
            detalleLcLayout = (LinearLayout) v.findViewById(R.id.detalleLcLayout);
            //Visibilidad del detalle del contenedor
            detalleLc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LiquidacionCobranza liquidacionCobranza =
                            liquidacionCobranzaList.get(getAdapterPosition());
                    liquidacionCobranza.setVisible(!liquidacionCobranza.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
