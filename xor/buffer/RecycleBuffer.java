package xor.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.TreeMap;
import java.util.Map.Entry;

public class RecycleBuffer {

	private static final TreeMap<Integer, RecycleBuffer> CACHE = new TreeMap<Integer, RecycleBuffer>();

	private static final TreeMap<Integer, RecycleBuffer> CACHE_DIRECT = new TreeMap<Integer, RecycleBuffer>();
	
	public static RecycleBuffer allocate(int size, boolean direct) {
		TreeMap<Integer, RecycleBuffer> cache = direct ? CACHE_DIRECT : CACHE;
		Entry<Integer, RecycleBuffer> e = cache.ceilingEntry(size);
		RecycleBuffer buffer;
		if (e == null) {
			buffer = new RecycleBuffer(direct ? ByteBuffer.allocateDirect(size) : ByteBuffer.allocate(size));
		} else {
			buffer = e.getValue();
			RecycleBuffer c = buffer.next;
			if (c == null) {
				cache.remove(e.getKey());
			} else {
				e.setValue(c);
			}
		}

		return buffer.reuse(size);
	}
	
	public static RecycleBuffer allocateDirect(int size) {
		return allocate(size, true);
	}
	
	private final ByteBuffer bb;

	int capacity;

	RecycleBuffer next;

	RecycleBuffer(ByteBuffer bb) {
		this.bb = bb;
		capacity = bb.capacity();
	}

	public ByteBuffer asByteBuffer() {
		checkActive();
		return bb;
	}

	public void recycle() {
		checkActive();
		capacity = -1;
		TreeMap<Integer, RecycleBuffer> cache = bb.isDirect() ? CACHE_DIRECT : CACHE;
		RecycleBuffer rb = cache.get(bb.capacity());
		if (rb == null) {
			cache.put(bb.capacity(), this);
		} else {
			next = rb.next;
			rb.next = this;
		}
	}

	public RecycleBuffer reuse(int size) {
		bb.order(ByteOrder.nativeOrder()).rewind().limit(size);
		capacity = size;
		return this;
	}

	public RecycleBuffer order(ByteOrder bo) {
		checkActive();
		bb.order(bo);
		return this;
	}

	public boolean isDirect() {
		checkActive();
		return bb.isDirect();
	}

	public int capacity() {
		checkActive();
		return capacity;
	}

	void checkActive() {
		if (capacity == -1)
			throw new IllegalStateException("Buffer is recycled");
	}

}
