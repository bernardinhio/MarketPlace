package bernardo.bernardinhio.timersforaddingremovingitemsfromrecyclerview

import java.util.ArrayList


class ProductDataProvider{
    companion object {
        fun getArrayListProducts() : ArrayList<ProductModel>{
            val arrayListProducts : ArrayList<ProductModel> = ArrayList<ProductModel>()

            arrayListProducts.add(ProductModel(
                    "image1",
                    "Aunt Jamima Syrup",
                    "Deutschland",
                    "5.00"
            ))
            arrayListProducts.add(ProductModel(
                    "image2",
                    "Bounty Paper Towls",
                    "Bremen",
                    "1.07"
            ))
            arrayListProducts.add(ProductModel(
                    "image3",
                    "Campell's Soup",
                    "Bremen",
                    "0.89"
            ))
            arrayListProducts.add(ProductModel(
                    "image4",
                    "Charmin Ultra",
                    "Munich",
                    "4.00"
            ))
            arrayListProducts.add(ProductModel(
                    "image5",
                    "Chips Ahoy!",
                    "Deutschland",
                    "0.89"
            ))
            arrayListProducts.add(ProductModel(
                    "image6",
                    "Chiquita Banana",
                    "Deutschland",
                    "1.99"
            ))
            arrayListProducts.add(ProductModel(
                    "image7",
                    "Classico Pasta Sauce",
                    "Dormund",
                    "2.01"
            ))
            arrayListProducts.add(ProductModel(
                    "image8",
                    "Crest toothpaste",
                    "Deutschland",
                    "1.09"
            ))
            arrayListProducts.add(ProductModel(
                    "image9",
                    "French's Mustard",
                    "Munich",
                    "1.29"
            ))
            arrayListProducts.add(ProductModel(
                    "image10",
                    "General Mills Cereals",
                    "Munich",
                    "4.09"
            ))
            arrayListProducts.add(ProductModel(
                    "image11",
                    "Gorton's Fish Sticks",
                    "Bremen",
                    "6.05"
            ))
            arrayListProducts.add(ProductModel(
                    "image12",
                    "Hamburger Helper",
                    "Deutschland",
                    "3.29"
            ))
            arrayListProducts.add(ProductModel(
                    "image13",
                    "Heinz Ketchup",
                    "Munich",
                    "1.65"
            ))
            arrayListProducts.add(ProductModel(
                    "image14",
                    "Hershey's Chocolate Syrup",
                    "Cologne",
                    "1.20"
            ))
            arrayListProducts.add(ProductModel(
                    "image15",
                    "Hunts Tomato Paste",
                    "Bremen",
                    "1.07"
            ))
            arrayListProducts.add(ProductModel(
                    "image16",
                    "Keebler Fudge Stripe Cookies",
                    "Cologne",
                    "0.99"
            ))
            arrayListProducts.add(ProductModel(
                    "image17",
                    "Kraft Macaroni & Cheese",
                    "Cologne",
                    "2.50"
            ))
            arrayListProducts.add(ProductModel(
                    "image18",
                    "Lawry's Season Salt",
                    "Deutschland",
                    "0.49"
            ))
            arrayListProducts.add(ProductModel(
                    "image19",
                    "Maxwell House Coffee",
                    "Munich",
                    "1.30"
            ))
            arrayListProducts.add(ProductModel(
                    "image20",
                    "McCormic Seasoning",
                    "Munich",
                    "3.09"
            ))
            arrayListProducts.add(ProductModel(
                    "image21",
                    "Newman's Own Popcorn",
                    "Munich",
                    "2.79"
            ))
            arrayListProducts.add(ProductModel(
                    "image22",
                    "Rice-A-Roni",
                    "Munich",
                    "1.89"
            ))
            arrayListProducts.add(ProductModel(
                    "image23",
                    "Shake'n Bake",
                    "Dortmund",
                    "3.09"
            ))
            arrayListProducts.add(ProductModel(
                    "image24",
                    "Stove Top Stuffing",
                    "Dortmund",
                    "4.00"
            ))
            arrayListProducts.add(ProductModel(
                    "image25",
                    "Swanson Frozen Dinner",
                    "Dortmund",
                    "5.27"
            ))
            arrayListProducts.add(ProductModel(
                    "image26",
                    "Top Ramen",
                    "Deutschland",
                    "2.70"
            ))
            arrayListProducts.add(ProductModel(
                    "image27",
                    "Velveeta Shells and Cheese",
                    "Munich",
                    "0.76"
            ))
            arrayListProducts.add(ProductModel(
                    "image28",
                    "Wishbone Salad Dressing",
                    "Deutschland",
                    "3.59"
            ))
            return arrayListProducts
        }

        fun cloneDataProvider() : ArrayList<ProductModel>{
            var clonedArrayList : ArrayList<ProductModel> = ArrayList<ProductModel>()
            for(item in getArrayListProducts()){
                clonedArrayList.add(item)
            }
            return clonedArrayList
        }
    }
}