/*
aTodo tipo que é null-safety não possui o caractere ? no final.
Ex:
Boolean
Double
Int
String

aTodo tipo que ACEITA NULL possui um caractere ? no final.
Ex:
String?
Boolean?

Entendendo:
Boolean (possiveis valores = true or false) = Null Safety
*/
fun showInstructions(){
    println("Calculadora de Resistores")
    println("Informe as 4 cores separadas por vírgula:")
}
fun readColors(): String? {
    return readLine()
}

fun cleanColors(colors: String): String{
    return colors.replace(" ", "")
}

fun stringBasicCleaner(colors: String):String{
    val limpa = colors.replace(" ", "")
    val caixaAlta = limpa.uppercase()
    return caixaAlta
}

fun prepareColorsList(colors: String): List<String>{
    return colors.split(",")
}
fun checkColors(typedColors: List<String>): Boolean{
    val colors = listOf<String>("preto", "marrom", "vermelho", "laranja", "amarelo", "verde", "azul", "violeta", "cinza", "branco")
    val multipliers = listOf<String>("prata", "ouro","preto", "marrom", "vermelho", "laranja", "amarelo", "verde", "azul", "violeta")
    val tolerance = listOf<String>("prata", "ouro", "marrom", "vermelho", "verde", "azul", "violeta")
    return typedColors[0] in colors && typedColors[1] in colors && typedColors[2] in multipliers && typedColors[3] in tolerance
}
fun main() {
    showInstructions()
    var colors = readColors()
    if(!colors.isNullOrEmpty()){
        // limpar a string
        colors = stringBasicCleaner(colors)
        val colorsList = prepareColorsList(colors)
        //todo: verificar quantas cores, se a cor está em uma posição permitida, se a cor existe
        if(colorsList.size == 4 && checkColors(colorsList)){
            
        }else{
            println("Cores invalidas.")
            main()
        }
        println(colorsList)
    }else{
        println("Por favor, tente novamente")
    }
}