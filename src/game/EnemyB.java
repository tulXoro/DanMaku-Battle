package game;

public abstract class EnemyB extends GameObject{
	protected boolean brittle = true;
	protected int damage;
	protected int eneHP;
	protected EnemyID id;
	
	public EnemyB(int x, int y, int damage, int eneHP, Game game) {
		super(x, y, game);
		this.damage = damage; //allows enemies to deal damage
		this.eneHP = eneHP; //and have HP
	}
	//methods that could be useful for all enemies and I dont want to rewrite these so here they are
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	//kinda useless rn, should remove later
	public EnemyID getID() {
		return id;
	}
	
	public int getEneHP() {
		return eneHP;
	}
	
	public void setEneHP(int eneHP) {
		this.eneHP = eneHP;
	}
	
	public boolean isBrittle(){
		return brittle;
	}
	
	public void setBrittle(boolean brittle){
		this.brittle = brittle;
	}
}
