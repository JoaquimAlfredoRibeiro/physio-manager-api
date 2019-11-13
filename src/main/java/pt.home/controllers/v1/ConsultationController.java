package pt.home.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.home.api.v1.model.ConsultationListDTO;
import pt.home.services.ConsultationService;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(ConsultationController.BASE_URL)
public class ConsultationController {

    public static final String BASE_URL = "/api/v1/consultations";

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ConsultationListDTO getAllConsultations(){
        return new ConsultationListDTO(consultationService.getAllConsultations());
    }

    @GetMapping("/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public ConsultationListDTO getConsultationsByDate(@PathVariable Date startDate, Date endDate){
        //TODO implement this
        return new ConsultationListDTO(
                consultationService.getConsultationsByDate(LocalDateTime.of(2019, 11, 11, 10, 30),
                                                          LocalDateTime.of(2019, 11, 13, 10, 30)));
    }

}
