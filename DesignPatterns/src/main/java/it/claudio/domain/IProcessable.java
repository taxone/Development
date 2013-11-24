package it.claudio.domain;

public interface IProcessable {

	void accept(IProcessor processor);
}
