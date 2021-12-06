package pe.gob.sunat.base.android.ui.fotos;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.items.DetalleItemFragment;
import pe.gob.sunat.base.android.ui.series.fotos.DialogDescripcionSerieFotosFragment;
import pe.gob.sunat.base.android.ui.series.fotos.DialogEliminacionSerieFotosFragment;
import pe.gob.sunat.base.android.ui.series.fotos.SerieFotosFragment;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;

public class FotosFragment extends Fragment implements Injectable, DialogDescripcionFotosFragment.EnviarDescripcion,AdapterFotos.EventListener {

    public static final String TAG = FotosFragment.class.getSimpleName();
    OnFragmentIterationListener listener;

    @BindView(R.id.recycler_view_fotos)
    RecyclerView recyclerViewFotos;

    Unbinder unbinder;
    private View view;
    private AdapterFotos adapterFotos;
    private FotosViewModel fotosViewModel;

    public ArrayList<TestFotos> listaFotos = new ArrayList<>();
    //Detalle Fotos
    public String detallefoto;

    //TEMPORAL IMAGEN FOTO
    ImageView imgFoto;
    int Foto;
    Bitmap bitmap;
    public Uri miPath, resultUri;
    String path;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public static FotosFragment newInstance(Bundle bundle) {
        FotosFragment fragment = new FotosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_fotos, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTADO DE FOTOS");
        setHasOptionsMenu(true);
        return view;
    }

    public interface OnFragmentIterationListener {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fotosViewModel = new ViewModelProvider(this).get(FotosViewModel.class);
        // TODO: Use the ViewModel
        agregarFotos();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //ACCIONES FAB PARA AGREGAR IMAGENES Y FOTOS
        FloatingActionButton fabCamara = getView().findViewById(R.id.fab_camara);
        FloatingActionButton fabFotos = getView().findViewById(R.id.fab_fotos);


        //TOMAR FOTO
        fabCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //METODO PARA ACTIVAR CÁMARA
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);

            }
        });

        //CARGAR IMAGEN
        fabFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(getContext(), FotosFragment.this);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        FotosFragment.super.onActivityResult(requestCode, resultCode, data);
        //Tomar foto
        if (requestCode == 10) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            getImageUri(getContext(), bitmap);
            startCrop(Uri.parse(path));
        }
        //Cargar Imagen
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK){
            Uri imageuri = CropImage.getPickImageResultUri(getContext(),data);
            if (CropImage.isReadExternalStoragePermissionsRequired(getContext(),imageuri)){
                resultUri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            } else {
                startCrop(imageuri);
            }
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
        DialogDescripcionFotosFragment descripcionFotos = new DialogDescripcionFotosFragment();
        descripcionFotos.setTargetFragment(FotosFragment.this,1);
        descripcionFotos.show(getFragmentManager(), "DialogDescripcion");
    }

    private void startCrop(Uri imageuri){
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .setAspectRatio(1,1)
                .start(getContext(), this);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void agregarFotos(){
        recyclerViewFotos = getView().findViewById(R.id.recycler_view_fotos);
        recyclerViewFotos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewFotos.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerViewFotos.setHasFixedSize(true);

        adapterFotos = new AdapterFotos(listaFotos, this.getContext(), ItemAnimation.FADE_IN,this);
        recyclerViewFotos.setAdapter(adapterFotos);

    }
    public void onEvent(int position) {
        Log.d(TAG, "INVOCAR DIALOG");

        DialogEliminacionFotosFragment eliminacionFotos = new DialogEliminacionFotosFragment(adapterFotos, position);
        eliminacionFotos.setTargetFragment(FotosFragment.this, 1);
        eliminacionFotos.show(getFragmentManager(), "DialogSerieDescripcion");
    }

    //DIALOG DESCRIPCION
    @Override
    public void enviarDatos(String mensaje) {
        Log.d(TAG, "sendInput: found incoming input: " + mensaje);
        listaFotos.add(new TestFotos("Serie 1","Item 1","Foto 4", resultUri, R.drawable.ic_delete,R.drawable.ic_down, mensaje));
        adapterFotos.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        inflater.inflate(R.menu.menu_opciones_adicionales_series, menu);
    }

}