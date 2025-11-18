package org.singidunum.backend.service;


import org.singidunum.backend.model.Place;
import org.singidunum.backend.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Iterable<Place>  getAllPlaces()
    {
        return placeRepository.findAll();
    }

    public Place getPlaceById(Long id)
    {
        return placeRepository.findById(id).orElse(null);
    }
    public void delete(Long id) {
        placeRepository.deleteById(id);
    }

    public Place save(Place place)
    {
        return placeRepository.save(place);
    }

    public void deleteAll()
    {
        placeRepository.deleteAll();
    }
}
