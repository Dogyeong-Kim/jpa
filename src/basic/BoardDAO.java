package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    static {
        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        // Connection 생성
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "c##java";
        String password = "12345";

        return DriverManager.getConnection(url, user, password);
    }

    public int insert(BoardDTO boardDTO) {
        int result = 0;

        try {

            con = getConnection();
            String sql = "INSERT INTO board(bno, title, content, writer) ";
            sql += "VALUES(board_seq.nextval, 'title1', 'content1', 'user1')";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getConnect());
            pstmt.setString(3, boardDTO.getWriter());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
