package org.consulta.controller;

import org.consulta.domain.Consulta;
import org.consulta.domain.Medico;
import org.consulta.domain.Usuario;
import org.consulta.service.spec.IConsultaService;
import org.consulta.service.spec.IMedicoService;
import org.consulta.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class MedicoRestController {

    @Autowired
    private IMedicoService medicoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IConsultaService consultaService;

    // Retorna a lista de todos os médicos
    @GetMapping
    public ResponseEntity<List<Medico>> listagemMedicos() {
        List<Medico> listaMedicos = medicoService.buscarTodos();
        return ResponseEntity.ok(listaMedicos);
    }

    // Retorna a lista de todas as especialidades
    @GetMapping("/especialidades")
    public ResponseEntity<List<String>> listagemEspecialidades() {
        List<String> listaEspecialidades = medicoService.getEspecialidades();
        return ResponseEntity.ok(listaEspecialidades);
    }

    //TODO: Adicionar mensagem como a de CRM para username e email igual
    // Feito ^^ :D
    // Cria um novo médico
    @PostMapping
    public ResponseEntity<?> criarMedico(@RequestBody Medico medico) {
        if (medicoService.buscarPorCrm(medico.getCrm()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um médico com esse CRM já existe.");
        }
        if (medicoService.buscarPorUsername(medico.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um médico com esse Username já existe.");
        }
        if (medicoService.buscarPorEmail(medico.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um médico com esse Email já existe.");
        }
        try {
            medicoService.salvar(medico);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Informações inválidas.");
        }
    }

    // Retorna um médico específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable long id) {
        Medico medico = medicoService.buscarPorId(id);
        if (medico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medico);
    }

    //TODO: Adicionar mensagens para CRM Username e Email já existente
    // Atualiza um médico existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editarMedico(@PathVariable long id, @RequestBody Medico medicoAtualizado) {
        Medico medico = medicoService.buscarPorId(id);
        if (medico == null) {
            return ResponseEntity.notFound().build();
        }

        medicoService.salvar(medicoAtualizado);
        return ResponseEntity.ok().build();
    }

    // Deleta um médico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMedico(@PathVariable long id) {
        Medico medico = medicoService.buscarPorId(id);
        if (medico == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Médico não encontrado.");
        }

        medicoService.excluir(medico.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Lista médicos por especialidade
    @GetMapping("/especialidades/{nome}")
    public ResponseEntity<List<Medico>> listarMedicosPorEspecialidade(@PathVariable String nome) {
        List<Medico> listaMedicos = medicoService.buscarPorEspecialidade(nome);
        return ResponseEntity.ok(listaMedicos);
    }

    // Lista consultas de um médico pelo CRM
    @GetMapping("/consultas")
    public ResponseEntity<List<Consulta>> listagemConsultas(@RequestParam("crm") String crm) {
        List<Consulta> listaConsultas = consultaService.buscarPorCrm(crm);
        return ResponseEntity.ok(listaConsultas);
    }
}
