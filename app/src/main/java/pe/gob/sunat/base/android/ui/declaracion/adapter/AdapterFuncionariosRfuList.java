package pe.gob.sunat.base.android.ui.declaracion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.Contenedor;
import pe.gob.sunat.base.android.model.FuncionarioRFU;
import pe.gob.sunat.base.android.util.ItemAnimation;

public class AdapterFuncionariosRfuList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FuncionarioRFU> funcionarioRFUList;
    private Context context;
    private int animationType = 0;

    public AdapterFuncionariosRfuList(List<FuncionarioRFU> funcionarioRFUList, Context context, int animationType) {
        this.funcionarioRFUList = funcionarioRFUList;
        this.context = context;
        this.animationType = animationType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_funcionarios_rfu_list, parent, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            FuncionarioRFU funcionarioRFU = funcionarioRFUList.get(position);
            view.numeroRegistroNombesApellidos.setText(
                    funcionarioRFU.getNumeroRegistro()+"-" +
                            funcionarioRFU.getNombres() +" " +
                            funcionarioRFU.getApellidos()
            );
            view.tipoFuncionario.setText(funcionarioRFU.getTipoFuncionario());
            view.correoElectronico.setText(funcionarioRFU.getEmail());
            view.telefonoPrincipal.setText(funcionarioRFU.getTelefonos().get(0));
            view.telefonoSecundario.setText(funcionarioRFU.getTelefonos().get(1));

            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return funcionarioRFUList.size();
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

        public TextView numeroRegistroNombesApellidos;
        public TextView tipoFuncionario;
        public TextView correoElectronico;
        public TextView telefonoPrincipal;
        public TextView telefonoSecundario;
        public ImageView fotoPerfil;



        public OriginalViewHolder(View v) {
            super(v);
            numeroRegistroNombesApellidos = (TextView) v.findViewById(
                    R.id.text_view_numero_registro_nombres_apellidos);
            tipoFuncionario = (TextView) v.findViewById(R.id.text_view_tipo_funcionario);
            correoElectronico = (TextView) v.findViewById(R.id.text_view_correo_electronico);
            telefonoPrincipal = (TextView) v.findViewById(R.id.text_view_telefono_principal);
            telefonoSecundario = (TextView) v.findViewById(R.id.text_view_telefono1_secundario);
            fotoPerfil = (ImageView) v.findViewById(R.id.image_view_foto_funcionario);

        }
    }
}
