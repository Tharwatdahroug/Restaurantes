package restaurantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import static javafx.application.Application.launch;

import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DB {

    public static Connection cannent() {
        Connection con = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://Restaurante.accdb");
            //JOptionPane.showMessageDialog(null, "Done");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not connect");
        }
        return con;
    }

    public static int countD() {
        Connection con = cannent();
        try {
            PreparedStatement ps = con.prepareStatement("select count(numD)from Dranks");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {

        } finally {
            try {
                con.close();
            } catch (Exception e) {
            }

        }
        return 0;
    }

    public static int countM() {
        Connection con = cannent();
        try {
            PreparedStatement ps = con.prepareStatement("select count(numM)from Meals");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {

        } finally {
            try {
                con.close();
            } catch (Exception e) {
            }

        }
        return 0;
    }

    public static boolean insertM(int id, String name, String type, int cost) {
        Connection con = cannent();
        try {
            PreparedStatement ps = con.prepareStatement("insert into Meals Values('" + id + "','" + name + "','" + type + "','" + cost + "')");
            return ps.execute();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "رقم الوجبة اواسم الوجبة موجوده مسبقا");

        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return true;
    }

    public static boolean insertD(int id, String name, String type, int cost) {
        Connection con = cannent();
        try {
            PreparedStatement ps = con.prepareStatement("insert into Dranks Values('" + id + "','" + name + "','" + type + "','" + cost + "')");
            return ps.execute();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "رقم المشروب اواسم المشروب موجوده مسبقا");

        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return true;
    }

    public static ObservableList<Dranks> getDranks() {
        Connection con = cannent();
        ObservableList List = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = con.prepareStatement("select*from Dranks");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List.add(new Dranks(rs.getInt("numD"), rs.getString("nameD"), rs.getString("typeD"), rs.getInt("costD")));
            }

        } catch (Exception e) {

        } finally {
            try {
                con.close();
            } catch (Exception e) {
            }
        }

        return List;
    }

    public static ObservableList<Meals> getMeals() {
        Connection con = cannent();
        ObservableList List = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = con.prepareStatement("select * from Meals");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List.add(new Meals(rs.getInt("numM"), rs.getString("nameM"), rs.getString("typeM"), rs.getInt("costM")));
            }

        } catch (Exception e) {

        } finally {
            try {
                con.close();
            } catch (Exception e) {
            }
        }

        return List;
    }
     public static boolean updateD(String where, String name, String type, int cost){
         Connection con = cannent();
        // String sql;
         //sql=;
         try {
              PreparedStatement ps = con.prepareStatement("update Dranks set nameD='"+name+"',typeD='"+type+"',costD='"+cost+"'"+where);
              return !ps.execute();
         } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "رقم المشروب اواسم المشروب موجوده مسبقا");
         }finally{
             try {
                 con.close();
             } catch (Exception e) {
             }
         }
        return true;
     }
     public static boolean updateM(String where, String name, String type, int cost){
         Connection con = cannent();
         //String sql;
         //sql=
         try {
              PreparedStatement ps = con.prepareStatement("update Meals set nameM='"+name+"',typeM='"+type+"',costM='"+cost+"'"+where);
              return !ps.execute();
         } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "رقم الوجبة اواسم الوجبة موجوده مسبقا");
         }finally{
             try {
                 con.close();
             } catch (Exception e) {
             }
         }
        return false;
     }
     public static boolean deleteM(String where){
         Connection con = cannent();
          
         try {
             
             PreparedStatement ps = con.prepareStatement("delete from Meals where "+where);
                   return !ps.execute();
             
         } catch (Exception e) {
             
         }finally{
             try {
                 con.close();
             } catch (Exception e) {
             }
         }
        return true;
     }
     public static boolean deleteD(String where){
         Connection con = cannent();
          
         try {
             
             PreparedStatement ps = con.prepareStatement("delete from Dranks where "+where);
                   return !ps.execute();
             
         } catch (Exception e) {
             
         }finally{
             try {
                 con.close();
             } catch (Exception e) {
             }
         }
        return true;
     }
}
