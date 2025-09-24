package com.thana.teaminnovix.Service;

import com.thana.teaminnovix.Model.UserEntity;
import com.thana.teaminnovix.Model.WorkerHealthReport;
import com.thana.teaminnovix.Repo.UserRepo;
import com.thana.teaminnovix.Repo.WorkerHealthReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerHealthReportService {

    @Autowired
    private WorkerHealthReportRepository repository;

    @Autowired
    private UserRepo userRepo;

    public WorkerHealthReport saveReport(WorkerHealthReport report) {
        UserEntity user = userRepo.findByUserName(report.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + report.getUsername()));
        report.setHealthId(user.getHealthId());
        return repository.save(report);
    }


    public List<WorkerHealthReport> getAllReports() {
        return repository.findAll();
    }

    public Optional<WorkerHealthReport> getReportById(String healthId) {
        return repository.findByHealthId(healthId);
    }

    public void deleteReport(String healthId) {
        repository.deleteByHealthId(healthId);
    }
}
