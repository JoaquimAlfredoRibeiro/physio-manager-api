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
import java.util.HashSet;
import java.util.List;

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
        loadCustomers();
        loadConsultations();
        correlateCustomers();
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

    private void loadCustomers() {

        Customer c1 = Customer.builder()
                .fullName("John Doe")
                .address("Main Street 12")
                .email("johndow@email.biz")
                .phoneNumber(new Long(123456789))
                .pathologies(new HashSet<>())
                .consultations(new HashSet<>())
                .build();
        Customer c2 = Customer.builder()
                .fullName("Jane Lawson")
                .address("Main Street 13")
                .email("janelawson@email.biz")
                .phoneNumber(new Long(987654321))
                .pathologies(new HashSet<>())
                .consultations(new HashSet<>())
                .build();
        Customer c3 = Customer.builder()
                .fullName("Maya Origins")
                .address("Cambodja")
                .email("astralcards@email.biz")
                .phoneNumber(new Long(111111111))
                .pathologies(new HashSet<>())
                .consultations(new HashSet<>())
                .build();
        Customer c4 = Customer.builder()
                .fullName("Ricky the Martin Millers")
                .address("Pop Gin Street 1")
                .email("servedcold@email.biz")
                .phoneNumber(new Long(222333444))
                .pathologies(new HashSet<>())
                .consultations(new HashSet<>())
                .build();
        Customer c5 = Customer.builder()
                .fullName("Pirateman the Seafaring Muscleman")
                .address("Atlantic Ocean, under the stars")
                .email("flying_dove_11@messengerbirds.org")
                .phoneNumber(new Long(987987987))
                .pathologies(new HashSet<>())
                .consultations(new HashSet<>())
                .build();

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);

        System.out.println("Loaded Customer");
    }

    private void loadConsultations() {

        List<Customer> customers = customerRepository.findAll();

        Consultation c1 = Consultation.builder()
                .dateTime(LocalDateTime.of(2019, 11, 10, 10, 30))
                .description("Diagnosis Consultation")
                .customer(customers.get(0))
                .build();
        Consultation c2 = Consultation.builder()
                .dateTime(LocalDateTime.of(2019, 11, 11, 11, 30))
                .description("Follow-up Consultation")
                .customer(customers.get(1))
                .build();
        Consultation c3 = Consultation.builder().
                dateTime(LocalDateTime.of(2019, 11, 12, 12, 30))
                .description("General Consultation")
                .customer(customers.get(2))
                .build();
        Consultation c4 = Consultation.builder().
                dateTime(LocalDateTime.of(2019, 11, 13, 13, 30))
                .description("Diagnosis Consultation")
                .customer(customers.get(3))
                .build();
        Consultation c5 = Consultation.builder().
                dateTime(LocalDateTime.of(2019, 11, 14, 14, 30))
                .description("Follow-up Consultation")
                .customer(customers.get(4))
                .build();

        consultationRepository.save(c1);
        consultationRepository.save(c2);
        consultationRepository.save(c3);
        consultationRepository.save(c4);
        consultationRepository.save(c5);

        System.out.println("Consultations Loaded");
    }

    private void correlateCustomers()
    {
        List<Customer> customers = customerRepository.findAll();
        List<Consultation> consultations = consultationRepository.findAll();
        List<Pathology> pathologies = pathologyRepository.findAll();

        Customer c1 = customers.get(0);
        Customer c2 = customers.get(1);
        Customer c3 = customers.get(2);
        Customer c4 = customers.get(3);
        Customer c5 = customers.get(4);

        c1.getPathologies().add(pathologies.get(0));
        c1.getPathologies().add(pathologies.get(1));
        c1.getConsultations().add(consultations.get(0));

        c2.getPathologies().add(pathologies.get(0));
        c2.getConsultations().add(consultations.get(1));

        c3.getPathologies().add(pathologies.get(2));
        c3.getPathologies().add(pathologies.get(3));
        c3.getPathologies().add(pathologies.get(4));
        c3.getConsultations().add(consultations.get(2));

        c4.getPathologies().add(pathologies.get(2));
        c4.getPathologies().add(pathologies.get(3));
        c4.getConsultations().add(consultations.get(3));

        c5.getPathologies().add(pathologies.get(0));
        c5.getPathologies().add(pathologies.get(1));
        c5.getPathologies().add(pathologies.get(2));
        c5.getPathologies().add(pathologies.get(3));
        c5.getPathologies().add(pathologies.get(4));
        c5.getConsultations().add(consultations.get(4));

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);
    }

}
