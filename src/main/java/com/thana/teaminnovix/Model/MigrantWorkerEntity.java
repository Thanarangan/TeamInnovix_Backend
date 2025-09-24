package com.thana.teaminnovix.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class MigrantWorkerEntity {

    @Id
    @Column(name = "health_id", nullable = false, unique = true)
    private String healthId;

    private String username;

    @Column(name = "awaz_id_number", unique = true)
    private String awazIdNumber;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "age")
    private Integer age;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "local_address", length = 1000)
    private String localAddress;

    @Column(name = "aadhaar_or_id_number")
    private String aadhaarOrIdNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "parents_or_spouse_name")
    private String parentsOrSpouseName;

    @ElementCollection
    @CollectionTable(name = "migrant_children", joinColumns = @JoinColumn(name = "migrant_id"))
    private List<ChildInfo> children = new ArrayList<>();

    @Embedded
    private BankDetails bankDetails;

    @Embedded
    private JobDetails jobDetails;

    @Column(name = "wage", precision = 19, scale = 2)
    private BigDecimal wage;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_frequency")
    private PaymentFrequency paymentFrequency;

    @Column(name = "currently_working")
    private Boolean currentlyWorking;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChildInfo {
        @Column(name = "child_name")
        private String name;

        @Column(name = "school_name")
        private String schoolName;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BankDetails {
        @Column(name = "bank_account_number")
        private String accountNumber;

        @Column(name = "bank_name")
        private String bankName;

        @Column(name = "ifsc")
        private String ifsc;

        @Column(name = "branch")
        private String branch;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JobDetails {
        @Column(name = "work_type")
        private String workType;

        @Column(name = "designation")
        private String designation;

        @Column(name = "job_description", length = 2000)
        private String jobDescription;

        @Column(name = "wage_currency")
        private String wageCurrency;
    }

    public enum MaritalStatus {
        SINGLE,
        MARRIED,
        DIVORCED,
        WIDOWED,
        SEPARATED,
        OTHER
    }

    public enum PaymentFrequency {
        DAILY,
        WEEKLY,
        BIWEEKLY,
        MONTHLY,
        ONCE,
        OTHER
    }
}
