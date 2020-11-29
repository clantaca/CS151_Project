package coolGame;

import coolGame.controller.CombatController;
import coolGame.controller.Message;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;
import coolGame.view.CombatView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameTester {

	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
	private static CombatView view;
	private static Player player;
	private static Enemy enemy;

	public static void main(String[] args) {
		view = CombatView.init(queue);
		player = new Player("Tester");
		enemy = new Enemy(1);
		CombatController controller = new CombatController(view, enemy, player, queue);

		controller.mainLoop();
		view.dispose();
		queue.clear();
	}

/*
		Player player = new Player("Jon");
		MapView mapView = new MapView(player);
		NotificationView notificationView = new NotificationView();
		MapController controller = new MapController(mapView, player);*/
	
}

