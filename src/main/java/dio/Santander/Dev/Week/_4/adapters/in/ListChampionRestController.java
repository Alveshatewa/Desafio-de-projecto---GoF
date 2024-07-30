package dio.Santander.Dev.Week._4.adapters.in;


import dio.Santander.Dev.Week._4.application.ListChampionsUseCase;
import dio.Santander.Dev.Week._4.domain.model.Champion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Campeões", description = "Endpoint de domínio do Champions LoL")
@RestController
/*Nos permite acessar os nossos modelos nos endpoints a partir da nossa url*/
@RequestMapping("/Champion")
public record ListChampionRestController(ListChampionsUseCase useCase) {

@GetMapping /* Nos permite definir a forma como vamos acesar os nossos endpoint por meio do path
 informado no requestMappping*/

    public List<Champion> findAllChampion(){
        return useCase.findAll();
    }

}
