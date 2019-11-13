package pt.home.api.v1.mapper;

import org.junit.Test;
import pt.home.api.v1.model.CustomerDTO;
import pt.home.domain.Consultation;
import pt.home.domain.Customer;
import pt.home.domain.Pathology;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    //Constants
    public static final String FULLNAME = "John Doe";
    public static final String EMAIL = "johndoe@mybiz.net";
    public static final String ADDRESS = "Main Street, 34, Washington";
    public static final Long PHONE_NUMBER = new Long(123456789);
    public static final LocalDateTime DATE_TIME_1 = LocalDateTime.of(2019,11,11,10,30);
    public static final LocalDateTime DATE_TIME_2 = LocalDateTime.of(2019,12,12,12,00);

    //Pathologies
    Pathology pathology1 = Pathology.builder().name("Pathology1").description("description1").build();
    Pathology pathology2 = Pathology.builder().name("Pathology2").description("description2").build();

    //Consultations
    Consultation consultation1 = Consultation.builder().dateTime(DATE_TIME_1).description("Consultation 1").build();
    Consultation consultation2 = Consultation.builder().dateTime(DATE_TIME_2).description("Consultation 2").build();

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO(){

        //given
        Customer customer = Customer.builder().fullName(FULLNAME).phoneNumber(PHONE_NUMBER).email(EMAIL).address(ADDRESS).build();

//        Set<Pathology> pathologies = new HashSet<>();
//        pathologies.add(pathology1);
//        pathologies.add(pathology2);
//
//        customer.setPathologies(pathologies);
//
//        Set<Consultation> consultations = new HashSet<>();
//        consultations.add(consultation1);
//        consultations.add(consultation2);
//
//        customer.setConsultations(consultations);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FULLNAME, customerDTO.getFullName());
        assertEquals(PHONE_NUMBER, customerDTO.getPhoneNumber());
        assertEquals(EMAIL, customerDTO.getEmail());
        assertEquals(ADDRESS, customerDTO.getAddress());
//        assertEquals(pathologies, customerDTO.getPathologies());
//        assertEquals(consultations, customerDTO.getConsultations());
    }

    @Test
    public void customerDTOToCustomer(){

        //given
        CustomerDTO customerDTO = CustomerDTO.builder().fullName(FULLNAME).phoneNumber(PHONE_NUMBER).email(EMAIL).address(ADDRESS).build();

//        Set<Pathology> pathologies = new HashSet<>();
//        pathologies.add(pathology1);
//        pathologies.add(pathology2);
//
//        customerDTO.setPathologies(pathologies);
//
//        Set<Consultation> consultations = new HashSet<>();
//        consultations.add(consultation1);
//        consultations.add(consultation2);
//
//        customerDTO.setConsultations(consultations);

        //when
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        //then
        assertEquals(FULLNAME, customer.getFullName());
        assertEquals(PHONE_NUMBER, customer.getPhoneNumber());
        assertEquals(EMAIL, customer.getEmail());
        assertEquals(ADDRESS, customer.getAddress());
//        assertEquals(pathologies, customer.getPathologies());
//        assertEquals(consultations, customer.getConsultations());
    }
}
