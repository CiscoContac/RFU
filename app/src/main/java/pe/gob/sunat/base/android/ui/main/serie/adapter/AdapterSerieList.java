package pe.gob.sunat.base.android.ui.main.serie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pe.gob.sunat.base.android.model.SerieItem;

public class AdapterSerieList extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements Filterable {
    private Context ctx;
    private SerieItem serieItem;
    private int animation_type =0;


    public AdapterSerieList(Context context, SerieItem serieItem, int animation_type) {
        ctx = context;
        this.serieItem = serieItem;
        this.serieItem.setSerieItemList(serieItem.getSerieItemList());
        this.animation_type = animation_type;
    }

    public void clean(){
        serieItem.getSerieItemList().clear();
    }




    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
