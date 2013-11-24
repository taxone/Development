package it.claudio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Prova {

	public void test(){
		List<String> l;
		l = new ArrayList<>();
		
		for (Iterator<String> iterator = l.iterator(); iterator.hasNext();) {
			String s = iterator.next();
			System.out.println(s.toCharArray());
		}
	}
}
