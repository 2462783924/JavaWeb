package Dao;

import beans.Person;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    public PersonDao() {
    }

    public List<Person> fuzzyQuery(String stnick) {
        List<Person> list = new ArrayList();
        String sql = "";
        if (stnick != "" && !stnick.contentEquals("")) {
            sql = "select * from stuinfo where STUNAME REGEXP  ? ";
        } else {
            sql = "select * from stuinfo";
        }
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (stnick != null && !stnick.contentEquals("")) {
                pstmt.setString(1, stnick);
            }
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nick = rs.getString("NICK");
                String password = rs.getString("PASSWORD");
                String stuno = rs.getString("STUNO");
                String stuname = rs.getString("STUNAME");
                String sex = rs.getString("SEX");
                String age = rs.getString("AGE");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String pic = rs.getString("PIC");
                String intpoduce = rs.getString("INTPODUCE");
                Person person = new Person(id, nick, password, stuno, stuname, sex, age, phone, email, pic, intpoduce);
                list.add(person);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }
        return list;
    }

    public List<Person> findByKey(String key) {
        List<Person> list = new ArrayList();
        String sql = "";
        if (key != "" && !key.contentEquals("")) {
            sql = "select * from stuinfo where STUNAME like ? or STUNO like ?";
        } else {
            sql = "select * from stuinfo";
        }
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (key != null && !key.contentEquals("")) {
                pstmt.setString(1, "%" + key + "%");
                pstmt.setString(2, "%" + key + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nick = rs.getString("NICK");
                String password = rs.getString("PASSWORD");
                String stuno = rs.getString("STUNO");
                String stuname = rs.getString("STUNAME");
                String sex = rs.getString("SEX");
                String age = rs.getString("AGE");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String pic = rs.getString("PIC");
                String intpoduce = rs.getString("INTPODUCE");
                Person person = new Person(id, nick, password, stuno, stuname, sex, age, phone, email, pic, intpoduce);
                list.add(person);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }
        return list;
    }

    public List<Person> findByID(int ID) {
        List<Person> list = new ArrayList();
        String sql = "";
        sql = "select * from stuinfo where ID = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nick = rs.getString("NICK");
                String password = rs.getString("PASSWORD");
                String stuno = rs.getString("STUNO");
                String stuname = rs.getString("STUNAME");
                String sex = rs.getString("SEX");
                String age = rs.getString("AGE");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String pic = rs.getString("PIC");
                String intpoduce = rs.getString("INTPODUCE");
                Person person = new Person(id, nick, password, stuno, stuname, sex, age, phone, email, pic, intpoduce);
                list.add(person);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }
        return list;
    }

    public List<Person> searchPassword(String NICK) {
        List<Person> list = new ArrayList();
        String sql = "select * from stuinfo where NICK = ? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            if (NICK != null && !NICK.contentEquals("")) {
                pstmt.setString(1, NICK);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nick = rs.getString("NICK");
                String password = rs.getString("PASSWORD");
                String stuno = rs.getString("STUNO");
                String stuname = rs.getString("STUNAME");
                String sex = rs.getString("SEX");
                String age = rs.getString("AGE");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String pic = rs.getString("PIC");
                String intpoduce = rs.getString("INTPODUCE");
                Person person = new Person(id, nick, password, stuno, stuname, sex, age, phone, email, pic, intpoduce);
                list.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return list;
    }

    public List<Person> getStudentsByPage(int page, int size) {
        List<Person> list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement("select * from stuinfo limit ?,?");
            pstm.setInt(1, (page - 1) * size);
            pstm.setInt(2, size);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nick = rs.getString("NICK");
                String password = rs.getString("PASSWORD");
                String stuno = rs.getString("STUNO");
                String stuname = rs.getString("STUNAME");
                String sex = rs.getString("SEX");
                String age = rs.getString("AGE");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String pic = rs.getString("PIC");
                String intpoduce = rs.getString("INTPODUCE");
                Person person = new Person(id, nick, password, stuno, stuname, sex, age, phone, email, pic, intpoduce);
                list.add(person);
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            DBConnection.free(conn, pstm, rs);
        }

        return list;
    }

    public int insertStudent(Person student) {
        try {
            if (student == null) {
                return 1;
            }

            String sql = "INSERT INTO stuinfo (`NICK`, `PASSWORD`, `STUNO`, `STUNAME`, `SEX`, `AGE`, `PHONE`,`EMAIL`, `PIC`, `INTPODUCE`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getNick());
            pstmt.setString(2, student.getPassword());
            pstmt.setString(3, student.getStuno());
            pstmt.setString(4, student.getStuname());
            pstmt.setString(5, student.getSex());
            pstmt.setString(6, student.getAge());
            pstmt.setString(7, student.getPhone());
            pstmt.setString(8, student.getEmail());
            pstmt.setString(9, student.getPic());
            pstmt.setString(10, student.getIntpoduce());
            int i = pstmt.executeUpdate();
            if (i != 1) {
                return 2;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return 0;
    }

    public int updateStudent(Person student) {
        try {
            if (student == null) {
                return 1;
            }

            String sql = "update stuinfo set NICK=? , PASSWORD=? , STUNO=? , STUNAME=? ,SEX=? ,AGE=? ,PHONE=?, EMAIL=?,PIC=? ,INTPODUCE=? where ID=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getNick());
            pstmt.setString(2, student.getPassword());
            pstmt.setString(3, student.getStuno());
            pstmt.setString(4, student.getStuname());
            pstmt.setString(5, student.getSex());
            pstmt.setString(6, student.getAge());
            pstmt.setString(7, student.getPhone());
            pstmt.setString(8, student.getEmail());
            pstmt.setString(9, student.getPic());
            pstmt.setString(10, student.getIntpoduce());
            pstmt.setInt(11, student.getId());
            int i = pstmt.executeUpdate();
            if (i != 1) {
                return 2;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return 0;
    }

    public void deleInfo(int sid) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement("delete from stuinfo where ID=?");
            pstm.setString(1, String.valueOf(sid));
            pstm.executeUpdate();
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRowCount() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement("select count(1) as num from stuinfo");
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("num");
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            DBConnection.free(conn, pstm, rs);
        }

        return count;
    }

    public int getPage(int size) {
        int r = getRowCount();
        int page = 0;
        if (r % size == 0) {
            page = r / size;
        } else {
            page = r / size + 1;
        }
        System.out.println(page);
        return page;
    }
}
