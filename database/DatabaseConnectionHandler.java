package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import ca.ubc.cs304.model.BranchModel;
import model.*;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	
	private Connection connection = null; // we need to initialize it , but it is abstract so... ora_yzh99 a19862648
	private String playerID; //added
    private String username;
    private String password;
	
	public DatabaseConnectionHandler(String username, String password) {
		this.username = username;
		this.password = password;
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new driver;
            // this.connection.setAutoCommit(false);
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println("hads");
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}


	// originally only String appId
	public void refundGame(String playerID, String appId) throws IOException{
		try {
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM purchase WHERE player_id = ? AND app_id = ?");
			ps.setString(1, playerID);
			ps.setString(2, appId);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + "player" + playerID + " do not have Game " + appId + "!");
				throw new IOException("No founded");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void buyGame(PurchaseModel model) throws SQLException {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO purchase VALUES (?,?,?)");
			ps.setString(1, model.getPlayer_id());
			ps.setString(2, model.getApp_id());
			ps.setString(3, java.time.LocalDate.now().toString());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}

	public ResultSet searchGame(String keyword) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT d.app_id, d.product_name, g.genre, d.price " +
			"FROM develop_product d, game g " +
			"WHERE d.app_id = g.app_id AND LOWER(d.product_name) LIKE '%" + keyword + "%' ORDER BY d.product_name";
			rs = stmt.executeQuery(query);

			// while(rs.next()) {
			// BranchModel model = new BranchModel(rs.getString("branch_addr"),
			// rs.getString("branch_city"),
			// rs.getInt("branch_id"),
			// rs.getString("branch_name"),
			// rs.getInt("branch_phone"));
			// result.add(model);
			// }

//			rs.close();
//			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return rs;
	}

	public ResultSet getPurchasedGamesInfo(String playerID) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT p.app_id, d.product_name, g.genre, d.dv_name, COUNT(a.player_id) AS Num_Ach_Unlcoked "
					+ "FROM purchase p LEFT JOIN (attain a JOIN has_achievement h ON a.ach_id = h.ach_id) ON p.player_id = a.player_id AND p.app_id = h.app_id, develop_product d, game g "
					+ "WHERE p.player_id = " + playerID + " AND p.app_id = d.app_id AND d.app_id = g.app_id GROUP BY (p.app_id, d.product_name, g.genre, d.dv_name) "
					+ "ORDER BY d.product_name";
			rs = stmt.executeQuery(query);

			// while(rs.next()) {
			// BranchModel model = new BranchModel(rs.getString("branch_addr"),
			// rs.getString("branch_city"),
			// rs.getInt("branch_id"),
			// rs.getString("branch_name"),
			// rs.getInt("branch_phone"));
			// result.add(model);
			// }

//			rs.close();
//			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return rs;
	}

	public ResultSet getAllGroupThePlayerHas(String playerID) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT pg.gname, pg.num_mem, pg.tag FROM player_group pg, in_group ig WHERE pg.gname = ig.gname AND ig.player_id = " + playerID + " ORDER BY pg.gname";
			rs = stmt.executeQuery(query);

//			 while(rs.next()) {
//			 	PlayerGroupModel model = new PlayerGroupModel(rs.getString("gname"),
//			 	rs.getInt("num_mem"),
//			 	rs.getString("tag"));
//			 }

