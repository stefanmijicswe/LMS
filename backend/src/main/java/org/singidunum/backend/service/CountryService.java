package org.singidunum.backend.service;


import org.singidunum.backend.model.Country;
import org.singidunum.backend.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public Iterable<Country>  getAllCountries()
    {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id)
    {
        return countryRepository.findById(id).orElse(null);
    }

    public Country saveCountry(Country country)
    {
        return countryRepository.save(country);
    }

    public void deleteCountryById(Long id)
    {
        countryRepository.deleteById(id);
    }

    public void deleteAllCountries()
    {
        countryRepository.deleteAll();
    }
}
