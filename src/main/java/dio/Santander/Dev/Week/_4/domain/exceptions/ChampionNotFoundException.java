package dio.Santander.Dev.Week._4.domain.exceptions;

public class ChampionNotFoundException extends RuntimeException {

    public ChampionNotFoundException(Long championId) {
        super("Champion %d not found".formatted(championId));
        // Uma outra forma de resolver o mesmo problema
        //super(STR.""" Champion \{championId} not found""");
    }
}
