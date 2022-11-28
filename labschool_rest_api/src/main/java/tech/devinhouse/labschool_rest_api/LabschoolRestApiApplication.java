package tech.devinhouse.labschool_rest_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.Pedagogo;
import tech.devinhouse.labschool_rest_api.model.Professor;
import tech.devinhouse.labschool_rest_api.model.enums.Estado;
import tech.devinhouse.labschool_rest_api.model.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.labschool_rest_api.model.enums.FormacaoAcademica;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;
import tech.devinhouse.labschool_rest_api.service.AlunoService;
import tech.devinhouse.labschool_rest_api.service.PedagogoService;
import tech.devinhouse.labschool_rest_api.service.ProfessorService;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class LabschoolRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabschoolRestApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AlunoService alunoService, ProfessorService professorService, PedagogoService pedagogoService) {
		return args -> {
			List<Aluno> alunos = alunoService.consultar();
			if (alunos.isEmpty()) {
				alunoService.criar(new Aluno("Bart Simpson", "11-11111-1212", LocalDate.of(2014, 10, 29), 11839750073L, SituacaoMatricula.IRREGULAR, 3.5F));
				alunoService.criar(new Aluno("Lisa Simpson", "11-22222-2222", LocalDate.of(2012, 10, 29), 17158947076L, SituacaoMatricula.ATIVO, 10.0F));
				alunoService.criar(new Aluno("Meggie Simpson", "12-20002-2200", LocalDate.of(2019, 10, 29), 63701210020L, SituacaoMatricula.ATIVO, 9.0F));
				alunoService.criar(new Aluno("Milhouse Van Houten", "11-33333-2222", LocalDate.of(2014, 10, 29), 30119137062L, SituacaoMatricula.ATIVO, 8.0F));
				alunoService.criar(new Aluno("Nelson Muntz", "11-44333-4444", LocalDate.of(2007, 10, 29), 95704094015L, SituacaoMatricula.INATIVO, 2.0F));
			}

			List<Professor> professors = professorService.consultar();
			if (professors.isEmpty()) {
				professorService.criar(new Professor("Walter White", "14-22998-1882", LocalDate.of(1982, 10, 30), 40539019011L, FormacaoAcademica.MESTRADO, Estado.ATIVO, ExperienciaDesenvolvimento.FULL_STACK));
				professorService.criar(new Professor("Jesse Pinkman", "44-11111-1992", LocalDate.of(1997, 10, 30), 96107295097L, FormacaoAcademica.GRADUACAO_INCOMPLETA, Estado.ATIVO, ExperienciaDesenvolvimento.BACK_END));
				professorService.criar(new Professor("Hank Schrader", "44-11111-1002", LocalDate.of(1984, 10, 30), 70685977005L, FormacaoAcademica.MESTRADO, Estado.ATIVO, ExperienciaDesenvolvimento.FULL_STACK));
				professorService.criar(new Professor("Gustavo Fring", "44-11001-1002", LocalDate.of(1977, 10, 30), 57408927085L, FormacaoAcademica.GRADUCAO_COMPLETA, Estado.INATIVO, ExperienciaDesenvolvimento.FRONT_END));
				professorService.criar(new Professor("Saul Goodman", "44-11998-1882", LocalDate.of(1980, 10, 30), 86940162062L, FormacaoAcademica.MESTRADO, Estado.ATIVO, ExperienciaDesenvolvimento.FULL_STACK));
			}

			List<Pedagogo> pedagogos = pedagogoService.consultar();
			if (pedagogos.isEmpty()) {
				pedagogoService.criar(new Pedagogo("John Snow", "11-67333-4454", LocalDate.of(2000, 10, 30), 62316840086L));
				pedagogoService.criar(new Pedagogo("Sansa Stark", "22-22333-4454", LocalDate.of(2004, 10, 30), 49850253053L));
				pedagogoService.criar(new Pedagogo("Tyrion Lannister", "33-77333-4454", LocalDate.of(1990, 10, 30), 39125106015L));
				pedagogoService.criar(new Pedagogo("Sandor Clegane", "11-33333-2222", LocalDate.of(1995, 10, 30), 89089606009L));
			}
		};
	}

}
