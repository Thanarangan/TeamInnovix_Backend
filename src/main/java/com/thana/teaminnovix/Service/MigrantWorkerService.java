package com.thana.teaminnovix.Service;

import com.thana.teaminnovix.Model.MigrantWorkerEntity;
import com.thana.teaminnovix.Repo.MigrantWorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class MigrantWorkerService {

    @Autowired
    private MigrantWorkerRepo repository;

    public List<MigrantWorkerEntity> getAllWorkers() {
        return repository.findAll();
    }

    public Optional<MigrantWorkerEntity> getWorkerByHealthId(String healthId) {
        return repository.findByHealthId(healthId);
    }

    public MigrantWorkerEntity updateWorker(String healthId, MigrantWorkerEntity updatedWorker) {
        return repository.findByHealthId(healthId).map(worker -> {
            worker.setFullName(updatedWorker.getFullName());
            worker.setUsername(updatedWorker.getUsername());
            worker.setAwazIdNumber(updatedWorker.getAwazIdNumber());
            worker.setDateOfBirth(updatedWorker.getDateOfBirth());
            worker.setAge(updatedWorker.getAge());
            worker.setContactNumber(updatedWorker.getContactNumber());
            worker.setLocalAddress(updatedWorker.getLocalAddress());
            worker.setAadhaarOrIdNumber(updatedWorker.getAadhaarOrIdNumber());
            worker.setMaritalStatus(updatedWorker.getMaritalStatus());
            worker.setParentsOrSpouseName(updatedWorker.getParentsOrSpouseName());
            worker.setChildren(updatedWorker.getChildren());
            worker.setBankDetails(updatedWorker.getBankDetails());
            worker.setJobDetails(updatedWorker.getJobDetails());
            worker.setWage(updatedWorker.getWage());
            worker.setPaymentFrequency(updatedWorker.getPaymentFrequency());
            worker.setCurrentlyWorking(updatedWorker.getCurrentlyWorking());
            return repository.save(worker);
        }).orElseThrow(() -> new RuntimeException("Worker not found with healthId: " + healthId));
    }


    public void deleteWorker(String healthId) {
        repository.deleteByHealthId(healthId);
    }
}