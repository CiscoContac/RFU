package pe.gob.sunat.base.android.ui.fotos;

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

import java.util.ArrayList;
import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterFotos extends RecyclerView.Adapter<AdapterFotos.ViewHolderFotos> {

    List<TestFotos> listFotos;
    private Context context;
    private int animationType = 0;
    //LISTENER ELIMINAR FOTOS
    EventListener listener;

    public interface EventListener {
        void onEvent(int position);
    }

    public AdapterFotos(ArrayList<TestFotos> listFotos, Context context, int animationType, EventListener listener) {
        this.listFotos = listFotos;
        this.context = context;
        this.animationType = animationType;
        this.listener = listener;
    }

    @Override
    public ViewHolderFotos onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterFotos.ViewHolderFotos viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_item_foto,parent,false);
        viewHolder = new ViewHolderFotos(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFotos.ViewHolderFotos holder, int position) {
        if(holder instanceof ViewHolderFotos){
            ViewHolderFotos view = (ViewHolderFotos) holder;
            TestFotos testFotos = listFotos.get(position);
            view.numSerie.setText(listFotos.get(position).getNumeroSerie());
            view.numItem.setText(listFotos.get(position).getNumeroItem());
            view.numFoto.setText(listFotos.get(position).getNumeroFoto());
            view.ivFoto.setImageURI(listFotos.get(position).getImgUriFoto());
            view.ivBorrar.setImageResource(listFotos.get(position).getImgBorrar());
            view.ivDescripcion.setImageResource(listFotos.get(position).getImgDescripcion());

            //Visibilidad Detalle Foto
            view.detalleFoto.setText(listFotos.get(position).getDetalleFoto());
            boolean visible = listFotos.get(position).isVisible();
            view.detalleLayout.setVisibility(visible ? View.VISIBLE : View.GONE);

            setAnimation(holder.itemView, position);

        }
    }

    @Override
    public int getItemCount() { return listFotos.size(); }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animationType);
            lastPosition = position;
        }
    }
    //ELIMINAR FOTOS
    public void deleteItem(int position){
        listFotos.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolderFotos extends RecyclerView.ViewHolder {

        public TextView numSerie, numItem, numFoto, detalleFoto;
        public ImageView ivFoto, ivBorrar, ivDescripcion;

        //Detalle Item Visibilidad
        LinearLayout detalleLayout;

        public ViewHolderFotos(@NonNull View v) {
            super(v);
            numSerie= (TextView) v.findViewById(R.id.tv_num_serie);
            numItem= (TextView) v.findViewById(R.id.tv_num_item);
            numFoto= (TextView) v.findViewById(R.id.tv_num_foto);
            ivFoto= (ImageView) v.findViewById(R.id.iv_item_foto);
            ivBorrar= (ImageView) v.findViewById(R.id.iv_foto_borrar);
            ivDescripcion= (ImageView) v.findViewById(R.id.iv_foto_descripcion);
            //Visibilidad Detalle Item
            detalleFoto = (TextView) v.findViewById(R.id.tv_descripcion);
            detalleLayout = (LinearLayout) v.findViewById(R.id.detalleItemLayout);

            ivDescripcion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestFotos testFotos = listFotos.get(getAdapterPosition());
                    testFotos.setVisible(!testFotos.isVisible());
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
