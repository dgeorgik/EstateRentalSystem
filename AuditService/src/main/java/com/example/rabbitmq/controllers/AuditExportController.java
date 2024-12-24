package com.example.rabbitmq.controllers;

import com.example.rabbitmq.models.AuditGrpc;
import com.example.rabbitmq.repositories.AuditGrpcRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditExportController {

    private final AuditGrpcRepository auditGrpcRepository;

    @Autowired
    public AuditExportController(AuditGrpcRepository auditGrpcRepository) {
        this.auditGrpcRepository = auditGrpcRepository;
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportAuditDataToExcel(
            @RequestParam("month") int month,
            @RequestParam("year") int year) throws IOException {
//http://localhost:8082/api/audit/export/excel?month=10&year=2024
        List<AuditGrpc> auditRecords = auditGrpcRepository.findByMonthAndYear(month, year);

        ByteArrayInputStream in = createExcelFile(auditRecords);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=audit_data" + month + "_" + year+".xlsx");

         return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in.readAllBytes());
    }

     private ByteArrayInputStream createExcelFile(List<AuditGrpc> auditRecords) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Audit Data");

             Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Дата создания", "Очередь", "Сообщение", "Публикация", "Подписчик", "Имя пользователя", "Email пользователя"};

            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

             int rowIdx = 1;
             for (AuditGrpc record : auditRecords) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(record.getId());
                row.createCell(1).setCellValue(record.getCreate().toString());
                row.createCell(2).setCellValue(record.getRabbitQueue());
                row.createCell(3).setCellValue(record.getMessage());

                 if (record.getPublishing() != null) {
                    row.createCell(4).setCellValue(record.getPublishing() ? "Да" : "Нет");
                } else {
                    row.createCell(4).setCellValue("Нет данных");
                }
                 if (record.getSubscriber() != null) {
                    row.createCell(5).setCellValue(record.getSubscriber() ? "Да" : "Нет");
                } else {
                    row.createCell(5).setCellValue("Нет данных");
                }

                row.createCell(6).setCellValue(record.getUserName());
                row.createCell(7).setCellValue(record.getUserEmail());
            }


            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
