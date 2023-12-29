package com.database.services;

import java.util.ArrayList;

public interface Repository<T> {
    int insert(T data);
     ArrayList<T> findAll();
     T findById(int id);

}
