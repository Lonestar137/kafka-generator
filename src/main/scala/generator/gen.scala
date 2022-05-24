package org.goldteam.generator;
import GeneratorObjects._
import scala.collection.mutable.MutableList;
import scala.util.Random
import java.util.Date
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;


object Generator extends App {
  
  var generatorRunCount : Int = 30000;
  
  val dateFormat : SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  def randomString(list: scala.collection.mutable.MutableList[String]) : String = {
    return list(Random.nextInt(list.size));
  }

  def getProductId(newProduct : Product) : Unit = {
    if (product_ids.contains(newProduct.product_name)) {
      newProduct.product_id_=(product_ids(newProduct.product_name));
    }
    else {
      newProduct.product_id_=(product_ids.size);
      product_ids += (newProduct.product_name -> newProduct.product_id);
    }
  }

  // order_id = increasing integer value
  var current_order_id : Int = 0;

  // payment_type = pull from {card, Internet Banking, UPI, Wallet}
  val order_payment_types : MutableList[String] = MutableList("Credit","Debit","Internet Banking","UPI","Cash");
  // qty (Quantity ordered) = randomInRange
  // price (Price of the product) = dictionary<product_name, float>
  // datetime (Date and time when order was placed)
  // Start Date - Unix Timestamp
  // 2020/01/01 00:00:00 -> 1577854800
  // End Date - Unix Timestamp
  // 2021/06/01 00:00:00-> 1622520000
  // Difference between start and end date
  // 1622520000 - 1577854800 = 44665200
  // Difference divided into 25000 and 50000 segments
  // 44665200 / 25000 = ~1787
  // 44665200 / 50000 = ~894 
  // so if you increment your date by randomIntBetween(894,1787)
  // you will have somewhere between 25000 and 50000 orders
  // by the time your date reaches 2021/06/01
  var generatorDate : Date = new Date(1577854800l * 1000l);
  //generatorDate.setTime(System.currentTimeMillis()/1000);
  
  // ecommerce_website_name (Site from where order was placed) = pull from weighted listOfWebsites
  val order_ecommerce_website_names : MutableList[String] = MutableList("amazon.com","walmart.com","ebay.com");
  // payment_txn_id (Payment Transaction Confirmation Id) = use order_id
  // payment_txn_success (Payment Success or Failure (Y=Success. N=Failed)) = boolean based on failure probability
  // failure_reason (Reason for payment failulure) = pull from weighted listOfFailureReasons
  val order_failure_reasons : MutableList[String] = MutableList("Insufficient Funds","Card Expired","Account Suspended","Invalid Billing Address","Invalid Payment Gateway");

