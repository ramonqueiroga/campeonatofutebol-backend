package br.com.campeonato.futebol.service;

import java.util.List;

/**
 * Created by Ramon on 07-Sep-16.
 */
public interface DefaultService<E, I> {
    E save(E entity);
    E findOne(I id);
    List<E> findAll();
    void delete(I id);
}
