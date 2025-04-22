package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.CategoryDTO;
import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Controller/Resource for Products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = "application/json")
    @Operation(
            description = "Find all product",
            summary = "Find all product",
            responses = {
                    @ApiResponse(description = "ok",responseCode = "200")
            }
    )
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> products = productService.findAll(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(
            description = "Update a product",
            summary = "Update a product",
            responses = {
                    @ApiResponse(description = "ok",responseCode = "200"),
                    @ApiResponse(description = "Not found",responseCode = "404")

            }
    )
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping(produces = "application/json")
    @Operation(
            description = "Create a new product",
            summary = "Create a new product",
            responses = {
                    @ApiResponse(description = "created",responseCode = "201"),
                    @ApiResponse(description = "Bad request",responseCode = "400"),
                    @ApiResponse(description = "Unauthorized",responseCode = "401"),
                    @ApiResponse(description = "Forbbiden",responseCode = "403")
            }
    )
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        dto = productService.insert(dto);
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @Operation(
            description = "Update a product",
            summary = "Update a product",
            responses = {
                    @ApiResponse(description = "ok",responseCode = "200"),
                    @ApiResponse(description = "Bad request",responseCode = "400"),
                    @ApiResponse(description = "Unauthorized",responseCode = "401"),
                    @ApiResponse(description = "Forbbiden",responseCode = "403"),
                    @ApiResponse(description = "Not found",responseCode = "404"),

            }
    )
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto){
        dto =  productService.update(id,dto);
        return  ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            description = "Update a product",
            summary = "Update a product",
            responses = {
                    @ApiResponse(description = "ok",responseCode = "200"),
                    @ApiResponse(description = "Bad request",responseCode = "400"),
                    @ApiResponse(description = "Unauthorized",responseCode = "401"),
                    @ApiResponse(description = "Forbbiden",responseCode = "403"),
                    @ApiResponse(description = "Not found",responseCode = "404"),

            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return  ResponseEntity.noContent().build();
    }




}