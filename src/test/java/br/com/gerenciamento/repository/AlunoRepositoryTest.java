package br.com.gerenciamento.repository;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void deveBuscarPorStatusAtivo() {
        Aluno a = novoAluno("Ativo", Status.ATIVO);
        alunoRepository.save(a);
        Assert.assertFalse(alunoRepository.findByStatusAtivo().isEmpty());
    }

    @Test
    public void deveBuscarPorStatusInativo() {
        Aluno a = novoAluno("Inativo", Status.INATIVO);
        alunoRepository.save(a);
        Assert.assertFalse(alunoRepository.findByStatusInativo().isEmpty());
    }

    @Test
    public void deveBuscarPorNome() {
        Aluno a = novoAluno("NomeUnico", Status.ATIVO);
        alunoRepository.save(a);
        Assert.assertFalse(alunoRepository.findByNomeContainingIgnoreCase("nome").isEmpty());
    }

    @Test
    public void deveSalvarERecuperarAluno() {
        Aluno a = novoAluno("TestePersistencia", Status.ATIVO);
        alunoRepository.save(a);
        Assert.assertTrue(alunoRepository.findById(a.getId()).isPresent());
    }

    private Aluno novoAluno(String nome, Status status) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula("MAT-" + System.currentTimeMillis());
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(status);
        aluno.setTurno(Turno.MATUTINO);
        return aluno;
    }
}
