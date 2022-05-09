import GeneratorObjects._

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

//object Generator extends App{
//  //
//
//  val fruit = new Product
//  val hay = new Product
//  hay.name = "Hay"
//  hay.product_price = 5.0
//  hay.product_category = "Resource"
//  hay.product_id = 4
//
//  val resources = List(Product)
//  val retail = List(Product)
//
//
//  products(hay, metals, wood )
//
//
//  val home_depot = new Customer
//  home_depot.name = "Home Depot"
//  home_depot.possible_products = List(4,5)
//
//
//
//  val walmart = new Customer
//  walmart.customer_name = "Walmart"
//  walmart.possible_products = List(1, 2, 3)
//  walmart.city = "San Francisco"
//  wlamart.country = "USA"
//
//
//  println("hello world")
//
//}
