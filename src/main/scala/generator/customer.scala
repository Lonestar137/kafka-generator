package GeneratorObjects

class Customer{
    private var _customer_id: Int = 0
    private var _customer_name: String = ""
    private var _possible_product_ids: List[Int] = List() //list of orders this compnay can make
    private var _city: String = ""
    private var _country: String = ""

    //getters
    def customer_id: Int = _customer_id
    def customer_name: String = _customer_name
    def possible_product_ids: List[Int] = _possible_product_ids
    def city: String = _city
    def country: String = _country

    //setters
    def customer_id_= (new_id: Int): Unit = _customer_id = new_id
    def customer_name_= (new_name: String): Unit = _customer_name = new_name
    def possible_product_ids_= (new_ids: List[Int]): Unit = _possible_product_ids = new_ids
    def city_= (new_city: String): Unit = _city = new_city
    def country_= (new_country: String): Unit = _country = new_country

    def print() : Unit = {
        println(s"[${this._customer_id}] ${this._customer_name}: ${this._city}, ${this._country}");
    }

}
