package com.mad.backend.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mad.backend.model.*;

public interface MuseumRepository extends MongoRepository<Museum, String> {
    public List<Museum> findByName(String name);
    public List<Museum> findByAddress(Address address);
}