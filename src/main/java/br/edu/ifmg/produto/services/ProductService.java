    package br.edu.ifmg.produto.services;

    import br.edu.ifmg.produto.dtos.CategoryDTO;
    import br.edu.ifmg.produto.dtos.ProductDTO;
    import br.edu.ifmg.produto.entities.Category;
    import br.edu.ifmg.produto.entities.Product;
    import br.edu.ifmg.produto.repository.ProductRepository;
    import br.edu.ifmg.produto.services.exceptions.ResourceNotFound;
    import jakarta.persistence.EntityNotFoundException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class ProductService {

        @Autowired
        private ProductRepository productRepository;


        @Transactional(readOnly = true)
        public Page<ProductDTO> findAll(Pageable pageable) {
            Page<Product> list = productRepository.findAll(pageable);
            return list.map(p -> new ProductDTO(p));
        }

        @Transactional(readOnly = true)
        public ProductDTO findById(Long id) {
            Optional<Product> obj = productRepository.findById(id);

            Product product = obj.orElseThrow(() -> new ResourceNotFound("Product not found" + id));
            return new ProductDTO(product);
        }

        @Transactional
        public ProductDTO insert(ProductDTO dto) {
            Product entity = new Product();
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }

        @Transactional
        public ProductDTO update(Long id, ProductDTO dto) {
            try {
                Product entity = productRepository.getReferenceById(id);
                copyDtoToEntity(dto, entity);
                productRepository.save(entity);
                return new ProductDTO(entity);
            }
            catch(EntityNotFoundException e) {
                throw new ResourceNotFound("Product not Found " +id);
            }
        }



        private void copyDtoToEntity(ProductDTO dto, Product entity){
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setImageUrl(dto.getImageUrl());
        }


    }
