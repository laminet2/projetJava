package com.database.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Database {
     void openConnexion();
    void closeConnexion();
    ResultSet executeSelect();
    int executeUpdate();
    PreparedStatement getPs();
    void initPreparedStatement(String requeteString);
}
