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
        if(medico.getEmail().length() > 60 || medico.getUsername().length() > 60 ||
            medico.getCrm().length() > 60 || medico.getPassword().length() > 60 ||
            medico.getName().length() > 60 || medico.getEspecialidade().length() > 60) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Você está com uma entrada longa demais.");
        }
        try {
            medicoService.salvar(medico);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Informações inválidas.");
        }
    }

    // Retorna um médico específico por ID, e se ele n for encontrado, retorna a string explicando o erro
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMedicoPorId(@PathVariable long id) {
        Medico medico = medicoService.buscarPorId(id);
        if (medico == null) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Médico com o ID " + id + " não encontrado.");
        }
        return ResponseEntity.ok(medico);
    }

    // Atualiza um médico existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editarMedico(@PathVariable long id, @RequestBody Medico medicoAtualizado) {
        Medico medicoExistente = medicoService.buscarPorId(id);
        if (medicoExistente == null) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Médico com o ID " + id + " não encontrado para ser editado.");
        }
    
        // Verificações para evitar duplicidade de CRM, Username e Email
        Medico medicoComMesmoCrm = medicoService.buscarPorCrm(medicoAtualizado.getCrm());
        if (medicoComMesmoCrm != null && medicoComMesmoCrm.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um médico com esse CRM já existe.");
        }
    
        Medico medicoComMesmoUsername = medicoService.buscarPorUsername(medicoAtualizado.getUsername());
        if (medicoComMesmoUsername != null && medicoComMesmoUsername.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um médico com esse Username já existe.");
        }
    
        Medico medicoComMesmoEmail = medicoService.buscarPorEmail(medicoAtualizado.getEmail());
        if (medicoComMesmoEmail != null && medicoComMesmoEmail.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Um médico com esse Email já existe.");
        }

        if(medicoAtualizado.getEmail().length() > 60 || medicoAtualizado.getUsername().length() > 60 ||
            medicoAtualizado.getCrm().length() > 60 || medicoAtualizado.getPassword().length() > 60 ||
            medicoAtualizado.getName().length() > 60 || medicoAtualizado.getEspecialidade().length() > 60) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Você está com uma entrada longa demais.");
        }
    
        // Atualizando os campos do médico existente com os dados do médico atualizado
        medicoExistente.setName(medicoAtualizado.getName());
        // medicoExistente.setPassword(medicoAtualizado.getPassword());
        medicoExistente.setCrm(medicoAtualizado.getCrm());
        medicoExistente.setEspecialidade(medicoAtualizado.getEspecialidade());
        medicoExistente.setEmail(medicoAtualizado.getEmail());
        medicoExistente.setUsername(medicoAtualizado.getUsername());

        medicoService.salvar(medicoExistente);
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
