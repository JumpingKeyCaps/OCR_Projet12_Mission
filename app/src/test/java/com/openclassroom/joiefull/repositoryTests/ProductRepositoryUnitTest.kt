package com.openclassroom.joiefull.repositoryTests

import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.data.service.JoiefullApiService
import com.openclassroom.joiefull.model.Product
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Unit test for the ProductRepository class.
 */
@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class ProductRepositoryUnitTest {
    /**
     * Test the getClothes method of the ProductRepository class.
     */
    @Test
    fun getClothes_shouldReturnListOfProducts() = runTest {
        val mockApiService = mockk<JoiefullApiService>()
        val expectedProducts = listOf(
            Product(id = 1, name = "Comfortable pants", originalPrice = 29.99f, likes = 12, category = "", price = 19.99f, rating = 4.0f,picture = Product.Picture("","")),
            Product(id = 2, name = "Summer Dress", originalPrice = 37.0f, likes = 15, category = "", price = 13.49f, rating = 5.0f,picture = Product.Picture("","")),
            Product(id = 3, name = "Casual pants", originalPrice = 27.09f, likes = 17, category = "", price = 9.99f, rating = 2.0f,picture = Product.Picture("","")),
        )
        coEvery { mockApiService.getClothes() } returns expectedProducts
        val actualProducts = mockApiService.getClothes()
        assertEquals(expectedProducts, actualProducts)
    }


    /**
     * Test the getClothes method of the ProductRepository class when the API returns an empty list.
     */
    @Test
    fun getClothes_shouldHandleEmptyList() = runTest {
        val mockApiService = mockk<JoiefullApiService>()
        coEvery { mockApiService.getClothes() } returns emptyList()
        val repository = ProductRepository(mockApiService)
        val actualProducts = repository.getClothes()
        assertTrue(actualProducts.isEmpty())
    }




}