package com.example.project.services.impl;

import com.example.project.model.AnnouncementGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.repository.AnnouncementGrpcRepository;
import com.example.project.services.AnnouncementGrpcService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
@Service
public class AnnouncementGrpcServiceImpl implements AnnouncementGrpcService {

    private final AnnouncementGrpcRepository announcementGrpcRepository;

    @Autowired
    public AnnouncementGrpcServiceImpl(AnnouncementGrpcRepository announcementGrpcRepository) {
        this.announcementGrpcRepository = announcementGrpcRepository;
    }

    @Override
    public AnnouncementGrpc getAnnouncementGrpcById(Long id) {
        return announcementGrpcRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id: " + id));
    }

    @Override
    public List<AnnouncementGrpc> getAllAnnouncementGrpc() {
        return announcementGrpcRepository.findAll();
    }


    @Override
    public AnnouncementGrpc createAnnouncementGrpc(AnnouncementGrpc announcementGrpc) {
        return announcementGrpcRepository.save(announcementGrpc);
    }


    @Override
    public void deleteAnnouncementGrpcById(Long id) {
        if (!announcementGrpcRepository.existsById(id)) {
            throw new EntityNotFoundException("Announcement not found with id: " + id);
        }
        announcementGrpcRepository.deleteById(id);
    }


}