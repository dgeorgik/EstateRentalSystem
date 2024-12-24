package com.example.project.controller;

import com.example.project.model.CityGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.services.CityGrpcService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */

@RestController
@RequestMapping("/city")
public class CityGrpcController {

    @Autowired
    private CityGrpcService cityGrpcService;

    @GetMapping("/{id}")
    public HttpEntity<CityGrpc> getCityGrpcById(@PathVariable Long id) {
        CityGrpc cityGrpc = cityGrpcService.getCityGrpcById(id);
        cityGrpc.add(linkTo(methodOn(CityGrpcController.class).getCityGrpcById(id)).withSelfRel());

        return new ResponseEntity<>(cityGrpc, HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<CollectionModel<EntityModel<CityGrpc>>> getAllCityGrpc() {
        List<CityGrpc> cityGrpcList = cityGrpcService.getAllCityGrpc();

        List<EntityModel<CityGrpc>> cityModels = cityGrpcList.stream()
                .map(cityGrpc -> EntityModel.of(cityGrpc,
                        linkTo(methodOn(CityGrpcController.class).getCityGrpcById(cityGrpc.getId())).withSelfRel()))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<CityGrpc>> collectionModel = CollectionModel.of(cityModels);

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @PostMapping
    public CityGrpc createCityGrpc(@RequestBody CityGrpc cityGrpc) {
        return cityGrpcService.createCityGrpc(cityGrpc);
    }

    @DeleteMapping("/{id}")
    public void deleteCityGrpcById(@PathVariable Long id) {
        cityGrpcService.deleteCityGrpcById(id);
    }

}
