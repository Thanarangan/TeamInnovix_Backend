package com.thana.teaminnovix.Repo;

import com.thana.teaminnovix.Model.WorkerHealthReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerHealthReportRepository extends JpaRepository<WorkerHealthReport, String> {

    Optional<WorkerHealthReport> findByHealthId(String healthId);

    void deleteByHealthId(String healthId);
}