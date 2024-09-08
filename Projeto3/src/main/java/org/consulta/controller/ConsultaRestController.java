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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaRestController {

    @Autowired
    private IConsultaService consultaService;
    @Autowired
    private IMedicoService medicoService;
    @Autowired
    private IUsuarioService usuarioService;

    // Retorna a lista de todas as consultas
    @GetMapping
    public ResponseEntity<List<Consulta>> listagemConsultas() {
        List<Consulta> listaConsultas = consultaService.buscarTodos();
        return ResponseEntity.ok(listaConsultas);
    }

    // Cria uma nova consulta
    @PostMapping
    public ResponseEntity<?> criarConsulta(@RequestBody Consulta consulta) {
        boolean isValid = consultaService.checkValidity(consulta.getCrm(), consulta.getCpf(), consulta.getDataHora());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Consulta inválida: já existe uma consulta marcada para este horário.");
        }

        consultaService.salvar(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Retorna uma consulta específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaPorId(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarPorId(id);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }

    // Atualiza uma consulta existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editarConsulta(@PathVariable Long id, @RequestBody Consulta consultaAtualizada) {
        boolean isValid = consultaService.checkValidity(consultaAtualizada.getCrm(), consultaAtualizada.getCpf(), consultaAtualizada.getDataHora());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Consulta inválida: já existe uma consulta marcada para este horário.");
        }

        Consulta consulta = consultaService.buscarPorId(id);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }

        consultaService.atualizar(consultaAtualizada);
        return ResponseEntity.ok().build();
    }

    // Deleta uma consulta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarConsulta(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarPorId(id);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }

        consultaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Retorna consultas por CPF
    @GetMapping("/clientes/{cpf}")
    public ResponseEntity<List<Consulta>> listarConsultasPorCpf(@PathVariable String cpf) {
        List<Consulta> listaConsultas = consultaService.buscarPorCpf(cpf);
        return ResponseEntity.ok(listaConsultas);
    }

    // Retorna consultas por CRM
    @GetMapping("/profissionais/{crm}")
    public ResponseEntity<List<Consulta>> listarConsultasPorCrm(@PathVariable String crm) {
        List<Consulta> listaConsultas = consultaService.buscarPorCrm(crm);
        return ResponseEntity.ok(listaConsultas);
    }
}