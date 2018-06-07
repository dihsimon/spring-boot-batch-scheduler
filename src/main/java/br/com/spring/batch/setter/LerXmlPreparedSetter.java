package br.com.spring.batch.setter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import br.com.spring.batch.model.User;

/**
 * Classe responsável por preparar o objeto para persistencia na base
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Carmona</a>
 * 6 de jun de 2018
 */
public class LerXmlPreparedSetter implements ItemPreparedStatementSetter<User>{

	@Override
	public void setValues(User item, PreparedStatement ps) throws SQLException {
		ps.setString(1, item.getName());
		ps.setTimestamp(2, item.getDateCreated());
	}
}
