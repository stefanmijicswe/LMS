package org.singidunum.backend.service;

import org.singidunum.backend.model.Address;
import org.singidunum.backend.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Iterable<Address> findAllAddresses()
    {
        return addressRepository.findAll();
    }

    public Address findAddressById(Long id)
    {
        return addressRepository.findById(id).orElse(null);
    }

    public Address save(Address address)
    {
        return addressRepository.save(address);
    }

    public void  delete(Address address)
    {
        addressRepository.delete(address);
    }

    public void deleteAll()
    {
        addressRepository.deleteAll();
    }
}
