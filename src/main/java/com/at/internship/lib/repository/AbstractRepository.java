package com.at.internship.lib.repository;

import com.at.internship.lib.serialization.ISerializer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T, ID> implements IRepository<T, ID> {
    private List<T> table;
    private final ISerializer<T> serializer;

    protected AbstractRepository(ISerializer<T> serializer) {
        this.serializer = serializer;
        this.table = serializer.deserialize();
    }

    public List<T> findAll() {
        return table.stream().map(this::cloneRow).collect(Collectors.toList());
    }

    public List<T> findAll(Predicate<? super T> predicate) {
        if(predicate == null)
            return findAll();
        return table.stream().filter(predicate).map(this::cloneRow).collect(Collectors.toList());
    }

    public Optional<T> findOne(ID id) {
        return table
                .stream()
                .filter(a -> Objects.equals(getId(a), id))
                .findFirst();
    }

    public synchronized T save(T c) throws IOException {
        T clone = cloneRow(c);
        if(getId(clone) == null)
            assignId(clone);

        int index = table.indexOf(clone);
        if(index > -1)
            table.set(index, clone);
        else
            table.add(clone);

        serializer.serialize(table);

        return cloneRow(clone);
    }

    public void delete(ID id) throws IOException {
        table = table.stream().filter(c -> !getId(c).equals(id)).collect(Collectors.toList());
        serializer.serialize(table);
    }

    protected abstract T cloneRow(T source);

    protected abstract ID getId(T source);

    protected abstract void assignId(T newRow);
}
