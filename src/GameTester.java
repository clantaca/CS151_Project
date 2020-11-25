
public class GameTester {

	public static void main(String[] args) {
		
		Player player = new Player("Jon");
		MapView mapView = new MapView(player);
		NotificationView notificationView = new NotificationView();
		MapController controller = new MapController(mapView, player);
	}
	
}

