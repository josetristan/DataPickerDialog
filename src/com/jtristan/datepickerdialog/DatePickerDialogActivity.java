package com.jtristan.datepickerdialog;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DatePickerDialogActivity extends Activity implements View.OnClickListener{
		
	private TextView tvFechaHora;
	private TextView tvHora;
	int año;
	int mes;
	int dia;
	int hora;
	int minuto;
	
	static final int DATE_DIALOG_ID = 0;	 
	static final int TIME_DIALOG_ID = 1;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tvFechaHora = (TextView)this.findViewById(R.id.tvFecha);        
        tvFechaHora.setOnClickListener(this);    
        
        final Calendar calendar = Calendar.getInstance();
        año = calendar.get(Calendar.YEAR);
        mes =  calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
                       
        actualizarLaFechaEnTextView();
        
        tvHora = (TextView)this.findViewById(R.id.tvHora);
        tvHora.setOnClickListener(this);
        
        hora = calendar.get(Calendar.HOUR);
        minuto = calendar.get(Calendar.MINUTE);
        
        actualizarLaHoraEnTextView();                     
        
    }


	private void actualizarLaFechaEnTextView() {
		tvFechaHora.setText(new StringBuilder()
									.append(dia).append("/")
									.append(mes + 1).append("/")
									.append(año));									
		
	}
	
	private void actualizarLaHoraEnTextView() {
		tvHora.setText(new StringBuilder()
							.append(hora).append(":")
							.append(minuto));							
		
	}

	@Override
	public void onClick(View view) {		   
		switch (view.getId()){
			case R.id.tvFecha:		
				this.showDialog(DATE_DIALOG_ID);
				break;
			case R.id.tvHora:
				this.showDialog(TIME_DIALOG_ID);
				break;
		}
	}
	
	private DatePickerDialog.OnDateSetListener dateSetListener = new  DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			año = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			
			actualizarLaFechaEnTextView();
		}
	};
    
	//Evento que salta cuando el usuario acepta la hora que ha marcado en el TimePicker
	private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			hora = hourOfDay;
			minuto = minute;			
			actualizarLaHoraEnTextView();
		}
		
	};
 
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id){
			case DATE_DIALOG_ID:				
				return new DatePickerDialog(this,dateSetListener,año,mes,dia);
			case TIME_DIALOG_ID:
				return new TimePickerDialog(this, timeSetListener,hora, minuto, true);
		}
		return null;
	}
   
    
}