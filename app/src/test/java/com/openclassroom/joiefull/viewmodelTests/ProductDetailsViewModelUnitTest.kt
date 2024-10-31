package com.openclassroom.joiefull.viewmodelTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openclassroom.joiefull.data.dataBase.entity.ProductDetailsDto
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.viewmodel.ProductDetailsViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the ProductDetailsViewModel class.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsViewModelUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var productDetailsRepository: ProductDetailsRepository
    private lateinit var productRepository: ProductRepository
    private lateinit var viewModel: ProductDetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        productDetailsRepository = mockk()
        productRepository = mockk()

        viewModel = ProductDetailsViewModel(
            productId = 1,
            productDetailsRepository = productDetailsRepository,
            productRepository = productRepository
        )

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Test the initialization of the ViewModel with a valid ID.
     */
    @Test(expected = IllegalArgumentException::class)
    fun initialization_with_invalid_id_should_throw_IllegalArgumentException() {
        viewModel = ProductDetailsViewModel(-1, productDetailsRepository, productRepository)
    }

    /**
     * Test the fetchProductsWithDetailsById method with a valid ID.
     */
    @Test
    fun fetchProductsWithDetailsById_should_update_productWithDetails_if_product_exists_in_db() = runTest {
        val productId = 1
        val product = Product(id = productId)
        val productDetailsDto = ProductDetailsDto(id = productId, isLiked = false)
        val productDetails = ProductDetails.fromDto(productDetailsDto)
        coEvery { productRepository.getClothes() } returns listOf(product)
        coEvery { productDetailsRepository.getProductDetailsById(productId) } returns flowOf(productDetailsDto)
        viewModel = ProductDetailsViewModel(productId, productDetailsRepository, productRepository)
        val job = launch { viewModel.productWithDetails.collectLatest { } }
        advanceUntilIdle()
        val result = viewModel.productWithDetails.value
        assertEquals(productId, result.product.id)
        assertEquals(productDetails, result.details)
        job.cancel()
    }

    /**
     * Test the addProductDetails method.
     */
    @Test
    fun addProductDetails_should_call_repository_with_correct_productDetails() = runTest {
        val productDetails = ProductDetails(id = 1, isLiked = false)
        coEvery { productDetailsRepository.addProductDetails(any()) } just Runs
        viewModel.addProductDetails(productDetails)
        coVerify { productDetailsRepository.addProductDetails(productDetails) }
    }




}