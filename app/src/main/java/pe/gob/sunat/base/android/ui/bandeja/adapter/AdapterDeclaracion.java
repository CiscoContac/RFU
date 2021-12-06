package pe.gob.sunat.base.android.ui.bandeja.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.Declaracion;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterDeclaracion extends RecyclerView.Adapter<AdapterDeclaracion.DeclaracionViewHolder> implements Filterable {

    private List<Declaracion> declaraciones;
    private List<Declaracion> declaracionesCompletas;
    private Context context;
    private int animationType = 0;

    private int lastPosition = -1;
    private boolean on_attach = true;

    public  AdapterDeclaracion(List<Declaracion> lista, Context context, int animationType){
        this.declaraciones = lista;
        this.declaracionesCompletas = new ArrayList<>(lista);
        this.context = context;
        this.animationType = animationType;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public AdapterDeclaracion.DeclaracionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_declaracion, parent, false);
        return new DeclaracionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDeclaracion.DeclaracionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return declaraciones.size();
    }

    public class DeclaracionViewHolder extends RecyclerView.ViewHolder {
        public DeclaracionViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Declaracion> filtrados = new ArrayList<>();

            filtrados.addAll(declaracionesCompletas);

            FilterResults results = new FilterResults();
            results.values = filtrados;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            declaraciones.clear();
            declaraciones.addAll((List<Declaracion>) results.values);
            notifyDataSetChanged();
        }
    };
    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animationType);
            lastPosition = position;
        }
    }
}
