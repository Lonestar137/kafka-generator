package GeneratorObjects
import java.util.Date
import play.api.libs.json._

class Order{
    //order to be made
    private var _order_id: Int = 0
    private var _order_datetime: Date = new Date()//year-month-day
    private var _order_customer_id: Int = 0
    private var _order_product_id: Int = 0
    private var _qty: Int = 0
    private var _ecommerce_website_name: String = ""
    private var _payment_type: String = ""
    private var _payment_txn_id: String = ""

    private var _payment_txn_success: Boolean = false
    private var _payment_failure_reason: String = ""

    //getters
    def order_id: Int = _order_id
    def order_datetime: Date = _order_datetime
    def order_customer_id: Int = _order_customer_id
    def order_product_id: Int = _order_product_id
    def qty: Int = _qty
    def ecommerce_website_name: String = _ecommerce_website_name
    def payment_type: String = _payment_type
    def payment_txn_id: String = _payment_txn_id
    def payment_txn_success: Boolean = _payment_txn_success
    def payment_failure_reason: String = _payment_failure_reason

    //setters
    def order_id_=(order_id: Int): Unit = _order_id = order_id
    def order_datetime_=(order_datetime: Date): Unit = _order_datetime = order_datetime
    def order_customer_id_=(order_customer_id: Int): Unit = _order_customer_id = order_customer_id
    def order_product_id_=(order_product_id: Int): Unit = _order_product_id = order_product_id
    def qty_=(qty: Int): Unit = _qty = qty
    def ecommerce_website_name_=(ecommerce_website_name: String): Unit = _ecommerce_website_name = ecommerce_website_name
    def payment_type_=(payment_type: String): Unit = _payment_type = payment_type
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

    def print() : Unit = {
        println(s"[${this._order_datetime.toString()} ${this._order_id}] (${this._order_customer_id} -> ${this._order_product_id} x ${this._qty}) ${this._ecommerce_website_name} ${this._payment_type} ${this._payment_txn_success} ${this._payment_failure_reason}");
        println();
    }

}
