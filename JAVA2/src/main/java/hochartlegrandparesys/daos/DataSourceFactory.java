package hochartlegrandparesys.daos;


import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;



public class DataSourceFactory {

	private static MysqlDataSource dataSource;

	public static DataSource getDataSource() {
		/*
		 * Returns the database which contains the users and contacts
		 */
		if (dataSource == null) {
			dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("projetjava2");
			dataSource.setUser("root");
			dataSource.setPassword("");
		}
		return dataSource;
	}
}

