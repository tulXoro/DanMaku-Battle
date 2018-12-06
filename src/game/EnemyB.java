package game;

public abstract class EnemyB extends GameObject{
	protected boolean brittle = true;
	protected int damage;
	protected int eneHP;
	protected EnemyID id;
	
	public EnemyB(int x, int y, int damage, int eneHP, Game game) {
		super(x, y, game);
		this.damage = damage;
		this.eneHP = eneHP;
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
