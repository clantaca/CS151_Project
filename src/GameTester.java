
public class GameTester {

	public static void main(String[] args) {
		
		Enemy enemy = new Enemy();
		Player player = new Player("Jon");
		CombatView combatView = new CombatView(player, enemy);
		GameController controller = new GameController(combatView, enemy, player);
		
	}
	
}

