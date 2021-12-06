package pe.gob.sunat.base.android.ui.series.fotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.ui.fotos.AdapterFotos;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterSerieFotos extends RecyclerView.Adapter<AdapterSerieFotos.ViewHolderSerieFotos>{

    List<TestSerieFotos> listSerieFotos;
    private Context context;
    private int animationType = 0;
    EventListener listener;

    public interface EventListener {
        void onEvent(int position);
    }
    public AdapterSerieFotos(List<TestSerieFotos> listSerieFotos, Context context, int animationType, EventListener listener) {
        this.listSerieFotos = listSerieFotos;
        this.context = context;
        this.animationType = animationType;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderSerieFotos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterSerieFotos.ViewHolderSerieFotos viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_serie_foto,parent,false);
        viewHolder = new AdapterSerieFotos.ViewHolderSerieFotos(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSerieFotos.ViewHolderSerieFotos holder, int position) {
        if (holder instanceof ViewHolderSerieFotos){
            ViewHolderSerieFotos view = (ViewHolderSerieFotos) holder;
            TestSerieFotos testSerieFotos = listSerieFotos.get(position);
            view.numSerie.setText(listSerieFotos.get(position).getNumeroSerie());
            view.numSerieFoto.setText(listSerieFotos.get(position).getNumeroFoto());
            view.ivSerieFoto.setImageURI(listSerieFotos.get(position).getImgUriFoto());
            view.ivSerieBorrar.setImageResource(listSerieFotos.get(position).getImgBorrar());
            //Visibilidad Detalle Foto
            view.detalleSerieFoto.setText(listSerieFotos.get(position).getDetalleFoto());
            boolean visible = listSerieFotos.get(position).isVisible();
            view.detalleSerieLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() { return listSerieFotos.size(); }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animationType);
            lastPosition = position;
        }
    }
    public void deleteItem(int position){
        listSerieFotos.remove(position);
        notifyItemRemoved(position);
    }
    public class ViewHolderSerieFotos extends RecyclerView.ViewHolder {

        public TextView numSerie, numSerieFoto, detalleSerieFoto;
        public ImageView ivSerieFoto, ivSerieBorrar, ivSerieDescripcion;

        //Detalle Item Visibilidad
        LinearLayout detalleSerieLayout;

        public ViewHolderSerieFotos(@NonNull View v) {
            super(v);
            numSerie = (TextView) v.findViewById(R.id.tv_num_serie_serie);
            numSerieFoto = (TextView) v.findViewById(R.id.tv_num_serie_foto);
            ivSerieFoto = (ImageView) v.findViewById(R.id.iv_serie_foto);
            ivSerieBorrar = (ImageView) v.findViewById(R.id.iv_foto_serie_borrar);
            ivSerieDescripcion = (ImageView) v.findViewById(R.id.iv_foto_serie_descripcion);
            //Visibilidad Detalle Item
            detalleSerieFoto = (TextView) v.findViewById(R.id.tv_serie_foto_descripcion);
            detalleSerieLayout = (LinearLayout) v.findViewById(R.id.detalleSerieFotoLayout);

            ivSerieDescripcion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestSerieFotos testSerieFotos = listSerieFotos.get(getAdapterPosition());
                    testSerieFotos.setVisible(!testSerieFotos.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            ivSerieBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(), "Aquí se elimina la foto.",Toast.LENGTH_SHORT).show();

                    listener.onEvent(getAdapterPosition());
                }
            });
        }
    }
}
