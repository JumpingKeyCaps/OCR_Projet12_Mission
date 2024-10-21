package com.openclassroom.joiefull.viewModelUnitTests

import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.viewmodel.ProductListsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductListsViewModelUnitTest {


    @Test
    fun fetchClothes_shouldEmitProductsGroupedByCategoryOnSuccess() = runTest {
        // Given
        val productRepository = mockk<ProductRepository>()
        val expectedProducts = listOf(
            Product(id = 1, name = "Shirt", originalPrice = 29.99f, likes = 12, category = "category1", price = 19.99f, rating = 4.0f,picture = Product.Picture("","")),
            Product(id = 2, name = "Summer Dress", originalPrice = 37.0f, likes = 15, category = "category1", price = 13.49f, rating = 5.0f,picture = Product.Picture("","")),

            )
        coEvery { productRepository.getClothes() } returns expectedProducts

        val viewModel = ProductListsViewModel(productRepository, mockk(relaxed = true))

        // Then
        val actualProducts = viewModel.products.value
        assertEquals(2, actualProducts.size)
        assertEquals("category1", actualProducts.keys.first())
        assertEquals("Shirt", actualProducts["category1"]?.first()?.name)
    }





}