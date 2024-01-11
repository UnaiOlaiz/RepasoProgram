package C1Swing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
public class EjemploPaintComponent {
	public static void main(String[] args) {
		 JFrame frame = new JFrame("Ejemplo Paint Component");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(400, 300);
	     frame.setLocationRelativeTo(null);
	     
	     DefaultTableModel modelo = new DefaultTableModel();
	     modelo.addColumn("Dibujar Rectángulo");
	     
	     modelo.addRow(new Object[] {""});
	     
	     JTable tabla = new JTable();
	     tabla.setModel(modelo);
	     
	     // Empezemos con el renderer + paintComponent()
	     tabla.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				int anchura = getWidth();
				int altura = getHeight();
				
				g.setColor(Color.BLUE);
		        g.fillRect(5, 5, anchura - 10, altura - 10); // Ajustar las coordenadas y dimensiones como desees
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				return c;
			}
	    	 
	     });
	     
	     frame.add(new JScrollPane(tabla), BorderLayout.CENTER);
	     frame.setVisible(true);
	}

}
