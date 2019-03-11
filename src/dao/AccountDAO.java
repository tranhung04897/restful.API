package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.Account;

public class AccountDAO {
	DataAccess instanceSQL = DataAccess.getInstance();
	
	public List<Account> getListAccount() throws SQLException {
		ArrayList<Account> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM accounts";
			result = stmt.executeQuery(query);
			Account account = null;
			while (result.next()) {
				account = new Account();
				account.setId(result.getString("id"));
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("password"));
				account.setRole(result.getString("role"));
				list.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}

	public Account getAccount(String id) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		Account account = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM accounts WHERE id = " + id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				account = new Account();
				account.setId(result.getString("id"));
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("password"));
				account.setRole(result.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return account;
	}

	public boolean insertAccount(String username, String password, String role) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO accounts(username, password, role) VALUES(?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, username);
			preStm.setString(2, password);
			preStm.setString(3, role);

			if (preStm.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (preStm != null) {
				preStm.close();
			}
		}
	}

	public boolean updateAccount(String id, String username, String password, String role) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE accounts SET username = ?, password = ?, role = ? WHERE id = ?";
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, username);
			preStm.setString(2, password);
			preStm.setString(3, role);
			preStm.setString(4, id);
			if (preStm.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (preStm != null) {
				preStm.close();
			}
		}
	}

	public boolean deleteAccount(String id) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "DELETE FROM accounts WHERE id = ?";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, id);
			if (preStm.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (preStm != null) {
				preStm.close();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}