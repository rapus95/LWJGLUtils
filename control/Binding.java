package control;

public class Binding implements Updateable {

	protected double state;
	protected double delta;

	private final Key defaultKey;
	private boolean locked;

	private Key key;

	public static Binding create(Key key) {
		return create(key, null);
	}
	
	public static Binding createAndDefault(Key key) {
		return create(key, key);
	}

	public static Binding create(Key key, Key defaultKey) {
		Binding b = new Binding(defaultKey);
		if (b.setKey(key))
			return b;
		return null;
	}

	private Binding(Key defaultKey) {
		this.defaultKey = defaultKey;
	}

	public void update() {
		double nstate = key.getState();
		delta = nstate - state;
		state = nstate;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean restoreDefault() {
		return setKey(defaultKey);
	}

	public boolean setKey(Key key) {
		if (key == null || locked)
			return false;
		this.key = key;
		update();
		update();
		return true;
	}

	public double getState() {
		return state;
	}

	public double getDelta() {
		return delta;
	}

	public boolean isPressed() {
		return state > 0.5;
	}

	public boolean isStartPressed() {
		return delta > 0.5;
	}

	public boolean isEndPressed() {
		return delta < -0.5;
	}

}