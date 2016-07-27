package br.com.interaje.mypersonalwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.interaje.mypersonalwallet.R;
import br.com.interaje.mypersonalwallet.models.Saldo;
import br.com.interaje.mypersonalwallet.utils.BalanceDB;
import io.realm.Realm;
import io.realm.RealmResults;

public class SaqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);
    }

    public void sacar(View v){
        // Cria um objeto que representa o EditText do arquivo XML (Tela)
        EditText valor = (EditText) findViewById(R.id.valorSaque);

        // Pega o valor do EditText (que foi digitado)
        String saque = valor.getText().toString();

        // Converte o valor em Número
        Double saqueC = Double.parseDouble(saque);

        // Verifica se o valor digitado é maior que zero
        if (saqueC > 0) {
            // Soma o saldo
            BalanceDB.getBalance = BalanceDB.getBalance - saqueC;

            // Chama a tela de VerSaldo
            startActivity(new Intent(this, MyBalance.class));
        } else {
            Toast.makeText(this, "Não coloque números negativos", Toast.LENGTH_SHORT).show();
        }

    }

    public void sacarRealm(View v) {
        EditText valor = (EditText) findViewById(R.id.valorSaque);
        Double valorDoUsuario = Double.parseDouble(valor.getText().toString());

        // Recupera o valor do Realm

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Saldo> saldos = realm.where(Saldo.class).findAll();
        int index = saldos.size();
        Double valorDoBanco = saldos.get(index - 1).getValor();

        // Preparando a verificação

        if (valorDoUsuario <= valorDoBanco) {

            // Persistindo o valor no Realm

            Saldo gravarSaldo = new Saldo();
            gravarSaldo.setValor(valorDoBanco - valorDoUsuario);

            Realm gravarRealm = Realm.getDefaultInstance();
            gravarRealm.beginTransaction();

            gravarRealm.copyToRealm(gravarSaldo);

            gravarRealm.commitTransaction();

            startActivity(new Intent(this, MyBalance.class));
        } else {
            Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
        }
    }
}
