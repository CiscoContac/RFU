package pe.gob.sunat.base.android.ui.series.documento;

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

public class AdapterAdjuntoDocumentoControl extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private List<TestAdjuntoDocumento> adjuntoDocumentoList;
    Context context;
    private int animationType = 0;

    public AdapterAdjuntoDocumentoControl(List<TestAdjuntoDocumento> adjuntoDocumentoList, Context context, int animationType) {
        this.adjuntoDocumentoList = adjuntoDocumentoList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_adjunto_documento_control,parent,false);
        view.setOnClickListener(this);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  OriginalViewHolder){
            OriginalViewHolder view = (OriginalViewHolder) holder;
            TestAdjuntoDocumento testAdjuntoDocumento = adjuntoDocumentoList.get(position);
            view.text_nomAdjunto.setText(adjuntoDocumentoList.get(position).getNomAdjunto());
        }
    }

    @Override
    public int getItemCount() {
        return adjuntoDocumentoList.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder{
        public TextView text_nomAdjunto;


        //Detalle Item Visibilidad text_view_nom_adjunto
        LinearLayout AdjuntoDocumentoeLayout;

        public OriginalViewHolder(@NonNull View comprobanteView) {
            super(comprobanteView);


            text_nomAdjunto= (TextView) comprobanteView.findViewById(R.id.text_view_nom_adjunto);

            //Visibilidad Detalle Item
            AdjuntoDocumentoeLayout = (LinearLayout) comprobanteView.findViewById(R.id.detalle_adjunto_documento_conotrol);


        }
    }
}
