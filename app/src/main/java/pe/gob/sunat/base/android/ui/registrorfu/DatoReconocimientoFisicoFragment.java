package pe.gob.sunat.base.android.ui.registrorfu;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;

//import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.declaracion.DeclaracionFragment;
import pe.gob.sunat.base.android.util.Tools;
import pe.gob.sunat.base.android.viewmodel.DatoReconocimientoFisicoViewModel;

public class DatoReconocimientoFisicoFragment extends Fragment implements Injectable {

    public static final String TAG = DatoReconocimientoFisicoFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    @BindView(R.id.edit_text_fecha_reconocimiento)
    EditText edit_text_fecha_reconocimiento;

    @BindView(R.id.edit_text_hora_reconocimiento)
    EditText edit_text_hora_reconocimiento;

    @BindView(R.id.canti_bultos_reconocimiento)
    EditText canti_bultos_reconocimiento;

    @BindView(R.id.descripcion_reconocimiento)
    EditText descripcion_reconocimiento;

    @BindView(R.id.btn_dato_reconocimiento_siguiente)
    Button btn_dato_reconocimiento_siguiente;

    @BindView(R.id.button_calendario_fecha)
    ImageButton btn_calendario_fecha;

    @BindView(R.id.button_hora)
    ImageButton btn_hora;

    Unbinder unbinder;

    private View view;
    private static DatePickerDialog.OnDateSetListener onDateSetListener;
    private int dia, mes, ao, hora,minutos;

    private DatoReconocimientoFisicoViewModel mViewModel;


    public static DatoReconocimientoFisicoFragment newInstance(Bundle bundle) {
        DatoReconocimientoFisicoFragment fragment = new DatoReconocimientoFisicoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dato_reconocimiento_fisico, container, false);
        unbinder = ButterKnife.bind(this, view);

        btn_calendario_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialogDatePickerLight();
            }
        });

        btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c=Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);

                edit_text_hora_reconocimiento.setText(hora+":"+minutos);
            }
        });

        btn_dato_reconocimiento_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDespachoAuxliarFragment(bundle);
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DatoReconocimientoFisicoViewModel.class);
        // TODO: Use the ViewModel

    }

    public interface OnFragmentIterationListener {
        void setDespachoAuxliarFragment(Bundle bundle);
    }


    private void dialogDatePickerLight() {
        Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    long date = calendar.getTimeInMillis();

                    edit_text_fecha_reconocimiento.setText(Tools.getFormattedDateSimple(date));
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //datePicker.setMinDate(cal);
        //datePicker.setMaxDate(cur_calender);
        datePicker.show(getActivity().getFragmentManager(),"Fecha Reconocimiento Físico");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof DatoReconocimientoFisicoFragment.OnFragmentIterationListener) {
            listener = (DatoReconocimientoFisicoFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }
}