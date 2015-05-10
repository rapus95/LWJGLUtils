package control;

public class GamepadKey implements Key {

	private final String g;
	private Gamepad pad;
	private final int key;

	public GamepadKey(String g, int key) {
		this.g = g;
		this.key = key;
	}

	private void updatePad() {
		if (pad == null) {
			pad = Gamepad.get(g);
		}
	}

	@Override
	public double getState() {
		updatePad();
		return pad == null ? 0 : pad.getState(key);
	}

	@Override
	public String getName() {
		return g + ":" + key;
	}

	@Override
	public Type getType() {
		return Type.GAMEPAD;
	}

}