  // customer_id = dictionary<customer_name, int>
  var customer_ids = collection.mutable.Map[String, Int]();
  // customer_name
  val first_names : MutableList[String] = MutableList("Larisa","Tabbatha","Jehu","Arluene","Brina","Clare","Petronella","Tiphani","Vanda","Duffie","Hinda","Augustina","Benny","Gene","Thaine","Ailene","Kat","Barthel","Oralla","Patrizia","Maxim","Aland","Stepha","Sandor","Thaddus","Kyle","Celestyn","Dory","Ophelia","Agathe","Daisey","Morna","Juliet","Smitty","Had","Barny","Wittie","Saidee","Merrie","Linell","Rorke","Trenna","Les","Englebert","Ronica","Adeline","Carlye","Federica","Lil","James","Joaquin","Engelbert","Alyce","Anna-maria","Britteny","Annice","Lela","Lorraine","Agneta","Noble","Galvin","Stanleigh","Darb","Pren","Carey","Ced","Nan","Sephira","Petronia","Susi","Cecile","Mary","Cecil","Melicent","Care","Editha","William","Krishnah","Norbie","Lynnet","Basil","Cris","Dollie","Myrwyn","Micheal","Morie","Gerladina","Crichton","Leontine","Vale","Dari","Bogey","Lyon","Konstance","Faina","Pepita","Muffin","Rosalinda","Myrwyn","Ari","Phylys","Bertie","Vonny","Marve","Cassie","Calida","Kellia","Kyle","Stavros","Carma","Emory","Silas","Waylon","Chloette","Elva","Leola","Nerty","Donelle","Gabey","Ody","Kristine","Brucie","Harbert","Jonell","Helsa","Marion","Thomasine","Darci","Bernarr","Gordan","Alvin","Colly","Nadine","Dulsea","Zitella","Adlai","Cart","Harald","Ailsun","Link","Feliza","Ronnie","Padget","Josie","Kandy","Pet","Tabbitha","Rheba","Alexander","Sabrina","Lorianna","Titus","Rosalinde","Germaine","Octavia","Faulkner","Hortensia","Elwira","Jervis","Meggy","Connie","Terrye","Brenda","Jillayne","Gardner","Petunia","Gannie","Berkley","Loydie","Maryanne","Cathrin","Salvidor","Marlin","Roma","Salli","Harriett","Cymbre","Anthea","Elissa","Martin","Sherlock","Ferrell","Bone","Johny","Derwin","Delilah","Mead","Lacee","Maxwell","Rosabella","Coleman","Fredric","Charil","Noah","Madelene","Enos","Cordelia","Adelheid","Robers","Melli","Izak","Trueman","Marianna","Dory","Teresina","Allis","Averil","Britt","Vance","Pen","Rowena","Constantine","Krissie","Cobbie","Sandy","Torrey","Roberto","Natassia","Shaylynn","Josh","Nelly","Jody","Gan","Laurena","Erhart","Danny","Etienne","Lucio","Myra","Francklyn","Immanuel","Jordon","Iosep","Terrijo","Averyl","Ronnie","Jonathan","Nessy","Vasily","Gunar","Leroy","Allyson","Lorne","Ollie","Preston","Ulrich","Claudian","Robinetta","Deny","Eachelle","Gard","Merrielle","Corbett","Pennie","Timmie","Erroll","Teddy","Nickola","Timothy","Hugo","Hastings","Lionello","Tana","Yehudit","Milicent","Morgen","Colby","Immanuel","Culver","Selestina","Dolph","Becki","Donielle","Vonny","Mitchael","Shauna","Stan","Bat","Fowler","Yardley","Avril","John","Alexina","Danella","Hilliard","Charlton","Philly","Jane","Micky","Byrle","Kris","Jamill","Tammara","Enrichetta","Colly","Corinna","Delphine","Cherilynn","Manuel","Stan");
  val last_names : MutableList[String] = MutableList("Whale","Gurwood","Ludman","Ollivier","Shelbourne","Gammie","Powlett","Woollin","Bentz","Palatini","Aneley","Cogswell","Ullrich","Castanares","Anglim","Alessandrelli","Pechacek","O'Dooghaine","Paxforde","Sandeland","Tovey","Kubec","Finkle","Hamper","Meenan","Yarranton","Huband","Perkin","Duchenne","Rampling","Chyuerton","Olivia","Brigdale","Navarijo","Penwright","Highwood","Titcom","Toffoletto","Riche","Alan","Ramlot","Hildrew","Jesse","Lusgdin","Pothergill","Genn","Terbrugge","Goodhand","Norrie","Meneyer","Floweth","Rozenbaum","Crayden","Hawyes","Grinham","Dufore","McAlindon","Tatham","Demaid","Bartoshevich","Jerisch","Ruvel","Larderot","Stanbra","Spellicy","Jullian","Willwood","McGrouther","Stoggles","Wakely","Baughn","Darrel","Credland","Gretton","Dielhenn","Ten Broek","Gustus","Muckleston","Imlin","Doxsey","Sporle","Bridal","Isaaksohn","Breagan","Martinello","Thrustle","Ewestace","Gollin","Lewerenz","Pethick","Jays","Gadney","Simmens","Neath","Bunclark","Stonebanks","Behnke","Vasechkin","Duthy","Crowe","Aveyard","Flacknell","Gyurko","Vell","Heale","Ablewhite","Messitt","Penniall","Timbrell","Hewell","Hanratty","Gothup","Schwand","Bartunek","Humbey","Howood","Alltimes","Hutson","Mathew","Klasen","Mitrovic","Trowbridge","Wythill","Guyon","Brigman","Martignoni","McGill","Necolds","Morewood","Cossentine","Hollow","Morrel","Stephens","Oswald","de Aguirre","Lerhinan","Burniston","Beedon","Mallan","Rogans","Bond","Kless","Rowell","Bramich","Barkaway","Troutbeck","Winchcombe","Brombell","Reder","Keenlayside","Cohen","D'Abbot-Doyle","Finnigan","Howcroft","Mauchline","Maddams","Werrilow","Sarfatti","Strathman","Dufer","Severs","Leversuch","Izhakov","Fabb","Kegan","Darnell","Vinick","Kemshell","Ribou","Pape","Burgett","Connor","Shapcott","Roz","Thome","Meneyer","Germon","Casbon","Jon","Coning","Kubera","Dodgshon","Jansens","Bomb","Bartot","Terry","Iacobetto","Mattussevich","Grolmann","Freer","Troth","Kingh","Colgan","Fairlie","Bignold","Gilberthorpe","Rosindill","Karolowski","Dovington","Aitken","Witherbed","Leipold","Vescovini","Beaney","Duggon","Frankis","Darwin","Ventris","Boarer","Estick","Amps","Balling","Sperrett","Foard","Knowlys","Boughen","Cornbill","Mossdale","Supple","Bernuzzi","Haggerty","Keal","Bohje","Gatward","Loding","Maggiore","Ashment","Soldi","Dymick","Stolze","Forton","England","Tuite","Manoelli","Gilhespy","Dallas","Sealeaf","Petti","Totterdill","Hallihan","Dudman","Harcase","McCorkell","Drewet","Dongate","Kollatsch","Flemmich","Caldero","Ribeiro","Pougher","Waterhous","Challicombe","Gorgler","Cisco","Mallender","Segrave","Samsin","Beckerleg","De Morena","Merioth","Hayter","Pancast","Bear","Jackes","Micka","Wooton","Chatainier","Ellerey","Krzyzaniak","Witcherley","Hayer","Rizzello","Hallock","Carvilla","Baynes","Giffkins","Jesteco","Packington","Stive","Dinsmore","Berrey","Beyn","McClenan","Mountney","Pietroni","Dowsey","Yackiminie","Phair","Bazek","McCrostie","Casina","Lehon","Cometti","Jermin","Clapperton","Solman","Kinson","Jewell","Philipard","Yarrall");  
  val pregenerated_names : MutableList[String] = MutableList("Walmart", "Home Depot");

  // country (Customer Country) = pull from weighted listOfCountries
  val modset_countries : ModSet = new ModSet("Countries",
    Mod("United States"),
    Mod("China"),
    Mod("United Kingdom"),
    Mod("Japan"),
    Mod("Germany"),
    Mod("France"),
    Mod("Canada"),
    Mod("Italy"),
    Mod("Spain"),
    Mod("South Korea")
  );
  // city (Customer City) = pull from dictionary<country, city_list>
  
  var product_ids : collection.mutable.Map[String,Int] = collection.mutable.Map[String, Int]();
  
  // Tools
  val product_tool_category : String = "Tools";
  val modset_tool_materials : ModSet = new ModSet("Tool Materials",
    Mod("Iron", 1f), 
    Mod("Steel", 2f), 
    Mod("Titanium", 3f)
  );
  val modset_tool_types : ModSet = new ModSet("Tool Types",
    Mod("Axe", 1.4f),
    Mod("Pick", 1.6f),
    Mod("Shovel", 1.2f),
    Mod("Hammer", 0.8f),
    Mod("Drill", 3f),
    Mod("Saw", 2f),
    Mod("Pliers", 1f),
    Mod("Screwdriver", 0.9f),
    Mod("Wrench", 1.1f)
  );
  
