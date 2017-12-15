import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.css.RGBColor;

public class Principal extends JFrame implements ActionListener {
	JLabel lCode, lDescription, lQuantity, lUnit;
	JButton bCreate, bRemove;
	JTextField tCode, tDescription, tQuantity;
	JComboBox cUnit;
	JList list;
	JPanel buttonPane, createPane;
	RegisterDao dao;
	
	ImageIcon createIcon = new ImageIcon("img/create.png");
	ImageIcon deleteIcon = new ImageIcon("img/delete.png");

	public Principal() {
		setTitle("Sistema de Gerenciamento de Materiais");
		setBounds(350, 100, 700, 550);

		list = new JList();
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(10, 10, 10, 10), "Materiais", 2, 1,
				new Font("Serif", Font.ITALIC, 25)));
		list.setFont(new Font("Serif", Font.ITALIC, 15));
		list.setForeground(new Color(218,50,65));

		lCode = new JLabel("Código: ");
		lDescription = new JLabel("Descrição: ");
		lQuantity = new JLabel("Quantidade: ");
		lUnit = new JLabel("Unidade: ");

		tCode = new JTextField();
		tDescription = new JTextField();
		tQuantity = new JTextField();
		String[] unit = { "m²", "m³", "kg", "l", "unidade" };
		cUnit = new JComboBox(unit);
		
		lCode.setFont(new Font("Serif", Font.ITALIC, 18));
		lDescription.setFont(new Font("Serif", Font.ITALIC, 18));
		lQuantity.setFont(new Font("Serif", Font.ITALIC, 18));
		cUnit.setFont(new Font("Serif", Font.ITALIC, 18));
		lUnit.setFont(new Font("Serif", Font.ITALIC, 18));
		
		createPane = new JPanel();
		createPane.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(10, 10, 10, 10),
				"Para realizar um novo cadastro ou atualizar, preencha os dados: ", 2, 1,
				new Font("Serif", Font.ITALIC, 18)));
		
		createPane.setLayout(new GridLayout(4, 2));
		createPane.add(lCode);
		createPane.add(tCode);
		createPane.add(lDescription);
		createPane.add(tDescription);
		createPane.add(lQuantity);
		createPane.add(tQuantity);
		createPane.add(lUnit);
		createPane.add(cUnit);

		bCreate = new JButton("Cadastrar", createIcon);
		bRemove = new JButton("Deletar", deleteIcon);
		
		bCreate.setHorizontalTextPosition(JButton.LEFT);
		bRemove.setHorizontalTextPosition(JButton.LEFT);
		
		bCreate.setFont(new Font("Serif", Font.ITALIC, 18));
		bRemove.setFont(new Font("Serif", Font.ITALIC, 18));

		bCreate.addActionListener(this);
		bRemove.addActionListener(this);
		
		buttonPane = new JPanel();
		
		buttonPane.add(bCreate);
		buttonPane.add(bRemove);

		getContentPane().setLayout(new GridLayout(3, 1));
		add(scrollPane);
		add(createPane);
		add(buttonPane);
		
		dao = new RegisterDaoJDBC();
		carregarModelo(dao.registers());
	}

	public void carregarModelo(List<Register> registers) {
		DefaultListModel model = new DefaultListModel();
		for (Register register : registers) {
			model.addElement(register);
		}
		list.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bCreate) {
			carregarModelo(dao.registers());
			try {
				int code = Integer.parseInt(tCode.getText().trim());
			String description = tDescription.getText().trim();
			int quantity = Integer.parseInt(tQuantity.getText().trim());
			String unit = (String) cUnit.getSelectedItem();
			if (description.equals("")) {
				JOptionPane.showMessageDialog(null, "O campo nome não pode ser vazio!!!!!!");
				return;
			
			}
			dao.inserir(new Register(code, description, quantity, unit));
			} catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "o valor do campo não é válido!!!");
			}
			carregarModelo(dao.registers());
		}
		if (e.getSource() == bRemove) {
			int index = list.getSelectedIndex();
			if (index == -1) {
				JOptionPane.showMessageDialog(null, "Selecione um item para ser removido");
				return;
			}
			int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o item selecionado?",
					"remover o elemento selecionado?", JOptionPane.YES_NO_OPTION);
			if (op == JOptionPane.YES_OPTION) {
				Register register = (Register) list.getSelectedValue();
				dao.remover(register.getItem());
				carregarModelo(dao.registers());
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new Principal();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
