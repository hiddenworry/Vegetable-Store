/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, roleID, status from tblUsers WHERE userID =? AND password=? AND status=?";
    private static final String SEARCH = "SELECT userID, fullName, birthday, address, phone,roleID, status from tblUsers WHERE fullName=?";
    private static final String INSER_GOOGLE_USER = "INSERT INTO tblUsers (userID,password,fullName,email,roleID,status) values(?,?,?,?,?,?)";
    private static final String CHECK_EXIST = "SELECT userID FROM tblUsers WHERE userID=?";

    public UserDTO login(String userID, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(LOGIN);
                ps.setString(1, userID);
                ps.setString(2, password);
                ps.setBoolean(3, true);
                rs = ps.executeQuery();
                if (rs.next()) {

                    String fullName = rs.getNString("fullName");
                    boolean roleID = rs.getBoolean("roleID");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, "****", fullName, roleID, "", "", "", status);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return user;
    }

    // Search
    public List<UserDTO> search(String fullName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserDTO> rsList = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(SEARCH);
            ps.setString(1, "%" + fullName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String name = rs.getNString("fullName");
                String address = rs.getNString("address");
                boolean roleID = rs.getBoolean("roleID");
                String birthday = rs.getString("birthday");
                String phone = rs.getString("phone");
                boolean status = rs.getBoolean("status");
                UserDTO user = new UserDTO(userID, "****", fullName, roleID, address, birthday, phone, status);
                rsList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return rsList;
    }

    /// Check user exist
    public boolean checkExist(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(CHECK_EXIST);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
    // inser new user while login by google

    public boolean insertGoogleUser(String userID, String fullName, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean check = false;
        String password = generateRandomPassword(15); // 
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(INSER_GOOGLE_USER);
            ps.setString(1, userID);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setString(4, email);
            ps.setBoolean(5, false);
            ps.setBoolean(6, true);
            check = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    // generate password: Tao mot day ki tu ngau nhien la, mat khau khi login bang google de insert vao database
    // Method to generate a random alphanumeric password of a specific length
    private String generateRandomPassword(int len) {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

}
