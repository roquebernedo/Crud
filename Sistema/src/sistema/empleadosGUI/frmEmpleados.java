package sistema.empleadosGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sistema.empleadosBL.empleadosBL;
import sistema.empleadosDAL.conexion;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class frmEmpleados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTable tblEmpleados;
	
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmEmpleados frame = new frmEmpleados();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public frmEmpleados() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setBounds(10, 30, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 79, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Correo:");
		lblNewLabel_2.setBounds(10, 103, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(10, 117, 86, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				conexion objConexion = new conexion();
				
				empleadosBL oEmpleados = recuperarDatosGUI();
				
				String strSentenciaInsert = String.format("INSERT INTO Empleados (ID, Nombre, Correo) "
						+ "VALUES (null, '%s', '%s')",oEmpleados.getID(),oEmpleados.getNombre(),oEmpleados.getCorreo());
				
				objConexion.ejecutarSentenciaSQL(strSentenciaInsert);
				
			}
		});
		btnAgregar.setBounds(10, 145, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 145, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(220, 145, 89, 23);
		contentPane.add(btnBorrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(319, 145, 89, 23);
		contentPane.add(btnCancelar);
		
		tblEmpleados = new JTable();
		tblEmpleados.setBounds(10, 179, 398, 102);
		contentPane.add(tblEmpleados);
		
		
		
		//modelo.addColumn("ID");
		//modelo.addColumn("Nombre");
		//modelo.addColumn("Correo");
		
		String[] titulos = {"ID", "Nombre", "Correo"};
		modelo = new DefaultTableModel(null, titulos);
		
		tblEmpleados.setModel(modelo);
		
		
		
		mostrarDatos();
	}
	
	public void mostrarDatos() {
		conexion objConexion = new conexion();
		try {
			ResultSet resultado = objConexion.consultarRegistros("SELECT * FROM Empleados");
			
			while (resultado.next()) {
				System.out.println(resultado.getString("ID"));
				System.out.println(resultado.getString("Nombre"));
				System.out.println(resultado.getString("Correo"));
				
				
				Object[] oUsuario = {resultado.getString("ID"),resultado.getString("Nombre"),resultado.getString("Correo")};
				
				modelo.addRow(oUsuario);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public empleadosBL recuperarDatosGUI() {
		empleadosBL oEmpleados = new empleadosBL();
		
		int ID = (txtID.getText().isEmpty()) ? 0 : Integer.parseInt(txtID.getText());
		
		oEmpleados.setID(ID);
		oEmpleados.setNombre(txtNombre.getText());
		oEmpleados.setCorreo(txtCorreo.getText());
		
		return oEmpleados;
	}

}
