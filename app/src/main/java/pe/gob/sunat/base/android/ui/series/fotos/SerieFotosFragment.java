package pe.gob.sunat.base.android.ui.series.fotos;

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
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;

public class SerieFotosFragment extends Fragment implements DialogDescripcionSerieFotosFragment.EnviarSerieDescripcion, Injectable,AdapterSerieFotos.EventListener {

    public static final String TAG = SerieFotosFragment.class.getSimpleName();
    Unbinder unbinder;

    @BindView(R.id.recycler_view_serie_fotos)
    RecyclerView recyclerViewSerieFotos;

    public View view;
    public AdapterSerieFotos adapterSerieFotos;
    private SerieFotosViewModel serieFotosViewModel;

    public ArrayList<TestSerieFotos> listaSerieFotos = new ArrayList<>();
    //Detalle Fotos
    public String detalleSerieFoto;

    //TEMPORAL IMAGEN FOTO
    ImageView imgSerieFoto;
    int serieFoto;
    Bitmap bitmap;
    Uri miPath, resultUri;
    String path;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public static SerieFotosFragment newInstance(Bundle bundle) {
        SerieFotosFragment fragment = new SerieFotosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_serie_fotos, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTADO DE FOTOS");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        serieFotosViewModel = new ViewModelProvider(this).get(SerieFotosViewModel.class);
        //TODO: User the ViewModel
        agregarSerieFotos();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //ACCIONES FAB PARA AGREGAR IMAGENES Y FOTOS
        FloatingActionButton fabSerieCamara = getView().findViewById(R.id.fab_serie_camara);
        FloatingActionButton fabSerieFotos = getView().findViewById(R.id.fab_serie_fotos);

        imgSerieFoto = getView().findViewById(R.id.iv_serie_foto);

        //TOMAR FOTO
        fabSerieCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //METODO PARA ACTIVAR CÁMARA
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);
            }
        });

        //CARGAR IMAGEN
        fabSerieFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(getContext(), SerieFotosFragment.this);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        SerieFotosFragment.super.onActivityResult(requestCode, resultCode, data);
        //Tomar Foto
        if (requestCode == 10) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            getImageUri(getContext(), bitmap);
            startCrop(Uri.parse(path));
        }
        //Cargar Imagen
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK){
            Uri imageuri = CropImage.getPickImageResultUri(getContext(),data);
            if (CropImage.isReadExternalStoragePermissionsRequired(getContext(), imageuri)){
                resultUri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            } else {
                startCrop(imageuri);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                resultUri = result.getUri();
                openDialogSerieDescripcion();
            }
        }
    }

    //Dialog Descripcion
    public void openDialogSerieDescripcion(){
        DialogDescripcionSerieFotosFragment descripcionSerieFotos = new DialogDescripcionSerieFotosFragment();
        descripcionSerieFotos.setTargetFragment(SerieFotosFragment.this, 1);
        descripcionSerieFotos.show(getFragmentManager(), "DialogSerieDescripcion");
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

    public void agregarSerieFotos(){
        recyclerViewSerieFotos = getView().findViewById(R.id.recycler_view_serie_fotos);
        recyclerViewSerieFotos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewSerieFotos.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerViewSerieFotos.setHasFixedSize(true);

        adapterSerieFotos = new AdapterSerieFotos(listaSerieFotos, this.getContext(), ItemAnimation.FADE_IN,this);
        recyclerViewSerieFotos.setAdapter(adapterSerieFotos);
    }
    public void onEvent(int position) {
        Log.d(TAG, "INVOCAR DIALOG");

        DialogEliminacionSerieFotosFragment descripcionSerieFotos = new DialogEliminacionSerieFotosFragment(adapterSerieFotos,position);
        descripcionSerieFotos.setTargetFragment(SerieFotosFragment.this, 1);
        descripcionSerieFotos.show(getFragmentManager(), "DialogSerieDescripcion");
    }
    //DIALOG DESCRIPCION
    @Override
    public void enviarSerieDatos(String mensaje) {
        Log.d(TAG,"sendInput: found incoming input: " + mensaje);
        listaSerieFotos.add(new TestSerieFotos("Serie 1","Foto 1", resultUri, R.drawable.ic_delete, R.drawable.ic_down,mensaje));
        adapterSerieFotos.notifyDataSetChanged();

    }
}
