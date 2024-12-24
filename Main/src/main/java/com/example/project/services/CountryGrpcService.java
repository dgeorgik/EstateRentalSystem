package com.example.project.services;

import com.example.project.model.CountryGrpc;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */

public interface CountryGrpcService {
    CountryGrpc getCountryGrpcById(Long id);
    List<CountryGrpc> getAllCountryGrpc();
    CountryGrpc createCountry(CountryGrpc countryGrpc);
    void deleteCountryById(Long id);

}
