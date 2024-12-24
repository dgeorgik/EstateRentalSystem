package com.example.project.services;

import com.example.project.model.AnnouncementGrpc;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
public interface AnnouncementGrpcService {
    AnnouncementGrpc getAnnouncementGrpcById(Long id);
    List<AnnouncementGrpc> getAllAnnouncementGrpc();
    AnnouncementGrpc createAnnouncementGrpc(AnnouncementGrpc announcementGrpc);
    void deleteAnnouncementGrpcById(Long id);
}
