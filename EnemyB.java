package game;

public abstract class EnemyB extends GameObject{
	
	//TODO determine whether something is tank w/ bool
	protected int damage;
	protected EnemyID id;
	protected int EneHP=1;
	
	public EnemyB(int x, int y, Game game) {
		super(x, y, game);
	}
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public EnemyID getID() {
		return id;
	}
	
	public int getEneHP() {
		return EneHP;
	}
	
	public void setEneHP(int EneHP) {
		this.EneHP = 0;
	}
}