//			rs.close();
//			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return rs;
	}

	public void addSelfToGroup(InGroupModel model) throws SQLException{
		try {
			PreparedStatement ps1 = connection.prepareStatement("INSERT INTO in_group VALUES(?, ?)");
			ps1.setString(1, model.getGname());
			ps1.setString(2, model.getPlayer_id());

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE player_group SET num_mem = num_mem + 1 WHERE gname = ?");
			ps2.setString(1, model.getGname());

			ps1.executeUpdate();
			int rowCount = ps2.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + "Group" + model.getGname() + " does not exist! ");
			}

			connection.commit();

			ps1.close();
			ps2.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}

	// originally only String gname
	public void removeSelfFromGroup(String gname, String playerID) throws Exception{
		try {
			PreparedStatement ps1 = connection.prepareStatement("DELETE FROM in_group WHERE gname = ? AND player_id = ?");
			ps1.setString(1, gname);
			ps1.setString(2, playerID);

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE player_group SET num_mem = num_mem - 1 WHERE gname = ?");
			ps2.setString(1, gname);

			int row = ps1.executeUpdate();
			int rowCount = ps2.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + "Player" + playerID + " does not exist in Group " + gname);
				throw new IOException();
			}
			if (row == 0) {
				throw new RuntimeException();
			}

			connection.commit();

			ps1.close();
			ps2.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}

	public void giftItem(TradeModel model) throws SQLException{
		try {
			PreparedStatement ps1 = connection
					.prepareStatement("UPDATE own_item SET player_id = ? WHERE item_id = ?");
			ps1.setString(1, model.getReceiver_id());
			ps1.setString(2, model.getItem_id());

			PreparedStatement ps2 = connection.prepareStatement("INSERT INTO trade VALUES(?, ?, ?, ?)");
			ps2.setString(1, model.getGiver_id());
			ps2.setString(2, model.getReceiver_id());
			ps2.setString(3, model.getItem_id());
			ps2.setString(4, java.time.LocalDate.now().toString());

			ps1.executeUpdate();
			int rowCount = ps2.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Item " + model.getItem_id() + " does not exist!");
			}

			connection.commit();

			ps1.close();
			ps2.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}

	public void writeReview(ReviewWritereviewModel model) throws SQLException {
		try {
			int nextID_from_seq = 0;
			String sql = "select review_id_counter.nextval from DUAL";
			PreparedStatement nextval = connection.prepareStatement(sql);
			ResultSet rs = nextval.executeQuery();
			if(rs.next()) nextID_from_seq = rs.getInt(1);

			PreparedStatement ps = connection.prepareStatement("INSERT INTO review_writereview VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, Integer.toString(nextID_from_seq));
			ps.setString(2, model.getRdate());
			ps.setBoolean(3, model.getRecommendation());
			ps.setString(4, model.getPlayer_id());
			ps.setString(5, model.getApp_id());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}

	public ResultSet readGameInfo(){
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT dp.app_id, dp.dv_name, dp.ddate, dp.product_name, dp.price, dp.base_game_name, g.genre "
					+ "FROM develop_product dp LEFT JOIN game g "
					+ "WHERE dp.app_id = g.app_id  GROUP BY dp.app_id "
					+ "ORDER BY dp.app_id";
			rs = stmt.executeQuery(query);

//			rs.close();
//			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return rs;
	}

	public PlayerGroupModel[] getGroupInfo() {
		ArrayList<PlayerGroupModel> result = new ArrayList<PlayerGroupModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM player_group");

			while(rs.next()) {
				PlayerGroupModel model = new PlayerGroupModel(rs.getString("gname"),
						rs.getInt("num_mem"),
						rs.getString("tag"));
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result.toArray(new PlayerGroupModel[result.size()]);
	}

	public ResultSet getPlayerInfo(String playerID){
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT p.player_id, p.pname, p.email, p.city, cc.country "
					+ "FROM player p, country_city cc "
					+ "WHERE p.city = cc.city AND p.player_id = " + playerID
					+ "ORDER BY p.player_id";
			rs = stmt.executeQuery(query);

//			rs.close();
//			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return rs;
	}

	public ResultSet getInventory(String playerID){
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT oi.item_id, oi.item_type FROM own_item oi, type_tradability tt WHERE oi.player_id = " + playerID + " AND oi.item_type = tt.item_type";
			rs = stmt.executeQuery(query);

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return rs;
	}


	public void deleteBranch(int branchId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
			ps.setInt(1, branchId);
			
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
			}
			
			connection.commit();
	
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	
	public void insertBranch(BranchModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
			ps.setInt(1, model.getId());
			ps.setString(2, model.getName());
			ps.setString(3, model.getAddress());
			ps.setString(4, model.getCity());
			if (model.getPhoneNumber() == 0) {
				ps.setNull(5, java.sql.Types.INTEGER);
			} else {
				ps.setInt(5, model.getPhoneNumber());
			}

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	
	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
		
//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
			while(rs.next()) {
				BranchModel model = new BranchModel(rs.getString("branch_addr"),
													rs.getString("branch_city"),
													rs.getInt("branch_id"),
													rs.getString("branch_name"),
													rs.getInt("branch_phone"));
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result.toArray(new BranchModel[result.size()]);
	}
	
	public void updateBranch(int id, String name) {
		try {
		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
		  ps.setString(1, name);
		  ps.setInt(2, id);
		
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
		  }
	
		  connection.commit();
		  
		  ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}	
	}
	
	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}
	
			this.connection = DriverManager.getConnection(ORACLE_URL, username, password);
			this.connection.setAutoCommit(false);
	
			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void databaseSetup() {

		//dropBranchTableIfExists();

		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		
		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
		insertBranch(branch1);
		
		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
		insertBranch(branch2);

	}
	
	private void dropBranchTableIfExists() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select table_name from user_tables");
			
			while(rs.next()) {
				if(rs.getString(1).toLowerCase().equals("branch")) {
					stmt.execute("DROP TABLE branch");
					break;
				}
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
