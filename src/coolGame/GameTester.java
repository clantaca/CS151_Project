package coolGame;

import coolGame.controller.MapController;
import coolGame.controller.NotificationView;
import coolGame.model.character.Player;
import coolGame.view.MapView;

public class GameTester {

	public static void main(String[] args) {
		
		Player player = new Player("Jon");
		MapView mapView = new MapView(player);
		NotificationView notificationView = new NotificationView();
		MapController controller = new MapController(mapView, player);
	}
	
}

