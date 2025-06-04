package model.dao;

import model.dto.UpdateUserDto;
import model.enities.User;
import util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, Integer>{
    @Override
    public User save(User user) {
        String sql = """
                INSERT INTO users (user_name, email, uuid, created_date, password)
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
        String sql = """
                DELETE FROM users
                WHERE id = ?
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            if(ps.executeUpdate() > 0){
                return 1;
            }
            return 0;
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return 0;
    }

    public User findUserByUuid(String uuid){
        String sql = """
                SELECT * FROM users
                WHERE uuid = ?
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uuid);
            return getUser(ps);
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return null;
    }

    public User updateUserByUuid(String uuid, UpdateUserDto userDto){
        User user = findUserByUuid(uuid);
        if(user != null){
            String sql = """
                    UPDATE users
                    SET user_name = ?, password = ?
                    WHERE uuid = ?
                    RETURNING *
                    """;
            try(Connection conn = DatabaseConfig.getConnection()){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,userDto.userName());
                ps.setString(2, userDto.password());
                ps.setString(3, uuid);
                return getUser(ps);
            }catch (SQLException e){
                System.out.println("[!] SQLException: " + e.getMessage());
            }
        }
        return null;
    }

    public User getUser(PreparedStatement pre){
        try{
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setUuid(rs.getString("uuid"));
                user.setCreatedDate(rs.getDate("created_date").toLocalDate());
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
