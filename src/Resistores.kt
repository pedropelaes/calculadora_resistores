/*
    Criar uma calculadora de resistores.
    O usuário vai informar quatro cores e
    o programa dará a saída de qual o valor do resistor (aproveite depois e implemente
    também para 5 faixas/bandas)

    Dica: USAR a calculadora de qualquer site bom como aprendizado (digikey.com)
    TRABALHAR com resistores de 4 faixas (4 cores)
    VERMELHO, PRETO, VERMELHO, DOURADO

    ** Neste código de correção o professor está trabalhando com
    resistores de 4 e 5 faixas para você notar a diferença.
    ATENÇÃO: ESTE CÓDIGO FOI SIMPLIFICADO para o nível da turma neste momento.

    Exercício: Aprimorar o código do professor para funcionar melhor.
    Existem coisas propositalmente que não estão corretas.

    Além disso, acrescentar conceitos de orientação a objetos.
 */

/***
 * Essa função devolve um mapa estático com todos os
 * valores imutáveis para cálculo de resistência.
 * Como ao longo do programa não temos intenção nenhuma
 * de alterar esses valores de referência, não existe porque
 * devolver um mapa mutável.
 */
fun getResistorColorsMap(): Map<String, List<Double?>>{
    var referenceMap: Map<String, List<Double?>> = mapOf(
        Pair("PRETO", listOf(0.0, 0.0, 0.0, 1.0, null)),
        Pair("MARROM", listOf(1.0, 1.0, 1.0, 10.0, 2.0)),
        Pair("VERMELHO", listOf(1.0, 1.0, 1.0, 10.0, 2.0)),
        Pair("LARANJA", listOf(3.0, 3.0, 3.0, 1_000.0, null)),
        Pair("AMARELO", listOf(4.0, 4.0, 4.0, 10_000.0, null)),
        Pair("VERDE", listOf(5.0, 5.0, 5.0, 100_000.0, 0.5)),
        Pair("AZUL", listOf(6.0, 6.0, 6.0, 1_000_000.0, 0.25)),
        Pair("VIOLETA", listOf(7.0, 7.0, 7.0, 10_000_000.0, 0.10)),
        Pair("CINZA", listOf(8.0, 8.0, 8.0, 100_000_000.0, 0.05)),
        Pair("BRANCO", listOf(9.0, 9.0, 9.0, 1_000_000_000.0, null)),
        Pair("DOURADO", listOf(null, null, null, 0.1, 5.0)),
        Pair("PRATA", listOf(null, null, null, 0.01, 10.0))
    )
    return referenceMap
}


fun welcomeAndInstructions(){
    println(">>>>> CALCULADORA DE RESISTORES <<<<<")
    println("Informe as cores do resistor\nseparadas por vírgula,\nExemplo: VERMELHO, PRETO, VERMELHO, DOURADO")
    println("Cores válidas: PRETO, MARROM, VERMELHO, LARANJA, AMARELO, VERDE, AZUL, VIOLETA, CINZA, BRANCO, DOURADO, PRATA")
}

fun getColorsOfResistor(): List<String>{
    println(">>> ")
    var coresStr: String = readLine()!!
    coresStr = coresStr.uppercase().replace(" ","")
    return coresStr.split(",")
}

fun validateNameOfColors(colorsList: List<String>, resColorsInfo: Map<String, List<Double?>>): Boolean{
    // Para cada cor verificamos se ela EXISTE no mapa.
    // Se existir, verificamos se na posição que ela está da lista existe algum valor que não seja null na lista de
    // referência.
    val size = colorsList.size
    var allColorsAreValid = true
    run loop@{
        colorsList.forEachIndexed { index, color ->
            if (resColorsInfo.containsKey(color) && size == 4) {
                // existe a cor, vamos verificar se na posição que ela se encontra tem algum valor
                if (resColorsInfo[color]!![index + 1] == null) {
                    println("A cor $color não pode aparecer na banda ${index + 1}")
                    allColorsAreValid = false;
                    return@loop
                }
            }
            else if(resColorsInfo.containsKey(color) && size == 5){
                // existe a cor, vamos verificar se na posição que ela se encontra tem algum valor
                if (resColorsInfo[color]!![index] == null) {
                    println("A cor $color não pode aparecer na banda ${index + 1}")
                    allColorsAreValid = false;
                    return@loop
                }
            }
            else {
                println("A cor $color não existe.")
                allColorsAreValid = false
                return@loop
            }
        }
    }

   return allColorsAreValid
}

/**
 * Concatena dois numeros inteiros devolvendo
 * um terceiro, sem usar strings e conversão.
 * Estude...
 */
fun concat(a: Int, b: Int): Int {
    var a = a
    if (b == 0) a *= 10
    else {
        var auxB = b
        while (auxB > 0) {
            auxB /= 10
            a *= 10
        }
    }
    return a + b
}

fun concat3(a: Int, b: Int, c: Int): Int = ((a*100) + (b*10) + c)


/***
 * Calcula e devolve uma instância de um objeto chamado PAIR (par) de
 * dois valores, no caso Double.
 */
fun calculate(colors: List<String>, resistorsMap: Map<String, List<Double?>> ): Pair<Double?, Double?> {
    var resistance: Double? = null
    var tolerance: Double? = null
    // calcularemos conforme a quantidade de bandas.
    if(colors.isNotEmpty()){
        // 4 ou 5 bandas
        if(colors.size == 4){
            val first = resistorsMap[colors[0]]?.get(0)
            val sec = resistorsMap[colors[1]]?.get(1)
            val concat1e2 = concat(first!!.toInt(), sec!!.toInt())
            val mul = resistorsMap[colors[2]]?.get(3)
            resistance = concat1e2 * mul!!
            tolerance = resistorsMap[colors[3]]?.get(4)
        }else if (colors.size == 5){
            // TODO: Estudante, faça para 5 bandas... e depois melhore o algoritmo por completo.
            val first = resistorsMap[colors[0]]?.get(0)
            val sec = resistorsMap[colors[1]]?.get(1)
            val third = resistorsMap[colors[2]]?.get(2)
            val concat123 = concat3(first!!.toInt(), sec!!.toInt(), third!!.toInt())
            val mul = resistorsMap[colors[3]]?.get(3)
            resistance = concat123 * mul!!
            tolerance = resistorsMap[colors[4]]?.get(4)
        }
    }
    // retorna o cálculo em Ohm
    return Pair(resistance, tolerance)
}

fun main() {
    val resColorsInfo = getResistorColorsMap()
    welcomeAndInstructions()
    val colorsList = getColorsOfResistor()
    val bands = colorsList.size
    if (bands == 4 || bands == 5){
        if(validateNameOfColors(colorsList, resColorsInfo)){
            // cores válidas.
            val res = calculate(colorsList, resColorsInfo)
            println("Resistencia: ${res.first} e tolerancia: ${res.second}")
        }
    }else{
        println("Atenção: Você deve informar 4 ou 5 cores separadas por virgula.\n Recomece o programa.")
    }
}