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
import pe.gob.sunat.base.android.model.DocumentoDigitalArchivo;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterDocuList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DocumentoDigitalArchivo> documentoTransportesList;
    private Context context;
    private int animationType = 0;
    private AdapterDocTransList adapterDocTransList;
    public AdapterDocuList(List<DocumentoDigitalArchivo> documentoTransportesList, Context context, int animationType) {
        this.documentoTransportesList = documentoTransportesList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_listado_docu, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            DocumentoDigitalArchivo docuTrans = documentoTransportesList.get(position);
            view.tipoDocu.setText(docuTrans.getDesTipodocumento());
            view.observacion.setText(docuTrans.getObsOperador());
            view.txtFechaAtencion.setText(docuTrans.getFecAtereq());
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

        public TextView tipoDocu,observacion,txtFechaAtencion;


        public OriginalViewHolder(View v) {
            super(v);
            tipoDocu = (TextView) v.findViewById(R.id.txtTipoDoc);
            observacion =  (TextView) v.findViewById(R.id.txtTipoDoc);
            txtFechaAtencion = (TextView)v.findViewById(R.id.txtatencion);


        }
    }
}
