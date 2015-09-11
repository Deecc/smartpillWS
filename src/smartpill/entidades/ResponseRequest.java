package smartpill.entidades;

public class ResponseRequest {
	private boolean success;
	private String mensagem;
	
	/*
	 * Construtores
	 * */
	
	// Mensagem de sucesso
	public ResponseRequest() {
		this.success = true;
		mensagem = "";
	}
	
	// Mensagem de erro
	public ResponseRequest(String mensagem) {
		super();
		this.success = false;
		this.mensagem = mensagem;
	}
	
	// Generico
	public ResponseRequest(boolean success, String mensagem) {
		super();
		this.success = success;
		this.mensagem = mensagem;
	}

	
	/*
	 * Acessadores
	 * */
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
