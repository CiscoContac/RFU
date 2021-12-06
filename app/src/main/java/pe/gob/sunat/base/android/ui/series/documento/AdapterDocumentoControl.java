package pe.gob.sunat.base.android.ui.series.documento;

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

public class AdapterDocumentoControl extends RecyclerView.Adapter <RecyclerView.ViewHolder> implements View.OnClickListener{

    private List<TestDocumentoControl> documentoControlList;
    private Context context;
    protected  int animationType= 0;

    private View.OnClickListener itemListener;

    public AdapterDocumentoControl(List<TestDocumentoControl> documentoControlList, Context context, int animationType) {
        this.documentoControlList = documentoControlList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_documento_control,parent,false);
        view.setOnClickListener(this);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder){
            OriginalViewHolder view = (OriginalViewHolder) holder;
            TestDocumentoControl testDocumentoControl = documentoControlList.get(position);
            view.nombreIdentificacion.setText(documentoControlList.get(position).getNumDoc());
            view.numIdentificacion.setText(documentoControlList.get(position).getNumSerie());
            view.docExonerado.setText(documentoControlList.get(position).getEntidadNombre());

            boolean visible = documentoControlList.get(position).isVisible();
            view.detalleDocumentoeLayout.setVisibility(visible ? View.VISIBLE : View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return documentoControlList.size();
    }

    private int lasPosition= -1 ;
    private boolean on_attach = true;

    public void setOnClickListener(View.OnClickListener itemListener){
        this.itemListener = itemListener;
    }

    @Override
    public void onClick(View v) {
        if (itemListener!=null){
            itemListener.onClick(v);
        }
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreIdentificacion, numIdentificacion, docExonerado;

        public ImageView ivDocumentoDetalle;
        //Detalle Item Visibilidad
        LinearLayout detalleDocumentoeLayout;

        public OriginalViewHolder(@NonNull View comprobanteView) {
            super(comprobanteView);


            nombreIdentificacion = (TextView) comprobanteView.findViewById(R.id.text_view_identificacion_document);
            numIdentificacion = (TextView) comprobanteView.findViewById(R.id.text_view_num_identificacion_documento);
            docExonerado = (TextView) comprobanteView.findViewById(R.id.text_view_documento_Exonerado);
            ivDocumentoDetalle = (ImageView) comprobanteView.findViewById(R.id.image_button_lista_doc_contr);
            //Visibilidad Detalle Item
            detalleDocumentoeLayout = (LinearLayout) comprobanteView.findViewById(R.id.detalleDocumentoControl);

            ivDocumentoDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestDocumentoControl testDocumentoControl = documentoControlList.get(getAdapterPosition());
                    testDocumentoControl.setVisible(!testDocumentoControl.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }


}
