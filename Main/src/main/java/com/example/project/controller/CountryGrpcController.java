package com.example.project.controller;

import com.example.project.model.CountryGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.services.CountryGrpcService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */


@RestController
@RequestMapping("/country")
public class CountryGrpcController {

    @Autowired
    private CountryGrpcService countryGrpcService;

    @GetMapping("/{id}")
    public HttpEntity<CountryGrpc> getCountryGrpcById(@PathVariable Long id) {
        CountryGrpc countryGrpc = countryGrpcService.getCountryGrpcById(id);
        countryGrpc.add(linkTo(methodOn(CountryGrpcController.class).getCountryGrpcById(id)).withSelfRel());

        return new ResponseEntity<>(countryGrpc, HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<List<CountryGrpc>> getAllCountryGrpc() {
        List<CountryGrpc> countryGrpcList = countryGrpcService.getAllCountryGrpc();
        countryGrpcList.forEach(countryGrpc ->
                countryGrpc.add(linkTo(methodOn(CountryGrpcController.class).getCountryGrpcById(countryGrpc.getId())).withSelfRel())
        );
        return new ResponseEntity<>(countryGrpcList, HttpStatus.OK);
    }

    @PostMapping
    public CountryGrpc createCountryGrpc(@RequestBody CountryGrpc countryGrpc) {
        return countryGrpcService.createCountry(countryGrpc);
    }

    @DeleteMapping("/{id}")
    public void deleteCountryGrpcById(@PathVariable Long id) {
        countryGrpcService.deleteCountryById(id);
    }


}
