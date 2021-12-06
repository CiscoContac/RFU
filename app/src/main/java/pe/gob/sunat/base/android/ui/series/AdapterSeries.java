package pe.gob.sunat.base.android.ui.series;

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
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterSeries extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private List<TestSeries> serieList;
    private Context context;
    private int animationType = 0;

    //Para los Onclick de los items del Recycler
    private View.OnClickListener itemListener;

    public AdapterSeries(List<TestSeries> serieList, Context context, int animationType){
        this.serieList = serieList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_serie, parent, false);
        //Listener OnClick Recycler
        view.setOnClickListener(this);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof OriginalViewHolder){
            OriginalViewHolder view = (OriginalViewHolder) holder;
            TestSeries testSeries = serieList.get(position);
            view.numSerie.setText(serieList.get(position).getNumero());
            view.descripcionSerie.setText(serieList.get(position).getDescripcion());
            view.subpartidaNacional.setText(serieList.get(position).getSubpartidaNacional());

            //Visibilidad Detalle Item
            boolean visible = serieList.get(position).isVisible();
            view.detalleLayout.setVisibility(visible ? View.VISIBLE : View.GONE);

            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return serieList.size();
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animationType);
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

    public class OriginalViewHolder extends RecyclerView.ViewHolder{
        public TextView numSerie;
        public TextView descripcionSerie;
        public TextView subpartidaNacional;
        public ImageView ivSerieDetalle;

        //Detalle Item Visibilidad
        LinearLayout detalleLayout;

        public OriginalViewHolder(@NonNull View itemView) {
            super(itemView);
            numSerie = (TextView) itemView.findViewById(R.id.text_view_numero_serie);
            descripcionSerie = (TextView) itemView.findViewById(R.id.text_view_descripcion_serie);
            subpartidaNacional = (TextView) itemView.findViewById(R.id.text_view_subpartida_nacional);
            ivSerieDetalle = (ImageView) itemView.findViewById(R.id.iv_serie_detalle);
            //Visibilidad Detalle Item
            detalleLayout = (LinearLayout) itemView.findViewById(R.id.detalleSerieLayout);

            ivSerieDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestSeries testSeries = serieList.get(getAdapterPosition());
                    testSeries.setVisible(!testSeries.isVisible());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
