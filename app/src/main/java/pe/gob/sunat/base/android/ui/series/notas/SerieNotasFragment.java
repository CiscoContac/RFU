package pe.gob.sunat.base.android.ui.series.notas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.series.fotos.SerieFotosFragment;

public class SerieNotasFragment extends Fragment implements Injectable {

    public static final String TAG = SerieNotasFragment.class.getSimpleName();
    Unbinder unbinder;

    String Notas = "Nota";
    String nota;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public static SerieNotasFragment newInstance(Bundle bundle) {
        SerieNotasFragment fragment = new SerieNotasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listado_serie_notas, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("NOTAS");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(FragmentItemsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Animated FAB
        FloatingActionButton fabSerieOpen = getView().findViewById(R.id.fab_serie_opened);
        FloatingActionButton fabSerieDelete = getView().findViewById(R.id.fab_serie_delete);
        FloatingActionButton fabSerieAdd = getView().findViewById(R.id.fab_serie_add);

        TextView tvNotas = getView().findViewById(R.id.tv_serie_notas);

        Animation rotateOpen = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.rotate_open_anim);
        Animation rotateClose = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.rotate_close_anim);
        Animation fromBottom = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.from_bottom_anim);
        Animation toBottom = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.to_bottom_anim);

        //FUNCIONALIDADES FABS
        fabSerieOpen.setOnClickListener(new View.OnClickListener() {

            boolean clicked;

            @Override
            public void onClick(View view) {
                setVisibility(clicked);
                setAnimation(clicked);
                setClickable(clicked);
                clicked = !clicked;
            }

            public void setVisibility(Boolean clicked){
                if(!clicked){

                    fabSerieAdd.setVisibility(View.VISIBLE);
                    fabSerieDelete.setVisibility(View.VISIBLE);
                } else {
                    fabSerieAdd.setVisibility(View.GONE);
                    fabSerieDelete.setVisibility(View.GONE);
                }
            }

            public void setAnimation(Boolean clicked){
                if (!clicked){
                    fabSerieAdd.startAnimation(fromBottom);
                    fabSerieDelete.startAnimation(fromBottom);
                    fabSerieOpen.startAnimation(rotateOpen);
                } else {
                    fabSerieAdd.startAnimation(toBottom);
                    fabSerieDelete.startAnimation(toBottom);
                    fabSerieOpen.startAnimation(rotateClose);
                }
            }

            private void setClickable(Boolean clicked){
                if (clicked){
                    fabSerieAdd.setClickable(false);
                    fabSerieDelete.setClickable(false);
                } else {
                    fabSerieAdd.setClickable(true);
                    fabSerieDelete.setClickable(true);
                }
            }
        });

        fabSerieAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveNota();
                //readNota();
                openConfirmacionNota();
            }

            public void saveNota(){
                try {
                    FileOutputStream fos = getActivity().openFileOutput(Notas, Context.MODE_PRIVATE);
                    nota = tvNotas.getText().toString();
                    fos.write(nota.getBytes());
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }

            public void readNota(){
                try {
                    FileInputStream fin = getActivity().openFileInput(Notas);
                    InputStreamReader inputStream = new InputStreamReader(fin);
                    BufferedReader bufferedReader = new BufferedReader(inputStream);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line= bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }
                    fin.close();
                    inputStream.close();
                    tvNotas.setText(stringBuilder.toString());
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }

            public void openConfirmacionNota(){
                DialogConfirmacionSerieNotasFragment confirmacionSerieNota = new DialogConfirmacionSerieNotasFragment();
                confirmacionSerieNota.setTargetFragment(SerieNotasFragment.this, 1);
                confirmacionSerieNota.show(getFragmentManager(), "DialogConfirmacionSerieNota");
            }

        });

        fabSerieDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getBaseContext().
                        getApplicationContext(),"Aquí se elimina la nota.",Toast.LENGTH_SHORT).show();
                        openEliminacionSerieNota();
            }

            public void openEliminacionSerieNota(){
                DialogEliminacionSerieNotasFragment eliminacionSerieNota = new DialogEliminacionSerieNotasFragment();
                eliminacionSerieNota.setTargetFragment(SerieNotasFragment.this, 1);
                eliminacionSerieNota.show(getFragmentManager(), "DialogConfirmacionSerieNota");
            }
        });
    }
}
