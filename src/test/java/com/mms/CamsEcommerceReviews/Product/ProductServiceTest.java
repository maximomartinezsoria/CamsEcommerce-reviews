package com.mms.CamsEcommerceReviews.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldCallFindAllOnce() {
        productService.findAll();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void shouldCallSaveOnce() {
        Product product = new Product((long) 1, "Product title", null);
        productService.save(product);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).save(productArgumentCaptor.capture());
        assertThat(productArgumentCaptor.getValue()).isEqualTo(product);
    }

    @Test
    void shouldCallFindByIdOnce() {
        Long id = (long) 1;
        Product product = new Product(id, "Product title", null);
        given(productRepository.findById(anyLong())).willReturn(Optional.of(product));
        productService.findById(id);
        ArgumentCaptor<Long> productIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(productRepository, times(1)).findById(productIdArgumentCaptor.capture());
        assertThat(productIdArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void shouldCallFindByIdOnceAndThrowException() {
        Long id = (long) 1;
        assertThatThrownBy(() -> productService.findById(id))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Product with ID=" + id + " doesn't exist");
        ArgumentCaptor<Long> productIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(productRepository, times(1)).findById(productIdArgumentCaptor.capture());
        assertThat(productIdArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void shouldCallDeleteByIdOnce() {
        Long id = (long) 1;
        productService.delete(id);
        ArgumentCaptor<Long> productIdArgumentCapture = ArgumentCaptor.forClass(Long.class);
        verify(productRepository, times(1)).deleteById(productIdArgumentCapture.capture());
        assertThat(productIdArgumentCapture.getValue()).isEqualTo(id);
    }
}