package org.consulta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// Falta importar os outros daos e domains
import org.consulta.dao.IMedicoDAO;
import org.consulta.domain.Medico;
import org.consulta.dao.IUsuarioDAO;
import org.consulta.domain.Usuario;

@SpringBootApplication
public class ConsultaMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultaMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, /*IConsultaDAO consultaDAO,*/ IMedicoDAO medicoDAO/*, IPacienteDAO pacienteDAO*/) {
        return (args) -> {

            Usuario u1 = new Usuario();
            u1.setUsername("admin");
            u1.setPassword(encoder.encode("admin"));
            u1.setCpf("012.345.678-90");
            u1.setName("Administrador");
            u1.setRole("ROLE_ADMIN");
            u1.setEnabled(true);
            usuarioDAO.save(u1);

            Medico m1 = new Medico();
            m1.setEmail("medicoJoao@email.com");
            m1.setSenha("senhadojoao");
            m1.setCrm("SP-36730");
            m1.setNome("Dr. João");
            m1.setEspecialidade("Cardiologia");
            medicoDAO.save(m1);

            Usuario u2 = new Usuario();
            u2.setUsername("beltrano");
            u2.setPassword(encoder.encode("123"));
            u2.setCpf("985.849.614-10");
            u2.setName("Beltrano Andrade");
            u2.setRole("ROLE_USER");
            u2.setEnabled(true);
            usuarioDAO.save(u2);

            Usuario u3 = new Usuario();
            u3.setUsername("fulano");
            u3.setPassword(encoder.encode("123"));
            u3.setCpf("367.318.380-04");
            u3.setName("Fulano Silva");
            u3.setRole("ROLE_USER");
            u3.setEnabled(true);
            usuarioDAO.save(u3);

        };
    }
}
