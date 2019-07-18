package com.example.zgadnij;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	int liczba, liczbauz, wynik=0; //deklaracja liczb
	SharedPreferences sp;
	SharedPreferences.Editor edytor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sp = getSharedPreferences("com.example.zgadnij", Context.MODE_PRIVATE);
		edytor = sp.edit();
		TextView textView2 = (TextView)findViewById(R.id.textView3);
		textView2.setText("Rekord: "+ sp.getInt("najlepszy", 100));
	}
	
public void nowaGra(View view){
	Random rand = new Random(); //liczba losowa rand
	liczba = rand.nextInt((100-0)+1)+0; //losowanie liczby Int od 0 do 100
	wynik = 0;
	TextView textView = (TextView)findViewById(R.id.textView2);
	textView.setText("Wynik: "+wynik);
	EditText editText = (EditText)findViewById(R.id.editText1);
	editText.setText("");
}

public void zgadnij(View view){
	wynik++;
	EditText editText = (EditText)findViewById(R.id.editText1);
	liczbauz = Integer.parseInt(editText.getText().toString());
	String wiadomosc="";
	
	if(liczba>liczbauz){
		wiadomosc = "Wpisz wiêksz¹ liczbê!";
	}
	
	else if(liczba<liczbauz){
		wiadomosc = "Wpisz mniejsz¹ liczbê!";
	}
	
	else if(liczba==liczbauz){
		wiadomosc = "Brawo! Odgad³eœ liczbê!";
		if(wynik < sp.getInt("najlepszy", 100)){
			edytor.putInt("najlepszy", wynik);
			edytor.commit();
			TextView textView2 = (TextView)findViewById(R.id.textView3);
			textView2.setText("Rekord: "+ sp.getInt("najlepszy", 100));
		}
	}
	
	int duration = Toast.LENGTH_SHORT;
	
	Context context = getApplicationContext();
	
	Toast toast = Toast.makeText(context, wiadomosc, duration);
	toast.show();
	TextView textView = (TextView)findViewById(R.id.textView2);
	textView.setText("Wynik: "+wynik);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
