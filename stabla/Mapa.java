import java.util.*;
public class Mapa<K extends Comparable<K>, V>{
  public Cvor koren = null;
  public int velicina = 0;


  public class Cvor{
    K kljuc;
    V vrednost;
    Cvor levi, desni;

    Cvor(K kljuc, V vrednost){
      this.kljuc = kljuc;
      this.vrednost = vrednost;
    }

    public String toString(){
      return (String)kljuc;
    }
  }

  class komparator implements Comparator<Cvor>{
    public int compare(Cvor a, Cvor b)
    {
        return a.kljuc.compareTo(b.kljuc);
    }
  }

  public void add(K kljuc, V vrednost){
    if(koren == null){
      koren = new Cvor(kljuc, vrednost);
    }
    addCvor(koren, kljuc, vrednost);
    velicina++;
  }

  public void addCvor(Cvor c, K kljuc, V vrednost){
  //  System.out.println(c.kljuc);
    if(c.kljuc == kljuc){
      c.vrednost = vrednost;
      return;
    }
    if(c.kljuc.compareTo(kljuc) > 0){
      if(c.desni == null){
        c.desni = new Cvor(kljuc, vrednost);
        return;
      }
      addCvor(c.desni, kljuc, vrednost);
    }
    else{
      if(c.levi == null){
        c.levi = new Cvor(kljuc, vrednost);
        return;
      }
      addCvor(c.levi, kljuc, vrednost);
      return;
    }

  }

  public void maxDubina(){
    System.out.println("Maximalna dubina je "+maxDubina(koren));
  }

  public int maxDubina(Cvor c){
    if(c == null)
      return 0;
  //  System.out.println(c.kljuc);
    return Math.max(maxDubina(c.levi)+1, maxDubina(c.desni)+1);
  }

  public void minDubina(){
    System.out.println("Minimalna dubina je "+minDubina(koren));
  }

  public int minDubina(Cvor c){
    if(c == null)
      return 0;
  //  System.out.println(c.kljuc);
    return Math.min(minDubina(c.levi)+1, minDubina(c.desni)+1);
  }

  public void minVrednost(){
    System.out.println("Minimalna vrednost je "+minVrednost(koren));
  }

  public K minVrednost(Cvor c){
    if(c == null)
      return null;
    if(c.desni == null)
      return c.kljuc;
    return minVrednost(c.desni);
  }

  public void maxVrednost(){
    System.out.println("Maximalna vrednost je "+maxVrednost(koren));
  }

  public K maxVrednost(Cvor c){
    if(c == null)
      return null;
    if(c.levi == null)
      return c.kljuc;
    return maxVrednost(c.levi);
  }

  public void rebalansiraj(){
    ArrayList<Cvor> niz = vratiNiz();
    System.out.println(niz);
    Collections.sort(niz, new komparator());
    System.out.println(niz);
    koren = null;
    rebalansiraj(niz, 0, niz.size());
  }
  private void rebalansiraj(List<Cvor> niz, int i, int j){
    int polovina = (i+j)/2;
  //  System.out.println(niz);
    //System.out.println(i+"  "+j);
    add(niz.get(polovina).kljuc, niz.get(polovina).vrednost);
    if(polovina > i){
      rebalansiraj(niz, i, polovina);
    }
    if(polovina < j-1){
      rebalansiraj(niz, polovina, j);
    }

  }

  public ArrayList<Cvor> vratiNiz(){
    ArrayList<Cvor> niz = new ArrayList<Cvor>();
    //int brojac = 0;
    Queue<Cvor> red = new LinkedList<Cvor>();
    red.add(koren);
    while(red.size() != 0){
      Cvor trenutni = red.poll();
      niz.add(trenutni);
      if(trenutni.levi != null)
        red.add(trenutni.levi);
      if(trenutni.desni != null)
        red.add(trenutni.desni);
    }
    return niz;
  }

