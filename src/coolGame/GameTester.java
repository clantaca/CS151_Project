package coolGame;

import coolGame.controller.Controller;
import coolGame.controller.Message;
import coolGame.model.Map;
import coolGame.model.character.Player;
import coolGame.view.MapView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Test class for operating the game
 */
public class GameTester {

    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static MapView mapView;
    private static Player player;
    private static Map map;

    /**
     * Main method in constructing the game by initializing the player and map with the view and controller
     *
     * @param args
     */
    public static void main(String[] args) {
        player = new Player("You are here");
        map = new Map(player, 1);
        mapView = MapView.init(player, map, queue);

        Controller controller = new Controller(mapView, player, queue);

        controller.mainLoop();
    }

}