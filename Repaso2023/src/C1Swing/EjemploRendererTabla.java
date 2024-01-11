package C1Swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

public class EjemploRendererTabla {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo Renderer Tabla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");

        modelo.addRow(new Object[]{"Juan", 30});
        modelo.addRow(new Object[]{"María", 25});
        modelo.addRow(new Object[]{"Carlos", 40});
        modelo.addRow(new Object[]{"Ana", 35});

        JTable tabla = new JTable(modelo);
        
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == 1) {
					if ((int) value % 2 == 0) {
						c.setForeground(Color.GREEN);
					} else {
						c.setForeground(Color.RED);
					}
				} else {
					if (value.equals("Juan")) {
						c.setBackground(Color.YELLOW);
					} else {
						c.setBackground(table.getBackground());
					}
				}
				
//				if (column == 0) {
//					if (value.equals("Juan")) {
//						c.setBackground(Color.YELLOW);
//					} else {
//						c.setBackground(table.getBackground());
//					}
//				}
//				if (tabla.getValueAt(row, 0).equals("Juan")) {
//					c.setBackground(Color.YELLOW);
//				} else {
//					c.setBackground(Color.WHITE);
//				}
				return c;
			}
        	
        };
        
        tabla.setDefaultRenderer(Object.class, tableCellRenderer);
        

        frame.add(new JScrollPane(tabla));
        frame.setVisible(true);
    }
}
