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
    }

    @Test
    public void testAtributosConstantesAlRecibirDanoFisico() {
        int danoFisico = 80;

        defensor.receiveDamage(danoFisico);

        assertTrue(defensor.getHp() < hpInicial, "El HP debe disminuir tras recibir daño físico.");

        assertEquals(atkInicial, defensor.getAttack(), "El Ataque no debe cambiar tras un impacto.");
        assertEquals(defInicial, defensor.getDefense(), "La Defensa no debe cambiar tras un impacto.");
        assertEquals(spAtkInicial, defensor.getSpAttack(), "El Ataque Especial no debe cambiar tras un impacto.");
        assertEquals(spDefInicial, defensor.getSpDefense(), "La Defensa Especial no debe cambiar tras un impacto.");
        assertEquals(spdInicial, defensor.getSpeed(), "La Velocidad no debe cambiar tras un impacto.");
        assertEquals(tipoInicial, defensor.getType1(), "El Tipo principal no debe cambiar.");
    }

    @Test
    public void testAtributosConstantesAlRecibirDanoEspecial() {
        int danoEspecial = 100;

        defensor.receiveSpecialDamage(danoEspecial);

        assertTrue(defensor.getHp() < hpInicial, "El HP debe disminuir tras recibir daño especial.");

        assertEquals(atkInicial, defensor.getAttack(), "El Ataque no debe alterarse.");
        assertEquals(defInicial, defensor.getDefense(), "La Defensa no debe alterarse.");
        assertEquals(spAtkInicial, defensor.getSpAttack(), "El Ataque Especial no debe alterarse.");
        assertEquals(spDefInicial, defensor.getSpDefense(), "La Defensa Especial no debe alterarse.");
        assertEquals(spdInicial, defensor.getSpeed(), "La Velocidad no debe alterarse.");
        assertEquals(tipoInicial, defensor.getType1(), "El Tipo no debe verse afectado por ataques.");
    }
}