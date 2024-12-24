package com.example.project.services;

import com.example.project.model.RenovationGrpc;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */

public interface RenovationGrpcService {
    RenovationGrpc getRenovationGrpcById(Long id);
    List<RenovationGrpc> getAllRenovationGrpc();
    RenovationGrpc createRenovation(RenovationGrpc renovationGrpc);
    void deleteRenovationGrpcById(Long id);

}
