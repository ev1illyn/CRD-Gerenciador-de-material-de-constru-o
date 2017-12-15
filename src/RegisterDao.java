import java.util.List;

public interface RegisterDao {

	public boolean inserir(Register r);

	public boolean remover(int item);

	public List<Register> registers();
}
