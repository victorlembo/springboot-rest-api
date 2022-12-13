package br.com.springboot.curso_jdev_treinamento.controllers;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired /* Injecao de dependencia */
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */

	@GetMapping(value = "listatodos")
	@ResponseBody /* Retorna dados para o corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();/* executa consulta no DB */
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/* Retorna a lista em json */
	}

	@PostMapping(value = "salvar")
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /* Recebe os dados par salvar */
		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity(user, HttpStatus.CREATED);

	}

	@DeleteMapping(value = "deletar")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam Long iduser) { /* Recebe o id para deletar */

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("Deletado com sucesso!", HttpStatus.OK);

	}

	@GetMapping(value = "buscaruserid")
	@ResponseBody
	public ResponseEntity<Usuario> buscaruserid(
			@RequestParam(name = "iduser") Long iduser) { /* Recebe o id para deletar */

		Usuario usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}

	@PutMapping(value = "atualizar")
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { /* Recebe os dados par salvar */
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id nao informado!", HttpStatus.OK);

		}

		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}

	@GetMapping(value = "buscarNome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarNome(
			@RequestParam(name = "name") String name) { /* Recebe o id para deletar */

		List<Usuario> usuario = usuarioRepository.buscarNome(name.trim().toUpperCase());

		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

	}

}
