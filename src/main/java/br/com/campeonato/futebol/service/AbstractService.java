package br.com.campeonato.futebol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ramon on 07-Sep-16.
 */
public abstract class AbstractService<E, I extends Serializable, R extends CrudRepository<E, I>> implements DefaultService<E, I> {

    @Autowired
    private R repository;

    @Override
    public E save(E entity) {
        return this.repository.save(entity);
    }

    @Override
    public E findOne(I id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<E> findAll() {
        return (List<E>) this.repository.findAll();
    }

    @Override
    public void delete(I id) {
        this.repository.delete(id);
    }

    public R getRepository() {
        return repository;
    }

}
