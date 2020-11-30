package coolGame;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import coolGame.controller.MapController;
import coolGame.controller.Message;
import coolGame.model.character.Player;
import coolGame.view.MapView;
import coolGame.view.NotificationView;

public class GameTester {

	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
	private static MapView view;
	private static Player player;

	public static void main(String[] args) {
		player = new Player("Tester in Tester");
		view = MapView.init(player, queue);

		MapController controller = new MapController(view, player, queue);
		
		NotificationView notificationView = new NotificationView();
		
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

