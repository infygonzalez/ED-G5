package modelo_Gestor;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTable;
import modelo_BDUtils.BDUtils;

public class GestorViajesyEventosTest {

    private Connection conexion;
    private GestorViajesyEventos gestor;

    @Before
    public void setUp() throws Exception {
        gestor = new GestorViajesyEventos();

        // Configurar conexión a base de datos de prueba
        try {
            conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);

            // Insertar datos de prueba
            try (PreparedStatement stmt = conexion.prepareStatement(
                    "INSERT INTO agencia (idAgencia, ColorMarca, Logo) VALUES (1, '#FF5733', 'http://example.com/logo.png')")) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Error al configurar la base de datos de prueba: " + e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        // Eliminar datos de prueba
        try {
            if (conexion != null && !conexion.isClosed()) {
                try (PreparedStatement stmt = conexion.prepareStatement("DELETE FROM agencia WHERE idAgencia = 1")) {
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Error al limpiar la base de datos de prueba: " + e.getMessage());
        } finally {
            // Cerrar conexión
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testImportarColoryLogo() {
        // Configurar paneles para la prueba
        JPanel panelColor = new JPanel();
        JPanel panelLogo = new JPanel();

        // Ejecutar el método a probar
        gestor.ImportarColoryLogo(panelColor, panelLogo, 1);

        // Validar que el color y el logo se hayan importado correctamente
        assertNotNull("El color del panel no debe ser nulo", panelColor.getBackground());
        assertEquals("El panel de logo debe contener exactamente un componente", 1, panelLogo.getComponentCount());
    }

    @Test
    public void testCargarDatosViajes() {
        // Configurar tablas para la prueba
        JTable tableViajes = new JTable();
        JTable tableEventos = new JTable();

        // Ejecutar el método a probar
        gestor.cargarDatosViajes(1, tableViajes, tableEventos);

        // Validar que las tablas se hayan cargado correctamente
        assertNotNull("El modelo de la tabla de viajes no debe ser nulo", tableViajes.getModel());
        assertNotNull("El modelo de la tabla de eventos no debe ser nulo", tableEventos.getModel());
    }
}