package pt.home.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pt.home.domain.Consultation;
import pt.home.domain.Customer;
import pt.home.domain.Pathology;
import pt.home.repositories.ConsultationRepository;
import pt.home.repositories.CustomerRepository;
import pt.home.repositories.PathologyRepository;

import java.time.LocalDateTime;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ConsultationRepository consultationRepository;
    private final PathologyRepository pathologyRepository;

    public Bootstrap(CustomerRepository customerRepository, ConsultationRepository consultationRepository, PathologyRepository pathologyRepository) {
        this.customerRepository = customerRepository;
        this.consultationRepository = consultationRepository;
        this.pathologyRepository = pathologyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadPathologies();
        loadConsultations();
        loadCustomers();
    }

    private void loadPathologies() {
        Pathology p1 = Pathology.builder()
                .name("Knee Injury")
                .description("Standard knee injury")
                .build();
        Pathology p2 = Pathology.builder()
                .name("Arm Injury")
                .description("Standard arm injury")
                .build();
        Pathology p3 = Pathology.builder()
                .name("Foot Injury")
                .description("Standard foot injury")
                .build();
        Pathology p4 = Pathology.builder()
                .name("Shoulder Injury")
                .description("Standard shoulder injury")
                .build();
        Pathology p5 = Pathology.builder()
                .name("Head Injury")
                .description("Standard head injury")
                .build();

        pathologyRepository.save(p1);
        pathologyRepository.save(p2);
        pathologyRepository.save(p3);
        pathologyRepository.save(p4);
        pathologyRepository.save(p5);

        System.out.println("Pathologies Loaded");
    }

    private void loadConsultations() {

        Consultation c1 = Consultation.builder()
                .dateTime(LocalDateTime.of(2019, 11, 10, 10, 30))
                .description("Diagnosis Consultation")
                .build();
        Consultation c2 = Consultation.builder()
                .dateTime(LocalDateTime.of(2019, 11, 11, 11, 30))
                .description("Follow-up Consultation")
                .build();
        Consultation c3 = Consultation.builder().
                dateTime(LocalDateTime.of(2019, 11, 12, 12, 30))
                .description("General Consultation")
                .build();
        Consultation c4 = Consultation.builder().
                dateTime(LocalDateTime.of(2019, 11, 13, 13, 30))
                .description("Diagnosis Consultation")
                .build();
        Consultation c5 = Consultation.builder().
                dateTime(LocalDateTime.of(2019, 11, 14, 14, 30))
                .description("Follow-up Consultation")
                .build();

        consultationRepository.save(c1);
        consultationRepository.save(c2);
        consultationRepository.save(c3);
        consultationRepository.save(c4);
        consultationRepository.save(c5);

        System.out.println("Consultations Loaded");
    }

    private void loadCustomers() {

        Customer c1 = Customer.builder()
                .fullName("John Doe")
                .address("Main Street 12")
                .email("johndow@email.biz")
                .phoneNumber(new Long(123456789))
                .build();
        Customer c2 = Customer.builder()
                .fullName("Jane Lawson")
                .address("Main Street 13")
                .email("janelawson@email.biz")
                .phoneNumber(new Long(987654321))
                .build();
        Customer c3 = Customer.builder()
                .fullName("Maya Origins")
                .address("Cambodja")
                .email("astralcards@email.biz")
                .phoneNumber(new Long(111111111))
                .build();
        Customer c4 = Customer.builder()
                .fullName("Ricky the Martin Millers")
                .address("Pop Gin Street 1")
                .email("servedcold@email.biz")
                .phoneNumber(new Long(222333444))
                .build();
        Customer c5 = Customer.builder()
                .fullName("Pirateman the Seafaring Muscleman")
                .address("Atlantic Ocean, under the stars")
                .email("flying_dove_11@messengerbirds.org")
                .phoneNumber(new Long(987987987))
                .build();

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);

        System.out.println("Loaded Customer");
    }

}
