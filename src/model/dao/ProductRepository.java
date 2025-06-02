package model.dao;

import model.ProductData;
import model.dto.UpdateProductDto;
import model.enities.Product;
import util.DatabaseConfig;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ProductRepository implements Repository<Product, Integer> {

    @Override
    public Product save(Product product) {
        String sql = """
                INSERT INTO products (uuid, p_name, expired_date)
                VALUES (?, ?, ?)
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, product.getUuid());
            pre.setString(2, product.getName());
            pre.setDate(3, Date.valueOf(product.getExpiryDate()));
            pre.executeUpdate();
            return product;
        } catch (SQLException e) {
            System.err.println("[!] SQLException: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        String sql = """
                SELECT * FROM products
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                String uuid = rs.getString("uuid");
                String name = rs.getString("p_name");
                Date expiryDate = rs.getDate("expired_date");
                Product product = new Product(id, uuid, name, expiryDate.toLocalDate());
                products.add(product);
            }
            return products;
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        String sql = """
                DELETE FROM products
                WHERE id = ?
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            return pre.executeUpdate();
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return 0;
    }

    public Product findProductByUuid(String uuid){
        String sql = """
                SELECT * FROM products
                WHERE uuid = ?
                """;
        try(Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, uuid);
            return getProduct(pre);
        }catch (SQLException e){
            System.out.println("[!] SQLException: " + e.getMessage());
        }
        return null;
    }

    public Product updateProductByUuid(String uuid, UpdateProductDto updateProductDto){
        Product product = findProductByUuid(uuid);
        if(product!=null){
            String sql = """
                    UPDATE products
                    SET p_name = ?
                    RETURNING *
                    """;
            try (Connection conn = DatabaseConfig.getConnection()){
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, updateProductDto.name());
                return getProduct(pre);
            } catch (SQLException e) {
                System.out.println("[!] SQLException: " + e.getMessage());
            }
        }
        throw new NoSuchElementException("Product not found");
    }

    private Product getProduct(PreparedStatement pre) throws SQLException {
        ResultSet rs = pre.executeQuery();
        if(rs.next()){
            int id = rs.getInt("id");
            String uuid2 = rs.getString("uuid");
            String name = rs.getString("p_name");
            Date expiryDate = rs.getDate("expired_date");
            return new Product(id, uuid2, name, expiryDate.toLocalDate());
        }
        return null;
    }
}
