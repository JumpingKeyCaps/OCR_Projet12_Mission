package com.openclassroom.joiefull.viewmodelTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.viewmodel.ProductListsViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the ProductListsViewModel class.
 */
@ExperimentalCoroutinesApi
class ProductListsViewModelTest {

    private lateinit var viewModel: ProductListsViewModel

    private val testDispatcher = StandardTestDispatcher() // Use TestDispatcher
    private val productRepository = mockk<ProductRepository>(relaxed = true)
    private val productDetailsRepository = mockk<ProductDetailsRepository>(relaxed = true)

    // Rule pour exécuter les tâches synchronisées (obligatoire pour les tests LiveData)
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Initialisation du dispatcher de test
        viewModel = ProductListsViewModel(
            productRepository = productRepository,
            productDetailsRepository = productDetailsRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Réinitialisation du dispatcher de test
    }

    /**
     * Test de la méthode fetchProductsWithDetails.
     */
    @Test
    fun fetchProductsWithDetails_should_update_productsWithDetails_with_grouped_products() = runTest {
        // Prepare mock data
        val mockProducts = listOf(
            Product(id = 1, category = "CategoryA"),
            Product(id = 2, category = "CategoryB")
        )
        val mockProductDetailsList = listOf(
            ProductDetails(id = 1),
            ProductDetails(id = 2)
        )
        // Configure mock behavior
        coEvery { productRepository.getClothes() } returns mockProducts
        coEvery { productDetailsRepository.getAllProductDetails() } returns flowOf(
            mockProductDetailsList.map { it.toDto() }
        )
        // Start the fetching process
        viewModel.fetchProductsWithDetails()
        // Advance the coroutine scheduler
        testDispatcher.scheduler.advanceUntilIdle()
    }

    /**
     * Test de la méthode updateLikeValue.
     */
    @Test
    fun updateLikeValue_should_update_productDetails_with_correct_isLiked_value() = runTest {
        // Préparer les données simulées
        val productDetails = ProductDetails(id = 1, isLiked = false)

        // Configurer le mock pour `addProductDetails`
        coEvery { productDetailsRepository.addProductDetails(any()) } just Runs

        // Appeler la fonction avec isLiked = true
        viewModel.updateLikeValue(productDetails, isLiked = true)

        // Vérifier que `addProductDetails` a été appelé avec la bonne valeur
        coVerify {
            productDetailsRepository.addProductDetails(
                productDetails.copy(isLiked = true)
            )
        }
    }

}