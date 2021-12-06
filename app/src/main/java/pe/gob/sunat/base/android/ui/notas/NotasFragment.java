package pe.gob.sunat.base.android.ui.notas;

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
import pe.gob.sunat.base.android.ui.fotos.FotosFragment;

public class NotasFragment extends Fragment implements Injectable {

    public static final String TAG = NotasFragment.class.getSimpleName();
    OnFragmentIterationListener listener;


    //private FragmentItemsViewModel mViewModel;
    String Notas = "Nota";
    String nota;

    Unbinder unbinder;
    private View view;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public static NotasFragment newInstance(Bundle bundle) {
        NotasFragment fragment = new NotasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listado_notas, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("NOTAS");

        return view;
    }

    public interface OnFragmentIterationListener {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(FragmentItemsViewModel.class);

        // TODO: Use the ViewModel
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Animated FAB
        FloatingActionButton fabOpen = getView().findViewById(R.id.fab_open);
        FloatingActionButton fabDelete = getView().findViewById(R.id.fab_delete);
        FloatingActionButton fabAdd = getView().findViewById(R.id.fab_add);

        TextView tvNotas = getView().findViewById(R.id.tv_notas);

        Animation rotateOpen = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.rotate_open_anim);
        Animation rotateClose = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.rotate_close_anim);
        Animation fromBottom = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.from_bottom_anim);
        Animation toBottom = AnimationUtils.loadAnimation(getActivity().getBaseContext().
                getApplicationContext(), R.anim.to_bottom_anim);

        //FUNCIONALIDADES FABS

        fabOpen.setOnClickListener(new View.OnClickListener() {

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
                    fabAdd.setVisibility(View.VISIBLE);
                    fabDelete.setVisibility(View.VISIBLE);
                } else {
                    fabAdd.setVisibility(View.GONE);
                    fabDelete.setVisibility(View.GONE);
                }
            }

            public void setAnimation(Boolean clicked){
                if (!clicked){
                    fabAdd.startAnimation(fromBottom);
                    fabDelete.startAnimation(fromBottom);
                    fabOpen.startAnimation(rotateOpen);
                } else {
                    fabAdd.startAnimation(toBottom);
                    fabDelete.startAnimation(toBottom);
                    fabOpen.startAnimation(rotateClose);
                }
            }

            private void setClickable(Boolean clicked){
                if (clicked){
                    fabAdd.setClickable(false);
                    fabDelete.setClickable(false);
                } else {
                    fabAdd.setClickable(true);
                    fabDelete.setClickable(true);
                }
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(getActivity().getBaseContext().
                            getApplicationContext(),"Se ha guardado la nota.",Toast.LENGTH_SHORT).show();


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
                DialogConfirmacionNotasFragment confirmacionNota = new DialogConfirmacionNotasFragment();
                confirmacionNota.setTargetFragment(NotasFragment.this, 1);
                confirmacionNota.show(getFragmentManager(), "DialogConfirmacionNota");
            }

        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getBaseContext().
                        getApplicationContext(),"Aquí se elimina la nota.",Toast.LENGTH_SHORT).show();
                        openEliminacionNota();
            }

            public void openEliminacionNota(){
                DialogEliminacionNotasFragment eliminacionNota = new DialogEliminacionNotasFragment();
                eliminacionNota.setTargetFragment(NotasFragment.this, 1);
                eliminacionNota.show(getFragmentManager(), "DialogConfirmacionNota");
            }
        });



    }

}