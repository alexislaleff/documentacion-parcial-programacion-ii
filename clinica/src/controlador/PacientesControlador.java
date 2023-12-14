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
import modelo.Pacientes;

/**
 *
 * @author ale_l
 */
public class PacientesControlador {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List Listar() {
        List<Pacientes> lista = new ArrayList<>();
        String sql = "select * from pacientes order by nombre asc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pacientes c = new Pacientes();
                c.setDni(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setFechaNacimiento(rs.getDate(4));
                c.setTelefono(rs.getInt(5));
                c.setSexo(rs.getString(6));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public List<Pacientes> listarDniFiltro(int dni) {
        List<Pacientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE dni = ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pacientes c = new Pacientes();
                c.setDni(rs.getInt(1));
                c.setNombre(rs.getString(2));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public List<Pacientes> listarNombre(String nombre) {
        List<Pacientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE nombre LIKE ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Pacientes c = new Pacientes();
                c.setDni(rs.getInt(1));
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
        String sql = "insert into pacientes(dni, nombre, apellido, fecha_nac, telefono, sexo) values(?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    public int actualizar(Object[] o) {

        int r = 0;
        String sql = "update pacientes set nombre=?, apellido=?, fecha_nac=?, telefono=?, sexo=? where dni=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[1]);
            ps.setObject(2, o[2]);
            ps.setObject(3, o[3]);
            ps.setObject(4, o[4]);
            ps.setObject(5, o[5]);
            ps.setObject(6, o[0]);
            r = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    public void eliminar(int dni) {
        String sql = "delete from pacientes where dni=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
