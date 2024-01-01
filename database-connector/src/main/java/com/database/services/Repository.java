package com.database.services;

import java.util.ArrayList;

public interface Repository<T> {
     int insertOrUpdate(T data);
     ArrayList<T> findAll();
     T findById(int id);
     boolean archiver(T data);

}
