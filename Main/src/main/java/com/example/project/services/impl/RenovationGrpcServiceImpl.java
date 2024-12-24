package com.example.project.services.impl;

import com.example.project.model.RenovationGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.repository.RenovationGrpcRepository;
import com.example.project.services.RenovationGrpcService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */

@Service
public class RenovationGrpcServiceImpl implements RenovationGrpcService {

    private final RenovationGrpcRepository renovationGrpcRepository;

    @Autowired
    public RenovationGrpcServiceImpl(RenovationGrpcRepository renovationGrpcRepository) {
        this.renovationGrpcRepository = renovationGrpcRepository;
    }

    @Override
    public RenovationGrpc getRenovationGrpcById(Long id) {
        return renovationGrpcRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RenovationGrpc not found with id: " + id));
    }

    @Override
    public List<RenovationGrpc> getAllRenovationGrpc() {
        return renovationGrpcRepository.findAll();
    }

    @Override
    public RenovationGrpc createRenovation(RenovationGrpc renovationGrpc) {
        return renovationGrpcRepository.save(renovationGrpc);
    }

    @Override
    public void deleteRenovationGrpcById(Long id) {
        if (!renovationGrpcRepository.existsById(id)) {
            throw new EntityNotFoundException("Renovation not found with id: " + id);
        }
        renovationGrpcRepository.deleteById(id);
    }
}
