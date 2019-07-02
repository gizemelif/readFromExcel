package Resources;

import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExportResource {
    private <T> Set<T> findDuplicates(Collection<T> list){
        Set<T> duplicates = new HashSet<T>();
        Set<T> uniques = new HashSet<T>();

        for(T t : list){
            if(!uniques.add(t)){
                duplicates.add(t);
            }
        }
        return duplicates;
    }

}
