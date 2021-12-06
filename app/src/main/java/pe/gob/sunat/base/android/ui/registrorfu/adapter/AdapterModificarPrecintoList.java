package pe.gob.sunat.base.android.ui.registrorfu.adapter;

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
import pe.gob.sunat.base.android.model.Contenedor;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterInformesSiniList;
import pe.gob.sunat.base.android.ui.registrorfu.TestPrecinto;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterModificarPrecintoList extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<TestPrecinto> testPrecintos;
    private Context context;
    private int animationType = 0;

    private View.OnClickListener itemListener;

    public AdapterModificarPrecintoList(List<TestPrecinto> testPrecintos, Context context, int animationType) {
        this.testPrecintos = testPrecintos;
        this.context = context;
        this.animationType = animationType;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_modificar_precinto_list, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            TestPrecinto testPrecinto = testPrecintos.get(position);
            view.numContenedor.setText(testPrecintos.get(position).getNumContenedor());
            view.precinActual.setText(testPrecintos.get(position).getUltPrecintoActual());
            view.precinModif.setText(testPrecintos.get(position).getUltprecintoModif());

            boolean visible = testPrecintos.get(position).isVisible();
            view.detalleLayout.setVisibility(visible ? View.VISIBLE: View.GONE);

            setAnimation(holder.itemView, position);
        }

    }

    @Override
    public int getItemCount() {
        return testPrecintos.size();
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

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView numContenedor;
        public TextView precinActual;
        public TextView precinModif;
        public ImageView imagebuttondeploya;

        LinearLayout detalleLayout;

        public OriginalViewHolder(View v) {
            super(v);
            numContenedor = (TextView) v.findViewById(R.id.text_num_contenedor);
            precinActual = (TextView) v.findViewById(R.id.text_ultimo_oprecinto_actual);
            precinModif = (TextView) v.findViewById(R.id.text_ultimo_precinto_modificado);
            imagebuttondeploya = (ImageView) itemView.findViewById(R.id.image_button_deploy_a);

            detalleLayout = (LinearLayout) itemView.findViewById(R.id.detalle_modificar_preciento);

            imagebuttondeploya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TestPrecinto testPrecinto = testPrecintos.get(getAdapterPosition());
                    testPrecinto.setVisible(!testPrecinto.isVisible());
                    notifyItemChanged(getAdapterPosition());


                }
            });
        }
    }
}
