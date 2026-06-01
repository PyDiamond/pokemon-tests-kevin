package pokemon;

import moves.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Regla4 {

    class PokemonPrueba extends Pokemon {
        public PokemonPrueba(int id, String name, int hp, int attack, int defense, int speed) {
            super(id, name, MoveType.NORMAL, null, 300, hp, attack, defense, attack, defense, speed);
        }
    }

    private Pokemon atacante;
    private Pokemon defensor;

    @BeforeEach
    public void setUp() {
        atacante = new PokemonPrueba(1, "Charizard", 100, 80, 50, 100);
        defensor = new PokemonPrueba(2, "Bulbasaur", 50, 40, 20, 45);
    }

    @Test
    public void testVidaNoBajaDeCeroYQuedaDerrotado() {
        int danoMasivo = 150; 
        defensor.receiveDamage(danoMasivo);

        assertEquals(0, defensor.getHp(), "El HP nunca debe ser negativo, debe quedar en 0.");
        assertTrue(defensor.isFainted(), "El Pokémon debe ser considerado derrotado (Fainted).");
        assertFalse(defensor.isAlive(), "El Pokémon ya no debería estar vivo.");
    }

    @Test
    public void testVidaLlegaExactamenteACero() {
        int danoExacto = 50;
        defensor.receiveDamage(danoExacto);

        assertEquals(0, defensor.getHp(), "El HP debe ser exactamente 0 al recibir daño igual a la vida.");
        assertTrue(defensor.isFainted(), "El Pokémon debe estar derrotado.");
    }

    @Test
    public void testPokemonDerrotadoNoVuelveAAtacar() {
        atacante.receiveDamage(500); 
        assertTrue(atacante.isFainted(), "El atacante debería estar muerto.");
        
        int hpDefensorAntes = defensor.getHp();
        atacante.useMove(1, defensor); 

        assertEquals(hpDefensorAntes, defensor.getHp(), "Un Pokémon derrotado no debería causar daño al intentar usar un movimiento.");
    }

    @Test
    public void testSistemaIdentificaCorrectamenteAlGanadorYPerdedor() {
        atacante.useMove(1, defensor);
        defensor.receiveDamage(100);

        assertTrue(defensor.isFainted(), "Bulbasaur debería estar derrotado.");
        assertFalse(atacante.isFainted(), "Charizard sigue en pie.");
        
        assertTrue(atacante.isAlive(), "El sistema debe reconocer a Charizard como vivo (Ganador de la ronda).");
        assertFalse(defensor.isAlive(), "El sistema debe reconocer a Bulbasaur como no vivo (Perdedor de la ronda).");
    }
}
