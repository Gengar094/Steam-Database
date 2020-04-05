package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.TerminalTransactionsDelegate;
import model.*;
import ui.Login;
import ui.TerminalTransactions;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Bank {
	private DatabaseConnectionHandler dbHandler = null;
	private Login loginWindow = null;
	private String username;
	private String password;

	public Bank(DatabaseConnectionHandler dch) {
		this.dbHandler = dch;
	}

//	/**
//	 * LoginWindowDelegate Implementation
//	 *
//     * connects to Oracle database with supplied username and password
//     */
//	public void login(String username, String password) {
//		boolean didConnect = dbHandler.login(username, password);
//
//		if (didConnect) {
//			// Once connected, remove login window and start text transaction flow
//			loginWindow.dispose();
//
//			TerminalTransactions transaction = new TerminalTransactions();
//			transaction.setupDatabase(this);
//			transaction.showMainMenu(this);
//		} else {
//			loginWindow.handleLoginFailed();
//
//			if (loginWindow.hasReachedMaxLoginAttempts()) {
//				loginWindow.dispose();
//				System.out.println("You have exceeded your number of allowed attempts");
//				System.exit(-1);
//			}
//		}
//	}
	
	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Insert a branch with the given info
	 */
    public void insertBranch(BranchModel model) {
    	dbHandler.insertBranch(model);
    }

    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Delete branch with given branch ID.
	 */ 
    public void deleteBranch(int branchId) {
    	dbHandler.deleteBranch(branchId);
    }
    
    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Update the branch name for a specific ID
	 */

    public void updateBranch(int branchId, String name) {
    	dbHandler.updateBranch(branchId, name);
    }




	public void buyGame(PurchaseModel model) {
		dbHandler.buyGame(model);
	}

	public void refundGame(String playerId, String appId) throws IOException {
		dbHandler.refundGame(playerId, appId);
	}

	/*
	public ResultSet listPurchasedGames() {
		return dbHandler.listPurchasedGames();
	}
	*/

	public void listPurchasedGames(){
		//read from ResultSet
	}

	public ResultSet getAllGroupThePlayerHas(String playerID) { return dbHandler.getAllGroupThePlayerHas(playerID); }

	public void addSelfToGroup(InGroupModel model) {
		dbHandler.addSelfToGroup(model);
	}

	public void removeSelfFromGroup(String gname, String playerId) {
		dbHandler.removeSelfFromGroup(gname, playerId);
	}

	public void giftItem(TradeModel model) {
		dbHandler.giftItem(model);
	}




    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Displays information about varies bank branches.
	 */
    public void showBranch() {
    	BranchModel[] models = dbHandler.getBranchInfo();
    	
    	for (int i = 0; i < models.length; i++) {
    		BranchModel model = models[i];
    		
    		// simplified output formatting; truncation may occur
    		System.out.printf("%-10.10s", model.getId());
    		System.out.printf("%-20.20s", model.getName());
    		if (model.getAddress() == null) {
    			System.out.printf("%-20.20s", " ");
    		} else {
    			System.out.printf("%-20.20s", model.getAddress());
    		}
    		System.out.printf("%-15.15s", model.getCity());
    		if (model.getPhoneNumber() == 0) {
    			System.out.printf("%-15.15s", " ");
    		} else {
    			System.out.printf("%-15.15s", model.getPhoneNumber());
    		}
    		
    		System.out.println();
    	}
    }
	
    /**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that it is done with what it's 
     * doing so we are cleaning up the connection since it's no longer needed.
     */ 
    public void terminalTransactionsFinished() {
    	dbHandler.close();
    	dbHandler = null;
    	
    	System.exit(0);
    }
    
    /**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that the user is fine with dropping any existing table
     * called branch and creating a new one for this project to use
     */ 
	public void databaseSetup() {
		dbHandler.databaseSetup();;
		
	}
    
	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		Login log = new Login();
		log.main();
	}
}
