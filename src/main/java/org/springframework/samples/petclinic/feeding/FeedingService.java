package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;



@Service
public class FeedingService {

    private FeedingRepository feedingRepository;

    @Autowired
    public FeedingService(FeedingRepository feedingRepository){
        this.feedingRepository = feedingRepository;
    }

    @Transactional
    public List<Feeding> getAll(){
        return feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return null;
    }

    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.getFeedingType(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        Pet pet = p.getPet();
        FeedingType feedingType = p.getFeedingType();
        PetType goodPetType = feedingType.getPetType();
        if(pet.getType() != goodPetType){
            throw new UnfeasibleFeedingException();
        }
        else{
            feedingRepository.save(p);
            return p;
        }
    }

    
}
