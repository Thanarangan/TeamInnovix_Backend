package com.thana.teaminnovix.Repo;

import com.thana.teaminnovix.Model.MigrantWorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


public interface MigrantWorkerRepo extends JpaRepository<MigrantWorkerEntity, Integer> {
    Optional<MigrantWorkerEntity> findByHealthId(String healthId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MigrantWorkerEntity m WHERE m.healthId = :healthId")
    void deleteByHealthId(String healthId);
}
