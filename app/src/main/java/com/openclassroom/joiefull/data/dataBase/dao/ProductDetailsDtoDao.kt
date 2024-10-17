package com.openclassroom.joiefull.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openclassroom.joiefull.data.dataBase.entity.ProductDetailsDto
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object (DAO) interface of the ProductDetails dto.
 *  regroup all CRUD actions for the product_details table.
 */
@Dao
interface ProductDetailsDtoDao {

    /**
     * Method to INSERT a new product details.
     * @param productDetails the product details dto to insert.
     * @return represents the product ID used like a primary key
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductDetails(productDetails: ProductDetailsDto)



    /**
     * Method to GET details of a product by his ID.
     * @param id the product details dto id to get.
     * @return a Flow of the product details object.
     */
    @Query("SELECT * FROM product_details WHERE id = :id")
    fun getProductDetailsById(id:Int): Flow<ProductDetailsDto>


    /**
     * Method to DELETE a ProductDetails entry.
     * @param productDetails the product details dto to delete.
     */
    @Delete
    suspend fun deleteProductDetails(productDetails: ProductDetailsDto)

}