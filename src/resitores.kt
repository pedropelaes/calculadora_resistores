data class Cor(val id:Int, val nome:String, val M: Any?, val T: Any?)
fun main(){
    val Cores = listOf(
        Cor(0,"Preto", 1, null),
        Cor(1,"Marrom", 10, 1),
        Cor(2,"Vermelho", 100, 2),
        Cor(3,"Laranja", 1000, null),
        Cor(4,"Amarelo", 10_000, null),
        Cor(5,"Verde", 100_000, 0.5),
        Cor(6,"Azul", 1_000_000, 0.25),
        Cor(7,"Violeta", 10_000_000, 0.1),
        Cor(8,"Cinza", null, null),
        Cor(9,"Branco", null, null),
        Cor(22,"Prata", 0.01, 10),
        Cor(33,"Ouro", 0.1, 5)
    )
    val coresEscolhidas = mutableListOf<Int>()

    while(true){
        println("|Calculadora de Resistores|\n Digite quantas faixas(4 ou 5):")
        when(readlnOrNull()?.toIntOrNull()){
            4 -> {
                // 4 faixas
                printCores(Cores)
                userEscolheCor(Cores, coresEscolhidas, 4)
                break
            }
            5 -> {
                // 5 faixas
                printCores(Cores)
                userEscolheCor(Cores, coresEscolhidas, 5)
                break
            }
            else -> {
                println("Programa Encerrado")
                break
            }
        }
    }
}
fun userEscolheCor(Cores: List<Cor>, coresEscolhidas: MutableList<Int>, nFaixas: Int): MutableList<Int>{
    coresEscolhidas.clear()
    coresEscolhidas.addAll(escolherCor(nFaixas))
    println("Escolha a cor da faixa multiplicadora")
    for(cor in Cores){//imprimindo as cores dos multiplicadores
        if(cor.M != null)println("${cor.id} - ${cor.nome}")
    }
    escolherMT()?.let { coresEscolhidas.add(it) }
    println("Escolha a cor da faixa de tolerância")
    for(cor in Cores){//imprimindo as cores da tolerância
        if(cor.T != null)println("${cor.id} - ${cor.nome}")
    }
    escolherMT()?.let { coresEscolhidas.add(it) }
    println(calculo(coresEscolhidas, Cores))
    return coresEscolhidas
}
fun escolherCor(nFaixas: Int): MutableList<Int> {
    val coresFaixas = mutableListOf<Int>()
    for(i in 1..(nFaixas - 2)){
        println("Escolher cor da faixa $i")
        val cor = readlnOrNull()?.toInt()
        if(cor != null) {
            coresFaixas.add(cor)
        }
    }
    return coresFaixas
}
fun escolherMT(): Int? = readlnOrNull()?.toInt()

fun calculo(escolha: MutableList<Int>, cores: List<Cor>):String{
    if(escolha.size == 4){
        val M = buscarMultiplicador(escolha[2], cores)
        val r = (escolha[0] * 10 + escolha[1]) * M.toFloat()
        return "$r Ohms ${buscarTolerancia(escolha[3], cores)}%"
    }
    else{
        val M = buscarMultiplicador(escolha[3], cores)
        val r = ((escolha[0] * 100) + (escolha[1] * 10) + escolha[2]) * M.toFloat()
        return "$r Ohms ${buscarTolerancia(escolha[4], cores)}%"
    }
}
fun buscarMultiplicador(id: Int, cores: List<Cor>): String{
    val corMul = cores.find{it.id == id}
    return corMul?.M.toString()
}
fun buscarTolerancia(id: Int, cores: List<Cor>): Any? {
    val corTol = cores.find{it.id == id}
    return corTol?.T
}
fun printCores(cores: List<Cor>){
    for(i in 0..9){ //imprimindo as cores
        println("$i - ${cores[i].nome}")
    }
}