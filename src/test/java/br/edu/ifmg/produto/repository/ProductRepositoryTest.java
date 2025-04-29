package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName(value = "Verificando se o objeto não existe no DB depois de deletado")
    public void deleteShoudDeleteObjectWhenIdExists() {
        productRepository.deleteById(1L);
        Optional<Product> optional = productRepository.findById(1L);

        Assertions.assertFalse(optional.isPresent());
    }
}
