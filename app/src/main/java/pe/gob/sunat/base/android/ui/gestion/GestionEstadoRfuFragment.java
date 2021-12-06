package pe.gob.sunat.base.android.ui.gestion;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.util.Tools;
import pe.gob.sunat.base.android.viewmodel.GestionEstadoRfuViewModel;

public class GestionEstadoRfuFragment extends Fragment implements Injectable {

    public static final String TAG = GestionEstadoRfuFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    @BindView(R.id.edit_text_gestion_estado_rfu_fecha_inicio)
    EditText editTextFechaInicio;

    @BindView(R.id.edit_text_gestion_estado_rfu_fecha_fin)
    EditText editTextFechaFin;

    @BindView(R.id.btnGestionEstadoRfuConsultarGrafico)
    Button btnGestionEstadoRfuConsultarGrafico;

    @BindView(R.id.image_button_gestion_rfu_calendario_fecha_inicial)
    ImageButton imageButtonCalendarioIncial;

    @BindView(R.id.image_button_gestion_rfu_calendario_fecha_final)
    ImageButton imageButtonCalendarioFinal;

    Unbinder unbinder;
    private View view;

    private static DatePickerDialog.OnDateSetListener onDateSetListener;
    private int dia, mes, ao, hora,minutos;

    private GestionEstadoRfuViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public static GestionEstadoRfuFragment newInstance(Bundle bundle) {
        GestionEstadoRfuFragment fragment = new GestionEstadoRfuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gestion_estado_rfu, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("ESTADO DE RECONOCIMIENTO FÍSICO DE LA DAM");

        btnGestionEstadoRfuConsultarGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setGestionEstadoRfuGrafico(bundle);
            }
        });

        imageButtonCalendarioIncial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePickerLightInicio();
            }
        });

        imageButtonCalendarioFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePickerLightFinal();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        AutoCompleteTextView autoCompleteTextViewFuncionarios = getView().
                findViewById(R.id.autoCompleteTextViewFuncionariosGestionRfu);


        List<String> listFuncionario = new ArrayList<String>(Arrays.asList(
                getResources().getStringArray(R.array.lista_empleados)));


        ArrayAdapter arrayAdapterFuncionario = new ArrayAdapter(requireContext(),
                R.layout.dropdown_funcionarios,
                R.id.textViewTFuncionarios,
                listFuncionario);

        autoCompleteTextViewFuncionarios.setAdapter(arrayAdapterFuncionario);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GestionEstadoRfuViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnFragmentIterationListener {
        void setGestionEstadoRfuGrafico(Bundle bundle);
    }

    private void dialogDatePickerLightInicio(){
        Calendar calendarInicio = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) ->{
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    long date = calendar.getTimeInMillis();

                    editTextFechaInicio.setText(Tools.getFormattedDateSimple(date));
                },
                calendarInicio.get(Calendar.YEAR),
                calendarInicio.get(Calendar.MONTH),
                calendarInicio.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.setThemeDark(false);
        datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        datePickerDialog.show(getActivity().getFragmentManager(),"Fecha inicial del periodo" +
                "de asignación.");
    }
    private void dialogDatePickerLightFinal(){
        Calendar calendarFinal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) ->{
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    long date = calendar.getTimeInMillis();

                    editTextFechaFin.setText(Tools.getFormattedDateSimple(date));
                },
                calendarFinal.get(Calendar.YEAR),
                calendarFinal.get(Calendar.MONTH),
                calendarFinal.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.setThemeDark(false);
        datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        datePickerDialog.show(getActivity().getFragmentManager(),"Fecha final del periodo" +
                "de asignación.");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if(context instanceof GestionEstadoRfuFragment.OnFragmentIterationListener){
            listener = (GestionEstadoRfuFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }
}