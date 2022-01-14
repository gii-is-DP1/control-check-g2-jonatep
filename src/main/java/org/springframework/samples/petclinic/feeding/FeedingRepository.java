package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FeedingRepository extends CrudRepository<Feeding, Integer>{
    List<Feeding> findAll();

    @Query("SELECT feedingType FROM FeedingType feedingType")
    List<FeedingType> findAllFeedingTypes();


    @Query("SELECT feedingType FROM FeedingType feedingType WHERE feedingType.name LIKE ?1")
    FeedingType getFeedingType(String name);

    Optional<Feeding> findById(int id);
    Feeding save(Feeding p);
}
