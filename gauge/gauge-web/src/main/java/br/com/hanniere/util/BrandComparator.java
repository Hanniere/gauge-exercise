package br.com.hanniere.util;

import java.util.Comparator;

import br.com.hanniere.entity.Brand;

public class BrandComparator implements Comparator<Brand> {

	@Override
	public int compare(Brand arg0, Brand arg1) {
		// TODO Auto-generated method stub
        Comparable id1 = (Comparable)(arg0.getNumeroIteracoes());
        Comparable id2 = (Comparable)(arg1.getNumeroIteracoes());
        return id2.compareTo(id1);
	}

}
