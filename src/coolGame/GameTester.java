package coolGame;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import coolGame.controller.MapController;
import coolGame.controller.Message;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;
import coolGame.view.MapView;

public class GameTester {

	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
	private static MapView view;
	private static Player player;
	private static Enemy enemy;

	public static void main(String[] args) {
		view = MapView.init(queue);
		player = new Player("TESTER");
		enemy = new Enemy(1);
		MapController controller = new MapController(view, player, queue);

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

