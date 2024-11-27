class Pokemon (val idPokemon: Int, val namePokemon: String, val type1: String, val type2: String,
               val hp: Int = 0, val ad: Int = 0, val ap: Int = 0, val armor: Int = 0,
               val mr: Int = 0, val haste: Int = 0, val mana: Int = 0, val icon: String, val front: String, val back: String, val tier: Int, val evo: Boolean) {
    var psAct = hp
    var manaAct = mana

}
