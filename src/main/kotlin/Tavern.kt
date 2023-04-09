import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli","Mordoc","Sophie")
val lastName = listOf("Ironfoot","Fernsworth","Baggis")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")
val patronGold= mutableMapOf<String,Double>()
val readOnlyPatronList = patronList.toList()

fun main() {

    var WelcomePrase="*** Welcome to $TAVERN_NAME ***"
    println(WelcomePrase)

    var x = menuList.forEach{ data ->
        var (one,two,three) = data.split(',')
        var bird ="           ~[ $one ]~"
        var superList1 = mutableListOf<String>()
        superList1.add(0,"$one")
println( bird)

val restic = "desert dessert,pickled camel hump,7.33".indexOf('.')
        var qualiri = " ${two.capitalize()}...........$three"
        println(qualiri)
    }

    if(patronList.contains("Eli")){
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here")
    }
    if (patronList.containsAll(listOf("Sophie","Mordoc"))){
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    }else{
        println("The tavern master says: Nay, they departed hours ago.")
    }
    (0..9).forEach{
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }
    uniquePatrons.forEach{
        patronGold[it] = 6.0
    }
    var orderCount = 0
    while (orderCount<=9) {
        placeOrder(
            uniquePatrons.random(),
            menuList.random()
        )
        orderCount++
    }
    displayPatronBalances()
}
private fun displayPatronBalances(){
    patronGold.forEach{ patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}
fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}
private fun toDragonSpeak(phrase:String)=
    phrase.replace(Regex("[aAeEiIoOuU]")){
        when (it.value) {
            "A","a" -> "4"
            "E","e" -> "3"
            "I","i"->"1"
            "O","o" ->"0"
            "U","u"->"|_|"
            else->it.value
        }
    }
private fun placeOrder(patronName:String,menuData:String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price"
    println(message)
    performPurchase(price.toDouble(), patronName)
    val phrase = if (name == "Dragon's Breath") {
    "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
    "$patronName says: Thanks for the $name."
    }
    println(phrase)
}