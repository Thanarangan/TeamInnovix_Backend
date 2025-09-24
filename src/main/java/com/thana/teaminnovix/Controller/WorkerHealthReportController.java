package com.thana.teaminnovix.Controller;

import com.thana.teaminnovix.Model.WorkerHealthReport;
import com.thana.teaminnovix.Service.WorkerHealthReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-reports")
public class WorkerHealthReportController {

    @Autowired
    private WorkerHealthReportService service;

    @PostMapping
    public ResponseEntity<WorkerHealthReport> createReport(@RequestBody WorkerHealthReport report) {
        WorkerHealthReport savedReport = service.saveReport(report);
        return ResponseEntity.ok(savedReport);
    }

    @GetMapping
    public ResponseEntity<List<WorkerHealthReport>> getAllReports() {
        return ResponseEntity.ok(service.getAllReports());
    }

    @GetMapping("/{healthId}")
    public ResponseEntity<WorkerHealthReport> getReportById(@PathVariable String healthId) {
        return service.getReportById(healthId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{healthId}")
    public ResponseEntity<WorkerHealthReport> updateReport(
            @PathVariable String healthId,
            @RequestBody WorkerHealthReport reportDetails) {
        return service.getReportById(healthId).map(report -> {
            report.setCurrentIllness(reportDetails.getCurrentIllness());
            report.setRecentHospitalisation(reportDetails.getRecentHospitalisation());
            report.setOngoingMedication(reportDetails.getOngoingMedication());
            report.setWorkInjuryDetails(reportDetails.getWorkInjuryDetails());
            report.setWorkInjuryDate(reportDetails.getWorkInjuryDate());
            report.setAllergies(reportDetails.getAllergies());
            report.setDisabilityStatus(reportDetails.getDisabilityStatus());
            report.setPastTreatmentHistory(reportDetails.getPastTreatmentHistory());
            WorkerHealthReport updated = service.saveReport(report);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{healthId}")
    public ResponseEntity<Void> deleteReport(@PathVariable String healthId) {
        service.deleteReport(healthId);
        return ResponseEntity.noContent().build();
    }
}