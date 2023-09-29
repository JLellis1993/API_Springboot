package br.julialellis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.julialellis.entity.Pessoa;
import br.julialellis.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> listaPessoas() {
		return pessoaRepository.findAll();

	}

	@PostMapping
	public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		return pessoaRepository.findById(id).map(obj -> {
			obj.setNome(pessoa.getNome());
			obj.setCPF(pessoa.getCPF());
			obj.setDataNascimento(pessoa.getCPF());
			obj.setEndereco(pessoa.getEndereco());
			obj.setTelefone(pessoa.getTelefone());
			Pessoa pessoaAtualizado = pessoaRepository.save(obj);

			return ResponseEntity.ok().body(pessoaAtualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPessoas(@PathVariable Long id) {
		return pessoaRepository.findById(id).map(obj -> {
			pessoaRepository.deleteById(id);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.noContent().build());
	}

}