  // Clothing
  val product_clothing_category : String = "Clothing";
  val modset_clothing_colors : ModSet = new ModSet("Clothing Colors",
    Mod("Red"),
    Mod("Orange"),
    Mod("Yellow"),
    Mod("Green"),
    Mod("Blue"),
    Mod("Indigo"),
    Mod("Violet"),
    Mod("Black"),
    Mod("White")
  );
  val modset_clothing_sizes : ModSet = new ModSet("Clothing Sizes",
    Mod("XL", 1.1f),
    Mod("L", 1.05f),
    Mod("M", 1f),
    Mod("S", 0.95f),
    Mod("XS", 0.9f)
  );
  val modset_clothing_materials : ModSet = new ModSet("Clothing Materials",
    Mod("Cotton", 1f),
    Mod("Denim", 1.8f),
    Mod("Silk", 3f)
  );
  val modset_clothing_types : ModSet = new ModSet("Clothing Types",
    Mod("Hat", 0.8f),
    Mod("Jacket", 2f),
    Mod("Pants", 1.4f),
    Mod("Shorts", 1.1f),
    Mod("Gloves", 0.5f),
    Mod("Shirt", 1f)
  );
  
  // Electronics
  val product_electronic_category : String = "Electronics";  
  val modset_electronic_sizes : ModSet = new ModSet("Electronics Sizes",
    Mod("Huge"),
    Mod("Large"),
    Mod("Medium"),
    Mod("Small"),
    Mod("Micro")
  );  
  val modset_electronic_brands : ModSet = new ModSet("Electronics Brands", 
    Mod("LG"), 
    Mod("Dell"), 
    Mod("HP"), 
    Mod("Android"), 
    Mod("Apple"), 
    Mod("Samsung"), 
    Mod("AMD"), 
    Mod("Intel"), 
    Mod("Microsoft"), 
    Mod("Google")
  );  
  val modset_electronic_types : ModSet = new ModSet("Electronics Types", 
    Mod("Laptop", 6f), 
    Mod("Desktop", 7f), 
    Mod("Printer", 2f), 
    Mod("Television", 3.5f), 
    Mod("Smartphone", 4f),
    Mod("Microwave", 1.5f)
  );
  
  var modset_product_categories : ModSet = new ModSet("Product Categories", Mod("Tools"), Mod("Clothing"), Mod("Electronics"));
  var modset_payment_types : ModSet = new ModSet("Payment Types", Mod("Card"), Mod("Internet Banking"), Mod("UPI"), Mod("Wallet"), Mod("Failure"));
  var failablePaymentTypes : MutableList[String] = MutableList("Card", "Internet Banking", "UPI", "Wallet");
  var cardPaymentFailures : MutableList[String] = MutableList("Insufficient Funds", "Card Expired", "Account Suspended", "Invalid Billing Address", "Invalid Payment Gateway");
  var internetBankingPaymentFailures : MutableList[String] = MutableList("Insufficient Funds", "Account Suspended", "Invalid Billing Address", "Invalid Payment Gateway");
  var upiPaymentFailures : MutableList[String] = MutableList("Insufficient Funds", "Account Suspended", "Invalid Billing Address", "Invalid Payment Gateway");
  var walletPaymentFailures : MutableList[String] = MutableList("Insufficient Funds", "Account Suspended", "Invalid Billing Address", "Invalid Payment Gateway");
  var paymentFailureReasonLists = collection.mutable.Map[String, MutableList[String]]("Card" -> cardPaymentFailures, "Internet Banking" -> internetBankingPaymentFailures, "UPI" -> upiPaymentFailures, "Wallet" -> walletPaymentFailures);

  def generateOrder(customer : Customer, product : Product) : Order = {
    var newOrder : Order = new Order();
    newOrder.order_id_=(current_order_id);
    newOrder.payment_txn_id_=(current_order_id.toString());
    current_order_id += 1;
    newOrder.order_datetime_=(new Date(generatorDate.getTime()));
    generatorDate.setTime(generatorDate.getTime + (ThreadLocalRandom.current().nextLong(894l, 1787l) * 1000l))
    newOrder.order_customer_id_=(customer.customer_id);
    newOrder.order_product_id_=(product.product_id);
    newOrder.qty_=(Random.nextInt(10));
    newOrder.ecommerce_website_name_=(randomString(order_ecommerce_website_names));
    newOrder.payment_type_=(randomString(order_payment_types));
    newOrder.payment_txn_success_=(Random.nextInt(100) < 98); // 98% success rate
    if (!newOrder.payment_txn_success) {
      newOrder.payment_failure_reason_=(randomString(order_failure_reasons));
    }
    return newOrder;
  }

  def generateCustomer(country_mod : Mod) : Customer = {
    var newCustomer = new Customer();
    newCustomer.customer_name_=(s"${randomString(first_names)} ${randomString(last_names)}");
    // var customerCountry : Mod = modset_countries.getMod();
    //for (mod <- modset_countries.mods) { print(s"${mod.name}(${f"${mod.trendAlpha}%.2f"})|"); }
    //println();
    newCustomer.country_=(country_mod.name);
    newCustomer.city_=(country_mod.randomSubstring());
    if (customer_ids.contains(newCustomer.customer_name + " " + newCustomer.country)) {
      newCustomer.customer_id_=(customer_ids(newCustomer.customer_name + " " + newCustomer.country));
    }
    else {
      newCustomer.customer_id_=(customer_ids.size);
      customer_ids += ((newCustomer.customer_name + " " + newCustomer.country) -> newCustomer.customer_id);
    }
    return newCustomer;
  }

  def generateTool() : Product = {
    
    // material trend steel demand spike
    if (Random.nextFloat() < 0.008f) { 
      modset_tool_materials("Iron").startTrend(); 
      modset_tool_materials("Steel").startTrend(); 
    }
    
    var mod_material = modset_tool_materials.getMod();
    var mod_type = modset_tool_types.getMod();

    var newProduct = new Product();
    newProduct.product_category_=(s"${mod_material.name} $product_tool_category");
    newProduct.product_name_=(s"${mod_material.useName()} ${mod_type.useName()}");
    getProductId(newProduct);
    newProduct.product_price_=(30f);
    newProduct.product_price_=(newProduct.product_price * mod_material.price() * mod_type.price());
    return newProduct;
  }

