package src.com.KovaxG.test;

public class Maybe<T> {
	private boolean _isNothing;
	private T _just;
	
	public Maybe(T just) {
		_just = just;
		_isNothing = false;
	}
	
	public Maybe() {
		_just = null;
		_isNothing = true;
	}
	
	public boolean Nothing() {return _isNothing;}
	public T Just() {return _just;}
}
