package br.com.interaje.mypersonalwallet.activities;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.interaje.mypersonalwallet.R;
import br.com.interaje.mypersonalwallet.utils.BalanceDB;

public class SaqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);
    }

    public void sacar(View v){
        EditText valor;
        valor = (EditText) findViewById(R.id.valorSaque);
        String saque = valor.getText().toString();
        Double saqueC = Double.parseDouble(saque);
        BalanceDB.getBalance = BalanceDB.getBalance - saqueC;
        startActivity(new Intent(this, MyBalance.class));
    }
}
