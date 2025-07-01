package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveSalvarUsuario() {
        Usuario user = novoUsuario("test1@email.com", "user1", "senha");
        usuarioRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void deveBuscarPorEmail() {
        Usuario user = novoUsuario("buscar@email.com", "userBuscar", "senha");
        usuarioRepository.save(user);
        Usuario encontrado = usuarioRepository.findByEmail("buscar@email.com");
        Assert.assertNotNull(encontrado);
    }

    @Test
    public void deveBuscarPorLogin() {
        Usuario user = novoUsuario("login@email.com", "userlogin", "senha123");
        usuarioRepository.save(user);
        Usuario login = usuarioRepository.buscarLogin("userlogin", "senha123");
        Assert.assertNotNull(login);
    }

    @Test
    public void buscarLoginInvalidoRetornaNull() {
        Usuario login = usuarioRepository.buscarLogin("fake", "fake");
        Assert.assertNull(login);
    }

    private Usuario novoUsuario(String email, String user, String senha) {
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setUser(user);
        u.setSenha(senha);
        return u;
    }
}
