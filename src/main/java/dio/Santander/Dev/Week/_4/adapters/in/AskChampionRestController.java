package dio.Santander.Dev.Week._4.adapters.in;

import dio.Santander.Dev.Week._4.application.AskChampionUseCase;
import dio.Santander.Dev.Week._4.application.ListChampionsUseCase;
import dio.Santander.Dev.Week._4.domain.model.Champion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public record AskChampionRestController() {

    @Tag(name = "Campeões", description = "Endpoint de domínio do Champions LoL")
    @RestController
    /*Nos permite acessar os nossos modelos nos endpoints a partir da nossa url*/
    @RequestMapping("/Champion")
    public record ListChampionRestController(AskChampionUseCase useCase) {

        @PostMapping ("/{championId}/ask")

        public championResponse askChampion(@PathVariable Long championId, @RequestBody championRequest request) {

            String answer = useCase.askChampion(championId, request.question());

            return new championResponse(answer);


        }

        public record championRequest (String question){ }
        public record championResponse (String answer) { }
    }
}
