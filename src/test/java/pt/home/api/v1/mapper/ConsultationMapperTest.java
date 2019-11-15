package pt.home.api.v1.mapper;

import org.junit.Test;
import pt.home.api.v1.model.ConsultationDTO;
import pt.home.domain.Consultation;
import pt.home.domain.Customer;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ConsultationMapperTest {

    //Constants
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2019, 11, 11, 10, 30);
    public static final String DESCRIPTION = "Description";

    ConsultationMapper consultationMapper = ConsultationMapper.INSTANCE;

    @Test
    public void consultationToConsultationDTO(){

        //given
        Consultation consultation = Consultation.builder().dateTime(DATE_TIME).description(DESCRIPTION).build();

        Customer customer = Customer.builder().id(1L).fullName("John Doe").build();
        consultation.setCustomer(customer);

        //when
        ConsultationDTO consultationDTO = consultationMapper.consultationToConsultationDTO(consultation);

        //then
        assertEquals(DATE_TIME, consultationDTO.getDateTime());
        assertEquals(DESCRIPTION, consultationDTO.getDescription());
        assertEquals(customer, consultationDTO.getCustomer());
    }

    @Test
    public void consultationDTOToConsultation(){

        //given
        ConsultationDTO consultationDTO = ConsultationDTO.builder().dateTime(DATE_TIME).description(DESCRIPTION).build();

        Customer customer = Customer.builder().id(1L).fullName("John Doe").build();
        consultationDTO.setCustomer(customer);

        //when
        Consultation consultation = consultationMapper.consultationDTOToConsultation(consultationDTO);

        //then
        assertEquals(DATE_TIME, consultation.getDateTime());
        assertEquals(DESCRIPTION, consultation.getDescription());
        assertEquals(customer, consultation.getCustomer());
    }
}
