package br.com.interaje.mypersonalwallet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.interaje.mypersonalwallet.R;
import br.com.interaje.mypersonalwallet.dao.BalanceDAO;
import br.com.interaje.mypersonalwallet.dao.BalanceDAOImpl;
import br.com.interaje.mypersonalwallet.databases.Database;
import br.com.interaje.mypersonalwallet.databases.DatabaseHelper;
import br.com.interaje.mypersonalwallet.utils.BalanceDB;

public class MyBalance extends AppCompatActivity {

    private TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);

        // Busca o valor do componente na tela
        balance = (TextView) findViewById(R.id.balance);

        // Converte o valor em String (Texto)
        //String convertedBalance = BalanceDB.getBalance.toString();
        Double value = 0D;
        if (getBalanceFromDB() != null) {
            value = getBalanceFromDB();
        }
        String convertedBalance = value.toString();

        // Atribue o valor (já convertido) ao componente
        balance.setText(convertedBalance);
    }

    private Double getBalanceFromDB() {
        DatabaseHelper helper = new DatabaseHelper(this);
        Database db = new Database(helper);
        BalanceDAO dao = new BalanceDAOImpl();
        return dao.getBalance(db);
    }
}
