package GeneratorObjects

class Order{
    //order to be made
    private var _order_id: Int = 0
    private var _order_date: String = "2000-01-01"//year-month-day
    private var _order_customer_id: Int = 0
    private var _order_product_id: Int = 0
    private var _qty: Int = 0
    private var _ecommerce_website_name: String = ""
    private var _payment_txn_id: String = ""

    private var _payment_txn_success: Boolean = false
    private var _payment_failure_reason: String = ""

    //getters
    def order_id: Int = _order_id
    def order_date: String = _order_date
    def order_customer_id: Int = _order_customer_id
    def order_product_id: Int = _order_product_id
    def qty: Int = _qty
    def ecommerce_website_name: String = _ecommerce_website_name
    def payment_txn_id: String = _payment_txn_id
    def payment_txn_success: Boolean = _payment_txn_success
    def payment_failure_reason: String = _payment_failure_reason

    //setters
    def order_id_=(order_id: Int): Unit = _order_id = order_id
    def order_date_=(order_date: String): Unit = _order_date = order_date
    def order_customer_id_=(order_customer_id: Int): Unit = _order_customer_id = order_customer_id
    def order_product_id_=(order_product_id: Int): Unit = _order_product_id = order_product_id
    def qty_=(qty: Int): Unit = _qty = qty
    def ecommerce_website_name_=(ecommerce_website_name: String): Unit = _ecommerce_website_name = ecommerce_website_name
    def payment_txn_id_=(payment_txn_id: String): Unit = _payment_txn_id = payment_txn_id
    def payment_txn_success_=(payment_txn_success: Boolean): Unit = _payment_txn_success = payment_txn_success
    def payment_failure_reason_=(reason: String): Unit = {
        if(!_payment_txn_success){
            //TODO change payment_failure_reason to Int error code instead of String
            _payment_failure_reason = reason
        } else {
            //payment successful
            _payment_failure_reason = ""
        }
    }

}
