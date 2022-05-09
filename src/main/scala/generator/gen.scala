import GeneratorObjects._
import scala.util.Random

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
  
  def randomString(list: List[String]) : String = {
    return list(Random.nextInt(list.size));
  }

  // order_id = increasing integer value
  
  // payment_type = pull from {card, Internet Banking, UPI, Wallet}
  // qty (Quantity ordered) = randomInRange
  // price (Price of the product) = dictionary<product_name, float>
  // datetime (Date and time when order was placed)
  
  // ecommerce_website_name (Site from where order was placed) = pull from weighted listOfWebsites
  // payment_txn_id (Payment Transaction Confirmation Id) = another unique integer based on order id?
  // payment_txn_success (Payment Success or Failure (Y=Success. N=Failed)) = boolean based on failure probability
  // failure_reason (Reason for payment failulure) = pull from weighted listOfFailureReasons

  // customer_id = dictionary<customer_name, int>
  var customer_ids = collection.mutable.Map[String, Int]();
  // customer_name
  val first_names : List[String] = List("Larisa","Tabbatha","Jehu","Arluene","Brina","Clare","Petronella","Tiphani","Vanda","Duffie","Hinda","Augustina","Benny","Gene","Thaine","Ailene","Kat","Barthel","Oralla","Patrizia","Maxim","Aland","Stepha","Sandor","Thaddus","Kyle","Celestyn","Dory","Ophelia","Agathe","Daisey","Morna","Juliet","Smitty","Had","Barny","Wittie","Saidee","Merrie","Linell","Rorke","Trenna","Les","Englebert","Ronica","Adeline","Carlye","Federica","Lil","James","Joaquin","Engelbert","Alyce","Anna-maria","Britteny","Annice","Lela","Lorraine","Agneta","Noble","Galvin","Stanleigh","Darb","Pren","Carey","Ced","Nan","Sephira","Petronia","Susi","Cecile","Mary","Cecil","Melicent","Care","Editha","William","Krishnah","Norbie","Lynnet","Basil","Cris","Dollie","Myrwyn","Micheal","Morie","Gerladina","Crichton","Leontine","Vale","Dari","Bogey","Lyon","Konstance","Faina","Pepita","Muffin","Rosalinda","Myrwyn","Ari","Phylys","Bertie","Vonny","Marve","Cassie","Calida","Kellia","Kyle","Stavros","Carma","Emory","Silas","Waylon","Chloette","Elva","Leola","Nerty","Donelle","Gabey","Ody","Kristine","Brucie","Harbert","Jonell","Helsa","Marion","Thomasine","Darci","Bernarr","Gordan","Alvin","Colly","Nadine","Dulsea","Zitella","Adlai","Cart","Harald","Ailsun","Link","Feliza","Ronnie","Padget","Josie","Kandy","Pet","Tabbitha","Rheba","Alexander","Sabrina","Lorianna","Titus","Rosalinde","Germaine","Octavia","Faulkner","Hortensia","Elwira","Jervis","Meggy","Connie","Terrye","Brenda","Jillayne","Gardner","Petunia","Gannie","Berkley","Loydie","Maryanne","Cathrin","Salvidor","Marlin","Roma","Salli","Harriett","Cymbre","Anthea","Elissa","Martin","Sherlock","Ferrell","Bone","Johny","Derwin","Delilah","Mead","Lacee","Maxwell","Rosabella","Coleman","Fredric","Charil","Noah","Madelene","Enos","Cordelia","Adelheid","Robers","Melli","Izak","Trueman","Marianna","Dory","Teresina","Allis","Averil","Britt","Vance","Pen","Rowena","Constantine","Krissie","Cobbie","Sandy","Torrey","Roberto","Natassia","Shaylynn","Josh","Nelly","Jody","Gan","Laurena","Erhart","Danny","Etienne","Lucio","Myra","Francklyn","Immanuel","Jordon","Iosep","Terrijo","Averyl","Ronnie","Jonathan","Nessy","Vasily","Gunar","Leroy","Allyson","Lorne","Ollie","Preston","Ulrich","Claudian","Robinetta","Deny","Eachelle","Gard","Merrielle","Corbett","Pennie","Timmie","Erroll","Teddy","Nickola","Timothy","Hugo","Hastings","Lionello","Tana","Yehudit","Milicent","Morgen","Colby","Immanuel","Culver","Selestina","Dolph","Becki","Donielle","Vonny","Mitchael","Shauna","Stan","Bat","Fowler","Yardley","Avril","John","Alexina","Danella","Hilliard","Charlton","Philly","Jane","Micky","Byrle","Kris","Jamill","Tammara","Enrichetta","Colly","Corinna","Delphine","Cherilynn","Manuel","Stan");
  val last_names : List[String] = List("Whale","Gurwood","Ludman","Ollivier","Shelbourne","Gammie","Powlett","Woollin","Bentz","Palatini","Aneley","Cogswell","Ullrich","Castanares","Anglim","Alessandrelli","Pechacek","O'Dooghaine","Paxforde","Sandeland","Tovey","Kubec","Finkle","Hamper","Meenan","Yarranton","Huband","Perkin","Duchenne","Rampling","Chyuerton","Olivia","Brigdale","Navarijo","Penwright","Highwood","Titcom","Toffoletto","Riche","Alan","Ramlot","Hildrew","Jesse","Lusgdin","Pothergill","Genn","Terbrugge","Goodhand","Norrie","Meneyer","Floweth","Rozenbaum","Crayden","Hawyes","Grinham","Dufore","McAlindon","Tatham","Demaid","Bartoshevich","Jerisch","Ruvel","Larderot","Stanbra","Spellicy","Jullian","Willwood","McGrouther","Stoggles","Wakely","Baughn","Darrel","Credland","Gretton","Dielhenn","Ten Broek","Gustus","Muckleston","Imlin","Doxsey","Sporle","Bridal","Isaaksohn","Breagan","Martinello","Thrustle","Ewestace","Gollin","Lewerenz","Pethick","Jays","Gadney","Simmens","Neath","Bunclark","Stonebanks","Behnke","Vasechkin","Duthy","Crowe","Aveyard","Flacknell","Gyurko","Vell","Heale","Ablewhite","Messitt","Penniall","Timbrell","Hewell","Hanratty","Gothup","Schwand","Bartunek","Humbey","Howood","Alltimes","Hutson","Mathew","Klasen","Mitrovic","Trowbridge","Wythill","Guyon","Brigman","Martignoni","McGill","Necolds","Morewood","Cossentine","Hollow","Morrel","Stephens","Oswald","de Aguirre","Lerhinan","Burniston","Beedon","Mallan","Rogans","Bond","Kless","Rowell","Bramich","Barkaway","Troutbeck","Winchcombe","Brombell","Reder","Keenlayside","Cohen","D'Abbot-Doyle","Finnigan","Howcroft","Mauchline","Maddams","Werrilow","Sarfatti","Strathman","Dufer","Severs","Leversuch","Izhakov","Fabb","Kegan","Darnell","Vinick","Kemshell","Ribou","Pape","Burgett","Connor","Shapcott","Roz","Thome","Meneyer","Germon","Casbon","Jon","Coning","Kubera","Dodgshon","Jansens","Bomb","Bartot","Terry","Iacobetto","Mattussevich","Grolmann","Freer","Troth","Kingh","Colgan","Fairlie","Bignold","Gilberthorpe","Rosindill","Karolowski","Dovington","Aitken","Witherbed","Leipold","Vescovini","Beaney","Duggon","Frankis","Darwin","Ventris","Boarer","Estick","Amps","Balling","Sperrett","Foard","Knowlys","Boughen","Cornbill","Mossdale","Supple","Bernuzzi","Haggerty","Keal","Bohje","Gatward","Loding","Maggiore","Ashment","Soldi","Dymick","Stolze","Forton","England","Tuite","Manoelli","Gilhespy","Dallas","Sealeaf","Petti","Totterdill","Hallihan","Dudman","Harcase","McCorkell","Drewet","Dongate","Kollatsch","Flemmich","Caldero","Ribeiro","Pougher","Waterhous","Challicombe","Gorgler","Cisco","Mallender","Segrave","Samsin","Beckerleg","De Morena","Merioth","Hayter","Pancast","Bear","Jackes","Micka","Wooton","Chatainier","Ellerey","Krzyzaniak","Witcherley","Hayer","Rizzello","Hallock","Carvilla","Baynes","Giffkins","Jesteco","Packington","Stive","Dinsmore","Berrey","Beyn","McClenan","Mountney","Pietroni","Dowsey","Yackiminie","Phair","Bazek","McCrostie","Casina","Lehon","Cometti","Jermin","Clapperton","Solman","Kinson","Jewell","Philipard","Yarrall");  
  val pregenerated_names : List[String] = List("Walmart", "Home Depot")

  // country (Customer Country) = pull from weighted listOfCountries
  // city (Customer City) = pull from dictionary<country, city_list>

  val country_names : List[String] = List("United States","China","United Kingdom", "Japan","Germany","France","Canada","Italy","Spain","South Korea");
  var country_cities = collection.mutable.Map[String, List[String]]();
  country_cities += ("United States" -> List("New York","Los Angeles","Chicago","Houston","Phoenix","Philadelphia","San Antonio","San Diego","Dallas","San Jose","Austin","Jacksonville","Fort Worth","Columbus","Indianapolis","Charlotte","San Francisco","Seattle","Denver","Washington","Nashville","Oklahoma City","El Paso","Boston","Portland","Las Vegas","Detroit","Memphis","Louisville","Baltimore","Milwaukee","Albuquerque","Tucson","Fresno","Sacramento","Kansas City","Mesa","Atlanta","Omaha","Colorado Springs","Raleigh","Long Beach","Virginia Beach","Miami","Oakland","Minneapolis","Tulsa","Bakersfield","Wichita","Arlington","Aurora","Tampa","New Orleans","Cleveland","Honolulu","Anaheim","Lexington","Stockton","Corpus Christi","Henderson","Riverside","Newark","Saint Paul","Santa Ana","Cincinnati","Irvine","Orlando","Pittsburgh","St. Louis","Greensboro","Jersey City","Anchorage","Lincoln","Plano","Durham","Buffalo","Chandler","Chula Vista","Toledo","Madison","Gilbert","Reno","Fort Wayne","North Las Vegas","St. Petersburg","Lubbock","Irving","Laredo","Winston–Salem","Chesapeake","Glendale","Garland","Scottsdale","Norfolk","Boise","Fremont","Spokane","Santa Clarita","Baton Rouge","Richmond","Hialeah","San Bernardino","Tacoma","Modesto","Huntsville","Des Moines","Yonkers","Rochester","Moreno Valley","Fayetteville","Fontana","Columbus","Worcester","Port St. Lucie","Little Rock","Augusta","Oxnard","Birmingham","Montgomery","Frisco","Amarillo","Salt Lake City","Grand Rapids","Huntington Beach","Overland Park","Glendale","Tallahassee","Grand Prairie","McKinney","Cape Coral","Sioux Falls","Peoria","Providence","Vancouver","Knoxville","Akron","Shreveport","Mobile","Brownsville","Newport News","Fort Lauderdale","Chattanooga","Tempe","Aurora","Santa Rosa","Eugene","Elk Grove","Salem","Ontario","Cary","Rancho Cucamonga","Oceanside","Lancaster","Garden Grove","Pembroke Pines","Fort Collins","Palmdale","Springfield","Clarksville","Salinas","Hayward","Paterson","Alexandria","Macon","Corona","Kansas City","Lakewood","Springfield","Sunnyvale","Jackson","Killeen","Hollywood","Murfreesboro","Pasadena","Bellevue","Pomona","Escondido","Joliet","Charleston","Mesquite","Naperville","Rockford","Bridgeport","Syracuse","Savannah","Roseville","Torrance","Fullerton","Surprise","McAllen","Thornton","Visalia","Olathe","Gainesville","West Valley City","Orange","Denton","Warren","Pasadena","Waco","Cedar Rapids","Dayton","Elizabeth","Hampton","Columbia","Kent","Stamford","Lakewood","Victorville","Miramar","Coral Springs","Sterling Heights","New Haven","Carrollton","Midland","Norman","Santa Clara","Athens","Thousand Oaks","Topeka","Simi Valley","Columbia","Vallejo","Fargo","Allentown","Pearland","Concord","Abilene","Arvada","Berkeley","Ann Arbor","Independence","Rochester","Lafayette","Hartford","College Station","Clovis","Fairfield","Palm Bay","Richardson","Round Rock","Cambridge","Meridian","West Palm Beach","Evansville","Clearwater","Billings","West Jordan","Richmond","Westminster","Manchester","Lowell","Wilmington","Antioch","Beaumont","Provo","North Charleston","Elgin","Carlsbad","Odessa","Waterbury","Springfield","League City","Downey","Gresham","High Point","Broken Arrow","Peoria","Lansing","Lakeland","Pompano Beach","Costa Mesa","Pueblo","Lewisville","Miami Gardens","Las Cruces","Sugar Land","Murrieta","Ventura","Everett","Temecula","Dearborn","Santa Maria","West Covina","El Monte","Greeley","Sparks","Centennial","Boulder","Sandy Springs","Inglewood","Edison","South Fulton","Green Bay","Burbank","Renton","Hillsboro","El Cajon","Tyler","Davie"));
  country_cities += ("China" -> List("Beijing","Shanghai","Bengbu","Hefei","Fuzhou","Xiamen","Lanzhou","Guiyang","Zhengzhou","Changsha","Wuxi","Nanchang","Jilin","Dandong","Fuxin","Jinzhou","Yingkou","Jinan","Qingdao","Taiyuan","Chengdu","Zigong","Ürümqi","Kunming","Hangzhou","Wenzhou","Ningbo","Hohhot","Baotou","Huangshi","Huainan","Kashgar","Yining","Yanji","Jingdezhen","Nanjing","Manzhouli","Guangzhou","Harbin","Wuhan","Changchun","Anshan","Benxi","Dalian","Fushun","Shenyang","Xi'an","Xining","Ma'anshan","Zhuzhou","Yinchuan","Gejiu","Lhasa","Huaibei","Pingxiang","Zaozhuang","Zibo","Yumen","Guilin","Liuzhou","Wuzhou","Nanning","Kaifeng","Changzhou","Lianyungang","Nantong","Suzhou","Xuzhou","Qiqihar","Luoyang","Tumen","Panzhihua","Liaoyang","Daqing","Erenhot","Hegang","Jixi","Shuangyashan","Duyun","Tongchuan","Tianjin","Pingdingshan","Pingxiang","Houma","Jiayuguan","Baoji","Tongling","Datong","Yangquan","Wuhu","Shiyan","Hebi","Jiaozuo","Changzhi","Suifenhe","Kuytun","Wuhai","Foshan","Jiangmen","Maoming","Shantou","Shaoguan","Zhanjiang","Shizuishan","Shihezi","Shijiazhuang","Tangshan","Liupanshui","Shenzhen","Zhuhai","Xiangyang","Yichang","Xichang","Hongjiang","Korla","Anqing","Laohekou","Yichun","Jinshi","Hengyang","Shaoyang","Xiangtan","Jiujiang","Golmud","Ulanhot","Kaiyuan","Jinchang","Yima","Heshan","Enshi","Karamay","Anyang","Xinxiang","Jishou","Dongying","Botou","Bei'an","Changshu","Huai'an","Yancheng","Yangzhou","Zhenjiang","Yueyang","Qinhuangdao","Luzhou","Sanming","Lengshuijiang","Changji","Xinyu","Yingtan","Huzhou","Jiaxing","Shaoxing","Shaowu","Deyang","Kaili","Danjiangkou","Ezhou","Jingmen","Aksu","Liaoyuan","Siping","Jining","Linqing","Weifang","Xintai","Yantai","Linxia","Puyang","Putian","Hancheng","Weinan","Xianyang","Hotan","Chuxiong","Dali","Beihai","Jiamusi","Mudanjiang","Qitaihe","Wudalianchi","Chifeng","Xilinhot","Yakeshi","Zhalantun","Baoding","Cangzhou","Chengde","Handan","Xingtai","Zhangjiakou","Panjin","Chaoyang","Tieling","Yong'an","Anda","Altay","Tacheng","Jinggangshan","Qingtongxia"));
  country_cities += ("United Kingdom" -> List("Bath","Manchester","Birmingham","Bradford","Norwich","Brighton","Nottingham","Bristol","Oxford","Carlisle","Peterborough","Cambridge","Plymouth","Canterbury","Portsmouth","Chelmsford","Preston","Chester","Ripon","Chichester","Salford","Coventry","Salisbury","Derby","Sheffield","Durham","Southampton","Ely","St Albans","Exeter","Gloucester","Sunderland","Hereford","Truro","Wakefield","Lancaster","Wells","Leeds","Westminster","Leicester","Winchester","Lichfield","Wolverhampton","Lincoln","Worcester","Liverpool","York","London"));
  country_cities += ("Japan" -> List("Tokyo","Osaka","Kyoto","Aichi","Hyōgo","Kanagawa","Ishikawa","Miyagi","Hiroshima","Tokushima","Toyama","Kagoshima","Wakayama","Nagasaki","Fukuoka","Hokkaido","Kumamoto","Okayama","Osaka","Niigata","Fukui","Shizuoka","Shimane","Ehime","Kōchi","Iwate","Yamanashi","Tochigi","Aomori","Shiga","Yamaguchi","Yamagata","Akita","Nagano","Yamagata","Nagano","Toyama","Tottori","Mie","Gunma","Mie","Gifu","Hyōgo","Saga","Osaka","Ibaraki","Fukuoka"));
  country_cities += ("Germany" -> List("Berlin","Hamburg","Munich","Cologne","Frankfurt","Stuttgart","Düsseldorf","Dortmund","Essen","Leipzig","Dresden","Nuremberg","Duisburg","Bochum","Wuppertal","Bonn","Münster","Karlsruhe","Mannheim","Augsburg","Wiesbaden","Gelsenkirchen","Mönchengladbach","Braunschweig","Chemnitz","Kiel","Lübeck","Oberhausen","Erfurt","Rostock","Kassel","Hagen","Hamm","Potsdam","Leverkusen","Solingen"));
  country_cities += ("France" -> List("Paris","Marseille","Lyon","Toulouse","Nice","Nantes","Strasbourg","Montpellier","Bordeaux","Lille","Rennes","Reims","Le Havre","Saint-Étienne","Toulon","Grenoble","Dijon","Nîmes","Angers","Villeurbanne","Le Mans","Saint-Denis","Aix-en-Provence","Clermont-Ferrand","Brest","Limoges","Tours","Amiens","Perpignan","Metz","Besançon","Boulogne-Billancourt","Orléans","Mulhouse","Rouen","Saint-Denis","Caen","Argenteuil","Saint-Paul","Montreuil","Nancy","Roubaix","Tourcoing","Nanterre","Avignon","Vitry-sur-Seine","Créteil","Dunkirk","Poitiers","Asnières-sur-Seine","Courbevoie","Versailles","Colombes","Fort-de-France","Aulnay-sous-Bois","Saint-Pierre","Rueil-Malmaison","Pau","Aubervilliers","Le Tampon","Champigny-sur-Marne","Antibes","Béziers","La Rochelle","Saint-Maur-des-Fossés","Cannes","Calais","Saint-Nazaire","Mérignac","Drancy","Colmar","Ajaccio","Bourges","Issy-les-Moulineaux","Levallois-Perret","La Seyne-sur-Mer","Quimper","Noisy-le-Grand","Villeneuve-d'Ascq","Neuilly-sur-Seine","Valence","Antony","Cergy","Vénissieux","Pessac","Troyes","Clichy","Ivry-sur-Seine","Chambéry","Lorient","Les Abymes","Montauban","Sarcelles","Niort","Villejuif","Saint-André","Hyères","Saint-Quentin","Beauvais","Épinay-sur-Seine"));
  country_cities += ("Canada" -> List("Toronto","Montreal","Calgary","Ottawa","Edmonton","Mississauga","Winnipeg","Vancouver","Brampton","Hamilton","Quebec City","Surrey","Laval","Halifax","London","Markham","Vaughan","Gatineau","Saskatoon","Longueuil","Kitchener","Burnaby","Windsor","Regina","Richmond","Richmond Hill","Oakville","Burlington","Greater Sudbury","Sherbrooke","Oshawa","Saguenay","Lévis","Barrie","Abbotsford","Coquitlam","Trois-Rivières","St. Catharines","Guelph","Cambridge","Whitby","Kelowna","Kingston","Ajax","Langley","Saanich","Terrebonne","Milton","St. John's","Thunder Bay","Waterloo","Delta","Chatham-Kent","Red Deer","Strathcona County","Brantford","Saint-Jean-sur-Richelieu","Cape Breton","Lethbridge","Clarington","Pickering","Nanaimo","Kamloops","Niagara Falls","North Vancouver","Victoria","Brossard","Repentigny","Newmarket","Chilliwack","Maple Ridge","Peterborough","Kawartha Lakes","Drummondville","Saint-Jérôme","Prince George","Sault Ste. Marie","Moncton","Sarnia","Wood Buffalo","New Westminster","Saint John","Caledon","Granby","St. Albert","Norfolk County","Medicine Hat","Grande Prairie","Airdrie","Halton Hills","Port Coquitlam","Fredericton","Blainville","Saint-Hyacinthe","Aurora","North Vancouver","Welland","North Bay","Belleville","Mirabel"));
  country_cities += ("Italy" -> List("Rome","Milan","Naples","Turin","Palermo","Genoa","Bologna","Florence","Catania","Bari","Messina","Verona","Padova","Trieste","Brescia","Prato","Taranto","Reggio Calabria","Modena","Livorno","Cagliari","Mestre","Parma","Foggia","Reggio nell'Emilia","Acilia-Castel Fusano-Ostia Antica","Salerno","Perugia","Monza","Rimini","Pescara","Bergamo","Vicenza","Bolzano","Andria","Udine","Siracusa","Terni","Forli","Novara","Barletta","Piacenza","Ferrara","Sassari","Ancona","La Spezia","Torre del Greco","Como","Lucca","Ravenna","Lecce","Trento","Giugliano in Campania","Busto Arsizio","Lido di Ostia","Cesena","Catanzaro","Brindisi","Marsala","Treviso","Pesaro","Pisa","Varese","Sesto San Giovanni","Arezzo","Latina","Gela","Pistoia","Caserta","Cinisello Balsamo","Lamezia Terme","Altamura","Guidonia Montecelio","Quartu Sant'Elena","Pavia","Castellammare di Stabia","Massa","Alessandria","Cosenza","Afragola","Ragusa","Asti","Grosseto","Cremona","Molfetta","Trapani","Carrara","Casoria","Savona","Vigevano","Legnano","Caltanissetta","Potenza","Portici","Matera","San Severo","Cerignola","Trani","Bisceglie","Acerra","Ercolano","Carpi Centro","Imola","Bagheria","Manfredonia","Aversa","Bitonto","Venice","Vittoria","Gallarate","Marano di Napoli","Pordenone","Acireale","Scafati","Moncalieri","Viareggio","Benevento","Crotone","Velletri","Cava De Tirreni","Avellino","Foligno","Nichelino","Civitavecchia","Viterbo","Battipaglia","Rho","San Remo","Lecco","Collegno","Corato","Cuneo","Paderno Dugnano","Rivoli","Paterno","Montesilvano Marina","Mazara del Vallo","San Benedetto del Tronto","Casalnuovo di Napoli","Cologno Monzese","Sesto Fiorentino","Vercelli","Pozzuoli","Misterbianco","San Giorgio a Cremano","Anzio","Nocera Inferiore","Scandicci","Nettuno","Frosinone","Chieti","Alcamo","Settimo Torinese","Torre Annunziata","Biella","Olbia","Siena","Seregno","Gravina in Puglia","Chioggia","Ascoli Piceno","Faenza","Civitanova Marche","Modica","Capannori","Aprilia","Lodi","Cascina","Desio","Marcianise","Nicastro","Rozzano","Campobasso","Rovigo","Mantova","Rieti","Fano","Pomigliano d'Arco","Imperia","Bassano del Grappa","Saronno","Marino","Avezzano","Monopoli","Martina Franca","Quarto","Lissone","Cantu","Monterotondo","Cesano Maderno","Jesi","Melito di Napoli","Sassuolo","Sciacca","Licata","Modugno","Teramo","Voghera","Nuoro","Caltagirone"));
  country_cities += ("Spain" -> List("Madrid","Barcelona","Valencia","Sevilla","Zaragoza","Malaga","Murcia","Palma","Las Palmas de Gran Canaria","Bilbao","Alicante","Cordoba","Valladolid","Vigo","Gijon","Eixample","L'Hospitalet de Llobregat","Latina","Carabanchel","A Coruna","Puente de Vallecas","Sant Marti","Gasteiz / Vitoria","Granada","Elche","Ciudad Lineal","Oviedo","Santa Cruz de Tenerife","Fuencarral-El Pardo","Badalona","Cartagena","Terrassa","Jerez de la Frontera","Sabadell","Mostoles","Alcala de Henares","Pamplona","Fuenlabrada","Almeria","Leganes","San Sebastian","Sants-Montjuic","Santander","Castello de la Plana","Burgos","Albacete","Horta-Guinardo","Alcorcon","Getafe","Nou Barris","Hortaleza","San Blas-Canillejas","Salamanca","Tetuan de las Victorias","Logrono","La Laguna","City Center","Huelva","Arganzuela","Badajoz","Sarria-Sant Gervasi","Sant Andreu","Salamanca","Chamberi","Usera","Tarragona","Chamartin","Lleida","Marbella","Leon","Villaverde","Cadiz","Retiro","Dos Hermanas","Mataro","Gracia","Santa Coloma de Gramenet","Torrejon de Ardoz","Jaen","Moncloa-Aravaca","Algeciras","Parla","Delicias","Ourense","Alcobendas","Reus","Moratalaz","Ciutat Vella","Torrevieja","Telde","Barakaldo","Lugo","San Fernando","Girona","Santiago de Compostela","Caceres","Lorca","Coslada","Talavera de la Reina","El Puerto de Santa Maria","Cornella de Llobregat","Las Rozas de Madrid","Orihuela","Aviles","El Ejido","Guadalajara","Roquetas de Mar","Palencia","Algorta","Pozuelo de Alarcon","Sant Boi de Llobregat","Toledo","Les Corts","Pontevedra","Getxo","Gandia","Sant Cugat del Valles","Ceuta","Arona","Torrent","Chiclana de la Frontera","Manresa","San Sebastian de los Reyes","Ferrol","Velez-Malaga","Ciudad Real","Mijas","Melilla","Rubi","Fuengirola","Benidorm","Alcala de Guadaira","Ponferrada","Rivas-Vaciamadrid","Majadahonda","Campina","Vicalvaro","Zamora","Sagunto","Vilanova i la Geltru","Sanlucar de Barrameda","Estepona","Torremolinos","Villa de Vallecas","La Linea de la Concepcion","Molina de Segura","Paterna","Santa Lucia","Viladecans","El Prat de Llobregat","Valdemoro","Grao de Murviedro","Castelldefels","Alcoy","Linares","Irun","Granollers","Motril","Santutxu","Arrecife","Benalmadena","Cerdanyola del Valles","la Nova Esquerra de l'Eixample","Avila","Barri de Sant Andreu","Segovia","Merida","Cuenca","Torrelavega","Elda","Collado-Villalba","Aranjuez","San Vicent del Raspeig","Mollet del Valles","San Bartolome de Tirajana","Huesca","Puertollano","Calvia","Sagrada Familia","Arganda","Vila-real","la Vila de Gracia","Utrera","Ibiza","el Raval","Portugalete","Sant Gervasi - Galvany","Santurtzi","Esplugues de Llobregat","Barri de les Corts","Gava","Sama","Antequera","Alzira","Denia","Mieres","Dreta de l'Eixample","Llefia","Mislata","Colmenar Viejo"));
  country_cities += ("South Korea" -> List("Seoul","Busan","Incheon","Daegu","Daejeon","Gwangju","Suwon","Goyang-si","Seongnam-si","Ulsan","Bucheon-si","Jeonju","Ansan-si","Cheongju-si","Anyang-si","Changwon","Pohang","Uijeongbu-si","Hwaseong-si","Masan","Jeju City","Cheonan","Kwangmyong","Kimhae","Chinju","Yeosu","Gumi","Iksan","Mokpo","Gunsan","Wonju","Suncheon","Sejong","Chuncheon","Icheon-si","Guri-si","Gangneung","Yangju","Osan","Seogwipo","Gyeongju","Gimcheon","Jeongeup","Hanam","Gyeongsan-si","Andong","Hwado","Tonghae","Asan","Wabu","Namyangju","Kwangyang","Hongseong","Sokcho","Eisen","Wanju","Yangp'yong","Ungsang","Sinhyeon","Gwangju","Mungyeong","Naeso","Muan","Hongch'on","Changnyeong","Seosan","Koch'ang","Gongju","Kyosai","Yangsan","Anseong","Hwawon","Pubal","Kosong","Taisen-ri","Chinch'on","Hwasun","Nangen","Okcheon","Eisen","Sangju","Gapyeong","Yeongam","Sinan","Miryang","T'aebaek","Kimje","Koesan","Nonsan","Munsan","Gijang","Naju","Santyoku","Yeonil","Hayang","Yesan","Imsil","Yeoju","Heunghae","Kunwi","Tangjin","Fuyo","Cheongsong gun","Jinan-gun","Kurye","Waegwan","Seonghwan","Goseong","Changsu","Ganghwa-gun","Neietsu","Kinzan","Yonmu","Yong-dong","Yanggu","Haenam","Hwacheon","Gaigeturi","Taesal-li","Puan","Jenzan","Yeonggwang","Beolgyo","Sintansin","Yongsan-dong","Songgangdong","Pyeongchang"));
  //{"Sweden", List()},{"Austria", List()},{"Australia", List()},{"Netherlands", List()},{"India", List()},{"Russia", List()},{"Norway", List()},{"Mexico", List()},{"Switzerland", List()},{"Indonesia", List()},{"Saudi Arabia", List()},{"Denmark", List()},{"Turkey", List()},{"Poland", List()},{"Ireland", List()},{"Belgium", List()},{"Singapore", List()},{"Finland", List()},{"Hong Kong", List()},{"Portugal", List()}

  def generateCustomer() : Customer = {
    var newCustomer = new Customer();
    newCustomer.customer_name_=(s"${randomString(first_names)} ${randomString(last_names)}");
    newCustomer.country_=(randomString(country_names));
    newCustomer.city_=(randomString(country_cities(newCustomer.country)));
    if (customer_ids.contains(newCustomer.customer_name)) {
      newCustomer.customer_id_=(customer_ids(newCustomer.customer_name));
    }
    else {
      newCustomer.customer_id_=(customer_ids.size);
      customer_ids += (newCustomer.customer_name -> newCustomer.customer_id);
    }
    return newCustomer;
  }
  def listCustomers() : Unit = { for((k,v) <- customer_ids) { println(s"[$v] $k"); } }


  // product_id = dictionary<product_name, int>
  var product_ids = collection.mutable.Map[String, Int]();
  
  // product_name & product_category
  
  val product_tool_category : String = "Tools";
  val product_tool_materials : List[String] = List("Iron","Steel","Titanium");
  val product_tool_materials_prices : Map[String, Float] = Map("Iron" -> 1f,"Steel" -> 2f,"Titanium" -> 3f);
  val product_tool_types : List[String] = List("Axe","Pick","Shovel","Hammer","Drill","Saw","Pliers","Screwdriver","Wrench");
  val product_tool_types_prices : Map[String, Float] = Map("Axe" -> 1.4f,"Pick" -> 1.6f,"Shovel" -> 1.2f,"Hammer" -> 0.8f,"Drill" -> 3f,"Saw" -> 2f,"Pliers" -> 1f,"Screwdriver" -> 0.9f,"Wrench" -> 1.1f);
  
  val product_clothing_category : String = "Clothing";
  val product_clothing_sizes : List[String] = List("XL","L","M","S","XS");
  val product_clothing_sizes_prices : Map[String, Float] = Map("XL" -> 1.1f,"L" -> 1.05f,"M" -> 1f,"S" -> 0.95f,"XS" -> 0.9f);
  val product_clothing_colors : List[String] = List("Red","Orange","Yellow","Green","Blue","Indigo","Violet","Black","White");
  val product_clothing_materials : List[String] = List("Cotton","Denim","Silk");
  val product_clothing_materials_prices : Map[String, Float] = Map("Cotton" -> 1f,"Denim" -> 2f,"Silk" -> 3f);
  val product_clothing_types : List[String] = List("Hat","Jacket","Pants","Shorts","Gloves","Shirt");
  val product_clothing_types_prices : Map[String, Float] = Map("Hat" -> 0.8f,"Jacket" -> 2f,"Pants" -> 1.4f,"Shorts" -> 1.1f,"Gloves" -> 0.5f,"Shirt" -> 1f);
  
  val product_electronic_category : String = "Electronics";
  val product_electronic_sizes : List[String] = List("Huge","Large","Medium","Small","Micro");
  val product_electronic_brands : List[String] = List("LG","Dell","HP","Android","Apple","Samsung","AMD","Intel","Microsoft","Google");
  val product_electronic_types : List[String] = List("Laptop Computer","Desktop Computer","Printer","Television","Smartphone","3D Printer","Microwave");
  val product_electronic_types_prices : Map[String, Float] = Map("Laptop Computer" -> 6f,"Desktop Computer" -> 7f,"Printer" -> 2f,"Television" -> 3.5f,"Smartphone" -> 4f,"3D Printer" -> 5f,"Microwave" -> 1.5f);

  def getProductId(newProduct : Product) : Unit = {
    if (product_ids.contains(newProduct.product_name)) {
      newProduct.product_id_=(product_ids(newProduct.product_name));
    }
    else {
      newProduct.product_id_=(product_ids.size);
      product_ids += (newProduct.product_name -> newProduct.product_id);
    }
  }
  
  def modifyPrice(product : Product, map : Map[String, Float]) : Unit = {
    for ((k,v) <- map) {
      if (product.product_name.contains(k)) {
        product.product_price_=(product.product_price * v);
        return;
      }
    }
  }

  def generateTool() : Product = {
    var newProduct = new Product();
    newProduct.product_category_=(product_tool_category);
    newProduct.product_name_=(s"${randomString(product_tool_materials)} ${randomString(product_tool_types)}");
    getProductId(newProduct);
    newProduct.product_price_=(30f);
    modifyPrice(newProduct, product_tool_materials_prices);
    modifyPrice(newProduct, product_tool_types_prices);
    return newProduct;
  }

  def generateClothing() : Product = {
    var newProduct = new Product();
    newProduct.product_category_=(product_clothing_category);
    newProduct.product_name_=(s"${randomString(product_clothing_sizes)} ${randomString(product_clothing_colors)} ${randomString(product_clothing_materials)} ${randomString(product_clothing_types)}");
    getProductId(newProduct);
    newProduct.product_price_=(25f);
    modifyPrice(newProduct, product_clothing_sizes_prices);
    modifyPrice(newProduct, product_clothing_materials_prices);
    modifyPrice(newProduct, product_clothing_types_prices);
    return newProduct;
  }

  def generateElectronic() : Product = {
    var newProduct = new Product();
    newProduct.product_category_=(product_electronic_category);
    newProduct.product_name_=(s"${randomString(product_electronic_sizes)} ${randomString(product_electronic_brands)} ${randomString(product_electronic_types)}");
    getProductId(newProduct);
    newProduct.product_price_=(60f);
    modifyPrice(newProduct, product_electronic_types_prices);
    return newProduct;
  }
  

  // GENERATE CUSTOMER TEST
  var genCustomer : Customer = null; 
  println();
  for (i <- 0 until 3) {
    genCustomer = generateCustomer();
    genCustomer.print();
  }
  println();
  listCustomers();
  println();

  // GENERATE PRODUCTS TEST
  generateElectronic().print();
  generateElectronic().print();
  generateClothing().print();
  generateClothing().print();
  generateTool().print();
  generateTool().print();

  // TREND EXAMPLE - higher steel prices in january (pseudocode)
  // if (date.month == january && product.product_name.contains("steel")) { product.product_price = product.product_price * 1.1; }

}
