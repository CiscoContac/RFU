package pe.gob.sunat.base.android.ui.registrorfu;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.fotos.AdapterFotos;
import pe.gob.sunat.base.android.ui.fotos.DialogDescripcionFotosFragment;
import pe.gob.sunat.base.android.ui.fotos.DialogEliminacionFotosFragment;
import pe.gob.sunat.base.android.ui.fotos.FotosFragment;
import pe.gob.sunat.base.android.ui.fotos.TestFotos;
import pe.gob.sunat.base.android.ui.registrorfu.adapter.AdapterListaFotoList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.AgregarFotoViewModel;

public class AgregarFotoFragment extends Fragment implements Injectable, DialogDescripcionFotosFragment.EnviarDescripcion,AdapterListaFotoList.EventListener {

    public static final String TAG = AgregarFotoFragment.class.getSimpleName();

    AgregarFotoFragment.OnFragmentIterationListener listener;

    ImageView imagelistafoto;

    Unbinder unbinder;
    private View view;

    Bitmap bitmap;
    public Uri miPath, resultUri;
    String path;

    private AgregarFotoViewModel mViewModel;

    @BindView(R.id.btn_foto_lista_regresar)
    Button btn_regresar;

    @BindView(R.id.recycler_diligencia_foto)
    RecyclerView recyclerViewFotos;

    private AdapterListaFotoList adapterFotos;
    public ArrayList<TestListaFoto> listaDiligenciaFotos = new ArrayList<>();

    public static AgregarFotoFragment newInstance(Bundle bundle) {

        AgregarFotoFragment fragment = new AgregarFotoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_agregar_foto, container, false);
        unbinder = ButterKnife.bind(this,view);

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDatoComplementarioFragmemt(bundle);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AgregarFotoViewModel.class);
        // TODO: Use the ViewModel
        agregarFotos();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton fabListaCamara = getView().findViewById(R.id.fab_nueva_camara);

        //tomar foto

        fabListaCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //act. camara
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        AgregarFotoFragment.super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            getImageUri(getContext(), bitmap);
            startCrop(Uri.parse(path));
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                resultUri=result.getUri();
                openDialogDescripcion();
            }
        }
    }

    //Dialog Descripción
    public void openDialogDescripcion() {
        DialogDescripcionDiligenciaFotosFragment descripcionFotos = new DialogDescripcionDiligenciaFotosFragment();
        descripcionFotos.setTargetFragment(AgregarFotoFragment.this,1);
        descripcionFotos.show(getFragmentManager(), "DialogDescripcion");
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void startCrop(Uri imageuri){
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .setAspectRatio(1,1)
                .start(getContext(), this);
    }

    private void agregarFotos(){
        recyclerViewFotos = getView().findViewById(R.id.recycler_diligencia_foto);
        recyclerViewFotos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewFotos.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerViewFotos.setHasFixedSize(true);

        adapterFotos = new AdapterListaFotoList(listaDiligenciaFotos, this.getContext(), ItemAnimation.FADE_IN,this);
        recyclerViewFotos.setAdapter(adapterFotos);
    }

    public void onEvent(int position) {
        Log.d(TAG, "INVOCAR DIALOG");

        DialogEliminacionDiligenciaFotosFragment eliminacionFotos = new DialogEliminacionDiligenciaFotosFragment(adapterFotos, position);
        eliminacionFotos.setTargetFragment(AgregarFotoFragment.this, 1);
        eliminacionFotos.show(getFragmentManager(), "DialogEliminacionDiligenciaFotosFragment");
    }

    //DIALOG DESCRIPCION
    @Override
    public void enviarDatos(String mensaje) {
        Log.d(TAG, "sendInput: found incoming input: " + mensaje);
        listaDiligenciaFotos.add(new TestListaFoto("Foto 4",  R.drawable.ic_delete,R.drawable.ic_down, resultUri, mensaje));
        adapterFotos.notifyDataSetChanged();
    }

    public interface OnFragmentIterationListener{
        void setDatoComplementarioFragmemt(Bundle bundle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof AgregarFotoFragment.OnFragmentIterationListener) {
            listener = (AgregarFotoFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

}