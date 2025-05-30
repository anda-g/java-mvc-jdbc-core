package model.dao;

import java.util.List;

public interface Repository <T,D>{
    List<T> findAll();
    T findById(int id);
    void save(T t);
    void delete(T t);
}
