package br.com.gerenciamento.service;

import br.com.gerenciamento.exception.EmailExistsException;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.util.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Test
    public void deveSalvarUsuarioComSucesso() throws Exception {
        Usuario user = new Usuario();
        user.setUser("teste123");
        user.setEmail("teste123@email.com");
        user.setSenha("123456");

        serviceUsuario.salvarUsuario(user);

        Assert.assertNotNull(user.getId());
    }

    @Test
    public void deveFalharSalvarEmailDuplicado() throws Exception {
        Usuario user1 = new Usuario();
        user1.setUser("user1");
        user1.setEmail("duplicado@email.com");
        user1.setSenha("abc123");
        serviceUsuario.salvarUsuario(user1);

        Usuario user2 = new Usuario();
        user2.setUser("user2");
        user2.setEmail("duplicado@email.com");
        user2.setSenha("xyz123");

        Assert.assertThrows(EmailExistsException.class, () -> {
            serviceUsuario.salvarUsuario(user2);
        });
    }

    @Test
    public void deveRetornarUsuarioLoginValido() throws Exception {
        Usuario user = new Usuario();
        user.setUser("loginteste");
        user.setEmail("loginteste@email.com");
        user.setSenha("senha123");

        serviceUsuario.salvarUsuario(user);

        Usuario login = serviceUsuario.loginUser("loginteste", Util.md5("senha123"));
        Assert.assertNotNull(login);
    }

    @Test
    public void loginInvalidoDeveRetornarNull() throws Exception {
        Usuario login = serviceUsuario.loginUser("inexistente", Util.md5("naosenha"));
        Assert.assertNull(login);
    }
}
