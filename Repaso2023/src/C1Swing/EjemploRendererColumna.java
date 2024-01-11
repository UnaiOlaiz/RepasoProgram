package C1Swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class EjemploRendererColumna {
	
	public static void main(String[] args) {
		// Ventana rápida
		JFrame frame = new JFrame("Ejemplo Renderer Columna");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		
		DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("Animal Name");
	    modelo.addColumn("Type"); // Doméstico o Salvaje

	    modelo.addRow(new Object[]{"Perro", "Doméstico"});
	    modelo.addRow(new Object[]{"León", "Salvaje"});
	    modelo.addRow(new Object[]{"Gato", "Doméstico"});
	    modelo.addRow(new Object[]{"Elefante", "Salvaje"});
        
        JTable table = new JTable();
        table.setModel(modelo);
        
        // Empezemos con el renderer individual para la columna
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				String tipo = (String) value;
				if(tipo.equals("Doméstico")) {
					c.setBackground(Color.GREEN);
				} else if (tipo.equals("Salvaje")) {
					c.setBackground(Color.RED);
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
			}
        	
        };
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				String i = (String) value;
				if(i.equals("Gato")) {
					c.setForeground(Color.YELLOW);
					c.setFont(new Font("Times New Roman", Font.BOLD, 18));
				} else {
					c.setForeground(table.getForeground());
				}
				return c;
			}
        	
        };
        
        table.getColumnModel().getColumn(1).setCellRenderer(renderer);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        
        frame.add(new JScrollPane(table));
        
        frame.setVisible(true);
		
	}

}
