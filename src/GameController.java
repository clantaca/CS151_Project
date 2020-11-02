
public class GameController {
	
	private CombatView combatView;
	private Enemy enemy;
	private Player player;
	
	public GameController(CombatView combatView, Enemy enemy, Player player) {
		
		this.combatView = combatView;
		this.enemy = enemy;
		this.player = player;
		
		//this.combatView.addPhyAtkButListener(new PhyAtkListener());
		
	}
	
//	class PhyAtkListener implements ActionListner {
//		
//	}
	
}
