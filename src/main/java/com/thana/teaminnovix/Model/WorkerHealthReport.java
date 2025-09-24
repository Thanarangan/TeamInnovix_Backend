package com.thana.teaminnovix.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerHealthReport {

    @Id
    private String healthId;

    @JsonProperty("Username")
    private String username;

    @Column(name = "current_illness", length = 500)
    private String currentIllness;

    @Column(name = "recent_hospitalisation", length = 500)
    private String recentHospitalisation;

    @Column(name = "ongoing_medication", length = 500)
    private String ongoingMedication;

    @Column(name = "work_injury_details", length = 500)
    private String workInjuryDetails;

    @Column(name = "work_injury_date")
    private LocalDate workInjuryDate;

    @Column(name = "allergies", length = 500)
    private String allergies;

    @Column(name = "disability_status", length = 100)
    private String disabilityStatus;

    @Column(name = "past_treatment_history", length = 500)
    private String pastTreatmentHistory;

}