  def generateClothing() : Product = {
    
    // material trend cotton shortage / denim demand spike
    if (Random.nextFloat() < 0.008f) { 
      modset_clothing_materials("Cotton").startTrend(); 
      modset_clothing_materials("Denim").startTrend(); 
    }
    
    var mod_color = modset_clothing_colors.getMod();
    var mod_size = modset_clothing_sizes.getMod();
    var mod_material = modset_clothing_materials.getMod();
    var mod_type = modset_clothing_types.getMod();
    
    var newProduct = new Product();
    newProduct.product_category_=(s"${mod_material.name} ${product_clothing_category}");
    newProduct.product_name_=(s"${mod_size.useName()} ${mod_color.useName()} ${mod_material.useName()} ${mod_type.useName()}");
    getProductId(newProduct);
    newProduct.product_price_=(25f);
    newProduct.product_price_=(newProduct.product_price * mod_size.price() * mod_material.price() * mod_type.price());
    return newProduct;
  }

  def generateElectronic() : Product = {
    
    // LG brand popularity increase
    if (Random.nextFloat() < 0.008f) { modset_electronic_brands("LG").startTrend(); }
    // Dell brand popularity increase
    if (Random.nextFloat() < 0.008f) { modset_electronic_brands("Dell").startTrend(); }
    // laptop computer popularity increase
    if (Random.nextFloat() < 0.008f) { modset_electronic_types("Laptop").startTrend(); }
    
    var mod_size : Mod = modset_electronic_sizes.getMod();
    var mod_brand : Mod = modset_electronic_brands.getMod();
    var mod_type : Mod = modset_electronic_types.getMod();
    
    var newProduct = new Product();

    newProduct.product_category_=(product_electronic_category);
    newProduct.product_name_=(s"${mod_size.useName} ${mod_brand.useName} ${mod_type.useName}");
    getProductId(newProduct);
    newProduct.product_price_=(60f);
    newProduct.product_price_=(newProduct.product_price * mod_type.price());
    
    return newProduct;

  }
  
  def generateOrderJSON(paymentType: String, transactionSuccess: Boolean = true, transactionFailureReason: String = "") : String = {

    var order : Order = null;
    var customer : Customer = null;
    var product : Product = null;
    var productType : Int = 0;
    var output : String = "";
    
    var country_mod = modset_countries.getModLinear((current_order_id + 1).toFloat / generatorRunCount.toFloat); country_mod.useName();
    customer = generateCustomer(country_mod);
    
    var mod_category = modset_product_categories.getMod();
    var category_name = mod_category.useName();
    if (category_name == "Tools") { product = generateTool(); }
    else if (category_name == "Clothing") { product = generateClothing(); }
    else { product = generateElectronic(); }
    
    order = generateOrder(customer, product);

    output = "";
    output += "{\n";
    output += "\t\"order_id\":" + order.order_id + ",\n";
    output += "\t\"customer_id\":" + order.order_customer_id + ",\n";
    output += "\t\"customer_name\":\"" + customer.customer_name + "\",\n";
    output += "\t\"product_id\":" + product.product_id + ",\n";
    output += "\t\"product_name\":\"" + product.product_name + "\",\n";
    output += "\t\"product_category\":\"" + product.product_category + "\",\n";
    output += "\t\"payment_type\":\"" + paymentType + "\",\n";
    output += "\t\"qty\":" + order.qty + ",\n";
    output += "\t\"price\":" + f"${product.product_price}%.2f" + ",\n";
    output += "\t\"datetime\":\"" + order.order_datetime.toString() + "\",\n";

    ////TODO -- remove the unix timestamp.  Placed here for testing purposes. conver to format "yyyy-MM-dd HH:mm:ss"
    val fmt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = fmt.format(order.order_datetime)
    ////output += "\t\"time\" : " + order.order_datetime.getTime()/1000 + ",\n";
    output += "\t\"time\":\"" + date + "\",\n";
    //println(date)

    output += "\t\"country\":\"" + customer.country + "\",\n";
    output += "\t\"city\":\"" + customer.city + "\",\n";
    output += "\t\"ecommerce_website_name\":\"" + order.ecommerce_website_name + "\",\n";
    output += "\t\"payment_txn_id\":\"" + order.payment_txn_id + "\",\n";
    output += "\t\"payment_txn_success\":" + transactionSuccess + ",\n";
    output += "\t\"failure_reason\":\"" + transactionFailureReason + "\"\n";
    output += "}";

    return output.replace("\t","").replace("\n","");
    //return s"[${order.order_id} ${dateFormat.format(order.order_datetime)}] ${product.product_name} ($$${f"${product.product_price}%.2f"}) ${customer.country}";
    
  }

  def generateOrderJSON_Card() : String = { return generateOrderJSON("Card"); }
  def generateOrderJSON_InternetBanking() : String = { return generateOrderJSON("Internet Banking"); }
  def generateOrderJSON_UPI() : String = { return generateOrderJSON("UPI"); }
  def generateOrderJSON_Wallet() : String = { return generateOrderJSON("Wallet"); }
  
  def generateOrderJSON_Failed() : String = {
    var nextPaymentFailure : String = randomString(failablePaymentTypes);
    return generateOrderJSON(nextPaymentFailure, false, randomString(paymentFailureReasonLists(nextPaymentFailure)));
  }
  
  def generateOrderJSON() : String = {
    var nextProduct : String = "";
    var payType : String = "";
    // payment-type trend
    if (Random.nextFloat() < 0.004f) { for (mod <- modset_payment_types.mods) { mod.startTrendGrowOnly(); } }
    payType = modset_payment_types.getMod().useName();
    nextProduct = payType match {
      case "Card" => generateOrderJSON_Card();
      case "Internet Banking" => generateOrderJSON_InternetBanking();
      case "UPI" => generateOrderJSON_UPI();
      case "Wallet" => generateOrderJSON_Wallet();
      case "Failure" => generateOrderJSON_Failed();
    }
    return nextProduct.replace("\n","").replace("\t","");
  }
  
