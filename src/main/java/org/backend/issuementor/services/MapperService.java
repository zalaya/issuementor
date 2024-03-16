package org.backend.issuementor.services;

public interface MapperService {
    <T, E> E map(T source, Class<E> destination);
}
