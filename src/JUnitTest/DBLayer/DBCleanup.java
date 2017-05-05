package DBLayer;

import java.sql.SQLException;
import java.sql.Connection;

public class DBCleanup {
	
	public static void main(String[] args) throws SQLException {
		cleanDB(); // call to the utility class that resets the database
		System.out.println("cleaned");
	}
	
	public static void cleanDB() throws SQLException {
		
		Connection conn = DBConnection.getInstance().getDBcon();
		
		e(conn, "drop table Person");
		e(conn, "drop table Contractor");
		for(int i = 0 ; i < script.length; i++) {
			e(conn, script[i]);
		} 
	}

	private static void e(Connection conn, String sql) throws SQLException {
		conn.createStatement().executeUpdate(sql);
	}



	private static final String[] script = {
		"create table Person (\n" +
				"\t[id] [int] PRIMARY KEY IDENTITY(1,1) NOT NULL,\n" +
				"\t[name] [varchar](50) NOT NULL,\n" +
				"\t[address] [varchar](50) NOT NULL,\n" +
				"\t[email] [varchar](30) NOT NULL,\n" +
				"\t[city] [varchar](30) NOT NULL,\n" +
				"\t[category] [int] NOT NULL,\n" +
				"\t[phone] [nvarchar](10) NULL)",
		"create table Contractor (\n" +
				"\t[cvr] [int] PRIMARY KEY NOT NULL,\n" +
				"\t[person_id] [int] NOT NULL,\n" +
				"\tCONSTRAINT [Contractor_Person__fk_Cascade] FOREIGN KEY([person_id]) REFERENCES [dbo].[Person] ([id]) ON DELETE CASCADE)",
		"insert into Person values ('testName','someAddress','testMail@gmail.bg','123456','Aalborg',665544);",
		"insert into Contractor values(665544, 1);"
		/* TODO complete script with insert */
	};
}
