package dio.Santander.Dev.Week._4.adapters.out;

import dio.Santander.Dev.Week._4.domain.model.Champion;
import dio.Santander.Dev.Week._4.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Champion> rowMapper; /*permite manipular os resultsets que virem do banco de dados
    por meio do Record*/

    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = (rs, rowNum) -> new Champion(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("lore"),
                rs.getString("image_url")
                );
    }

    @Override
    public List<Champion> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS ", rowMapper);
    }

    @Override
    public Optional<Champion> findOneById(Long id) {
        String sql = "SELECT * FROM CHAMPIONS   WHERE ID = ?";
        List<Champion> champion = jdbcTemplate.query(sql, rowMapper, id);
        //return jdbcTemplate.queryForObject("select * from CHAMPIONS where id = ?", rowMapper, id);
        return champion.stream().findFirst();
    }
}
