import java.awt.*;
import javax.swing.*;

import java.util.*;
import java.awt.event.*;
import java.sql.*;

/**
 * 
 * @author Joshua
 *
 */
public class Gui {
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_14";
	public static final String LOGIN_NAME = "csc371_14";
	public static final String PASSWORD = "Password14";
	private static Connection m_dbConn = null;
	private static Random ran = new Random();


	
	private static JFrame sliver = new JFrame("The Database Accessor");
	private static JPanel addItemPanel = new JPanel();
	private static JPanel selectCharacterPanel = new JPanel();
	private static JPanel modifyRoomsPanel = new JPanel();
	private static JPanel addCharItemPanel = new JPanel();
	private static JTabbedPane tabbedPane = new JTabbedPane();


	private static final Color green = new Color(13, 123, 6);
	private static final Color niceBlue = new Color(66, 179, 245);
	private static final Color mountainDew = new Color(194, 245, 66);
	private static final Color Charcoal = new Color(66, 66, 66);

	private final static int WINDOW_SIZE_X = 900;
	private final static int WINDOW_SIZE_Y = 600;

	public static void main(String[] args) throws SQLException {
		m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);

//		insertStatement("ALTER TABLE Item CASCADE ON DELETE CASCADE ON UPDATE");
		
		sliver.setDefaultCloseOperation(1);
		sliver.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);

		setupAddItemPanel();
		setupSelectCharacterPanel();
		setupModifyRoomsPanel();
		setupaddCharItemPanel();

		tabbedPane.addTab("Add/Remove/Edit Item", addItemPanel);
		tabbedPane.addTab("Select a Char", selectCharacterPanel);
		tabbedPane.addTab("Modify Rooms", modifyRoomsPanel);
		tabbedPane.addTab("Add Items to Char", addCharItemPanel);

		sliver.add(tabbedPane);

		sliver.setResizable(false);
		sliver.setVisible(true);
	}

	/**
	 * Panel that gives Characters Items
	 * @author Dan
	 * @throws SQLException
	 */
	private static void setupaddCharItemPanel() throws SQLException {
		addCharItemPanel.setBackground(Charcoal);
		
		ArrayList<String> charList = new ArrayList<String>();
		ArrayList<String> charsItemList = new ArrayList<String>();
		ArrayList<String> availableItemList = new ArrayList<String>();
		
		/* grabs lists of characters from the database */
		charList = selectStatement("SELECT * FROM Kharacter", "Name");

		JComboBox charsBox = new JComboBox(charList.toArray());
		
		
		JButton insertButton = new JButton("Give Item");
		JButton removeButton = new JButton("Take Item");
		String selectedChar = (String) charsBox.getSelectedItem();

		charsItemList = selectStatement("SELECT ITEM FROM Character_Item AS cI WHERE cI.C_Name = '" + selectedChar + "'", "Item");
		
		JComboBox listOfCharsItems = new JComboBox();
		DefaultComboBoxModel charsItemsModel = new DefaultComboBoxModel(charsItemList.toArray());
		listOfCharsItems.setModel(charsItemsModel);
				
		JTable tableOfCharsItems = new JTable(10, 1);
		JTable tableOfAvailableItems = new JTable(30, 1);
		
		JComboBox listOfAvailableItems = new JComboBox();
		DefaultComboBoxModel availableItemsModel = new DefaultComboBoxModel(availableItemList.toArray());
		listOfAvailableItems.setModel(availableItemsModel);
		
		/* give the selected item to the selected character */
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You pressed the create button");
				String selectedItem = (String) tableOfAvailableItems.getValueAt(tableOfAvailableItems.getSelectedRow(), tableOfAvailableItems.getSelectedColumn());
				String selectedChar = (String) charsBox.getSelectedItem();
				try {
					insertStatement("INSERT INTO Character_Item VALUES ('" + selectedChar + "', " + selectedItem + ", 'Storage')");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// update the lists
				System.out.println("Give " + selectedItem + " to " + selectedChar);
				updateListsForCharItemPanel(charsBox, tableOfCharsItems, tableOfAvailableItems);
			}

		});
		
		/* removes the selected item from the character */
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You pressed the create button");
				String selectedChar = (String) charsBox.getSelectedItem();
				String selectedItem = (String) tableOfCharsItems.getValueAt(tableOfCharsItems.getSelectedRow(), tableOfCharsItems.getSelectedColumn());
				try {
					insertStatement("DELETE FROM Character_Item WHERE Item = " + selectedItem + " AND C_Name = '" + selectedChar + "'");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<String> charItems = new ArrayList<String>();
				try {
					charItems = selectStatement("SELECT ITEM FROM Character_Item AS cI WHERE cI.C_Name = '" + selectedChar + "'", "Item");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int temp = 0;
				for(int i = 0; i < charItems.size(); i++) {
						tableOfCharsItems.setValueAt(charItems.get(i), i, 0);
						temp = i;
				}
				for(int i = temp + 1; i < 10; i++)
				{
					tableOfCharsItems.setValueAt(null, i, 0);
				}
				System.out.println("Take " + selectedItem + " from " + selectedChar);
				updateListsForCharItemPanel(charsBox, tableOfCharsItems, tableOfAvailableItems);
			}
		});
		charsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateListsForCharItemPanel(charsBox, tableOfCharsItems, tableOfAvailableItems);
				
			}
		});
		addCharItemPanel.add(charsBox);
		addCharItemPanel.add(tableOfCharsItems);
		addCharItemPanel.add(removeButton);
		addCharItemPanel.add(tableOfAvailableItems);
		addCharItemPanel.add(insertButton);

	}
	
	private static void updateListsForCharItemPanel(JComboBox charsBox, JTable tableOfCharsItem, JTable tableOfAvailableItems)
	{
		String selectedChar = (String) charsBox.getSelectedItem();
		ArrayList<String> charItems = new ArrayList<String>();
		try {
			charItems = selectStatement("SELECT Item FROM Character_Item AS cI WHERE cI.C_Name = '" + selectedChar + "'", "Item");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int temp = 0;
		for(int i = 0; i < charItems.size(); i++) {
				tableOfCharsItem.setValueAt(charItems.get(i), i, 0);
				temp = i;
		}
		for(int i = temp + 1; i < 10; i++)
		{
			tableOfCharsItem.setValueAt(null, i, 0);
		}
		
		ArrayList<String> itemsAvail = new ArrayList<String>();
		try {

			itemsAvail = selectStatement("SELECT DISTINCT ItemID FROM Item AS i, Character_Item AS cI WHERE NOT(i.ItemID = cI.Item)", "ItemID");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i = 0; i < itemsAvail.size(); i++) {
			tableOfAvailableItems.setValueAt(itemsAvail.get(i), i, 0);
			temp = i;
		}
		for(int i = temp + 1; i < 30; i++)
		{
			tableOfAvailableItems.setValueAt(null, i, 0);
		}
	}

	/**
	 * Adds and removes items from the database
	 * @author Marlee
	 * @throws SQLException 
	 */
	private static void setupAddItemPanel() throws SQLException {
		String listOfItems[] = { "Narsil", "Uzi", "Excalibur", "Sting", "Cowboy Hat", "Chaps", "Shoe", "Super Salad",
				"Pickle", "Buffalo Sauce", "Sliver cards" };

		ArrayList<String> ItemInDatabaseList = new ArrayList<String>();

		/* grabs items from the database */
		ItemInDatabaseList = selectStatement("SELECT * FROM Item", "ItemID");
		
		JTable itemAttributes = new JTable(2, 2);
		itemAttributes.setValueAt("Weight", 0, 0);
		itemAttributes.setValueAt("Volume", 1, 0);

		JTable itemsInDatabaseTable = new JTable(30, 1);
		int temp = 0;
		for(int i = 0; i < ItemInDatabaseList.size(); i++) 
		{
			itemsInDatabaseTable.setValueAt(ItemInDatabaseList.get(i), i, 0);
			temp = i;
		}
		for(int i = temp + 1; i < 30; i++)
		{
			itemsInDatabaseTable.setValueAt(null, i, 0);
		}
		JComboBox itemsAvailableBox = new JComboBox(listOfItems);

		JButton createButton = new JButton("Create Item");
		JButton removeButton = new JButton("Remove Item");
		JButton updateButton = new JButton("Update Item");
		JButton selectButton = new JButton("Select");   
		addItemPanel.setBackground(green);

		/* based on the item current selected in the table will update the table with the weight and volume of the selected item */
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) itemsInDatabaseTable.getValueAt(itemsInDatabaseTable.getSelectedRow(), itemsInDatabaseTable.getSelectedColumn());
				try {
					itemAttributes.setValueAt(selectStatement("SELECT Weight FROM Item WHERE ItemID = " + selectedItem, "Weight"), 0, 1);
					itemAttributes.setValueAt(selectStatement("SELECT Volume FROM Item WHERE ItemID = " + selectedItem, "Volume"), 1, 1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		/* will create an item with predetermined weight and volume */
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedItem = (String) itemsAvailableBox.getSelectedItem();
				System.out.println("Insert " + selectedItem + " into the database");
				ArrayList<String> listForSize = new ArrayList<String>();

				try {
					listForSize = selectStatement("SELECT * FROM Item", "ItemID");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					insertStatement("INSERT INTO Item (ItemID, Weight, Volume, Loc_ID) VALUES (" + (ran.nextInt((1000 - 20) + 1) + 20) + ", 10, 10, 5);");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayList<String> updateInDatabaseList = new ArrayList<String>();

				try {
					updateInDatabaseList = selectStatement("SELECT * FROM Item", "ItemID");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int temp = 0;
				for(int i = 0; i < updateInDatabaseList.size(); i++) 
				{
					itemsInDatabaseTable.setValueAt(updateInDatabaseList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 30; i++)
				{
					itemsInDatabaseTable.setValueAt(null, i, 0);
				}
				// INSERT into database selectedItem
			}
		});
		
		/* will remove the selected item from the database */
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) itemsInDatabaseTable.getValueAt(itemsInDatabaseTable.getSelectedRow(), itemsInDatabaseTable.getSelectedColumn());
				System.out.println("Remove " + selectedItem + " from the database");
				try {
					//FIX THIS!!!!
					insertStatement("DELETE FROM Item WHERE ItemID = " + selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayList<String> updateInDatabaseList = new ArrayList<String>();

				try {
					updateInDatabaseList = selectStatement("SELECT * FROM Item", "ItemID");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int temp = 0;
				for(int i = 0; i < updateInDatabaseList.size(); i++) 
				{
					itemsInDatabaseTable.setValueAt(updateInDatabaseList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 30; i++)
				{
					itemsInDatabaseTable.setValueAt(null, i, 0);
				}
				// remove from database selectedItem
			}
		});
		/* will change the weight of volume of the selected items with the items with the input updated by the user */
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) itemsInDatabaseTable.getValueAt(itemsInDatabaseTable.getSelectedRow(), itemsInDatabaseTable.getSelectedColumn());
				System.out.println("Give " + selectedItem + " a " + itemAttributes.getValueAt(0, 0) + " value of " + itemAttributes.getValueAt(0, 1));
				System.out.println("Give " + selectedItem + " a " + itemAttributes.getValueAt(1, 0) + " value of " + itemAttributes.getValueAt(1, 1));
				
				itemAttributes.getValueAt( 0, 1);
				itemAttributes.getValueAt( 1, 1);

				try {
					insertStatement("UPDATE Item SET Weight = " + itemAttributes.getValueAt( 0, 1) + ", Volume = " + itemAttributes.getValueAt( 1, 1) + " WHERE ItemID = " + selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// remove from database selectedItem
			}
		});

		addItemPanel.add(itemsInDatabaseTable);
		addItemPanel.add(selectButton);
		addItemPanel.add(removeButton);
		addItemPanel.add(itemAttributes);
		addItemPanel.add(updateButton);
		addItemPanel.add(itemsAvailableBox);
		addItemPanel.add(createButton);
		// JPanel listItems = new JPanel();
	}

	/**
	 * Shows and allows edits to character attributes
	 * @author Taryn
	 * @throws SQLException
	 */
	private static void setupSelectCharacterPanel() throws SQLException {
		selectCharacterPanel.setBackground(niceBlue);
		ArrayList<String> charList = new ArrayList<String>();
		/* grabs the list of characters from the databse */
		charList = selectStatement("SELECT * FROM Kharacter", "Name");

		//JComboBox charactersList = new JComboBox(listOfItems);
		JButton updateButton = new JButton("Update");
		
		JComboBox charactersList = new JComboBox(charList.toArray());
		JTable charAttributes = new JTable(4, 2);
		charAttributes.setValueAt("Strongth", 0, 0);
		charAttributes.setValueAt("Stamina", 1, 0);
		charAttributes.setValueAt("Current HP", 2, 0);
		charAttributes.setValueAt("Max HP", 3, 0);
		

		charactersList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedChar = (String) charactersList.getSelectedItem();

				System.out.println("You selected " + selectedChar);
				
//				Get Attribute values from select statements with selectedChar
				try {
					charAttributes.setValueAt(selectStatement("SELECT Strength FROM Kharacter WHERE Name = '" + selectedChar + "'", "Strength"), 0, 1); //Strength
					charAttributes.setValueAt(selectStatement("SELECT Stamina FROM Kharacter WHERE Name = '" + selectedChar + "'", "Stamina"), 1, 1); //Stamina
					charAttributes.setValueAt(selectStatement("SELECT Current_Hit_Points FROM Kharacter WHERE Name = '" + selectedChar + "'", "Current_Hit_Points"), 2, 1); //Current HP
					charAttributes.setValueAt(selectStatement("SELECT Max_Hit_Points FROM Kharacter WHERE Name = '" + selectedChar + "'", "Max_Hit_Points"), 3, 1); //Max HP
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}


		});
		/* insert the new input of stamina, current hp, max hp, from the user and put into the database */
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedChar = (String) charactersList.getSelectedItem();
				for(int i = 0; i < 4; i++)
				{
					System.out.println("Give " + selectedChar + " a " + charAttributes.getValueAt(i, 0) + " value of " + charAttributes.getValueAt(i, 1));
					try {
						insertStatement("UPDATE Kharacter SET Strength = " + charAttributes.getValueAt( 0, 1) +
								", Stamina = " + charAttributes.getValueAt( 1, 1) + 
								", Current_Hit_Points = " + charAttributes.getValueAt( 2, 1) + 
								 ", Max_Hit_Points = " + charAttributes.getValueAt( 3, 1) + 
								 " WHERE Name = '" + selectedChar + "'");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Update selectedChar's charAttributes.getValueAt(i, 0) with charAttributes.getValueAt(i, 1)
				}
			}
		});

		selectCharacterPanel.add(charactersList);
		selectCharacterPanel.add(charAttributes);
		selectCharacterPanel.add(updateButton);
	}

	/**
	 * Lists stuff at a location and then allows you to remove or add items
	 * @author Joshua
	 * @throws SQLException 
	 */
	private static void setupModifyRoomsPanel() throws SQLException {
		modifyRoomsPanel.setBackground(mountainDew);

		/* list of items and creatures the user can add to location */
		String listOfStuffAtLocation[] = { "A Sword", "A Shield", "Chaps", "Lion", "Tiger", "Aardvark" };
		ArrayList<String> locationsList = new ArrayList<String>();
		ArrayList<String> creaturesAtLocationsList = new ArrayList<String>();
		ArrayList<String> stuffAtLocationsList = new ArrayList<String>();

		/* grabs the list of locations from the database*/
		locationsList = selectStatement("SELECT * FROM Location", "Type");
		
		JComboBox locationsBox = new JComboBox(locationsList.toArray());
		
		JTable creaturesAtLocationTable = new JTable(10, 1);
		JTable iteamsAtLocationTable = new JTable(10, 1);
		
		/* updates list of creatures and items at the location */
		int temp = 0;
		for(int i = 0; i < creaturesAtLocationsList.size(); i++) 
		{
			creaturesAtLocationTable.setValueAt(creaturesAtLocationsList.get(i), i, 0);
			temp = i;
		}
		for(int i = temp + 1; i < 10; i++)
		{
			creaturesAtLocationTable.setValueAt(null, i, 0);
		}
		temp = 0;
		for(int i = 0; i < stuffAtLocationsList.size(); i++) 
		{
			iteamsAtLocationTable.setValueAt(stuffAtLocationsList.get(i), i, 0);
			temp = i;
		}
		for(int i = temp + 1; i < 10; i++)
		{
			iteamsAtLocationTable.setValueAt(null, i, 0);
		}
		
		JComboBox possibleStuffBox = new JComboBox(listOfStuffAtLocation);
		
		JButton removeItemButton = new JButton("Remove Iten"); 
		JButton createButton = new JButton("Create");
		JButton removeCreatureButton = new JButton("Remove Creature");
		
		/* removes the selected item from the database */
		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIX THIS!!!!
				String selectedLocation = (String) locationsBox.getSelectedItem();
				String selectedThing = (String) iteamsAtLocationTable.getValueAt(iteamsAtLocationTable.getSelectedRow(), iteamsAtLocationTable.getSelectedColumn());
				
				try {
					insertStatement("DELETE FROM Item WHERE ItemID = " + selectedThing);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int temp = 0;
				for(int i = 0; i < stuffAtLocationsList.size(); i++) 
				{
					iteamsAtLocationTable.setValueAt(stuffAtLocationsList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 10; i++)
				{
					iteamsAtLocationTable.setValueAt(null, i, 0);
				}
				// remove selectedThing from selectedLocation
				// update the lists
			}
		});
		
		/* removes selected creature from the database */
		removeCreatureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIX THIS!!!!
				String selectedLocation = (String) locationsBox.getSelectedItem();
				
				String selectedCreature = (String) creaturesAtLocationTable.getValueAt(creaturesAtLocationTable.getSelectedRow(), creaturesAtLocationTable.getSelectedColumn());
				try {
					insertStatement("DELETE FROM Creature WHERE CreaID = " + selectedCreature);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int temp = 0;
				for(int i = 0; i < creaturesAtLocationsList.size(); i++) 
				{
					creaturesAtLocationTable.setValueAt(creaturesAtLocationsList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 10; i++)
				{
					creaturesAtLocationTable.setValueAt(null, i, 0);
				}
				// remove selectedThing from selectedLocation
				// update the lists
			}
		});
		
		/* creates a new item or creature based on the user and adds it into the selected location */
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIX THIS!!!!
				String selectedLocation = (String) locationsBox.getSelectedItem();
				String selectedThing = (String) possibleStuffBox.getSelectedItem();
				System.out.println("Add " + selectedThing + " to " + selectedLocation);
				ArrayList<String> listForSize = new ArrayList<String>();
				ArrayList<String> locID = new ArrayList<String>();
				
				try {
					locID = selectStatement("SELECT LocID FROM Location WHERE '" + selectedLocation + "' = Location.Type", "LocID");
					if(possibleStuffBox.getSelectedIndex() < 3)
					{
						insertStatement("INSERT INTO Item (ItemID, Weight, Volume, Loc_ID) VALUES (" + (ran.nextInt((1000 - 20) + 1) + 20) + ", 10, 10, " + locID.get(0) + " );  ");
					}else
					{
						insertStatement("INSERT INTO Creature (CreaID, Current_Hit_Points, Max_Hit_Points, Strength, Stamina, Amount_of_Protection, Clocation)"
								+ " VALUES (" + (ran.nextInt((1000 - 20) + 1) + 20) + ", 43, 83, 12, 98, 43, " + locID.get(0) + ");  ");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Add selected Item to selectedLocation
				// update the lists
				int temp = 0;
				for(int i = 0; i < creaturesAtLocationsList.size(); i++) 
				{
					creaturesAtLocationTable.setValueAt(creaturesAtLocationsList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 10; i++)
				{
					creaturesAtLocationTable.setValueAt(null, i, 0);
				}
				temp = 0;
				for(int i = 0; i < stuffAtLocationsList.size(); i++) 
				{
					iteamsAtLocationTable.setValueAt(stuffAtLocationsList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 10; i++)
				{
					iteamsAtLocationTable.setValueAt(null, i, 0);
				}
			}
		});
		locationsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIX THIS!!!!
				String selectedLocation = (String) locationsBox.getSelectedItem();
				ArrayList<String> creaturesList = new ArrayList<String>();
				ArrayList<String> itemsList = new ArrayList<String>();
				try {
					creaturesList = selectStatement("SELECT CreaID FROM Creature as c, Location as l WHERE l.Type = '" + selectedLocation + "' AND c.CLocation = l.LocID", "CreaID");
					itemsList = selectStatement("SELECT ItemID FROM Item as i, Location as l WHERE l.Type = '" + selectedLocation + "' AND i.Loc_ID = l.LocID", "ItemID");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int temp = 0;
				for(int i = 0; i < creaturesList.size(); i++) 
				{
					creaturesAtLocationTable.setValueAt(creaturesList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 10; i++)
				{
					creaturesAtLocationTable.setValueAt(null, i, 0);
				}
				temp = 0;
				for(int i = 0; i < itemsList.size(); i++) 
				{
					iteamsAtLocationTable.setValueAt(itemsList.get(i), i, 0);
					temp = i;
				}
				for(int i = temp + 1; i < 10; i++)
				{
					iteamsAtLocationTable.setValueAt(null, i, 0);
				}
				//Update list with creatures and items at location
			}
		});

		modifyRoomsPanel.add(locationsBox);
		modifyRoomsPanel.add(creaturesAtLocationTable);
		modifyRoomsPanel.add(iteamsAtLocationTable);
		modifyRoomsPanel.add(removeCreatureButton);
		modifyRoomsPanel.add(removeItemButton);
		modifyRoomsPanel.add(possibleStuffBox);
		modifyRoomsPanel.add(createButton);
	}

	public static ArrayList<String> selectStatement(String statement, String cols) throws SQLException {
		ArrayList<String> results = new ArrayList<String>();
		PreparedStatement stmt = m_dbConn.prepareStatement(statement);
		ResultSet rs = stmt.executeQuery(statement);
		while (rs.next()) {
			results.add(rs.getString(cols));
		}
		return results;
	}
	
	private static void insertStatement(String statement) throws SQLException {
		PreparedStatement stmt = m_dbConn.prepareStatement(statement);
		stmt.execute();
	}

}
