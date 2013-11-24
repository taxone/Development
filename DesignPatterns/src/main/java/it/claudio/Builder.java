package it.claudio;

import it.claudio.domain.ConcreteBaseModel;

public interface Builder <T extends ConcreteBaseModel>{

	T build();
}
