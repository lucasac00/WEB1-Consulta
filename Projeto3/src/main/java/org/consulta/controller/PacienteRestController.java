package org.consulta.controller;

import org.consulta.domain.Paciente;
import org.consulta.domain.Usuario;
import org.consulta.service.spec.IPacienteService;
import org.consulta.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class PacienteRestController {

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IUsuarioService usuarioService;

    // Retorna a lista de todos os pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        return ResponseEntity.ok(pacientes);
    }

    // Cria um novo paciente
    @PostMapping
    public ResponseEntity<?> criarPaciente(@RequestBody Paciente paciente) {
        Paciente jaExiste = pacienteService.buscarPorCpf(paciente.getCpf());
        if (jaExiste != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um paciente com esse CPF já existe.");
        }

        Paciente jaExisteUsername = pacienteService.buscarPorUsername(paciente.getUsername());
        if (jaExisteUsername != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um paciente com esse Username já existe.");
        }

        Paciente jaExisteEmail = pacienteService.buscarPorEmail(paciente.getEmail());
        if (jaExisteEmail != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um paciente com esse Email já existe.");
        }

        // Validações de tamanho dos campos
        if(paciente.getCpf().length() > 64 || paciente.getEmail().length() > 64 ||
                paciente.getNome().length() > 64 || paciente.getSenha().length() > 64 ||
                paciente.getSexo().length() > 64 || paciente.getTelefone().length() > 64 ||
                paciente.getUsername().length() > 64) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Você está com uma entrada longa demais.");
        }

        // Criação de novo Usuario e Paciente
        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(paciente.getUsername());
            usuario.setEmail(paciente.getEmail());
            usuario.setPassword(paciente.getSenha());
            usuario.setCpf(paciente.getCpf());
            usuario.setName(paciente.getNome());
            usuario.setRole("ROLE_PACIENTE");
            usuario.setEnabled(true);
            usuarioService.salvar(usuario);

            pacienteService.salvar(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Informações inválidas.");
        }
    }

    // Retorna um paciente específico por ID, e se ele n for encontrado, retorna a string explicando o erro
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPacientePorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente == null) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND)
            //        .body(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Paciente com o ID " + id + " não encontrado.");
        }
        return ResponseEntity.ok(paciente);
    }

    // Atualiza um paciente existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado) {
        // Verifica se o paciente com o ID fornecido existe
        Paciente pacienteExistente = pacienteService.buscarPorId(id);
        if (pacienteExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente não encontrado.");
        }

        // Verifica se o username já está sendo utilizado por outro paciente
        Paciente pacienteComMesmoUsername = pacienteService.buscarPorUsername(pacienteAtualizado.getUsername());
        if (pacienteComMesmoUsername != null && !pacienteComMesmoUsername.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("O username já está sendo utilizado por outro paciente.");
        }

        // Verifica se o cpf já está sendo utilizado por outro paciente
        Paciente pacienteComMesmoCpf = pacienteService.buscarPorCpf(pacienteAtualizado.getCpf());
        if (pacienteComMesmoCpf != null && !pacienteComMesmoCpf.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("O cpf já está sendo utilizado por outro paciente.");
        }

        // Verifica se o email já está sendo utilizado por outro paciente
        Paciente pacienteComMesmoEmail = pacienteService.buscarPorEmail(pacienteAtualizado.getEmail());
        if (pacienteComMesmoEmail != null && !pacienteComMesmoEmail.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("O email já está sendo utilizado por outro paciente.");
        }

        // Validações de tamanho dos campos
        if(pacienteAtualizado.getCpf().length() > 64 || pacienteAtualizado.getEmail().length() > 64 ||
            pacienteAtualizado.getNome().length() > 64 || pacienteAtualizado.getSenha().length() > 64 ||
            pacienteAtualizado.getSexo().length() > 64 || pacienteAtualizado.getTelefone().length() > 64 ||
            pacienteAtualizado.getUsername().length() > 64) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Você está com uma entrada longa demais.");
        }

        // Atualiza o paciente existente com os novos dados
        pacienteExistente.setEmail(pacienteAtualizado.getEmail());
        pacienteExistente.setSenha(pacienteAtualizado.getSenha());
        pacienteExistente.setCpf(pacienteAtualizado.getCpf());
        pacienteExistente.setNome(pacienteAtualizado.getNome());
        pacienteExistente.setTelefone(pacienteAtualizado.getTelefone());
        pacienteExistente.setSexo(pacienteAtualizado.getSexo());
        pacienteExistente.setDataNascimento(pacienteAtualizado.getDataNascimento());
        pacienteExistente.setUsername(pacienteAtualizado.getUsername());

        // Salva as alterações
        pacienteService.atualizar(pacienteExistente);

        return ResponseEntity.ok().build();
    }


    // Deleta um paciente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente não encontrado.");
        }

        Usuario usuario = usuarioService.buscarPorDocumento(paciente.getCpf());
        pacienteService.excluir(id);
        if (usuario != null) {
            usuarioService.excluir(usuario.getId());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
