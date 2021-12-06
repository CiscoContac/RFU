package pe.gob.sunat.base.android.ui.registrorfu.adapter;

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

import java.util.EventListener;
import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.ui.fotos.AdapterFotos;
import pe.gob.sunat.base.android.ui.fotos.TestFotos;
import pe.gob.sunat.base.android.ui.registrorfu.TestListaFoto;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterListaFotoList extends RecyclerView.Adapter<AdapterListaFotoList.ViewHolderFotos> {

    List<TestListaFoto> listaFotoList;
    private Context context;
    private int animationType = 0;
    //LISTENER ELIMINAR FOTOS
    EventListener listener;

    public interface EventListener {
        void onEvent(int position);
    }

    public AdapterListaFotoList(List<TestListaFoto> listaFotoList, Context context, int animationType, EventListener listener) {
        this.listaFotoList = listaFotoList;
        this.context = context;
        this.animationType = animationType;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterListaFotoList.ViewHolderFotos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterListaFotoList.ViewHolderFotos viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_diligencia_foto,parent,false);
        viewHolder = new ViewHolderFotos(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaFotoList.ViewHolderFotos holder, int position) {
        if(holder instanceof AdapterListaFotoList.ViewHolderFotos){
            AdapterListaFotoList.ViewHolderFotos view = (AdapterListaFotoList.ViewHolderFotos) holder;
            TestListaFoto testListaFoto = listaFotoList.get(position);
            view.numFoto.setText(listaFotoList.get(position).getNumeroFoto());
            view.ivFoto.setImageURI(listaFotoList.get(position).getImgUriFoto());
            view.ivBorrar.setImageResource(listaFotoList.get(position).getImgBorrar());
            view.ivDescripcion.setImageResource(listaFotoList.get(position).getImgDescripcion());

            //Visibilidad Detalle Foto
            view.detalleFoto.setText(listaFotoList.get(position).getDetalleFoto());
            boolean visible = listaFotoList.get(position).isVisible();
            view.detalleLayout.setVisibility(visible ? View.VISIBLE : View.GONE);

            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() { return listaFotoList.size(); }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animationType);
            lastPosition = position;
        }
    }

    public void deleteItem(int position){
        listaFotoList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolderFotos extends RecyclerView.ViewHolder {

        public TextView numFoto, detalleFoto;
        public ImageView ivFoto, ivBorrar, ivDescripcion;

        //Detalle Item Visibilidad
        LinearLayout detalleLayout;

        public ViewHolderFotos(@NonNull View v) {
            super(v);
            numFoto= (TextView) v.findViewById(R.id.tv_num_diligencia_foto);
            ivFoto= (ImageView) v.findViewById(R.id.iv_diligencia_foto);
            ivBorrar= (ImageView) v.findViewById(R.id.iv_diligencia_foto_borrar);
            ivDescripcion= (ImageView) v.findViewById(R.id.iv_diligencia_foto_descripcion);
            //Visibilidad Detalle Item
            detalleFoto = (TextView) v.findViewById(R.id.tv_diligencia_foto_descripcion);
            detalleLayout = (LinearLayout) v.findViewById(R.id.detalleDiligenciaFotoLayout);

            ivDescripcion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestListaFoto testListaFoto = listaFotoList.get(getAdapterPosition());
                    testListaFoto.setVisible(!testListaFoto.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            ivBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(), "Aquí se elimina la foto.",Toast.LENGTH_SHORT).show();
                    listener.onEvent(getAdapterPosition());
                }
            });
        }
    }
}
