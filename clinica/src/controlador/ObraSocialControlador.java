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
import modelo.ObraSocial;

/**
 *
 * @author ale_l
 */
public class ObraSocialControlador {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    
    public List Listar() {
        List<ObraSocial> lista = new ArrayList<>();
        String sql = "select * from obra_social order by nombre asc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ObraSocial c = new ObraSocial();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public List<ObraSocial> listarNombre(String nombre) {
        List<ObraSocial> lista = new ArrayList<>();
        String sql = "SELECT * FROM obra_social WHERE nombre LIKE ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ObraSocial c = new ObraSocial();
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
        String sql = "insert into obra_social(nombre)values(?)";
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
        String sql = "update obra_social set nombre=? where id_obra_social=?";
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
        String sql = "delete from obra_social where id_obra_social=?";
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
