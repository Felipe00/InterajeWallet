package br.com.interaje.mypersonalwallet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.interaje.mypersonalwallet.R;
import br.com.interaje.mypersonalwallet.dao.BalanceDAO;
import br.com.interaje.mypersonalwallet.dao.BalanceDAOImpl;
import br.com.interaje.mypersonalwallet.databases.Database;
import br.com.interaje.mypersonalwallet.databases.DatabaseHelper;
import br.com.interaje.mypersonalwallet.models.Saldo;
import br.com.interaje.mypersonalwallet.utils.BalanceDB;
import io.realm.Realm;
import io.realm.RealmResults;

public class DepositoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);
    }

    public void depositar(View v){
        EditText valor = (EditText) findViewById(R.id.valorDeposito);
        Double userValue = Double.parseDouble(valor.getText().toString());
        Double dbValue = 0D;

        // Recupera o valor do Realm

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Saldo> saldos = realm.where(Saldo.class).findAll();
        int index = saldos.size();
        Double valorFinal = saldos.get(index - 1).getValor();

        // Persistindo o valor no Realm

        Saldo gravarSaldo = new Saldo();
        gravarSaldo.setValor(userValue + valorFinal);

        Realm gravarRealm = Realm.getDefaultInstance();
        gravarRealm.beginTransaction();

        gravarRealm.copyToRealm(gravarSaldo);

        gravarRealm.commitTransaction();

        Double balanceTotal = userValue + dbValue;

        setBalanceInDB(balanceTotal);

        startActivity(new Intent(this, MyBalance.class));
    }

    /* public void depositar(View v){
        EditText valor = (EditText) findViewById(R.id.valorDeposito);

        Double userValue = Double.parseDouble(valor.getText().toString());

        Double dbValue = 0D;
        if (getBalanceFromDB() != null) {
            dbValue = getBalanceFromDB();
        }

        Double balanceTotal = userValue + dbValue;

        setBalanceInDB(balanceTotal);

        startActivity(new Intent(this, MyBalance.class));
    }*/

    private Double getBalanceFromDB() {
        DatabaseHelper helper = new DatabaseHelper(this);
        Database db = new Database(helper);
        BalanceDAO dao = new BalanceDAOImpl();
        return dao.getBalance(db);
    }

    private void setBalanceInDB(Double value) {
        DatabaseHelper helper = new DatabaseHelper(this);
        Database db = new Database(helper);
        BalanceDAO dao = new BalanceDAOImpl();
        dao.intertBalance(value, db);
    }
}
