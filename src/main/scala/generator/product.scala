package GeneratorObjects

class Product{
    //available product
    private var _product_name: String = ""
    private var _product_id: Int = 0
    private var _product_category: String = ""
    private var _product_price: Double = 0.0

    //getters
    def product_name: String = _product_name
    def product_id: Int = _product_id
    def product_category: String = _product_category
    def product_price: Double = _product_price

    //setters
    def product_name_= (new_name: String): Unit = _product_name = new_name
    def product_id_= (new_id: Int): Unit = _product_id = new_id
    def product_category_= (new_category: String): Unit = _product_category = new_category
    def product_price_= (new_price: Double): Unit = _product_price = new_price

}
