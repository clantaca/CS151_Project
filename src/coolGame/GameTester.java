package coolGame;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import coolGame.controller.Controller;
import coolGame.controller.Message;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;
import coolGame.view.CombatView;
import coolGame.view.MapView;

public class GameTester {

	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
	private static MapView mapView;
	private static CombatView combatView;
	private static Player player;
	private static Enemy enemy;
	
	public static void main(String[] args) {
		player = new Player("Tester");
		enemy = new Enemy(1);
		mapView = MapView.init(player, queue);

		Controller controller = new Controller(mapView, player, queue);
		
		controller.mainLoop();
		mapView.dispose();
		combatView.dispose();
		queue.clear();
	}
	
}

