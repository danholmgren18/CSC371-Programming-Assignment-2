import java.sql.*;

public class TheInsert {
	
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_14";
	public static final String LOGIN_NAME = "csc371_14";
	public static final String PASSWORD = "Password14";
	protected static Connection m_dbConn = null;

	public static void main(String args[]) throws SQLException {
		fillTable();
		
		System.out.println("All Tables Filled");
	}

	private static void fillTable() throws SQLException {
		Connection m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		PreparedStatement stmt;
		// 5 inserts for people
			String insertPeople1 = "INSERT INTO People VALUES ('coolguy12', 'coolguyATgmail.com', 'secretsoftie')";
			stmt = m_dbConn.prepareStatement(insertPeople1);
			stmt.executeUpdate();
			String insertPeople2 = "INSERT INTO People VALUES ('badguy123', 'billieATgmail.com', 'dududududu')";
			stmt = m_dbConn.prepareStatement(insertPeople2);
			stmt.executeUpdate();
			String insertPeople3 = "INSERT INTO People VALUES ('alex123', 'alexwATgmail.com', 'hiiamalex')";
			stmt = m_dbConn.prepareStatement(insertPeople3);
			stmt.executeUpdate();
			String insertPeople4 = "INSERT INTO People VALUES ('hithereitsame', 'marioATgmail.com', 'secretlyluigi')";
			stmt = m_dbConn.prepareStatement(insertPeople4);
			stmt.executeUpdate();
			String insertPeople5 = "INSERT INTO People VALUES ('rando12345', 'mysteriousATgmail.com', 'guesswho')";
			stmt = m_dbConn.prepareStatement(insertPeople5);
			stmt.executeUpdate();
	
			// 5 inserts for player
			String insertPlayer1 = "INSERT INTO Player VALUES ('coolguy12', 'Coolio', 1)";
			stmt = m_dbConn.prepareStatement(insertPlayer1);
			stmt.executeUpdate();
			String insertPlayer2 = "INSERT INTO Player VALUES ('badguy123', 'BillieFan1', 2)";
			stmt = m_dbConn.prepareStatement(insertPlayer2);
			stmt.executeUpdate();
			String insertPlayer3 = "INSERT INTO Player VALUES ('alex123', 'AlexanderTheGreat', 3)";
			stmt = m_dbConn.prepareStatement(insertPlayer3);
			stmt.executeUpdate();
			String insertPlayer4 = "INSERT INTO Player VALUES ('hithereitsme', 'guesswhoItMe', 4)";
			stmt = m_dbConn.prepareStatement(insertPlayer4);
			stmt.executeUpdate();
			String insertPlayer5 = "INSERT INTO Player VALUES ('rando12345', 'Mr.Stranger', 5)";
			stmt = m_dbConn.prepareStatement(insertPlayer5);
			stmt.executeUpdate();
	
			// 5 inserts for location
			String insertLocation1 = "INSERT INTO Location VALUES (5, 1000, 'Forest')";
			stmt = m_dbConn.prepareStatement(insertLocation1);
			stmt.executeUpdate();
			String insertLocation2 = "INSERT INTO Location VALUES (4, 800, 'Town')";
			stmt = m_dbConn.prepareStatement(insertLocation2);
			stmt.executeUpdate();
			String insertLocation3 = "INSERT INTO Location VALUES (3, 1200, 'City')";
			stmt = m_dbConn.prepareStatement(insertLocation3);
			stmt.executeUpdate();
			String insertLocation4 = "INSERT INTO Location VALUES (2, 1500, 'Mountain')";
			stmt = m_dbConn.prepareStatement(insertLocation4);
			stmt.executeUpdate();
			String insertLocation5 = "INSERT INTO Location VALUES (1, 1200, 'Woods')";
			stmt = m_dbConn.prepareStatement(insertLocation5);
			stmt.executeUpdate();
	
			// 5 inserts for ability
			String insertAbility1 = "INSERT INTO Ability VALUES (1, 20, .5, 'Flame', 'HP')";
			stmt = m_dbConn.prepareStatement(insertAbility1);
			stmt.executeUpdate();
			String insertAbility2 = "INSERT INTO Ability VALUES (2, 100, .5, 'Auto Heal', 'LP')";
			stmt = m_dbConn.prepareStatement(insertAbility2);
			stmt.executeUpdate();
			String insertAbility3 = "INSERT INTO Ability VALUES (3, 10, .1, 'Confusion', 'Speed')";
			stmt = m_dbConn.prepareStatement(insertAbility3);
			stmt.executeUpdate();
			String insertAbility4 = "INSERT INTO Ability VALUES (4, 40, .3, 'Super Strike', 'HP')";
			stmt = m_dbConn.prepareStatement(insertAbility4);
			stmt.executeUpdate();
			String insertAbility5 = "INSERT INTO Ability VALUES (5, 35, .5, 'Lightning', 'LP')";
			stmt = m_dbConn.prepareStatement(insertAbility5);
			stmt.executeUpdate();
	
			// 5 inserts for creature
			String insertCreature1 = "INSERT INTO Creature VALUES (1, 25, 40, 20, 20, 15, 5)";
			stmt = m_dbConn.prepareStatement(insertCreature1);
			stmt.executeUpdate();
			String insertCreature2 = "INSERT INTO Creature VALUES (2, 10, 15, 5, 5, 4, 4)";
			stmt = m_dbConn.prepareStatement(insertCreature2);
			stmt.executeUpdate();
			String insertCreature3 = "INSERT INTO Creature VALUES (3, 15, 25, 10, 10, 2, 3)";
			stmt = m_dbConn.prepareStatement(insertCreature3);
			stmt.executeUpdate();
			String insertCreature4 = "INSERT INTO Creature VALUES (4, 19, 26, 12, 12, 2, 2)";
			stmt = m_dbConn.prepareStatement(insertCreature4);
			stmt.executeUpdate();
			String insertCreature5 = "INSERT INTO Creature VALUES (5, 1, 100, 20, 20, 1, 1)";
			stmt = m_dbConn.prepareStatement(insertCreature5);
			stmt.executeUpdate();
	
			// 5 inserts for Area_Type
			String insertArea_Type1 = "INSERT INTO Area_Type VALUES (1, 'Woods', TRUE)";
			stmt = m_dbConn.prepareStatement(insertArea_Type1);
			stmt.executeUpdate();
			String insertArea_Type2 = "INSERT INTO Area_Type VALUES (2, 'Mountain', FALSE)";
			stmt = m_dbConn.prepareStatement(insertArea_Type2);
			stmt.executeUpdate();
			String insertArea_Type3 = "INSERT INTO Area_Type VALUES (3, 'City', FALSE)";
			stmt = m_dbConn.prepareStatement(insertArea_Type3);
			stmt.executeUpdate();
			String insertArea_Type4 = "INSERT INTO Area_Type VALUES (4, 'Town', FALSE)";
			stmt = m_dbConn.prepareStatement(insertArea_Type4);
			stmt.executeUpdate();
			String insertArea_Type5 = "INSERT INTO Area_Type VALUES (5, 'Forest', TRUE)";
			stmt = m_dbConn.prepareStatement(insertArea_Type5);
			stmt.executeUpdate();
	
			// 5 inserts for People_Ability
			String insertPeople_Ability1 = "INSERT INTO People_Ability VALUES ('coolguy12', 'Player')";
			stmt = m_dbConn.prepareStatement(insertPeople_Ability1);
			stmt.executeUpdate();
			String insertPeople_Ability2 = "INSERT INTO People_Ability VALUES ('badguy123', 'Player')";
			stmt = m_dbConn.prepareStatement(insertPeople_Ability2);
			stmt.executeUpdate();
			String insertPeople_Ability3 = "INSERT INTO People_Ability VALUES ('hithereitsme', 'Player')";
			stmt = m_dbConn.prepareStatement(insertPeople_Ability3);
			stmt.executeUpdate();
			String insertPeople_Ability4 = "INSERT INTO People_Ability VALUES ('alex123', 'Moderator')";
			stmt = m_dbConn.prepareStatement(insertPeople_Ability4);
			stmt.executeUpdate();
			String insertPeople_Ability5 = "INSERT INTO People_Ability VALUES ('rando12345', 'Player')";
			stmt = m_dbConn.prepareStatement(insertPeople_Ability5);
		stmt.executeUpdate();

		// 5 inserts for Kharacter
		String insertKharacter1 = "INSERT INTO Kharacter VALUES ('Curly the Crab', 20, 20, 25, 20, 1, 1)";
		stmt = m_dbConn.prepareStatement(insertKharacter1);
		stmt.executeUpdate();
		String insertKharacter2 = "INSERT INTO Kharacter VALUES ('Sassy Squid', 10, 22, 15, 12, 2, 2)";
		stmt = m_dbConn.prepareStatement(insertKharacter2);
		stmt.executeUpdate();
		String insertKharacter3 = "INSERT INTO Kharacter VALUES ('Cute Dragon', 15, 30, 20, 15, 2, 3)";
		stmt = m_dbConn.prepareStatement(insertKharacter3);
		stmt.executeUpdate();
		String insertKharacter4 = "INSERT INTO Kharacter VALUES ('Amy the Giraffe', 30, 30, 30, 25, 4, 4)";
		stmt = m_dbConn.prepareStatement(insertKharacter4);
		stmt.executeUpdate();
		String insertKharacter5 = "INSERT INTO Kharacter VALUES ('Sophie Snake', 17, 22, 30, 30, 5, 5)";
		stmt = m_dbConn.prepareStatement(insertKharacter5);
		stmt.executeUpdate();

		// 5 inserts for Players_Character
		String insertPlayers_Character1 = "INSERT INTO Players_Character VALUES ('coolguy12', 'Curly the Crab')";
		stmt = m_dbConn.prepareStatement(insertPlayers_Character1);
		stmt.executeUpdate();
		String insertPlayers_Character2 = "INSERT INTO Players_Character VALUES ('badguy123', 'Sassy Squid')";
		stmt = m_dbConn.prepareStatement(insertPlayers_Character2);
		stmt.executeUpdate();
		String insertPlayers_Character3 = "INSERT INTO Players_Character VALUES ('hithereitsme', 'Cute Dragon')";
		stmt = m_dbConn.prepareStatement(insertPlayers_Character3);
		stmt.executeUpdate();
		String insertPlayers_Character4 = "INSERT INTO Players_Character VALUES ('hithereitsme', 'Amy the Giraffe')";
		stmt = m_dbConn.prepareStatement(insertPlayers_Character4);
		stmt.executeUpdate();
		String insertPlayers_Character5 = "INSERT INTO Players_Character VALUES ('rando12345', 'Sophie Snake')";
		stmt = m_dbConn.prepareStatement(insertPlayers_Character5);
		stmt.executeUpdate();

		String insertCreature_Ability = "INSERT INTO Creature_Ability VALUES (3, 1)";
		stmt = m_dbConn.prepareStatement(insertCreature_Ability);
		stmt.executeUpdate();
		insertCreature_Ability = "INSERT INTO Creature_Ability VALUES (2, 1)";
		stmt = m_dbConn.prepareStatement(insertCreature_Ability);
		stmt.executeUpdate();
		insertCreature_Ability = "INSERT INTO Creature_Ability VALUES (1, 2)";
		stmt = m_dbConn.prepareStatement(insertCreature_Ability);
		stmt.executeUpdate();
		insertCreature_Ability = "INSERT INTO Creature_Ability VALUES (5, 3)";
		stmt = m_dbConn.prepareStatement(insertCreature_Ability);
		stmt.executeUpdate();
		insertCreature_Ability = "INSERT INTO Creature_Ability VALUES (4, 5)";
		stmt = m_dbConn.prepareStatement(insertCreature_Ability);
		stmt.executeUpdate();

		String insertItem = "INSERT INTO Item VALUES (1, 5, 6, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (2, 57, 20, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (3, 34, 55, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (4, 12, 12, 4)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (5, 95, 9, 1)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (6, 5, 6, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (7, 37, 20, 5)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (8, 34, 5, 5)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (9, 12, 12, 3)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (10, 93, 9, 3)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (11, 5, 6, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (12, 87, 10, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (13, 34, 5, 4)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (14, 2, 12, 3)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (15, 95, 9, 1)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (16, 5, 6, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (17, 87, 20, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (18, 34, 5, 2)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (19, 12, 12, 3)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();
		insertItem = "INSERT INTO Item VALUES (20, 95, 9, 1)";
		stmt = m_dbConn.prepareStatement(insertItem);
		stmt.executeUpdate();

		String insertGeneric = "INSERT INTO Generic VALUES (1, 1)";
		stmt = m_dbConn.prepareStatement(insertGeneric);
		stmt.executeUpdate();
		insertGeneric = "INSERT INTO Generic VALUES (2, 2)";
		stmt = m_dbConn.prepareStatement(insertGeneric);
		stmt.executeUpdate();
		insertGeneric = "INSERT INTO Generic VALUES (3, 3)";
		stmt = m_dbConn.prepareStatement(insertGeneric);
		stmt.executeUpdate();
		insertGeneric = "INSERT INTO Generic VALUES (4, 4)";
		stmt = m_dbConn.prepareStatement(insertGeneric);
		stmt.executeUpdate();
		insertGeneric = "INSERT INTO Generic VALUES (5, 5)";
		stmt = m_dbConn.prepareStatement(insertGeneric);
		stmt.executeUpdate();

		String insertCharacter_Item = "INSERT INTO Character_Item VALUES ('Curly the Crab', 1, 'Storage')";
		stmt = m_dbConn.prepareStatement(insertCharacter_Item);
		stmt.executeUpdate();
		insertCharacter_Item = "INSERT INTO Character_Item VALUES ('Sassy Squid', 2, 'Storage')";
		stmt = m_dbConn.prepareStatement(insertCharacter_Item);
		stmt.executeUpdate();
		insertCharacter_Item = "INSERT INTO Character_Item VALUES ('Sassy Squid', 3, 'Head')";
		stmt = m_dbConn.prepareStatement(insertCharacter_Item);
		stmt.executeUpdate();
		insertCharacter_Item = "INSERT INTO Character_Item VALUES ('Sophie Snake', 4, 'Storage')";
		stmt = m_dbConn.prepareStatement(insertCharacter_Item);
		stmt.executeUpdate();
		insertCharacter_Item = "INSERT INTO Character_Item VALUES ('Amy the Giraffe', 5, 'Legs')";
		stmt = m_dbConn.prepareStatement(insertCharacter_Item);
		stmt.executeUpdate();

		String insertExit_Location = "INSERT INTO Exit_Location VALUES (1, 2)";
		stmt = m_dbConn.prepareStatement(insertExit_Location);
		stmt.executeUpdate();
		insertExit_Location = "INSERT INTO Exit_Location VALUES (2, 3)";
		stmt = m_dbConn.prepareStatement(insertExit_Location);
		stmt.executeUpdate();
		insertExit_Location = "INSERT INTO Exit_Location VALUES (3, 4)";
		stmt = m_dbConn.prepareStatement(insertExit_Location);
		stmt.executeUpdate();
		insertExit_Location = "INSERT INTO Exit_Location VALUES (4, 5)";
		stmt = m_dbConn.prepareStatement(insertExit_Location);
		stmt.executeUpdate();
		insertExit_Location = "INSERT INTO Exit_Location VALUES (5, 1)";
		stmt = m_dbConn.prepareStatement(insertExit_Location);
		stmt.executeUpdate();

		String insertLikes_Hates = "INSERT INTO Likes_Hates VALUES (TRUE, 1, 1)";
		stmt = m_dbConn.prepareStatement(insertLikes_Hates);
		stmt.executeUpdate();
		insertLikes_Hates = "INSERT INTO Likes_Hates VALUES (FALSE, 2, 3)";
		stmt = m_dbConn.prepareStatement(insertLikes_Hates);
		stmt.executeUpdate();
		insertLikes_Hates = "INSERT INTO Likes_Hates VALUES (TRUE, 3, 3)";
		stmt = m_dbConn.prepareStatement(insertLikes_Hates);
		stmt.executeUpdate();
		insertLikes_Hates = "INSERT INTO Likes_Hates VALUES (TRUE, 4, 3)";
		stmt = m_dbConn.prepareStatement(insertLikes_Hates);
		stmt.executeUpdate();
		insertLikes_Hates = "INSERT INTO Likes_Hates VALUES (FALSE, 5, 4)";
		stmt = m_dbConn.prepareStatement(insertLikes_Hates);
		stmt.executeUpdate();

		String insertWeapon = "INSERT INTO Weapon VALUES (6, TRUE, 45, 'Storage', 1)";
		stmt = m_dbConn.prepareStatement(insertWeapon);
		stmt.executeUpdate();
		insertWeapon = "INSERT INTO Weapon VALUES (7, FALSE, 65, 'Storage', 1)";
		stmt = m_dbConn.prepareStatement(insertWeapon);
		stmt.executeUpdate();
		insertWeapon = "INSERT INTO Weapon VALUES (8, TRUE, 13, 'Hand', 2)";
		stmt = m_dbConn.prepareStatement(insertWeapon);
		stmt.executeUpdate();
		insertWeapon = "INSERT INTO Weapon VALUES (9, FALSE, 2, 'Storage', 3)";
		stmt = m_dbConn.prepareStatement(insertWeapon);
		stmt.executeUpdate();
		insertWeapon = "INSERT INTO Weapon VALUES (10, FALSE, 90, 'Hand', 4)";
		stmt = m_dbConn.prepareStatement(insertWeapon);
		stmt.executeUpdate();

		String insertContainer = "INSERT INTO Container VALUES (1, 100, 100, 11)";
		stmt = m_dbConn.prepareStatement(insertContainer);
		stmt.executeUpdate();
		insertContainer = "INSERT INTO Container VALUES (2, 10, 50, 12)";
		stmt = m_dbConn.prepareStatement(insertContainer);
		stmt.executeUpdate();
		insertContainer = "INSERT INTO Container VALUES (3, 50, 1000, 13)";
		stmt = m_dbConn.prepareStatement(insertContainer);
		stmt.executeUpdate();
		insertContainer = "INSERT INTO Container VALUES (4, 1050, 400, 14)";
		stmt = m_dbConn.prepareStatement(insertContainer);
		stmt.executeUpdate();
		insertContainer = "INSERT INTO Container VALUES (5, 500, 50, 15)";
		stmt = m_dbConn.prepareStatement(insertContainer);
		stmt.executeUpdate();

		String insertArmor = "INSERT INTO Armor VALUES (16, 'Head', 100, 1)";
		stmt = m_dbConn.prepareStatement(insertArmor);
		stmt.executeUpdate();
		insertArmor = "INSERT INTO Armor VALUES (17, 'Body', 50, 2)";
		stmt = m_dbConn.prepareStatement(insertArmor);
		stmt.executeUpdate();
		insertArmor = "INSERT INTO Armor VALUES (18, 'Legs', 90, 3)";
		stmt = m_dbConn.prepareStatement(insertArmor);
		stmt.executeUpdate();
		insertArmor = "INSERT INTO Armor VALUES (19, 'Hand', 20, 4)";
		stmt = m_dbConn.prepareStatement(insertArmor);
		stmt.executeUpdate();
		insertArmor = "INSERT INTO Armor VALUES (20, 'Legs', 900, 3)";
		stmt = m_dbConn.prepareStatement(insertArmor);
		stmt.executeUpdate();

		String insertItem_in_Container = "INSERT INTO Item_in_Container VALUES (16, 'Helmet')";
		stmt = m_dbConn.prepareStatement(insertItem_in_Container);
		stmt.executeUpdate();
		insertItem_in_Container = "INSERT INTO Item_in_Container VALUES (17, 'Leggings')";
		stmt = m_dbConn.prepareStatement(insertItem_in_Container);
		stmt.executeUpdate();
		insertItem_in_Container = "INSERT INTO Item_in_Container VALUES (6, 'Excalibur')";
		stmt = m_dbConn.prepareStatement(insertItem_in_Container);
		stmt.executeUpdate();
		insertItem_in_Container = "INSERT INTO Item_in_Container VALUES (7, 'Uzi')";
		stmt = m_dbConn.prepareStatement(insertItem_in_Container);
		stmt.executeUpdate();
		insertItem_in_Container = "INSERT INTO Item_in_Container VALUES (9, 'Narsil')";
		stmt = m_dbConn.prepareStatement(insertItem_in_Container);
		stmt.executeUpdate();

		stmt.close();
	}
}