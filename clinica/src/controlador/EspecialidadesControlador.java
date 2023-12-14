/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Especialidades;

/**
 *
 * @author ale_l
 */
public class EspecialidadesControlador {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    
    public List Listar() {
        List<Especialidades> lista = new ArrayList<>();
        String sql = "select * from especialidades order by nombre asc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Especialidades c = new Especialidades();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public List<Especialidades> listarNombre(String nombre) {
        List<Especialidades> lista = new ArrayList<>();
        String sql = "SELECT * FROM especialidades WHERE nombre LIKE ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Especialidades c = new Especialidades();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
    public int agregar(Object[] o) {
        int r = 0;
        String sql = "insert into especialidades(nombre)values(?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update especialidades set nombre=? where id_especialidades=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[1]);
            ps.setObject(2, o[0]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    
    public void eliminar(int id) {
        String sql = "delete from especialidades where id_especialidades=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
