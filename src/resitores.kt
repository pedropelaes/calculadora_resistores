data class Cor(val nome:String, val M: Double?, val T: Double?)
fun main(){
    val Cores: Map<Int, Cor> = listOf(
        Cor("Preto", 1.0, null),
        Cor("Marrom", 10.0, 1.0),
        Cor("Vermelho", 100.0, 2.0),
        Cor("Laranja", 1000.0, null),
        Cor("Amarelo", 10_000.0, null),
        Cor("Verde", 100_000.0, 0.5),
        Cor("Azul", 1_000_000.0, 0.25),
        Cor("Violeta", 10_000_000.0, 0.1),
        Cor("Cinza", null, null),
        Cor("Branco", null, null),
        Cor("Prata", 0.01, 10.0),
        Cor("Ouro", 0.1, 5.0)
    ).withIndex().associate { it.index to it.value }

    val coresEscolhidas = mutableListOf<Int>()

    while(true){
        println("|Calculadora de Resistores|\n|Digite quantas faixas(4 ou 5)\n|Para sair digite qualquer tecla:")
        when(readlnOrNull()?.toIntOrNull()){
            4 -> {
                // 4 faixas
                printCores(Cores)
                userEscolheCor(Cores, coresEscolhidas, 4)

            }
            5 -> {
                // 5 faixas
                printCores(Cores)
                userEscolheCor(Cores, coresEscolhidas, 5)

            }
            else -> {
                println("Programa Encerrado")
                break
            }
        }
    }
}
fun userEscolheCor(Cores: Map<Int, Cor>, coresEscolhidas: MutableList<Int>, nFaixas: Int): MutableList<Int>{
    coresEscolhidas.clear()
    coresEscolhidas.addAll(escolherCor(nFaixas))
    val coresT = mutableListOf<Int>()
    val coresM = mutableListOf<Int>()
    println("Escolha a cor da faixa multiplicadora")
    for(cor in Cores){//imprimindo as cores dos multiplicadores
        if(cor.value.M != null) {
            println("${cor.key} - ${cor.value.nome}")
            coresM.add(cor.key)
        }
    }
    escolherMT(coresM).let { coresEscolhidas.add(it) }
    println("Escolha a cor da faixa de tolerância")
    for(cor in Cores){//imprimindo as cores da tolerância
        if(cor.value.T != null) {
            println("${cor.key} - ${cor.value.nome}")
            coresT.add(cor.key)
        }
    }
    escolherMT(coresT).let { coresEscolhidas.add(it) }
    println(calculo(coresEscolhidas, Cores))
    return coresEscolhidas
}
fun escolherCor(nFaixas: Int): MutableList<Int> {
    val coresFaixas = mutableListOf<Int>()
    for(i in 1..(nFaixas - 2)){
        println("Escolher cor da faixa $i")
        var cor = readlnOrNull()?.toIntOrNull()
        while(cor == null || cor !in 0..9){
            println("Por favor, digite um número entre 0 e 9:")
            cor = readlnOrNull()?.toIntOrNull()
        }
        coresFaixas.add(cor)
    }
    return coresFaixas
}
fun escolherMT(cores: MutableList<Int>): Int {
    var cor = readlnOrNull()?.toIntOrNull()
    while(cor == null || cor !in cores){
        println("Por favor, digite uma cor válida:")
        cor = readlnOrNull()?.toIntOrNull()
    }
    return cor
}

fun calculo(escolha: MutableList<Int>, cores: Map<Int, Cor>):String{
    if(escolha.size == 4){
        val M = buscarMultiplicador(escolha[2], cores)
        val r = (escolha[0] * 10 + escolha[1]) * M as Double
        return "${formatarResultado(r)} ${buscarTolerancia(escolha[3], cores)}%"
    }
    else{
        val M = buscarMultiplicador(escolha[3], cores)
        val r = ((escolha[0] * 100) + (escolha[1] * 10) + escolha[2]) * M as Double
        return "${formatarResultado(r)} ${buscarTolerancia(escolha[4], cores)}%"
    }
}
fun buscarMultiplicador(id: Int, cores: Map<Int, Cor>): Double? {
    val corMul = cores[id]?.M
    return corMul
}
fun buscarTolerancia(id: Int, cores: Map<Int, Cor>): Double? {
    val corTol = cores[id]?.T
    return corTol
}
fun printCores(cores: Map<Int, Cor>){
    for(i in 0..9){ //imprimindo as cores
        println("$i - ${cores[i]?.nome}")
    }
}
fun formatarResultado(r : Double):String{
    return when {
        r >= 1_000_000 -> String.format("%dMOhms", (r / 1_000_000).toInt())
        r >= 1_000 -> String.format("%dKOhms", (r / 1_000).toInt())
        else -> String.format("%dOhms", r.toInt())
    }
}