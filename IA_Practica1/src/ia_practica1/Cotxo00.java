/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

/**
 *
 * @author
 */

// Exemple de Cotxo molt bàsic


public class Cotxo00 extends Agent {

    static final boolean DEBUG = false;

    static final int ESQUERRA = 0;
    static final int CENTRAL = 1;
    static final int DRETA = 2;
    static final int COTXE = 1;
    
    int VELOCITATTOPE = 5;
    int VELOCITATFRE = 4;

    Estat estat;
    int espera = 0;

    double desquerra, ddreta, dcentral;


    public Cotxo00(Agents pare) {
        super(pare, "CAR RT 0.1", "imatges/CocheAzul.png");
    }

    @Override
    public void inicia() {
        setAngleVisors(40);
        setDistanciaVisors(350);
        setVelocitatAngular(9);
    }

    @Override
    public void avaluaComportament() {

        estat = estatCombat();  // Recuperam la informació actualitzada de l'entorn

        // Si volem repetir una determinada acció durant varies interaccions
        // ho hem de gestionar amb una variable (per exemple "espera") que faci
        // l'acció que volem durant el temps que necessitem
        
        if (espera > 0) {  // no facis res, continua amb el que estaves fent
            espera--;
            return;
        } else {
                  
            if (estat.enCollisio && estat.distanciaVisors[CENTRAL] < 15) // evita fer-ho marxa enrera
            {
                noGiris();

                if (estat.distanciaVisors[CENTRAL] > 20) {
                    endavant(4);
                    return;
                }

                enrere(4);
                espera = 30;
                return;
            }

            ddreta = estat.distanciaVisors[DRETA];
            desquerra = estat.distanciaVisors[ESQUERRA];
            dcentral = estat.distanciaVisors[CENTRAL];

            if (dcentral > 170) {
                endavant(VELOCITATTOPE);
            }
            
            if (estat.objecteVisor[CENTRAL] == COTXE)
            {
                dispara();
            }
            //---------VELOCIDADES-----------------
            if ((desquerra > 40) && (ddreta > 40) && dcentral > 180 && estat.velocitat < 110){
                endavant(1);
                noGiris();
                return;
            }
            if ((desquerra > 40) && (ddreta > 40) && dcentral > 180 && estat.velocitat < 200){
                endavant(2);
                noGiris();
                return;
            }
            if ((desquerra > 40) && (ddreta > 40) && dcentral > 180 && estat.velocitat < 250){
                endavant(3);
                noGiris();
                return;
            }
            if ((desquerra > 40) && (ddreta > 40) && dcentral > 180 && estat.velocitat < 300){
                endavant(4);
                noGiris();
                return;
            }
            // Per si vull anar el més recte possible: no sempre és la manera més ràpida
            if ((desquerra > 40) && (ddreta > 40) && dcentral > 180) {
                endavant(VELOCITATTOPE);
                noGiris();
                return;
            }

            if (ddreta > desquerra) {
                dreta();
            } else {
                esquerra();
            } 
            endavant(VELOCITATFRE);
        }
    }
}
