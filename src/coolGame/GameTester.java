package coolGame;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import coolGame.controller.Controller;
import coolGame.controller.Message;
import coolGame.model.Map;
import coolGame.model.character.Player;
import coolGame.view.CombatView;
import coolGame.view.MapView;

public class GameTester {

	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
	private static MapView mapView;
	private static Player player;
	private static Map map;
	
	public static void main(String[] args) {
		player = new Player("Tester");
		map = new Map(player, 1);
		mapView = MapView.init(player, map, queue);

		Controller controller = new Controller(mapView, player, queue);
		
		controller.mainLoop();
	}
	
}