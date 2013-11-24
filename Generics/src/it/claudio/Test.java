package it.claudio;

import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		Generic<B> b = new Generic<B>(new B());
		Generic<A> a = new Generic<A>(new A());
		
		Generic<? extends Base> base = b;
		
		base.toString();
		
		Service s = new Service() {
			
			@Override
			public <T extends Base> void f(Generic<T> t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void f2(Generic<Base> g) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void f3(Generic<? extends Base> p) {
				// TODO Auto-generated method stub
				
			}
		};
		
		s.f(b);
		s.f(a);
		s.f(base);
		
		s.f2((Generic<Base>)base); //senza cast non funziona
		
		s.f3(a);
		s.f3(b);
		s.f3(base);
		
		List<String> stringlist = Collections.<String>emptyList();
		
		stringlist.toArray();
	}
}
