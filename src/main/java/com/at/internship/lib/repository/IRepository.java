package com.at.internship.lib.repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IRepository<T, ID> {

    List<T> findAll();

    List<T> findAll(Predicate<? super T> predicate);

    Optional<T> findOne(ID id);

    T save(T c) throws IOException;

    void delete(ID id) throws IOException;

}
