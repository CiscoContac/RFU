package pe.gob.sunat.base.android.ui.declaracion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.DocumentoDigitalArchivo;
import pe.gob.sunat.base.android.model.DocumentoTransporte;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;

public class AdapterDocDigitalizadoList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DocumentoDigitalArchivo> documentoTransportesList;
    private Context context;
    private int animationType = 0;
    private AdapterDocuList adapterDocTransList;
    public AdapterDocDigitalizadoList(List<DocumentoDigitalArchivo> documentoTransportesList, Context context, int animationType) {
        this.documentoTransportesList = documentoTransportesList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_docu_digitalizado, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            DocumentoDigitalArchivo docuTrans = documentoTransportesList.get(position);
            view.tipoRequ.setText(docuTrans.getDesRequerimiento());

            view.imageUpDown.setOnClickListener(z-> {
                if(view.listaDocu.getVisibility() == View.VISIBLE){
                    view.listaDocu.setVisibility(View.GONE);
                    view.imageUpDown.setBackgroundResource(R.drawable.ic_arrowdown);
                }else {
                    view.imageUpDown.setBackgroundResource(R.drawable.ic_arrowup);
                    view.listaDocu.setVisibility(View.VISIBLE);
                }
                //notifyItemChanged(position);
                setAnimation(holder.itemView, position);
            });
            setAnimation(holder.itemView, position);

            adapterDocTransList = new AdapterDocuList(documentoTransportesList, context.getApplicationContext(), ItemAnimation.FADE_IN);
            view.recyclerView.setAdapter(adapterDocTransList);

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

        public TextView tipoRequ,tipoDocu,observacion;
        RecyclerView recyclerView;

        LinearLayout listaDocu;

        ImageView imageUpDown;

        public OriginalViewHolder(View v) {
            super(v);
            tipoRequ = (TextView) v.findViewById(R.id.txtTipoReq);
            tipoDocu = (TextView) v.findViewById(R.id.txtTipoDoc);
            observacion =  (TextView) v.findViewById(R.id.txtTipoDoc);
            recyclerView = (RecyclerView)v.findViewById(R.id.recycler_lista_docu);
            imageUpDown =  (ImageView) v.findViewById(R.id.imageVie_Up_down);
            listaDocu =  (LinearLayout)    v.findViewById(R.id.ll_lista_docu);

            imageUpDown.setBackgroundResource(R.drawable.ic_arrowdown);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setHasFixedSize(true);

        }
    }
}
