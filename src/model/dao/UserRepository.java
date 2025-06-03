package model.dao;

import model.enities.User;
import util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements Repository<User, Integer>{
    @Override
    public User save(User user) {
        String sql = """
                INSERT INTP users (user_name, email, uuid, created_date, password)
                VALUES (?, ?, ?, ?, ?)
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }
}
