package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalcolaRegine {
	
	private int lato;
	private List<Integer> posti ;
	private int conta = 0 ;
	private List<List<Integer>> tutte = null ;

	public CalcolaRegine(int lato) {
		this.lato = lato ;
		posti = new ArrayList<>(lato) ;
		for(int i=0; i<lato; i++) {
			posti.add(i,-1) ;
		}
		
		tutte = new LinkedList<List<Integer>>() ;
	}
	
	public List<List<Integer>> trova() {
		
		tutte.clear();
		trova_ric(0) ;
		return tutte ;
		
	}
	
	private void trova_ric(int riga) {
		
		if(riga==lato) {
			// Trovata!
			conta++;
			// System.out.println("Trovata "+conta) ;
			// dump(posti) ;
			tutte.add( new ArrayList<Integer>(posti) ) ;
			return ;
		}
		
		for(int colonna=0; colonna<lato; colonna++) {
			
			// Posso mettere una regina nella posizione (riga, colonna)?
			if( verifica(riga,colonna, posti) ) {
				
				posti.set(riga, colonna) ;
				
				trova_ric(riga+1) ;
				
				posti.set(riga, -1) ; 
			}
		}
	}
	
	private boolean verifica(int riga, int colonna, List<Integer> posti) {
		
		// esiste qualche regina, nelle righe precedenti, sulla stessa colonna?
		for(int i=0; i<riga; i++)
			if( posti.get(i)==colonna )
				return false ;
		
		// esiste qualche regina, nelle righe precedenti, sulla stessa diagonale dx o sx?
		for(int i=0; i<riga; i++)
			if( riga-i == Math.abs(colonna - posti.get(i)) )
				return false ;

		return true ;
	}

	private void dump(List<Integer> p) {
		for(int i=0; i<lato; i++) {
			for(int j=0; j<lato; j++) {
				if( p.get(i)==j)
					System.out.print("Q ");
				else
					System.out.print(". ");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args) {
		CalcolaRegine c = new CalcolaRegine(8) ;
		List<List<Integer>> trovate = c.trova();
		
		System.out.println("Soluzioni trovate: "+trovate.size());
		c.dump(trovate.get(0)) ;
		
	}

}
