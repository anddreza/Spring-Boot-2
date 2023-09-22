package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {

	@Autowired
	//Toda vez que precisar passar uma mensagem basta chamar o objeto 'mensagem', não precisa instanciar uma objeto da classe Mensagem em nenhum de nossos métodos. 
	public Mensagem mensagem;

	//Validar se o cadastro estiver correto
	@Autowired
	private Repositorio acao;

	//Quando eu cadastrar tenho que ter um objeto do tipo Pessoa
	//Método para cadastrar pessaos
	public ResponseEntity<?> cadastrar(Pessoa obj){

		if(obj.getNome().equals("")){
			mensagem.setMensagem("O nome é inválido");
			// Se houver uma mensagem ele retorna uma mensagem, e ela está acima
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		} else if(obj.getIdade() < 0) {
			//Se for uma idade menor que zero retorna uma mensagem
			mensagem.setMensagem("Informe uma idade válida");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		} else {
			//Se houver um cadastro maior que zero e um nome com todos os caracteres, ele vai cadastrar, vai retornar um codigo gerado pelo banco, o nome e a idade
			return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
		}
	}


	public ResponseEntity<?> selecionar(){
		return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
	}
}
