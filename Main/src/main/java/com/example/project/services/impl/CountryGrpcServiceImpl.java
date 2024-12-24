package com.example.project.services.impl;

import com.example.project.model.CountryGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.repository.CountryGrpcRepository;
import com.example.project.services.CountryGrpcService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
@Service
public class CountryGrpcServiceImpl implements CountryGrpcService {

    private final CountryGrpcRepository countryGrpcRepository;

    @Autowired
    public CountryGrpcServiceImpl(CountryGrpcRepository countryGrpcRepository) {
        this.countryGrpcRepository = countryGrpcRepository;
    }

    @Override
    public CountryGrpc getCountryGrpcById(Long id) {
        return countryGrpcRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CountryGrpc not found with id: " + id));
    }

    @Override
    public List<CountryGrpc> getAllCountryGrpc() {
        return countryGrpcRepository.findAll();
    }


    @Override
    public CountryGrpc createCountry(CountryGrpc countryGrpc) {
        return countryGrpcRepository.save(countryGrpc);
    }

    @Override
    public void deleteCountryById(Long id) {
        if (!countryGrpcRepository.existsById(id)) {
            throw new EntityNotFoundException("Country not found with id: " + id);
        }
        countryGrpcRepository.deleteById(id);
    }
}
