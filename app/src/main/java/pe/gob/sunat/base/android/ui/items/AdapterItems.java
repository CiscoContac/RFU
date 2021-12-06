package pe.gob.sunat.base.android.ui.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolderItems>
                implements View.OnClickListener {

    List<TestItems> listItems;
    private Context context;
    private int animationType = 0;

    //Para los Onclick de los items del Recycler
    private View.OnClickListener itemListener;

    public  AdapterItems(ArrayList<TestItems> listItems, Context context, int animationType) {
        this.listItems = listItems;
        this.context = context;
        this.animationType = animationType;
    }

    @Override
    public AdapterItems.ViewHolderItems onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterItems.ViewHolderItems viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_list_item,parent,false);
        //Listener OnClick Recycler
        view.setOnClickListener(this);
        viewHolder = new ViewHolderItems(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItems.ViewHolderItems holder, int position) {
        if(holder instanceof AdapterItems.ViewHolderItems){
            ViewHolderItems view = (ViewHolderItems) holder;
            TestItems testItems = listItems.get(position);
            view.numItem.setText(listItems.get(position).getNumeroItem());
            view.numItemComponente.setText(listItems.get(position).getNumeroItemComponente());
            view.ivItem.setImageResource(listItems.get(position).getImgItem());
            view.ivItemDetalle.setImageResource(listItems.get(position).getImgitemDetalle());
            view.detalleItem.setText(listItems.get(position).getDetalleItem());

            //Visibilidad Detalle Item
            boolean visible = listItems.get(position).isVisible();
            view.detalleLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
            setAnimation(holder.itemView, position);

        }
    }

    @Override
    public int getItemCount() { return listItems.size(); }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View itemView, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(itemView, on_attach ? position : -1, animationType);
            lastPosition = position;
        }
    }

    //Método setOnClick Recycler
    public void setOnClickListener(View.OnClickListener itemListener){
        this.itemListener = itemListener;
    }
    //Método OnClick Recycler
    @Override
    public void onClick(View v) {
        if (itemListener!=null){
            itemListener.onClick(v);
        }

    }

    public class ViewHolderItems extends RecyclerView.ViewHolder {

        public TextView numItem, numItemComponente, detalleItem;
        public ImageView ivItem, ivItemDetalle;

        //Detalle Item Visibilidad
        LinearLayout detalleLayout;

        public  ViewHolderItems(@NonNull View v){
            super(v);
            numItem = (TextView) v.findViewById(R.id.tv_num_item);
            numItemComponente = (TextView) v.findViewById(R.id.tv_item_componente);
            ivItem = (ImageView)  v.findViewById(R.id.iv_item);
            ivItemDetalle = (ImageView) v.findViewById(R.id.iv_detalle_item);
            detalleItem = (TextView) v.findViewById(R.id.tv_subpartida_nacional);
            //Visibilidad Detalle Item
            detalleLayout = (LinearLayout) v.findViewById(R.id.detalleItemLayout);

            ivItemDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestItems testItems = listItems.get(getAdapterPosition());
                    testItems.setVisible(!testItems.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}

