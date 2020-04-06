package delegates;

import model.*;

import java.sql.ResultSet;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
	public void databaseSetup();
	
	public void deleteBranch(int branchId);
	public void insertBranch(BranchModel model);
	public void showBranch();
	public void updateBranch(int branchId, String name);

	public void buyGame(PurchaseModel model);
	public void refundGame(String playerID, String appId);
	//public ResultSet listPurchasedGames(); should be a void function that shows the purchased games
	public void listPurchasedGames();
	public ResultSet getAllGroupThePlayerHas(String playerID);
	public void addSelfToGroup(InGroupModel model);
	public void removeSelfFromGroup(String gname, String playerID);
	public void giftItem(TradeModel model);
	public ResultSet readGameInfo();
	public PlayerGroupModel[] getGroupInfo();
	public ResultSet getPlayerInfo(String playerID);
	public ResultSet getInventory(String playerID);
	public ResultSet searchReview(String appID);
	public void modifyProfile(String playerID, String field, String value);
	
	public void terminalTransactionsFinished();
}
