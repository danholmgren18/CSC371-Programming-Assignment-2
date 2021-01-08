import java.sql.*;

public class Runner {
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_14";
	public static final String LOGIN_NAME = "csc371_14";
	public static final String PASSWORD = "Password14";
	
	protected static Connection m_dbConn = null;
	
	public static void main(String args[]) throws SQLException {
		createTables();
		System.out.println("Created All Tables");
	}

	private static void createTables() throws SQLException {
		Connection m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		Statement stmt = m_dbConn.createStatement();

		String people = "CREATE TABLE IF NOT EXISTS People" +
		"(Login		VARCHAR(24)  	NOT NULL," +
		"Email		VARCHAR(25)  	NOT NULL," +
		"Password	VARCHAR(24) 	NOT NULL," + 
		"PRIMARY KEY (Login))";
		stmt.execute(people);
		
		String player = "CREATE TABLE IF NOT EXISTS Player" + 
		"(pUsername		VARCHAR(24)		NOT NULL," +
		"C_name			VARCHAR(25)," 			   +
		"PlaID			INT				NOT NULL," + 
		"PRIMARY KEY(PlaID))";
		stmt.execute(player);
		
		String location = "CREATE TABLE IF NOT EXISTS Location" +
		"(LocID			INT				NOT NULL," +
	    "Size			INT," +					
		"Type			VARCHAR(25)," + 	
		"PRIMARY KEY (LocID))";
		stmt.execute(location);
		
		String ability = "CREATE TABLE IF NOT EXISTS Ability" +
		"(AbID                INT			NOT NULL," + 
		"Amount_of_effect   INT," +
		"Execution_time     DECIMAL(10,2)," + 
		"Type               VARCHAR(15)," + 
		"Stat               VARCHAR(10)," +
		"PRIMARY KEY(AbID))";
		stmt.execute(ability);
		
		String creature = "CREATE TABLE IF NOT EXISTS Creature" + 
		"(CreaID               	INT		NOT NULL," + 
		"Current_hit_points 	INT," + 
		"Max_hit_points     	INT," + 
		"Strength           	INT," + 
		"Stamina            	INT," + 
		"Amount_of_Protection   INT," + 
		"Clocation              INT		NOT NULL," + 
		"PRIMARY KEY(CreaID)," + 
		"FOREIGN KEY(Clocation) REFERENCES Location(LocID))";
		stmt.execute(creature);
		
		String areaType = "CREATE TABLE IF NOT EXISTS Area_Type" + 
		"(Crea_ID       INT				NOT NULL," + 
		"Type           VARCHAR(9)		NOT NULL," + 
		"Like_Hate      BOOLEAN," + 
		"PRIMARY KEY(Crea_ID, Type))";
		stmt.execute(areaType);
		
		String peopleAbility = "CREATE TABLE IF NOT EXISTS People_Ability" + 
		"(P_Login		VARCHAR(24)		NOT NULL," + 
		"P_Type			VARCHAR(12)		NOT NULL," + 
		"PRIMARY KEY (P_Login, P_Type) )";
		stmt.execute(peopleAbility);
		
		String kharacter = "CREATE TABLE IF NOT EXISTS Kharacter" + 
		"(Name					VARCHAR(25)		NOT NULL," + 
		"Strength				INT," + 
		"Stamina				INT," + 
		"Max_hit_points			INT," + 
		"Current_hit_points		INT," + 
		"Loc_ID 				INT				NOT NULL," +
		"PRIMARY KEY(Name)," + 
		"FOREIGN KEY(Loc_ID) REFERENCES Location(LocID))";
		stmt.execute(kharacter);
		
		String playersCharacter = "CREATE TABLE IF NOT EXISTS Players_Character" + 
		"(P_username	VARCHAR(24)		NOT NULL," + 
		"C_name			VARCHAR(25))";
		stmt.execute(playersCharacter);
		
		String creatureAbility = "CREATE TABLE IF NOT EXISTS Creature_Ability" + 
		"(Creature_ID		INT				NOT NULL," + 
		"Ability_Type		INT				NOT NULL," + 
		"FOREIGN KEY(Creature_ID) REFERENCES Creature(CreaID)," + 
		"FOREIGN KEY(Ability_Type) REFERENCES Ability(AbID)," + 
		"PRIMARY KEY (Creature_ID, Ability_Type))";
		stmt.execute(creatureAbility);
		
		String item = "CREATE TABLE IF NOT EXISTS Item" + 
		"(ItemID		INT 		NOT NULL AUTO_INCREMENT," + 
		"Weight			INT," + 
		"Volume			INT," + 
		"Loc_ID			INT			NOT NULL, " + 
		"PRIMARY KEY (ItemID), " + 
		"FOREIGN KEY (Loc_ID) REFERENCES Location(LocID)" + 
		"ON DELETE CASCADE)";
		stmt.execute(item);
		
		String generic = "CREATE TABLE IF NOT EXISTS Generic " + 
		"(GenID			INT			NOT NULL," + 
		"ItemID         INT			NOT NULL," + 
		"PRIMARY KEY (GenID)," + 
		"FOREIGN KEY(ItemID) REFERENCES Item(ItemID) ON DELETE CASCADE)";
		stmt.execute(generic);
		
		String charItem = "CREATE TABLE IF NOT EXISTS Character_Item" + 
		"(C_Name		VARCHAR(25)		NOT NULL," + 
		"Item			INT				NOT NULL," + 
		"Location		VARCHAR(9)," + 
		"PRIMARY KEY(C_Name, Item)," + 
		"FOREIGN KEY(C_Name) REFERENCES Kharacter(name)," + 
		"FOREIGN KEY(Item) REFERENCES Item(ItemID) ON DELETE CASCADE)";
		stmt.execute(charItem);
		
		String exitLocation = "CREATE TABLE IF NOT EXISTS Exit_Location" + 
		"(Loc_ID			INT			NOT NULL," + 
		"Destination		INT			NOT NULL," + 
		"PRIMARY KEY(Loc_ID, Destination)," + 
		"FOREIGN KEY(Loc_ID) REFERENCES Location(LocID))";
		stmt.execute(exitLocation);
		
		String likesHates = "CREATE TABLE IF NOT EXISTS Likes_Hates" + 
		"(Type         		 BOOLEAN," + 
		"Create_ID  	   	INT				NOT NULL," + 
		"P_userID	      	INT             NOT NULL," + 
		"PRIMARY KEY(Create_ID, P_userID)," + 
		"FOREIGN KEY(Create_ID) REFERENCES Creature(CreaID)," + 
		"FOREIGN KEY(P_userID) REFERENCES Player(PlaID))";
		stmt.execute(likesHates);
		
		String weapon = "CREATE TABLE IF NOT EXISTS Weapon" + 
		"(WeapID                INT				NOT NULL," + 
		"Successful_hit        	BOOLEAN," + 
		"Amount_of_effect    	INT," + 
		"Location               VARCHAR(9)," + 
		"Ability                INT				NOT NULL," + 
		"PRIMARY KEY(WeapID)," + 
		"FOREIGN KEY(WeapID) REFERENCES Item(ItemID) ON DELETE CASCADE," + 
		"FOREIGN KEY(Ability) REFERENCES Ability(AbID))";
		stmt.execute(weapon);
		
		String container = "CREATE TABLE IF NOT EXISTS Container" + 
		"(ConID              INT				NOT NULL," + 
		"Weight_limit 	   	INT," + 
		"Volume_limit  		INT,		" + 
		"ItemID             INT," + 
		"PRIMARY KEY(ConID)," + 
		"FOREIGN KEY(ItemID) REFERENCES Item(ItemID) ON DELETE CASCADE)";
		stmt.execute(container);
		
		String armor = "CREATE TABLE IF NOT EXISTS Armor" + 
		"(ArmID                 INT			NOT NULL," + 
		"Location_worn       VARCHAR(25)," + 
		"Amount_of_damage  	 INT," + 
		"ItemID              INT			NOT NULL," + 
		"PRIMARY KEY(ArmID)," + 
		"FOREIGN KEY(ItemID) REFERENCES Item(ItemID) ON DELETE CASCADE)";
		stmt.execute(armor);
		
		String itemInContainer = "CREATE TABLE IF NOT EXISTS Item_in_Container" + 
		"(Con_ID          INT(8)			NOT NULL," + 
		"Item             VARCHAR(15)		NOT NULL," + 
		"PRIMARY KEY(Con_ID, Item))";
		stmt.execute(itemInContainer);
		
		String addColumn = "ALTER TABLE Kharacter ADD p_ID INT NOT NULL";
		stmt.execute(addColumn);
		String updateKharacter = "ALTER TABLE Kharacter ADD FOREIGN KEY (p_ID) REFERENCES Player(PlaID)";
		stmt.execute(updateKharacter);
		
		String updatePC = "ALTER TABLE Players_Characters ADD FOREIGN KEY (P_username) REFERENCES Player(pUsername)";
		
		stmt.close();
		m_dbConn.close();
	}
}