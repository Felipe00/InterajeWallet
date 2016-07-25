package br.com.interaje.mypersonalwallet.dao;

import br.com.interaje.mypersonalwallet.databases.Database;

/**
 * Created by rayquaza on 25/07/16.
 */
public interface BalanceDAO {

    Double getBalance(Database database);

    void intertBalance(Double value, Database database);

}
