package com.mad.backend.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mad.backend.model.Museum;

public interface MuseumRepository extends MongoRepository<Museum, String> {
    public Museum findByName(String name);

    public List<Museum> findByCity(String city);
}