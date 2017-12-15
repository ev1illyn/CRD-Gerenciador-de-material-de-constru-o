import java.util.List;

public class TestaDB {
	public static void main(String[] args) {
		RegisterDaoJDBC dao = new RegisterDaoJDBC();
		List<Register> registers = dao.registers();
		String s = "Item\tCodigo\tDescrição\tQuantidade\tUnit\n-----\t---------\n";
		for (Register register : registers) {
			s += register.getItem() + "\t";
			s += register.getCode() + "\t";
			s += register.getDescription() + "\t";
			s += register.getQuantity() + "\t";
			s += register.getUnit() + "\t\n";
		}
		System.out.println(s);
	}
}
