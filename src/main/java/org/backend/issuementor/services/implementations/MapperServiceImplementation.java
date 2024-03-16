package org.backend.issuementor.services.implementations;

import org.backend.issuementor.services.MapperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImplementation implements MapperService {
    @Autowired
    private ModelMapper modelMapper;

    public <T, E> E map(T source, Class<E> destination) {
        return modelMapper.map(source, destination);
    }
}
