package es.studium.ElGusano;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class MenuInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInicio frame = new MenuInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MenuInicio() {
		setTitle("EL GUSANO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("JUGAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jugar();
			}
		});
		btnNewButton.setFont(new Font("Courier New", Font.BOLD, 12));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setBounds(54, 42, 100, 49);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("AYUDA");
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda();
			}
		});
		btnNewButton_1.setFont(new Font("Courier New", Font.BOLD, 12));
		btnNewButton_1.setBounds(54, 102, 100, 49);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("SALIR");
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setFont(new Font("Courier New", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		btnNewButton_2.setBounds(54, 162, 100, 49);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuInicio.class.getResource("/es/studium/ElGusano/img.png")));
		lblNewLabel.setBounds(164, 0, 260, 261);
		contentPane.add(lblNewLabel);
	}

	protected void ayuda() {
		JOptionPane.showMessageDialog(contentPane, "Usa las flechas para moverte y comer todas las manzanas posible","Ayuda",JOptionPane.INFORMATION_MESSAGE);
	}

	protected void jugar() {
		ElGusano gus = new ElGusano();
		gus.setVisible(true);
		
	}

	protected void salir() {
		System.exit(0);
		
	}
}

