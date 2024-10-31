package com.openclassroom.joiefull.repositoryTests
import com.openclassroom.joiefull.data.dataBase.dao.ProductDetailsDtoDao
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.model.ProductDetails
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Unit test for the ProductDetailsRepository class.
 */
@RunWith(JUnit4::class)
class ProductDetailsRepositoryUnitTest {

    /**
     * Test the getProductDetailsById method of the ProductDetailsRepository class.
     */
    @Test
    fun getProductDetailsById_shouldReturnCorrectProductDetails() = runTest {
        val expectedProductDetails = ProductDetails(id = 1, isLiked =  false, rating = 4f, commentary = "commentary 1")
        val mockDao = mockk<ProductDetailsDtoDao>()
        coEvery { mockDao.getProductDetailsById(any()) } returns flowOf(expectedProductDetails.toDto())
        val repository = ProductDetailsRepository(mockDao)
        val actualProductDetails = repository.getProductDetailsById(1).first()
        assertEquals(expectedProductDetails.toDto(), actualProductDetails)
    }

    /**
     * Test the getAllProductDetails method of the ProductDetailsRepository class.
     */
    @Test
    fun getAllProductDetails_shouldReturnAllProductDetails() = runTest {
        val expectedProductDetailsList = listOf(
            ProductDetails(id = 1, isLiked =  false, rating = 4f, commentary = "commentary 1"),
            ProductDetails(id = 2, isLiked =  true, rating = 3f, commentary = "commentary 2"),
            ProductDetails(id = 3, isLiked =  false, rating = 2f, commentary = "commentary 3")
        )
        val mockDao = mockk<ProductDetailsDtoDao>()
        coEvery { mockDao.getAllProductDetails() } returns flowOf(expectedProductDetailsList.map { it.toDto() })
        val repository = ProductDetailsRepository(mockDao)
        val actualProductDetailsList = repository.getAllProductDetails().first()
        assertEquals(expectedProductDetailsList.map { it.toDto() }, actualProductDetailsList)
    }


}