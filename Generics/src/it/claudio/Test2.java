package it.claudio;

public class Test2 {

	public static void main(String[] args) {
		MyInterface<? extends Base> base;
		
		base = new MyImpl<A>();
		base = new MyImpl<B>();
		
		Base result = base.f();
		
	
	}
}
