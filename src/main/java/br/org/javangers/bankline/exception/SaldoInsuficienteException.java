package br.org.javangers.bankline.exception;

public class SaldoInsuficienteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SaldoInsuficienteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
