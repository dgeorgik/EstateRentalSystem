package com.example.project.controller;

import com.example.project.model.RenovationGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.services.RenovationGrpcService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */

@RestController
@RequestMapping("/renovation")
public class RenovationGrpcController {

    @Autowired
    private RenovationGrpcService renovationGrpcService;

    @GetMapping("/{id}")
    public HttpEntity<RenovationGrpc> getRenovationGrpcById(@PathVariable Long id) {
        RenovationGrpc renovationGrpc = renovationGrpcService.getRenovationGrpcById(id);
        renovationGrpc.add(linkTo(methodOn(RenovationGrpcController.class).getRenovationGrpcById(id)).withSelfRel());

        return new ResponseEntity<>(renovationGrpc, HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<List<RenovationGrpc>> getAllRenovationGrpc() {
        List<RenovationGrpc> renovationGrpcList = renovationGrpcService.getAllRenovationGrpc();
        renovationGrpcList.forEach(renovationGrpc ->
                renovationGrpc.add(linkTo(methodOn(RenovationGrpcController.class).getRenovationGrpcById(renovationGrpc.getId())).withSelfRel())
        );

        return new ResponseEntity<>(renovationGrpcList, HttpStatus.OK);
    }

}
