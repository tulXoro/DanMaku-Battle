package game;

public abstract class EnemyB extends GameObject{
	protected int damage;
	protected int EneHP=1;
	protected EnemyID id;
	
	public EnemyB(int x, int y, int damage, int EneHP, Game game) {
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
