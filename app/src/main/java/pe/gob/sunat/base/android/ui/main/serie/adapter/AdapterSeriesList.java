package pe.gob.sunat.base.android.ui.main.serie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.Serie;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterSeriesList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Serie> serieList;
    private Context context;
    private int animationType = 0;

    public AdapterSeriesList(List<Serie> serieList, Context context, int animationType) {
        this.serieList = serieList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_series_list, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof OriginalViewHolder){
            OriginalViewHolder view = (OriginalViewHolder) holder;
            Serie serie = serieList.get(position);
            view.numSerie.setText(serie.getNumero());
            view.descripcionSerie.setText(serie.getDescripcion());
            view.subpartidaNacional.setText(serie.getSubpartidaNacional());

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

    public class OriginalViewHolder extends RecyclerView.ViewHolder{
        public TextView numSerie;
        public TextView descripcionSerie;
        public TextView subpartidaNacional;

        public OriginalViewHolder(@NonNull View itemView) {
            super(itemView);
            numSerie = (TextView) itemView.findViewById(R.id.text_view_numero_serie);
            descripcionSerie = (TextView) itemView.findViewById(R.id.text_view_descripcion_serie);
            subpartidaNacional = (TextView) itemView.findViewById(R.id.text_view_subpartida_nacional);
        }
    }
}
