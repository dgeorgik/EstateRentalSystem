package com.example.project.services.impl;

import com.example.project.model.CityGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.repository.CityGrpcRepository;
import com.example.project.services.CityGrpcService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
@Service
public class CityGrpcServiceImpl implements CityGrpcService {

    private final CityGrpcRepository cityGrpcRepository;

    @Autowired
    public CityGrpcServiceImpl(CityGrpcRepository cityGrpcRepository) {
        this.cityGrpcRepository = cityGrpcRepository;
    }

    @Override
    public CityGrpc getCityGrpcById(Long id) {
        return cityGrpcRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CityGrpc not found with id: " + id));
    }

    @Override
    public List<CityGrpc> getAllCityGrpc() {
        return cityGrpcRepository.findAll();
    }


    @Override
    public CityGrpc createCityGrpc(CityGrpc cityGrpc) {
        return cityGrpcRepository.save(cityGrpc);
    }

    @Override
    public void deleteCityGrpcById(Long id) {
        if (!cityGrpcRepository.existsById(id)) {
            throw new EntityNotFoundException("City not found with id: " + id);
        }
        cityGrpcRepository.deleteById(id);
    }
}
