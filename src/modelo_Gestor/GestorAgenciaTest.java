package modelo_Gestor;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorAgenciaTest {
    private GestorAgencia gestorAgencia;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        gestorAgencia = new GestorAgencia();
        // Crear una base de datos en memoria
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = connection.createStatement();
        // Crear tabla para pruebas
        stmt.execute("CREATE TABLE agenciaviajes (idAgencia INT AUTO_INCREMENT PRIMARY KEY, Nombre VARCHAR(255), Logo VARCHAR(255), Color VARCHAR(7), NumEmp INT, TipoAgencia VARCHAR(255), Contraseña VARCHAR(255))");
        stmt.execute("INSERT INTO agenciaviajes (Nombre, Logo, Color, NumEmp, TipoAgencia, Contraseña) VALUES ('AgenciaTest', 'http://logo.com/logo.png', '#FFFFFF', 10, 'TipoTest', 'password123')");
        stmt.close();
    }

    @After
    public void tearDown() throws Exception {
        // Cerrar conexión
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testValidarLoginSuccess() {
        int idAgencia = gestorAgencia.validarLogin("AgenciaTest", "password123");
        assertTrue("El ID de la agencia debería ser mayor que 0", idAgencia > 0);
    }

    @Test
    public void testValidarLoginFailure() {
        int idAgencia = gestorAgencia.validarLogin("AgenciaTest", "wrongpassword");
        assertEquals("El ID de la agencia debería ser -1 para credenciales incorrectas", -1, idAgencia);
    }

    @Test
    public void testAñadirNuevaAgenciaSuccess() {
        JPanel panel = new JPanel();
        gestorAgencia.añadirNuevaAgencia("NuevaAgencia", "Entre 10 y 100 empleados", 10, "TipoNueva", "contraseñaSegura", "http://logo.com/nueva.png", Color.BLUE, panel);
        // Verificar que la nueva agencia fue añadida
        int idAgencia = gestorAgencia.validarLogin("NuevaAgencia", "contraseñaSegura");
        assertTrue("La nueva agencia debería ser añadida correctamente", idAgencia > 0);
    }

    @Test
    public void testAñadirNuevaAgenciaInvalidUrl() {
        JPanel panel = new JPanel();
        gestorAgencia.añadirNuevaAgencia("AgenciaInvalida", "Entre 10 y 100 empleados", 10, "TipoInvalido", "contraseñaSegura", "invalid_url", Color.BLUE, panel);
        // Verificar que la agencia no fue añadida
        int idAgencia = gestorAgencia.validarLogin("AgenciaInvalida", "contraseñaSegura");
        assertEquals("La agencia no debería ser añadida debido a URL inválida", -1, idAgencia);
    }

    @Test
    public void testVerificarNombreExistente() {
        JTextField textfield_nombre = new JTextField("AgenciaTest");
        JLabel lbl_nombreError = new JLabel();
        JButton btn_guardar = new JButton();
        gestorAgencia.verificarNombre(textfield_nombre, lbl_nombreError, btn_guardar);
        assertEquals("El nombre ya existe en la base de datos.", lbl_nombreError.getText());
        assertFalse("El botón debería estar desactivado", btn_guardar.isEnabled());
    }

    @Test
    public void testVerificarNombreNoExistente() {
        JTextField textfield_nombre = new JTextField("AgenciaNoExistente");
        JLabel lbl_nombreError = new JLabel();
        JButton btn_guardar = new JButton();
        gestorAgencia.verificarNombre(textfield_nombre, lbl_nombreError, btn_guardar);
        assertEquals("", lbl_nombreError.getText());
        assertTrue("El botón debería estar activado", btn_guardar.isEnabled());
    }
}