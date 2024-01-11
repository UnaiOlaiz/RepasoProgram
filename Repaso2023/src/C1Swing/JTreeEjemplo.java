package C1Swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class JTreeEjemplo {
	
	private static DefaultMutableTreeNode raiz;
	private static JTree arbol;
	private static DefaultTreeModel modeloArbol;
	private static JTextField textField;
	
	public static void main(String[] args) {
		// Ventana rápida
		JFrame ventana = new JFrame("Ejemplo JTree");
		ventana.setSize(800, 300);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		raiz = new DefaultMutableTreeNode("Sistema");
		modeloArbol = new DefaultTreeModel(raiz);
		arbol = new JTree(modeloArbol);
		
		ventana.add(arbol);
		
		// Panel botones añadir/eliminar/modificar nodo
		JButton botonAnyadirNodo = new JButton("Añadir nodo");
		botonAnyadirNodo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
				if (nodoSeleccionado != null) {
					String nombreNodo = "AGOTE";
					DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(nombreNodo);
					modeloArbol.insertNodeInto(nuevoNodo, nodoSeleccionado, nodoSeleccionado.getChildCount());
				}
			}
		});
		JButton botonEliminarNodo = new JButton("Eliminar nodo");
		botonEliminarNodo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
				if (nodoSeleccionado != null) {
					modeloArbol.removeNodeFromParent(nodoSeleccionado);
				}
			}
		});
		JButton botonModificarNodo = new JButton("Modificar nodo");
		botonModificarNodo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
				if (nodoSeleccionado != null) {
					nodoSeleccionado.setUserObject(textField.getText());
					modeloArbol.nodeChanged(nodoSeleccionado);
					
				}
			}
		});
		JPanel panelBotones = new JPanel();
		textField = new JTextField("", 20);
		panelBotones.add(botonAnyadirNodo);
		panelBotones.add(botonEliminarNodo);
		panelBotones.add(botonModificarNodo);
		panelBotones.add(textField);
		ventana.add(panelBotones, BorderLayout.SOUTH);

		
		ventana.setVisible(true);
	}
	

}
