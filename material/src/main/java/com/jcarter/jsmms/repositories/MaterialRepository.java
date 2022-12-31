package com.jcarter.jsmms.repositories;

import com.jcarter.jsmms.models.Material;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<Material> rowMapper;

    public MaterialRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.rowMapper = (r, i) -> {
            return Material.builder()
                    .materialId(r.getInt("materialId"))
                    .type(r.getString("materialType"))
                    .description(r.getString("description"))
                    .build();
        };
    }

    public void storeMaterial(Material m) {
        String sqlStatement = "INSERT INTO material (description, materialType) VALUES (?, ?)";
        jdbc.update(sqlStatement, m.getDescription(), m.getType());
    }

    public Material getMaterialForId(int id) {
        String sqlStatement = "SELECT * FROM material where materialId = ?";
        return jdbc.queryForObject(sqlStatement, new Object[]{id}, rowMapper);
    }

    public List<Material> getAllMaterials() {
        String sqlStatement = "SELECT * FROM material";
        return jdbc.query(sqlStatement, rowMapper);
    }

    public int deleteMaterialForId(int id) {
        String sqlStatement = "DELETE FROM material where materialId = ?";
        Object[] args = new Object[] {id};
        return jdbc.update(sqlStatement, args);
    }

    public int updateMaterialForId(int id, Material m) {
        String sqlStatement = "UPDATE material SET description = ?, materialType = ? WHERE materialId = ?";
        return jdbc.update(sqlStatement, m.getDescription(), m.getType(), id);
    }
}
