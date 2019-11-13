package pt.home.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    public Long id;

    private String fullName;
    private Long phoneNumber;
    private String email;
    private String address;

//    private Set<Pathology> pathologies;
//    private Set<Consultation> consultations;

    @JsonProperty("customer_url")
    private String customerUrl;
}