  public V vratiVrednost(K kljuc){
    Cvor cvorTmp = pronadjiCvor(kljuc);
    if(cvorTmp != null){
      System.out.println("Za kljuc "+kljuc+" sam pronasao "+cvorTmp.vrednost);
    }
    else
      System.out.println("Za kljuc "+kljuc+" nisam pronasao nista");
    return null;
  }

  public Cvor pronadjiCvor(K kljuc){
    return pronadjiCvor(koren, kljuc); 
  }

  private Cvor pronadjiCvor(Cvor c, K kljuc){
    if(c == null){
      return c;
    }
    if(kljuc.compareTo(c.kljuc)==0){
      return c;
    }
    if(kljuc.compareTo(c.kljuc)>0){
      return pronadjiCvor(c.levi, kljuc);
    }
    return pronadjiCvor(c.desni, kljuc);
  }

  void deleteKey(K kljuc) { 
    koren = deleteRec(koren, kljuc); 
  }
 
    /* A recursive function to 
      delete an existing key in BST
     */
  Cvor deleteRec(Cvor root, K key){
        /* Base Case: If the tree is empty */
        if (root == null)
            return root;
 
        /* Otherwise, recur down the tree */
        if (key.compareTo(root.kljuc) > 0)
            root.levi = deleteRec(root.levi, key);
        else if (key.compareTo(root.kljuc)<0)
            root.desni = deleteRec(root.desni, key);
 
        // if key is same as root's 
        // key, then This is the
        // node to be deleted
        else {
            // node with only one child or no child
            if (root.levi == null)
                return root.desni;
            else if (root.desni == null)
                return root.levi;
 
            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.kljuc = minValue(root.desni);
 
            // Delete the inorder successor
            root.desni = deleteRec(root.desni, root.kljuc);
        }
 
        return root;
    }
 
    K minValue(Cvor root){
        K minv = root.kljuc;
        while (root.levi != null) 
        {
            minv = root.levi.kljuc;
            root = root.levi;
        }
        return minv;
    }
















































  public  <T extends Comparable<?>> void printNode(Cvor root) {
     int maxLevel = maxLevel(root);

     printNodeInternal(Collections.singletonList(root), 1, maxLevel);
 }

 private  <T extends Comparable<?>> void printNodeInternal(List<Cvor> nodes, int level, int maxLevel) {
     if (nodes.isEmpty() || isAllElementsNull(nodes))
         return;

     int floor = maxLevel - level;
     int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
     int firstSpaces = (int) Math.pow(2, (floor)) - 1;
     int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

     printWhitespaces(firstSpaces);

     List<Cvor> newNodes = new ArrayList<Cvor>();
     for (Cvor node : nodes) {
         if (node != null) {
             System.out.print(node.kljuc);
             newNodes.add(node.levi);
             newNodes.add(node.desni);
         } else {
             newNodes.add(null);
             newNodes.add(null);
             System.out.print(" ");
         }

         printWhitespaces(betweenSpaces);
     }
     System.out.println("");

     for (int i = 1; i <= endgeLines; i++) {
         for (int j = 0; j < nodes.size(); j++) {
             printWhitespaces(firstSpaces - i);
             if (nodes.get(j) == null) {
                 printWhitespaces(endgeLines + endgeLines + i + 1);
                 continue;
             }

             if (nodes.get(j).levi != null)
                 System.out.print("/");
             else
                 printWhitespaces(1);

             printWhitespaces(i + i - 1+5);

             if (nodes.get(j).desni != null)
                 System.out.print("\\");
             else
                 printWhitespaces(1);

             printWhitespaces(endgeLines + endgeLines - i);
         }

         System.out.println("");
     }

     printNodeInternal(newNodes, level + 1, maxLevel);
 }

 private  void printWhitespaces(int count) {
     for (int i = 0; i < count; i++)
         System.out.print(" ");
 }

 private  <T extends Comparable<?>> int maxLevel(Cvor node) {
     if (node == null)
         return 0;

     return Math.max(maxLevel(node.levi), maxLevel(node.desni)) + 1;
 }

 private  <T> boolean isAllElementsNull(List<T> list) {
     for (Object object : list) {
         if (object != null)
             return false;
     }

     return true;
 }

}
