import GeneratorObjects._

<<<<<<< HEAD
//object SparkConsumer{
//    val spark = SparkSession.builder().appName("Generator").master("local[*]").getOrCreate()
//    val sc = spark.sparkContext
//    val sqlContext = spark.sqlContext
//    val df = sqlContext.read.json("/path/to/people.json")
//    df.show()
//    df.write.mode("overwrite").json("file:///D:/data/people.json")
//    sc.stop()
//
//}

object Generator extends App{
  //

  println("hello world")

}
=======
// class Customer{
//     val customer_name: String
//     val possible_product_ids: List[Int] //list of orders this compnay can make
//     val city: String
//     val country: String

// }

// class Product{
//     //available product
//     val product_name: String
//     val product_id: Int
//     val product_category: String
//     val product_price: Double
// }

// class Order{
//     //order to be made
//     val order_id: Int
//     val order_date: String //year-month-day
//     val order_customer_id: Int
//     val order_product_id: Int
//     val qty: Int
//     val ecommerce_website_name: String
//     val payment_txn_id: String
//     private val payment_txn_success: Boolean
//     private val payment_failure_reason: String = ""

//     def setPaymentSuccess(success: Boolean): Unit = {
//         payment_txn_success = success
//     }

//     def setPaymentFailureReason(reason: String): Unit = {
//         if(!payment_txn_success){
//             //TODO change payment_failure_reason to Int error code instead of String
//             payment_failure_reason = reason
//         } else {
//             //payment successful
//             payment_failure_reason = ""
//         }
//     }

//     def getPaymentSuccess(): Boolean = {
//         payment_txn_success
//     }
// }

// object SparkConsumer{
//     val spark = SparkSession.builder().appName("Generator").master("local[*]").getOrCreate()
//     val sc = spark.sparkContext
//     val sqlContext = spark.sqlContext
//     val df = sqlContext.read.json("file:///D:/data/people.json")
//     df.show()
//     df.write.mode("overwrite").json("file:///D:/data/people.json")
//     sc.stop()

// }

// object Generator{

//     def main(args: Array[String]): Unit = {
//         val company = new Customer()
//         company.customer_name = "company1"
//         company.address = "address1"

//     }
//     def emp_id(): String = {
//         val random = new Random()
//     }
// }
>>>>>>> c52b2bcdd3015128d454b40734a4d5cef0b3e07c
