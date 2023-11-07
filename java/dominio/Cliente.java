package dominio;



public class Cliente extends Pessoa {
	
	private String cpf;
	private Endereco endereco;
	private String telefone1;
	private String telefone2;
	private String dataNascimento;
	private String numCartao;
	private String DataValCartao;
	private String CVVCartao; 
	


	public Cliente(String nome, String cpf, Endereco endereco,  String telefone1, String telefone2, String dataNascimento, String numCartao, String DataValCartao, String CVVCartao) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone1=telefone1;
		this.telefone2=telefone2;
		this.dataNascimento=dataNascimento;
		this.numCartao=numCartao;
		this.DataValCartao=DataValCartao;
		this.CVVCartao=CVVCartao;
		
		
	}

		
	public Cliente() {
		// TODO Auto-generated constructor stub
	}


	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	
	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2= telefone2;
	}
	
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	 public String getDataNascimento() {
	        return dataNascimento;
	    }

	    public void setDataNascimento(String dataNascimento) {
	        this.dataNascimento = dataNascimento;
	    }
	    
	    public String getNumCartao() {
	        return numCartao;
	    }

	   
	    public void setNumCartao(String numCartao) {
	        this.numCartao = numCartao;
	    }

	   
	    public String getDataValCartao() {
	        return DataValCartao;
	    }

	    
	    public void setDataValCartao(String DataValCartao) {
	        this.DataValCartao = DataValCartao;
	    }

	   
	    public String getCVVCartao() {
	        return CVVCartao;
	    }

	    
	    public void setCVVCartao(String CVVCartao) {
	        this.CVVCartao = CVVCartao;
	    }
	
	public String validarDados(){
		StringBuilder sb = new StringBuilder();
		
		if(nome == null || nome.trim().equals("")){
			sb.append("NOME E UM CAMPO OBRIGATORIO!");
		}
		
		if(cpf == null || cpf.trim().equals("")){
			sb.append("CPF E UM CAMPO OBRIGATORIO!");
		}
		if(telefone1 == null || telefone1.trim().equals("")){
			sb.append("E obrigatorio pelo menos um numero de telefone.");
		}
		
		if(dataNascimento == null ){
			sb.append("a data de nascimento é obrigatoria");
		}
	
		String msgEndereco = endereco.validar();
		
		if(msgEndereco != null){
			sb.append(msgEndereco);
		}
		
		
		if(sb.length()>0){
			return sb.toString();
		}else{
			return null;
		}		
		
	}

	
	
}
