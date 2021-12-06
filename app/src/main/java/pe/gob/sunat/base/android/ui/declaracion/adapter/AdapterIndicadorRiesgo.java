package pe.gob.sunat.base.android.ui.declaracion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.DocumentoTransporte;
import pe.gob.sunat.base.android.model.Riesgo;
import pe.gob.sunat.base.android.ui.declaracion.IndicadorRiesgoFragment;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterIndicadorRiesgo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Riesgo> documentoTransportesList;
    private Context context;
    private int animationType = 0;


    OnItemClickListener mOnItemClickListener;

    public AdapterIndicadorRiesgo(List<Riesgo> documentoTransportesList, Context context, int animationType) {
        this.documentoTransportesList = documentoTransportesList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_indicador_riesgo, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, Riesgo item, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            Riesgo docuTrans = documentoTransportesList.get(position);
            view.seleccion.setText(docuTrans.getDesSeleccion());
            view.unidad.setText(docuTrans.getDesUnidad());
            view.listaRiesgo.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, docuTrans, position);
                }
            });
            setAnimation(holder.itemView, position);
        }

    }

    @Override
    public int getItemCount() {
        return documentoTransportesList.size();
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

        public TextView seleccion, unidad;
        public LinearLayout listaRiesgo;

        public OriginalViewHolder(View v) {
            super(v);
            seleccion = (TextView) v.findViewById(R.id.tv_seleccion_riesgo);
            unidad = (TextView) v.findViewById(R.id.tv_unidad_riesgo);
            listaRiesgo = (LinearLayout) v.findViewById(R.id.ll_Lista_Riesgo);


        }
    }
}
