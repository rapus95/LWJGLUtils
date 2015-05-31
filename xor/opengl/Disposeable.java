package xor.opengl;

public abstract class Disposeable implements IDisposeable {

	private static final int DISPOSE = 1;
	private static final int DISPOSED = 2;
	private static final int DD_MASK = 3;

	private int flags;

	protected void markForDispose() {
		flags |= DISPOSE;
	}

	protected void unmarkForDispose() {
		flags &= ~DISPOSE;
	}

	@Override
	public final void dispose() {
		internDispose(false);
	}

	@Override
	protected final void finalize() throws Throwable {
		internDispose(true);
	}

	private void internDispose(boolean finalizer) {
		if ((flags & DD_MASK) != 1)
			return;
		flags |= DISPOSED;
		if (dispose(false)) {
			flags &= ~DISPOSED;
		}
	}

	protected boolean dispose(boolean finalizer) {
		return pdispose();
	}

	protected abstract boolean pdispose();

}
