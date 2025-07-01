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
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveAbrirTelaLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveAbrirTelaCadastro() throws Exception {
        mockMvc.perform(get("/cadastro"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveCadastrarUsuario() throws Exception {
        mockMvc.perform(post("/salvarUsuario")
                        .param("user", "integUser")
                        .param("email", "integ@email.com")
                        .param("senha", "123456"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void loginInvalidoDeveRedirecionar() throws Exception {
        mockMvc.perform(post("/login")
                        .param("user", "naoexiste")
                        .param("senha", "errada"))
                .andExpect(status().isOk());
    }
}