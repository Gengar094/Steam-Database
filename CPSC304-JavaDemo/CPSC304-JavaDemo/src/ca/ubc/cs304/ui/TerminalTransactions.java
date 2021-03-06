package ca.ubc.cs304.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class TerminalTransactions {
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	
	private BufferedReader bufferedReader = null;
	private TerminalTransactionsDelegate delegate = null;

	public TerminalTransactions() {
	}
	
	/**
	 * Sets up the database to have a branch table with two tuples so we can insert/update/delete from it.
	 * Refer to the databaseSetup.sql file to determine what tuples are going to be in the table.
	 */
	public void setupDatabase(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while(choice != 1 && choice != 2) {
			System.out.println("If you have a table called Branch in your database (capitialization of the name does not matter), it will be dropped and a new Branch table will be created.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");
			
			choice = readInteger(false);
			
			if (choice != INVALID_INPUT) {
				switch (choice) {
				case 1:  
					delegate.databaseSetup(); 
					break;
				case 2:  
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.\n");
					break;
				}
			}
		}
	}

	/**
	 * Displays simple text interface
	 */ 
	public void showMainMenu(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		
	    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while (choice != 5) {
			System.out.println();
			System.out.println("1. Insert branch");
			System.out.println("2. Delete branch");
			System.out.println("3. Update branch name");
			System.out.println("4. Show branch");
			System.out.println("5. Quit");
			System.out.println("6. Gift item");
			System.out.println("7. Refund game");
			System.out.println("8. Buy game");
			System.out.println("9. Add self to group");
			System.out.println("10. Remove self from group");
			System.out.println("11. Show Purchased Games (Not working now)");
			System.out.print("Please choose one of the above 11 options: ");

			choice = readInteger(false);

			System.out.println(" ");

			if (choice != INVALID_INPUT) {
				switch (choice) {
					case 1: {
						handleInsertOption();
						break;
					}
					case 2: {
						handleDeleteOption();
						break;
					}
					case 3: {
						handleUpdateOption();
						break;
					}
					case 4: {
						delegate.showBranch();
						break;
					}
					case 5: {
						handleQuitOption();
						break;
					}
					case 6: {
						handleGiftItem();
						break;
					}
					case 7: {
						handleRefundGame();
						break;
					}
					case 8: {
						handleBuyGame();
						break;
					}
					case 9: {
						handleAddSelfToGroup();
						break;
					}
					case 10: {
						handleRemoveSelfFromGroup();
						break;
					}
					case 11: {
						//delegate.
						break;
					}
					default: {
						System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
						break;
					}
				}
			}
		}
	}


	private void handleRefundGame(){
		String playerID = null;
		while (playerID == null || playerID.length() <= 0) {
			System.out.print("Please enter the Player ID you wish to refund from: ");
			playerID= readLine().trim();
		}

		String appID = null;
		while (appID == null || appID.length() <= 0) {
			System.out.print("Please enter the App ID you wish to refund: ");
			appID= readLine().trim();
		}

		delegate.refundGame(playerID, appID);
	}

	private void handleBuyGame(){
		String playerID = null;
		while (playerID == null || playerID.length() <= 0) {
			System.out.print("Please enter the Player ID you wish to buy for: ");
			playerID= readLine().trim();
		}

		String appID = null;
		while (appID == null || appID.length() <= 0) {
			System.out.print("Please enter the App ID you wish to buy: ");
			appID= readLine().trim();
		}

		PurchaseModel model = new PurchaseModel( playerID,
				appID,
				java.time.LocalDate.now().toString());

		delegate.buyGame(model);
	}

	private void handleAddSelfToGroup(){
		String gname = null;
		while (gname == null || gname.length() <= 0) {
			System.out.print("Please enter the Group Name you wish to update: ");
			gname = readLine().trim();
		}

		String  playerID = null;
		while (playerID == null || playerID.length() <= 0) {
			System.out.print("Please enter the Player ID you wish to update: ");
			playerID = readLine().trim();
		}

		InGroupModel model = new InGroupModel(gname, playerID);
		delegate.addSelfToGroup(model);
	}

	private void handleRemoveSelfFromGroup(){
		String gname = null;
		while (gname == null || gname.length() <= 0) {
			System.out.print("Please enter the Group Name you wish to delete: ");
			gname = readLine().trim();
		}

		String  playerID = null;
		while (playerID == null || playerID.length() <= 0) {
			System.out.print("Please enter the Player ID you wish to update: ");
			playerID = readLine().trim();
		}

		delegate.removeSelfFromGroup(gname, playerID);
	}

	private void handleGiftItem(){
		String giverID = null;
		while (giverID == null || giverID.length() <= 0) {
			System.out.print("Please enter the Giver ID you wish to update: ");
			giverID = readLine().trim();
		}

		String  receiverID = null;
		while (receiverID == null || receiverID.length() <= 0) {
			System.out.print("Please enter the Receiver ID you wish to update: ");
			receiverID = readLine().trim();
		}

		String itemID = null;
		while (itemID == null || itemID.length() <= 0) {
			System.out.print("Please enter the Item ID you wish to update: ");
			itemID = readLine().trim();
		}

		TradeModel model = new TradeModel(	giverID,
				receiverID,
				itemID,
				java.time.LocalDate.now().toString());

		delegate.giftItem(model);
	}



	
	private void handleDeleteOption() {
		int branchId = INVALID_INPUT;
		while (branchId == INVALID_INPUT) {
			System.out.print("Please enter the branch ID you wish to delete: ");
			branchId = readInteger(false);
			if (branchId != INVALID_INPUT) {
				delegate.deleteBranch(branchId);
			}
		}
	}
	
	private void handleInsertOption() {
		int id = INVALID_INPUT;
		while (id == INVALID_INPUT) {
			System.out.print("Please enter the branch ID you wish to insert: ");
			id = readInteger(false);
		}
		
		String name = null;
		while (name == null || name.length() <= 0) {
			System.out.print("Please enter the branch name you wish to insert: ");
			name = readLine().trim();
		}
		
		// branch address is allowed to be null so we don't need to repeatedly ask for the address
		System.out.print("Please enter the branch address you wish to insert: ");
		String address = readLine().trim();
		if (address.length() == 0) {
			address = null;
		}
		
		String city = null;
		while (city == null || city.length() <= 0) {
			System.out.print("Please enter the branch city you wish to insert: ");
			city = readLine().trim();
		}
		
		int phoneNumber = INVALID_INPUT;
		while (phoneNumber == INVALID_INPUT) {
			System.out.print("Please enter the branch phone number you wish to insert: ");
			phoneNumber = readInteger(true);
		}
		
		BranchModel model = new BranchModel(address,
											city,
											id,
											name,
											phoneNumber);
		delegate.insertBranch(model);
	}
	
	private void handleQuitOption() {
		System.out.println("Good Bye!");
		
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}
		
		delegate.terminalTransactionsFinished();
	}
	
	private void handleUpdateOption() {
		int id = INVALID_INPUT;
		while (id == INVALID_INPUT) {
			System.out.print("Please enter the branch ID you wish to update: ");
			id = readInteger(false);
		}
		
		String name = null;
		while (name == null || name.length() <= 0) {
			System.out.print("Please enter the branch name you wish to update: ");
			name = readLine().trim();
		}

		delegate.updateBranch(id, name);
	}
	
	private int readInteger(boolean allowEmpty) {
		String line = null;
		int input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Integer.parseInt(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}
	
	private String readLine() {
		String result = null;
		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result;
	}
}
