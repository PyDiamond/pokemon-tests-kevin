package test;

import moves.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

public class Regla5 {

    private Pokemon defensor;
    private Pokemon atacante;
    
    private int hpInicial;
    private int atkInicial;
    private int defInicial;
    private int spAtkInicial;
    private int spDefInicial;
    private int spdInicial;
    private MoveType tipoInicial;

    @BeforeEach
    public void setUp() {
        defensor = PokemonFactory.createPokemon(133); 
        atacante = PokemonFactory.createPokemon(4);   

        hpInicial = defensor.getHp();
        atkInicial = defensor.getAttack();
        defInicial = defensor.getDefense();
        spAtkInicial = defensor.getSpAttack();
        spDefInicial = defensor.getSpDefense();
        spdInicial = defensor.getSpeed();
        tipoInicial = defensor.getType1();
    }

    @Test
    public void testAtributosConstantesAlRecibirDanoFisico() {
        defensor.receiveDamage(100); 

        assertTrue(defensor.getHp() < hpInicial, "El HP debe disminuir tras recibir daño físico.");
        verificarAtributosConstantes(defensor);
    }

    @Test
    public void testAtributosConstantesAlRecibirDanoEspecial() {
        defensor.receiveSpecialDamage(100); 

        assertTrue(defensor.getHp() < hpInicial, "El HP debe disminuir tras recibir daño especial.");
        verificarAtributosConstantes(defensor);
    }

    @Test
    public void testAtributosDelAtacanteYDefensorSeMantienenAlAtacar() {
        int hpAtacanteAntes = atacante.getHp();
        int atkAtacanteAntes = atacante.getAttack();
        int hpDefensorAntes = defensor.getHp();
        
        atacante.useMove(1, defensor);
        
        assertEquals(hpAtacanteAntes, atacante.getHp(), "El HP del atacante no debe cambiar por atacar.");
        assertEquals(atkAtacanteAntes, atacante.getAttack(), "El Ataque del atacante no debe cambiar.");
        
        assertTrue(defensor.getHp() < hpDefensorAntes, "El HP del defensor debió disminuir.");
        verificarAtributosConstantes(defensor);
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