  def initializeGenerator() : Unit = {

    modset_countries("United States").substrings =  MutableList("New York","Los Angeles","Chicago","Houston","Phoenix","Philadelphia","San Antonio","San Diego","Dallas","San Jose","Austin","Jacksonville","Fort Worth","Columbus","Indianapolis","Charlotte","San Francisco","Seattle","Denver","Washington","Nashville","Oklahoma City","El Paso","Boston","Portland","Las Vegas","Detroit","Memphis","Louisville","Baltimore","Milwaukee","Albuquerque","Tucson","Fresno","Sacramento","Kansas City","Mesa","Atlanta","Omaha","Colorado Springs","Raleigh","Long Beach","Virginia Beach","Miami","Oakland","Minneapolis","Tulsa","Bakersfield","Wichita","Arlington","Aurora","Tampa","New Orleans","Cleveland","Honolulu","Anaheim","Lexington","Stockton","Corpus Christi","Henderson","Riverside","Newark","Saint Paul","Santa Ana","Cincinnati","Irvine","Orlando","Pittsburgh","St. Louis","Greensboro","Jersey City","Anchorage","Lincoln","Plano","Durham","Buffalo","Chandler","Chula Vista","Toledo","Madison","Gilbert","Reno","Fort Wayne","North Las Vegas","St. Petersburg","Lubbock","Irving","Laredo","Winston–Salem","Chesapeake","Glendale","Garland","Scottsdale","Norfolk","Boise","Fremont","Spokane","Santa Clarita","Baton Rouge","Richmond","Hialeah","San Bernardino","Tacoma","Modesto","Huntsville","Des Moines","Yonkers","Rochester","Moreno Valley","Fayetteville","Fontana","Columbus","Worcester","Port St. Lucie","Little Rock","Augusta","Oxnard","Birmingham","Montgomery","Frisco","Amarillo","Salt Lake City","Grand Rapids","Huntington Beach","Overland Park","Glendale","Tallahassee","Grand Prairie","McKinney","Cape Coral","Sioux Falls","Peoria","Providence","Vancouver","Knoxville","Akron","Shreveport","Mobile","Brownsville","Newport News","Fort Lauderdale","Chattanooga","Tempe","Aurora","Santa Rosa","Eugene","Elk Grove","Salem","Ontario","Cary","Rancho Cucamonga","Oceanside","Lancaster","Garden Grove","Pembroke Pines","Fort Collins","Palmdale","Springfield","Clarksville","Salinas","Hayward","Paterson","Alexandria","Macon","Corona","Kansas City","Lakewood","Springfield","Sunnyvale","Jackson","Killeen","Hollywood","Murfreesboro","Pasadena","Bellevue","Pomona","Escondido","Joliet","Charleston","Mesquite","Naperville","Rockford","Bridgeport","Syracuse","Savannah","Roseville","Torrance","Fullerton","Surprise","McAllen","Thornton","Visalia","Olathe","Gainesville","West Valley City","Orange","Denton","Warren","Pasadena","Waco","Cedar Rapids","Dayton","Elizabeth","Hampton","Columbia","Kent","Stamford","Lakewood","Victorville","Miramar","Coral Springs","Sterling Heights","New Haven","Carrollton","Midland","Norman","Santa Clara","Athens","Thousand Oaks","Topeka","Simi Valley","Columbia","Vallejo","Fargo","Allentown","Pearland","Concord","Abilene","Arvada","Berkeley","Ann Arbor","Independence","Rochester","Lafayette","Hartford","College Station","Clovis","Fairfield","Palm Bay","Richardson","Round Rock","Cambridge","Meridian","West Palm Beach","Evansville","Clearwater","Billings","West Jordan","Richmond","Westminster","Manchester","Lowell","Wilmington","Antioch","Beaumont","Provo","North Charleston","Elgin","Carlsbad","Odessa","Waterbury","Springfield","League City","Downey","Gresham","High Point","Broken Arrow","Peoria","Lansing","Lakeland","Pompano Beach","Costa Mesa","Pueblo","Lewisville","Miami Gardens","Las Cruces","Sugar Land","Murrieta","Ventura","Everett","Temecula","Dearborn","Santa Maria","West Covina","El Monte","Greeley","Sparks","Centennial","Boulder","Sandy Springs","Inglewood","Edison","South Fulton","Green Bay","Burbank","Renton","Hillsboro","El Cajon","Tyler","Davie");
    modset_countries("China").substrings =          MutableList("Beijing","Shanghai","Bengbu","Hefei","Fuzhou","Xiamen","Lanzhou","Guiyang","Zhengzhou","Changsha","Wuxi","Nanchang","Jilin","Dandong","Fuxin","Jinzhou","Yingkou","Jinan","Qingdao","Taiyuan","Chengdu","Zigong","Ürümqi","Kunming","Hangzhou","Wenzhou","Ningbo","Hohhot","Baotou","Huangshi","Huainan","Kashgar","Yining","Yanji","Jingdezhen","Nanjing","Manzhouli","Guangzhou","Harbin","Wuhan","Changchun","Anshan","Benxi","Dalian","Fushun","Shenyang","Xi'an","Xining","Ma'anshan","Zhuzhou","Yinchuan","Gejiu","Lhasa","Huaibei","Pingxiang","Zaozhuang","Zibo","Yumen","Guilin","Liuzhou","Wuzhou","Nanning","Kaifeng","Changzhou","Lianyungang","Nantong","Suzhou","Xuzhou","Qiqihar","Luoyang","Tumen","Panzhihua","Liaoyang","Daqing","Erenhot","Hegang","Jixi","Shuangyashan","Duyun","Tongchuan","Tianjin","Pingdingshan","Pingxiang","Houma","Jiayuguan","Baoji","Tongling","Datong","Yangquan","Wuhu","Shiyan","Hebi","Jiaozuo","Changzhi","Suifenhe","Kuytun","Wuhai","Foshan","Jiangmen","Maoming","Shantou","Shaoguan","Zhanjiang","Shizuishan","Shihezi","Shijiazhuang","Tangshan","Liupanshui","Shenzhen","Zhuhai","Xiangyang","Yichang","Xichang","Hongjiang","Korla","Anqing","Laohekou","Yichun","Jinshi","Hengyang","Shaoyang","Xiangtan","Jiujiang","Golmud","Ulanhot","Kaiyuan","Jinchang","Yima","Heshan","Enshi","Karamay","Anyang","Xinxiang","Jishou","Dongying","Botou","Bei'an","Changshu","Huai'an","Yancheng","Yangzhou","Zhenjiang","Yueyang","Qinhuangdao","Luzhou","Sanming","Lengshuijiang","Changji","Xinyu","Yingtan","Huzhou","Jiaxing","Shaoxing","Shaowu","Deyang","Kaili","Danjiangkou","Ezhou","Jingmen","Aksu","Liaoyuan","Siping","Jining","Linqing","Weifang","Xintai","Yantai","Linxia","Puyang","Putian","Hancheng","Weinan","Xianyang","Hotan","Chuxiong","Dali","Beihai","Jiamusi","Mudanjiang","Qitaihe","Wudalianchi","Chifeng","Xilinhot","Yakeshi","Zhalantun","Baoding","Cangzhou","Chengde","Handan","Xingtai","Zhangjiakou","Panjin","Chaoyang","Tieling","Yong'an","Anda","Altay","Tacheng","Jinggangshan","Qingtongxia");
    modset_countries("United Kingdom").substrings = MutableList("Bath","Manchester","Birmingham","Bradford","Norwich","Brighton","Nottingham","Bristol","Oxford","Carlisle","Peterborough","Cambridge","Plymouth","Canterbury","Portsmouth","Chelmsford","Preston","Chester","Ripon","Chichester","Salford","Coventry","Salisbury","Derby","Sheffield","Durham","Southampton","Ely","St Albans","Exeter","Gloucester","Sunderland","Hereford","Truro","Wakefield","Lancaster","Wells","Leeds","Westminster","Leicester","Winchester","Lichfield","Wolverhampton","Lincoln","Worcester","Liverpool","York","London");
    modset_countries("Japan").substrings =          MutableList("Tokyo","Osaka","Kyoto","Aichi","Hyōgo","Kanagawa","Ishikawa","Miyagi","Hiroshima","Tokushima","Toyama","Kagoshima","Wakayama","Nagasaki","Fukuoka","Hokkaido","Kumamoto","Okayama","Osaka","Niigata","Fukui","Shizuoka","Shimane","Ehime","Kōchi","Iwate","Yamanashi","Tochigi","Aomori","Shiga","Yamaguchi","Yamagata","Akita","Nagano","Yamagata","Nagano","Toyama","Tottori","Mie","Gunma","Mie","Gifu","Hyōgo","Saga","Osaka","Ibaraki","Fukuoka");
    modset_countries("Germany").substrings =        MutableList("Berlin","Hamburg","Munich","Cologne","Frankfurt","Stuttgart","Düsseldorf","Dortmund","Essen","Leipzig","Dresden","Nuremberg","Duisburg","Bochum","Wuppertal","Bonn","Münster","Karlsruhe","Mannheim","Augsburg","Wiesbaden","Gelsenkirchen","Mönchengladbach","Braunschweig","Chemnitz","Kiel","Lübeck","Oberhausen","Erfurt","Rostock","Kassel","Hagen","Hamm","Potsdam","Leverkusen","Solingen");
    modset_countries("France").substrings =         MutableList("Paris","Marseille","Lyon","Toulouse","Nice","Nantes","Strasbourg","Montpellier","Bordeaux","Lille","Rennes","Reims","Le Havre","Saint-Étienne","Toulon","Grenoble","Dijon","Nîmes","Angers","Villeurbanne","Le Mans","Saint-Denis","Aix-en-Provence","Clermont-Ferrand","Brest","Limoges","Tours","Amiens","Perpignan","Metz","Besançon","Boulogne-Billancourt","Orléans","Mulhouse","Rouen","Saint-Denis","Caen","Argenteuil","Saint-Paul","Montreuil","Nancy","Roubaix","Tourcoing","Nanterre","Avignon","Vitry-sur-Seine","Créteil","Dunkirk","Poitiers","Asnières-sur-Seine","Courbevoie","Versailles","Colombes","Fort-de-France","Aulnay-sous-Bois","Saint-Pierre","Rueil-Malmaison","Pau","Aubervilliers","Le Tampon","Champigny-sur-Marne","Antibes","Béziers","La Rochelle","Saint-Maur-des-Fossés","Cannes","Calais","Saint-Nazaire","Mérignac","Drancy","Colmar","Ajaccio","Bourges","Issy-les-Moulineaux","Levallois-Perret","La Seyne-sur-Mer","Quimper","Noisy-le-Grand","Villeneuve-d'Ascq","Neuilly-sur-Seine","Valence","Antony","Cergy","Vénissieux","Pessac","Troyes","Clichy","Ivry-sur-Seine","Chambéry","Lorient","Les Abymes","Montauban","Sarcelles","Niort","Villejuif","Saint-André","Hyères","Saint-Quentin","Beauvais","Épinay-sur-Seine");
    modset_countries("Canada").substrings =         MutableList("Toronto","Montreal","Calgary","Ottawa","Edmonton","Mississauga","Winnipeg","Vancouver","Brampton","Hamilton","Quebec City","Surrey","Laval","Halifax","London","Markham","Vaughan","Gatineau","Saskatoon","Longueuil","Kitchener","Burnaby","Windsor","Regina","Richmond","Richmond Hill","Oakville","Burlington","Greater Sudbury","Sherbrooke","Oshawa","Saguenay","Lévis","Barrie","Abbotsford","Coquitlam","Trois-Rivières","St. Catharines","Guelph","Cambridge","Whitby","Kelowna","Kingston","Ajax","Langley","Saanich","Terrebonne","Milton","St. John's","Thunder Bay","Waterloo","Delta","Chatham-Kent","Red Deer","Strathcona County","Brantford","Saint-Jean-sur-Richelieu","Cape Breton","Lethbridge","Clarington","Pickering","Nanaimo","Kamloops","Niagara Falls","North Vancouver","Victoria","Brossard","Repentigny","Newmarket","Chilliwack","Maple Ridge","Peterborough","Kawartha Lakes","Drummondville","Saint-Jérôme","Prince George","Sault Ste. Marie","Moncton","Sarnia","Wood Buffalo","New Westminster","Saint John","Caledon","Granby","St. Albert","Norfolk County","Medicine Hat","Grande Prairie","Airdrie","Halton Hills","Port Coquitlam","Fredericton","Blainville","Saint-Hyacinthe","Aurora","North Vancouver","Welland","North Bay","Belleville","Mirabel");
    modset_countries("Italy").substrings =          MutableList("Rome","Milan","Naples","Turin","Palermo","Genoa","Bologna","Florence","Catania","Bari","Messina","Verona","Padova","Trieste","Brescia","Prato","Taranto","Reggio Calabria","Modena","Livorno","Cagliari","Mestre","Parma","Foggia","Reggio nell'Emilia","Acilia-Castel Fusano-Ostia Antica","Salerno","Perugia","Monza","Rimini","Pescara","Bergamo","Vicenza","Bolzano","Andria","Udine","Siracusa","Terni","Forli","Novara","Barletta","Piacenza","Ferrara","Sassari","Ancona","La Spezia","Torre del Greco","Como","Lucca","Ravenna","Lecce","Trento","Giugliano in Campania","Busto Arsizio","Lido di Ostia","Cesena","Catanzaro","Brindisi","Marsala","Treviso","Pesaro","Pisa","Varese","Sesto San Giovanni","Arezzo","Latina","Gela","Pistoia","Caserta","Cinisello Balsamo","Lamezia Terme","Altamura","Guidonia Montecelio","Quartu Sant'Elena","Pavia","Castellammare di Stabia","Massa","Alessandria","Cosenza","Afragola","Ragusa","Asti","Grosseto","Cremona","Molfetta","Trapani","Carrara","Casoria","Savona","Vigevano","Legnano","Caltanissetta","Potenza","Portici","Matera","San Severo","Cerignola","Trani","Bisceglie","Acerra","Ercolano","Carpi Centro","Imola","Bagheria","Manfredonia","Aversa","Bitonto","Venice","Vittoria","Gallarate","Marano di Napoli","Pordenone","Acireale","Scafati","Moncalieri","Viareggio","Benevento","Crotone","Velletri","Cava De Tirreni","Avellino","Foligno","Nichelino","Civitavecchia","Viterbo","Battipaglia","Rho","San Remo","Lecco","Collegno","Corato","Cuneo","Paderno Dugnano","Rivoli","Paterno","Montesilvano Marina","Mazara del Vallo","San Benedetto del Tronto","Casalnuovo di Napoli","Cologno Monzese","Sesto Fiorentino","Vercelli","Pozzuoli","Misterbianco","San Giorgio a Cremano","Anzio","Nocera Inferiore","Scandicci","Nettuno","Frosinone","Chieti","Alcamo","Settimo Torinese","Torre Annunziata","Biella","Olbia","Siena","Seregno","Gravina in Puglia","Chioggia","Ascoli Piceno","Faenza","Civitanova Marche","Modica","Capannori","Aprilia","Lodi","Cascina","Desio","Marcianise","Nicastro","Rozzano","Campobasso","Rovigo","Mantova","Rieti","Fano","Pomigliano d'Arco","Imperia","Bassano del Grappa","Saronno","Marino","Avezzano","Monopoli","Martina Franca","Quarto","Lissone","Cantu","Monterotondo","Cesano Maderno","Jesi","Melito di Napoli","Sassuolo","Sciacca","Licata","Modugno","Teramo","Voghera","Nuoro","Caltagirone");
    modset_countries("Spain").substrings =          MutableList("Madrid","Barcelona","Valencia","Sevilla","Zaragoza","Malaga","Murcia","Palma","Las Palmas de Gran Canaria","Bilbao","Alicante","Cordoba","Valladolid","Vigo","Gijon","Eixample","L'Hospitalet de Llobregat","Latina","Carabanchel","A Coruna","Puente de Vallecas","Sant Marti","Gasteiz / Vitoria","Granada","Elche","Ciudad Lineal","Oviedo","Santa Cruz de Tenerife","Fuencarral-El Pardo","Badalona","Cartagena","Terrassa","Jerez de la Frontera","Sabadell","Mostoles","Alcala de Henares","Pamplona","Fuenlabrada","Almeria","Leganes","San Sebastian","Sants-Montjuic","Santander","Castello de la Plana","Burgos","Albacete","Horta-Guinardo","Alcorcon","Getafe","Nou Barris","Hortaleza","San Blas-Canillejas","Salamanca","Tetuan de las Victorias","Logrono","La Laguna","City Center","Huelva","Arganzuela","Badajoz","Sarria-Sant Gervasi","Sant Andreu","Salamanca","Chamberi","Usera","Tarragona","Chamartin","Lleida","Marbella","Leon","Villaverde","Cadiz","Retiro","Dos Hermanas","Mataro","Gracia","Santa Coloma de Gramenet","Torrejon de Ardoz","Jaen","Moncloa-Aravaca","Algeciras","Parla","Delicias","Ourense","Alcobendas","Reus","Moratalaz","Ciutat Vella","Torrevieja","Telde","Barakaldo","Lugo","San Fernando","Girona","Santiago de Compostela","Caceres","Lorca","Coslada","Talavera de la Reina","El Puerto de Santa Maria","Cornella de Llobregat","Las Rozas de Madrid","Orihuela","Aviles","El Ejido","Guadalajara","Roquetas de Mar","Palencia","Algorta","Pozuelo de Alarcon","Sant Boi de Llobregat","Toledo","Les Corts","Pontevedra","Getxo","Gandia","Sant Cugat del Valles","Ceuta","Arona","Torrent","Chiclana de la Frontera","Manresa","San Sebastian de los Reyes","Ferrol","Velez-Malaga","Ciudad Real","Mijas","Melilla","Rubi","Fuengirola","Benidorm","Alcala de Guadaira","Ponferrada","Rivas-Vaciamadrid","Majadahonda","Campina","Vicalvaro","Zamora","Sagunto","Vilanova i la Geltru","Sanlucar de Barrameda","Estepona","Torremolinos","Villa de Vallecas","La Linea de la Concepcion","Molina de Segura","Paterna","Santa Lucia","Viladecans","El Prat de Llobregat","Valdemoro","Grao de Murviedro","Castelldefels","Alcoy","Linares","Irun","Granollers","Motril","Santutxu","Arrecife","Benalmadena","Cerdanyola del Valles","la Nova Esquerra de l'Eixample","Avila","Barri de Sant Andreu","Segovia","Merida","Cuenca","Torrelavega","Elda","Collado-Villalba","Aranjuez","San Vicent del Raspeig","Mollet del Valles","San Bartolome de Tirajana","Huesca","Puertollano","Calvia","Sagrada Familia","Arganda","Vila-real","la Vila de Gracia","Utrera","Ibiza","el Raval","Portugalete","Sant Gervasi - Galvany","Santurtzi","Esplugues de Llobregat","Barri de les Corts","Gava","Sama","Antequera","Alzira","Denia","Mieres","Dreta de l'Eixample","Llefia","Mislata","Colmenar Viejo");
    modset_countries("South Korea").substrings =    MutableList("Seoul","Busan","Incheon","Daegu","Daejeon","Gwangju","Suwon","Goyang-si","Seongnam-si","Ulsan","Bucheon-si","Jeonju","Ansan-si","Cheongju-si","Anyang-si","Changwon","Pohang","Uijeongbu-si","Hwaseong-si","Masan","Jeju City","Cheonan","Kwangmyong","Kimhae","Chinju","Yeosu","Gumi","Iksan","Mokpo","Gunsan","Wonju","Suncheon","Sejong","Chuncheon","Icheon-si","Guri-si","Gangneung","Yangju","Osan","Seogwipo","Gyeongju","Gimcheon","Jeongeup","Hanam","Gyeongsan-si","Andong","Hwado","Tonghae","Asan","Wabu","Namyangju","Kwangyang","Hongseong","Sokcho","Eisen","Wanju","Yangp'yong","Ungsang","Sinhyeon","Gwangju","Mungyeong","Naeso","Muan","Hongch'on","Changnyeong","Seosan","Koch'ang","Gongju","Kyosai","Yangsan","Anseong","Hwawon","Pubal","Kosong","Taisen-ri","Chinch'on","Hwasun","Nangen","Okcheon","Eisen","Sangju","Gapyeong","Yeongam","Sinan","Miryang","T'aebaek","Kimje","Koesan","Nonsan","Munsan","Gijang","Naju","Santyoku","Yeonil","Hayang","Yesan","Imsil","Yeoju","Heunghae","Kunwi","Tangjin","Fuyo","Cheongsong gun","Jinan-gun","Kurye","Waegwan","Seonghwan","Goseong","Changsu","Ganghwa-gun","Neietsu","Kinzan","Yonmu","Yong-dong","Yanggu","Haenam","Hwacheon","Gaigeturi","Taesal-li","Puan","Jenzan","Yeonggwang","Beolgyo","Sintansin","Yongsan-dong","Songgangdong","Pyeongchang");

    modset_countries("United States").setProbabilityTrendInfo(0.44409f, 1.175f * 0.44409f);
    modset_countries("China").setProbabilityTrendInfo(0.32339f, 1.26f * 0.32339f);
    modset_countries("United Kingdom").setProbabilityTrendInfo(0.02947f, /*1.12f **/ 0.02947f); 
    modset_countries("Japan").setProbabilityTrendInfo(0.05404f, /*1.13f **/ 0.05404f);  
    modset_countries("Germany").setProbabilityTrendInfo(0.03610f, /*1.14f **/ 0.03610f);  
    modset_countries("France").setProbabilityTrendInfo(0.02822f, /*1.13f **/ 0.02822f); 
    modset_countries("Canada").setProbabilityTrendInfo(0.01652f, /*1.12f **/ 0.01652f); 
    modset_countries("Italy").setProbabilityTrendInfo(0.02593f, /*1.13f **/ 0.02593f);  
    modset_countries("Spain").setProbabilityTrendInfo(0.02010f, /*1.14f **/ 0.02010f);  
    modset_countries("South Korea").setProbabilityTrendInfo(0.02209f, /*1.13f **/ 0.02209f);

    modset_tool_materials("Iron").setProbabilityTrendInfo(0.4f, 0.2f);
    modset_tool_materials("Steel").setProbabilityTrendInfo(0.33f, 0.66f);
    modset_tool_materials("Titanium").setProbabilityTrendInfo(0.1f, 0.1f);

    modset_clothing_materials("Cotton").setPricePeakAsMultiplier(2f);
    modset_clothing_materials("Cotton").setProbabilityTrendInfo(0.6f, 0.3f);
    modset_clothing_materials("Denim").setPricePeakAsMultiplier(1.33f);
    modset_clothing_materials("Denim").setProbabilityTrendInfo(0.3f, 0.6f);
    modset_clothing_materials("Silk").setProbabilityTrendInfo(0.1f, 0.2f);

    modset_electronic_types("Laptop").setPricePeakAsMultiplier(2f);
    modset_electronic_types("Laptop").setProbabilityPeakAsMultiplier(2.75f);
    
    modset_payment_types("Failure").setProbabilityTrendInfo(0.05f, 0.05f);            // Failure rate is constant 5%
    modset_payment_types("Card").setProbabilityTrendInfo(0.2375f, 0.3f);               // Card payments increase over time
    modset_payment_types("Internet Banking").setProbabilityTrendInfo(0.2375f, 0.21f);  //
    modset_payment_types("UPI").setProbabilityTrendInfo(0.2375f, 0.21f);               // All other payment types decrease over time
    modset_payment_types("Wallet").setProbabilityTrendInfo(0.2375f, 0.21f);            //
    for (mod <- modset_payment_types.mods) { mod.setTrendGrowthFactorAsMultiplier(0.2f); } // Decrease trend speed

  }

  def testRun() : Unit = {

    var orderJSON : String = "";
    for (i <- 0 until generatorRunCount) {
      orderJSON = generateOrderJSON();
      println(orderJSON);
    }
    
    // println();
    // println(modset_payment_types.getDistributionString(true));
    // println();
    // println(modset_electronic_brands.getDistributionString(true));
    // println();
    // println(modset_electronic_types.getDistributionString(true));
    // println();
    // println(modset_clothing_materials.getDistributionString(true));
    // println();
    // println(modset_tool_materials.getDistributionString(true));
    // println();
    // println(modset_countries.getDistributionString(true));
    // println();

  }

  initializeGenerator();
  testRun();

}