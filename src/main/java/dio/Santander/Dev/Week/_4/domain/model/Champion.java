package dio.Santander.Dev.Week._4.domain.model;

public record Champion(long id, String name, String role, String lore, String imageUrl) {

 public String GeneratedContextByQuestion(String question){
     return """
             Pergunta: %s
             Nome do Campeao: %s
             Funcao: %s
             Lore (Historia):%s
             """.formatted(question, this.name, this.role, this.lore);
 }
}
