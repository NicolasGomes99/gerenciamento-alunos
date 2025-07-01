package br.com.gerenciamento.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarFormularioInserirAlunos() throws Exception {
        mockMvc.perform(get("/inserirAlunos"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRedirecionarAposSalvar() throws Exception {
        mockMvc.perform(post("/InsertAlunos")
                        .param("nome", "Teste Aluno")
                        .param("matricula", "MAT123")
                        .param("curso", "ADMINISTRACAO")
                        .param("status", "ATIVO")
                        .param("turno", "NOTURNO"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void deveListarAlunos() throws Exception {
        mockMvc.perform(get("/alunos-adicionados"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveFiltrarPorNome() throws Exception {
        mockMvc.perform(post("/pesquisar-aluno")
                        .param("nome", "Teste"))
                .andExpect(status().isOk());
    }
}