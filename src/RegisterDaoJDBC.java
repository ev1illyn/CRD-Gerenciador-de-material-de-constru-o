import java.util.ArrayList;
import java.util.List;

public class RegisterDaoJDBC implements RegisterDao {

	@Override
	public boolean inserir(Register r) {
		String sql = "INSERT INTO register (code, description, quantity, unit) VALUES (" + r.getCode() + ",'"
				+ r.getDescription() + "'," + r.getQuantity() + ",'" + r.getUnit() + "')";
		return executaSQL(sql);
	}

	@Override
	public boolean remover(int item) {
		String sql = "DELETE FROM register WHERE item=" + item;
		return executaSQL(sql);
	}

	private boolean executaSQL(String sql) {
		boolean sucesso = false;
		if (BD.getConnection()) {
			if (BD.runSQL(sql) > 0) {
				sucesso = true;
			}
			BD.close();
		}
		return sucesso;
	}

	@Override
	public List<Register> registers() {
		String sql = "SELECT * FROM register";
		return executaConsulta(sql);
	}

	private List<Register> executaConsulta(String sql) {
		List<Register> registers = new ArrayList<Register>();
		if (BD.getConnection()) {
			try {
				BD.setResultSet(sql);
				while (BD.resultSet.next()) {
					int item = BD.resultSet.getInt("ITEM");
					int code = BD.resultSet.getInt("CODE");
					String description = BD.resultSet.getString("DESCRIPTION");
					int quantity = BD.resultSet.getInt("QUANTITY");
					String unit = BD.resultSet.getString("UNIT");
					registers.add(new Register(item, code, description, quantity, unit));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BD.close();
			}
		}
		return registers;
	}

}