package pe.gob.sunat.base.android.ui.declaracion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.Contenedor;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterRecepcionMercanciasList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Contenedor> contenedorList;
    private Context context;
    private int animationType = 0;

    public AdapterRecepcionMercanciasList(List<Contenedor> contenedorList, Context context, int animationType) {
        this.contenedorList = contenedorList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_recepcion_mercancias_list, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            Contenedor contenedor = contenedorList.get(position);
            view.numContenedor.setText(contenedor.getNumContenedor());
            view.cantidadBultosRM.setText(String.valueOf(contenedor.getCantidadBultosRM()));
            //Visibilidad del detalle del contenedor
            boolean visible = contenedorList.get(position).isVisible();
            view.detalleLayout.setVisibility(visible ? View.VISIBLE : View.GONE);

            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return contenedorList.size();
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

        public TextView numContenedor;
        public TextView cantidadBultosRM;
        public ImageView detalleRecepcionMercancia;
        //Detalle visible
        LinearLayout detalleLayout;

        public OriginalViewHolder(View v) {
            super(v);
            numContenedor = (TextView) v.findViewById(R.id.text_view_num_contenedor_rm);
            cantidadBultosRM = (TextView) v.findViewById(R.id.text_view_cantidad_bultos_rm);
            detalleRecepcionMercancia = (ImageView) v.findViewById(R.id.image_button_deploy_detalle_recepcion_mercancia);
            detalleLayout = (LinearLayout) v.findViewById(R.id.detalleRecepcionMercanciaLayout);
            //Visibilidad del detalle del contenedor
            detalleRecepcionMercancia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contenedor contenedor = contenedorList.get(getAdapterPosition());
                    contenedor.setVisible(!contenedor.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
