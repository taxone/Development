package it.claudio;

public interface Service {

	 <T extends Base> void  f(Generic<T> t);
	
	void f2(Generic<Base> g);
	
	void f3(Generic<? extends Base> p);
}
