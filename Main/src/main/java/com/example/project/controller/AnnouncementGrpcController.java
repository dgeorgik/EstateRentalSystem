package com.example.project.controller;

import com.example.project.model.AnnouncementGrpc;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.services.AnnouncementGrpcService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */


@RestController
@RequestMapping("/announcement")
public class AnnouncementGrpcController {

    @Autowired
    private AnnouncementGrpcService announcementGrpcService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public AnnouncementGrpcController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping("/{id}")
    public HttpEntity<EntityModel<AnnouncementGrpc>> getAnnouncementGrpcById(@PathVariable Long id) {
        AnnouncementGrpc announcementGrpc = announcementGrpcService.getAnnouncementGrpcById(id);

        EntityModel<AnnouncementGrpc> resource = EntityModel.of(announcementGrpc);
        resource.add(linkTo(methodOn(AnnouncementGrpcController.class).getAnnouncementGrpcById(id)).withSelfRel());
        if (announcementGrpc.getCountryGrpc() != null) {
            resource.add(linkTo(methodOn(CountryGrpcController.class)
                    .getCountryGrpcById(announcementGrpc.getCountryGrpc().getId())).withRel("countryGrpc"));
        }
        if (announcementGrpc.getCityGrpc() != null) {
            resource.add(linkTo(methodOn(CityGrpcController.class)
                    .getCityGrpcById(announcementGrpc.getCityGrpc().getId())).withRel("cityGrpc"));
        }
        if (announcementGrpc.getRenovationGrpc() != null) {
            resource.add(linkTo(methodOn(RenovationGrpcController.class)
                    .getRenovationGrpcById(announcementGrpc.getRenovationGrpc().getId())).withRel("renovationGrpc"));
        }
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("/getAllAnnouncementGrpc")
    public HttpEntity<List<AnnouncementGrpc>> getAllAnnouncementGrpc() {
        List<AnnouncementGrpc> announcementGrpcList = announcementGrpcService.getAllAnnouncementGrpc();
        announcementGrpcList.forEach(announcementGrpc ->
                announcementGrpc.add(linkTo(methodOn(AnnouncementGrpcController.class)
                        .getAnnouncementGrpcById(announcementGrpc.getId())).withSelfRel())
        );
        return new ResponseEntity<>(announcementGrpcList, HttpStatus.OK);
    }

    @PostMapping
    public AnnouncementGrpc createAnnouncementGrpc(@RequestBody AnnouncementGrpc announcementGrpc) {
        rabbitTemplate.convertAndSend("grpc-created-exchange", "announcement.new", announcementGrpc);
        rabbitTemplate.convertAndSend("grpc-created-exchange", "audit.announcement.new", announcementGrpc);

        return announcementGrpcService.createAnnouncementGrpc(announcementGrpc);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncementGrpcById(@PathVariable Long id) {
        announcementGrpcService.deleteAnnouncementGrpcById(id);
    }

}