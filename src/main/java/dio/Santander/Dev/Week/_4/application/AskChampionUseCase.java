package dio.Santander.Dev.Week._4.application;

import dio.Santander.Dev.Week._4.domain.exceptions.ChampionNotFoundException;
import dio.Santander.Dev.Week._4.domain.model.Champion;
import dio.Santander.Dev.Week._4.domain.ports.ChampionsRepository;
import dio.Santander.Dev.Week._4.domain.ports.GenerativeAiService;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiService genAiAPi) {

    public String askChampion(Long championId, String ask){

        Champion champion = repository.findOneById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String context = champion.GeneratedContextByQuestion(ask);
        String objective = """
                Actue como um assistente com a habilidade de conversar como os Heroes ou campeões do League of Legends
                LoL. Responda as perguntas incorporando a personalidade e estilo de cada um dos Heroes.
                Segue a pergunta, o nome do herói ou campeão e sua respectiva lore (história).
                """;


        return genAiAPi.generativeContext(objective, context);
    }
}
