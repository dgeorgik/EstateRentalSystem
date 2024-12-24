package com.example.project.services;

import com.example.project.model.CityGrpc;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
public interface CityGrpcService {
    CityGrpc getCityGrpcById(Long id);
    List<CityGrpc> getAllCityGrpc();
    CityGrpc createCityGrpc(CityGrpc cityGrpc);
    void deleteCityGrpcById(Long id);

}
