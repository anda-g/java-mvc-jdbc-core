package model.dao;

import model.enities.User;
import util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
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
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUuid());
            ps.setDate(4, Date.valueOf(user.getCreatedDate()));
            ps.setString(5, user.getPassword());
            if(ps.executeUpdate() > 0){
                return user;
            }
            return null;
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = """
                SELECT * FROM users
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setUuid(rs.getString("uuid"));
                user.setCreatedDate(rs.getDate("created_date").toLocalDate());
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }
}
