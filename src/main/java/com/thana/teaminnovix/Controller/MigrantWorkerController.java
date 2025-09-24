package com.thana.teaminnovix.Controller;

import com.thana.teaminnovix.Model.MigrantWorkerEntity;
import com.thana.teaminnovix.Service.MigrantWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/migrant-workers")
public class MigrantWorkerController {

    @Autowired
    private MigrantWorkerService service;

    @GetMapping
    public ResponseEntity<List<MigrantWorkerEntity>> getAllWorkers(){
        return ResponseEntity.ok(service.getAllWorkers());
    }

    @GetMapping("/{healthId}")
    public ResponseEntity<MigrantWorkerEntity> getWorkerByHealthId(@PathVariable String healthId) {
        return service.getWorkerByHealthId(healthId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{healthId}")
    public ResponseEntity<MigrantWorkerEntity> updateWorker(
            @PathVariable String healthId,
            @RequestBody MigrantWorkerEntity worker
    ) {
        return ResponseEntity.ok(service.updateWorker(healthId, worker));
    }

    @DeleteMapping("/{healthId}")
    public ResponseEntity<Void> deleteWorker(@PathVariable String healthId) {
        service.deleteWorker(healthId);
        return ResponseEntity.noContent().build();
    }
}