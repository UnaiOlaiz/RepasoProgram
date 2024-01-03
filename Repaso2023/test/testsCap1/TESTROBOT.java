package testsCap1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TESTROBOT {

    private JFrame frame;
    private JTextField textField;
    private JButton button;

    @BeforeEach
    public void setUp() {
        // Crear una nueva ventana JFrame
        frame = new JFrame("Mi Ventana Swing");
        frame.setSize(300, 200);

        // Crear un JTextField y un JButton
        textField = new JTextField();
        button = new JButton("Haz clic");

        // Agregar acción al botón
        button.addActionListener(e -> textField.setText("Botón presionado"));

        // Agregar componentes al JFrame
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        frame.getContentPane().add(button, BorderLayout.SOUTH);

        // Mostrar la ventana
        frame.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        // Cerrar la ventana después de cada prueba
        frame.dispose();
    }

    @Test
    public void testButtonClickWithRobot() throws AWTException {
        // Crear una instancia de Robot
        Robot robot = new Robot();

        // Mover el mouse al botón y hacer clic
        robot.mouseMove(button.getLocationOnScreen().x + 50, button.getLocationOnScreen().y + 10);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        // Esperar un poco para que se procese la acción
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verificar que el campo de texto tenga el texto esperado después de hacer clic en el botón
        assertEquals("Botón presionado", textField.getText());
    }
}
