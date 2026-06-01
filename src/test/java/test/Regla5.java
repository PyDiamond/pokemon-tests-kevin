package pokemon;

import moves.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Regla5 {

    class PokemonPrueba extends Pokemon {
        public PokemonPrueba(int id, String name, MoveType type1, int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
            super(id, name, type1, null, 500, hp, attack, defense, spAttack, spDefense, speed);
        }
    }

    private Pokemon defensor;
    private Pokemon atacante;
    
    private final int hpInicial = 100;
    private final int atkInicial = 55;
    private final int defInicial = 50;
    private final int spAtkInicial = 45;
    private final int spDefInicial = 65;
    private final int spdInicial = 55;
    private final MoveType tipoInicial = MoveType.NORMAL;

    @BeforeEach
    public void setUp() {
        defensor = new PokemonPrueba(133, "Eevee", tipoInicial, hpInicial, atkInicial, defInicial, spAtkInicial, spDefInicial, spdInicial);
        atacante = new PokemonPrueba(1, "Rattata", MoveType.NORMAL, 100, 60, 40, 30, 40, 70);
    }

    @Test
    public void testAtributosConstantesAlRecibirDanoFisico() {
        int danoFisico = 30;
        defensor.receiveDamage(danoFisico);

        assertTrue(defensor.getHp() < hpInicial, "El HP debe disminuir tras recibir daño físico.");
        verificarAtributosConstantes(defensor);
    }

    @Test
    public void testAtributosConstantesAlRecibirDanoEspecial() {
        int danoEspecial = 40;
        defensor.receiveSpecialDamage(danoEspecial);

        assertTrue(defensor.getHp() < hpInicial, "El HP debe disminuir tras recibir daño especial.");
        verificarAtributosConstantes(defensor);
    }

    @Test
    public void testAtributosDelAtacanteSeMantienenAlAtacar() {
        int hpAtacanteAntes = atacante.getHp();
        int atkAtacanteAntes = atacante.getAttack();
        
        atacante.useMove(0, defensor);
        
        assertEquals(hpAtacanteAntes, atacante.getHp(), "El HP del atacante no debe cambiar simplemente por atacar.");
        assertEquals(atkAtacanteAntes, atacante.getAttack(), "El Ataque del atacante no debe agotarse ni cambiar tras atacar.");
    }

    private void verificarAtributosConstantes(Pokemon pokemon) {
        assertEquals(atkInicial, pokemon.getAttack(), "El Ataque no debe cambiar.");
        assertEquals(defInicial, pokemon.getDefense(), "La Defensa no debe cambiar.");
        assertEquals(spAtkInicial, pokemon.getSpAttack(), "El Ataque Especial no debe cambiar.");
        assertEquals(spDefInicial, pokemon.getSpDefense(), "La Defensa Especial no debe cambiar.");
        assertEquals(spdInicial, pokemon.getSpeed(), "La Velocidad no debe cambiar.");
        assertEquals(tipoInicial, pokemon.getType1(), "El Tipo principal no debe cambiar.");
    }
}
