package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Account;
import dao.AccountDAO;
public class AccountBO {
	
	AccountDAO dao = new AccountDAO();
	
	public List<Account> getListAccount() {
		List<Account> list = new ArrayList<>();
		try {
			list = dao.getListAccount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Account getAccount(String id) {
		Account account = null;
		try {
			account = dao.getAccount(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public boolean insertAccount(String username, String password, String role) {
		try {
			if (dao.insertAccount(username, password, role)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateAccount(String id, String username, String password, String role) {
		try {
			if (dao.updateAccount(id, username, password, role)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteAccount(String id) {
		try {
			if (dao.deleteAccount(id)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}